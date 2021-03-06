package com.skytala.eCommerce.domain.party.relations.communicationEvent.control.purpose;

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
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.purpose.AddCommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.purpose.DeleteCommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.purpose.UpdateCommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose.CommunicationEventPurposeAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose.CommunicationEventPurposeDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose.CommunicationEventPurposeFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose.CommunicationEventPurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.purpose.CommunicationEventPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.query.purpose.FindCommunicationEventPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/communicationEvent/communicationEventPurposes")
public class CommunicationEventPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventPurpose
	 * @return a List with the CommunicationEventPurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventPurpose>> findCommunicationEventPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventPurposesBy query = new FindCommunicationEventPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventPurpose> communicationEventPurposes =((CommunicationEventPurposeFound) Scheduler.execute(query).data()).getCommunicationEventPurposes();

		return ResponseEntity.ok().body(communicationEventPurposes);

	}

	/**
	 * creates a new CommunicationEventPurpose entry in the ofbiz database
	 * 
	 * @param communicationEventPurposeToBeAdded
	 *            the CommunicationEventPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommunicationEventPurpose> createCommunicationEventPurpose(@RequestBody CommunicationEventPurpose communicationEventPurposeToBeAdded) throws Exception {

		AddCommunicationEventPurpose command = new AddCommunicationEventPurpose(communicationEventPurposeToBeAdded);
		CommunicationEventPurpose communicationEventPurpose = ((CommunicationEventPurposeAdded) Scheduler.execute(command).data()).getAddedCommunicationEventPurpose();
		
		if (communicationEventPurpose != null) 
			return successful(communicationEventPurpose);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommunicationEventPurpose with the specific Id
	 * 
	 * @param communicationEventPurposeToBeUpdated
	 *            the CommunicationEventPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommunicationEventPurpose(@RequestBody CommunicationEventPurpose communicationEventPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventPurposeToBeUpdated.setnull(null);

		UpdateCommunicationEventPurpose command = new UpdateCommunicationEventPurpose(communicationEventPurposeToBeUpdated);

		try {
			if(((CommunicationEventPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventPurposeId}")
	public ResponseEntity<CommunicationEventPurpose> findById(@PathVariable String communicationEventPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventPurposeId", communicationEventPurposeId);
		try {

			List<CommunicationEventPurpose> foundCommunicationEventPurpose = findCommunicationEventPurposesBy(requestParams).getBody();
			if(foundCommunicationEventPurpose.size()==1){				return successful(foundCommunicationEventPurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventPurposeId}")
	public ResponseEntity<String> deleteCommunicationEventPurposeByIdUpdated(@PathVariable String communicationEventPurposeId) throws Exception {
		DeleteCommunicationEventPurpose command = new DeleteCommunicationEventPurpose(communicationEventPurposeId);

		try {
			if (((CommunicationEventPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
