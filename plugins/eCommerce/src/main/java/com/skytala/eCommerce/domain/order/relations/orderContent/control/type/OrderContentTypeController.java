package com.skytala.eCommerce.domain.order.relations.orderContent.control.type;

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
import com.skytala.eCommerce.domain.order.relations.orderContent.command.type.AddOrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContent.command.type.DeleteOrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContent.command.type.UpdateOrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.type.OrderContentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.type.OrderContentTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.type.OrderContentTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.type.OrderContentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContent.mapper.type.OrderContentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
import com.skytala.eCommerce.domain.order.relations.orderContent.query.type.FindOrderContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderContent/orderContentTypes")
public class OrderContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderContentType
	 * @return a List with the OrderContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderContentType>> findOrderContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderContentTypesBy query = new FindOrderContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderContentType> orderContentTypes =((OrderContentTypeFound) Scheduler.execute(query).data()).getOrderContentTypes();

		return ResponseEntity.ok().body(orderContentTypes);

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
	public ResponseEntity<OrderContentType> createOrderContentType(HttpServletRequest request) throws Exception {

		OrderContentType orderContentTypeToBeAdded = new OrderContentType();
		try {
			orderContentTypeToBeAdded = OrderContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderContentType(orderContentTypeToBeAdded);

	}

	/**
	 * creates a new OrderContentType entry in the ofbiz database
	 * 
	 * @param orderContentTypeToBeAdded
	 *            the OrderContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderContentType> createOrderContentType(@RequestBody OrderContentType orderContentTypeToBeAdded) throws Exception {

		AddOrderContentType command = new AddOrderContentType(orderContentTypeToBeAdded);
		OrderContentType orderContentType = ((OrderContentTypeAdded) Scheduler.execute(command).data()).getAddedOrderContentType();
		
		if (orderContentType != null) 
			return successful(orderContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderContentType with the specific Id
	 * 
	 * @param orderContentTypeToBeUpdated
	 *            the OrderContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderContentType(@RequestBody OrderContentType orderContentTypeToBeUpdated,
			@PathVariable String orderContentTypeId) throws Exception {

		orderContentTypeToBeUpdated.setOrderContentTypeId(orderContentTypeId);

		UpdateOrderContentType command = new UpdateOrderContentType(orderContentTypeToBeUpdated);

		try {
			if(((OrderContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderContentTypeId}")
	public ResponseEntity<OrderContentType> findById(@PathVariable String orderContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderContentTypeId", orderContentTypeId);
		try {

			List<OrderContentType> foundOrderContentType = findOrderContentTypesBy(requestParams).getBody();
			if(foundOrderContentType.size()==1){				return successful(foundOrderContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderContentTypeId}")
	public ResponseEntity<String> deleteOrderContentTypeByIdUpdated(@PathVariable String orderContentTypeId) throws Exception {
		DeleteOrderContentType command = new DeleteOrderContentType(orderContentTypeId);

		try {
			if (((OrderContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
