package com.skytala.eCommerce.domain.order.relations.orderAttribute;

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
import com.skytala.eCommerce.domain.order.relations.orderAttribute.command.AddOrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.command.DeleteOrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.command.UpdateOrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.mapper.OrderAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.query.FindOrderAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderAttributes")
public class OrderAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAttribute
	 * @return a List with the OrderAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAttributesBy query = new FindOrderAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAttribute> orderAttributes =((OrderAttributeFound) Scheduler.execute(query).data()).getOrderAttributes();

		if (orderAttributes.size() == 1) {
			return ResponseEntity.ok().body(orderAttributes.get(0));
		}

		return ResponseEntity.ok().body(orderAttributes);

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
	public ResponseEntity<Object> createOrderAttribute(HttpServletRequest request) throws Exception {

		OrderAttribute orderAttributeToBeAdded = new OrderAttribute();
		try {
			orderAttributeToBeAdded = OrderAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderAttribute(orderAttributeToBeAdded);

	}

	/**
	 * creates a new OrderAttribute entry in the ofbiz database
	 * 
	 * @param orderAttributeToBeAdded
	 *            the OrderAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderAttribute(@RequestBody OrderAttribute orderAttributeToBeAdded) throws Exception {

		AddOrderAttribute command = new AddOrderAttribute(orderAttributeToBeAdded);
		OrderAttribute orderAttribute = ((OrderAttributeAdded) Scheduler.execute(command).data()).getAddedOrderAttribute();
		
		if (orderAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderAttribute could not be created.");
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
	public boolean updateOrderAttribute(HttpServletRequest request) throws Exception {

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

		OrderAttribute orderAttributeToBeUpdated = new OrderAttribute();

		try {
			orderAttributeToBeUpdated = OrderAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderAttribute(orderAttributeToBeUpdated, orderAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderAttribute with the specific Id
	 * 
	 * @param orderAttributeToBeUpdated
	 *            the OrderAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderAttribute(@RequestBody OrderAttribute orderAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderAttributeToBeUpdated.setAttrName(attrName);

		UpdateOrderAttribute command = new UpdateOrderAttribute(orderAttributeToBeUpdated);

		try {
			if(((OrderAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAttributeId", orderAttributeId);
		try {

			Object foundOrderAttribute = findOrderAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderAttributeId}")
	public ResponseEntity<Object> deleteOrderAttributeByIdUpdated(@PathVariable String orderAttributeId) throws Exception {
		DeleteOrderAttribute command = new DeleteOrderAttribute(orderAttributeId);

		try {
			if (((OrderAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderAttribute could not be deleted");

	}

}
