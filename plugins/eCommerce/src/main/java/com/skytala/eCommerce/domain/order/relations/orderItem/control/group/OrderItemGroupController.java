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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemGroups")
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
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemGroup>> findOrderItemGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemGroupsBy query = new FindOrderItemGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemGroup> orderItemGroups =((OrderItemGroupFound) Scheduler.execute(query).data()).getOrderItemGroups();

		return ResponseEntity.ok().body(orderItemGroups);

	}

	/**
	 * creates a new OrderItemGroup entry in the ofbiz database
	 * 
	 * @param orderItemGroupToBeAdded
	 *            the OrderItemGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemGroup> createOrderItemGroup(@RequestBody OrderItemGroup orderItemGroupToBeAdded) throws Exception {

		AddOrderItemGroup command = new AddOrderItemGroup(orderItemGroupToBeAdded);
		OrderItemGroup orderItemGroup = ((OrderItemGroupAdded) Scheduler.execute(command).data()).getAddedOrderItemGroup();
		
		if (orderItemGroup != null) 
			return successful(orderItemGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateOrderItemGroup(@RequestBody OrderItemGroup orderItemGroupToBeUpdated,
			@PathVariable String orderItemGroupSeqId) throws Exception {

		orderItemGroupToBeUpdated.setOrderItemGroupSeqId(orderItemGroupSeqId);

		UpdateOrderItemGroup command = new UpdateOrderItemGroup(orderItemGroupToBeUpdated);

		try {
			if(((OrderItemGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemGroupId}")
	public ResponseEntity<OrderItemGroup> findById(@PathVariable String orderItemGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemGroupId", orderItemGroupId);
		try {

			List<OrderItemGroup> foundOrderItemGroup = findOrderItemGroupsBy(requestParams).getBody();
			if(foundOrderItemGroup.size()==1){				return successful(foundOrderItemGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemGroupId}")
	public ResponseEntity<String> deleteOrderItemGroupByIdUpdated(@PathVariable String orderItemGroupId) throws Exception {
		DeleteOrderItemGroup command = new DeleteOrderItemGroup(orderItemGroupId);

		try {
			if (((OrderItemGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
