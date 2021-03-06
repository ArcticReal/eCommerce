package com.skytala.eCommerce.domain.order.relations.orderPaymentPreference;

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
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.command.AddOrderPaymentPreference;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.command.DeleteOrderPaymentPreference;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.command.UpdateOrderPaymentPreference;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event.OrderPaymentPreferenceAdded;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event.OrderPaymentPreferenceDeleted;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event.OrderPaymentPreferenceFound;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event.OrderPaymentPreferenceUpdated;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.mapper.OrderPaymentPreferenceMapper;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.model.OrderPaymentPreference;
import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.query.FindOrderPaymentPreferencesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderPaymentPreferences")
public class OrderPaymentPreferenceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderPaymentPreferenceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderPaymentPreference
	 * @return a List with the OrderPaymentPreferences
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderPaymentPreference>> findOrderPaymentPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderPaymentPreferencesBy query = new FindOrderPaymentPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderPaymentPreference> orderPaymentPreferences =((OrderPaymentPreferenceFound) Scheduler.execute(query).data()).getOrderPaymentPreferences();

		return ResponseEntity.ok().body(orderPaymentPreferences);

	}

	/**
	 * creates a new OrderPaymentPreference entry in the ofbiz database
	 * 
	 * @param orderPaymentPreferenceToBeAdded
	 *            the OrderPaymentPreference thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderPaymentPreference> createOrderPaymentPreference(@RequestBody OrderPaymentPreference orderPaymentPreferenceToBeAdded) throws Exception {

		AddOrderPaymentPreference command = new AddOrderPaymentPreference(orderPaymentPreferenceToBeAdded);
		OrderPaymentPreference orderPaymentPreference = ((OrderPaymentPreferenceAdded) Scheduler.execute(command).data()).getAddedOrderPaymentPreference();
		
		if (orderPaymentPreference != null) 
			return successful(orderPaymentPreference);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderPaymentPreference with the specific Id
	 * 
	 * @param orderPaymentPreferenceToBeUpdated
	 *            the OrderPaymentPreference thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderPaymentPreferenceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderPaymentPreference(@RequestBody OrderPaymentPreference orderPaymentPreferenceToBeUpdated,
			@PathVariable String orderPaymentPreferenceId) throws Exception {

		orderPaymentPreferenceToBeUpdated.setOrderPaymentPreferenceId(orderPaymentPreferenceId);

		UpdateOrderPaymentPreference command = new UpdateOrderPaymentPreference(orderPaymentPreferenceToBeUpdated);

		try {
			if(((OrderPaymentPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderPaymentPreferenceId}")
	public ResponseEntity<OrderPaymentPreference> findById(@PathVariable String orderPaymentPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderPaymentPreferenceId", orderPaymentPreferenceId);
		try {

			List<OrderPaymentPreference> foundOrderPaymentPreference = findOrderPaymentPreferencesBy(requestParams).getBody();
			if(foundOrderPaymentPreference.size()==1){				return successful(foundOrderPaymentPreference.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderPaymentPreferenceId}")
	public ResponseEntity<String> deleteOrderPaymentPreferenceByIdUpdated(@PathVariable String orderPaymentPreferenceId) throws Exception {
		DeleteOrderPaymentPreference command = new DeleteOrderPaymentPreference(orderPaymentPreferenceId);

		try {
			if (((OrderPaymentPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
