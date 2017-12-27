package com.skytala.eCommerce.domain.accounting.relations.budget;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.AddBudget;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.DeleteBudget;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.UpdateBudget;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.BudgetAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.BudgetDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.BudgetFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.BudgetUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.BudgetMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.Budget;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.FindBudgetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budgets")
public class BudgetController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Budget
	 * @return a List with the Budgets
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Budget>> findBudgetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetsBy query = new FindBudgetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Budget> budgets =((BudgetFound) Scheduler.execute(query).data()).getBudgets();

		return ResponseEntity.ok().body(budgets);

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
	public ResponseEntity<Budget> createBudget(HttpServletRequest request) throws Exception {

		Budget budgetToBeAdded = new Budget();
		try {
			budgetToBeAdded = BudgetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createBudget(budgetToBeAdded);

	}

	/**
	 * creates a new Budget entry in the ofbiz database
	 * 
	 * @param budgetToBeAdded
	 *            the Budget thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Budget> createBudget(@RequestBody Budget budgetToBeAdded) throws Exception {

		AddBudget command = new AddBudget(budgetToBeAdded);
		Budget budget = ((BudgetAdded) Scheduler.execute(command).data()).getAddedBudget();
		
		if (budget != null) 
			return successful(budget);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Budget with the specific Id
	 * 
	 * @param budgetToBeUpdated
	 *            the Budget thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudget(@RequestBody Budget budgetToBeUpdated,
			@PathVariable String budgetId) throws Exception {

		budgetToBeUpdated.setBudgetId(budgetId);

		UpdateBudget command = new UpdateBudget(budgetToBeUpdated);

		try {
			if(((BudgetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetId}")
	public ResponseEntity<Budget> findById(@PathVariable String budgetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetId", budgetId);
		try {

			List<Budget> foundBudget = findBudgetsBy(requestParams).getBody();
			if(foundBudget.size()==1){				return successful(foundBudget.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetId}")
	public ResponseEntity<String> deleteBudgetByIdUpdated(@PathVariable String budgetId) throws Exception {
		DeleteBudget command = new DeleteBudget(budgetId);

		try {
			if (((BudgetDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
