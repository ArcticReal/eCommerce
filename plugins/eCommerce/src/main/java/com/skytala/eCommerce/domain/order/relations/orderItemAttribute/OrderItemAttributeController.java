package com.skytala.eCommerce.domain.order.relations.orderItemAttribute;

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
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.command.AddOrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.command.DeleteOrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.command.UpdateOrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event.OrderItemAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event.OrderItemAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event.OrderItemAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event.OrderItemAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.mapper.OrderItemAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.query.FindOrderItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemAttributes")
public class OrderItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemAttribute
	 * @return a List with the OrderItemAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemAttributesBy query = new FindOrderItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemAttribute> orderItemAttributes =((OrderItemAttributeFound) Scheduler.execute(query).data()).getOrderItemAttributes();

		if (orderItemAttributes.size() == 1) {
			return ResponseEntity.ok().body(orderItemAttributes.get(0));
		}

		return ResponseEntity.ok().body(orderItemAttributes);

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
	public ResponseEntity<Object> createOrderItemAttribute(HttpServletRequest request) throws Exception {

		OrderItemAttribute orderItemAttributeToBeAdded = new OrderItemAttribute();
		try {
			orderItemAttributeToBeAdded = OrderItemAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemAttribute(orderItemAttributeToBeAdded);

	}

	/**
	 * creates a new OrderItemAttribute entry in the ofbiz database
	 * 
	 * @param orderItemAttributeToBeAdded
	 *            the OrderItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemAttribute(@RequestBody OrderItemAttribute orderItemAttributeToBeAdded) throws Exception {

		AddOrderItemAttribute command = new AddOrderItemAttribute(orderItemAttributeToBeAdded);
		OrderItemAttribute orderItemAttribute = ((OrderItemAttributeAdded) Scheduler.execute(command).data()).getAddedOrderItemAttribute();
		
		if (orderItemAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemAttribute could not be created.");
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
	public boolean updateOrderItemAttribute(HttpServletRequest request) throws Exception {

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

		OrderItemAttribute orderItemAttributeToBeUpdated = new OrderItemAttribute();

		try {
			orderItemAttributeToBeUpdated = OrderItemAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemAttribute(orderItemAttributeToBeUpdated, orderItemAttributeToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemAttribute with the specific Id
	 * 
	 * @param orderItemAttributeToBeUpdated
	 *            the OrderItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemAttribute(@RequestBody OrderItemAttribute orderItemAttributeToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemAttributeToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItemAttribute command = new UpdateOrderItemAttribute(orderItemAttributeToBeUpdated);

		try {
			if(((OrderItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemAttributeId", orderItemAttributeId);
		try {

			Object foundOrderItemAttribute = findOrderItemAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemAttributeId}")
	public ResponseEntity<Object> deleteOrderItemAttributeByIdUpdated(@PathVariable String orderItemAttributeId) throws Exception {
		DeleteOrderItemAttribute command = new DeleteOrderItemAttribute(orderItemAttributeId);

		try {
			if (((OrderItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemAttribute/\" plus one of the following: "
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