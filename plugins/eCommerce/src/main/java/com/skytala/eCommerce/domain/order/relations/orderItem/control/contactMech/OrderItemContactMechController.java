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
	public ResponseEntity<Object> findOrderItemContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemContactMechsBy query = new FindOrderItemContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemContactMech> orderItemContactMechs =((OrderItemContactMechFound) Scheduler.execute(query).data()).getOrderItemContactMechs();

		if (orderItemContactMechs.size() == 1) {
			return ResponseEntity.ok().body(orderItemContactMechs.get(0));
		}

		return ResponseEntity.ok().body(orderItemContactMechs);

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
	public ResponseEntity<Object> createOrderItemContactMech(HttpServletRequest request) throws Exception {

		OrderItemContactMech orderItemContactMechToBeAdded = new OrderItemContactMech();
		try {
			orderItemContactMechToBeAdded = OrderItemContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemContactMech(orderItemContactMechToBeAdded);

	}

	/**
	 * creates a new OrderItemContactMech entry in the ofbiz database
	 * 
	 * @param orderItemContactMechToBeAdded
	 *            the OrderItemContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemContactMech(@RequestBody OrderItemContactMech orderItemContactMechToBeAdded) throws Exception {

		AddOrderItemContactMech command = new AddOrderItemContactMech(orderItemContactMechToBeAdded);
		OrderItemContactMech orderItemContactMech = ((OrderItemContactMechAdded) Scheduler.execute(command).data()).getAddedOrderItemContactMech();
		
		if (orderItemContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemContactMech could not be created.");
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
	public boolean updateOrderItemContactMech(HttpServletRequest request) throws Exception {

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

		OrderItemContactMech orderItemContactMechToBeUpdated = new OrderItemContactMech();

		try {
			orderItemContactMechToBeUpdated = OrderItemContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemContactMech(orderItemContactMechToBeUpdated, orderItemContactMechToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateOrderItemContactMech(@RequestBody OrderItemContactMech orderItemContactMechToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemContactMechToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItemContactMech command = new UpdateOrderItemContactMech(orderItemContactMechToBeUpdated);

		try {
			if(((OrderItemContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderItemContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemContactMechId", orderItemContactMechId);
		try {

			Object foundOrderItemContactMech = findOrderItemContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderItemContactMechId}")
	public ResponseEntity<Object> deleteOrderItemContactMechByIdUpdated(@PathVariable String orderItemContactMechId) throws Exception {
		DeleteOrderItemContactMech command = new DeleteOrderItemContactMech(orderItemContactMechId);

		try {
			if (((OrderItemContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemContactMech could not be deleted");

	}

}
