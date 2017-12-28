package com.skytala.eCommerce.domain.order.relations.orderItem.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.attribute.AddOrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.attribute.DeleteOrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.attribute.UpdateOrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute.OrderItemAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute.OrderItemAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute.OrderItemAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute.OrderItemAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.attribute.OrderItemAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.attribute.OrderItemAttribute;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.attribute.FindOrderItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemAttributes")
public class OrderItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemAttribute
	 * @return a List with the OrderItemAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemAttribute>> findOrderItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemAttributesBy query = new FindOrderItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemAttribute> orderItemAttributes =((OrderItemAttributeFound) Scheduler.execute(query).data()).getOrderItemAttributes();

		return ResponseEntity.ok().body(orderItemAttributes);

	}

	/**
	 * creates a new OrderItemAttribute entry in the ofbiz database
	 * 
	 * @param orderItemAttributeToBeAdded
	 *            the OrderItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemAttribute> createOrderItemAttribute(@RequestBody OrderItemAttribute orderItemAttributeToBeAdded) throws Exception {

		AddOrderItemAttribute command = new AddOrderItemAttribute(orderItemAttributeToBeAdded);
		OrderItemAttribute orderItemAttribute = ((OrderItemAttributeAdded) Scheduler.execute(command).data()).getAddedOrderItemAttribute();
		
		if (orderItemAttribute != null) 
			return successful(orderItemAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemAttribute with the specific Id
	 * 
	 * @param orderItemAttributeToBeUpdated
	 *            the OrderItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemAttribute(@RequestBody OrderItemAttribute orderItemAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderItemAttributeToBeUpdated.setnull(null);

		UpdateOrderItemAttribute command = new UpdateOrderItemAttribute(orderItemAttributeToBeUpdated);

		try {
			if(((OrderItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemAttributeId}")
	public ResponseEntity<OrderItemAttribute> findById(@PathVariable String orderItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemAttributeId", orderItemAttributeId);
		try {

			List<OrderItemAttribute> foundOrderItemAttribute = findOrderItemAttributesBy(requestParams).getBody();
			if(foundOrderItemAttribute.size()==1){				return successful(foundOrderItemAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemAttributeId}")
	public ResponseEntity<String> deleteOrderItemAttributeByIdUpdated(@PathVariable String orderItemAttributeId) throws Exception {
		DeleteOrderItemAttribute command = new DeleteOrderItemAttribute(orderItemAttributeId);

		try {
			if (((OrderItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
