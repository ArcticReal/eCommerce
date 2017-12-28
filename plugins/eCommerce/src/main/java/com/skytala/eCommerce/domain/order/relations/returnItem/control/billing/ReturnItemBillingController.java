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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<ReturnItemBilling>> findReturnItemBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemBillingsBy query = new FindReturnItemBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemBilling> returnItemBillings =((ReturnItemBillingFound) Scheduler.execute(query).data()).getReturnItemBillings();

		return ResponseEntity.ok().body(returnItemBillings);

	}

	/**
	 * creates a new ReturnItemBilling entry in the ofbiz database
	 * 
	 * @param returnItemBillingToBeAdded
	 *            the ReturnItemBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnItemBilling> createReturnItemBilling(@RequestBody ReturnItemBilling returnItemBillingToBeAdded) throws Exception {

		AddReturnItemBilling command = new AddReturnItemBilling(returnItemBillingToBeAdded);
		ReturnItemBilling returnItemBilling = ((ReturnItemBillingAdded) Scheduler.execute(command).data()).getAddedReturnItemBilling();
		
		if (returnItemBilling != null) 
			return successful(returnItemBilling);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateReturnItemBilling(@RequestBody ReturnItemBilling returnItemBillingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		returnItemBillingToBeUpdated.setnull(null);

		UpdateReturnItemBilling command = new UpdateReturnItemBilling(returnItemBillingToBeUpdated);

		try {
			if(((ReturnItemBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnItemBillingId}")
	public ResponseEntity<ReturnItemBilling> findById(@PathVariable String returnItemBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemBillingId", returnItemBillingId);
		try {

			List<ReturnItemBilling> foundReturnItemBilling = findReturnItemBillingsBy(requestParams).getBody();
			if(foundReturnItemBilling.size()==1){				return successful(foundReturnItemBilling.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnItemBillingId}")
	public ResponseEntity<String> deleteReturnItemBillingByIdUpdated(@PathVariable String returnItemBillingId) throws Exception {
		DeleteReturnItemBilling command = new DeleteReturnItemBilling(returnItemBillingId);

		try {
			if (((ReturnItemBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
