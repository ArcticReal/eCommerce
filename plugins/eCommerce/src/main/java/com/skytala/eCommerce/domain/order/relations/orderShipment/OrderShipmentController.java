package com.skytala.eCommerce.domain.order.relations.orderShipment;

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
import com.skytala.eCommerce.domain.order.relations.orderShipment.command.AddOrderShipment;
import com.skytala.eCommerce.domain.order.relations.orderShipment.command.DeleteOrderShipment;
import com.skytala.eCommerce.domain.order.relations.orderShipment.command.UpdateOrderShipment;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentDeleted;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentFound;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderShipment.mapper.OrderShipmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
import com.skytala.eCommerce.domain.order.relations.orderShipment.query.FindOrderShipmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderShipments")
public class OrderShipmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderShipmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderShipment
	 * @return a List with the OrderShipments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderShipment>> findOrderShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderShipmentsBy query = new FindOrderShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderShipment> orderShipments =((OrderShipmentFound) Scheduler.execute(query).data()).getOrderShipments();

		return ResponseEntity.ok().body(orderShipments);

	}

	/**
	 * creates a new OrderShipment entry in the ofbiz database
	 * 
	 * @param orderShipmentToBeAdded
	 *            the OrderShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderShipment> createOrderShipment(@RequestBody OrderShipment orderShipmentToBeAdded) throws Exception {

		AddOrderShipment command = new AddOrderShipment(orderShipmentToBeAdded);
		OrderShipment orderShipment = ((OrderShipmentAdded) Scheduler.execute(command).data()).getAddedOrderShipment();
		
		if (orderShipment != null) 
			return successful(orderShipment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderShipment with the specific Id
	 * 
	 * @param orderShipmentToBeUpdated
	 *            the OrderShipment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderShipment(@RequestBody OrderShipment orderShipmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderShipmentToBeUpdated.setnull(null);

		UpdateOrderShipment command = new UpdateOrderShipment(orderShipmentToBeUpdated);

		try {
			if(((OrderShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderShipmentId}")
	public ResponseEntity<OrderShipment> findById(@PathVariable String orderShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderShipmentId", orderShipmentId);
		try {

			List<OrderShipment> foundOrderShipment = findOrderShipmentsBy(requestParams).getBody();
			if(foundOrderShipment.size()==1){				return successful(foundOrderShipment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderShipmentId}")
	public ResponseEntity<String> deleteOrderShipmentByIdUpdated(@PathVariable String orderShipmentId) throws Exception {
		DeleteOrderShipment command = new DeleteOrderShipment(orderShipmentId);

		try {
			if (((OrderShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
