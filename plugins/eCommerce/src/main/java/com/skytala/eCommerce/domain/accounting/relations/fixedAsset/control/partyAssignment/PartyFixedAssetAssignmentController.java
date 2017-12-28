package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.partyAssignment;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment.AddPartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment.DeletePartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment.UpdatePartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.partyAssignment.PartyFixedAssetAssignmentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.partyAssignment.FindPartyFixedAssetAssignmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/partyFixedAssetAssignments")
public class PartyFixedAssetAssignmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyFixedAssetAssignmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyFixedAssetAssignment
	 * @return a List with the PartyFixedAssetAssignments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyFixedAssetAssignment>> findPartyFixedAssetAssignmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyFixedAssetAssignmentsBy query = new FindPartyFixedAssetAssignmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyFixedAssetAssignment> partyFixedAssetAssignments =((PartyFixedAssetAssignmentFound) Scheduler.execute(query).data()).getPartyFixedAssetAssignments();

		return ResponseEntity.ok().body(partyFixedAssetAssignments);

	}

	/**
	 * creates a new PartyFixedAssetAssignment entry in the ofbiz database
	 * 
	 * @param partyFixedAssetAssignmentToBeAdded
	 *            the PartyFixedAssetAssignment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyFixedAssetAssignment> createPartyFixedAssetAssignment(@RequestBody PartyFixedAssetAssignment partyFixedAssetAssignmentToBeAdded) throws Exception {

		AddPartyFixedAssetAssignment command = new AddPartyFixedAssetAssignment(partyFixedAssetAssignmentToBeAdded);
		PartyFixedAssetAssignment partyFixedAssetAssignment = ((PartyFixedAssetAssignmentAdded) Scheduler.execute(command).data()).getAddedPartyFixedAssetAssignment();
		
		if (partyFixedAssetAssignment != null) 
			return successful(partyFixedAssetAssignment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyFixedAssetAssignment with the specific Id
	 * 
	 * @param partyFixedAssetAssignmentToBeUpdated
	 *            the PartyFixedAssetAssignment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyFixedAssetAssignment(@RequestBody PartyFixedAssetAssignment partyFixedAssetAssignmentToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		partyFixedAssetAssignmentToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePartyFixedAssetAssignment command = new UpdatePartyFixedAssetAssignment(partyFixedAssetAssignmentToBeUpdated);

		try {
			if(((PartyFixedAssetAssignmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyFixedAssetAssignmentId}")
	public ResponseEntity<PartyFixedAssetAssignment> findById(@PathVariable String partyFixedAssetAssignmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyFixedAssetAssignmentId", partyFixedAssetAssignmentId);
		try {

			List<PartyFixedAssetAssignment> foundPartyFixedAssetAssignment = findPartyFixedAssetAssignmentsBy(requestParams).getBody();
			if(foundPartyFixedAssetAssignment.size()==1){				return successful(foundPartyFixedAssetAssignment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyFixedAssetAssignmentId}")
	public ResponseEntity<String> deletePartyFixedAssetAssignmentByIdUpdated(@PathVariable String partyFixedAssetAssignmentId) throws Exception {
		DeletePartyFixedAssetAssignment command = new DeletePartyFixedAssetAssignment(partyFixedAssetAssignmentId);

		try {
			if (((PartyFixedAssetAssignmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
