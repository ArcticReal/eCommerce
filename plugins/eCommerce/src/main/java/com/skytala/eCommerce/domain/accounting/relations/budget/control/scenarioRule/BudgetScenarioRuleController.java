package com.skytala.eCommerce.domain.accounting.relations.budget.control.scenarioRule;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioRule.AddBudgetScenarioRule;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioRule.DeleteBudgetScenarioRule;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioRule.UpdateBudgetScenarioRule;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule.BudgetScenarioRuleAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule.BudgetScenarioRuleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule.BudgetScenarioRuleFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule.BudgetScenarioRuleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenarioRule.BudgetScenarioRuleMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioRule.BudgetScenarioRule;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.scenarioRule.FindBudgetScenarioRulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetScenarioRules")
public class BudgetScenarioRuleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetScenarioRuleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetScenarioRule
	 * @return a List with the BudgetScenarioRules
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetScenarioRule>> findBudgetScenarioRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetScenarioRulesBy query = new FindBudgetScenarioRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetScenarioRule> budgetScenarioRules =((BudgetScenarioRuleFound) Scheduler.execute(query).data()).getBudgetScenarioRules();

		return ResponseEntity.ok().body(budgetScenarioRules);

	}

	/**
	 * creates a new BudgetScenarioRule entry in the ofbiz database
	 * 
	 * @param budgetScenarioRuleToBeAdded
	 *            the BudgetScenarioRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetScenarioRule> createBudgetScenarioRule(@RequestBody BudgetScenarioRule budgetScenarioRuleToBeAdded) throws Exception {

		AddBudgetScenarioRule command = new AddBudgetScenarioRule(budgetScenarioRuleToBeAdded);
		BudgetScenarioRule budgetScenarioRule = ((BudgetScenarioRuleAdded) Scheduler.execute(command).data()).getAddedBudgetScenarioRule();
		
		if (budgetScenarioRule != null) 
			return successful(budgetScenarioRule);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetScenarioRule with the specific Id
	 * 
	 * @param budgetScenarioRuleToBeUpdated
	 *            the BudgetScenarioRule thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetScenarioRule(@RequestBody BudgetScenarioRule budgetScenarioRuleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetScenarioRuleToBeUpdated.setnull(null);

		UpdateBudgetScenarioRule command = new UpdateBudgetScenarioRule(budgetScenarioRuleToBeUpdated);

		try {
			if(((BudgetScenarioRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetScenarioRuleId}")
	public ResponseEntity<BudgetScenarioRule> findById(@PathVariable String budgetScenarioRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetScenarioRuleId", budgetScenarioRuleId);
		try {

			List<BudgetScenarioRule> foundBudgetScenarioRule = findBudgetScenarioRulesBy(requestParams).getBody();
			if(foundBudgetScenarioRule.size()==1){				return successful(foundBudgetScenarioRule.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetScenarioRuleId}")
	public ResponseEntity<String> deleteBudgetScenarioRuleByIdUpdated(@PathVariable String budgetScenarioRuleId) throws Exception {
		DeleteBudgetScenarioRule command = new DeleteBudgetScenarioRule(budgetScenarioRuleId);

		try {
			if (((BudgetScenarioRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
