package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode;

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
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.command.AddOrderProductPromoCode;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.command.DeleteOrderProductPromoCode;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.command.UpdateOrderProductPromoCode;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeAdded;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeFound;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.mapper.OrderProductPromoCodeMapper;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.query.FindOrderProductPromoCodesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderProductPromoCodes")
public class OrderProductPromoCodeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderProductPromoCodeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderProductPromoCode
	 * @return a List with the OrderProductPromoCodes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findOrderProductPromoCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderProductPromoCodesBy query = new FindOrderProductPromoCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderProductPromoCode> orderProductPromoCodes =((OrderProductPromoCodeFound) Scheduler.execute(query).data()).getOrderProductPromoCodes();

		if (orderProductPromoCodes.size() == 1) {
			return ResponseEntity.ok().body(orderProductPromoCodes.get(0));
		}

		return ResponseEntity.ok().body(orderProductPromoCodes);

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
	public ResponseEntity<Object> createOrderProductPromoCode(HttpServletRequest request) throws Exception {

		OrderProductPromoCode orderProductPromoCodeToBeAdded = new OrderProductPromoCode();
		try {
			orderProductPromoCodeToBeAdded = OrderProductPromoCodeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createOrderProductPromoCode(orderProductPromoCodeToBeAdded);

	}

	/**
	 * creates a new OrderProductPromoCode entry in the ofbiz database
	 * 
	 * @param orderProductPromoCodeToBeAdded
	 *            the OrderProductPromoCode thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOrderProductPromoCode(@RequestBody OrderProductPromoCode orderProductPromoCodeToBeAdded) throws Exception {

		AddOrderProductPromoCode command = new AddOrderProductPromoCode(orderProductPromoCodeToBeAdded);
		OrderProductPromoCode orderProductPromoCode = ((OrderProductPromoCodeAdded) Scheduler.execute(command).data()).getAddedOrderProductPromoCode();
		
		if (orderProductPromoCode != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderProductPromoCode);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderProductPromoCode could not be created.");
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
	public boolean updateOrderProductPromoCode(HttpServletRequest request) throws Exception {

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

		OrderProductPromoCode orderProductPromoCodeToBeUpdated = new OrderProductPromoCode();

		try {
			orderProductPromoCodeToBeUpdated = OrderProductPromoCodeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderProductPromoCode(orderProductPromoCodeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the OrderProductPromoCode with the specific Id
	 * 
	 * @param orderProductPromoCodeToBeUpdated
	 *            the OrderProductPromoCode thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOrderProductPromoCode(@RequestBody OrderProductPromoCode orderProductPromoCodeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderProductPromoCodeToBeUpdated.setnull(null);

		UpdateOrderProductPromoCode command = new UpdateOrderProductPromoCode(orderProductPromoCodeToBeUpdated);

		try {
			if(((OrderProductPromoCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{orderProductPromoCodeId}")
	public ResponseEntity<Object> findById(@PathVariable String orderProductPromoCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderProductPromoCodeId", orderProductPromoCodeId);
		try {

			Object foundOrderProductPromoCode = findOrderProductPromoCodesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderProductPromoCode);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{orderProductPromoCodeId}")
	public ResponseEntity<Object> deleteOrderProductPromoCodeByIdUpdated(@PathVariable String orderProductPromoCodeId) throws Exception {
		DeleteOrderProductPromoCode command = new DeleteOrderProductPromoCode(orderProductPromoCodeId);

		try {
			if (((OrderProductPromoCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderProductPromoCode could not be deleted");

	}

}
