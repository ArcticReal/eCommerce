package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry;

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
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command.AddOrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command.DeleteOrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command.UpdateOrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryAdded;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryDeleted;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryFound;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryUpdated;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.mapper.OrderSummaryEntryMapper;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.query.FindOrderSummaryEntrysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/orderSummaryEntrys")
public class OrderSummaryEntryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderSummaryEntryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderSummaryEntry
	 * @return a List with the OrderSummaryEntrys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderSummaryEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderSummaryEntrysBy query = new FindOrderSummaryEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderSummaryEntry> orderSummaryEntrys =((OrderSummaryEntryFound) Scheduler.execute(query).data()).getOrderSummaryEntrys();

		if (orderSummaryEntrys.size() == 1) {
			return ResponseEntity.ok().body(orderSummaryEntrys.get(0));
		}

		return ResponseEntity.ok().body(orderSummaryEntrys);

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
	public ResponseEntity<Object> createOrderSummaryEntry(HttpServletRequest request) throws Exception {

		OrderSummaryEntry orderSummaryEntryToBeAdded = new OrderSummaryEntry();
		try {
			orderSummaryEntryToBeAdded = OrderSummaryEntryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderSummaryEntry(orderSummaryEntryToBeAdded);

	}

	/**
	 * creates a new OrderSummaryEntry entry in the ofbiz database
	 * 
	 * @param orderSummaryEntryToBeAdded
	 *            the OrderSummaryEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderSummaryEntry(@RequestBody OrderSummaryEntry orderSummaryEntryToBeAdded) throws Exception {

		AddOrderSummaryEntry command = new AddOrderSummaryEntry(orderSummaryEntryToBeAdded);
		OrderSummaryEntry orderSummaryEntry = ((OrderSummaryEntryAdded) Scheduler.execute(command).data()).getAddedOrderSummaryEntry();
		
		if (orderSummaryEntry != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderSummaryEntry);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderSummaryEntry could not be created.");
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
	public boolean updateOrderSummaryEntry(HttpServletRequest request) throws Exception {

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

		OrderSummaryEntry orderSummaryEntryToBeUpdated = new OrderSummaryEntry();

		try {
			orderSummaryEntryToBeUpdated = OrderSummaryEntryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderSummaryEntry(orderSummaryEntryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderSummaryEntry with the specific Id
	 * 
	 * @param orderSummaryEntryToBeUpdated
	 *            the OrderSummaryEntry thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderSummaryEntry(@RequestBody OrderSummaryEntry orderSummaryEntryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderSummaryEntryToBeUpdated.setnull(null);

		UpdateOrderSummaryEntry command = new UpdateOrderSummaryEntry(orderSummaryEntryToBeUpdated);

		try {
			if(((OrderSummaryEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderSummaryEntryId}")
	public ResponseEntity<Object> findById(@PathVariable String orderSummaryEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderSummaryEntryId", orderSummaryEntryId);
		try {

			Object foundOrderSummaryEntry = findOrderSummaryEntrysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderSummaryEntry);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderSummaryEntryId}")
	public ResponseEntity<Object> deleteOrderSummaryEntryByIdUpdated(@PathVariable String orderSummaryEntryId) throws Exception {
		DeleteOrderSummaryEntry command = new DeleteOrderSummaryEntry(orderSummaryEntryId);

		try {
			if (((OrderSummaryEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderSummaryEntry could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderSummaryEntry/\" plus one of the following: "
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
