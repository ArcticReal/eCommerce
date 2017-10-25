package com.skytala.eCommerce.domain.order.relations.orderItem;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.AddOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.DeleteOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.UpdateOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.OrderItemAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.OrderItemDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.OrderItemFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.OrderItemUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.OrderItemMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.FindOrderItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItem
	 * @return a List with the OrderItems
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemsBy query = new FindOrderItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItem> orderItems =((OrderItemFound) Scheduler.execute(query).data()).getOrderItems();

		if (orderItems.size() == 1) {
			return ResponseEntity.ok().body(orderItems.get(0));
		}

		return ResponseEntity.ok().body(orderItems);

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
	public ResponseEntity<Object> createOrderItem(HttpServletRequest request) throws Exception {

		OrderItem orderItemToBeAdded = new OrderItem();
		try {
			orderItemToBeAdded = OrderItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItem(orderItemToBeAdded);

	}

	/**
	 * creates a new OrderItem entry in the ofbiz database
	 * 
	 * @param orderItemToBeAdded
	 *            the OrderItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItem(@RequestBody OrderItem orderItemToBeAdded) throws Exception {

		AddOrderItem command = new AddOrderItem(orderItemToBeAdded);
		OrderItem orderItem = ((OrderItemAdded) Scheduler.execute(command).data()).getAddedOrderItem();
		
		if (orderItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItem could not be created.");
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
	public boolean updateOrderItem(HttpServletRequest request) throws Exception {

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

		OrderItem orderItemToBeUpdated = new OrderItem();

		try {
			orderItemToBeUpdated = OrderItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItem(orderItemToBeUpdated, orderItemToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItem with the specific Id
	 * 
	 * @param orderItemToBeUpdated
	 *            the OrderItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItem(@RequestBody OrderItem orderItemToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItem command = new UpdateOrderItem(orderItemToBeUpdated);

		try {
			if(((OrderItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemId", orderItemId);
		try {

			Object foundOrderItem = findOrderItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemId}")
	public ResponseEntity<Object> deleteOrderItemByIdUpdated(@PathVariable String orderItemId) throws Exception {
		DeleteOrderItem command = new DeleteOrderItem(orderItemId);

		try {
			if (((OrderItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItem could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItem/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
