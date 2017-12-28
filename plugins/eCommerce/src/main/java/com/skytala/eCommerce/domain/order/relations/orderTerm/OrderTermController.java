package com.skytala.eCommerce.domain.order.relations.orderTerm;

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
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.AddOrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.DeleteOrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.UpdateOrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermAdded;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermDeleted;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermFound;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermUpdated;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.OrderTermMapper;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.query.FindOrderTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderTerms")
public class OrderTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderTerm
	 * @return a List with the OrderTerms
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderTerm>> findOrderTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTermsBy query = new FindOrderTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderTerm> orderTerms =((OrderTermFound) Scheduler.execute(query).data()).getOrderTerms();

		return ResponseEntity.ok().body(orderTerms);

	}

	/**
	 * creates a new OrderTerm entry in the ofbiz database
	 * 
	 * @param orderTermToBeAdded
	 *            the OrderTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderTerm> createOrderTerm(@RequestBody OrderTerm orderTermToBeAdded) throws Exception {

		AddOrderTerm command = new AddOrderTerm(orderTermToBeAdded);
		OrderTerm orderTerm = ((OrderTermAdded) Scheduler.execute(command).data()).getAddedOrderTerm();
		
		if (orderTerm != null) 
			return successful(orderTerm);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderTerm with the specific Id
	 * 
	 * @param orderTermToBeUpdated
	 *            the OrderTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderTerm(@RequestBody OrderTerm orderTermToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderTermToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderTerm command = new UpdateOrderTerm(orderTermToBeUpdated);

		try {
			if(((OrderTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderTermId}")
	public ResponseEntity<OrderTerm> findById(@PathVariable String orderTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTermId", orderTermId);
		try {

			List<OrderTerm> foundOrderTerm = findOrderTermsBy(requestParams).getBody();
			if(foundOrderTerm.size()==1){				return successful(foundOrderTerm.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderTermId}")
	public ResponseEntity<String> deleteOrderTermByIdUpdated(@PathVariable String orderTermId) throws Exception {
		DeleteOrderTerm command = new DeleteOrderTerm(orderTermId);

		try {
			if (((OrderTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
