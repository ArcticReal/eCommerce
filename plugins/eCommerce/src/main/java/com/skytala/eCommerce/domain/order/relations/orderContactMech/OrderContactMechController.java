package com.skytala.eCommerce.domain.order.relations.orderContactMech;

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
import com.skytala.eCommerce.domain.order.relations.orderContactMech.command.AddOrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.command.DeleteOrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.command.UpdateOrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechDeleted;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechFound;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.mapper.OrderContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.query.FindOrderContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderContactMechs")
public class OrderContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderContactMech
	 * @return a List with the OrderContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderContactMechsBy query = new FindOrderContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderContactMech> orderContactMechs =((OrderContactMechFound) Scheduler.execute(query).data()).getOrderContactMechs();

		if (orderContactMechs.size() == 1) {
			return ResponseEntity.ok().body(orderContactMechs.get(0));
		}

		return ResponseEntity.ok().body(orderContactMechs);

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
	public ResponseEntity<Object> createOrderContactMech(HttpServletRequest request) throws Exception {

		OrderContactMech orderContactMechToBeAdded = new OrderContactMech();
		try {
			orderContactMechToBeAdded = OrderContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderContactMech(orderContactMechToBeAdded);

	}

	/**
	 * creates a new OrderContactMech entry in the ofbiz database
	 * 
	 * @param orderContactMechToBeAdded
	 *            the OrderContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderContactMech(@RequestBody OrderContactMech orderContactMechToBeAdded) throws Exception {

		AddOrderContactMech command = new AddOrderContactMech(orderContactMechToBeAdded);
		OrderContactMech orderContactMech = ((OrderContactMechAdded) Scheduler.execute(command).data()).getAddedOrderContactMech();
		
		if (orderContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderContactMech could not be created.");
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
	public boolean updateOrderContactMech(HttpServletRequest request) throws Exception {

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

		OrderContactMech orderContactMechToBeUpdated = new OrderContactMech();

		try {
			orderContactMechToBeUpdated = OrderContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderContactMech(orderContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderContactMech with the specific Id
	 * 
	 * @param orderContactMechToBeUpdated
	 *            the OrderContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderContactMech(@RequestBody OrderContactMech orderContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderContactMechToBeUpdated.setnull(null);

		UpdateOrderContactMech command = new UpdateOrderContactMech(orderContactMechToBeUpdated);

		try {
			if(((OrderContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String orderContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderContactMechId", orderContactMechId);
		try {

			Object foundOrderContactMech = findOrderContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderContactMechId}")
	public ResponseEntity<Object> deleteOrderContactMechByIdUpdated(@PathVariable String orderContactMechId) throws Exception {
		DeleteOrderContactMech command = new DeleteOrderContactMech(orderContactMechId);

		try {
			if (((OrderContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderContactMech could not be deleted");

	}

}
