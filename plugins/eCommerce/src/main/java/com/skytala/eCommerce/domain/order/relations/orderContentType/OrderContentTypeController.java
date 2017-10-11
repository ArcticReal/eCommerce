package com.skytala.eCommerce.domain.order.relations.orderContentType;

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
import com.skytala.eCommerce.domain.order.relations.orderContentType.command.AddOrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContentType.command.DeleteOrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContentType.command.UpdateOrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContentType.event.OrderContentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderContentType.event.OrderContentTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderContentType.event.OrderContentTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderContentType.event.OrderContentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContentType.mapper.OrderContentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderContentType.model.OrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContentType.query.FindOrderContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderContentTypes")
public class OrderContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderContentType
	 * @return a List with the OrderContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderContentTypesBy query = new FindOrderContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderContentType> orderContentTypes =((OrderContentTypeFound) Scheduler.execute(query).data()).getOrderContentTypes();

		if (orderContentTypes.size() == 1) {
			return ResponseEntity.ok().body(orderContentTypes.get(0));
		}

		return ResponseEntity.ok().body(orderContentTypes);

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
	public ResponseEntity<Object> createOrderContentType(HttpServletRequest request) throws Exception {

		OrderContentType orderContentTypeToBeAdded = new OrderContentType();
		try {
			orderContentTypeToBeAdded = OrderContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderContentType(orderContentTypeToBeAdded);

	}

	/**
	 * creates a new OrderContentType entry in the ofbiz database
	 * 
	 * @param orderContentTypeToBeAdded
	 *            the OrderContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderContentType(@RequestBody OrderContentType orderContentTypeToBeAdded) throws Exception {

		AddOrderContentType command = new AddOrderContentType(orderContentTypeToBeAdded);
		OrderContentType orderContentType = ((OrderContentTypeAdded) Scheduler.execute(command).data()).getAddedOrderContentType();
		
		if (orderContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderContentType could not be created.");
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
	public boolean updateOrderContentType(HttpServletRequest request) throws Exception {

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

		OrderContentType orderContentTypeToBeUpdated = new OrderContentType();

		try {
			orderContentTypeToBeUpdated = OrderContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderContentType(orderContentTypeToBeUpdated, orderContentTypeToBeUpdated.getOrderContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderContentType with the specific Id
	 * 
	 * @param orderContentTypeToBeUpdated
	 *            the OrderContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderContentType(@RequestBody OrderContentType orderContentTypeToBeUpdated,
			@PathVariable String orderContentTypeId) throws Exception {

		orderContentTypeToBeUpdated.setOrderContentTypeId(orderContentTypeId);

		UpdateOrderContentType command = new UpdateOrderContentType(orderContentTypeToBeUpdated);

		try {
			if(((OrderContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderContentTypeId", orderContentTypeId);
		try {

			Object foundOrderContentType = findOrderContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderContentTypeId}")
	public ResponseEntity<Object> deleteOrderContentTypeByIdUpdated(@PathVariable String orderContentTypeId) throws Exception {
		DeleteOrderContentType command = new DeleteOrderContentType(orderContentTypeId);

		try {
			if (((OrderContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderContentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderContentType/\" plus one of the following: "
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