package com.skytala.eCommerce.domain.party.relations.party.control.invitation;

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
import com.skytala.eCommerce.domain.party.relations.party.command.invitation.AddPartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.command.invitation.DeletePartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.command.invitation.UpdatePartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationFound;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitation.PartyInvitationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitation.PartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.query.invitation.FindPartyInvitationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyInvitations")
public class PartyInvitationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyInvitationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyInvitation
	 * @return a List with the PartyInvitations
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyInvitationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyInvitationsBy query = new FindPartyInvitationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyInvitation> partyInvitations =((PartyInvitationFound) Scheduler.execute(query).data()).getPartyInvitations();

		if (partyInvitations.size() == 1) {
			return ResponseEntity.ok().body(partyInvitations.get(0));
		}

		return ResponseEntity.ok().body(partyInvitations);

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
	public ResponseEntity<Object> createPartyInvitation(HttpServletRequest request) throws Exception {

		PartyInvitation partyInvitationToBeAdded = new PartyInvitation();
		try {
			partyInvitationToBeAdded = PartyInvitationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyInvitation(partyInvitationToBeAdded);

	}

	/**
	 * creates a new PartyInvitation entry in the ofbiz database
	 * 
	 * @param partyInvitationToBeAdded
	 *            the PartyInvitation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyInvitation(@RequestBody PartyInvitation partyInvitationToBeAdded) throws Exception {

		AddPartyInvitation command = new AddPartyInvitation(partyInvitationToBeAdded);
		PartyInvitation partyInvitation = ((PartyInvitationAdded) Scheduler.execute(command).data()).getAddedPartyInvitation();
		
		if (partyInvitation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyInvitation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyInvitation could not be created.");
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
	public boolean updatePartyInvitation(HttpServletRequest request) throws Exception {

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

		PartyInvitation partyInvitationToBeUpdated = new PartyInvitation();

		try {
			partyInvitationToBeUpdated = PartyInvitationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyInvitation(partyInvitationToBeUpdated, partyInvitationToBeUpdated.getPartyInvitationId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyInvitation with the specific Id
	 * 
	 * @param partyInvitationToBeUpdated
	 *            the PartyInvitation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyInvitationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyInvitation(@RequestBody PartyInvitation partyInvitationToBeUpdated,
			@PathVariable String partyInvitationId) throws Exception {

		partyInvitationToBeUpdated.setPartyInvitationId(partyInvitationId);

		UpdatePartyInvitation command = new UpdatePartyInvitation(partyInvitationToBeUpdated);

		try {
			if(((PartyInvitationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyInvitationId}")
	public ResponseEntity<Object> findById(@PathVariable String partyInvitationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyInvitationId", partyInvitationId);
		try {

			Object foundPartyInvitation = findPartyInvitationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyInvitation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyInvitationId}")
	public ResponseEntity<Object> deletePartyInvitationByIdUpdated(@PathVariable String partyInvitationId) throws Exception {
		DeletePartyInvitation command = new DeletePartyInvitation(partyInvitationId);

		try {
			if (((PartyInvitationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyInvitation could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyInvitation/\" plus one of the following: "
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
