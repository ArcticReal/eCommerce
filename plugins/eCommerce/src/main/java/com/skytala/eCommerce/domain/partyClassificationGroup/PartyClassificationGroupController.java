package com.skytala.eCommerce.domain.partyClassificationGroup;

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
import com.skytala.eCommerce.domain.partyClassificationGroup.command.AddPartyClassificationGroup;
import com.skytala.eCommerce.domain.partyClassificationGroup.command.DeletePartyClassificationGroup;
import com.skytala.eCommerce.domain.partyClassificationGroup.command.UpdatePartyClassificationGroup;
import com.skytala.eCommerce.domain.partyClassificationGroup.event.PartyClassificationGroupAdded;
import com.skytala.eCommerce.domain.partyClassificationGroup.event.PartyClassificationGroupDeleted;
import com.skytala.eCommerce.domain.partyClassificationGroup.event.PartyClassificationGroupFound;
import com.skytala.eCommerce.domain.partyClassificationGroup.event.PartyClassificationGroupUpdated;
import com.skytala.eCommerce.domain.partyClassificationGroup.mapper.PartyClassificationGroupMapper;
import com.skytala.eCommerce.domain.partyClassificationGroup.model.PartyClassificationGroup;
import com.skytala.eCommerce.domain.partyClassificationGroup.query.FindPartyClassificationGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyClassificationGroups")
public class PartyClassificationGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyClassificationGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyClassificationGroup
	 * @return a List with the PartyClassificationGroups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyClassificationGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyClassificationGroupsBy query = new FindPartyClassificationGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyClassificationGroup> partyClassificationGroups =((PartyClassificationGroupFound) Scheduler.execute(query).data()).getPartyClassificationGroups();

		if (partyClassificationGroups.size() == 1) {
			return ResponseEntity.ok().body(partyClassificationGroups.get(0));
		}

		return ResponseEntity.ok().body(partyClassificationGroups);

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
	public ResponseEntity<Object> createPartyClassificationGroup(HttpServletRequest request) throws Exception {

		PartyClassificationGroup partyClassificationGroupToBeAdded = new PartyClassificationGroup();
		try {
			partyClassificationGroupToBeAdded = PartyClassificationGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyClassificationGroup(partyClassificationGroupToBeAdded);

	}

	/**
	 * creates a new PartyClassificationGroup entry in the ofbiz database
	 * 
	 * @param partyClassificationGroupToBeAdded
	 *            the PartyClassificationGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyClassificationGroup(@RequestBody PartyClassificationGroup partyClassificationGroupToBeAdded) throws Exception {

		AddPartyClassificationGroup command = new AddPartyClassificationGroup(partyClassificationGroupToBeAdded);
		PartyClassificationGroup partyClassificationGroup = ((PartyClassificationGroupAdded) Scheduler.execute(command).data()).getAddedPartyClassificationGroup();
		
		if (partyClassificationGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyClassificationGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyClassificationGroup could not be created.");
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
	public boolean updatePartyClassificationGroup(HttpServletRequest request) throws Exception {

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

		PartyClassificationGroup partyClassificationGroupToBeUpdated = new PartyClassificationGroup();

		try {
			partyClassificationGroupToBeUpdated = PartyClassificationGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyClassificationGroup(partyClassificationGroupToBeUpdated, partyClassificationGroupToBeUpdated.getPartyClassificationGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyClassificationGroup with the specific Id
	 * 
	 * @param partyClassificationGroupToBeUpdated
	 *            the PartyClassificationGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyClassificationGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyClassificationGroup(@RequestBody PartyClassificationGroup partyClassificationGroupToBeUpdated,
			@PathVariable String partyClassificationGroupId) throws Exception {

		partyClassificationGroupToBeUpdated.setPartyClassificationGroupId(partyClassificationGroupId);

		UpdatePartyClassificationGroup command = new UpdatePartyClassificationGroup(partyClassificationGroupToBeUpdated);

		try {
			if(((PartyClassificationGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyClassificationGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String partyClassificationGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyClassificationGroupId", partyClassificationGroupId);
		try {

			Object foundPartyClassificationGroup = findPartyClassificationGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyClassificationGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyClassificationGroupId}")
	public ResponseEntity<Object> deletePartyClassificationGroupByIdUpdated(@PathVariable String partyClassificationGroupId) throws Exception {
		DeletePartyClassificationGroup command = new DeletePartyClassificationGroup(partyClassificationGroupId);

		try {
			if (((PartyClassificationGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyClassificationGroup could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyClassificationGroup/\" plus one of the following: "
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
