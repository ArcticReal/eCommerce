package com.skytala.eCommerce.domain.party.relations.party.control.group;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.party.command.group.AddPartyGroup;
import com.skytala.eCommerce.domain.party.relations.party.command.group.DeletePartyGroup;
import com.skytala.eCommerce.domain.party.relations.party.command.group.UpdatePartyGroup;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupFound;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.group.PartyGroupMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;
import com.skytala.eCommerce.domain.party.relations.party.query.group.FindPartyGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyGroups")
public class PartyGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyGroup
	 * @return a List with the PartyGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyGroupsBy query = new FindPartyGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyGroup> partyGroups =((PartyGroupFound) Scheduler.execute(query).data()).getPartyGroups();

		if (partyGroups.size() == 1) {
			return ResponseEntity.ok().body(partyGroups.get(0));
		}

		return ResponseEntity.ok().body(partyGroups);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPartyGroup(HttpServletRequest request) throws Exception {

		PartyGroup partyGroupToBeAdded = new PartyGroup();
		try {
			partyGroupToBeAdded = PartyGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyGroup(partyGroupToBeAdded);

	}

	/**
	 * creates a new PartyGroup entry in the ofbiz database
	 * 
	 * @param partyGroupToBeAdded
	 *            the PartyGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyGroup(@RequestBody PartyGroup partyGroupToBeAdded) throws Exception {

		AddPartyGroup command = new AddPartyGroup(partyGroupToBeAdded);
		PartyGroup partyGroup = ((PartyGroupAdded) Scheduler.execute(command).data()).getAddedPartyGroup();
		
		if (partyGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyGroup could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePartyGroup(HttpServletRequest request) throws Exception {

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

		PartyGroup partyGroupToBeUpdated = new PartyGroup();

		try {
			partyGroupToBeUpdated = PartyGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyGroup(partyGroupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyGroup with the specific Id
	 * 
	 * @param partyGroupToBeUpdated
	 *            the PartyGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyGroup(@RequestBody PartyGroup partyGroupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyGroupToBeUpdated.setnull(null);

		UpdatePartyGroup command = new UpdatePartyGroup(partyGroupToBeUpdated);

		try {
			if(((PartyGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String partyGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyGroupId", partyGroupId);
		try {

			Object foundPartyGroup = findPartyGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyGroupId}")
	public ResponseEntity<Object> deletePartyGroupByIdUpdated(@PathVariable String partyGroupId) throws Exception {
		DeletePartyGroup command = new DeletePartyGroup(partyGroupId);

		try {
			if (((PartyGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyGroup could not be deleted");

	}

}
