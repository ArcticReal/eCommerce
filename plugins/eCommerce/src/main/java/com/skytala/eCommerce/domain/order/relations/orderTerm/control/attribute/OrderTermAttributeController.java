package com.skytala.eCommerce.domain.order.relations.orderTerm.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.attribute.AddOrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.attribute.DeleteOrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.command.attribute.UpdateOrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.attribute.OrderTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;
import com.skytala.eCommerce.domain.order.relations.orderTerm.query.attribute.FindOrderTermAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderTerm/orderTermAttributes")
public class OrderTermAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTermAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderTermAttribute
	 * @return a List with the OrderTermAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTermAttributesBy query = new FindOrderTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderTermAttribute> orderTermAttributes =((OrderTermAttributeFound) Scheduler.execute(query).data()).getOrderTermAttributes();

		if (orderTermAttributes.size() == 1) {
			return ResponseEntity.ok().body(orderTermAttributes.get(0));
		}

		return ResponseEntity.ok().body(orderTermAttributes);

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
	public ResponseEntity<Object> createOrderTermAttribute(HttpServletRequest request) throws Exception {

		OrderTermAttribute orderTermAttributeToBeAdded = new OrderTermAttribute();
		try {
			orderTermAttributeToBeAdded = OrderTermAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderTermAttribute(orderTermAttributeToBeAdded);

	}

	/**
	 * creates a new OrderTermAttribute entry in the ofbiz database
	 * 
	 * @param orderTermAttributeToBeAdded
	 *            the OrderTermAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderTermAttribute(@RequestBody OrderTermAttribute orderTermAttributeToBeAdded) throws Exception {

		AddOrderTermAttribute command = new AddOrderTermAttribute(orderTermAttributeToBeAdded);
		OrderTermAttribute orderTermAttribute = ((OrderTermAttributeAdded) Scheduler.execute(command).data()).getAddedOrderTermAttribute();
		
		if (orderTermAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderTermAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderTermAttribute could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateOrderTermAttribute(HttpServletRequest request) throws Exception {

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

		OrderTermAttribute orderTermAttributeToBeUpdated = new OrderTermAttribute();

		try {
			orderTermAttributeToBeUpdated = OrderTermAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderTermAttribute(orderTermAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderTermAttribute with the specific Id
	 * 
	 * @param orderTermAttributeToBeUpdated
	 *            the OrderTermAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderTermAttribute(@RequestBody OrderTermAttribute orderTermAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderTermAttributeToBeUpdated.setnull(null);

		UpdateOrderTermAttribute command = new UpdateOrderTermAttribute(orderTermAttributeToBeUpdated);

		try {
			if(((OrderTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderTermAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTermAttributeId", orderTermAttributeId);
		try {

			Object foundOrderTermAttribute = findOrderTermAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderTermAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderTermAttributeId}")
	public ResponseEntity<Object> deleteOrderTermAttributeByIdUpdated(@PathVariable String orderTermAttributeId) throws Exception {
		DeleteOrderTermAttribute command = new DeleteOrderTermAttribute(orderTermAttributeId);

		try {
			if (((OrderTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderTermAttribute could not be deleted");

	}

}
