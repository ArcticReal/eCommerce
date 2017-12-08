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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/orderPaymentPreferences")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderPaymentPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderPaymentPreferencesBy query = new FindOrderPaymentPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderPaymentPreference> orderPaymentPreferences =((OrderPaymentPreferenceFound) Scheduler.execute(query).data()).getOrderPaymentPreferences();

		if (orderPaymentPreferences.size() == 1) {
			return ResponseEntity.ok().body(orderPaymentPreferences.get(0));
		}

		return ResponseEntity.ok().body(orderPaymentPreferences);

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
	public ResponseEntity<Object> createOrderPaymentPreference(HttpServletRequest request) throws Exception {

		OrderPaymentPreference orderPaymentPreferenceToBeAdded = new OrderPaymentPreference();
		try {
			orderPaymentPreferenceToBeAdded = OrderPaymentPreferenceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderPaymentPreference(orderPaymentPreferenceToBeAdded);

	}

	/**
	 * creates a new OrderPaymentPreference entry in the ofbiz database
	 * 
	 * @param orderPaymentPreferenceToBeAdded
	 *            the OrderPaymentPreference thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderPaymentPreference(@RequestBody OrderPaymentPreference orderPaymentPreferenceToBeAdded) throws Exception {

		AddOrderPaymentPreference command = new AddOrderPaymentPreference(orderPaymentPreferenceToBeAdded);
		OrderPaymentPreference orderPaymentPreference = ((OrderPaymentPreferenceAdded) Scheduler.execute(command).data()).getAddedOrderPaymentPreference();
		
		if (orderPaymentPreference != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderPaymentPreference);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderPaymentPreference could not be created.");
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
	public boolean updateOrderPaymentPreference(HttpServletRequest request) throws Exception {

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

		OrderPaymentPreference orderPaymentPreferenceToBeUpdated = new OrderPaymentPreference();

		try {
			orderPaymentPreferenceToBeUpdated = OrderPaymentPreferenceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderPaymentPreference(orderPaymentPreferenceToBeUpdated, orderPaymentPreferenceToBeUpdated.getOrderPaymentPreferenceId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateOrderPaymentPreference(@RequestBody OrderPaymentPreference orderPaymentPreferenceToBeUpdated,
			@PathVariable String orderPaymentPreferenceId) throws Exception {

		orderPaymentPreferenceToBeUpdated.setOrderPaymentPreferenceId(orderPaymentPreferenceId);

		UpdateOrderPaymentPreference command = new UpdateOrderPaymentPreference(orderPaymentPreferenceToBeUpdated);

		try {
			if(((OrderPaymentPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderPaymentPreferenceId}")
	public ResponseEntity<Object> findById(@PathVariable String orderPaymentPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderPaymentPreferenceId", orderPaymentPreferenceId);
		try {

			Object foundOrderPaymentPreference = findOrderPaymentPreferencesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderPaymentPreference);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderPaymentPreferenceId}")
	public ResponseEntity<Object> deleteOrderPaymentPreferenceByIdUpdated(@PathVariable String orderPaymentPreferenceId) throws Exception {
		DeleteOrderPaymentPreference command = new DeleteOrderPaymentPreference(orderPaymentPreferenceId);

		try {
			if (((OrderPaymentPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderPaymentPreference could not be deleted");

	}

}
