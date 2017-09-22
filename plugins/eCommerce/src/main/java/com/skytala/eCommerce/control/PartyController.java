package com.skytala.eCommerce.control;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddParty;
import com.skytala.eCommerce.command.DeleteParty;
import com.skytala.eCommerce.command.UpdateParty;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.entity.PartyMapper;
import com.skytala.eCommerce.event.PartyAdded;
import com.skytala.eCommerce.event.PartyDeleted;
import com.skytala.eCommerce.event.PartyFound;
import com.skytala.eCommerce.event.PartyUpdated;
import com.skytala.eCommerce.query.FindPartysBy;

@RestController
@RequestMapping("/partys")
public class PartyController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<Party>> queryReturnVal = new HashMap<>();
	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);

	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Party
	 * @return a List with the Partys
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartysBy(@RequestParam Map<String, String> allRequestParams) {

		FindPartysBy query = new FindPartysBy(allRequestParams);

		int usedTicketId;

		synchronized (PartyController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PartyFound.class,
				event -> sendPartysFoundMessage(((PartyFound) event).getPartys(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return ResponseEntity.ok().body(queryReturnVal.remove(usedTicketId));

	}

	public void sendPartysFoundMessage(List<Party> partys, int usedTicketId) {
		queryReturnVal.put(usedTicketId, partys);
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
	public boolean createParty(HttpServletRequest request) {

		Party partyToBeAdded = new Party();
		try {
			partyToBeAdded = PartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}

		return this.createParty(partyToBeAdded);

	}

	/**
	 * creates a new Party entry in the ofbiz database
	 * 
	 * @param partyToBeAdded
	 *            the Party thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean createParty(Party partyToBeAdded) {

		AddParty com = new AddParty(partyToBeAdded);
		int usedTicketId;

		synchronized (PartyController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PartyAdded.class,
				event -> sendPartyChangedMessage(((PartyAdded) event).isSuccess(), usedTicketId));

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
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateParty(HttpServletRequest request) {

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

		Party partyToBeUpdated = new Party();

		try {
			partyToBeUpdated = PartyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return updateParty(partyToBeUpdated, partyToBeUpdated.getPartyId());

	}

	/**
	 * Updates the Party with the specific Id
	 * 
	 * @param partyToBeUpdated
	 *            the Party thats to be updated
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyId}/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean updateParty(Party partyToBeUpdated, @PathVariable String partyId) {

		partyToBeUpdated.setPartyId(partyId);

		UpdateParty com = new UpdateParty(partyToBeUpdated);

		int usedTicketId;

		synchronized (PartyController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PartyUpdated.class,
				event -> sendPartyChangedMessage(((PartyUpdated) event).isSuccess(), usedTicketId));

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
	 * removes a Party from the database
	 * 
	 * @deprecated
	 * @param partyId:
	 *            the id of the Party thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deletepartyById(@RequestParam(value = "partyId") String partyId) {

		DeleteParty com = new DeleteParty(partyId);

		int usedTicketId;

		synchronized (PartyController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PartyDeleted.class,
				event -> sendPartyChangedMessage(((PartyDeleted) event).isSuccess(), usedTicketId));

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

	public void sendPartyChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyId}")
	public ResponseEntity<Object> findById(@PathVariable String partyId) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyId", partyId);

		return findPartysBy(requestParams);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyId}")
	public ResponseEntity<Object> deletePartyByIdUpdated(@PathVariable String partyId) {
		DeleteParty com = new DeleteParty(partyId);

		int usedTicketId;

		synchronized (PartyController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PartyDeleted.class,
				event -> sendPartyChangedMessage(((PartyDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error ocuured while processing Command!");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (commandReturnVal.remove(usedTicketId)) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Party was deleted successfully.");

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Party could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/party/\" plus one of the following: "
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
