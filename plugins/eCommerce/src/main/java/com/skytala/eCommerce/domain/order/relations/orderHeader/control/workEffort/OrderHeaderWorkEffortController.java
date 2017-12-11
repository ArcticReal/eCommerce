package com.skytala.eCommerce.domain.order.relations.orderHeader.control.workEffort;

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
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort.AddOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort.DeleteOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort.UpdateOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.workEffort.OrderHeaderWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.query.workEffort.FindOrderHeaderWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderHeader/orderHeaderWorkEfforts")
public class OrderHeaderWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderHeaderWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderHeaderWorkEffort
	 * @return a List with the OrderHeaderWorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderHeaderWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderHeaderWorkEffortsBy query = new FindOrderHeaderWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeaderWorkEffort> orderHeaderWorkEfforts =((OrderHeaderWorkEffortFound) Scheduler.execute(query).data()).getOrderHeaderWorkEfforts();

		if (orderHeaderWorkEfforts.size() == 1) {
			return ResponseEntity.ok().body(orderHeaderWorkEfforts.get(0));
		}

		return ResponseEntity.ok().body(orderHeaderWorkEfforts);

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
	public ResponseEntity<Object> createOrderHeaderWorkEffort(HttpServletRequest request) throws Exception {

		OrderHeaderWorkEffort orderHeaderWorkEffortToBeAdded = new OrderHeaderWorkEffort();
		try {
			orderHeaderWorkEffortToBeAdded = OrderHeaderWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderHeaderWorkEffort(orderHeaderWorkEffortToBeAdded);

	}

	/**
	 * creates a new OrderHeaderWorkEffort entry in the ofbiz database
	 * 
	 * @param orderHeaderWorkEffortToBeAdded
	 *            the OrderHeaderWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderHeaderWorkEffort(@RequestBody OrderHeaderWorkEffort orderHeaderWorkEffortToBeAdded) throws Exception {

		AddOrderHeaderWorkEffort command = new AddOrderHeaderWorkEffort(orderHeaderWorkEffortToBeAdded);
		OrderHeaderWorkEffort orderHeaderWorkEffort = ((OrderHeaderWorkEffortAdded) Scheduler.execute(command).data()).getAddedOrderHeaderWorkEffort();
		
		if (orderHeaderWorkEffort != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderHeaderWorkEffort);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderHeaderWorkEffort could not be created.");
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
	public boolean updateOrderHeaderWorkEffort(HttpServletRequest request) throws Exception {

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

		OrderHeaderWorkEffort orderHeaderWorkEffortToBeUpdated = new OrderHeaderWorkEffort();

		try {
			orderHeaderWorkEffortToBeUpdated = OrderHeaderWorkEffortMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderHeaderWorkEffort(orderHeaderWorkEffortToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderHeaderWorkEffort with the specific Id
	 * 
	 * @param orderHeaderWorkEffortToBeUpdated
	 *            the OrderHeaderWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderHeaderWorkEffort(@RequestBody OrderHeaderWorkEffort orderHeaderWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderHeaderWorkEffortToBeUpdated.setnull(null);

		UpdateOrderHeaderWorkEffort command = new UpdateOrderHeaderWorkEffort(orderHeaderWorkEffortToBeUpdated);

		try {
			if(((OrderHeaderWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderHeaderWorkEffortId}")
	public ResponseEntity<Object> findById(@PathVariable String orderHeaderWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderHeaderWorkEffortId", orderHeaderWorkEffortId);
		try {

			Object foundOrderHeaderWorkEffort = findOrderHeaderWorkEffortsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderHeaderWorkEffort);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderHeaderWorkEffortId}")
	public ResponseEntity<Object> deleteOrderHeaderWorkEffortByIdUpdated(@PathVariable String orderHeaderWorkEffortId) throws Exception {
		DeleteOrderHeaderWorkEffort command = new DeleteOrderHeaderWorkEffort(orderHeaderWorkEffortId);

		try {
			if (((OrderHeaderWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderHeaderWorkEffort could not be deleted");

	}

}
