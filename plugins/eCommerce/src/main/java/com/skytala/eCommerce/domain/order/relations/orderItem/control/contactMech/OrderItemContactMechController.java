package com.skytala.eCommerce.domain.order.relations.orderItem.control.contactMech;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.contactMech.AddOrderItemContactMech;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.contactMech.DeleteOrderItemContactMech;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.contactMech.UpdateOrderItemContactMech;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech.OrderItemContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech.OrderItemContactMechDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech.OrderItemContactMechFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech.OrderItemContactMechUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.contactMech.OrderItemContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.contactMech.OrderItemContactMech;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.contactMech.FindOrderItemContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemContactMechs")
public class OrderItemContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemContactMech
	 * @return a List with the OrderItemContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemContactMech>> findOrderItemContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemContactMechsBy query = new FindOrderItemContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemContactMech> orderItemContactMechs =((OrderItemContactMechFound) Scheduler.execute(query).data()).getOrderItemContactMechs();

		return ResponseEntity.ok().body(orderItemContactMechs);

	}

	/**
	 * creates a new OrderItemContactMech entry in the ofbiz database
	 * 
	 * @param orderItemContactMechToBeAdded
	 *            the OrderItemContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemContactMech> createOrderItemContactMech(@RequestBody OrderItemContactMech orderItemContactMechToBeAdded) throws Exception {

		AddOrderItemContactMech command = new AddOrderItemContactMech(orderItemContactMechToBeAdded);
		OrderItemContactMech orderItemContactMech = ((OrderItemContactMechAdded) Scheduler.execute(command).data()).getAddedOrderItemContactMech();
		
		if (orderItemContactMech != null) 
			return successful(orderItemContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemContactMech with the specific Id
	 * 
	 * @param orderItemContactMechToBeUpdated
	 *            the OrderItemContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemContactMech(@RequestBody OrderItemContactMech orderItemContactMechToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemContactMechToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItemContactMech command = new UpdateOrderItemContactMech(orderItemContactMechToBeUpdated);

		try {
			if(((OrderItemContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemContactMechId}")
	public ResponseEntity<OrderItemContactMech> findById(@PathVariable String orderItemContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemContactMechId", orderItemContactMechId);
		try {

			List<OrderItemContactMech> foundOrderItemContactMech = findOrderItemContactMechsBy(requestParams).getBody();
			if(foundOrderItemContactMech.size()==1){				return successful(foundOrderItemContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemContactMechId}")
	public ResponseEntity<String> deleteOrderItemContactMechByIdUpdated(@PathVariable String orderItemContactMechId) throws Exception {
		DeleteOrderItemContactMech command = new DeleteOrderItemContactMech(orderItemContactMechId);

		try {
			if (((OrderItemContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
