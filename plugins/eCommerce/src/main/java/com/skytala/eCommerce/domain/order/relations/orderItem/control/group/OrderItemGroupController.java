package com.skytala.eCommerce.domain.order.relations.orderItem.control.group;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.group.AddOrderItemGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.group.DeleteOrderItemGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.group.UpdateOrderItemGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.group.OrderItemGroupAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.group.OrderItemGroupDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.group.OrderItemGroupFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.group.OrderItemGroupUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.group.OrderItemGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.group.OrderItemGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.group.FindOrderItemGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemGroups")
public class OrderItemGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemGroup
	 * @return a List with the OrderItemGroups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemGroupsBy query = new FindOrderItemGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemGroup> orderItemGroups =((OrderItemGroupFound) Scheduler.execute(query).data()).getOrderItemGroups();

		if (orderItemGroups.size() == 1) {
			return ResponseEntity.ok().body(orderItemGroups.get(0));
		}

		return ResponseEntity.ok().body(orderItemGroups);

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
	public ResponseEntity<Object> createOrderItemGroup(HttpServletRequest request) throws Exception {

		OrderItemGroup orderItemGroupToBeAdded = new OrderItemGroup();
		try {
			orderItemGroupToBeAdded = OrderItemGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemGroup(orderItemGroupToBeAdded);

	}

	/**
	 * creates a new OrderItemGroup entry in the ofbiz database
	 * 
	 * @param orderItemGroupToBeAdded
	 *            the OrderItemGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemGroup(@RequestBody OrderItemGroup orderItemGroupToBeAdded) throws Exception {

		AddOrderItemGroup command = new AddOrderItemGroup(orderItemGroupToBeAdded);
		OrderItemGroup orderItemGroup = ((OrderItemGroupAdded) Scheduler.execute(command).data()).getAddedOrderItemGroup();
		
		if (orderItemGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemGroup could not be created.");
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
	public boolean updateOrderItemGroup(HttpServletRequest request) throws Exception {

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

		OrderItemGroup orderItemGroupToBeUpdated = new OrderItemGroup();

		try {
			orderItemGroupToBeUpdated = OrderItemGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemGroup(orderItemGroupToBeUpdated, orderItemGroupToBeUpdated.getOrderItemGroupSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemGroup with the specific Id
	 * 
	 * @param orderItemGroupToBeUpdated
	 *            the OrderItemGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemGroupSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemGroup(@RequestBody OrderItemGroup orderItemGroupToBeUpdated,
			@PathVariable String orderItemGroupSeqId) throws Exception {

		orderItemGroupToBeUpdated.setOrderItemGroupSeqId(orderItemGroupSeqId);

		UpdateOrderItemGroup command = new UpdateOrderItemGroup(orderItemGroupToBeUpdated);

		try {
			if(((OrderItemGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemGroupId", orderItemGroupId);
		try {

			Object foundOrderItemGroup = findOrderItemGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemGroupId}")
	public ResponseEntity<Object> deleteOrderItemGroupByIdUpdated(@PathVariable String orderItemGroupId) throws Exception {
		DeleteOrderItemGroup command = new DeleteOrderItemGroup(orderItemGroupId);

		try {
			if (((OrderItemGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemGroup could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemGroup/\" plus one of the following: "
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
