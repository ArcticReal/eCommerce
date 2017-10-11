package com.skytala.eCommerce.domain.order.relations.orderItemType;

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
import com.skytala.eCommerce.domain.order.relations.orderItemType.command.AddOrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItemType.command.DeleteOrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItemType.command.UpdateOrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItemType.event.OrderItemTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemType.event.OrderItemTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItemType.event.OrderItemTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderItemType.event.OrderItemTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItemType.mapper.OrderItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemType.model.OrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItemType.query.FindOrderItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemTypes")
public class OrderItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemType
	 * @return a List with the OrderItemTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemTypesBy query = new FindOrderItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemType> orderItemTypes =((OrderItemTypeFound) Scheduler.execute(query).data()).getOrderItemTypes();

		if (orderItemTypes.size() == 1) {
			return ResponseEntity.ok().body(orderItemTypes.get(0));
		}

		return ResponseEntity.ok().body(orderItemTypes);

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
	public ResponseEntity<Object> createOrderItemType(HttpServletRequest request) throws Exception {

		OrderItemType orderItemTypeToBeAdded = new OrderItemType();
		try {
			orderItemTypeToBeAdded = OrderItemTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemType(orderItemTypeToBeAdded);

	}

	/**
	 * creates a new OrderItemType entry in the ofbiz database
	 * 
	 * @param orderItemTypeToBeAdded
	 *            the OrderItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemType(@RequestBody OrderItemType orderItemTypeToBeAdded) throws Exception {

		AddOrderItemType command = new AddOrderItemType(orderItemTypeToBeAdded);
		OrderItemType orderItemType = ((OrderItemTypeAdded) Scheduler.execute(command).data()).getAddedOrderItemType();
		
		if (orderItemType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemType could not be created.");
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
	public boolean updateOrderItemType(HttpServletRequest request) throws Exception {

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

		OrderItemType orderItemTypeToBeUpdated = new OrderItemType();

		try {
			orderItemTypeToBeUpdated = OrderItemTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemType(orderItemTypeToBeUpdated, orderItemTypeToBeUpdated.getOrderItemTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemType with the specific Id
	 * 
	 * @param orderItemTypeToBeUpdated
	 *            the OrderItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemType(@RequestBody OrderItemType orderItemTypeToBeUpdated,
			@PathVariable String orderItemTypeId) throws Exception {

		orderItemTypeToBeUpdated.setOrderItemTypeId(orderItemTypeId);

		UpdateOrderItemType command = new UpdateOrderItemType(orderItemTypeToBeUpdated);

		try {
			if(((OrderItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemTypeId", orderItemTypeId);
		try {

			Object foundOrderItemType = findOrderItemTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemTypeId}")
	public ResponseEntity<Object> deleteOrderItemTypeByIdUpdated(@PathVariable String orderItemTypeId) throws Exception {
		DeleteOrderItemType command = new DeleteOrderItemType(orderItemTypeId);

		try {
			if (((OrderItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemType/\" plus one of the following: "
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
