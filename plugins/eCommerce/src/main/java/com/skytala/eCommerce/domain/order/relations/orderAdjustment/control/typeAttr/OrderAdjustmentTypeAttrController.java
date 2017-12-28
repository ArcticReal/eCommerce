package com.skytala.eCommerce.domain.order.relations.orderAdjustment.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.typeAttr.AddOrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.typeAttr.DeleteOrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.typeAttr.UpdateOrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr.OrderAdjustmentTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr.OrderAdjustmentTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr.OrderAdjustmentTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr.OrderAdjustmentTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.typeAttr.OrderAdjustmentTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.typeAttr.OrderAdjustmentTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.typeAttr.FindOrderAdjustmentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderAdjustment/orderAdjustmentTypeAttrs")
public class OrderAdjustmentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustmentTypeAttr
	 * @return a List with the OrderAdjustmentTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderAdjustmentTypeAttr>> findOrderAdjustmentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentTypeAttrsBy query = new FindOrderAdjustmentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentTypeAttr> orderAdjustmentTypeAttrs =((OrderAdjustmentTypeAttrFound) Scheduler.execute(query).data()).getOrderAdjustmentTypeAttrs();

		return ResponseEntity.ok().body(orderAdjustmentTypeAttrs);

	}

	/**
	 * creates a new OrderAdjustmentTypeAttr entry in the ofbiz database
	 * 
	 * @param orderAdjustmentTypeAttrToBeAdded
	 *            the OrderAdjustmentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderAdjustmentTypeAttr> createOrderAdjustmentTypeAttr(@RequestBody OrderAdjustmentTypeAttr orderAdjustmentTypeAttrToBeAdded) throws Exception {

		AddOrderAdjustmentTypeAttr command = new AddOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrToBeAdded);
		OrderAdjustmentTypeAttr orderAdjustmentTypeAttr = ((OrderAdjustmentTypeAttrAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentTypeAttr();
		
		if (orderAdjustmentTypeAttr != null) 
			return successful(orderAdjustmentTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderAdjustmentTypeAttr with the specific Id
	 * 
	 * @param orderAdjustmentTypeAttrToBeUpdated
	 *            the OrderAdjustmentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderAdjustmentTypeAttr(@RequestBody OrderAdjustmentTypeAttr orderAdjustmentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderAdjustmentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateOrderAdjustmentTypeAttr command = new UpdateOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrToBeUpdated);

		try {
			if(((OrderAdjustmentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderAdjustmentTypeAttrId}")
	public ResponseEntity<OrderAdjustmentTypeAttr> findById(@PathVariable String orderAdjustmentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentTypeAttrId", orderAdjustmentTypeAttrId);
		try {

			List<OrderAdjustmentTypeAttr> foundOrderAdjustmentTypeAttr = findOrderAdjustmentTypeAttrsBy(requestParams).getBody();
			if(foundOrderAdjustmentTypeAttr.size()==1){				return successful(foundOrderAdjustmentTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderAdjustmentTypeAttrId}")
	public ResponseEntity<String> deleteOrderAdjustmentTypeAttrByIdUpdated(@PathVariable String orderAdjustmentTypeAttrId) throws Exception {
		DeleteOrderAdjustmentTypeAttr command = new DeleteOrderAdjustmentTypeAttr(orderAdjustmentTypeAttrId);

		try {
			if (((OrderAdjustmentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
