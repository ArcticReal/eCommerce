package com.skytala.eCommerce.domain.order.relations.orderAdjustment.control.type;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type.AddOrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type.DeleteOrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type.UpdateOrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.type.OrderAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.type.FindOrderAdjustmentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderAdjustment/orderAdjustmentTypes")
public class OrderAdjustmentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustmentType
	 * @return a List with the OrderAdjustmentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderAdjustmentType>> findOrderAdjustmentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentTypesBy query = new FindOrderAdjustmentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentType> orderAdjustmentTypes =((OrderAdjustmentTypeFound) Scheduler.execute(query).data()).getOrderAdjustmentTypes();

		return ResponseEntity.ok().body(orderAdjustmentTypes);

	}

	/**
	 * creates a new OrderAdjustmentType entry in the ofbiz database
	 * 
	 * @param orderAdjustmentTypeToBeAdded
	 *            the OrderAdjustmentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderAdjustmentType> createOrderAdjustmentType(@RequestBody OrderAdjustmentType orderAdjustmentTypeToBeAdded) throws Exception {

		AddOrderAdjustmentType command = new AddOrderAdjustmentType(orderAdjustmentTypeToBeAdded);
		OrderAdjustmentType orderAdjustmentType = ((OrderAdjustmentTypeAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentType();
		
		if (orderAdjustmentType != null) 
			return successful(orderAdjustmentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderAdjustmentType with the specific Id
	 * 
	 * @param orderAdjustmentTypeToBeUpdated
	 *            the OrderAdjustmentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderAdjustmentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderAdjustmentType(@RequestBody OrderAdjustmentType orderAdjustmentTypeToBeUpdated,
			@PathVariable String orderAdjustmentTypeId) throws Exception {

		orderAdjustmentTypeToBeUpdated.setOrderAdjustmentTypeId(orderAdjustmentTypeId);

		UpdateOrderAdjustmentType command = new UpdateOrderAdjustmentType(orderAdjustmentTypeToBeUpdated);

		try {
			if(((OrderAdjustmentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderAdjustmentTypeId}")
	public ResponseEntity<OrderAdjustmentType> findById(@PathVariable String orderAdjustmentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentTypeId", orderAdjustmentTypeId);
		try {

			List<OrderAdjustmentType> foundOrderAdjustmentType = findOrderAdjustmentTypesBy(requestParams).getBody();
			if(foundOrderAdjustmentType.size()==1){				return successful(foundOrderAdjustmentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderAdjustmentTypeId}")
	public ResponseEntity<String> deleteOrderAdjustmentTypeByIdUpdated(@PathVariable String orderAdjustmentTypeId) throws Exception {
		DeleteOrderAdjustmentType command = new DeleteOrderAdjustmentType(orderAdjustmentTypeId);

		try {
			if (((OrderAdjustmentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
