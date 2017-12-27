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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<OrderRole>> findOrderRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderRolesBy query = new FindOrderRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderRole> orderRoles =((OrderRoleFound) Scheduler.execute(query).data()).getOrderRoles();

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
	public ResponseEntity<OrderRole> createOrderRole(HttpServletRequest request) throws Exception {

		OrderRole orderRoleToBeAdded = new OrderRole();
		try {
			orderRoleToBeAdded = OrderRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<OrderRole> createOrderRole(@RequestBody OrderRole orderRoleToBeAdded) throws Exception {

		AddOrderRole command = new AddOrderRole(orderRoleToBeAdded);
		OrderRole orderRole = ((OrderRoleAdded) Scheduler.execute(command).data()).getAddedOrderRole();
		
		if (orderRole != null) 
			return successful(orderRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateOrderRole(@RequestBody OrderRole orderRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		orderRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateOrderRole command = new UpdateOrderRole(orderRoleToBeUpdated);

		try {
			if(((OrderRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderRoleId}")
	public ResponseEntity<OrderRole> findById(@PathVariable String orderRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderRoleId", orderRoleId);
		try {

			List<OrderRole> foundOrderRole = findOrderRolesBy(requestParams).getBody();
			if(foundOrderRole.size()==1){				return successful(foundOrderRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderRoleId}")
	public ResponseEntity<String> deleteOrderRoleByIdUpdated(@PathVariable String orderRoleId) throws Exception {
		DeleteOrderRole command = new DeleteOrderRole(orderRoleId);

		try {
			if (((OrderRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
