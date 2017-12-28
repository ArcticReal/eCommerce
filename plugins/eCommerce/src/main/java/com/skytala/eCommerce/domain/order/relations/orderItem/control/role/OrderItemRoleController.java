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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemRoles")
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
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemRole>> findOrderItemRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemRolesBy query = new FindOrderItemRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemRole> orderItemRoles =((OrderItemRoleFound) Scheduler.execute(query).data()).getOrderItemRoles();

		return ResponseEntity.ok().body(orderItemRoles);

	}

	/**
	 * creates a new OrderItemRole entry in the ofbiz database
	 * 
	 * @param orderItemRoleToBeAdded
	 *            the OrderItemRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemRole> createOrderItemRole(@RequestBody OrderItemRole orderItemRoleToBeAdded) throws Exception {

		AddOrderItemRole command = new AddOrderItemRole(orderItemRoleToBeAdded);
		OrderItemRole orderItemRole = ((OrderItemRoleAdded) Scheduler.execute(command).data()).getAddedOrderItemRole();
		
		if (orderItemRole != null) 
			return successful(orderItemRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemRole with the specific Id
	 * 
	 * @param orderItemRoleToBeUpdated
	 *            the OrderItemRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemRole(@RequestBody OrderItemRole orderItemRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemRoleToBeUpdated.setnull(null);

		UpdateOrderItemRole command = new UpdateOrderItemRole(orderItemRoleToBeUpdated);

		try {
			if(((OrderItemRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemRoleId}")
	public ResponseEntity<OrderItemRole> findById(@PathVariable String orderItemRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemRoleId", orderItemRoleId);
		try {

			List<OrderItemRole> foundOrderItemRole = findOrderItemRolesBy(requestParams).getBody();
			if(foundOrderItemRole.size()==1){				return successful(foundOrderItemRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemRoleId}")
	public ResponseEntity<String> deleteOrderItemRoleByIdUpdated(@PathVariable String orderItemRoleId) throws Exception {
		DeleteOrderItemRole command = new DeleteOrderItemRole(orderItemRoleId);

		try {
			if (((OrderItemRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
