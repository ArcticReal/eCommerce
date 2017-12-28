package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry;

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
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command.AddOrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command.DeleteOrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command.UpdateOrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryAdded;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryDeleted;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryFound;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryUpdated;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.mapper.OrderSummaryEntryMapper;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.query.FindOrderSummaryEntrysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderSummaryEntrys")
public class OrderSummaryEntryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderSummaryEntryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderSummaryEntry
	 * @return a List with the OrderSummaryEntrys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderSummaryEntry>> findOrderSummaryEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderSummaryEntrysBy query = new FindOrderSummaryEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderSummaryEntry> orderSummaryEntrys =((OrderSummaryEntryFound) Scheduler.execute(query).data()).getOrderSummaryEntrys();

		return ResponseEntity.ok().body(orderSummaryEntrys);

	}

	/**
	 * creates a new OrderSummaryEntry entry in the ofbiz database
	 * 
	 * @param orderSummaryEntryToBeAdded
	 *            the OrderSummaryEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderSummaryEntry> createOrderSummaryEntry(@RequestBody OrderSummaryEntry orderSummaryEntryToBeAdded) throws Exception {

		AddOrderSummaryEntry command = new AddOrderSummaryEntry(orderSummaryEntryToBeAdded);
		OrderSummaryEntry orderSummaryEntry = ((OrderSummaryEntryAdded) Scheduler.execute(command).data()).getAddedOrderSummaryEntry();
		
		if (orderSummaryEntry != null) 
			return successful(orderSummaryEntry);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderSummaryEntry with the specific Id
	 * 
	 * @param orderSummaryEntryToBeUpdated
	 *            the OrderSummaryEntry thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderSummaryEntry(@RequestBody OrderSummaryEntry orderSummaryEntryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderSummaryEntryToBeUpdated.setnull(null);

		UpdateOrderSummaryEntry command = new UpdateOrderSummaryEntry(orderSummaryEntryToBeUpdated);

		try {
			if(((OrderSummaryEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderSummaryEntryId}")
	public ResponseEntity<OrderSummaryEntry> findById(@PathVariable String orderSummaryEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderSummaryEntryId", orderSummaryEntryId);
		try {

			List<OrderSummaryEntry> foundOrderSummaryEntry = findOrderSummaryEntrysBy(requestParams).getBody();
			if(foundOrderSummaryEntry.size()==1){				return successful(foundOrderSummaryEntry.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderSummaryEntryId}")
	public ResponseEntity<String> deleteOrderSummaryEntryByIdUpdated(@PathVariable String orderSummaryEntryId) throws Exception {
		DeleteOrderSummaryEntry command = new DeleteOrderSummaryEntry(orderSummaryEntryId);

		try {
			if (((OrderSummaryEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
