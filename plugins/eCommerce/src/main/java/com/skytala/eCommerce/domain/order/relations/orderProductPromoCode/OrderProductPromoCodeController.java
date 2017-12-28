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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<OrderProductPromoCode>> findOrderProductPromoCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderProductPromoCodesBy query = new FindOrderProductPromoCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderProductPromoCode> orderProductPromoCodes =((OrderProductPromoCodeFound) Scheduler.execute(query).data()).getOrderProductPromoCodes();

		return ResponseEntity.ok().body(orderProductPromoCodes);

	}

	/**
	 * creates a new OrderProductPromoCode entry in the ofbiz database
	 * 
	 * @param orderProductPromoCodeToBeAdded
	 *            the OrderProductPromoCode thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderProductPromoCode> createOrderProductPromoCode(@RequestBody OrderProductPromoCode orderProductPromoCodeToBeAdded) throws Exception {

		AddOrderProductPromoCode command = new AddOrderProductPromoCode(orderProductPromoCodeToBeAdded);
		OrderProductPromoCode orderProductPromoCode = ((OrderProductPromoCodeAdded) Scheduler.execute(command).data()).getAddedOrderProductPromoCode();
		
		if (orderProductPromoCode != null) 
			return successful(orderProductPromoCode);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateOrderProductPromoCode(@RequestBody OrderProductPromoCode orderProductPromoCodeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderProductPromoCodeToBeUpdated.setnull(null);

		UpdateOrderProductPromoCode command = new UpdateOrderProductPromoCode(orderProductPromoCodeToBeUpdated);

		try {
			if(((OrderProductPromoCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderProductPromoCodeId}")
	public ResponseEntity<OrderProductPromoCode> findById(@PathVariable String orderProductPromoCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderProductPromoCodeId", orderProductPromoCodeId);
		try {

			List<OrderProductPromoCode> foundOrderProductPromoCode = findOrderProductPromoCodesBy(requestParams).getBody();
			if(foundOrderProductPromoCode.size()==1){				return successful(foundOrderProductPromoCode.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderProductPromoCodeId}")
	public ResponseEntity<String> deleteOrderProductPromoCodeByIdUpdated(@PathVariable String orderProductPromoCodeId) throws Exception {
		DeleteOrderProductPromoCode command = new DeleteOrderProductPromoCode(orderProductPromoCodeId);

		try {
			if (((OrderProductPromoCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
