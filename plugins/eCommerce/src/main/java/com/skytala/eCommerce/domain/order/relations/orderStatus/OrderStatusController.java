package com.skytala.eCommerce.domain.order.relations.orderStatus;

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
import com.skytala.eCommerce.domain.order.relations.orderStatus.command.AddOrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.command.DeleteOrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.command.UpdateOrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusAdded;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusFound;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.orderStatus.mapper.OrderStatusMapper;
import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.query.FindOrderStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderStatuss")
public class OrderStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderStatus
	 * @return a List with the OrderStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderStatussBy query = new FindOrderStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderStatus> orderStatuss =((OrderStatusFound) Scheduler.execute(query).data()).getOrderStatuss();

		if (orderStatuss.size() == 1) {
			return ResponseEntity.ok().body(orderStatuss.get(0));
		}

		return ResponseEntity.ok().body(orderStatuss);

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
	public ResponseEntity<Object> createOrderStatus(HttpServletRequest request) throws Exception {

		OrderStatus orderStatusToBeAdded = new OrderStatus();
		try {
			orderStatusToBeAdded = OrderStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderStatus(orderStatusToBeAdded);

	}

	/**
	 * creates a new OrderStatus entry in the ofbiz database
	 * 
	 * @param orderStatusToBeAdded
	 *            the OrderStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderStatus(@RequestBody OrderStatus orderStatusToBeAdded) throws Exception {

		AddOrderStatus command = new AddOrderStatus(orderStatusToBeAdded);
		OrderStatus orderStatus = ((OrderStatusAdded) Scheduler.execute(command).data()).getAddedOrderStatus();
		
		if (orderStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderStatus could not be created.");
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
	public boolean updateOrderStatus(HttpServletRequest request) throws Exception {

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

		OrderStatus orderStatusToBeUpdated = new OrderStatus();

		try {
			orderStatusToBeUpdated = OrderStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderStatus(orderStatusToBeUpdated, orderStatusToBeUpdated.getOrderStatusId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderStatus with the specific Id
	 * 
	 * @param orderStatusToBeUpdated
	 *            the OrderStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderStatusId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderStatus(@RequestBody OrderStatus orderStatusToBeUpdated,
			@PathVariable String orderStatusId) throws Exception {

		orderStatusToBeUpdated.setOrderStatusId(orderStatusId);

		UpdateOrderStatus command = new UpdateOrderStatus(orderStatusToBeUpdated);

		try {
			if(((OrderStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String orderStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderStatusId", orderStatusId);
		try {

			Object foundOrderStatus = findOrderStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderStatusId}")
	public ResponseEntity<Object> deleteOrderStatusByIdUpdated(@PathVariable String orderStatusId) throws Exception {
		DeleteOrderStatus command = new DeleteOrderStatus(orderStatusId);

		try {
			if (((OrderStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderStatus could not be deleted");

	}

}
