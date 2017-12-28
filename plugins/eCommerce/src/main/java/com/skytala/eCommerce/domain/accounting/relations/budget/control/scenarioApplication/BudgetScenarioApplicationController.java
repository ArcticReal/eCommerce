package com.skytala.eCommerce.domain.accounting.relations.budget.control.scenarioApplication;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioApplication.AddBudgetScenarioApplication;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioApplication.DeleteBudgetScenarioApplication;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioApplication.UpdateBudgetScenarioApplication;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication.BudgetScenarioApplicationAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication.BudgetScenarioApplicationDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication.BudgetScenarioApplicationFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication.BudgetScenarioApplicationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenarioApplication.BudgetScenarioApplicationMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication.BudgetScenarioApplication;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.scenarioApplication.FindBudgetScenarioApplicationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetScenarioApplications")
public class BudgetScenarioApplicationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetScenarioApplicationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetScenarioApplication
	 * @return a List with the BudgetScenarioApplications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetScenarioApplication>> findBudgetScenarioApplicationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetScenarioApplicationsBy query = new FindBudgetScenarioApplicationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetScenarioApplication> budgetScenarioApplications =((BudgetScenarioApplicationFound) Scheduler.execute(query).data()).getBudgetScenarioApplications();

		return ResponseEntity.ok().body(budgetScenarioApplications);

	}

	/**
	 * creates a new BudgetScenarioApplication entry in the ofbiz database
	 * 
	 * @param budgetScenarioApplicationToBeAdded
	 *            the BudgetScenarioApplication thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetScenarioApplication> createBudgetScenarioApplication(@RequestBody BudgetScenarioApplication budgetScenarioApplicationToBeAdded) throws Exception {

		AddBudgetScenarioApplication command = new AddBudgetScenarioApplication(budgetScenarioApplicationToBeAdded);
		BudgetScenarioApplication budgetScenarioApplication = ((BudgetScenarioApplicationAdded) Scheduler.execute(command).data()).getAddedBudgetScenarioApplication();
		
		if (budgetScenarioApplication != null) 
			return successful(budgetScenarioApplication);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetScenarioApplication with the specific Id
	 * 
	 * @param budgetScenarioApplicationToBeUpdated
	 *            the BudgetScenarioApplication thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetScenarioApplicId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetScenarioApplication(@RequestBody BudgetScenarioApplication budgetScenarioApplicationToBeUpdated,
			@PathVariable String budgetScenarioApplicId) throws Exception {

		budgetScenarioApplicationToBeUpdated.setBudgetScenarioApplicId(budgetScenarioApplicId);

		UpdateBudgetScenarioApplication command = new UpdateBudgetScenarioApplication(budgetScenarioApplicationToBeUpdated);

		try {
			if(((BudgetScenarioApplicationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetScenarioApplicationId}")
	public ResponseEntity<BudgetScenarioApplication> findById(@PathVariable String budgetScenarioApplicationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetScenarioApplicationId", budgetScenarioApplicationId);
		try {

			List<BudgetScenarioApplication> foundBudgetScenarioApplication = findBudgetScenarioApplicationsBy(requestParams).getBody();
			if(foundBudgetScenarioApplication.size()==1){				return successful(foundBudgetScenarioApplication.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetScenarioApplicationId}")
	public ResponseEntity<String> deleteBudgetScenarioApplicationByIdUpdated(@PathVariable String budgetScenarioApplicationId) throws Exception {
		DeleteBudgetScenarioApplication command = new DeleteBudgetScenarioApplication(budgetScenarioApplicationId);

		try {
			if (((BudgetScenarioApplicationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
