package com.skytala.eCommerce.domain.order.relations.orderTerm;

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
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.AddOrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.DeleteOrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.UpdateOrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermAdded;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermDeleted;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermFound;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermUpdated;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.OrderTermMapper;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
import com.skytala.eCommerce.domain.order.relations.orderTerm.query.FindOrderTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderTerms")
public class OrderTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderTerm
	 * @return a List with the OrderTerms
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTermsBy query = new FindOrderTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderTerm> orderTerms =((OrderTermFound) Scheduler.execute(query).data()).getOrderTerms();

		if (orderTerms.size() == 1) {
			return ResponseEntity.ok().body(orderTerms.get(0));
		}

		return ResponseEntity.ok().body(orderTerms);

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
	public ResponseEntity<Object> createOrderTerm(HttpServletRequest request) throws Exception {

		OrderTerm orderTermToBeAdded = new OrderTerm();
		try {
			orderTermToBeAdded = OrderTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderTerm(orderTermToBeAdded);

	}

	/**
	 * creates a new OrderTerm entry in the ofbiz database
	 * 
	 * @param orderTermToBeAdded
	 *            the OrderTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderTerm(@RequestBody OrderTerm orderTermToBeAdded) throws Exception {

		AddOrderTerm command = new AddOrderTerm(orderTermToBeAdded);
		OrderTerm orderTerm = ((OrderTermAdded) Scheduler.execute(command).data()).getAddedOrderTerm();
		
		if (orderTerm != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderTerm);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderTerm could not be created.");
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
	public boolean updateOrderTerm(HttpServletRequest request) throws Exception {

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

		OrderTerm orderTermToBeUpdated = new OrderTerm();

		try {
			orderTermToBeUpdated = OrderTermMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderTerm(orderTermToBeUpdated, orderTermToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderTerm with the specific Id
	 * 
	 * @param orderTermToBeUpdated
	 *            the OrderTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderTerm(@RequestBody OrderTerm orderTermToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderTermToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderTerm command = new UpdateOrderTerm(orderTermToBeUpdated);

		try {
			if(((OrderTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderTermId}")
	public ResponseEntity<Object> findById(@PathVariable String orderTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTermId", orderTermId);
		try {

			Object foundOrderTerm = findOrderTermsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderTerm);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderTermId}")
	public ResponseEntity<Object> deleteOrderTermByIdUpdated(@PathVariable String orderTermId) throws Exception {
		DeleteOrderTerm command = new DeleteOrderTerm(orderTermId);

		try {
			if (((OrderTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderTerm could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderTerm/\" plus one of the following: "
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
