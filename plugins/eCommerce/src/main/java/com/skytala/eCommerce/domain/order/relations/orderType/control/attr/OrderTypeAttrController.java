package com.skytala.eCommerce.domain.order.relations.orderType.control.attr;

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
import com.skytala.eCommerce.domain.order.relations.orderType.command.attr.AddOrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.command.attr.DeleteOrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.command.attr.UpdateOrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderType.event.attr.OrderTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.orderType.mapper.attr.OrderTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderType.model.attr.OrderTypeAttr;
import com.skytala.eCommerce.domain.order.relations.orderType.query.attr.FindOrderTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderType/orderTypeAttrs")
public class OrderTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderTypeAttr
	 * @return a List with the OrderTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderTypeAttrsBy query = new FindOrderTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderTypeAttr> orderTypeAttrs =((OrderTypeAttrFound) Scheduler.execute(query).data()).getOrderTypeAttrs();

		if (orderTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(orderTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(orderTypeAttrs);

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
	public ResponseEntity<Object> createOrderTypeAttr(HttpServletRequest request) throws Exception {

		OrderTypeAttr orderTypeAttrToBeAdded = new OrderTypeAttr();
		try {
			orderTypeAttrToBeAdded = OrderTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderTypeAttr(orderTypeAttrToBeAdded);

	}

	/**
	 * creates a new OrderTypeAttr entry in the ofbiz database
	 * 
	 * @param orderTypeAttrToBeAdded
	 *            the OrderTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderTypeAttr(@RequestBody OrderTypeAttr orderTypeAttrToBeAdded) throws Exception {

		AddOrderTypeAttr command = new AddOrderTypeAttr(orderTypeAttrToBeAdded);
		OrderTypeAttr orderTypeAttr = ((OrderTypeAttrAdded) Scheduler.execute(command).data()).getAddedOrderTypeAttr();
		
		if (orderTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderTypeAttr could not be created.");
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
	public boolean updateOrderTypeAttr(HttpServletRequest request) throws Exception {

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

		OrderTypeAttr orderTypeAttrToBeUpdated = new OrderTypeAttr();

		try {
			orderTypeAttrToBeUpdated = OrderTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderTypeAttr(orderTypeAttrToBeUpdated, orderTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderTypeAttr with the specific Id
	 * 
	 * @param orderTypeAttrToBeUpdated
	 *            the OrderTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderTypeAttr(@RequestBody OrderTypeAttr orderTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		orderTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateOrderTypeAttr command = new UpdateOrderTypeAttr(orderTypeAttrToBeUpdated);

		try {
			if(((OrderTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String orderTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderTypeAttrId", orderTypeAttrId);
		try {

			Object foundOrderTypeAttr = findOrderTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderTypeAttrId}")
	public ResponseEntity<Object> deleteOrderTypeAttrByIdUpdated(@PathVariable String orderTypeAttrId) throws Exception {
		DeleteOrderTypeAttr command = new DeleteOrderTypeAttr(orderTypeAttrId);

		try {
			if (((OrderTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderTypeAttr could not be deleted");

	}

}
