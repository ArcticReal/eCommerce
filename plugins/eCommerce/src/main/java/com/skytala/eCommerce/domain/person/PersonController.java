package com.skytala.eCommerce.domain.person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.person.command.AddPerson;
import com.skytala.eCommerce.domain.person.command.DeletePerson;
import com.skytala.eCommerce.domain.person.command.UpdatePerson;
import com.skytala.eCommerce.domain.person.event.PersonAdded;
import com.skytala.eCommerce.domain.person.event.PersonDeleted;
import com.skytala.eCommerce.domain.person.event.PersonFound;
import com.skytala.eCommerce.domain.person.event.PersonUpdated;
import com.skytala.eCommerce.domain.person.mappper.PersonMapper;
import com.skytala.eCommerce.domain.person.model.Person;
import com.skytala.eCommerce.domain.person.query.FindPersonsBy;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/person")
public class PersonController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<Person>> queryReturnVal = new HashMap<>();
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
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public List<Person> findPersonsBy(@RequestParam Map<String, String> allRequestParams) {

		FindPersonsBy query = new FindPersonsBy(allRequestParams);

		int usedTicketId;

		synchronized (PersonController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PersonFound.class,
				event -> sendPersonsFoundMessage(((PersonFound) event).getPersons(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}

	public void sendPersonsFoundMessage(List<Person> persons, int usedTicketId) {
		queryReturnVal.put(usedTicketId, persons);
	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/x-www-form-urlencoded")
	public boolean createPerson(HttpServletRequest request) {

		Person personToBeAdded = new Person();
		try {
			personToBeAdded = PersonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
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
	public boolean createPerson(Person personToBeAdded) {

		AddPerson com = new AddPerson(personToBeAdded);
		int usedTicketId;

		synchronized (PersonController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PersonAdded.class,
				event -> sendPersonChangedMessage(((PersonAdded) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);

	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePerson(HttpServletRequest request) {

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

		return updatePerson(personToBeUpdated);

	}

	/**
	 * Updates the Person with the specific Id
	 * 
	 * @param personToBeUpdated the Person thats to be updated
	 * @return true on success, false on fail
	 */
	public boolean updatePerson(Person personToBeUpdated) {

		UpdatePerson com = new UpdatePerson(personToBeUpdated);

		int usedTicketId;

		synchronized (PersonController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PersonUpdated.class,
				event -> sendPersonChangedMessage(((PersonUpdated) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	/**
	 * removes a Person from the database
	 * 
	 * @param personId:
	 *            the id of the Person thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deletepersonById(@RequestParam(value = "personId") String personId) {

		DeletePerson com = new DeletePerson(personId);

		int usedTicketId;

		synchronized (PersonController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PersonDeleted.class,
				event -> sendPersonChangedMessage(((PersonDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	public void sendPersonChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(value = (" * "))
	public String returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			return "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri + "\"!\n"
					+ "Please use " + validRequests.get(usedRequest) + "!";

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

		return returnVal;

	}
}
