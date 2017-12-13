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
	public ResponseEntity<Object> findOrderShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderShipmentsBy query = new FindOrderShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderShipment> orderShipments =((OrderShipmentFound) Scheduler.execute(query).data()).getOrderShipments();

		if (orderShipments.size() == 1) {
			return ResponseEntity.ok().body(orderShipments.get(0));
		}

		return ResponseEntity.ok().body(orderShipments);

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
	public ResponseEntity<Object> createOrderShipment(HttpServletRequest request) throws Exception {

		OrderShipment orderShipmentToBeAdded = new OrderShipment();
		try {
			orderShipmentToBeAdded = OrderShipmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderShipment(orderShipmentToBeAdded);

	}

	/**
	 * creates a new OrderShipment entry in the ofbiz database
	 * 
	 * @param orderShipmentToBeAdded
	 *            the OrderShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderShipment(@RequestBody OrderShipment orderShipmentToBeAdded) throws Exception {

		AddOrderShipment command = new AddOrderShipment(orderShipmentToBeAdded);
		OrderShipment orderShipment = ((OrderShipmentAdded) Scheduler.execute(command).data()).getAddedOrderShipment();
		
		if (orderShipment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderShipment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderShipment could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateOrderShipment(HttpServletRequest request) throws Exception {

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

		OrderShipment orderShipmentToBeUpdated = new OrderShipment();

		try {
			orderShipmentToBeUpdated = OrderShipmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderShipment(orderShipmentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateOrderShipment(@RequestBody OrderShipment orderShipmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderShipmentToBeUpdated.setnull(null);

		UpdateOrderShipment command = new UpdateOrderShipment(orderShipmentToBeUpdated);

		try {
			if(((OrderShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderShipmentId}")
	public ResponseEntity<Object> findById(@PathVariable String orderShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderShipmentId", orderShipmentId);
		try {

			Object foundOrderShipment = findOrderShipmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderShipment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderShipmentId}")
	public ResponseEntity<Object> deleteOrderShipmentByIdUpdated(@PathVariable String orderShipmentId) throws Exception {
		DeleteOrderShipment command = new DeleteOrderShipment(orderShipmentId);

		try {
			if (((OrderShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderShipment could not be deleted");

	}

}
