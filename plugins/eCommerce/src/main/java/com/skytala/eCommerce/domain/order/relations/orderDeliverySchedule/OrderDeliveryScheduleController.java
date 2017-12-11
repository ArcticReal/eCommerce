package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule;

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
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command.AddOrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command.DeleteOrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command.UpdateOrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleAdded;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleDeleted;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleFound;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleUpdated;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.mapper.OrderDeliveryScheduleMapper;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.query.FindOrderDeliverySchedulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderDeliverySchedules")
public class OrderDeliveryScheduleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderDeliveryScheduleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderDeliverySchedule
	 * @return a List with the OrderDeliverySchedules
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderDeliverySchedulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderDeliverySchedulesBy query = new FindOrderDeliverySchedulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderDeliverySchedule> orderDeliverySchedules =((OrderDeliveryScheduleFound) Scheduler.execute(query).data()).getOrderDeliverySchedules();

		if (orderDeliverySchedules.size() == 1) {
			return ResponseEntity.ok().body(orderDeliverySchedules.get(0));
		}

		return ResponseEntity.ok().body(orderDeliverySchedules);

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
	public ResponseEntity<Object> createOrderDeliverySchedule(HttpServletRequest request) throws Exception {

		OrderDeliverySchedule orderDeliveryScheduleToBeAdded = new OrderDeliverySchedule();
		try {
			orderDeliveryScheduleToBeAdded = OrderDeliveryScheduleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderDeliverySchedule(orderDeliveryScheduleToBeAdded);

	}

	/**
	 * creates a new OrderDeliverySchedule entry in the ofbiz database
	 * 
	 * @param orderDeliveryScheduleToBeAdded
	 *            the OrderDeliverySchedule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderDeliverySchedule(@RequestBody OrderDeliverySchedule orderDeliveryScheduleToBeAdded) throws Exception {

		AddOrderDeliverySchedule command = new AddOrderDeliverySchedule(orderDeliveryScheduleToBeAdded);
		OrderDeliverySchedule orderDeliverySchedule = ((OrderDeliveryScheduleAdded) Scheduler.execute(command).data()).getAddedOrderDeliverySchedule();
		
		if (orderDeliverySchedule != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderDeliverySchedule);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderDeliverySchedule could not be created.");
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
	public boolean updateOrderDeliverySchedule(HttpServletRequest request) throws Exception {

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

		OrderDeliverySchedule orderDeliveryScheduleToBeUpdated = new OrderDeliverySchedule();

		try {
			orderDeliveryScheduleToBeUpdated = OrderDeliveryScheduleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderDeliverySchedule(orderDeliveryScheduleToBeUpdated, orderDeliveryScheduleToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderDeliverySchedule with the specific Id
	 * 
	 * @param orderDeliveryScheduleToBeUpdated
	 *            the OrderDeliverySchedule thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderDeliverySchedule(@RequestBody OrderDeliverySchedule orderDeliveryScheduleToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderDeliveryScheduleToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderDeliverySchedule command = new UpdateOrderDeliverySchedule(orderDeliveryScheduleToBeUpdated);

		try {
			if(((OrderDeliveryScheduleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderDeliveryScheduleId}")
	public ResponseEntity<Object> findById(@PathVariable String orderDeliveryScheduleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderDeliveryScheduleId", orderDeliveryScheduleId);
		try {

			Object foundOrderDeliverySchedule = findOrderDeliverySchedulesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderDeliverySchedule);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderDeliveryScheduleId}")
	public ResponseEntity<Object> deleteOrderDeliveryScheduleByIdUpdated(@PathVariable String orderDeliveryScheduleId) throws Exception {
		DeleteOrderDeliverySchedule command = new DeleteOrderDeliverySchedule(orderDeliveryScheduleId);

		try {
			if (((OrderDeliveryScheduleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderDeliverySchedule could not be deleted");

	}

}
