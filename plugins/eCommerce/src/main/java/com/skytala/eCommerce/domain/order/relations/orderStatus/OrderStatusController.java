package com.skytala.eCommerce.domain.order.relations.orderStatus;

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
import com.skytala.eCommerce.domain.order.relations.orderStatus.command.AddOrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.command.DeleteOrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.command.UpdateOrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusAdded;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusFound;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.orderStatus.mapper.OrderStatusMapper;
import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;
import com.skytala.eCommerce.domain.order.relations.orderStatus.query.FindOrderStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderStatuss")
public class OrderStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderStatus
	 * @return a List with the OrderStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderStatus>> findOrderStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderStatussBy query = new FindOrderStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderStatus> orderStatuss =((OrderStatusFound) Scheduler.execute(query).data()).getOrderStatuss();

		return ResponseEntity.ok().body(orderStatuss);

	}

	/**
	 * creates a new OrderStatus entry in the ofbiz database
	 * 
	 * @param orderStatusToBeAdded
	 *            the OrderStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderStatus> createOrderStatus(@RequestBody OrderStatus orderStatusToBeAdded) throws Exception {

		AddOrderStatus command = new AddOrderStatus(orderStatusToBeAdded);
		OrderStatus orderStatus = ((OrderStatusAdded) Scheduler.execute(command).data()).getAddedOrderStatus();
		
		if (orderStatus != null) 
			return successful(orderStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderStatus with the specific Id
	 * 
	 * @param orderStatusToBeUpdated
	 *            the OrderStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderStatusId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderStatus(@RequestBody OrderStatus orderStatusToBeUpdated,
			@PathVariable String orderStatusId) throws Exception {

		orderStatusToBeUpdated.setOrderStatusId(orderStatusId);

		UpdateOrderStatus command = new UpdateOrderStatus(orderStatusToBeUpdated);

		try {
			if(((OrderStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderStatusId}")
	public ResponseEntity<OrderStatus> findById(@PathVariable String orderStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderStatusId", orderStatusId);
		try {

			List<OrderStatus> foundOrderStatus = findOrderStatussBy(requestParams).getBody();
			if(foundOrderStatus.size()==1){				return successful(foundOrderStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderStatusId}")
	public ResponseEntity<String> deleteOrderStatusByIdUpdated(@PathVariable String orderStatusId) throws Exception {
		DeleteOrderStatus command = new DeleteOrderStatus(orderStatusId);

		try {
			if (((OrderStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
