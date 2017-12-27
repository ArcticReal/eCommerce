package com.skytala.eCommerce.domain.order.relations.orderNotification;

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
import com.skytala.eCommerce.domain.order.relations.orderNotification.command.AddOrderNotification;
import com.skytala.eCommerce.domain.order.relations.orderNotification.command.DeleteOrderNotification;
import com.skytala.eCommerce.domain.order.relations.orderNotification.command.UpdateOrderNotification;
import com.skytala.eCommerce.domain.order.relations.orderNotification.event.OrderNotificationAdded;
import com.skytala.eCommerce.domain.order.relations.orderNotification.event.OrderNotificationDeleted;
import com.skytala.eCommerce.domain.order.relations.orderNotification.event.OrderNotificationFound;
import com.skytala.eCommerce.domain.order.relations.orderNotification.event.OrderNotificationUpdated;
import com.skytala.eCommerce.domain.order.relations.orderNotification.mapper.OrderNotificationMapper;
import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;
import com.skytala.eCommerce.domain.order.relations.orderNotification.query.FindOrderNotificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderNotifications")
public class OrderNotificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderNotificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderNotification
	 * @return a List with the OrderNotifications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderNotification>> findOrderNotificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderNotificationsBy query = new FindOrderNotificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderNotification> orderNotifications =((OrderNotificationFound) Scheduler.execute(query).data()).getOrderNotifications();

		return ResponseEntity.ok().body(orderNotifications);

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
	public ResponseEntity<OrderNotification> createOrderNotification(HttpServletRequest request) throws Exception {

		OrderNotification orderNotificationToBeAdded = new OrderNotification();
		try {
			orderNotificationToBeAdded = OrderNotificationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderNotification(orderNotificationToBeAdded);

	}

	/**
	 * creates a new OrderNotification entry in the ofbiz database
	 * 
	 * @param orderNotificationToBeAdded
	 *            the OrderNotification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderNotification> createOrderNotification(@RequestBody OrderNotification orderNotificationToBeAdded) throws Exception {

		AddOrderNotification command = new AddOrderNotification(orderNotificationToBeAdded);
		OrderNotification orderNotification = ((OrderNotificationAdded) Scheduler.execute(command).data()).getAddedOrderNotification();
		
		if (orderNotification != null) 
			return successful(orderNotification);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderNotification with the specific Id
	 * 
	 * @param orderNotificationToBeUpdated
	 *            the OrderNotification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderNotificationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderNotification(@RequestBody OrderNotification orderNotificationToBeUpdated,
			@PathVariable String orderNotificationId) throws Exception {

		orderNotificationToBeUpdated.setOrderNotificationId(orderNotificationId);

		UpdateOrderNotification command = new UpdateOrderNotification(orderNotificationToBeUpdated);

		try {
			if(((OrderNotificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderNotificationId}")
	public ResponseEntity<OrderNotification> findById(@PathVariable String orderNotificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderNotificationId", orderNotificationId);
		try {

			List<OrderNotification> foundOrderNotification = findOrderNotificationsBy(requestParams).getBody();
			if(foundOrderNotification.size()==1){				return successful(foundOrderNotification.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderNotificationId}")
	public ResponseEntity<String> deleteOrderNotificationByIdUpdated(@PathVariable String orderNotificationId) throws Exception {
		DeleteOrderNotification command = new DeleteOrderNotification(orderNotificationId);

		try {
			if (((OrderNotificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
