package com.skytala.eCommerce.domain.order.relations.orderContent;

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
import com.skytala.eCommerce.domain.order.relations.orderContent.command.AddOrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.command.DeleteOrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.command.UpdateOrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentAdded;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentDeleted;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentFound;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContent.mapper.OrderContentMapper;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.query.FindOrderContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderContents")
public class OrderContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderContent
	 * @return a List with the OrderContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderContentsBy query = new FindOrderContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderContent> orderContents =((OrderContentFound) Scheduler.execute(query).data()).getOrderContents();

		if (orderContents.size() == 1) {
			return ResponseEntity.ok().body(orderContents.get(0));
		}

		return ResponseEntity.ok().body(orderContents);

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
	public ResponseEntity<Object> createOrderContent(HttpServletRequest request) throws Exception {

		OrderContent orderContentToBeAdded = new OrderContent();
		try {
			orderContentToBeAdded = OrderContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderContent(orderContentToBeAdded);

	}

	/**
	 * creates a new OrderContent entry in the ofbiz database
	 * 
	 * @param orderContentToBeAdded
	 *            the OrderContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderContent(@RequestBody OrderContent orderContentToBeAdded) throws Exception {

		AddOrderContent command = new AddOrderContent(orderContentToBeAdded);
		OrderContent orderContent = ((OrderContentAdded) Scheduler.execute(command).data()).getAddedOrderContent();
		
		if (orderContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderContent could not be created.");
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
	public boolean updateOrderContent(HttpServletRequest request) throws Exception {

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

		OrderContent orderContentToBeUpdated = new OrderContent();

		try {
			orderContentToBeUpdated = OrderContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderContent(orderContentToBeUpdated, orderContentToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderContent with the specific Id
	 * 
	 * @param orderContentToBeUpdated
	 *            the OrderContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderContent(@RequestBody OrderContent orderContentToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderContentToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderContent command = new UpdateOrderContent(orderContentToBeUpdated);

		try {
			if(((OrderContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderContentId}")
	public ResponseEntity<Object> findById(@PathVariable String orderContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderContentId", orderContentId);
		try {

			Object foundOrderContent = findOrderContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderContentId}")
	public ResponseEntity<Object> deleteOrderContentByIdUpdated(@PathVariable String orderContentId) throws Exception {
		DeleteOrderContent command = new DeleteOrderContent(orderContentId);

		try {
			if (((OrderContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderContent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderContent/\" plus one of the following: "
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
