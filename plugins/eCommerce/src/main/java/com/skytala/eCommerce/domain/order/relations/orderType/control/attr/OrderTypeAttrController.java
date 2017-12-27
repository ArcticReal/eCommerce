package com.skytala.eCommerce.domain.order.relations.orderType.control.attr;

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
import com.skytala.eCommerce.domain.order.relations.orderType.command.attr.AddOrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.command.attr.DeleteOrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.command.attr.UpdateOrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.orderType.mapper.attr.OrderTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.query.attr.FindOrderTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderType/orderTypeAttrs")
public class OrderTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderTypeAttr
	 * @return a List with the OrderTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderTypeAttr>> findOrderTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTypeAttrsBy query = new FindOrderTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderTypeAttr> orderTypeAttrs =((OrderTypeAttrFound) Scheduler.execute(query).data()).getOrderTypeAttrs();

		return ResponseEntity.ok().body(orderTypeAttrs);

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
	public ResponseEntity<OrderTypeAttr> createOrderTypeAttr(HttpServletRequest request) throws Exception {

		OrderTypeAttr orderTypeAttrToBeAdded = new OrderTypeAttr();
		try {
			orderTypeAttrToBeAdded = OrderTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderTypeAttr(orderTypeAttrToBeAdded);

	}

	/**
	 * creates a new OrderTypeAttr entry in the ofbiz database
	 * 
	 * @param orderTypeAttrToBeAdded
	 *            the OrderTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderTypeAttr> createOrderTypeAttr(@RequestBody OrderTypeAttr orderTypeAttrToBeAdded) throws Exception {

		AddOrderTypeAttr command = new AddOrderTypeAttr(orderTypeAttrToBeAdded);
		OrderTypeAttr orderTypeAttr = ((OrderTypeAttrAdded) Scheduler.execute(command).data()).getAddedOrderTypeAttr();
		
		if (orderTypeAttr != null) 
			return successful(orderTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderTypeAttr with the specific Id
	 * 
	 * @param orderTypeAttrToBeUpdated
	 *            the OrderTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderTypeAttr(@RequestBody OrderTypeAttr orderTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateOrderTypeAttr command = new UpdateOrderTypeAttr(orderTypeAttrToBeUpdated);

		try {
			if(((OrderTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderTypeAttrId}")
	public ResponseEntity<OrderTypeAttr> findById(@PathVariable String orderTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTypeAttrId", orderTypeAttrId);
		try {

			List<OrderTypeAttr> foundOrderTypeAttr = findOrderTypeAttrsBy(requestParams).getBody();
			if(foundOrderTypeAttr.size()==1){				return successful(foundOrderTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderTypeAttrId}")
	public ResponseEntity<String> deleteOrderTypeAttrByIdUpdated(@PathVariable String orderTypeAttrId) throws Exception {
		DeleteOrderTypeAttr command = new DeleteOrderTypeAttr(orderTypeAttrId);

		try {
			if (((OrderTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
