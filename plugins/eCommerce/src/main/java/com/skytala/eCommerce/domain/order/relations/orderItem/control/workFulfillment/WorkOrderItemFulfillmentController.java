package com.skytala.eCommerce.domain.order.relations.orderItem.control.workFulfillment;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.workFulfillment.AddWorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.workFulfillment.DeleteWorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.workFulfillment.UpdateWorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment.WorkOrderItemFulfillmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment.WorkOrderItemFulfillmentDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment.WorkOrderItemFulfillmentFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.workFulfillment.WorkOrderItemFulfillmentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.workFulfillment.WorkOrderItemFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.workFulfillment.WorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.workFulfillment.FindWorkOrderItemFulfillmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/workOrderItemFulfillments")
public class WorkOrderItemFulfillmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkOrderItemFulfillmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkOrderItemFulfillment
	 * @return a List with the WorkOrderItemFulfillments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkOrderItemFulfillment>> findWorkOrderItemFulfillmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkOrderItemFulfillmentsBy query = new FindWorkOrderItemFulfillmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkOrderItemFulfillment> workOrderItemFulfillments =((WorkOrderItemFulfillmentFound) Scheduler.execute(query).data()).getWorkOrderItemFulfillments();

		return ResponseEntity.ok().body(workOrderItemFulfillments);

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
	public ResponseEntity<WorkOrderItemFulfillment> createWorkOrderItemFulfillment(HttpServletRequest request) throws Exception {

		WorkOrderItemFulfillment workOrderItemFulfillmentToBeAdded = new WorkOrderItemFulfillment();
		try {
			workOrderItemFulfillmentToBeAdded = WorkOrderItemFulfillmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkOrderItemFulfillment(workOrderItemFulfillmentToBeAdded);

	}

	/**
	 * creates a new WorkOrderItemFulfillment entry in the ofbiz database
	 * 
	 * @param workOrderItemFulfillmentToBeAdded
	 *            the WorkOrderItemFulfillment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkOrderItemFulfillment> createWorkOrderItemFulfillment(@RequestBody WorkOrderItemFulfillment workOrderItemFulfillmentToBeAdded) throws Exception {

		AddWorkOrderItemFulfillment command = new AddWorkOrderItemFulfillment(workOrderItemFulfillmentToBeAdded);
		WorkOrderItemFulfillment workOrderItemFulfillment = ((WorkOrderItemFulfillmentAdded) Scheduler.execute(command).data()).getAddedWorkOrderItemFulfillment();
		
		if (workOrderItemFulfillment != null) 
			return successful(workOrderItemFulfillment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkOrderItemFulfillment with the specific Id
	 * 
	 * @param workOrderItemFulfillmentToBeUpdated
	 *            the WorkOrderItemFulfillment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkOrderItemFulfillment(@RequestBody WorkOrderItemFulfillment workOrderItemFulfillmentToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		workOrderItemFulfillmentToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateWorkOrderItemFulfillment command = new UpdateWorkOrderItemFulfillment(workOrderItemFulfillmentToBeUpdated);

		try {
			if(((WorkOrderItemFulfillmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workOrderItemFulfillmentId}")
	public ResponseEntity<WorkOrderItemFulfillment> findById(@PathVariable String workOrderItemFulfillmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workOrderItemFulfillmentId", workOrderItemFulfillmentId);
		try {

			List<WorkOrderItemFulfillment> foundWorkOrderItemFulfillment = findWorkOrderItemFulfillmentsBy(requestParams).getBody();
			if(foundWorkOrderItemFulfillment.size()==1){				return successful(foundWorkOrderItemFulfillment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workOrderItemFulfillmentId}")
	public ResponseEntity<String> deleteWorkOrderItemFulfillmentByIdUpdated(@PathVariable String workOrderItemFulfillmentId) throws Exception {
		DeleteWorkOrderItemFulfillment command = new DeleteWorkOrderItemFulfillment(workOrderItemFulfillmentId);

		try {
			if (((WorkOrderItemFulfillmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
