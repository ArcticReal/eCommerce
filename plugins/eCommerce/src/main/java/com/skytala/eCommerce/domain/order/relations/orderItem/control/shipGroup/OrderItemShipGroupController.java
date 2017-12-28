package com.skytala.eCommerce.domain.order.relations.orderItem.control.shipGroup;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroup.AddOrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroup.DeleteOrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroup.UpdateOrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGroup.OrderItemShipGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.shipGroup.FindOrderItemShipGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemShipGroups")
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
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemShipGroup>> findOrderItemShipGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemShipGroupsBy query = new FindOrderItemShipGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemShipGroup> orderItemShipGroups =((OrderItemShipGroupFound) Scheduler.execute(query).data()).getOrderItemShipGroups();

		return ResponseEntity.ok().body(orderItemShipGroups);

	}

	/**
	 * creates a new OrderItemShipGroup entry in the ofbiz database
	 * 
	 * @param orderItemShipGroupToBeAdded
	 *            the OrderItemShipGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemShipGroup> createOrderItemShipGroup(@RequestBody OrderItemShipGroup orderItemShipGroupToBeAdded) throws Exception {

		AddOrderItemShipGroup command = new AddOrderItemShipGroup(orderItemShipGroupToBeAdded);
		OrderItemShipGroup orderItemShipGroup = ((OrderItemShipGroupAdded) Scheduler.execute(command).data()).getAddedOrderItemShipGroup();
		
		if (orderItemShipGroup != null) 
			return successful(orderItemShipGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateOrderItemShipGroup(@RequestBody OrderItemShipGroup orderItemShipGroupToBeUpdated,
			@PathVariable String shipGroupSeqId) throws Exception {

		orderItemShipGroupToBeUpdated.setShipGroupSeqId(shipGroupSeqId);

		UpdateOrderItemShipGroup command = new UpdateOrderItemShipGroup(orderItemShipGroupToBeUpdated);

		try {
			if(((OrderItemShipGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemShipGroupId}")
	public ResponseEntity<OrderItemShipGroup> findById(@PathVariable String orderItemShipGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemShipGroupId", orderItemShipGroupId);
		try {

			List<OrderItemShipGroup> foundOrderItemShipGroup = findOrderItemShipGroupsBy(requestParams).getBody();
			if(foundOrderItemShipGroup.size()==1){				return successful(foundOrderItemShipGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemShipGroupId}")
	public ResponseEntity<String> deleteOrderItemShipGroupByIdUpdated(@PathVariable String orderItemShipGroupId) throws Exception {
		DeleteOrderItemShipGroup command = new DeleteOrderItemShipGroup(orderItemShipGroupId);

		try {
			if (((OrderItemShipGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
