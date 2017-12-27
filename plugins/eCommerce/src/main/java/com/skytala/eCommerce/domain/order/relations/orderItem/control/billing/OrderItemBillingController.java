package com.skytala.eCommerce.domain.order.relations.orderItem.control.billing;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.billing.AddOrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.billing.DeleteOrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.billing.UpdateOrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.billing.OrderItemBillingAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.billing.OrderItemBillingDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.billing.OrderItemBillingFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.billing.OrderItemBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.billing.OrderItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.billing.OrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.billing.FindOrderItemBillingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemBillings")
public class OrderItemBillingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemBillingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemBilling
	 * @return a List with the OrderItemBillings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemBilling>> findOrderItemBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemBillingsBy query = new FindOrderItemBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemBilling> orderItemBillings =((OrderItemBillingFound) Scheduler.execute(query).data()).getOrderItemBillings();

		return ResponseEntity.ok().body(orderItemBillings);

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
	public ResponseEntity<OrderItemBilling> createOrderItemBilling(HttpServletRequest request) throws Exception {

		OrderItemBilling orderItemBillingToBeAdded = new OrderItemBilling();
		try {
			orderItemBillingToBeAdded = OrderItemBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderItemBilling(orderItemBillingToBeAdded);

	}

	/**
	 * creates a new OrderItemBilling entry in the ofbiz database
	 * 
	 * @param orderItemBillingToBeAdded
	 *            the OrderItemBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemBilling> createOrderItemBilling(@RequestBody OrderItemBilling orderItemBillingToBeAdded) throws Exception {

		AddOrderItemBilling command = new AddOrderItemBilling(orderItemBillingToBeAdded);
		OrderItemBilling orderItemBilling = ((OrderItemBillingAdded) Scheduler.execute(command).data()).getAddedOrderItemBilling();
		
		if (orderItemBilling != null) 
			return successful(orderItemBilling);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemBilling with the specific Id
	 * 
	 * @param orderItemBillingToBeUpdated
	 *            the OrderItemBilling thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemBilling(@RequestBody OrderItemBilling orderItemBillingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemBillingToBeUpdated.setnull(null);

		UpdateOrderItemBilling command = new UpdateOrderItemBilling(orderItemBillingToBeUpdated);

		try {
			if(((OrderItemBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemBillingId}")
	public ResponseEntity<OrderItemBilling> findById(@PathVariable String orderItemBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemBillingId", orderItemBillingId);
		try {

			List<OrderItemBilling> foundOrderItemBilling = findOrderItemBillingsBy(requestParams).getBody();
			if(foundOrderItemBilling.size()==1){				return successful(foundOrderItemBilling.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemBillingId}")
	public ResponseEntity<String> deleteOrderItemBillingByIdUpdated(@PathVariable String orderItemBillingId) throws Exception {
		DeleteOrderItemBilling command = new DeleteOrderItemBilling(orderItemBillingId);

		try {
			if (((OrderItemBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
