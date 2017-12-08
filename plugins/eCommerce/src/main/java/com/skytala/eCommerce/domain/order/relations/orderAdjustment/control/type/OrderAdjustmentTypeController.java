package com.skytala.eCommerce.domain.order.relations.orderAdjustment.control.type;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type.AddOrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type.DeleteOrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.type.UpdateOrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.type.OrderAdjustmentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.type.OrderAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.type.OrderAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.type.FindOrderAdjustmentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/orderAdjustmentTypes")
public class OrderAdjustmentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustmentType
	 * @return a List with the OrderAdjustmentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderAdjustmentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentTypesBy query = new FindOrderAdjustmentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentType> orderAdjustmentTypes =((OrderAdjustmentTypeFound) Scheduler.execute(query).data()).getOrderAdjustmentTypes();

		if (orderAdjustmentTypes.size() == 1) {
			return ResponseEntity.ok().body(orderAdjustmentTypes.get(0));
		}

		return ResponseEntity.ok().body(orderAdjustmentTypes);

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
	public ResponseEntity<Object> createOrderAdjustmentType(HttpServletRequest request) throws Exception {

		OrderAdjustmentType orderAdjustmentTypeToBeAdded = new OrderAdjustmentType();
		try {
			orderAdjustmentTypeToBeAdded = OrderAdjustmentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderAdjustmentType(orderAdjustmentTypeToBeAdded);

	}

	/**
	 * creates a new OrderAdjustmentType entry in the ofbiz database
	 * 
	 * @param orderAdjustmentTypeToBeAdded
	 *            the OrderAdjustmentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderAdjustmentType(@RequestBody OrderAdjustmentType orderAdjustmentTypeToBeAdded) throws Exception {

		AddOrderAdjustmentType command = new AddOrderAdjustmentType(orderAdjustmentTypeToBeAdded);
		OrderAdjustmentType orderAdjustmentType = ((OrderAdjustmentTypeAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentType();
		
		if (orderAdjustmentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderAdjustmentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderAdjustmentType could not be created.");
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
	public boolean updateOrderAdjustmentType(HttpServletRequest request) throws Exception {

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

		OrderAdjustmentType orderAdjustmentTypeToBeUpdated = new OrderAdjustmentType();

		try {
			orderAdjustmentTypeToBeUpdated = OrderAdjustmentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderAdjustmentType(orderAdjustmentTypeToBeUpdated, orderAdjustmentTypeToBeUpdated.getOrderAdjustmentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderAdjustmentType with the specific Id
	 * 
	 * @param orderAdjustmentTypeToBeUpdated
	 *            the OrderAdjustmentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderAdjustmentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderAdjustmentType(@RequestBody OrderAdjustmentType orderAdjustmentTypeToBeUpdated,
			@PathVariable String orderAdjustmentTypeId) throws Exception {

		orderAdjustmentTypeToBeUpdated.setOrderAdjustmentTypeId(orderAdjustmentTypeId);

		UpdateOrderAdjustmentType command = new UpdateOrderAdjustmentType(orderAdjustmentTypeToBeUpdated);

		try {
			if(((OrderAdjustmentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderAdjustmentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderAdjustmentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentTypeId", orderAdjustmentTypeId);
		try {

			Object foundOrderAdjustmentType = findOrderAdjustmentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderAdjustmentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderAdjustmentTypeId}")
	public ResponseEntity<Object> deleteOrderAdjustmentTypeByIdUpdated(@PathVariable String orderAdjustmentTypeId) throws Exception {
		DeleteOrderAdjustmentType command = new DeleteOrderAdjustmentType(orderAdjustmentTypeId);

		try {
			if (((OrderAdjustmentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderAdjustmentType could not be deleted");

	}

}
