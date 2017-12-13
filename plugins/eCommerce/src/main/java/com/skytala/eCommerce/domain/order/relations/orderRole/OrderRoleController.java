package com.skytala.eCommerce.domain.order.relations.orderRole;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.orderRole.command.AddOrderRole;
import com.skytala.eCommerce.domain.order.relations.orderRole.command.DeleteOrderRole;
import com.skytala.eCommerce.domain.order.relations.orderRole.command.UpdateOrderRole;
import com.skytala.eCommerce.domain.order.relations.orderRole.event.OrderRoleAdded;
import com.skytala.eCommerce.domain.order.relations.orderRole.event.OrderRoleDeleted;
import com.skytala.eCommerce.domain.order.relations.orderRole.event.OrderRoleFound;
import com.skytala.eCommerce.domain.order.relations.orderRole.event.OrderRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.orderRole.mapper.OrderRoleMapper;
import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;
import com.skytala.eCommerce.domain.order.relations.orderRole.query.FindOrderRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderRoles")
public class OrderRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderRole
	 * @return a List with the OrderRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderRolesBy query = new FindOrderRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderRole> orderRoles =((OrderRoleFound) Scheduler.execute(query).data()).getOrderRoles();

		if (orderRoles.size() == 1) {
			return ResponseEntity.ok().body(orderRoles.get(0));
		}

		return ResponseEntity.ok().body(orderRoles);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createOrderRole(HttpServletRequest request) throws Exception {

		OrderRole orderRoleToBeAdded = new OrderRole();
		try {
			orderRoleToBeAdded = OrderRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderRole(orderRoleToBeAdded);

	}

	/**
	 * creates a new OrderRole entry in the ofbiz database
	 * 
	 * @param orderRoleToBeAdded
	 *            the OrderRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderRole(@RequestBody OrderRole orderRoleToBeAdded) throws Exception {

		AddOrderRole command = new AddOrderRole(orderRoleToBeAdded);
		OrderRole orderRole = ((OrderRoleAdded) Scheduler.execute(command).data()).getAddedOrderRole();
		
		if (orderRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderRole could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateOrderRole(HttpServletRequest request) throws Exception {

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

		OrderRole orderRoleToBeUpdated = new OrderRole();

		try {
			orderRoleToBeUpdated = OrderRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderRole(orderRoleToBeUpdated, orderRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderRole with the specific Id
	 * 
	 * @param orderRoleToBeUpdated
	 *            the OrderRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderRole(@RequestBody OrderRole orderRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		orderRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateOrderRole command = new UpdateOrderRole(orderRoleToBeUpdated);

		try {
			if(((OrderRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String orderRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderRoleId", orderRoleId);
		try {

			Object foundOrderRole = findOrderRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderRoleId}")
	public ResponseEntity<Object> deleteOrderRoleByIdUpdated(@PathVariable String orderRoleId) throws Exception {
		DeleteOrderRole command = new DeleteOrderRole(orderRoleId);

		try {
			if (((OrderRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderRole could not be deleted");

	}

}
