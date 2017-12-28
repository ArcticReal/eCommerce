package com.skytala.eCommerce.domain.order.relations.orderContactMech;

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
import com.skytala.eCommerce.domain.order.relations.orderContactMech.command.AddOrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.command.DeleteOrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.command.UpdateOrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechDeleted;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechFound;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.mapper.OrderContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.query.FindOrderContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderContactMechs")
public class OrderContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderContactMech
	 * @return a List with the OrderContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderContactMech>> findOrderContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderContactMechsBy query = new FindOrderContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderContactMech> orderContactMechs =((OrderContactMechFound) Scheduler.execute(query).data()).getOrderContactMechs();

		return ResponseEntity.ok().body(orderContactMechs);

	}

	/**
	 * creates a new OrderContactMech entry in the ofbiz database
	 * 
	 * @param orderContactMechToBeAdded
	 *            the OrderContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderContactMech> createOrderContactMech(@RequestBody OrderContactMech orderContactMechToBeAdded) throws Exception {

		AddOrderContactMech command = new AddOrderContactMech(orderContactMechToBeAdded);
		OrderContactMech orderContactMech = ((OrderContactMechAdded) Scheduler.execute(command).data()).getAddedOrderContactMech();
		
		if (orderContactMech != null) 
			return successful(orderContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderContactMech with the specific Id
	 * 
	 * @param orderContactMechToBeUpdated
	 *            the OrderContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderContactMech(@RequestBody OrderContactMech orderContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		orderContactMechToBeUpdated.setnull(null);

		UpdateOrderContactMech command = new UpdateOrderContactMech(orderContactMechToBeUpdated);

		try {
			if(((OrderContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderContactMechId}")
	public ResponseEntity<OrderContactMech> findById(@PathVariable String orderContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderContactMechId", orderContactMechId);
		try {

			List<OrderContactMech> foundOrderContactMech = findOrderContactMechsBy(requestParams).getBody();
			if(foundOrderContactMech.size()==1){				return successful(foundOrderContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderContactMechId}")
	public ResponseEntity<String> deleteOrderContactMechByIdUpdated(@PathVariable String orderContactMechId) throws Exception {
		DeleteOrderContactMech command = new DeleteOrderContactMech(orderContactMechId);

		try {
			if (((OrderContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
