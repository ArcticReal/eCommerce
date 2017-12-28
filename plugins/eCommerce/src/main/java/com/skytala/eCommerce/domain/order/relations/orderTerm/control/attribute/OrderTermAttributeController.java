package com.skytala.eCommerce.domain.order.relations.orderTerm.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.attribute.AddOrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.attribute.DeleteOrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.attribute.UpdateOrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.attribute.OrderTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.query.attribute.FindOrderTermAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderTerm/orderTermAttributes")
public class OrderTermAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTermAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderTermAttribute
	 * @return a List with the OrderTermAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderTermAttribute>> findOrderTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTermAttributesBy query = new FindOrderTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderTermAttribute> orderTermAttributes =((OrderTermAttributeFound) Scheduler.execute(query).data()).getOrderTermAttributes();

		return ResponseEntity.ok().body(orderTermAttributes);

	}

	/**
	 * creates a new OrderTermAttribute entry in the ofbiz database
	 * 
	 * @param orderTermAttributeToBeAdded
	 *            the OrderTermAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderTermAttribute> createOrderTermAttribute(@RequestBody OrderTermAttribute orderTermAttributeToBeAdded) throws Exception {

		AddOrderTermAttribute command = new AddOrderTermAttribute(orderTermAttributeToBeAdded);
		OrderTermAttribute orderTermAttribute = ((OrderTermAttributeAdded) Scheduler.execute(command).data()).getAddedOrderTermAttribute();
		
		if (orderTermAttribute != null) 
			return successful(orderTermAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderTermAttribute with the specific Id
	 * 
	 * @param orderTermAttributeToBeUpdated
	 *            the OrderTermAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderTermAttribute(@RequestBody OrderTermAttribute orderTermAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderTermAttributeToBeUpdated.setnull(null);

		UpdateOrderTermAttribute command = new UpdateOrderTermAttribute(orderTermAttributeToBeUpdated);

		try {
			if(((OrderTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderTermAttributeId}")
	public ResponseEntity<OrderTermAttribute> findById(@PathVariable String orderTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTermAttributeId", orderTermAttributeId);
		try {

			List<OrderTermAttribute> foundOrderTermAttribute = findOrderTermAttributesBy(requestParams).getBody();
			if(foundOrderTermAttribute.size()==1){				return successful(foundOrderTermAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderTermAttributeId}")
	public ResponseEntity<String> deleteOrderTermAttributeByIdUpdated(@PathVariable String orderTermAttributeId) throws Exception {
		DeleteOrderTermAttribute command = new DeleteOrderTermAttribute(orderTermAttributeId);

		try {
			if (((OrderTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
