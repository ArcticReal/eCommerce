package com.skytala.eCommerce.domain.orderItemChange;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.orderItemChange.command.AddOrderItemChange;
import com.skytala.eCommerce.domain.orderItemChange.command.DeleteOrderItemChange;
import com.skytala.eCommerce.domain.orderItemChange.command.UpdateOrderItemChange;
import com.skytala.eCommerce.domain.orderItemChange.event.OrderItemChangeAdded;
import com.skytala.eCommerce.domain.orderItemChange.event.OrderItemChangeDeleted;
import com.skytala.eCommerce.domain.orderItemChange.event.OrderItemChangeFound;
import com.skytala.eCommerce.domain.orderItemChange.event.OrderItemChangeUpdated;
import com.skytala.eCommerce.domain.orderItemChange.mapper.OrderItemChangeMapper;
import com.skytala.eCommerce.domain.orderItemChange.model.OrderItemChange;
import com.skytala.eCommerce.domain.orderItemChange.query.FindOrderItemChangesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemChanges")
public class OrderItemChangeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemChangeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemChange
	 * @return a List with the OrderItemChanges
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemChangesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemChangesBy query = new FindOrderItemChangesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemChange> orderItemChanges =((OrderItemChangeFound) Scheduler.execute(query).data()).getOrderItemChanges();

		if (orderItemChanges.size() == 1) {
			return ResponseEntity.ok().body(orderItemChanges.get(0));
		}

		return ResponseEntity.ok().body(orderItemChanges);

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
	public ResponseEntity<Object> createOrderItemChange(HttpServletRequest request) throws Exception {

		OrderItemChange orderItemChangeToBeAdded = new OrderItemChange();
		try {
			orderItemChangeToBeAdded = OrderItemChangeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemChange(orderItemChangeToBeAdded);

	}

	/**
	 * creates a new OrderItemChange entry in the ofbiz database
	 * 
	 * @param orderItemChangeToBeAdded
	 *            the OrderItemChange thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemChange(@RequestBody OrderItemChange orderItemChangeToBeAdded) throws Exception {

		AddOrderItemChange command = new AddOrderItemChange(orderItemChangeToBeAdded);
		OrderItemChange orderItemChange = ((OrderItemChangeAdded) Scheduler.execute(command).data()).getAddedOrderItemChange();
		
		if (orderItemChange != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemChange);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemChange could not be created.");
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
	public boolean updateOrderItemChange(HttpServletRequest request) throws Exception {

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

		OrderItemChange orderItemChangeToBeUpdated = new OrderItemChange();

		try {
			orderItemChangeToBeUpdated = OrderItemChangeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemChange(orderItemChangeToBeUpdated, orderItemChangeToBeUpdated.getOrderItemChangeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemChange with the specific Id
	 * 
	 * @param orderItemChangeToBeUpdated
	 *            the OrderItemChange thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemChangeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemChange(@RequestBody OrderItemChange orderItemChangeToBeUpdated,
			@PathVariable String orderItemChangeId) throws Exception {

		orderItemChangeToBeUpdated.setOrderItemChangeId(orderItemChangeId);

		UpdateOrderItemChange command = new UpdateOrderItemChange(orderItemChangeToBeUpdated);

		try {
			if(((OrderItemChangeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemChangeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemChangeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemChangeId", orderItemChangeId);
		try {

			Object foundOrderItemChange = findOrderItemChangesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemChange);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemChangeId}")
	public ResponseEntity<Object> deleteOrderItemChangeByIdUpdated(@PathVariable String orderItemChangeId) throws Exception {
		DeleteOrderItemChange command = new DeleteOrderItemChange(orderItemChangeId);

		try {
			if (((OrderItemChangeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemChange could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemChange/\" plus one of the following: "
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
