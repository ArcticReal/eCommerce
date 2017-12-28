package com.skytala.eCommerce.domain.order.relations.orderItem.control.shipGrpInvRes;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes.AddOrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes.DeleteOrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes.UpdateOrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGrpInvRes.OrderItemShipGrpInvResMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGrpInvRes.OrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.shipGrpInvRes.FindOrderItemShipGrpInvRessBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemShipGrpInvRess")
public class OrderItemShipGrpInvResController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemShipGrpInvResController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemShipGrpInvRes
	 * @return a List with the OrderItemShipGrpInvRess
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemShipGrpInvRes>> findOrderItemShipGrpInvRessBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemShipGrpInvRessBy query = new FindOrderItemShipGrpInvRessBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemShipGrpInvRes> orderItemShipGrpInvRess =((OrderItemShipGrpInvResFound) Scheduler.execute(query).data()).getOrderItemShipGrpInvRess();

		return ResponseEntity.ok().body(orderItemShipGrpInvRess);

	}

	/**
	 * creates a new OrderItemShipGrpInvRes entry in the ofbiz database
	 * 
	 * @param orderItemShipGrpInvResToBeAdded
	 *            the OrderItemShipGrpInvRes thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemShipGrpInvRes> createOrderItemShipGrpInvRes(@RequestBody OrderItemShipGrpInvRes orderItemShipGrpInvResToBeAdded) throws Exception {

		AddOrderItemShipGrpInvRes command = new AddOrderItemShipGrpInvRes(orderItemShipGrpInvResToBeAdded);
		OrderItemShipGrpInvRes orderItemShipGrpInvRes = ((OrderItemShipGrpInvResAdded) Scheduler.execute(command).data()).getAddedOrderItemShipGrpInvRes();
		
		if (orderItemShipGrpInvRes != null) 
			return successful(orderItemShipGrpInvRes);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemShipGrpInvRes with the specific Id
	 * 
	 * @param orderItemShipGrpInvResToBeUpdated
	 *            the OrderItemShipGrpInvRes thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemShipGrpInvRes(@RequestBody OrderItemShipGrpInvRes orderItemShipGrpInvResToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemShipGrpInvResToBeUpdated.setnull(null);

		UpdateOrderItemShipGrpInvRes command = new UpdateOrderItemShipGrpInvRes(orderItemShipGrpInvResToBeUpdated);

		try {
			if(((OrderItemShipGrpInvResUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemShipGrpInvResId}")
	public ResponseEntity<OrderItemShipGrpInvRes> findById(@PathVariable String orderItemShipGrpInvResId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemShipGrpInvResId", orderItemShipGrpInvResId);
		try {

			List<OrderItemShipGrpInvRes> foundOrderItemShipGrpInvRes = findOrderItemShipGrpInvRessBy(requestParams).getBody();
			if(foundOrderItemShipGrpInvRes.size()==1){				return successful(foundOrderItemShipGrpInvRes.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemShipGrpInvResId}")
	public ResponseEntity<String> deleteOrderItemShipGrpInvResByIdUpdated(@PathVariable String orderItemShipGrpInvResId) throws Exception {
		DeleteOrderItemShipGrpInvRes command = new DeleteOrderItemShipGrpInvRes(orderItemShipGrpInvResId);

		try {
			if (((OrderItemShipGrpInvResDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
