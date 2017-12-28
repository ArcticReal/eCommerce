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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<BudgetRevision>> findBudgetRevisionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetRevisionsBy query = new FindBudgetRevisionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetRevision> budgetRevisions =((BudgetRevisionFound) Scheduler.execute(query).data()).getBudgetRevisions();

		return ResponseEntity.ok().body(budgetRevisions);

	}

	/**
	 * creates a new BudgetRevision entry in the ofbiz database
	 * 
	 * @param budgetRevisionToBeAdded
	 *            the BudgetRevision thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetRevision> createBudgetRevision(@RequestBody BudgetRevision budgetRevisionToBeAdded) throws Exception {

		AddBudgetRevision command = new AddBudgetRevision(budgetRevisionToBeAdded);
		BudgetRevision budgetRevision = ((BudgetRevisionAdded) Scheduler.execute(command).data()).getAddedBudgetRevision();
		
		if (budgetRevision != null) 
			return successful(budgetRevision);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateBudgetRevision(@RequestBody BudgetRevision budgetRevisionToBeUpdated,
			@PathVariable String revisionSeqId) throws Exception {

		budgetRevisionToBeUpdated.setRevisionSeqId(revisionSeqId);

		UpdateBudgetRevision command = new UpdateBudgetRevision(budgetRevisionToBeUpdated);

		try {
			if(((BudgetRevisionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetRevisionId}")
	public ResponseEntity<BudgetRevision> findById(@PathVariable String budgetRevisionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetRevisionId", budgetRevisionId);
		try {

			List<BudgetRevision> foundBudgetRevision = findBudgetRevisionsBy(requestParams).getBody();
			if(foundBudgetRevision.size()==1){				return successful(foundBudgetRevision.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetRevisionId}")
	public ResponseEntity<String> deleteBudgetRevisionByIdUpdated(@PathVariable String budgetRevisionId) throws Exception {
		DeleteBudgetRevision command = new DeleteBudgetRevision(budgetRevisionId);

		try {
			if (((BudgetRevisionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
