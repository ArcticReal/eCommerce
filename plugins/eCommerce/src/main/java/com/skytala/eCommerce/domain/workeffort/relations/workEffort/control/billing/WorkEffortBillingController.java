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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortBillingsBy query = new FindWorkEffortBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortBilling> workEffortBillings =((WorkEffortBillingFound) Scheduler.execute(query).data()).getWorkEffortBillings();

		if (workEffortBillings.size() == 1) {
			return ResponseEntity.ok().body(workEffortBillings.get(0));
		}

		return ResponseEntity.ok().body(workEffortBillings);

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
	public ResponseEntity<Object> createWorkEffortBilling(HttpServletRequest request) throws Exception {

		WorkEffortBilling workEffortBillingToBeAdded = new WorkEffortBilling();
		try {
			workEffortBillingToBeAdded = WorkEffortBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortBilling(workEffortBillingToBeAdded);

	}

	/**
	 * creates a new WorkEffortBilling entry in the ofbiz database
	 * 
	 * @param workEffortBillingToBeAdded
	 *            the WorkEffortBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortBilling(@RequestBody WorkEffortBilling workEffortBillingToBeAdded) throws Exception {

		AddWorkEffortBilling command = new AddWorkEffortBilling(workEffortBillingToBeAdded);
		WorkEffortBilling workEffortBilling = ((WorkEffortBillingAdded) Scheduler.execute(command).data()).getAddedWorkEffortBilling();
		
		if (workEffortBilling != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortBilling);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortBilling could not be created.");
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
	public boolean updateWorkEffortBilling(HttpServletRequest request) throws Exception {

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

		WorkEffortBilling workEffortBillingToBeUpdated = new WorkEffortBilling();

		try {
			workEffortBillingToBeUpdated = WorkEffortBillingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortBilling(workEffortBillingToBeUpdated, workEffortBillingToBeUpdated.getInvoiceItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkEffortBilling(@RequestBody WorkEffortBilling workEffortBillingToBeUpdated,
			@PathVariable String invoiceItemSeqId) throws Exception {

		workEffortBillingToBeUpdated.setInvoiceItemSeqId(invoiceItemSeqId);

		UpdateWorkEffortBilling command = new UpdateWorkEffortBilling(workEffortBillingToBeUpdated);

		try {
			if(((WorkEffortBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortBillingId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortBillingId", workEffortBillingId);
		try {

			Object foundWorkEffortBilling = findWorkEffortBillingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortBilling);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortBillingId}")
	public ResponseEntity<Object> deleteWorkEffortBillingByIdUpdated(@PathVariable String workEffortBillingId) throws Exception {
		DeleteWorkEffortBilling command = new DeleteWorkEffortBilling(workEffortBillingId);

		try {
			if (((WorkEffortBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortBilling could not be deleted");

	}

}
