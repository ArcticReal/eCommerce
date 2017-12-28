package com.skytala.eCommerce.domain.order.relations.orderItem.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr.AddOrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr.DeleteOrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.typeAttr.UpdateOrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.typeAttr.OrderItemTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.typeAttr.OrderItemTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.typeAttr.FindOrderItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemTypeAttrs")
public class OrderItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemTypeAttr
	 * @return a List with the OrderItemTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemTypeAttr>> findOrderItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemTypeAttrsBy query = new FindOrderItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemTypeAttr> orderItemTypeAttrs =((OrderItemTypeAttrFound) Scheduler.execute(query).data()).getOrderItemTypeAttrs();

		return ResponseEntity.ok().body(orderItemTypeAttrs);

	}

	/**
	 * creates a new OrderItemTypeAttr entry in the ofbiz database
	 * 
	 * @param orderItemTypeAttrToBeAdded
	 *            the OrderItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemTypeAttr> createOrderItemTypeAttr(@RequestBody OrderItemTypeAttr orderItemTypeAttrToBeAdded) throws Exception {

		AddOrderItemTypeAttr command = new AddOrderItemTypeAttr(orderItemTypeAttrToBeAdded);
		OrderItemTypeAttr orderItemTypeAttr = ((OrderItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedOrderItemTypeAttr();
		
		if (orderItemTypeAttr != null) 
			return successful(orderItemTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemTypeAttr with the specific Id
	 * 
	 * @param orderItemTypeAttrToBeUpdated
	 *            the OrderItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemTypeAttr(@RequestBody OrderItemTypeAttr orderItemTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderItemTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateOrderItemTypeAttr command = new UpdateOrderItemTypeAttr(orderItemTypeAttrToBeUpdated);

		try {
			if(((OrderItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemTypeAttrId}")
	public ResponseEntity<OrderItemTypeAttr> findById(@PathVariable String orderItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemTypeAttrId", orderItemTypeAttrId);
		try {

			List<OrderItemTypeAttr> foundOrderItemTypeAttr = findOrderItemTypeAttrsBy(requestParams).getBody();
			if(foundOrderItemTypeAttr.size()==1){				return successful(foundOrderItemTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemTypeAttrId}")
	public ResponseEntity<String> deleteOrderItemTypeAttrByIdUpdated(@PathVariable String orderItemTypeAttrId) throws Exception {
		DeleteOrderItemTypeAttr command = new DeleteOrderItemTypeAttr(orderItemTypeAttrId);

		try {
			if (((OrderItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
