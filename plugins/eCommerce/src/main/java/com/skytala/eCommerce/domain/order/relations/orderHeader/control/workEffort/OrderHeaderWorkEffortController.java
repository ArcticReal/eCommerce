package com.skytala.eCommerce.domain.order.relations.orderHeader.control.workEffort;

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
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort.AddOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort.DeleteOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort.UpdateOrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.workEffort.OrderHeaderWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;
import com.skytala.eCommerce.domain.order.relations.orderHeader.query.workEffort.FindOrderHeaderWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderHeader/orderHeaderWorkEfforts")
public class OrderHeaderWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderHeaderWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderHeaderWorkEffort
	 * @return a List with the OrderHeaderWorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderHeaderWorkEffort>> findOrderHeaderWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderHeaderWorkEffortsBy query = new FindOrderHeaderWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderHeaderWorkEffort> orderHeaderWorkEfforts =((OrderHeaderWorkEffortFound) Scheduler.execute(query).data()).getOrderHeaderWorkEfforts();

		return ResponseEntity.ok().body(orderHeaderWorkEfforts);

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
	public ResponseEntity<OrderHeaderWorkEffort> createOrderHeaderWorkEffort(HttpServletRequest request) throws Exception {

		OrderHeaderWorkEffort orderHeaderWorkEffortToBeAdded = new OrderHeaderWorkEffort();
		try {
			orderHeaderWorkEffortToBeAdded = OrderHeaderWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderHeaderWorkEffort(orderHeaderWorkEffortToBeAdded);

	}

	/**
	 * creates a new OrderHeaderWorkEffort entry in the ofbiz database
	 * 
	 * @param orderHeaderWorkEffortToBeAdded
	 *            the OrderHeaderWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderHeaderWorkEffort> createOrderHeaderWorkEffort(@RequestBody OrderHeaderWorkEffort orderHeaderWorkEffortToBeAdded) throws Exception {

		AddOrderHeaderWorkEffort command = new AddOrderHeaderWorkEffort(orderHeaderWorkEffortToBeAdded);
		OrderHeaderWorkEffort orderHeaderWorkEffort = ((OrderHeaderWorkEffortAdded) Scheduler.execute(command).data()).getAddedOrderHeaderWorkEffort();
		
		if (orderHeaderWorkEffort != null) 
			return successful(orderHeaderWorkEffort);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderHeaderWorkEffort with the specific Id
	 * 
	 * @param orderHeaderWorkEffortToBeUpdated
	 *            the OrderHeaderWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderHeaderWorkEffort(@RequestBody OrderHeaderWorkEffort orderHeaderWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderHeaderWorkEffortToBeUpdated.setnull(null);

		UpdateOrderHeaderWorkEffort command = new UpdateOrderHeaderWorkEffort(orderHeaderWorkEffortToBeUpdated);

		try {
			if(((OrderHeaderWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderHeaderWorkEffortId}")
	public ResponseEntity<OrderHeaderWorkEffort> findById(@PathVariable String orderHeaderWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderHeaderWorkEffortId", orderHeaderWorkEffortId);
		try {

			List<OrderHeaderWorkEffort> foundOrderHeaderWorkEffort = findOrderHeaderWorkEffortsBy(requestParams).getBody();
			if(foundOrderHeaderWorkEffort.size()==1){				return successful(foundOrderHeaderWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderHeaderWorkEffortId}")
	public ResponseEntity<String> deleteOrderHeaderWorkEffortByIdUpdated(@PathVariable String orderHeaderWorkEffortId) throws Exception {
		DeleteOrderHeaderWorkEffort command = new DeleteOrderHeaderWorkEffort(orderHeaderWorkEffortId);

		try {
			if (((OrderHeaderWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
