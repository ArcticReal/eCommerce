package com.skytala.eCommerce.domain.order.relations.orderBlacklist;

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
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.command.AddOrderBlacklist;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.command.DeleteOrderBlacklist;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.command.UpdateOrderBlacklist;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.OrderBlacklistAdded;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.OrderBlacklistDeleted;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.OrderBlacklistFound;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.OrderBlacklistUpdated;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper.OrderBlacklistMapper;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.query.FindOrderBlacklistsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderBlacklists")
public class OrderBlacklistController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderBlacklistController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderBlacklist
	 * @return a List with the OrderBlacklists
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderBlacklist>> findOrderBlacklistsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderBlacklistsBy query = new FindOrderBlacklistsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderBlacklist> orderBlacklists =((OrderBlacklistFound) Scheduler.execute(query).data()).getOrderBlacklists();

		return ResponseEntity.ok().body(orderBlacklists);

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
	public ResponseEntity<OrderBlacklist> createOrderBlacklist(HttpServletRequest request) throws Exception {

		OrderBlacklist orderBlacklistToBeAdded = new OrderBlacklist();
		try {
			orderBlacklistToBeAdded = OrderBlacklistMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderBlacklist(orderBlacklistToBeAdded);

	}

	/**
	 * creates a new OrderBlacklist entry in the ofbiz database
	 * 
	 * @param orderBlacklistToBeAdded
	 *            the OrderBlacklist thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderBlacklist> createOrderBlacklist(@RequestBody OrderBlacklist orderBlacklistToBeAdded) throws Exception {

		AddOrderBlacklist command = new AddOrderBlacklist(orderBlacklistToBeAdded);
		OrderBlacklist orderBlacklist = ((OrderBlacklistAdded) Scheduler.execute(command).data()).getAddedOrderBlacklist();
		
		if (orderBlacklist != null) 
			return successful(orderBlacklist);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderBlacklist with the specific Id
	 * 
	 * @param orderBlacklistToBeUpdated
	 *            the OrderBlacklist thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{blacklistString}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderBlacklist(@RequestBody OrderBlacklist orderBlacklistToBeUpdated,
			@PathVariable String blacklistString) throws Exception {

		orderBlacklistToBeUpdated.setBlacklistString(blacklistString);

		UpdateOrderBlacklist command = new UpdateOrderBlacklist(orderBlacklistToBeUpdated);

		try {
			if(((OrderBlacklistUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderBlacklistId}")
	public ResponseEntity<OrderBlacklist> findById(@PathVariable String orderBlacklistId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderBlacklistId", orderBlacklistId);
		try {

			List<OrderBlacklist> foundOrderBlacklist = findOrderBlacklistsBy(requestParams).getBody();
			if(foundOrderBlacklist.size()==1){				return successful(foundOrderBlacklist.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderBlacklistId}")
	public ResponseEntity<String> deleteOrderBlacklistByIdUpdated(@PathVariable String orderBlacklistId) throws Exception {
		DeleteOrderBlacklist command = new DeleteOrderBlacklist(orderBlacklistId);

		try {
			if (((OrderBlacklistDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
