package com.skytala.eCommerce.domain.accounting.relations.budget.control.revision;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.revision.AddBudgetRevision;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.revision.DeleteBudgetRevision;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.revision.UpdateBudgetRevision;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revision.BudgetRevisionAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revision.BudgetRevisionDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revision.BudgetRevisionFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revision.BudgetRevisionUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.revision.BudgetRevisionMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.revision.BudgetRevision;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.revision.FindBudgetRevisionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/budget/budgetRevisions")
public class BudgetRevisionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetRevisionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetRevision
	 * @return a List with the BudgetRevisions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetRevisionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetRevisionsBy query = new FindBudgetRevisionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetRevision> budgetRevisions =((BudgetRevisionFound) Scheduler.execute(query).data()).getBudgetRevisions();

		if (budgetRevisions.size() == 1) {
			return ResponseEntity.ok().body(budgetRevisions.get(0));
		}

		return ResponseEntity.ok().body(budgetRevisions);

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
	public ResponseEntity<Object> createBudgetRevision(HttpServletRequest request) throws Exception {

		BudgetRevision budgetRevisionToBeAdded = new BudgetRevision();
		try {
			budgetRevisionToBeAdded = BudgetRevisionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetRevision(budgetRevisionToBeAdded);

	}

	/**
	 * creates a new BudgetRevision entry in the ofbiz database
	 * 
	 * @param budgetRevisionToBeAdded
	 *            the BudgetRevision thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetRevision(@RequestBody BudgetRevision budgetRevisionToBeAdded) throws Exception {

		AddBudgetRevision command = new AddBudgetRevision(budgetRevisionToBeAdded);
		BudgetRevision budgetRevision = ((BudgetRevisionAdded) Scheduler.execute(command).data()).getAddedBudgetRevision();
		
		if (budgetRevision != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetRevision);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetRevision could not be created.");
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
	public boolean updateBudgetRevision(HttpServletRequest request) throws Exception {

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

		BudgetRevision budgetRevisionToBeUpdated = new BudgetRevision();

		try {
			budgetRevisionToBeUpdated = BudgetRevisionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetRevision(budgetRevisionToBeUpdated, budgetRevisionToBeUpdated.getRevisionSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetRevision with the specific Id
	 * 
	 * @param budgetRevisionToBeUpdated
	 *            the BudgetRevision thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{revisionSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetRevision(@RequestBody BudgetRevision budgetRevisionToBeUpdated,
			@PathVariable String revisionSeqId) throws Exception {

		budgetRevisionToBeUpdated.setRevisionSeqId(revisionSeqId);

		UpdateBudgetRevision command = new UpdateBudgetRevision(budgetRevisionToBeUpdated);

		try {
			if(((BudgetRevisionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetRevisionId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetRevisionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetRevisionId", budgetRevisionId);
		try {

			Object foundBudgetRevision = findBudgetRevisionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetRevision);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetRevisionId}")
	public ResponseEntity<Object> deleteBudgetRevisionByIdUpdated(@PathVariable String budgetRevisionId) throws Exception {
		DeleteBudgetRevision command = new DeleteBudgetRevision(budgetRevisionId);

		try {
			if (((BudgetRevisionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetRevision could not be deleted");

	}

}
