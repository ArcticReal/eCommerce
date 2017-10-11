package com.skytala.eCommerce.domain.party.relations.partyIdentification;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.command.AddPartyIdentification;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.command.DeletePartyIdentification;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.command.UpdatePartyIdentification;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.event.PartyIdentificationAdded;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.event.PartyIdentificationDeleted;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.event.PartyIdentificationFound;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.event.PartyIdentificationUpdated;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.mapper.PartyIdentificationMapper;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.model.PartyIdentification;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.query.FindPartyIdentificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyIdentifications")
public class PartyIdentificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyIdentificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyIdentification
	 * @return a List with the PartyIdentifications
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyIdentificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyIdentificationsBy query = new FindPartyIdentificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyIdentification> partyIdentifications =((PartyIdentificationFound) Scheduler.execute(query).data()).getPartyIdentifications();

		if (partyIdentifications.size() == 1) {
			return ResponseEntity.ok().body(partyIdentifications.get(0));
		}

		return ResponseEntity.ok().body(partyIdentifications);

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
	public ResponseEntity<Object> createPartyIdentification(HttpServletRequest request) throws Exception {

		PartyIdentification partyIdentificationToBeAdded = new PartyIdentification();
		try {
			partyIdentificationToBeAdded = PartyIdentificationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyIdentification(partyIdentificationToBeAdded);

	}

	/**
	 * creates a new PartyIdentification entry in the ofbiz database
	 * 
	 * @param partyIdentificationToBeAdded
	 *            the PartyIdentification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyIdentification(@RequestBody PartyIdentification partyIdentificationToBeAdded) throws Exception {

		AddPartyIdentification command = new AddPartyIdentification(partyIdentificationToBeAdded);
		PartyIdentification partyIdentification = ((PartyIdentificationAdded) Scheduler.execute(command).data()).getAddedPartyIdentification();
		
		if (partyIdentification != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyIdentification);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyIdentification could not be created.");
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
	public boolean updatePartyIdentification(HttpServletRequest request) throws Exception {

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

		PartyIdentification partyIdentificationToBeUpdated = new PartyIdentification();

		try {
			partyIdentificationToBeUpdated = PartyIdentificationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyIdentification(partyIdentificationToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyIdentification with the specific Id
	 * 
	 * @param partyIdentificationToBeUpdated
	 *            the PartyIdentification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyIdentification(@RequestBody PartyIdentification partyIdentificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyIdentificationToBeUpdated.setnull(null);

		UpdatePartyIdentification command = new UpdatePartyIdentification(partyIdentificationToBeUpdated);

		try {
			if(((PartyIdentificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyIdentificationId}")
	public ResponseEntity<Object> findById(@PathVariable String partyIdentificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyIdentificationId", partyIdentificationId);
		try {

			Object foundPartyIdentification = findPartyIdentificationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyIdentification);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyIdentificationId}")
	public ResponseEntity<Object> deletePartyIdentificationByIdUpdated(@PathVariable String partyIdentificationId) throws Exception {
		DeletePartyIdentification command = new DeletePartyIdentification(partyIdentificationId);

		try {
			if (((PartyIdentificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyIdentification could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyIdentification/\" plus one of the following: "
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
