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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PaymentBudgetAllocation>> findPaymentBudgetAllocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentBudgetAllocationsBy query = new FindPaymentBudgetAllocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentBudgetAllocation> paymentBudgetAllocations =((PaymentBudgetAllocationFound) Scheduler.execute(query).data()).getPaymentBudgetAllocations();

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
	public ResponseEntity<PaymentBudgetAllocation> createPaymentBudgetAllocation(HttpServletRequest request) throws Exception {

		PaymentBudgetAllocation paymentBudgetAllocationToBeAdded = new PaymentBudgetAllocation();
		try {
			paymentBudgetAllocationToBeAdded = PaymentBudgetAllocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PaymentBudgetAllocation> createPaymentBudgetAllocation(@RequestBody PaymentBudgetAllocation paymentBudgetAllocationToBeAdded) throws Exception {

		AddPaymentBudgetAllocation command = new AddPaymentBudgetAllocation(paymentBudgetAllocationToBeAdded);
		PaymentBudgetAllocation paymentBudgetAllocation = ((PaymentBudgetAllocationAdded) Scheduler.execute(command).data()).getAddedPaymentBudgetAllocation();
		
		if (paymentBudgetAllocation != null) 
			return successful(paymentBudgetAllocation);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePaymentBudgetAllocation(@RequestBody PaymentBudgetAllocation paymentBudgetAllocationToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		paymentBudgetAllocationToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdatePaymentBudgetAllocation command = new UpdatePaymentBudgetAllocation(paymentBudgetAllocationToBeUpdated);

		try {
			if(((PaymentBudgetAllocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentBudgetAllocationId}")
	public ResponseEntity<PaymentBudgetAllocation> findById(@PathVariable String paymentBudgetAllocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentBudgetAllocationId", paymentBudgetAllocationId);
		try {

			List<PaymentBudgetAllocation> foundPaymentBudgetAllocation = findPaymentBudgetAllocationsBy(requestParams).getBody();
			if(foundPaymentBudgetAllocation.size()==1){				return successful(foundPaymentBudgetAllocation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentBudgetAllocationId}")
	public ResponseEntity<String> deletePaymentBudgetAllocationByIdUpdated(@PathVariable String paymentBudgetAllocationId) throws Exception {
		DeletePaymentBudgetAllocation command = new DeletePaymentBudgetAllocation(paymentBudgetAllocationId);

		try {
			if (((PaymentBudgetAllocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
