package com.skytala.eCommerce.domain.order.relations.orderItemBilling;

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
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.command.AddOrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.command.DeleteOrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.command.UpdateOrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingFound;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.mapper.OrderItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.query.FindOrderItemBillingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemBillings")
public class OrderItemBillingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemBillingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemBilling
	 * @return a List with the OrderItemBillings
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemBillingsBy query = new FindOrderItemBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemBilling> orderItemBillings =((OrderItemBillingFound) Scheduler.execute(query).data()).getOrderItemBillings();

		if (orderItemBillings.size() == 1) {
			return ResponseEntity.ok().body(orderItemBillings.get(0));
		}

		return ResponseEntity.ok().body(orderItemBillings);

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
	public ResponseEntity<Object> createOrderItemBilling(HttpServletRequest request) throws Exception {

		OrderItemBilling orderItemBillingToBeAdded = new OrderItemBilling();
		try {
			orderItemBillingToBeAdded = OrderItemBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemBilling(orderItemBillingToBeAdded);

	}

	/**
	 * creates a new OrderItemBilling entry in the ofbiz database
	 * 
	 * @param orderItemBillingToBeAdded
	 *            the OrderItemBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemBilling(@RequestBody OrderItemBilling orderItemBillingToBeAdded) throws Exception {

		AddOrderItemBilling command = new AddOrderItemBilling(orderItemBillingToBeAdded);
		OrderItemBilling orderItemBilling = ((OrderItemBillingAdded) Scheduler.execute(command).data()).getAddedOrderItemBilling();
		
		if (orderItemBilling != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemBilling);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemBilling could not be created.");
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
	public boolean updateOrderItemBilling(HttpServletRequest request) throws Exception {

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

		OrderItemBilling orderItemBillingToBeUpdated = new OrderItemBilling();

		try {
			orderItemBillingToBeUpdated = OrderItemBillingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemBilling(orderItemBillingToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemBilling with the specific Id
	 * 
	 * @param orderItemBillingToBeUpdated
	 *            the OrderItemBilling thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemBilling(@RequestBody OrderItemBilling orderItemBillingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemBillingToBeUpdated.setnull(null);

		UpdateOrderItemBilling command = new UpdateOrderItemBilling(orderItemBillingToBeUpdated);

		try {
			if(((OrderItemBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemBillingId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemBillingId", orderItemBillingId);
		try {

			Object foundOrderItemBilling = findOrderItemBillingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemBilling);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemBillingId}")
	public ResponseEntity<Object> deleteOrderItemBillingByIdUpdated(@PathVariable String orderItemBillingId) throws Exception {
		DeleteOrderItemBilling command = new DeleteOrderItemBilling(orderItemBillingId);

		try {
			if (((OrderItemBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemBilling could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemBilling/\" plus one of the following: "
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