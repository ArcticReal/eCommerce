package com.skytala.eCommerce.domain.order.relations.orderItem.control.shipGrpInvRes;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes.AddOrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes.DeleteOrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes.UpdateOrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGrpInvRes.OrderItemShipGrpInvResMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGrpInvRes.OrderItemShipGrpInvRes;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.shipGrpInvRes.FindOrderItemShipGrpInvRessBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemShipGrpInvRess")
public class OrderItemShipGrpInvResController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemShipGrpInvResController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemShipGrpInvRes
	 * @return a List with the OrderItemShipGrpInvRess
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemShipGrpInvRessBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemShipGrpInvRessBy query = new FindOrderItemShipGrpInvRessBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemShipGrpInvRes> orderItemShipGrpInvRess =((OrderItemShipGrpInvResFound) Scheduler.execute(query).data()).getOrderItemShipGrpInvRess();

		if (orderItemShipGrpInvRess.size() == 1) {
			return ResponseEntity.ok().body(orderItemShipGrpInvRess.get(0));
		}

		return ResponseEntity.ok().body(orderItemShipGrpInvRess);

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
	public ResponseEntity<Object> createOrderItemShipGrpInvRes(HttpServletRequest request) throws Exception {

		OrderItemShipGrpInvRes orderItemShipGrpInvResToBeAdded = new OrderItemShipGrpInvRes();
		try {
			orderItemShipGrpInvResToBeAdded = OrderItemShipGrpInvResMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemShipGrpInvRes(orderItemShipGrpInvResToBeAdded);

	}

	/**
	 * creates a new OrderItemShipGrpInvRes entry in the ofbiz database
	 * 
	 * @param orderItemShipGrpInvResToBeAdded
	 *            the OrderItemShipGrpInvRes thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemShipGrpInvRes(@RequestBody OrderItemShipGrpInvRes orderItemShipGrpInvResToBeAdded) throws Exception {

		AddOrderItemShipGrpInvRes command = new AddOrderItemShipGrpInvRes(orderItemShipGrpInvResToBeAdded);
		OrderItemShipGrpInvRes orderItemShipGrpInvRes = ((OrderItemShipGrpInvResAdded) Scheduler.execute(command).data()).getAddedOrderItemShipGrpInvRes();
		
		if (orderItemShipGrpInvRes != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemShipGrpInvRes);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemShipGrpInvRes could not be created.");
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
	public boolean updateOrderItemShipGrpInvRes(HttpServletRequest request) throws Exception {

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

		OrderItemShipGrpInvRes orderItemShipGrpInvResToBeUpdated = new OrderItemShipGrpInvRes();

		try {
			orderItemShipGrpInvResToBeUpdated = OrderItemShipGrpInvResMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemShipGrpInvRes(orderItemShipGrpInvResToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemShipGrpInvRes with the specific Id
	 * 
	 * @param orderItemShipGrpInvResToBeUpdated
	 *            the OrderItemShipGrpInvRes thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemShipGrpInvRes(@RequestBody OrderItemShipGrpInvRes orderItemShipGrpInvResToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemShipGrpInvResToBeUpdated.setnull(null);

		UpdateOrderItemShipGrpInvRes command = new UpdateOrderItemShipGrpInvRes(orderItemShipGrpInvResToBeUpdated);

		try {
			if(((OrderItemShipGrpInvResUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemShipGrpInvResId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemShipGrpInvResId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemShipGrpInvResId", orderItemShipGrpInvResId);
		try {

			Object foundOrderItemShipGrpInvRes = findOrderItemShipGrpInvRessBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemShipGrpInvRes);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemShipGrpInvResId}")
	public ResponseEntity<Object> deleteOrderItemShipGrpInvResByIdUpdated(@PathVariable String orderItemShipGrpInvResId) throws Exception {
		DeleteOrderItemShipGrpInvRes command = new DeleteOrderItemShipGrpInvRes(orderItemShipGrpInvResId);

		try {
			if (((OrderItemShipGrpInvResDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemShipGrpInvRes could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemShipGrpInvRes/\" plus one of the following: "
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
