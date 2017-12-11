package com.skytala.eCommerce.domain.order.relations.orderAdjustment;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.AddOrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.DeleteOrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.UpdateOrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.OrderAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.OrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.FindOrderAdjustmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderAdjustments")
public class OrderAdjustmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustment
	 * @return a List with the OrderAdjustments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderAdjustmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentsBy query = new FindOrderAdjustmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustment> orderAdjustments =((OrderAdjustmentFound) Scheduler.execute(query).data()).getOrderAdjustments();

		if (orderAdjustments.size() == 1) {
			return ResponseEntity.ok().body(orderAdjustments.get(0));
		}

		return ResponseEntity.ok().body(orderAdjustments);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createOrderAdjustment(HttpServletRequest request) throws Exception {

		OrderAdjustment orderAdjustmentToBeAdded = new OrderAdjustment();
		try {
			orderAdjustmentToBeAdded = OrderAdjustmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderAdjustment(orderAdjustmentToBeAdded);

	}

	/**
	 * creates a new OrderAdjustment entry in the ofbiz database
	 * 
	 * @param orderAdjustmentToBeAdded
	 *            the OrderAdjustment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderAdjustment(@RequestBody OrderAdjustment orderAdjustmentToBeAdded) throws Exception {

		AddOrderAdjustment command = new AddOrderAdjustment(orderAdjustmentToBeAdded);
		OrderAdjustment orderAdjustment = ((OrderAdjustmentAdded) Scheduler.execute(command).data()).getAddedOrderAdjustment();
		
		if (orderAdjustment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderAdjustment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderAdjustment could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateOrderAdjustment(HttpServletRequest request) throws Exception {

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

		OrderAdjustment orderAdjustmentToBeUpdated = new OrderAdjustment();

		try {
			orderAdjustmentToBeUpdated = OrderAdjustmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderAdjustment(orderAdjustmentToBeUpdated, orderAdjustmentToBeUpdated.getOrderAdjustmentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderAdjustment with the specific Id
	 * 
	 * @param orderAdjustmentToBeUpdated
	 *            the OrderAdjustment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderAdjustmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderAdjustment(@RequestBody OrderAdjustment orderAdjustmentToBeUpdated,
			@PathVariable String orderAdjustmentId) throws Exception {

		orderAdjustmentToBeUpdated.setOrderAdjustmentId(orderAdjustmentId);

		UpdateOrderAdjustment command = new UpdateOrderAdjustment(orderAdjustmentToBeUpdated);

		try {
			if(((OrderAdjustmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderAdjustmentId}")
	public ResponseEntity<Object> findById(@PathVariable String orderAdjustmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentId", orderAdjustmentId);
		try {

			Object foundOrderAdjustment = findOrderAdjustmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderAdjustment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderAdjustmentId}")
	public ResponseEntity<Object> deleteOrderAdjustmentByIdUpdated(@PathVariable String orderAdjustmentId) throws Exception {
		DeleteOrderAdjustment command = new DeleteOrderAdjustment(orderAdjustmentId);

		try {
			if (((OrderAdjustmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderAdjustment could not be deleted");

	}

}
