package com.skytala.eCommerce.domain.party.relations.party.control.classificationGroup;

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
import com.skytala.eCommerce.domain.party.relations.party.command.classificationGroup.AddPartyClassificationGroup;
import com.skytala.eCommerce.domain.party.relations.party.command.classificationGroup.DeletePartyClassificationGroup;
import com.skytala.eCommerce.domain.party.relations.party.command.classificationGroup.UpdatePartyClassificationGroup;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup.PartyClassificationGroupAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup.PartyClassificationGroupDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup.PartyClassificationGroupFound;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup.PartyClassificationGroupUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationGroup.PartyClassificationGroupMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classificationGroup.PartyClassificationGroup;
import com.skytala.eCommerce.domain.party.relations.party.query.classificationGroup.FindPartyClassificationGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyClassificationGroups")
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
	@GetMapping("/find")
	public ResponseEntity<List<PartyClassificationGroup>> findPartyClassificationGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyClassificationGroupsBy query = new FindPartyClassificationGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyClassificationGroup> partyClassificationGroups =((PartyClassificationGroupFound) Scheduler.execute(query).data()).getPartyClassificationGroups();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PartyClassificationGroup> createPartyClassificationGroup(HttpServletRequest request) throws Exception {

		PartyClassificationGroup partyClassificationGroupToBeAdded = new PartyClassificationGroup();
		try {
			partyClassificationGroupToBeAdded = PartyClassificationGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PartyClassificationGroup> createPartyClassificationGroup(@RequestBody PartyClassificationGroup partyClassificationGroupToBeAdded) throws Exception {

		AddPartyClassificationGroup command = new AddPartyClassificationGroup(partyClassificationGroupToBeAdded);
		PartyClassificationGroup partyClassificationGroup = ((PartyClassificationGroupAdded) Scheduler.execute(command).data()).getAddedPartyClassificationGroup();
		
		if (partyClassificationGroup != null) 
			return successful(partyClassificationGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyClassificationGroup(@RequestBody PartyClassificationGroup partyClassificationGroupToBeUpdated,
			@PathVariable String partyClassificationGroupId) throws Exception {

		partyClassificationGroupToBeUpdated.setPartyClassificationGroupId(partyClassificationGroupId);

		UpdatePartyClassificationGroup command = new UpdatePartyClassificationGroup(partyClassificationGroupToBeUpdated);

		try {
			if(((PartyClassificationGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyClassificationGroupId}")
	public ResponseEntity<PartyClassificationGroup> findById(@PathVariable String partyClassificationGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyClassificationGroupId", partyClassificationGroupId);
		try {

			List<PartyClassificationGroup> foundPartyClassificationGroup = findPartyClassificationGroupsBy(requestParams).getBody();
			if(foundPartyClassificationGroup.size()==1){				return successful(foundPartyClassificationGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyClassificationGroupId}")
	public ResponseEntity<String> deletePartyClassificationGroupByIdUpdated(@PathVariable String partyClassificationGroupId) throws Exception {
		DeletePartyClassificationGroup command = new DeletePartyClassificationGroup(partyClassificationGroupId);

		try {
			if (((PartyClassificationGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
