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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/orderAdjustmentBillings")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findOrderAdjustmentBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOrderAdjustmentBillingsBy query = new FindOrderAdjustmentBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OrderAdjustmentBilling> orderAdjustmentBillings =((OrderAdjustmentBillingFound) Scheduler.execute(query).data()).getOrderAdjustmentBillings();

		if (orderAdjustmentBillings.size() == 1) {
			return ResponseEntity.ok().body(orderAdjustmentBillings.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createOrderAdjustmentBilling(HttpServletRequest request) throws Exception {

		OrderAdjustmentBilling orderAdjustmentBillingToBeAdded = new OrderAdjustmentBilling();
		try {
			orderAdjustmentBillingToBeAdded = OrderAdjustmentBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createOrderAdjustmentBilling(@RequestBody OrderAdjustmentBilling orderAdjustmentBillingToBeAdded) throws Exception {

		AddOrderAdjustmentBilling command = new AddOrderAdjustmentBilling(orderAdjustmentBillingToBeAdded);
		OrderAdjustmentBilling orderAdjustmentBilling = ((OrderAdjustmentBillingAdded) Scheduler.execute(command).data()).getAddedOrderAdjustmentBilling();
		
		if (orderAdjustmentBilling != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(orderAdjustmentBilling);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("OrderAdjustmentBilling could not be created.");
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
	public boolean updateOrderAdjustmentBilling(HttpServletRequest request) throws Exception {

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

		OrderAdjustmentBilling orderAdjustmentBillingToBeUpdated = new OrderAdjustmentBilling();

		try {
			orderAdjustmentBillingToBeUpdated = OrderAdjustmentBillingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateOrderAdjustmentBilling(orderAdjustmentBillingToBeUpdated, orderAdjustmentBillingToBeUpdated.getInvoiceItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateOrderAdjustmentBilling(@RequestBody OrderAdjustmentBilling orderAdjustmentBillingToBeUpdated,
			@PathVariable String invoiceItemSeqId) throws Exception {

		orderAdjustmentBillingToBeUpdated.setInvoiceItemSeqId(invoiceItemSeqId);

		UpdateOrderAdjustmentBilling command = new UpdateOrderAdjustmentBilling(orderAdjustmentBillingToBeUpdated);

		try {
			if(((OrderAdjustmentBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{orderAdjustmentBillingId}")
	public ResponseEntity<Object> findById(@PathVariable String orderAdjustmentBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("orderAdjustmentBillingId", orderAdjustmentBillingId);
		try {

			Object foundOrderAdjustmentBilling = findOrderAdjustmentBillingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundOrderAdjustmentBilling);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{orderAdjustmentBillingId}")
	public ResponseEntity<Object> deleteOrderAdjustmentBillingByIdUpdated(@PathVariable String orderAdjustmentBillingId) throws Exception {
		DeleteOrderAdjustmentBilling command = new DeleteOrderAdjustmentBilling(orderAdjustmentBillingId);

		try {
			if (((OrderAdjustmentBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("OrderAdjustmentBilling could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/orderAdjustmentBilling/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
