package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.command.AddWorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.command.DeleteWorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.command.UpdateWorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentAdded;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentDeleted;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentFound;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentUpdated;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.mapper.WorkOrderItemFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.query.FindWorkOrderItemFulfillmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workOrderItemFulfillments")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkOrderItemFulfillmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkOrderItemFulfillmentsBy query = new FindWorkOrderItemFulfillmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkOrderItemFulfillment> workOrderItemFulfillments =((WorkOrderItemFulfillmentFound) Scheduler.execute(query).data()).getWorkOrderItemFulfillments();

		if (workOrderItemFulfillments.size() == 1) {
			return ResponseEntity.ok().body(workOrderItemFulfillments.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createWorkOrderItemFulfillment(HttpServletRequest request) throws Exception {

		WorkOrderItemFulfillment workOrderItemFulfillmentToBeAdded = new WorkOrderItemFulfillment();
		try {
			workOrderItemFulfillmentToBeAdded = WorkOrderItemFulfillmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createWorkOrderItemFulfillment(@RequestBody WorkOrderItemFulfillment workOrderItemFulfillmentToBeAdded) throws Exception {

		AddWorkOrderItemFulfillment command = new AddWorkOrderItemFulfillment(workOrderItemFulfillmentToBeAdded);
		WorkOrderItemFulfillment workOrderItemFulfillment = ((WorkOrderItemFulfillmentAdded) Scheduler.execute(command).data()).getAddedWorkOrderItemFulfillment();
		
		if (workOrderItemFulfillment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workOrderItemFulfillment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkOrderItemFulfillment could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateWorkOrderItemFulfillment(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		WorkOrderItemFulfillment workOrderItemFulfillmentToBeUpdated = new WorkOrderItemFulfillment();

		try {
			workOrderItemFulfillmentToBeUpdated = WorkOrderItemFulfillmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkOrderItemFulfillment(workOrderItemFulfillmentToBeUpdated, workOrderItemFulfillmentToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkOrderItemFulfillment(@RequestBody WorkOrderItemFulfillment workOrderItemFulfillmentToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		workOrderItemFulfillmentToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateWorkOrderItemFulfillment command = new UpdateWorkOrderItemFulfillment(workOrderItemFulfillmentToBeUpdated);

		try {
			if(((WorkOrderItemFulfillmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workOrderItemFulfillmentId}")
	public ResponseEntity<Object> findById(@PathVariable String workOrderItemFulfillmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workOrderItemFulfillmentId", workOrderItemFulfillmentId);
		try {

			Object foundWorkOrderItemFulfillment = findWorkOrderItemFulfillmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkOrderItemFulfillment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workOrderItemFulfillmentId}")
	public ResponseEntity<Object> deleteWorkOrderItemFulfillmentByIdUpdated(@PathVariable String workOrderItemFulfillmentId) throws Exception {
		DeleteWorkOrderItemFulfillment command = new DeleteWorkOrderItemFulfillment(workOrderItemFulfillmentId);

		try {
			if (((WorkOrderItemFulfillmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkOrderItemFulfillment could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workOrderItemFulfillment/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
