package com.skytala.eCommerce.domain.order.relations.orderContent;

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
import com.skytala.eCommerce.domain.order.relations.orderContent.command.AddOrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.command.DeleteOrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.command.UpdateOrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentAdded;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentDeleted;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentFound;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContent.mapper.OrderContentMapper;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;
import com.skytala.eCommerce.domain.order.relations.orderContent.query.FindOrderContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderContents")
public class OrderContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderContent
	 * @return a List with the OrderContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderContent>> findOrderContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderContentsBy query = new FindOrderContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderContent> orderContents =((OrderContentFound) Scheduler.execute(query).data()).getOrderContents();

		return ResponseEntity.ok().body(orderContents);

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
	public ResponseEntity<OrderContent> createOrderContent(HttpServletRequest request) throws Exception {

		OrderContent orderContentToBeAdded = new OrderContent();
		try {
			orderContentToBeAdded = OrderContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderContent(orderContentToBeAdded);

	}

	/**
	 * creates a new OrderContent entry in the ofbiz database
	 * 
	 * @param orderContentToBeAdded
	 *            the OrderContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderContent> createOrderContent(@RequestBody OrderContent orderContentToBeAdded) throws Exception {

		AddOrderContent command = new AddOrderContent(orderContentToBeAdded);
		OrderContent orderContent = ((OrderContentAdded) Scheduler.execute(command).data()).getAddedOrderContent();
		
		if (orderContent != null) 
			return successful(orderContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderContent with the specific Id
	 * 
	 * @param orderContentToBeUpdated
	 *            the OrderContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{orderItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderContent(@RequestBody OrderContent orderContentToBeUpdated,
			@PathVariable String orderItemSeqId) throws Exception {

		orderContentToBeUpdated.setOrderItemSeqId(orderItemSeqId);

		UpdateOrderContent command = new UpdateOrderContent(orderContentToBeUpdated);

		try {
			if(((OrderContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderContentId}")
	public ResponseEntity<OrderContent> findById(@PathVariable String orderContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderContentId", orderContentId);
		try {

			List<OrderContent> foundOrderContent = findOrderContentsBy(requestParams).getBody();
			if(foundOrderContent.size()==1){				return successful(foundOrderContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderContentId}")
	public ResponseEntity<String> deleteOrderContentByIdUpdated(@PathVariable String orderContentId) throws Exception {
		DeleteOrderContent command = new DeleteOrderContent(orderContentId);

		try {
			if (((OrderContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
