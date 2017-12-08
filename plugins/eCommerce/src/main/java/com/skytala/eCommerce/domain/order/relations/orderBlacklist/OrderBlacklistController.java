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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/orderBlacklists")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderBlacklistsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderBlacklistsBy query = new FindOrderBlacklistsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderBlacklist> orderBlacklists =((OrderBlacklistFound) Scheduler.execute(query).data()).getOrderBlacklists();

		if (orderBlacklists.size() == 1) {
			return ResponseEntity.ok().body(orderBlacklists.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createOrderBlacklist(HttpServletRequest request) throws Exception {

		OrderBlacklist orderBlacklistToBeAdded = new OrderBlacklist();
		try {
			orderBlacklistToBeAdded = OrderBlacklistMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createOrderBlacklist(@RequestBody OrderBlacklist orderBlacklistToBeAdded) throws Exception {

		AddOrderBlacklist command = new AddOrderBlacklist(orderBlacklistToBeAdded);
		OrderBlacklist orderBlacklist = ((OrderBlacklistAdded) Scheduler.execute(command).data()).getAddedOrderBlacklist();
		
		if (orderBlacklist != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderBlacklist);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderBlacklist could not be created.");
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
	public boolean updateOrderBlacklist(HttpServletRequest request) throws Exception {

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

		OrderBlacklist orderBlacklistToBeUpdated = new OrderBlacklist();

		try {
			orderBlacklistToBeUpdated = OrderBlacklistMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderBlacklist(orderBlacklistToBeUpdated, orderBlacklistToBeUpdated.getBlacklistString()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateOrderBlacklist(@RequestBody OrderBlacklist orderBlacklistToBeUpdated,
			@PathVariable String blacklistString) throws Exception {

		orderBlacklistToBeUpdated.setBlacklistString(blacklistString);

		UpdateOrderBlacklist command = new UpdateOrderBlacklist(orderBlacklistToBeUpdated);

		try {
			if(((OrderBlacklistUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderBlacklistId}")
	public ResponseEntity<Object> findById(@PathVariable String orderBlacklistId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderBlacklistId", orderBlacklistId);
		try {

			Object foundOrderBlacklist = findOrderBlacklistsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderBlacklist);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderBlacklistId}")
	public ResponseEntity<Object> deleteOrderBlacklistByIdUpdated(@PathVariable String orderBlacklistId) throws Exception {
		DeleteOrderBlacklist command = new DeleteOrderBlacklist(orderBlacklistId);

		try {
			if (((OrderBlacklistDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderBlacklist could not be deleted");

	}

}
