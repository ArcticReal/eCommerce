package com.skytala.eCommerce.domain.order.relations.orderItem.control.role;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.role.AddOrderItemRole;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.role.DeleteOrderItemRole;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.role.UpdateOrderItemRole;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.role.OrderItemRoleAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.role.OrderItemRoleDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.role.OrderItemRoleFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.role.OrderItemRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.role.OrderItemRoleMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.role.OrderItemRole;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.role.FindOrderItemRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/orderItemRoles")
public class OrderItemRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemRole
	 * @return a List with the OrderItemRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemRolesBy query = new FindOrderItemRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemRole> orderItemRoles =((OrderItemRoleFound) Scheduler.execute(query).data()).getOrderItemRoles();

		if (orderItemRoles.size() == 1) {
			return ResponseEntity.ok().body(orderItemRoles.get(0));
		}

		return ResponseEntity.ok().body(orderItemRoles);

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
	public ResponseEntity<Object> createOrderItemRole(HttpServletRequest request) throws Exception {

		OrderItemRole orderItemRoleToBeAdded = new OrderItemRole();
		try {
			orderItemRoleToBeAdded = OrderItemRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderItemRole(orderItemRoleToBeAdded);

	}

	/**
	 * creates a new OrderItemRole entry in the ofbiz database
	 * 
	 * @param orderItemRoleToBeAdded
	 *            the OrderItemRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderItemRole(@RequestBody OrderItemRole orderItemRoleToBeAdded) throws Exception {

		AddOrderItemRole command = new AddOrderItemRole(orderItemRoleToBeAdded);
		OrderItemRole orderItemRole = ((OrderItemRoleAdded) Scheduler.execute(command).data()).getAddedOrderItemRole();
		
		if (orderItemRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemRole could not be created.");
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
	public boolean updateOrderItemRole(HttpServletRequest request) throws Exception {

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

		OrderItemRole orderItemRoleToBeUpdated = new OrderItemRole();

		try {
			orderItemRoleToBeUpdated = OrderItemRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemRole(orderItemRoleToBeUpdated, orderItemRoleToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderItemRole with the specific Id
	 * 
	 * @param orderItemRoleToBeUpdated
	 *            the OrderItemRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderItemRole(@RequestBody OrderItemRole orderItemRoleToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemRoleToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItemRole command = new UpdateOrderItemRole(orderItemRoleToBeUpdated);

		try {
			if(((OrderItemRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemRoleId", orderItemRoleId);
		try {

			Object foundOrderItemRole = findOrderItemRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemRoleId}")
	public ResponseEntity<Object> deleteOrderItemRoleByIdUpdated(@PathVariable String orderItemRoleId) throws Exception {
		DeleteOrderItemRole command = new DeleteOrderItemRole(orderItemRoleId);

		try {
			if (((OrderItemRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderItemRole/\" plus one of the following: "
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
