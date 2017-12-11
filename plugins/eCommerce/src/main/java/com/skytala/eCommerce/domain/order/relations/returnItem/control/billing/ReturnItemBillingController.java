package com.skytala.eCommerce.domain.order.relations.returnItem.control.billing;

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
import com.skytala.eCommerce.domain.order.relations.returnItem.command.billing.AddReturnItemBilling;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.billing.DeleteReturnItemBilling;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.billing.UpdateReturnItemBilling;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.billing.ReturnItemBillingAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.billing.ReturnItemBillingDeleted;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.billing.ReturnItemBillingFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.billing.ReturnItemBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.billing.ReturnItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.billing.ReturnItemBilling;
import com.skytala.eCommerce.domain.order.relations.returnItem.query.billing.FindReturnItemBillingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/returnItem/returnItemBillings")
public class ReturnItemBillingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemBillingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItemBilling
	 * @return a List with the ReturnItemBillings
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnItemBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemBillingsBy query = new FindReturnItemBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemBilling> returnItemBillings =((ReturnItemBillingFound) Scheduler.execute(query).data()).getReturnItemBillings();

		if (returnItemBillings.size() == 1) {
			return ResponseEntity.ok().body(returnItemBillings.get(0));
		}

		return ResponseEntity.ok().body(returnItemBillings);

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
	public ResponseEntity<Object> createReturnItemBilling(HttpServletRequest request) throws Exception {

		ReturnItemBilling returnItemBillingToBeAdded = new ReturnItemBilling();
		try {
			returnItemBillingToBeAdded = ReturnItemBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnItemBilling(returnItemBillingToBeAdded);

	}

	/**
	 * creates a new ReturnItemBilling entry in the ofbiz database
	 * 
	 * @param returnItemBillingToBeAdded
	 *            the ReturnItemBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnItemBilling(@RequestBody ReturnItemBilling returnItemBillingToBeAdded) throws Exception {

		AddReturnItemBilling command = new AddReturnItemBilling(returnItemBillingToBeAdded);
		ReturnItemBilling returnItemBilling = ((ReturnItemBillingAdded) Scheduler.execute(command).data()).getAddedReturnItemBilling();
		
		if (returnItemBilling != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnItemBilling);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnItemBilling could not be created.");
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
	public boolean updateReturnItemBilling(HttpServletRequest request) throws Exception {

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

		ReturnItemBilling returnItemBillingToBeUpdated = new ReturnItemBilling();

		try {
			returnItemBillingToBeUpdated = ReturnItemBillingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnItemBilling(returnItemBillingToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnItemBilling with the specific Id
	 * 
	 * @param returnItemBillingToBeUpdated
	 *            the ReturnItemBilling thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnItemBilling(@RequestBody ReturnItemBilling returnItemBillingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		returnItemBillingToBeUpdated.setnull(null);

		UpdateReturnItemBilling command = new UpdateReturnItemBilling(returnItemBillingToBeUpdated);

		try {
			if(((ReturnItemBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnItemBillingId}")
	public ResponseEntity<Object> findById(@PathVariable String returnItemBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemBillingId", returnItemBillingId);
		try {

			Object foundReturnItemBilling = findReturnItemBillingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnItemBilling);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnItemBillingId}")
	public ResponseEntity<Object> deleteReturnItemBillingByIdUpdated(@PathVariable String returnItemBillingId) throws Exception {
		DeleteReturnItemBilling command = new DeleteReturnItemBilling(returnItemBillingId);

		try {
			if (((ReturnItemBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnItemBilling could not be deleted");

	}

}
