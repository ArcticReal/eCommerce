package com.skytala.eCommerce.domain.order.relations.orderItem.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr.AddOrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr.DeleteOrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr.UpdateOrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.typeAttr.OrderItemTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.typeAttr.OrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.typeAttr.FindOrderItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/orderItemTypeAttrs")
public class OrderItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemTypeAttr
	 * @return a List with the OrderItemTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemTypeAttrsBy query = new FindOrderItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemTypeAttr> orderItemTypeAttrs =((OrderItemTypeAttrFound) Scheduler.execute(query).data()).getOrderItemTypeAttrs();

		if (orderItemTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(orderItemTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(orderItemTypeAttrs);

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
	public ResponseEntity<Object> createOrderItemTypeAttr(HttpServletRequest request) throws Exception {

		OrderItemTypeAttr orderItemTypeAttrToBeAdded = new OrderItemTypeAttr();
		try {
			orderItemTypeAttrToBeAdded = OrderItemTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemTypeAttr(orderItemTypeAttrToBeAdded);

	}

	/**
	 * creates a new OrderItemTypeAttr entry in the ofbiz database
	 * 
	 * @param orderItemTypeAttrToBeAdded
	 *            the OrderItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemTypeAttr(@RequestBody OrderItemTypeAttr orderItemTypeAttrToBeAdded) throws Exception {

		AddOrderItemTypeAttr command = new AddOrderItemTypeAttr(orderItemTypeAttrToBeAdded);
		OrderItemTypeAttr orderItemTypeAttr = ((OrderItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedOrderItemTypeAttr();
		
		if (orderItemTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemTypeAttr could not be created.");
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
	public boolean updateOrderItemTypeAttr(HttpServletRequest request) throws Exception {

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

		OrderItemTypeAttr orderItemTypeAttrToBeUpdated = new OrderItemTypeAttr();

		try {
			orderItemTypeAttrToBeUpdated = OrderItemTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemTypeAttr(orderItemTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemTypeAttr with the specific Id
	 * 
	 * @param orderItemTypeAttrToBeUpdated
	 *            the OrderItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemTypeAttr(@RequestBody OrderItemTypeAttr orderItemTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemTypeAttrToBeUpdated.setnull(null);

		UpdateOrderItemTypeAttr command = new UpdateOrderItemTypeAttr(orderItemTypeAttrToBeUpdated);

		try {
			if(((OrderItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemTypeAttrId", orderItemTypeAttrId);
		try {

			Object foundOrderItemTypeAttr = findOrderItemTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemTypeAttrId}")
	public ResponseEntity<Object> deleteOrderItemTypeAttrByIdUpdated(@PathVariable String orderItemTypeAttrId) throws Exception {
		DeleteOrderItemTypeAttr command = new DeleteOrderItemTypeAttr(orderItemTypeAttrId);

		try {
			if (((OrderItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemTypeAttr/\" plus one of the following: "
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
