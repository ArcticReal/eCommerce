package com.skytala.eCommerce.domain.order.relations.orderItem.control.assocType;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType.AddOrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType.DeleteOrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType.UpdateOrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.assocType.OrderItemAssocTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.assocType.FindOrderItemAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemAssocTypes")
public class OrderItemAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemAssocType
	 * @return a List with the OrderItemAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemAssocType>> findOrderItemAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemAssocTypesBy query = new FindOrderItemAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemAssocType> orderItemAssocTypes =((OrderItemAssocTypeFound) Scheduler.execute(query).data()).getOrderItemAssocTypes();

		return ResponseEntity.ok().body(orderItemAssocTypes);

	}

	/**
	 * creates a new OrderItemAssocType entry in the ofbiz database
	 * 
	 * @param orderItemAssocTypeToBeAdded
	 *            the OrderItemAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemAssocType> createOrderItemAssocType(@RequestBody OrderItemAssocType orderItemAssocTypeToBeAdded) throws Exception {

		AddOrderItemAssocType command = new AddOrderItemAssocType(orderItemAssocTypeToBeAdded);
		OrderItemAssocType orderItemAssocType = ((OrderItemAssocTypeAdded) Scheduler.execute(command).data()).getAddedOrderItemAssocType();
		
		if (orderItemAssocType != null) 
			return successful(orderItemAssocType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemAssocType with the specific Id
	 * 
	 * @param orderItemAssocTypeToBeUpdated
	 *            the OrderItemAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemAssocType(@RequestBody OrderItemAssocType orderItemAssocTypeToBeUpdated,
			@PathVariable String orderItemAssocTypeId) throws Exception {

		orderItemAssocTypeToBeUpdated.setOrderItemAssocTypeId(orderItemAssocTypeId);

		UpdateOrderItemAssocType command = new UpdateOrderItemAssocType(orderItemAssocTypeToBeUpdated);

		try {
			if(((OrderItemAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemAssocTypeId}")
	public ResponseEntity<OrderItemAssocType> findById(@PathVariable String orderItemAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemAssocTypeId", orderItemAssocTypeId);
		try {

			List<OrderItemAssocType> foundOrderItemAssocType = findOrderItemAssocTypesBy(requestParams).getBody();
			if(foundOrderItemAssocType.size()==1){				return successful(foundOrderItemAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemAssocTypeId}")
	public ResponseEntity<String> deleteOrderItemAssocTypeByIdUpdated(@PathVariable String orderItemAssocTypeId) throws Exception {
		DeleteOrderItemAssocType command = new DeleteOrderItemAssocType(orderItemAssocTypeId);

		try {
			if (((OrderItemAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
