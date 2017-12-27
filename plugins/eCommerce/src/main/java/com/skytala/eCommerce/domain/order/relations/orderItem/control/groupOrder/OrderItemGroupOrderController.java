package com.skytala.eCommerce.domain.order.relations.orderItem.control.groupOrder;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.groupOrder.AddOrderItemGroupOrder;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.groupOrder.DeleteOrderItemGroupOrder;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.groupOrder.UpdateOrderItemGroupOrder;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.groupOrder.OrderItemGroupOrderAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.groupOrder.OrderItemGroupOrderDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.groupOrder.OrderItemGroupOrderFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.groupOrder.OrderItemGroupOrderUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.groupOrder.OrderItemGroupOrderMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.groupOrder.OrderItemGroupOrder;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.groupOrder.FindOrderItemGroupOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemGroupOrders")
public class OrderItemGroupOrderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemGroupOrderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemGroupOrder
	 * @return a List with the OrderItemGroupOrders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemGroupOrder>> findOrderItemGroupOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemGroupOrdersBy query = new FindOrderItemGroupOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemGroupOrder> orderItemGroupOrders =((OrderItemGroupOrderFound) Scheduler.execute(query).data()).getOrderItemGroupOrders();

		return ResponseEntity.ok().body(orderItemGroupOrders);

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
	public ResponseEntity<OrderItemGroupOrder> createOrderItemGroupOrder(HttpServletRequest request) throws Exception {

		OrderItemGroupOrder orderItemGroupOrderToBeAdded = new OrderItemGroupOrder();
		try {
			orderItemGroupOrderToBeAdded = OrderItemGroupOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderItemGroupOrder(orderItemGroupOrderToBeAdded);

	}

	/**
	 * creates a new OrderItemGroupOrder entry in the ofbiz database
	 * 
	 * @param orderItemGroupOrderToBeAdded
	 *            the OrderItemGroupOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemGroupOrder> createOrderItemGroupOrder(@RequestBody OrderItemGroupOrder orderItemGroupOrderToBeAdded) throws Exception {

		AddOrderItemGroupOrder command = new AddOrderItemGroupOrder(orderItemGroupOrderToBeAdded);
		OrderItemGroupOrder orderItemGroupOrder = ((OrderItemGroupOrderAdded) Scheduler.execute(command).data()).getAddedOrderItemGroupOrder();
		
		if (orderItemGroupOrder != null) 
			return successful(orderItemGroupOrder);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemGroupOrder with the specific Id
	 * 
	 * @param orderItemGroupOrderToBeUpdated
	 *            the OrderItemGroupOrder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemGroupOrder(@RequestBody OrderItemGroupOrder orderItemGroupOrderToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemGroupOrderToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItemGroupOrder command = new UpdateOrderItemGroupOrder(orderItemGroupOrderToBeUpdated);

		try {
			if(((OrderItemGroupOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemGroupOrderId}")
	public ResponseEntity<OrderItemGroupOrder> findById(@PathVariable String orderItemGroupOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemGroupOrderId", orderItemGroupOrderId);
		try {

			List<OrderItemGroupOrder> foundOrderItemGroupOrder = findOrderItemGroupOrdersBy(requestParams).getBody();
			if(foundOrderItemGroupOrder.size()==1){				return successful(foundOrderItemGroupOrder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemGroupOrderId}")
	public ResponseEntity<String> deleteOrderItemGroupOrderByIdUpdated(@PathVariable String orderItemGroupOrderId) throws Exception {
		DeleteOrderItemGroupOrder command = new DeleteOrderItemGroupOrder(orderItemGroupOrderId);

		try {
			if (((OrderItemGroupOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
