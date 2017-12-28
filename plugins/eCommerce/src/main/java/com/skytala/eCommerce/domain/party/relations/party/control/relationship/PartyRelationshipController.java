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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyRelationships")
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
	@GetMapping("/find")
	public ResponseEntity<List<PartyRelationship>> findPartyRelationshipsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRelationshipsBy query = new FindPartyRelationshipsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRelationship> partyRelationships =((PartyRelationshipFound) Scheduler.execute(query).data()).getPartyRelationships();

		return ResponseEntity.ok().body(partyRelationships);

	}

	/**
	 * creates a new PartyRelationship entry in the ofbiz database
	 * 
	 * @param partyRelationshipToBeAdded
	 *            the PartyRelationship thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyRelationship> createPartyRelationship(@RequestBody PartyRelationship partyRelationshipToBeAdded) throws Exception {

		AddPartyRelationship command = new AddPartyRelationship(partyRelationshipToBeAdded);
		PartyRelationship partyRelationship = ((PartyRelationshipAdded) Scheduler.execute(command).data()).getAddedPartyRelationship();
		
		if (partyRelationship != null) 
			return successful(partyRelationship);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyRelationship(@RequestBody PartyRelationship partyRelationshipToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyRelationshipToBeUpdated.setnull(null);

		UpdatePartyRelationship command = new UpdatePartyRelationship(partyRelationshipToBeUpdated);

		try {
			if(((PartyRelationshipUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyRelationshipId}")
	public ResponseEntity<PartyRelationship> findById(@PathVariable String partyRelationshipId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRelationshipId", partyRelationshipId);
		try {

			List<PartyRelationship> foundPartyRelationship = findPartyRelationshipsBy(requestParams).getBody();
			if(foundPartyRelationship.size()==1){				return successful(foundPartyRelationship.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyRelationshipId}")
	public ResponseEntity<String> deletePartyRelationshipByIdUpdated(@PathVariable String partyRelationshipId) throws Exception {
		DeletePartyRelationship command = new DeletePartyRelationship(partyRelationshipId);

		try {
			if (((PartyRelationshipDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
