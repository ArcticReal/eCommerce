package com.skytala.eCommerce.domain.order.relations.orderItem.control.assoc;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assoc.AddOrderItemAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assoc.DeleteOrderItemAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assoc.UpdateOrderItemAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assoc.OrderItemAssocAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assoc.OrderItemAssocDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assoc.OrderItemAssocFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assoc.OrderItemAssocUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.assoc.OrderItemAssocMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.assoc.OrderItemAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.assoc.FindOrderItemAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemAssocs")
public class OrderItemAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemAssoc
	 * @return a List with the OrderItemAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemAssoc>> findOrderItemAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemAssocsBy query = new FindOrderItemAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemAssoc> orderItemAssocs =((OrderItemAssocFound) Scheduler.execute(query).data()).getOrderItemAssocs();

		return ResponseEntity.ok().body(orderItemAssocs);

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
	public ResponseEntity<OrderItemAssoc> createOrderItemAssoc(HttpServletRequest request) throws Exception {

		OrderItemAssoc orderItemAssocToBeAdded = new OrderItemAssoc();
		try {
			orderItemAssocToBeAdded = OrderItemAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderItemAssoc(orderItemAssocToBeAdded);

	}

	/**
	 * creates a new OrderItemAssoc entry in the ofbiz database
	 * 
	 * @param orderItemAssocToBeAdded
	 *            the OrderItemAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemAssoc> createOrderItemAssoc(@RequestBody OrderItemAssoc orderItemAssocToBeAdded) throws Exception {

		AddOrderItemAssoc command = new AddOrderItemAssoc(orderItemAssocToBeAdded);
		OrderItemAssoc orderItemAssoc = ((OrderItemAssocAdded) Scheduler.execute(command).data()).getAddedOrderItemAssoc();
		
		if (orderItemAssoc != null) 
			return successful(orderItemAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemAssoc with the specific Id
	 * 
	 * @param orderItemAssocToBeUpdated
	 *            the OrderItemAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemAssoc(@RequestBody OrderItemAssoc orderItemAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemAssocToBeUpdated.setnull(null);

		UpdateOrderItemAssoc command = new UpdateOrderItemAssoc(orderItemAssocToBeUpdated);

		try {
			if(((OrderItemAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemAssocId}")
	public ResponseEntity<OrderItemAssoc> findById(@PathVariable String orderItemAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemAssocId", orderItemAssocId);
		try {

			List<OrderItemAssoc> foundOrderItemAssoc = findOrderItemAssocsBy(requestParams).getBody();
			if(foundOrderItemAssoc.size()==1){				return successful(foundOrderItemAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemAssocId}")
	public ResponseEntity<String> deleteOrderItemAssocByIdUpdated(@PathVariable String orderItemAssocId) throws Exception {
		DeleteOrderItemAssoc command = new DeleteOrderItemAssoc(orderItemAssocId);

		try {
			if (((OrderItemAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
