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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findBudgetScenarioRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetScenarioRulesBy query = new FindBudgetScenarioRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetScenarioRule> budgetScenarioRules =((BudgetScenarioRuleFound) Scheduler.execute(query).data()).getBudgetScenarioRules();

		if (budgetScenarioRules.size() == 1) {
			return ResponseEntity.ok().body(budgetScenarioRules.get(0));
		}

		return ResponseEntity.ok().body(budgetScenarioRules);

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
	public ResponseEntity<Object> createBudgetScenarioRule(HttpServletRequest request) throws Exception {

		BudgetScenarioRule budgetScenarioRuleToBeAdded = new BudgetScenarioRule();
		try {
			budgetScenarioRuleToBeAdded = BudgetScenarioRuleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetScenarioRule(budgetScenarioRuleToBeAdded);

	}

	/**
	 * creates a new BudgetScenarioRule entry in the ofbiz database
	 * 
	 * @param budgetScenarioRuleToBeAdded
	 *            the BudgetScenarioRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetScenarioRule(@RequestBody BudgetScenarioRule budgetScenarioRuleToBeAdded) throws Exception {

		AddBudgetScenarioRule command = new AddBudgetScenarioRule(budgetScenarioRuleToBeAdded);
		BudgetScenarioRule budgetScenarioRule = ((BudgetScenarioRuleAdded) Scheduler.execute(command).data()).getAddedBudgetScenarioRule();
		
		if (budgetScenarioRule != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetScenarioRule);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetScenarioRule could not be created.");
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
	public boolean updateBudgetScenarioRule(HttpServletRequest request) throws Exception {

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

		BudgetScenarioRule budgetScenarioRuleToBeUpdated = new BudgetScenarioRule();

		try {
			budgetScenarioRuleToBeUpdated = BudgetScenarioRuleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetScenarioRule(budgetScenarioRuleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBudgetScenarioRule(@RequestBody BudgetScenarioRule budgetScenarioRuleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetScenarioRuleToBeUpdated.setnull(null);

		UpdateBudgetScenarioRule command = new UpdateBudgetScenarioRule(budgetScenarioRuleToBeUpdated);

		try {
			if(((BudgetScenarioRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{budgetScenarioRuleId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetScenarioRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetScenarioRuleId", budgetScenarioRuleId);
		try {

			Object foundBudgetScenarioRule = findBudgetScenarioRulesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetScenarioRule);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{budgetScenarioRuleId}")
	public ResponseEntity<Object> deleteBudgetScenarioRuleByIdUpdated(@PathVariable String budgetScenarioRuleId) throws Exception {
		DeleteBudgetScenarioRule command = new DeleteBudgetScenarioRule(budgetScenarioRuleId);

		try {
			if (((BudgetScenarioRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetScenarioRule could not be deleted");

	}

}
