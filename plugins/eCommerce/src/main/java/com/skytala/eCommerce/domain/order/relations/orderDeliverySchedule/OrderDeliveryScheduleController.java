package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule;

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
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command.AddOrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command.DeleteOrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command.UpdateOrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleAdded;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleDeleted;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleFound;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleUpdated;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.mapper.OrderDeliveryScheduleMapper;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.query.FindOrderDeliverySchedulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderDeliverySchedules")
public class OrderDeliveryScheduleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderDeliveryScheduleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderDeliverySchedule
	 * @return a List with the OrderDeliverySchedules
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderDeliverySchedule>> findOrderDeliverySchedulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderDeliverySchedulesBy query = new FindOrderDeliverySchedulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderDeliverySchedule> orderDeliverySchedules =((OrderDeliveryScheduleFound) Scheduler.execute(query).data()).getOrderDeliverySchedules();

		return ResponseEntity.ok().body(orderDeliverySchedules);

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
	public ResponseEntity<OrderDeliverySchedule> createOrderDeliverySchedule(HttpServletRequest request) throws Exception {

		OrderDeliverySchedule orderDeliveryScheduleToBeAdded = new OrderDeliverySchedule();
		try {
			orderDeliveryScheduleToBeAdded = OrderDeliveryScheduleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderDeliverySchedule(orderDeliveryScheduleToBeAdded);

	}

	/**
	 * creates a new OrderDeliverySchedule entry in the ofbiz database
	 * 
	 * @param orderDeliveryScheduleToBeAdded
	 *            the OrderDeliverySchedule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderDeliverySchedule> createOrderDeliverySchedule(@RequestBody OrderDeliverySchedule orderDeliveryScheduleToBeAdded) throws Exception {

		AddOrderDeliverySchedule command = new AddOrderDeliverySchedule(orderDeliveryScheduleToBeAdded);
		OrderDeliverySchedule orderDeliverySchedule = ((OrderDeliveryScheduleAdded) Scheduler.execute(command).data()).getAddedOrderDeliverySchedule();
		
		if (orderDeliverySchedule != null) 
			return successful(orderDeliverySchedule);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderDeliverySchedule with the specific Id
	 * 
	 * @param orderDeliveryScheduleToBeUpdated
	 *            the OrderDeliverySchedule thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderDeliverySchedule(@RequestBody OrderDeliverySchedule orderDeliveryScheduleToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderDeliveryScheduleToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderDeliverySchedule command = new UpdateOrderDeliverySchedule(orderDeliveryScheduleToBeUpdated);

		try {
			if(((OrderDeliveryScheduleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderDeliveryScheduleId}")
	public ResponseEntity<OrderDeliverySchedule> findById(@PathVariable String orderDeliveryScheduleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderDeliveryScheduleId", orderDeliveryScheduleId);
		try {

			List<OrderDeliverySchedule> foundOrderDeliverySchedule = findOrderDeliverySchedulesBy(requestParams).getBody();
			if(foundOrderDeliverySchedule.size()==1){				return successful(foundOrderDeliverySchedule.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderDeliveryScheduleId}")
	public ResponseEntity<String> deleteOrderDeliveryScheduleByIdUpdated(@PathVariable String orderDeliveryScheduleId) throws Exception {
		DeleteOrderDeliverySchedule command = new DeleteOrderDeliverySchedule(orderDeliveryScheduleId);

		try {
			if (((OrderDeliveryScheduleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
