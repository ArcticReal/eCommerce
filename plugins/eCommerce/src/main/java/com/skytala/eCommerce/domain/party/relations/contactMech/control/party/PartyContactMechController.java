package com.skytala.eCommerce.domain.party.relations.contactMech.control.party;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.party.AddPartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.party.DeletePartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.party.UpdatePartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.party.PartyContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.party.FindPartyContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/partyContactMechs")
public class PartyContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContactMech
	 * @return a List with the PartyContactMechs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContactMechsBy query = new FindPartyContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContactMech> partyContactMechs =((PartyContactMechFound) Scheduler.execute(query).data()).getPartyContactMechs();

		if (partyContactMechs.size() == 1) {
			return ResponseEntity.ok().body(partyContactMechs.get(0));
		}

		return ResponseEntity.ok().body(partyContactMechs);

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
	public ResponseEntity<Object> createPartyContactMech(HttpServletRequest request) throws Exception {

		PartyContactMech partyContactMechToBeAdded = new PartyContactMech();
		try {
			partyContactMechToBeAdded = PartyContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyContactMech(partyContactMechToBeAdded);

	}

	/**
	 * creates a new PartyContactMech entry in the ofbiz database
	 * 
	 * @param partyContactMechToBeAdded
	 *            the PartyContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyContactMech(@RequestBody PartyContactMech partyContactMechToBeAdded) throws Exception {

		AddPartyContactMech command = new AddPartyContactMech(partyContactMechToBeAdded);
		PartyContactMech partyContactMech = ((PartyContactMechAdded) Scheduler.execute(command).data()).getAddedPartyContactMech();
		
		if (partyContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyContactMech could not be created.");
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
	public boolean updatePartyContactMech(HttpServletRequest request) throws Exception {

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

		PartyContactMech partyContactMechToBeUpdated = new PartyContactMech();

		try {
			partyContactMechToBeUpdated = PartyContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyContactMech(partyContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyContactMech with the specific Id
	 * 
	 * @param partyContactMechToBeUpdated
	 *            the PartyContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyContactMech(@RequestBody PartyContactMech partyContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyContactMechToBeUpdated.setnull(null);

		UpdatePartyContactMech command = new UpdatePartyContactMech(partyContactMechToBeUpdated);

		try {
			if(((PartyContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String partyContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContactMechId", partyContactMechId);
		try {

			Object foundPartyContactMech = findPartyContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyContactMechId}")
	public ResponseEntity<Object> deletePartyContactMechByIdUpdated(@PathVariable String partyContactMechId) throws Exception {
		DeletePartyContactMech command = new DeletePartyContactMech(partyContactMechId);

		try {
			if (((PartyContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyContactMech could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyContactMech/\" plus one of the following: "
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
