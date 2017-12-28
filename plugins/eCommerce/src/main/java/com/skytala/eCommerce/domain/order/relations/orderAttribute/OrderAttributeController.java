package com.skytala.eCommerce.domain.order.relations.orderAttribute;

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
import com.skytala.eCommerce.domain.order.relations.orderAttribute.command.AddOrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.command.DeleteOrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.command.UpdateOrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.mapper.OrderAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.query.FindOrderAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderAttributes")
public class OrderAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAttribute
	 * @return a List with the OrderAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderAttribute>> findOrderAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAttributesBy query = new FindOrderAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAttribute> orderAttributes =((OrderAttributeFound) Scheduler.execute(query).data()).getOrderAttributes();

		return ResponseEntity.ok().body(orderAttributes);

	}

	/**
	 * creates a new OrderAttribute entry in the ofbiz database
	 * 
	 * @param orderAttributeToBeAdded
	 *            the OrderAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderAttribute> createOrderAttribute(@RequestBody OrderAttribute orderAttributeToBeAdded) throws Exception {

		AddOrderAttribute command = new AddOrderAttribute(orderAttributeToBeAdded);
		OrderAttribute orderAttribute = ((OrderAttributeAdded) Scheduler.execute(command).data()).getAddedOrderAttribute();
		
		if (orderAttribute != null) 
			return successful(orderAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderAttribute with the specific Id
	 * 
	 * @param orderAttributeToBeUpdated
	 *            the OrderAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderAttribute(@RequestBody OrderAttribute orderAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderAttributeToBeUpdated.setAttrName(attrName);

		UpdateOrderAttribute command = new UpdateOrderAttribute(orderAttributeToBeUpdated);

		try {
			if(((OrderAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderAttributeId}")
	public ResponseEntity<OrderAttribute> findById(@PathVariable String orderAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAttributeId", orderAttributeId);
		try {

			List<OrderAttribute> foundOrderAttribute = findOrderAttributesBy(requestParams).getBody();
			if(foundOrderAttribute.size()==1){				return successful(foundOrderAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderAttributeId}")
	public ResponseEntity<String> deleteOrderAttributeByIdUpdated(@PathVariable String orderAttributeId) throws Exception {
		DeleteOrderAttribute command = new DeleteOrderAttribute(orderAttributeId);

		try {
			if (((OrderAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
