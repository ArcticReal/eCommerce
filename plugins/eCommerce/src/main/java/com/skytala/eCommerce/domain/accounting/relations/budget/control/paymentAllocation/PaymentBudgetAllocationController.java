package com.skytala.eCommerce.domain.accounting.relations.budget.control.paymentAllocation;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.paymentAllocation.AddPaymentBudgetAllocation;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.paymentAllocation.DeletePaymentBudgetAllocation;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.paymentAllocation.UpdatePaymentBudgetAllocation;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation.PaymentBudgetAllocationAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation.PaymentBudgetAllocationDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation.PaymentBudgetAllocationFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation.PaymentBudgetAllocationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.paymentAllocation.PaymentBudgetAllocationMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.paymentAllocation.PaymentBudgetAllocation;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.paymentAllocation.FindPaymentBudgetAllocationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/budget/paymentBudgetAllocations")
public class PaymentBudgetAllocationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentBudgetAllocationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentBudgetAllocation
	 * @return a List with the PaymentBudgetAllocations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPaymentBudgetAllocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentBudgetAllocationsBy query = new FindPaymentBudgetAllocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentBudgetAllocation> paymentBudgetAllocations =((PaymentBudgetAllocationFound) Scheduler.execute(query).data()).getPaymentBudgetAllocations();

		if (paymentBudgetAllocations.size() == 1) {
			return ResponseEntity.ok().body(paymentBudgetAllocations.get(0));
		}

		return ResponseEntity.ok().body(paymentBudgetAllocations);

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
	public ResponseEntity<Object> createPaymentBudgetAllocation(HttpServletRequest request) throws Exception {

		PaymentBudgetAllocation paymentBudgetAllocationToBeAdded = new PaymentBudgetAllocation();
		try {
			paymentBudgetAllocationToBeAdded = PaymentBudgetAllocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentBudgetAllocation(paymentBudgetAllocationToBeAdded);

	}

	/**
	 * creates a new PaymentBudgetAllocation entry in the ofbiz database
	 * 
	 * @param paymentBudgetAllocationToBeAdded
	 *            the PaymentBudgetAllocation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentBudgetAllocation(@RequestBody PaymentBudgetAllocation paymentBudgetAllocationToBeAdded) throws Exception {

		AddPaymentBudgetAllocation command = new AddPaymentBudgetAllocation(paymentBudgetAllocationToBeAdded);
		PaymentBudgetAllocation paymentBudgetAllocation = ((PaymentBudgetAllocationAdded) Scheduler.execute(command).data()).getAddedPaymentBudgetAllocation();
		
		if (paymentBudgetAllocation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentBudgetAllocation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentBudgetAllocation could not be created.");
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
	public boolean updatePaymentBudgetAllocation(HttpServletRequest request) throws Exception {

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

		PaymentBudgetAllocation paymentBudgetAllocationToBeUpdated = new PaymentBudgetAllocation();

		try {
			paymentBudgetAllocationToBeUpdated = PaymentBudgetAllocationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentBudgetAllocation(paymentBudgetAllocationToBeUpdated, paymentBudgetAllocationToBeUpdated.getBudgetItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentBudgetAllocation with the specific Id
	 * 
	 * @param paymentBudgetAllocationToBeUpdated
	 *            the PaymentBudgetAllocation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentBudgetAllocation(@RequestBody PaymentBudgetAllocation paymentBudgetAllocationToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		paymentBudgetAllocationToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdatePaymentBudgetAllocation command = new UpdatePaymentBudgetAllocation(paymentBudgetAllocationToBeUpdated);

		try {
			if(((PaymentBudgetAllocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentBudgetAllocationId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentBudgetAllocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentBudgetAllocationId", paymentBudgetAllocationId);
		try {

			Object foundPaymentBudgetAllocation = findPaymentBudgetAllocationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentBudgetAllocation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentBudgetAllocationId}")
	public ResponseEntity<Object> deletePaymentBudgetAllocationByIdUpdated(@PathVariable String paymentBudgetAllocationId) throws Exception {
		DeletePaymentBudgetAllocation command = new DeletePaymentBudgetAllocation(paymentBudgetAllocationId);

		try {
			if (((PaymentBudgetAllocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentBudgetAllocation could not be deleted");

	}

}
