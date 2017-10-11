package com.skytala.eCommerce.domain.order.relations.orderItemShipGroup;

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
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.command.AddOrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.command.DeleteOrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.command.UpdateOrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event.OrderItemShipGroupAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event.OrderItemShipGroupDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event.OrderItemShipGroupFound;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event.OrderItemShipGroupUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.mapper.OrderItemShipGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.model.OrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.query.FindOrderItemShipGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderItemShipGroups")
public class OrderItemShipGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemShipGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemShipGroup
	 * @return a List with the OrderItemShipGroups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemShipGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemShipGroupsBy query = new FindOrderItemShipGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemShipGroup> orderItemShipGroups =((OrderItemShipGroupFound) Scheduler.execute(query).data()).getOrderItemShipGroups();

		if (orderItemShipGroups.size() == 1) {
			return ResponseEntity.ok().body(orderItemShipGroups.get(0));
		}

		return ResponseEntity.ok().body(orderItemShipGroups);

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
	public ResponseEntity<Object> createOrderItemShipGroup(HttpServletRequest request) throws Exception {

		OrderItemShipGroup orderItemShipGroupToBeAdded = new OrderItemShipGroup();
		try {
			orderItemShipGroupToBeAdded = OrderItemShipGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemShipGroup(orderItemShipGroupToBeAdded);

	}

	/**
	 * creates a new OrderItemShipGroup entry in the ofbiz database
	 * 
	 * @param orderItemShipGroupToBeAdded
	 *            the OrderItemShipGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemShipGroup(@RequestBody OrderItemShipGroup orderItemShipGroupToBeAdded) throws Exception {

		AddOrderItemShipGroup command = new AddOrderItemShipGroup(orderItemShipGroupToBeAdded);
		OrderItemShipGroup orderItemShipGroup = ((OrderItemShipGroupAdded) Scheduler.execute(command).data()).getAddedOrderItemShipGroup();
		
		if (orderItemShipGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemShipGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemShipGroup could not be created.");
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
	public boolean updateOrderItemShipGroup(HttpServletRequest request) throws Exception {

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

		OrderItemShipGroup orderItemShipGroupToBeUpdated = new OrderItemShipGroup();

		try {
			orderItemShipGroupToBeUpdated = OrderItemShipGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemShipGroup(orderItemShipGroupToBeUpdated, orderItemShipGroupToBeUpdated.getShipGroupSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemShipGroup with the specific Id
	 * 
	 * @param orderItemShipGroupToBeUpdated
	 *            the OrderItemShipGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipGroupSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemShipGroup(@RequestBody OrderItemShipGroup orderItemShipGroupToBeUpdated,
			@PathVariable String shipGroupSeqId) throws Exception {

		orderItemShipGroupToBeUpdated.setShipGroupSeqId(shipGroupSeqId);

		UpdateOrderItemShipGroup command = new UpdateOrderItemShipGroup(orderItemShipGroupToBeUpdated);

		try {
			if(((OrderItemShipGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemShipGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemShipGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemShipGroupId", orderItemShipGroupId);
		try {

			Object foundOrderItemShipGroup = findOrderItemShipGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemShipGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemShipGroupId}")
	public ResponseEntity<Object> deleteOrderItemShipGroupByIdUpdated(@PathVariable String orderItemShipGroupId) throws Exception {
		DeleteOrderItemShipGroup command = new DeleteOrderItemShipGroup(orderItemShipGroupId);

		try {
			if (((OrderItemShipGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemShipGroup could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemShipGroup/\" plus one of the following: "
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