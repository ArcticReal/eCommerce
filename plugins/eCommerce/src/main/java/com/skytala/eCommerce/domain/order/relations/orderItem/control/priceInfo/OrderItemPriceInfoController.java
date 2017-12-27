package com.skytala.eCommerce.domain.order.relations.orderItem.control.priceInfo;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.priceInfo.AddOrderItemPriceInfo;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.priceInfo.DeleteOrderItemPriceInfo;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.priceInfo.UpdateOrderItemPriceInfo;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo.OrderItemPriceInfoAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo.OrderItemPriceInfoDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo.OrderItemPriceInfoFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo.OrderItemPriceInfoUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.priceInfo.OrderItemPriceInfoMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.priceInfo.OrderItemPriceInfo;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.priceInfo.FindOrderItemPriceInfosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderItem/orderItemPriceInfos")
public class OrderItemPriceInfoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderItemPriceInfoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderItemPriceInfo
	 * @return a List with the OrderItemPriceInfos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderItemPriceInfo>> findOrderItemPriceInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderItemPriceInfosBy query = new FindOrderItemPriceInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderItemPriceInfo> orderItemPriceInfos =((OrderItemPriceInfoFound) Scheduler.execute(query).data()).getOrderItemPriceInfos();

		return ResponseEntity.ok().body(orderItemPriceInfos);

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
	public ResponseEntity<OrderItemPriceInfo> createOrderItemPriceInfo(HttpServletRequest request) throws Exception {

		OrderItemPriceInfo orderItemPriceInfoToBeAdded = new OrderItemPriceInfo();
		try {
			orderItemPriceInfoToBeAdded = OrderItemPriceInfoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderItemPriceInfo(orderItemPriceInfoToBeAdded);

	}

	/**
	 * creates a new OrderItemPriceInfo entry in the ofbiz database
	 * 
	 * @param orderItemPriceInfoToBeAdded
	 *            the OrderItemPriceInfo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderItemPriceInfo> createOrderItemPriceInfo(@RequestBody OrderItemPriceInfo orderItemPriceInfoToBeAdded) throws Exception {

		AddOrderItemPriceInfo command = new AddOrderItemPriceInfo(orderItemPriceInfoToBeAdded);
		OrderItemPriceInfo orderItemPriceInfo = ((OrderItemPriceInfoAdded) Scheduler.execute(command).data()).getAddedOrderItemPriceInfo();
		
		if (orderItemPriceInfo != null) 
			return successful(orderItemPriceInfo);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderItemPriceInfo with the specific Id
	 * 
	 * @param orderItemPriceInfoToBeUpdated
	 *            the OrderItemPriceInfo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemPriceInfoId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderItemPriceInfo(@RequestBody OrderItemPriceInfo orderItemPriceInfoToBeUpdated,
			@PathVariable String orderItemPriceInfoId) throws Exception {

		orderItemPriceInfoToBeUpdated.setOrderItemPriceInfoId(orderItemPriceInfoId);

		UpdateOrderItemPriceInfo command = new UpdateOrderItemPriceInfo(orderItemPriceInfoToBeUpdated);

		try {
			if(((OrderItemPriceInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderItemPriceInfoId}")
	public ResponseEntity<OrderItemPriceInfo> findById(@PathVariable String orderItemPriceInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderItemPriceInfoId", orderItemPriceInfoId);
		try {

			List<OrderItemPriceInfo> foundOrderItemPriceInfo = findOrderItemPriceInfosBy(requestParams).getBody();
			if(foundOrderItemPriceInfo.size()==1){				return successful(foundOrderItemPriceInfo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderItemPriceInfoId}")
	public ResponseEntity<String> deleteOrderItemPriceInfoByIdUpdated(@PathVariable String orderItemPriceInfoId) throws Exception {
		DeleteOrderItemPriceInfo command = new DeleteOrderItemPriceInfo(orderItemPriceInfoId);

		try {
			if (((OrderItemPriceInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
