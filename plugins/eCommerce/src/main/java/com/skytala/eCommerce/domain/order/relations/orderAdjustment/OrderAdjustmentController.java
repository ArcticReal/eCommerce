package com.skytala.eCommerce.domain.order.relations.orderAdjustment;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.AddOrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.DeleteOrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.UpdateOrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.OrderAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.OrderAdjustment;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.FindOrderAdjustmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderAdjustments")
public class OrderAdjustmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustment
	 * @return a List with the OrderAdjustments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderAdjustment>> findOrderAdjustmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentsBy query = new FindOrderAdjustmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustment> orderAdjustments =((OrderAdjustmentFound) Scheduler.execute(query).data()).getOrderAdjustments();

		return ResponseEntity.ok().body(orderAdjustments);

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
	public ResponseEntity<OrderAdjustment> createOrderAdjustment(HttpServletRequest request) throws Exception {

		OrderAdjustment orderAdjustmentToBeAdded = new OrderAdjustment();
		try {
			orderAdjustmentToBeAdded = OrderAdjustmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderAdjustment(orderAdjustmentToBeAdded);

	}

	/**
	 * creates a new OrderAdjustment entry in the ofbiz database
	 * 
	 * @param orderAdjustmentToBeAdded
	 *            the OrderAdjustment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderAdjustment> createOrderAdjustment(@RequestBody OrderAdjustment orderAdjustmentToBeAdded) throws Exception {

		AddOrderAdjustment command = new AddOrderAdjustment(orderAdjustmentToBeAdded);
		OrderAdjustment orderAdjustment = ((OrderAdjustmentAdded) Scheduler.execute(command).data()).getAddedOrderAdjustment();
		
		if (orderAdjustment != null) 
			return successful(orderAdjustment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderAdjustment with the specific Id
	 * 
	 * @param orderAdjustmentToBeUpdated
	 *            the OrderAdjustment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderAdjustmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderAdjustment(@RequestBody OrderAdjustment orderAdjustmentToBeUpdated,
			@PathVariable String orderAdjustmentId) throws Exception {

		orderAdjustmentToBeUpdated.setOrderAdjustmentId(orderAdjustmentId);

		UpdateOrderAdjustment command = new UpdateOrderAdjustment(orderAdjustmentToBeUpdated);

		try {
			if(((OrderAdjustmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderAdjustmentId}")
	public ResponseEntity<OrderAdjustment> findById(@PathVariable String orderAdjustmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentId", orderAdjustmentId);
		try {

			List<OrderAdjustment> foundOrderAdjustment = findOrderAdjustmentsBy(requestParams).getBody();
			if(foundOrderAdjustment.size()==1){				return successful(foundOrderAdjustment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderAdjustmentId}")
	public ResponseEntity<String> deleteOrderAdjustmentByIdUpdated(@PathVariable String orderAdjustmentId) throws Exception {
		DeleteOrderAdjustment command = new DeleteOrderAdjustment(orderAdjustmentId);

		try {
			if (((OrderAdjustmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
