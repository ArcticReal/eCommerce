package com.skytala.eCommerce.domain.accounting.relations.budget.control.status;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.status.AddBudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.status.DeleteBudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.status.UpdateBudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.status.BudgetStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.status.BudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.status.FindBudgetStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetStatuss")
public class BudgetStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetStatus
	 * @return a List with the BudgetStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetStatus>> findBudgetStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetStatussBy query = new FindBudgetStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetStatus> budgetStatuss =((BudgetStatusFound) Scheduler.execute(query).data()).getBudgetStatuss();

		return ResponseEntity.ok().body(budgetStatuss);

	}

	/**
	 * creates a new BudgetStatus entry in the ofbiz database
	 * 
	 * @param budgetStatusToBeAdded
	 *            the BudgetStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetStatus> createBudgetStatus(@RequestBody BudgetStatus budgetStatusToBeAdded) throws Exception {

		AddBudgetStatus command = new AddBudgetStatus(budgetStatusToBeAdded);
		BudgetStatus budgetStatus = ((BudgetStatusAdded) Scheduler.execute(command).data()).getAddedBudgetStatus();
		
		if (budgetStatus != null) 
			return successful(budgetStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetStatus with the specific Id
	 * 
	 * @param budgetStatusToBeUpdated
	 *            the BudgetStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetStatus(@RequestBody BudgetStatus budgetStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetStatusToBeUpdated.setnull(null);

		UpdateBudgetStatus command = new UpdateBudgetStatus(budgetStatusToBeUpdated);

		try {
			if(((BudgetStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetStatusId}")
	public ResponseEntity<BudgetStatus> findById(@PathVariable String budgetStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetStatusId", budgetStatusId);
		try {

			List<BudgetStatus> foundBudgetStatus = findBudgetStatussBy(requestParams).getBody();
			if(foundBudgetStatus.size()==1){				return successful(foundBudgetStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetStatusId}")
	public ResponseEntity<String> deleteBudgetStatusByIdUpdated(@PathVariable String budgetStatusId) throws Exception {
		DeleteBudgetStatus command = new DeleteBudgetStatus(budgetStatusId);

		try {
			if (((BudgetStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
