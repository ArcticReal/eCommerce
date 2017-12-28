package com.skytala.eCommerce.domain.party.relations.contactMech.control.partyPurpose;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.partyPurpose.AddPartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.partyPurpose.DeletePartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.partyPurpose.UpdatePartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.partyPurpose.PartyContactMechPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.partyPurpose.PartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.partyPurpose.FindPartyContactMechPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/partyContactMechPurposes")
public class PartyContactMechPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContactMechPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContactMechPurpose
	 * @return a List with the PartyContactMechPurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyContactMechPurpose>> findPartyContactMechPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContactMechPurposesBy query = new FindPartyContactMechPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContactMechPurpose> partyContactMechPurposes =((PartyContactMechPurposeFound) Scheduler.execute(query).data()).getPartyContactMechPurposes();

		return ResponseEntity.ok().body(partyContactMechPurposes);

	}

	/**
	 * creates a new PartyContactMechPurpose entry in the ofbiz database
	 * 
	 * @param partyContactMechPurposeToBeAdded
	 *            the PartyContactMechPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyContactMechPurpose> createPartyContactMechPurpose(@RequestBody PartyContactMechPurpose partyContactMechPurposeToBeAdded) throws Exception {

		AddPartyContactMechPurpose command = new AddPartyContactMechPurpose(partyContactMechPurposeToBeAdded);
		PartyContactMechPurpose partyContactMechPurpose = ((PartyContactMechPurposeAdded) Scheduler.execute(command).data()).getAddedPartyContactMechPurpose();
		
		if (partyContactMechPurpose != null) 
			return successful(partyContactMechPurpose);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyContactMechPurpose with the specific Id
	 * 
	 * @param partyContactMechPurposeToBeUpdated
	 *            the PartyContactMechPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyContactMechPurpose(@RequestBody PartyContactMechPurpose partyContactMechPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyContactMechPurposeToBeUpdated.setnull(null);

		UpdatePartyContactMechPurpose command = new UpdatePartyContactMechPurpose(partyContactMechPurposeToBeUpdated);

		try {
			if(((PartyContactMechPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyContactMechPurposeId}")
	public ResponseEntity<PartyContactMechPurpose> findById(@PathVariable String partyContactMechPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContactMechPurposeId", partyContactMechPurposeId);
		try {

			List<PartyContactMechPurpose> foundPartyContactMechPurpose = findPartyContactMechPurposesBy(requestParams).getBody();
			if(foundPartyContactMechPurpose.size()==1){				return successful(foundPartyContactMechPurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyContactMechPurposeId}")
	public ResponseEntity<String> deletePartyContactMechPurposeByIdUpdated(@PathVariable String partyContactMechPurposeId) throws Exception {
		DeletePartyContactMechPurpose command = new DeletePartyContactMechPurpose(partyContactMechPurposeId);

		try {
			if (((PartyContactMechPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
