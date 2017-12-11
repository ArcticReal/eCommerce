package com.skytala.eCommerce.domain.order.relations.orderBlacklist.control.type;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.command.type.AddOrderBlacklistType;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.command.type.DeleteOrderBlacklistType;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.command.type.UpdateOrderBlacklistType;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type.OrderBlacklistTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type.OrderBlacklistTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type.OrderBlacklistTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type.OrderBlacklistTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper.type.OrderBlacklistTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.query.type.FindOrderBlacklistTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderBlacklist/orderBlacklistTypes")
public class OrderBlacklistTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderBlacklistTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderBlacklistType
	 * @return a List with the OrderBlacklistTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderBlacklistTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderBlacklistTypesBy query = new FindOrderBlacklistTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderBlacklistType> orderBlacklistTypes =((OrderBlacklistTypeFound) Scheduler.execute(query).data()).getOrderBlacklistTypes();

		if (orderBlacklistTypes.size() == 1) {
			return ResponseEntity.ok().body(orderBlacklistTypes.get(0));
		}

		return ResponseEntity.ok().body(orderBlacklistTypes);

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
	public ResponseEntity<Object> createOrderBlacklistType(HttpServletRequest request) throws Exception {

		OrderBlacklistType orderBlacklistTypeToBeAdded = new OrderBlacklistType();
		try {
			orderBlacklistTypeToBeAdded = OrderBlacklistTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderBlacklistType(orderBlacklistTypeToBeAdded);

	}

	/**
	 * creates a new OrderBlacklistType entry in the ofbiz database
	 * 
	 * @param orderBlacklistTypeToBeAdded
	 *            the OrderBlacklistType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderBlacklistType(@RequestBody OrderBlacklistType orderBlacklistTypeToBeAdded) throws Exception {

		AddOrderBlacklistType command = new AddOrderBlacklistType(orderBlacklistTypeToBeAdded);
		OrderBlacklistType orderBlacklistType = ((OrderBlacklistTypeAdded) Scheduler.execute(command).data()).getAddedOrderBlacklistType();
		
		if (orderBlacklistType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderBlacklistType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderBlacklistType could not be created.");
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
	public boolean updateOrderBlacklistType(HttpServletRequest request) throws Exception {

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

		OrderBlacklistType orderBlacklistTypeToBeUpdated = new OrderBlacklistType();

		try {
			orderBlacklistTypeToBeUpdated = OrderBlacklistTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderBlacklistType(orderBlacklistTypeToBeUpdated, orderBlacklistTypeToBeUpdated.getOrderBlacklistTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderBlacklistType with the specific Id
	 * 
	 * @param orderBlacklistTypeToBeUpdated
	 *            the OrderBlacklistType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderBlacklistTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderBlacklistType(@RequestBody OrderBlacklistType orderBlacklistTypeToBeUpdated,
			@PathVariable String orderBlacklistTypeId) throws Exception {

		orderBlacklistTypeToBeUpdated.setOrderBlacklistTypeId(orderBlacklistTypeId);

		UpdateOrderBlacklistType command = new UpdateOrderBlacklistType(orderBlacklistTypeToBeUpdated);

		try {
			if(((OrderBlacklistTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderBlacklistTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderBlacklistTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderBlacklistTypeId", orderBlacklistTypeId);
		try {

			Object foundOrderBlacklistType = findOrderBlacklistTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderBlacklistType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderBlacklistTypeId}")
	public ResponseEntity<Object> deleteOrderBlacklistTypeByIdUpdated(@PathVariable String orderBlacklistTypeId) throws Exception {
		DeleteOrderBlacklistType command = new DeleteOrderBlacklistType(orderBlacklistTypeId);

		try {
			if (((OrderBlacklistTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderBlacklistType could not be deleted");

	}

}
