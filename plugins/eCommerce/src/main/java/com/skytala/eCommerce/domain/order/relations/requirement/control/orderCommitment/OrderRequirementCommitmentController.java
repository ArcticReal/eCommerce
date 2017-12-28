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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirement/orderRequirementCommitments")
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
	@GetMapping("/find")
	public ResponseEntity<List<OrderRequirementCommitment>> findOrderRequirementCommitmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderRequirementCommitmentsBy query = new FindOrderRequirementCommitmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderRequirementCommitment> orderRequirementCommitments =((OrderRequirementCommitmentFound) Scheduler.execute(query).data()).getOrderRequirementCommitments();

		return ResponseEntity.ok().body(orderRequirementCommitments);

	}

	/**
	 * creates a new OrderRequirementCommitment entry in the ofbiz database
	 * 
	 * @param orderRequirementCommitmentToBeAdded
	 *            the OrderRequirementCommitment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderRequirementCommitment> createOrderRequirementCommitment(@RequestBody OrderRequirementCommitment orderRequirementCommitmentToBeAdded) throws Exception {

		AddOrderRequirementCommitment command = new AddOrderRequirementCommitment(orderRequirementCommitmentToBeAdded);
		OrderRequirementCommitment orderRequirementCommitment = ((OrderRequirementCommitmentAdded) Scheduler.execute(command).data()).getAddedOrderRequirementCommitment();
		
		if (orderRequirementCommitment != null) 
			return successful(orderRequirementCommitment);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateOrderRequirementCommitment(@RequestBody OrderRequirementCommitment orderRequirementCommitmentToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderRequirementCommitmentToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderRequirementCommitment command = new UpdateOrderRequirementCommitment(orderRequirementCommitmentToBeUpdated);

		try {
			if(((OrderRequirementCommitmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderRequirementCommitmentId}")
	public ResponseEntity<OrderRequirementCommitment> findById(@PathVariable String orderRequirementCommitmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderRequirementCommitmentId", orderRequirementCommitmentId);
		try {

			List<OrderRequirementCommitment> foundOrderRequirementCommitment = findOrderRequirementCommitmentsBy(requestParams).getBody();
			if(foundOrderRequirementCommitment.size()==1){				return successful(foundOrderRequirementCommitment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderRequirementCommitmentId}")
	public ResponseEntity<String> deleteOrderRequirementCommitmentByIdUpdated(@PathVariable String orderRequirementCommitmentId) throws Exception {
		DeleteOrderRequirementCommitment command = new DeleteOrderRequirementCommitment(orderRequirementCommitmentId);

		try {
			if (((OrderRequirementCommitmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
