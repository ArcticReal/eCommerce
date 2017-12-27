package com.skytala.eCommerce.domain.order.relations.orderAdjustment.control.billing;

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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.billing.AddOrderAdjustmentBilling;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.billing.DeleteOrderAdjustmentBilling;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.billing.UpdateOrderAdjustmentBilling;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing.OrderAdjustmentBillingAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing.OrderAdjustmentBillingDeleted;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing.OrderAdjustmentBillingFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing.OrderAdjustmentBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.billing.OrderAdjustmentBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing.OrderAdjustmentBilling;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.billing.FindOrderAdjustmentBillingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/orderAdjustment/orderAdjustmentBillings")
public class OrderAdjustmentBillingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OrderAdjustmentBillingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OrderAdjustmentBilling
	 * @return a List with the OrderAdjustmentBillings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OrderAdjustmentBilling>> findOrderAdjustmentBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentBillingsBy query = new FindOrderAdjustmentBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentBilling> orderAdjustmentBillings =((OrderAdjustmentBillingFound) Scheduler.execute(query).data()).getOrderAdjustmentBillings();

		return ResponseEntity.ok().body(orderAdjustmentBillings);

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
	public ResponseEntity<OrderAdjustmentBilling> createOrderAdjustmentBilling(HttpServletRequest request) throws Exception {

		OrderAdjustmentBilling orderAdjustmentBillingToBeAdded = new OrderAdjustmentBilling();
		try {
			orderAdjustmentBillingToBeAdded = OrderAdjustmentBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createOrderAdjustmentBilling(orderAdjustmentBillingToBeAdded);

	}

	/**
	 * creates a new OrderAdjustmentBilling entry in the ofbiz database
	 * 
	 * @param orderAdjustmentBillingToBeAdded
	 *            the OrderAdjustmentBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderAdjustmentBilling> createOrderAdjustmentBilling(@RequestBody OrderAdjustmentBilling orderAdjustmentBillingToBeAdded) throws Exception {

		AddOrderAdjustmentBilling command = new AddOrderAdjustmentBilling(orderAdjustmentBillingToBeAdded);
		OrderAdjustmentBilling orderAdjustmentBilling = ((OrderAdjustmentBillingAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentBilling();
		
		if (orderAdjustmentBilling != null) 
			return successful(orderAdjustmentBilling);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OrderAdjustmentBilling with the specific Id
	 * 
	 * @param orderAdjustmentBillingToBeUpdated
	 *            the OrderAdjustmentBilling thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOrderAdjustmentBilling(@RequestBody OrderAdjustmentBilling orderAdjustmentBillingToBeUpdated,
			@PathVariable String invoiceItemSeqId) throws Exception {

		orderAdjustmentBillingToBeUpdated.setInvoiceItemSeqId(invoiceItemSeqId);

		UpdateOrderAdjustmentBilling command = new UpdateOrderAdjustmentBilling(orderAdjustmentBillingToBeUpdated);

		try {
			if(((OrderAdjustmentBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{orderAdjustmentBillingId}")
	public ResponseEntity<OrderAdjustmentBilling> findById(@PathVariable String orderAdjustmentBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentBillingId", orderAdjustmentBillingId);
		try {

			List<OrderAdjustmentBilling> foundOrderAdjustmentBilling = findOrderAdjustmentBillingsBy(requestParams).getBody();
			if(foundOrderAdjustmentBilling.size()==1){				return successful(foundOrderAdjustmentBilling.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{orderAdjustmentBillingId}")
	public ResponseEntity<String> deleteOrderAdjustmentBillingByIdUpdated(@PathVariable String orderAdjustmentBillingId) throws Exception {
		DeleteOrderAdjustmentBilling command = new DeleteOrderAdjustmentBilling(orderAdjustmentBillingId);

		try {
			if (((OrderAdjustmentBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
