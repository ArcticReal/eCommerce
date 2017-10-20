package com.skytala.eCommerce.domain.party.relations.party.control.relationship;

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
import com.skytala.eCommerce.domain.party.relations.party.command.relationship.AddPartyRelationship;
import com.skytala.eCommerce.domain.party.relations.party.command.relationship.DeletePartyRelationship;
import com.skytala.eCommerce.domain.party.relations.party.command.relationship.UpdatePartyRelationship;
import com.skytala.eCommerce.domain.party.relations.party.event.relationship.PartyRelationshipAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.relationship.PartyRelationshipDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.relationship.PartyRelationshipFound;
import com.skytala.eCommerce.domain.party.relations.party.event.relationship.PartyRelationshipUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.relationship.PartyRelationshipMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.relationship.PartyRelationship;
import com.skytala.eCommerce.domain.party.relations.party.query.relationship.FindPartyRelationshipsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/partyRelationships")
public class PartyRelationshipController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyRelationshipController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyRelationship
	 * @return a List with the PartyRelationships
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyRelationshipsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRelationshipsBy query = new FindPartyRelationshipsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRelationship> partyRelationships =((PartyRelationshipFound) Scheduler.execute(query).data()).getPartyRelationships();

		if (partyRelationships.size() == 1) {
			return ResponseEntity.ok().body(partyRelationships.get(0));
		}

		return ResponseEntity.ok().body(partyRelationships);

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
	public ResponseEntity<Object> createPartyRelationship(HttpServletRequest request) throws Exception {

		PartyRelationship partyRelationshipToBeAdded = new PartyRelationship();
		try {
			partyRelationshipToBeAdded = PartyRelationshipMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyRelationship(partyRelationshipToBeAdded);

	}

	/**
	 * creates a new PartyRelationship entry in the ofbiz database
	 * 
	 * @param partyRelationshipToBeAdded
	 *            the PartyRelationship thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyRelationship(@RequestBody PartyRelationship partyRelationshipToBeAdded) throws Exception {

		AddPartyRelationship command = new AddPartyRelationship(partyRelationshipToBeAdded);
		PartyRelationship partyRelationship = ((PartyRelationshipAdded) Scheduler.execute(command).data()).getAddedPartyRelationship();
		
		if (partyRelationship != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyRelationship);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyRelationship could not be created.");
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
	public boolean updatePartyRelationship(HttpServletRequest request) throws Exception {

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

		PartyRelationship partyRelationshipToBeUpdated = new PartyRelationship();

		try {
			partyRelationshipToBeUpdated = PartyRelationshipMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyRelationship(partyRelationshipToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyRelationship with the specific Id
	 * 
	 * @param partyRelationshipToBeUpdated
	 *            the PartyRelationship thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyRelationship(@RequestBody PartyRelationship partyRelationshipToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyRelationshipToBeUpdated.setnull(null);

		UpdatePartyRelationship command = new UpdatePartyRelationship(partyRelationshipToBeUpdated);

		try {
			if(((PartyRelationshipUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyRelationshipId}")
	public ResponseEntity<Object> findById(@PathVariable String partyRelationshipId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRelationshipId", partyRelationshipId);
		try {

			Object foundPartyRelationship = findPartyRelationshipsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyRelationship);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyRelationshipId}")
	public ResponseEntity<Object> deletePartyRelationshipByIdUpdated(@PathVariable String partyRelationshipId) throws Exception {
		DeletePartyRelationship command = new DeletePartyRelationship(partyRelationshipId);

		try {
			if (((PartyRelationshipDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyRelationship could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyRelationship/\" plus one of the following: "
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
