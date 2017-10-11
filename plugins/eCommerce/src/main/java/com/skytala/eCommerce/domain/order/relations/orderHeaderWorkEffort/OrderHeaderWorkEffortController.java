package com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort;

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
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.command.AddOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.command.DeleteOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.command.UpdateOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event.OrderHeaderWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event.OrderHeaderWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event.OrderHeaderWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event.OrderHeaderWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.mapper.OrderHeaderWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.model.OrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.query.FindOrderHeaderWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderHeaderWorkEfforts")
public class OrderHeaderWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderHeaderWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderHeaderWorkEffort
	 * @return a List with the OrderHeaderWorkEfforts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderHeaderWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderHeaderWorkEffortsBy query = new FindOrderHeaderWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeaderWorkEffort> orderHeaderWorkEfforts =((OrderHeaderWorkEffortFound) Scheduler.execute(query).data()).getOrderHeaderWorkEfforts();

		if (orderHeaderWorkEfforts.size() == 1) {
			return ResponseEntity.ok().body(orderHeaderWorkEfforts.get(0));
		}

		return ResponseEntity.ok().body(orderHeaderWorkEfforts);

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
	public ResponseEntity<Object> createOrderHeaderWorkEffort(HttpServletRequest request) throws Exception {

		OrderHeaderWorkEffort orderHeaderWorkEffortToBeAdded = new OrderHeaderWorkEffort();
		try {
			orderHeaderWorkEffortToBeAdded = OrderHeaderWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderHeaderWorkEffort(orderHeaderWorkEffortToBeAdded);

	}

	/**
	 * creates a new OrderHeaderWorkEffort entry in the ofbiz database
	 * 
	 * @param orderHeaderWorkEffortToBeAdded
	 *            the OrderHeaderWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderHeaderWorkEffort(@RequestBody OrderHeaderWorkEffort orderHeaderWorkEffortToBeAdded) throws Exception {

		AddOrderHeaderWorkEffort command = new AddOrderHeaderWorkEffort(orderHeaderWorkEffortToBeAdded);
		OrderHeaderWorkEffort orderHeaderWorkEffort = ((OrderHeaderWorkEffortAdded) Scheduler.execute(command).data()).getAddedOrderHeaderWorkEffort();
		
		if (orderHeaderWorkEffort != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderHeaderWorkEffort);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderHeaderWorkEffort could not be created.");
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
	public boolean updateOrderHeaderWorkEffort(HttpServletRequest request) throws Exception {

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

		OrderHeaderWorkEffort orderHeaderWorkEffortToBeUpdated = new OrderHeaderWorkEffort();

		try {
			orderHeaderWorkEffortToBeUpdated = OrderHeaderWorkEffortMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderHeaderWorkEffort(orderHeaderWorkEffortToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderHeaderWorkEffort with the specific Id
	 * 
	 * @param orderHeaderWorkEffortToBeUpdated
	 *            the OrderHeaderWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderHeaderWorkEffort(@RequestBody OrderHeaderWorkEffort orderHeaderWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderHeaderWorkEffortToBeUpdated.setnull(null);

		UpdateOrderHeaderWorkEffort command = new UpdateOrderHeaderWorkEffort(orderHeaderWorkEffortToBeUpdated);

		try {
			if(((OrderHeaderWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderHeaderWorkEffortId}")
	public ResponseEntity<Object> findById(@PathVariable String orderHeaderWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderHeaderWorkEffortId", orderHeaderWorkEffortId);
		try {

			Object foundOrderHeaderWorkEffort = findOrderHeaderWorkEffortsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderHeaderWorkEffort);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderHeaderWorkEffortId}")
	public ResponseEntity<Object> deleteOrderHeaderWorkEffortByIdUpdated(@PathVariable String orderHeaderWorkEffortId) throws Exception {
		DeleteOrderHeaderWorkEffort command = new DeleteOrderHeaderWorkEffort(orderHeaderWorkEffortId);

		try {
			if (((OrderHeaderWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderHeaderWorkEffort could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderHeaderWorkEffort/\" plus one of the following: "
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
