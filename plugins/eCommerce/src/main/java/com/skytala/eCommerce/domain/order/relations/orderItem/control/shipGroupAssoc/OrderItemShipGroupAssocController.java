package com.skytala.eCommerce.domain.order.relations.orderItem.control.shipGroupAssoc;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroupAssoc.AddOrderItemShipGroupAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroupAssoc.DeleteOrderItemShipGroupAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroupAssoc.UpdateOrderItemShipGroupAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc.OrderItemShipGroupAssocAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc.OrderItemShipGroupAssocDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc.OrderItemShipGroupAssocFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc.OrderItemShipGroupAssocUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGroupAssoc.OrderItemShipGroupAssocMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroupAssoc.OrderItemShipGroupAssoc;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.shipGroupAssoc.FindOrderItemShipGroupAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemShipGroupAssocs")
public class OrderItemShipGroupAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemShipGroupAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemShipGroupAssoc
	 * @return a List with the OrderItemShipGroupAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemShipGroupAssoc>> findOrderItemShipGroupAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemShipGroupAssocsBy query = new FindOrderItemShipGroupAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemShipGroupAssoc> orderItemShipGroupAssocs =((OrderItemShipGroupAssocFound) Scheduler.execute(query).data()).getOrderItemShipGroupAssocs();

		return ResponseEntity.ok().body(orderItemShipGroupAssocs);

	}

	/**
	 * creates a new OrderItemShipGroupAssoc entry in the ofbiz database
	 * 
	 * @param orderItemShipGroupAssocToBeAdded
	 *            the OrderItemShipGroupAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemShipGroupAssoc> createOrderItemShipGroupAssoc(@RequestBody OrderItemShipGroupAssoc orderItemShipGroupAssocToBeAdded) throws Exception {

		AddOrderItemShipGroupAssoc command = new AddOrderItemShipGroupAssoc(orderItemShipGroupAssocToBeAdded);
		OrderItemShipGroupAssoc orderItemShipGroupAssoc = ((OrderItemShipGroupAssocAdded) Scheduler.execute(command).data()).getAddedOrderItemShipGroupAssoc();
		
		if (orderItemShipGroupAssoc != null) 
			return successful(orderItemShipGroupAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemShipGroupAssoc with the specific Id
	 * 
	 * @param orderItemShipGroupAssocToBeUpdated
	 *            the OrderItemShipGroupAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemShipGroupAssoc(@RequestBody OrderItemShipGroupAssoc orderItemShipGroupAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemShipGroupAssocToBeUpdated.setnull(null);

		UpdateOrderItemShipGroupAssoc command = new UpdateOrderItemShipGroupAssoc(orderItemShipGroupAssocToBeUpdated);

		try {
			if(((OrderItemShipGroupAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemShipGroupAssocId}")
	public ResponseEntity<OrderItemShipGroupAssoc> findById(@PathVariable String orderItemShipGroupAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemShipGroupAssocId", orderItemShipGroupAssocId);
		try {

			List<OrderItemShipGroupAssoc> foundOrderItemShipGroupAssoc = findOrderItemShipGroupAssocsBy(requestParams).getBody();
			if(foundOrderItemShipGroupAssoc.size()==1){				return successful(foundOrderItemShipGroupAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemShipGroupAssocId}")
	public ResponseEntity<String> deleteOrderItemShipGroupAssocByIdUpdated(@PathVariable String orderItemShipGroupAssocId) throws Exception {
		DeleteOrderItemShipGroupAssoc command = new DeleteOrderItemShipGroupAssoc(orderItemShipGroupAssocId);

		try {
			if (((OrderItemShipGroupAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
