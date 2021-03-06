package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.billing;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.billing.AddWorkEffortBilling;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.billing.DeleteWorkEffortBilling;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.billing.UpdateWorkEffortBilling;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.billing.WorkEffortBillingAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.billing.WorkEffortBillingDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.billing.WorkEffortBillingFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.billing.WorkEffortBillingUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.billing.WorkEffortBillingMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.billing.WorkEffortBilling;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.billing.FindWorkEffortBillingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortBillings")
public class WorkEffortBillingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortBillingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortBilling
	 * @return a List with the WorkEffortBillings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortBilling>> findWorkEffortBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortBillingsBy query = new FindWorkEffortBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortBilling> workEffortBillings =((WorkEffortBillingFound) Scheduler.execute(query).data()).getWorkEffortBillings();

		return ResponseEntity.ok().body(workEffortBillings);

	}

	/**
	 * creates a new WorkEffortBilling entry in the ofbiz database
	 * 
	 * @param workEffortBillingToBeAdded
	 *            the WorkEffortBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortBilling> createWorkEffortBilling(@RequestBody WorkEffortBilling workEffortBillingToBeAdded) throws Exception {

		AddWorkEffortBilling command = new AddWorkEffortBilling(workEffortBillingToBeAdded);
		WorkEffortBilling workEffortBilling = ((WorkEffortBillingAdded) Scheduler.execute(command).data()).getAddedWorkEffortBilling();
		
		if (workEffortBilling != null) 
			return successful(workEffortBilling);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortBilling with the specific Id
	 * 
	 * @param workEffortBillingToBeUpdated
	 *            the WorkEffortBilling thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortBilling(@RequestBody WorkEffortBilling workEffortBillingToBeUpdated,
			@PathVariable String invoiceItemSeqId) throws Exception {

		workEffortBillingToBeUpdated.setInvoiceItemSeqId(invoiceItemSeqId);

		UpdateWorkEffortBilling command = new UpdateWorkEffortBilling(workEffortBillingToBeUpdated);

		try {
			if(((WorkEffortBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortBillingId}")
	public ResponseEntity<WorkEffortBilling> findById(@PathVariable String workEffortBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortBillingId", workEffortBillingId);
		try {

			List<WorkEffortBilling> foundWorkEffortBilling = findWorkEffortBillingsBy(requestParams).getBody();
			if(foundWorkEffortBilling.size()==1){				return successful(foundWorkEffortBilling.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortBillingId}")
	public ResponseEntity<String> deleteWorkEffortBillingByIdUpdated(@PathVariable String workEffortBillingId) throws Exception {
		DeleteWorkEffortBilling command = new DeleteWorkEffortBilling(workEffortBillingId);

		try {
			if (((WorkEffortBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
