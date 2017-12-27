package com.skytala.eCommerce.domain.order.relations.orderType;

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
import com.skytala.eCommerce.domain.order.relations.orderType.command.AddOrderType;
import com.skytala.eCommerce.domain.order.relations.orderType.command.DeleteOrderType;
import com.skytala.eCommerce.domain.order.relations.orderType.command.UpdateOrderType;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderType.mapper.OrderTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderType.model.OrderType;
import com.skytala.eCommerce.domain.order.relations.orderType.query.FindOrderTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderTypes")
public class OrderTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderType
	 * @return a List with the OrderTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderType>> findOrderTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTypesBy query = new FindOrderTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderType> orderTypes =((OrderTypeFound) Scheduler.execute(query).data()).getOrderTypes();

		return ResponseEntity.ok().body(orderTypes);

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
	public ResponseEntity<OrderType> createOrderType(HttpServletRequest request) throws Exception {

		OrderType orderTypeToBeAdded = new OrderType();
		try {
			orderTypeToBeAdded = OrderTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderType(orderTypeToBeAdded);

	}

	/**
	 * creates a new OrderType entry in the ofbiz database
	 * 
	 * @param orderTypeToBeAdded
	 *            the OrderType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderType> createOrderType(@RequestBody OrderType orderTypeToBeAdded) throws Exception {

		AddOrderType command = new AddOrderType(orderTypeToBeAdded);
		OrderType orderType = ((OrderTypeAdded) Scheduler.execute(command).data()).getAddedOrderType();
		
		if (orderType != null) 
			return successful(orderType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderType with the specific Id
	 * 
	 * @param orderTypeToBeUpdated
	 *            the OrderType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderType(@RequestBody OrderType orderTypeToBeUpdated,
			@PathVariable String orderTypeId) throws Exception {

		orderTypeToBeUpdated.setOrderTypeId(orderTypeId);

		UpdateOrderType command = new UpdateOrderType(orderTypeToBeUpdated);

		try {
			if(((OrderTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderTypeId}")
	public ResponseEntity<OrderType> findById(@PathVariable String orderTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTypeId", orderTypeId);
		try {

			List<OrderType> foundOrderType = findOrderTypesBy(requestParams).getBody();
			if(foundOrderType.size()==1){				return successful(foundOrderType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderTypeId}")
	public ResponseEntity<String> deleteOrderTypeByIdUpdated(@PathVariable String orderTypeId) throws Exception {
		DeleteOrderType command = new DeleteOrderType(orderTypeId);

		try {
			if (((OrderTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
