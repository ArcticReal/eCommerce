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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PartyGroup>> findPartyGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyGroupsBy query = new FindPartyGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyGroup> partyGroups =((PartyGroupFound) Scheduler.execute(query).data()).getPartyGroups();

		return ResponseEntity.ok().body(partyGroups);

	}

	/**
	 * creates a new PartyGroup entry in the ofbiz database
	 * 
	 * @param partyGroupToBeAdded
	 *            the PartyGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyGroup> createPartyGroup(@RequestBody PartyGroup partyGroupToBeAdded) throws Exception {

		AddPartyGroup command = new AddPartyGroup(partyGroupToBeAdded);
		PartyGroup partyGroup = ((PartyGroupAdded) Scheduler.execute(command).data()).getAddedPartyGroup();
		
		if (partyGroup != null) 
			return successful(partyGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyGroup(@RequestBody PartyGroup partyGroupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyGroupToBeUpdated.setnull(null);

		UpdatePartyGroup command = new UpdatePartyGroup(partyGroupToBeUpdated);

		try {
			if(((PartyGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyGroupId}")
	public ResponseEntity<PartyGroup> findById(@PathVariable String partyGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyGroupId", partyGroupId);
		try {

			List<PartyGroup> foundPartyGroup = findPartyGroupsBy(requestParams).getBody();
			if(foundPartyGroup.size()==1){				return successful(foundPartyGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyGroupId}")
	public ResponseEntity<String> deletePartyGroupByIdUpdated(@PathVariable String partyGroupId) throws Exception {
		DeletePartyGroup command = new DeletePartyGroup(partyGroupId);

		try {
			if (((PartyGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
