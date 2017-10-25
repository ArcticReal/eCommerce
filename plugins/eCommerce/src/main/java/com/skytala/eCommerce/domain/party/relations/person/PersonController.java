package com.skytala.eCommerce.domain.party.relations.person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.person.command.AddPerson;
import com.skytala.eCommerce.domain.party.relations.person.command.DeletePerson;
import com.skytala.eCommerce.domain.party.relations.person.command.UpdatePerson;
import com.skytala.eCommerce.domain.party.relations.person.event.PersonAdded;
import com.skytala.eCommerce.domain.party.relations.person.event.PersonDeleted;
import com.skytala.eCommerce.domain.party.relations.person.event.PersonFound;
import com.skytala.eCommerce.domain.party.relations.person.event.PersonUpdated;
import com.skytala.eCommerce.domain.party.relations.person.mapper.PersonMapper;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.domain.party.relations.person.query.FindPersonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PersonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Person
	 * @return a List with the Persons
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPersonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPersonsBy query = new FindPersonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Person> persons =((PersonFound) Scheduler.execute(query).data()).getPersons();

		if (persons.size() == 1) {
			return ResponseEntity.ok().body(persons.get(0));
		}

		return ResponseEntity.ok().body(persons);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPerson(HttpServletRequest request) throws Exception {

		Person personToBeAdded = new Person();
		try {
			personToBeAdded = PersonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPerson(personToBeAdded);

	}

	/**
	 * creates a new Person entry in the ofbiz database
	 * 
	 * @param personToBeAdded
	 *            the Person thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPerson(@RequestBody Person personToBeAdded) throws Exception {

		AddPerson command = new AddPerson(personToBeAdded);
		Person person = ((PersonAdded) Scheduler.execute(command).data()).getAddedPerson();
		
		if (person != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(person);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Person could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePerson(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		Person personToBeUpdated = new Person();

		try {
			personToBeUpdated = PersonMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePerson(personToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Person with the specific Id
	 * 
	 * @param personToBeUpdated
	 *            the Person thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePerson(@RequestBody Person personToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		personToBeUpdated.setnull(null);

		UpdatePerson command = new UpdatePerson(personToBeUpdated);

		try {
			if(((PersonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{personId}")
	public ResponseEntity<Object> findById(@PathVariable String personId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("personId", personId);
		try {

			Object foundPerson = findPersonsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPerson);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{personId}")
	public ResponseEntity<Object> deletePersonByIdUpdated(@PathVariable String personId) throws Exception {
		DeletePerson command = new DeletePerson(personId);

		try {
			if (((PersonDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Person could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/person/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
