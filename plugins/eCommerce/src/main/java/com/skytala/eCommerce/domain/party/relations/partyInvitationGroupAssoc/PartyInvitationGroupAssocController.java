package com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc;

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
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.command.AddPartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.command.DeletePartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.command.UpdatePartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.event.PartyInvitationGroupAssocAdded;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.event.PartyInvitationGroupAssocDeleted;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.event.PartyInvitationGroupAssocFound;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.event.PartyInvitationGroupAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.mapper.PartyInvitationGroupAssocMapper;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.model.PartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.query.FindPartyInvitationGroupAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyInvitationGroupAssocs")
public class PartyInvitationGroupAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyInvitationGroupAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyInvitationGroupAssoc
	 * @return a List with the PartyInvitationGroupAssocs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyInvitationGroupAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyInvitationGroupAssocsBy query = new FindPartyInvitationGroupAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyInvitationGroupAssoc> partyInvitationGroupAssocs =((PartyInvitationGroupAssocFound) Scheduler.execute(query).data()).getPartyInvitationGroupAssocs();

		if (partyInvitationGroupAssocs.size() == 1) {
			return ResponseEntity.ok().body(partyInvitationGroupAssocs.get(0));
		}

		return ResponseEntity.ok().body(partyInvitationGroupAssocs);

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
	public ResponseEntity<Object> createPartyInvitationGroupAssoc(HttpServletRequest request) throws Exception {

		PartyInvitationGroupAssoc partyInvitationGroupAssocToBeAdded = new PartyInvitationGroupAssoc();
		try {
			partyInvitationGroupAssocToBeAdded = PartyInvitationGroupAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyInvitationGroupAssoc(partyInvitationGroupAssocToBeAdded);

	}

	/**
	 * creates a new PartyInvitationGroupAssoc entry in the ofbiz database
	 * 
	 * @param partyInvitationGroupAssocToBeAdded
	 *            the PartyInvitationGroupAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyInvitationGroupAssoc(@RequestBody PartyInvitationGroupAssoc partyInvitationGroupAssocToBeAdded) throws Exception {

		AddPartyInvitationGroupAssoc command = new AddPartyInvitationGroupAssoc(partyInvitationGroupAssocToBeAdded);
		PartyInvitationGroupAssoc partyInvitationGroupAssoc = ((PartyInvitationGroupAssocAdded) Scheduler.execute(command).data()).getAddedPartyInvitationGroupAssoc();
		
		if (partyInvitationGroupAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyInvitationGroupAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyInvitationGroupAssoc could not be created.");
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
	public boolean updatePartyInvitationGroupAssoc(HttpServletRequest request) throws Exception {

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

		PartyInvitationGroupAssoc partyInvitationGroupAssocToBeUpdated = new PartyInvitationGroupAssoc();

		try {
			partyInvitationGroupAssocToBeUpdated = PartyInvitationGroupAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyInvitationGroupAssoc(partyInvitationGroupAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyInvitationGroupAssoc with the specific Id
	 * 
	 * @param partyInvitationGroupAssocToBeUpdated
	 *            the PartyInvitationGroupAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyInvitationGroupAssoc(@RequestBody PartyInvitationGroupAssoc partyInvitationGroupAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyInvitationGroupAssocToBeUpdated.setnull(null);

		UpdatePartyInvitationGroupAssoc command = new UpdatePartyInvitationGroupAssoc(partyInvitationGroupAssocToBeUpdated);

		try {
			if(((PartyInvitationGroupAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyInvitationGroupAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String partyInvitationGroupAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyInvitationGroupAssocId", partyInvitationGroupAssocId);
		try {

			Object foundPartyInvitationGroupAssoc = findPartyInvitationGroupAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyInvitationGroupAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyInvitationGroupAssocId}")
	public ResponseEntity<Object> deletePartyInvitationGroupAssocByIdUpdated(@PathVariable String partyInvitationGroupAssocId) throws Exception {
		DeletePartyInvitationGroupAssoc command = new DeletePartyInvitationGroupAssoc(partyInvitationGroupAssocId);

		try {
			if (((PartyInvitationGroupAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyInvitationGroupAssoc could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyInvitationGroupAssoc/\" plus one of the following: "
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
