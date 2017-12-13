package com.skytala.eCommerce.domain.order.relations.orderItem.control.assocType;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType.AddOrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType.DeleteOrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType.UpdateOrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.assocType.OrderItemAssocTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.assocType.FindOrderItemAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderItem/orderItemAssocTypes")
public class OrderItemAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemAssocType
	 * @return a List with the OrderItemAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderItemAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemAssocTypesBy query = new FindOrderItemAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemAssocType> orderItemAssocTypes =((OrderItemAssocTypeFound) Scheduler.execute(query).data()).getOrderItemAssocTypes();

		if (orderItemAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(orderItemAssocTypes.get(0));
		}

		return ResponseEntity.ok().body(orderItemAssocTypes);

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
	public ResponseEntity<Object> createOrderItemAssocType(HttpServletRequest request) throws Exception {

		OrderItemAssocType orderItemAssocTypeToBeAdded = new OrderItemAssocType();
		try {
			orderItemAssocTypeToBeAdded = OrderItemAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemAssocType(orderItemAssocTypeToBeAdded);

	}

	/**
	 * creates a new OrderItemAssocType entry in the ofbiz database
	 * 
	 * @param orderItemAssocTypeToBeAdded
	 *            the OrderItemAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemAssocType(@RequestBody OrderItemAssocType orderItemAssocTypeToBeAdded) throws Exception {

		AddOrderItemAssocType command = new AddOrderItemAssocType(orderItemAssocTypeToBeAdded);
		OrderItemAssocType orderItemAssocType = ((OrderItemAssocTypeAdded) Scheduler.execute(command).data()).getAddedOrderItemAssocType();
		
		if (orderItemAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemAssocType could not be created.");
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
	public boolean updateOrderItemAssocType(HttpServletRequest request) throws Exception {

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

		OrderItemAssocType orderItemAssocTypeToBeUpdated = new OrderItemAssocType();

		try {
			orderItemAssocTypeToBeUpdated = OrderItemAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemAssocType(orderItemAssocTypeToBeUpdated, orderItemAssocTypeToBeUpdated.getOrderItemAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemAssocType with the specific Id
	 * 
	 * @param orderItemAssocTypeToBeUpdated
	 *            the OrderItemAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemAssocType(@RequestBody OrderItemAssocType orderItemAssocTypeToBeUpdated,
			@PathVariable String orderItemAssocTypeId) throws Exception {

		orderItemAssocTypeToBeUpdated.setOrderItemAssocTypeId(orderItemAssocTypeId);

		UpdateOrderItemAssocType command = new UpdateOrderItemAssocType(orderItemAssocTypeToBeUpdated);

		try {
			if(((OrderItemAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderItemAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemAssocTypeId", orderItemAssocTypeId);
		try {

			Object foundOrderItemAssocType = findOrderItemAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderItemAssocTypeId}")
	public ResponseEntity<Object> deleteOrderItemAssocTypeByIdUpdated(@PathVariable String orderItemAssocTypeId) throws Exception {
		DeleteOrderItemAssocType command = new DeleteOrderItemAssocType(orderItemAssocTypeId);

		try {
			if (((OrderItemAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemAssocType could not be deleted");

	}

}
