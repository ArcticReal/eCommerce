package com.skytala.eCommerce.domain.order.relations.orderAdjustment.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.attribute.AddOrderAdjustmentAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.attribute.DeleteOrderAdjustmentAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.attribute.UpdateOrderAdjustmentAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.attribute.OrderAdjustmentAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.attribute.FindOrderAdjustmentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderAdjustment/orderAdjustmentAttributes")
public class OrderAdjustmentAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustmentAttribute
	 * @return a List with the OrderAdjustmentAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderAdjustmentAttribute>> findOrderAdjustmentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentAttributesBy query = new FindOrderAdjustmentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentAttribute> orderAdjustmentAttributes =((OrderAdjustmentAttributeFound) Scheduler.execute(query).data()).getOrderAdjustmentAttributes();

		return ResponseEntity.ok().body(orderAdjustmentAttributes);

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
	public ResponseEntity<OrderAdjustmentAttribute> createOrderAdjustmentAttribute(HttpServletRequest request) throws Exception {

		OrderAdjustmentAttribute orderAdjustmentAttributeToBeAdded = new OrderAdjustmentAttribute();
		try {
			orderAdjustmentAttributeToBeAdded = OrderAdjustmentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderAdjustmentAttribute(orderAdjustmentAttributeToBeAdded);

	}

	/**
	 * creates a new OrderAdjustmentAttribute entry in the ofbiz database
	 * 
	 * @param orderAdjustmentAttributeToBeAdded
	 *            the OrderAdjustmentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderAdjustmentAttribute> createOrderAdjustmentAttribute(@RequestBody OrderAdjustmentAttribute orderAdjustmentAttributeToBeAdded) throws Exception {

		AddOrderAdjustmentAttribute command = new AddOrderAdjustmentAttribute(orderAdjustmentAttributeToBeAdded);
		OrderAdjustmentAttribute orderAdjustmentAttribute = ((OrderAdjustmentAttributeAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentAttribute();
		
		if (orderAdjustmentAttribute != null) 
			return successful(orderAdjustmentAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderAdjustmentAttribute with the specific Id
	 * 
	 * @param orderAdjustmentAttributeToBeUpdated
	 *            the OrderAdjustmentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderAdjustmentAttribute(@RequestBody OrderAdjustmentAttribute orderAdjustmentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderAdjustmentAttributeToBeUpdated.setAttrName(attrName);

		UpdateOrderAdjustmentAttribute command = new UpdateOrderAdjustmentAttribute(orderAdjustmentAttributeToBeUpdated);

		try {
			if(((OrderAdjustmentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderAdjustmentAttributeId}")
	public ResponseEntity<OrderAdjustmentAttribute> findById(@PathVariable String orderAdjustmentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentAttributeId", orderAdjustmentAttributeId);
		try {

			List<OrderAdjustmentAttribute> foundOrderAdjustmentAttribute = findOrderAdjustmentAttributesBy(requestParams).getBody();
			if(foundOrderAdjustmentAttribute.size()==1){				return successful(foundOrderAdjustmentAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderAdjustmentAttributeId}")
	public ResponseEntity<String> deleteOrderAdjustmentAttributeByIdUpdated(@PathVariable String orderAdjustmentAttributeId) throws Exception {
		DeleteOrderAdjustmentAttribute command = new DeleteOrderAdjustmentAttribute(orderAdjustmentAttributeId);

		try {
			if (((OrderAdjustmentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
