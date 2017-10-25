package com.skytala.eCommerce.domain.order.relations.orderHeader;

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
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.AddOrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.DeleteOrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.UpdateOrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.OrderHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderHeader.query.FindOrderHeadersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderHeaders")
public class OrderHeaderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderHeaderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderHeader
	 * @return a List with the OrderHeaders
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderHeadersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderHeadersBy query = new FindOrderHeadersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeader> orderHeaders =((OrderHeaderFound) Scheduler.execute(query).data()).getOrderHeaders();

		if (orderHeaders.size() == 1) {
			return ResponseEntity.ok().body(orderHeaders.get(0));
		}

		return ResponseEntity.ok().body(orderHeaders);

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
	public ResponseEntity<Object> createOrderHeader(HttpServletRequest request) throws Exception {

		OrderHeader orderHeaderToBeAdded = new OrderHeader();
		try {
			orderHeaderToBeAdded = OrderHeaderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderHeader(orderHeaderToBeAdded);

	}

	/**
	 * creates a new OrderHeader entry in the ofbiz database
	 * 
	 * @param orderHeaderToBeAdded
	 *            the OrderHeader thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderHeader(@RequestBody OrderHeader orderHeaderToBeAdded) throws Exception {

		AddOrderHeader command = new AddOrderHeader(orderHeaderToBeAdded);
		OrderHeader orderHeader = ((OrderHeaderAdded) Scheduler.execute(command).data()).getAddedOrderHeader();
		
		if (orderHeader != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderHeader);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderHeader could not be created.");
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
	public boolean updateOrderHeader(HttpServletRequest request) throws Exception {

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

		OrderHeader orderHeaderToBeUpdated = new OrderHeader();

		try {
			orderHeaderToBeUpdated = OrderHeaderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderHeader(orderHeaderToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderHeader with the specific Id
	 * 
	 * @param orderHeaderToBeUpdated
	 *            the OrderHeader thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderHeader(@RequestBody OrderHeader orderHeaderToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderHeaderToBeUpdated.setnull(null);

		UpdateOrderHeader command = new UpdateOrderHeader(orderHeaderToBeUpdated);

		try {
			if(((OrderHeaderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderHeaderId}")
	public ResponseEntity<Object> findById(@PathVariable String orderHeaderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderHeaderId", orderHeaderId);
		try {

			Object foundOrderHeader = findOrderHeadersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderHeader);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderHeaderId}")
	public ResponseEntity<Object> deleteOrderHeaderByIdUpdated(@PathVariable String orderHeaderId) throws Exception {
		DeleteOrderHeader command = new DeleteOrderHeader(orderHeaderId);

		try {
			if (((OrderHeaderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderHeader could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderHeader/\" plus one of the following: "
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
