package com.skytala.eCommerce.domain.accounting.relations.budget.control.scenario;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenario.AddBudgetScenario;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenario.DeleteBudgetScenario;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.scenario.UpdateBudgetScenario;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario.BudgetScenarioAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario.BudgetScenarioDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario.BudgetScenarioFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario.BudgetScenarioUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenario.BudgetScenarioMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenario.BudgetScenario;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.scenario.FindBudgetScenariosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetScenarios")
public class BudgetScenarioController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetScenarioController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetScenario
	 * @return a List with the BudgetScenarios
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetScenario>> findBudgetScenariosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetScenariosBy query = new FindBudgetScenariosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetScenario> budgetScenarios =((BudgetScenarioFound) Scheduler.execute(query).data()).getBudgetScenarios();

		return ResponseEntity.ok().body(budgetScenarios);

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
	public ResponseEntity<BudgetScenario> createBudgetScenario(HttpServletRequest request) throws Exception {

		BudgetScenario budgetScenarioToBeAdded = new BudgetScenario();
		try {
			budgetScenarioToBeAdded = BudgetScenarioMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createBudgetScenario(budgetScenarioToBeAdded);

	}

	/**
	 * creates a new BudgetScenario entry in the ofbiz database
	 * 
	 * @param budgetScenarioToBeAdded
	 *            the BudgetScenario thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetScenario> createBudgetScenario(@RequestBody BudgetScenario budgetScenarioToBeAdded) throws Exception {

		AddBudgetScenario command = new AddBudgetScenario(budgetScenarioToBeAdded);
		BudgetScenario budgetScenario = ((BudgetScenarioAdded) Scheduler.execute(command).data()).getAddedBudgetScenario();
		
		if (budgetScenario != null) 
			return successful(budgetScenario);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetScenario with the specific Id
	 * 
	 * @param budgetScenarioToBeUpdated
	 *            the BudgetScenario thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetScenarioId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetScenario(@RequestBody BudgetScenario budgetScenarioToBeUpdated,
			@PathVariable String budgetScenarioId) throws Exception {

		budgetScenarioToBeUpdated.setBudgetScenarioId(budgetScenarioId);

		UpdateBudgetScenario command = new UpdateBudgetScenario(budgetScenarioToBeUpdated);

		try {
			if(((BudgetScenarioUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetScenarioId}")
	public ResponseEntity<BudgetScenario> findById(@PathVariable String budgetScenarioId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetScenarioId", budgetScenarioId);
		try {

			List<BudgetScenario> foundBudgetScenario = findBudgetScenariosBy(requestParams).getBody();
			if(foundBudgetScenario.size()==1){				return successful(foundBudgetScenario.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetScenarioId}")
	public ResponseEntity<String> deleteBudgetScenarioByIdUpdated(@PathVariable String budgetScenarioId) throws Exception {
		DeleteBudgetScenario command = new DeleteBudgetScenario(budgetScenarioId);

		try {
			if (((BudgetScenarioDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
