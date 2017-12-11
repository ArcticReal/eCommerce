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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderItemGroupOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemGroupOrdersBy query = new FindOrderItemGroupOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemGroupOrder> orderItemGroupOrders =((OrderItemGroupOrderFound) Scheduler.execute(query).data()).getOrderItemGroupOrders();

		if (orderItemGroupOrders.size() == 1) {
			return ResponseEntity.ok().body(orderItemGroupOrders.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createOrderItemGroupOrder(HttpServletRequest request) throws Exception {

		OrderItemGroupOrder orderItemGroupOrderToBeAdded = new OrderItemGroupOrder();
		try {
			orderItemGroupOrderToBeAdded = OrderItemGroupOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createOrderItemGroupOrder(@RequestBody OrderItemGroupOrder orderItemGroupOrderToBeAdded) throws Exception {

		AddOrderItemGroupOrder command = new AddOrderItemGroupOrder(orderItemGroupOrderToBeAdded);
		OrderItemGroupOrder orderItemGroupOrder = ((OrderItemGroupOrderAdded) Scheduler.execute(command).data()).getAddedOrderItemGroupOrder();
		
		if (orderItemGroupOrder != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderItemGroupOrder);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderItemGroupOrder could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateOrderItemGroupOrder(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		OrderItemGroupOrder orderItemGroupOrderToBeUpdated = new OrderItemGroupOrder();

		try {
			orderItemGroupOrderToBeUpdated = OrderItemGroupOrderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderItemGroupOrder(orderItemGroupOrderToBeUpdated, orderItemGroupOrderToBeUpdated.getOrderItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateOrderItemGroupOrder(@RequestBody OrderItemGroupOrder orderItemGroupOrderToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderItemGroupOrderToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderItemGroupOrder command = new UpdateOrderItemGroupOrder(orderItemGroupOrderToBeUpdated);

		try {
			if(((OrderItemGroupOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderItemGroupOrderId}")
	public ResponseEntity<Object> findById(@PathVariable String orderItemGroupOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemGroupOrderId", orderItemGroupOrderId);
		try {

			Object foundOrderItemGroupOrder = findOrderItemGroupOrdersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderItemGroupOrder);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderItemGroupOrderId}")
	public ResponseEntity<Object> deleteOrderItemGroupOrderByIdUpdated(@PathVariable String orderItemGroupOrderId) throws Exception {
		DeleteOrderItemGroupOrder command = new DeleteOrderItemGroupOrder(orderItemGroupOrderId);

		try {
			if (((OrderItemGroupOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderItemGroupOrder could not be deleted");

	}

}
