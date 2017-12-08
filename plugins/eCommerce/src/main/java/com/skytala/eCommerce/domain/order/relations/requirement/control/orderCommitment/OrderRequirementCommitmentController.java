package com.skytala.eCommerce.domain.order.relations.requirement.control.orderCommitment;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.requirement.command.orderCommitment.AddOrderRequirementCommitment;
import com.skytala.eCommerce.domain.order.relations.requirement.command.orderCommitment.DeleteOrderRequirementCommitment;
import com.skytala.eCommerce.domain.order.relations.requirement.command.orderCommitment.UpdateOrderRequirementCommitment;
import com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment.OrderRequirementCommitmentAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment.OrderRequirementCommitmentDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment.OrderRequirementCommitmentFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment.OrderRequirementCommitmentUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.orderCommitment.OrderRequirementCommitmentMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.orderCommitment.OrderRequirementCommitment;
import com.skytala.eCommerce.domain.order.relations.requirement.query.orderCommitment.FindOrderRequirementCommitmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderRequirementCommitments")
public class OrderRequirementCommitmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderRequirementCommitmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderRequirementCommitment
	 * @return a List with the OrderRequirementCommitments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderRequirementCommitmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderRequirementCommitmentsBy query = new FindOrderRequirementCommitmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderRequirementCommitment> orderRequirementCommitments =((OrderRequirementCommitmentFound) Scheduler.execute(query).data()).getOrderRequirementCommitments();

		if (orderRequirementCommitments.size() == 1) {
			return ResponseEntity.ok().body(orderRequirementCommitments.get(0));
		}

		return ResponseEntity.ok().body(orderRequirementCommitments);

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
	public ResponseEntity<Object> createOrderRequirementCommitment(HttpServletRequest request) throws Exception {

		OrderRequirementCommitment orderRequirementCommitmentToBeAdded = new OrderRequirementCommitment();
		try {
			orderRequirementCommitmentToBeAdded = OrderRequirementCommitmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderRequirementCommitment(orderRequirementCommitmentToBeAdded);

	}

	/**
	 * creates a new OrderRequirementCommitment entry in the ofbiz database
	 * 
	 * @param orderRequirementCommitmentToBeAdded
	 *            the OrderRequirementCommitment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderRequirementCommitment(@RequestBody OrderRequirementCommitment orderRequirementCommitmentToBeAdded) throws Exception {

		AddOrderRequirementCommitment command = new AddOrderRequirementCommitment(orderRequirementCommitmentToBeAdded);
		OrderRequirementCommitment orderRequirementCommitment = ((OrderRequirementCommitmentAdded) Scheduler.execute(command).data()).getAddedOrderRequirementCommitment();
		
		if (orderRequirementCommitment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderRequirementCommitment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderRequirementCommitment could not be created.");
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
	public boolean updateOrderRequirementCommitment(HttpServletRequest request) throws Exception {

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

		OrderRequirementCommitment orderRequirementCommitmentToBeUpdated = new OrderRequirementCommitment();

		try {
			orderRequirementCommitmentToBeUpdated = OrderRequirementCommitmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderRequirementCommitment(orderRequirementCommitmentToBeUpdated, orderRequirementCommitmentToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderRequirementCommitment with the specific Id
	 * 
	 * @param orderRequirementCommitmentToBeUpdated
	 *            the OrderRequirementCommitment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderRequirementCommitment(@RequestBody OrderRequirementCommitment orderRequirementCommitmentToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderRequirementCommitmentToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderRequirementCommitment command = new UpdateOrderRequirementCommitment(orderRequirementCommitmentToBeUpdated);

		try {
			if(((OrderRequirementCommitmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderRequirementCommitmentId}")
	public ResponseEntity<Object> findById(@PathVariable String orderRequirementCommitmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderRequirementCommitmentId", orderRequirementCommitmentId);
		try {

			Object foundOrderRequirementCommitment = findOrderRequirementCommitmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderRequirementCommitment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderRequirementCommitmentId}")
	public ResponseEntity<Object> deleteOrderRequirementCommitmentByIdUpdated(@PathVariable String orderRequirementCommitmentId) throws Exception {
		DeleteOrderRequirementCommitment command = new DeleteOrderRequirementCommitment(orderRequirementCommitmentId);

		try {
			if (((OrderRequirementCommitmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderRequirementCommitment could not be deleted");

	}

}
