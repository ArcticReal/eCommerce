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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.notFound;
import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.successful;

@RestController
@RequestMapping("/party/persons")
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
	public ResponseEntity<List<Person>> findPersonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPersonsBy query = new FindPersonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Person> persons =((PersonFound) Scheduler.execute(query).data()).getPersons();



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
	public ResponseEntity<Person> createPerson(HttpServletRequest request) throws Exception {

		Person personToBeAdded = new Person();
		try {
			personToBeAdded = PersonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
	public ResponseEntity<Person> createPerson(@RequestBody Person personToBeAdded) throws Exception {

		AddPerson command = new AddPerson(personToBeAdded);
		Person person = ((PersonAdded) Scheduler.execute(command).data()).getAddedPerson();
		
		if (person != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(person);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
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

	@RequestMapping(method = RequestMethod.GET, value = "/{partyId}")
	public ResponseEntity<Person> findById(@PathVariable String partyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyId", partyId);
		try {

			List<Person> foundPerson = findPersonsBy(requestParams).getBody();
			if(foundPerson.size()==1)
				return successful(foundPerson.get(0));
			else
				return notFound();
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

}
