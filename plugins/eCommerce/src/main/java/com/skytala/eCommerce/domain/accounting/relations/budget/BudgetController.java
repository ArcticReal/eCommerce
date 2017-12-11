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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findBudgetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetsBy query = new FindBudgetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Budget> budgets =((BudgetFound) Scheduler.execute(query).data()).getBudgets();

		if (budgets.size() == 1) {
			return ResponseEntity.ok().body(budgets.get(0));
		}

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
	public ResponseEntity<Object> createBudget(HttpServletRequest request) throws Exception {

		Budget budgetToBeAdded = new Budget();
		try {
			budgetToBeAdded = BudgetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createBudget(@RequestBody Budget budgetToBeAdded) throws Exception {

		AddBudget command = new AddBudget(budgetToBeAdded);
		Budget budget = ((BudgetAdded) Scheduler.execute(command).data()).getAddedBudget();
		
		if (budget != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budget);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Budget could not be created.");
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
	public boolean updateBudget(HttpServletRequest request) throws Exception {

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

		Budget budgetToBeUpdated = new Budget();

		try {
			budgetToBeUpdated = BudgetMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudget(budgetToBeUpdated, budgetToBeUpdated.getBudgetId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBudget(@RequestBody Budget budgetToBeUpdated,
			@PathVariable String budgetId) throws Exception {

		budgetToBeUpdated.setBudgetId(budgetId);

		UpdateBudget command = new UpdateBudget(budgetToBeUpdated);

		try {
			if(((BudgetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{budgetId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetId", budgetId);
		try {

			Object foundBudget = findBudgetsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudget);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{budgetId}")
	public ResponseEntity<Object> deleteBudgetByIdUpdated(@PathVariable String budgetId) throws Exception {
		DeleteBudget command = new DeleteBudget(budgetId);

		try {
			if (((BudgetDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Budget could not be deleted");

	}

}
