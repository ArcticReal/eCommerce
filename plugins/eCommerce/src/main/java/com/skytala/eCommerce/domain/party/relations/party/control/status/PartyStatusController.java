package com.skytala.eCommerce.domain.party.relations.party.control.status;

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
import com.skytala.eCommerce.domain.party.relations.party.command.status.AddPartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.command.status.DeletePartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.command.status.UpdatePartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusFound;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.status.PartyStatusMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.status.PartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.query.status.FindPartyStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyStatuss")
public class PartyStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyStatus
	 * @return a List with the PartyStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyStatus>> findPartyStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyStatussBy query = new FindPartyStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyStatus> partyStatuss =((PartyStatusFound) Scheduler.execute(query).data()).getPartyStatuss();

		return ResponseEntity.ok().body(partyStatuss);

	}

	/**
	 * creates a new PartyStatus entry in the ofbiz database
	 * 
	 * @param partyStatusToBeAdded
	 *            the PartyStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyStatus> createPartyStatus(@RequestBody PartyStatus partyStatusToBeAdded) throws Exception {

		AddPartyStatus command = new AddPartyStatus(partyStatusToBeAdded);
		PartyStatus partyStatus = ((PartyStatusAdded) Scheduler.execute(command).data()).getAddedPartyStatus();
		
		if (partyStatus != null) 
			return successful(partyStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyStatus with the specific Id
	 * 
	 * @param partyStatusToBeUpdated
	 *            the PartyStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyStatus(@RequestBody PartyStatus partyStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyStatusToBeUpdated.setnull(null);

		UpdatePartyStatus command = new UpdatePartyStatus(partyStatusToBeUpdated);

		try {
			if(((PartyStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyStatusId}")
	public ResponseEntity<PartyStatus> findById(@PathVariable String partyStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyStatusId", partyStatusId);
		try {

			List<PartyStatus> foundPartyStatus = findPartyStatussBy(requestParams).getBody();
			if(foundPartyStatus.size()==1){				return successful(foundPartyStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyStatusId}")
	public ResponseEntity<String> deletePartyStatusByIdUpdated(@PathVariable String partyStatusId) throws Exception {
		DeletePartyStatus command = new DeletePartyStatus(partyStatusId);

		try {
			if (((PartyStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
