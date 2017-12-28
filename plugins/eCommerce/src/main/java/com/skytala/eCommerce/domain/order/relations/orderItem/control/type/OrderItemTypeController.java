package com.skytala.eCommerce.domain.order.relations.orderItem.control.type;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.type.AddOrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.type.DeleteOrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.type.UpdateOrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.type.OrderItemTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.type.OrderItemTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.type.OrderItemTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.type.OrderItemTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.type.OrderItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.type.FindOrderItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemTypes")
public class OrderItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemType
	 * @return a List with the OrderItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemType>> findOrderItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemTypesBy query = new FindOrderItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemType> orderItemTypes =((OrderItemTypeFound) Scheduler.execute(query).data()).getOrderItemTypes();

		return ResponseEntity.ok().body(orderItemTypes);

	}

	/**
	 * creates a new OrderItemType entry in the ofbiz database
	 * 
	 * @param orderItemTypeToBeAdded
	 *            the OrderItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemType> createOrderItemType(@RequestBody OrderItemType orderItemTypeToBeAdded) throws Exception {

		AddOrderItemType command = new AddOrderItemType(orderItemTypeToBeAdded);
		OrderItemType orderItemType = ((OrderItemTypeAdded) Scheduler.execute(command).data()).getAddedOrderItemType();
		
		if (orderItemType != null) 
			return successful(orderItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemType with the specific Id
	 * 
	 * @param orderItemTypeToBeUpdated
	 *            the OrderItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemType(@RequestBody OrderItemType orderItemTypeToBeUpdated,
			@PathVariable String orderItemTypeId) throws Exception {

		orderItemTypeToBeUpdated.setOrderItemTypeId(orderItemTypeId);

		UpdateOrderItemType command = new UpdateOrderItemType(orderItemTypeToBeUpdated);

		try {
			if(((OrderItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemTypeId}")
	public ResponseEntity<OrderItemType> findById(@PathVariable String orderItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemTypeId", orderItemTypeId);
		try {

			List<OrderItemType> foundOrderItemType = findOrderItemTypesBy(requestParams).getBody();
			if(foundOrderItemType.size()==1){				return successful(foundOrderItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemTypeId}")
	public ResponseEntity<String> deleteOrderItemTypeByIdUpdated(@PathVariable String orderItemTypeId) throws Exception {
		DeleteOrderItemType command = new DeleteOrderItemType(orderItemTypeId);

		try {
			if (((OrderItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
