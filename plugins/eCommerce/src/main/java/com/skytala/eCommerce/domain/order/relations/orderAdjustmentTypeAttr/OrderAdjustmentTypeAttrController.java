package com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.command.AddOrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.command.DeleteOrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.command.UpdateOrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event.OrderAdjustmentTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event.OrderAdjustmentTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event.OrderAdjustmentTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.event.OrderAdjustmentTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.mapper.OrderAdjustmentTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.model.OrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.query.FindOrderAdjustmentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderAdjustmentTypeAttrs")
public class OrderAdjustmentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustmentTypeAttr
	 * @return a List with the OrderAdjustmentTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderAdjustmentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentTypeAttrsBy query = new FindOrderAdjustmentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentTypeAttr> orderAdjustmentTypeAttrs =((OrderAdjustmentTypeAttrFound) Scheduler.execute(query).data()).getOrderAdjustmentTypeAttrs();

		if (orderAdjustmentTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(orderAdjustmentTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(orderAdjustmentTypeAttrs);

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
	public ResponseEntity<Object> createOrderAdjustmentTypeAttr(HttpServletRequest request) throws Exception {

		OrderAdjustmentTypeAttr orderAdjustmentTypeAttrToBeAdded = new OrderAdjustmentTypeAttr();
		try {
			orderAdjustmentTypeAttrToBeAdded = OrderAdjustmentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrToBeAdded);

	}

	/**
	 * creates a new OrderAdjustmentTypeAttr entry in the ofbiz database
	 * 
	 * @param orderAdjustmentTypeAttrToBeAdded
	 *            the OrderAdjustmentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderAdjustmentTypeAttr(@RequestBody OrderAdjustmentTypeAttr orderAdjustmentTypeAttrToBeAdded) throws Exception {

		AddOrderAdjustmentTypeAttr command = new AddOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrToBeAdded);
		OrderAdjustmentTypeAttr orderAdjustmentTypeAttr = ((OrderAdjustmentTypeAttrAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentTypeAttr();
		
		if (orderAdjustmentTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderAdjustmentTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderAdjustmentTypeAttr could not be created.");
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
	public boolean updateOrderAdjustmentTypeAttr(HttpServletRequest request) throws Exception {

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

		OrderAdjustmentTypeAttr orderAdjustmentTypeAttrToBeUpdated = new OrderAdjustmentTypeAttr();

		try {
			orderAdjustmentTypeAttrToBeUpdated = OrderAdjustmentTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderAdjustmentTypeAttr with the specific Id
	 * 
	 * @param orderAdjustmentTypeAttrToBeUpdated
	 *            the OrderAdjustmentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderAdjustmentTypeAttr(@RequestBody OrderAdjustmentTypeAttr orderAdjustmentTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderAdjustmentTypeAttrToBeUpdated.setnull(null);

		UpdateOrderAdjustmentTypeAttr command = new UpdateOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrToBeUpdated);

		try {
			if(((OrderAdjustmentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderAdjustmentTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String orderAdjustmentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentTypeAttrId", orderAdjustmentTypeAttrId);
		try {

			Object foundOrderAdjustmentTypeAttr = findOrderAdjustmentTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderAdjustmentTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderAdjustmentTypeAttrId}")
	public ResponseEntity<Object> deleteOrderAdjustmentTypeAttrByIdUpdated(@PathVariable String orderAdjustmentTypeAttrId) throws Exception {
		DeleteOrderAdjustmentTypeAttr command = new DeleteOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrId);

		try {
			if (((OrderAdjustmentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderAdjustmentTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderAdjustmentTypeAttr/\" plus one of the following: "
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
