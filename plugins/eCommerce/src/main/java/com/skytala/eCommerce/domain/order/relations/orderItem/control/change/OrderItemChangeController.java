package com.skytala.eCommerce.domain.order.relations.orderItem.control.change;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.change.AddOrderItemChange;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.change.DeleteOrderItemChange;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.change.UpdateOrderItemChange;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.change.OrderItemChangeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.change.OrderItemChangeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.change.OrderItemChangeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.change.OrderItemChangeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.change.OrderItemChangeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.change.OrderItemChange;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.change.FindOrderItemChangesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemChanges")
public class OrderItemChangeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemChangeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemChange
	 * @return a List with the OrderItemChanges
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemChange>> findOrderItemChangesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemChangesBy query = new FindOrderItemChangesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemChange> orderItemChanges =((OrderItemChangeFound) Scheduler.execute(query).data()).getOrderItemChanges();

		return ResponseEntity.ok().body(orderItemChanges);

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
	public ResponseEntity<OrderItemChange> createOrderItemChange(HttpServletRequest request) throws Exception {

		OrderItemChange orderItemChangeToBeAdded = new OrderItemChange();
		try {
			orderItemChangeToBeAdded = OrderItemChangeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderItemChange(orderItemChangeToBeAdded);

	}

	/**
	 * creates a new OrderItemChange entry in the ofbiz database
	 * 
	 * @param orderItemChangeToBeAdded
	 *            the OrderItemChange thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemChange> createOrderItemChange(@RequestBody OrderItemChange orderItemChangeToBeAdded) throws Exception {

		AddOrderItemChange command = new AddOrderItemChange(orderItemChangeToBeAdded);
		OrderItemChange orderItemChange = ((OrderItemChangeAdded) Scheduler.execute(command).data()).getAddedOrderItemChange();
		
		if (orderItemChange != null) 
			return successful(orderItemChange);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemChange with the specific Id
	 * 
	 * @param orderItemChangeToBeUpdated
	 *            the OrderItemChange thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemChangeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemChange(@RequestBody OrderItemChange orderItemChangeToBeUpdated,
			@PathVariable String orderItemChangeId) throws Exception {

		orderItemChangeToBeUpdated.setOrderItemChangeId(orderItemChangeId);

		UpdateOrderItemChange command = new UpdateOrderItemChange(orderItemChangeToBeUpdated);

		try {
			if(((OrderItemChangeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemChangeId}")
	public ResponseEntity<OrderItemChange> findById(@PathVariable String orderItemChangeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemChangeId", orderItemChangeId);
		try {

			List<OrderItemChange> foundOrderItemChange = findOrderItemChangesBy(requestParams).getBody();
			if(foundOrderItemChange.size()==1){				return successful(foundOrderItemChange.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemChangeId}")
	public ResponseEntity<String> deleteOrderItemChangeByIdUpdated(@PathVariable String orderItemChangeId) throws Exception {
		DeleteOrderItemChange command = new DeleteOrderItemChange(orderItemChangeId);

		try {
			if (((OrderItemChangeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
