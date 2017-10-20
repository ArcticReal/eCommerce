package com.skytala.eCommerce.domain.party.relations.party.control.invitationRoleAssoc;

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
import com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc.AddPartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc.DeletePartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc.UpdatePartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocFound;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationRoleAssoc.PartyInvitationRoleAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.query.invitationRoleAssoc.FindPartyInvitationRoleAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/partyInvitationRoleAssocs")
public class PartyInvitationRoleAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyInvitationRoleAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyInvitationRoleAssoc
	 * @return a List with the PartyInvitationRoleAssocs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyInvitationRoleAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyInvitationRoleAssocsBy query = new FindPartyInvitationRoleAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyInvitationRoleAssoc> partyInvitationRoleAssocs =((PartyInvitationRoleAssocFound) Scheduler.execute(query).data()).getPartyInvitationRoleAssocs();

		if (partyInvitationRoleAssocs.size() == 1) {
			return ResponseEntity.ok().body(partyInvitationRoleAssocs.get(0));
		}

		return ResponseEntity.ok().body(partyInvitationRoleAssocs);

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
	public ResponseEntity<Object> createPartyInvitationRoleAssoc(HttpServletRequest request) throws Exception {

		PartyInvitationRoleAssoc partyInvitationRoleAssocToBeAdded = new PartyInvitationRoleAssoc();
		try {
			partyInvitationRoleAssocToBeAdded = PartyInvitationRoleAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyInvitationRoleAssoc(partyInvitationRoleAssocToBeAdded);

	}

	/**
	 * creates a new PartyInvitationRoleAssoc entry in the ofbiz database
	 * 
	 * @param partyInvitationRoleAssocToBeAdded
	 *            the PartyInvitationRoleAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyInvitationRoleAssoc(@RequestBody PartyInvitationRoleAssoc partyInvitationRoleAssocToBeAdded) throws Exception {

		AddPartyInvitationRoleAssoc command = new AddPartyInvitationRoleAssoc(partyInvitationRoleAssocToBeAdded);
		PartyInvitationRoleAssoc partyInvitationRoleAssoc = ((PartyInvitationRoleAssocAdded) Scheduler.execute(command).data()).getAddedPartyInvitationRoleAssoc();
		
		if (partyInvitationRoleAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyInvitationRoleAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyInvitationRoleAssoc could not be created.");
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
	public boolean updatePartyInvitationRoleAssoc(HttpServletRequest request) throws Exception {

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

		PartyInvitationRoleAssoc partyInvitationRoleAssocToBeUpdated = new PartyInvitationRoleAssoc();

		try {
			partyInvitationRoleAssocToBeUpdated = PartyInvitationRoleAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyInvitationRoleAssoc(partyInvitationRoleAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyInvitationRoleAssoc with the specific Id
	 * 
	 * @param partyInvitationRoleAssocToBeUpdated
	 *            the PartyInvitationRoleAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyInvitationRoleAssoc(@RequestBody PartyInvitationRoleAssoc partyInvitationRoleAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyInvitationRoleAssocToBeUpdated.setnull(null);

		UpdatePartyInvitationRoleAssoc command = new UpdatePartyInvitationRoleAssoc(partyInvitationRoleAssocToBeUpdated);

		try {
			if(((PartyInvitationRoleAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyInvitationRoleAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String partyInvitationRoleAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyInvitationRoleAssocId", partyInvitationRoleAssocId);
		try {

			Object foundPartyInvitationRoleAssoc = findPartyInvitationRoleAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyInvitationRoleAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyInvitationRoleAssocId}")
	public ResponseEntity<Object> deletePartyInvitationRoleAssocByIdUpdated(@PathVariable String partyInvitationRoleAssocId) throws Exception {
		DeletePartyInvitationRoleAssoc command = new DeletePartyInvitationRoleAssoc(partyInvitationRoleAssocId);

		try {
			if (((PartyInvitationRoleAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyInvitationRoleAssoc could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyInvitationRoleAssoc/\" plus one of the following: "
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
