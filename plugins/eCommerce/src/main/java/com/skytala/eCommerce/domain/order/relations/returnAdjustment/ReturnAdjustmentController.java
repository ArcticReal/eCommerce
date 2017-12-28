package com.skytala.eCommerce.domain.order.relations.returnAdjustment;

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
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.AddReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.DeleteReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.UpdateReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentDeleted;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentUpdated;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.mapper.ReturnAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.ReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.query.FindReturnAdjustmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnAdjustments")
public class ReturnAdjustmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnAdjustmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnAdjustment
	 * @return a List with the ReturnAdjustments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnAdjustment>> findReturnAdjustmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnAdjustmentsBy query = new FindReturnAdjustmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnAdjustment> returnAdjustments =((ReturnAdjustmentFound) Scheduler.execute(query).data()).getReturnAdjustments();

		return ResponseEntity.ok().body(returnAdjustments);

	}

	/**
	 * creates a new ReturnAdjustment entry in the ofbiz database
	 * 
	 * @param returnAdjustmentToBeAdded
	 *            the ReturnAdjustment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnAdjustment> createReturnAdjustment(@RequestBody ReturnAdjustment returnAdjustmentToBeAdded) throws Exception {

		AddReturnAdjustment command = new AddReturnAdjustment(returnAdjustmentToBeAdded);
		ReturnAdjustment returnAdjustment = ((ReturnAdjustmentAdded) Scheduler.execute(command).data()).getAddedReturnAdjustment();
		
		if (returnAdjustment != null) 
			return successful(returnAdjustment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnAdjustment with the specific Id
	 * 
	 * @param returnAdjustmentToBeUpdated
	 *            the ReturnAdjustment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnAdjustmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnAdjustment(@RequestBody ReturnAdjustment returnAdjustmentToBeUpdated,
			@PathVariable String returnAdjustmentId) throws Exception {

		returnAdjustmentToBeUpdated.setReturnAdjustmentId(returnAdjustmentId);

		UpdateReturnAdjustment command = new UpdateReturnAdjustment(returnAdjustmentToBeUpdated);

		try {
			if(((ReturnAdjustmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnAdjustmentId}")
	public ResponseEntity<ReturnAdjustment> findById(@PathVariable String returnAdjustmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnAdjustmentId", returnAdjustmentId);
		try {

			List<ReturnAdjustment> foundReturnAdjustment = findReturnAdjustmentsBy(requestParams).getBody();
			if(foundReturnAdjustment.size()==1){				return successful(foundReturnAdjustment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnAdjustmentId}")
	public ResponseEntity<String> deleteReturnAdjustmentByIdUpdated(@PathVariable String returnAdjustmentId) throws Exception {
		DeleteReturnAdjustment command = new DeleteReturnAdjustment(returnAdjustmentId);

		try {
			if (((ReturnAdjustmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
