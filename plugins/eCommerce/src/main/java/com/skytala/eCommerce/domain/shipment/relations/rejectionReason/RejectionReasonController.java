package com.skytala.eCommerce.domain.shipment.relations.rejectionReason;

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
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command.AddRejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command.DeleteRejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.command.UpdateRejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonAdded;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonDeleted;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonFound;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonUpdated;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.mapper.RejectionReasonMapper;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.query.FindRejectionReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/rejectionReasons")
public class RejectionReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RejectionReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RejectionReason
	 * @return a List with the RejectionReasons
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RejectionReason>> findRejectionReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRejectionReasonsBy query = new FindRejectionReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RejectionReason> rejectionReasons =((RejectionReasonFound) Scheduler.execute(query).data()).getRejectionReasons();

		return ResponseEntity.ok().body(rejectionReasons);

	}

	/**
	 * creates a new RejectionReason entry in the ofbiz database
	 * 
	 * @param rejectionReasonToBeAdded
	 *            the RejectionReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RejectionReason> createRejectionReason(@RequestBody RejectionReason rejectionReasonToBeAdded) throws Exception {

		AddRejectionReason command = new AddRejectionReason(rejectionReasonToBeAdded);
		RejectionReason rejectionReason = ((RejectionReasonAdded) Scheduler.execute(command).data()).getAddedRejectionReason();
		
		if (rejectionReason != null) 
			return successful(rejectionReason);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RejectionReason with the specific Id
	 * 
	 * @param rejectionReasonToBeUpdated
	 *            the RejectionReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{rejectionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRejectionReason(@RequestBody RejectionReason rejectionReasonToBeUpdated,
			@PathVariable String rejectionId) throws Exception {

		rejectionReasonToBeUpdated.setRejectionId(rejectionId);

		UpdateRejectionReason command = new UpdateRejectionReason(rejectionReasonToBeUpdated);

		try {
			if(((RejectionReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{rejectionReasonId}")
	public ResponseEntity<RejectionReason> findById(@PathVariable String rejectionReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("rejectionReasonId", rejectionReasonId);
		try {

			List<RejectionReason> foundRejectionReason = findRejectionReasonsBy(requestParams).getBody();
			if(foundRejectionReason.size()==1){				return successful(foundRejectionReason.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{rejectionReasonId}")
	public ResponseEntity<String> deleteRejectionReasonByIdUpdated(@PathVariable String rejectionReasonId) throws Exception {
		DeleteRejectionReason command = new DeleteRejectionReason(rejectionReasonId);

		try {
			if (((RejectionReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
