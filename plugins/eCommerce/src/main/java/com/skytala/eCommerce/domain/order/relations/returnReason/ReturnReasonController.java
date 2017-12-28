package com.skytala.eCommerce.domain.order.relations.returnReason;

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
import com.skytala.eCommerce.domain.order.relations.returnReason.command.AddReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.command.DeleteReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.command.UpdateReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonAdded;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonDeleted;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonFound;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonUpdated;
import com.skytala.eCommerce.domain.order.relations.returnReason.mapper.ReturnReasonMapper;
import com.skytala.eCommerce.domain.order.relations.returnReason.model.ReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.query.FindReturnReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnReasons")
public class ReturnReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnReason
	 * @return a List with the ReturnReasons
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnReason>> findReturnReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnReasonsBy query = new FindReturnReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnReason> returnReasons =((ReturnReasonFound) Scheduler.execute(query).data()).getReturnReasons();

		return ResponseEntity.ok().body(returnReasons);

	}

	/**
	 * creates a new ReturnReason entry in the ofbiz database
	 * 
	 * @param returnReasonToBeAdded
	 *            the ReturnReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnReason> createReturnReason(@RequestBody ReturnReason returnReasonToBeAdded) throws Exception {

		AddReturnReason command = new AddReturnReason(returnReasonToBeAdded);
		ReturnReason returnReason = ((ReturnReasonAdded) Scheduler.execute(command).data()).getAddedReturnReason();
		
		if (returnReason != null) 
			return successful(returnReason);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnReason with the specific Id
	 * 
	 * @param returnReasonToBeUpdated
	 *            the ReturnReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnReasonId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnReason(@RequestBody ReturnReason returnReasonToBeUpdated,
			@PathVariable String returnReasonId) throws Exception {

		returnReasonToBeUpdated.setReturnReasonId(returnReasonId);

		UpdateReturnReason command = new UpdateReturnReason(returnReasonToBeUpdated);

		try {
			if(((ReturnReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnReasonId}")
	public ResponseEntity<ReturnReason> findById(@PathVariable String returnReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnReasonId", returnReasonId);
		try {

			List<ReturnReason> foundReturnReason = findReturnReasonsBy(requestParams).getBody();
			if(foundReturnReason.size()==1){				return successful(foundReturnReason.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnReasonId}")
	public ResponseEntity<String> deleteReturnReasonByIdUpdated(@PathVariable String returnReasonId) throws Exception {
		DeleteReturnReason command = new DeleteReturnReason(returnReasonId);

		try {
			if (((ReturnReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
