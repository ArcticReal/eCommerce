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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/budgetScenarios")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetScenariosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetScenariosBy query = new FindBudgetScenariosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetScenario> budgetScenarios =((BudgetScenarioFound) Scheduler.execute(query).data()).getBudgetScenarios();

		if (budgetScenarios.size() == 1) {
			return ResponseEntity.ok().body(budgetScenarios.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createBudgetScenario(HttpServletRequest request) throws Exception {

		BudgetScenario budgetScenarioToBeAdded = new BudgetScenario();
		try {
			budgetScenarioToBeAdded = BudgetScenarioMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createBudgetScenario(@RequestBody BudgetScenario budgetScenarioToBeAdded) throws Exception {

		AddBudgetScenario command = new AddBudgetScenario(budgetScenarioToBeAdded);
		BudgetScenario budgetScenario = ((BudgetScenarioAdded) Scheduler.execute(command).data()).getAddedBudgetScenario();
		
		if (budgetScenario != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetScenario);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetScenario could not be created.");
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
	public boolean updateBudgetScenario(HttpServletRequest request) throws Exception {

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

		BudgetScenario budgetScenarioToBeUpdated = new BudgetScenario();

		try {
			budgetScenarioToBeUpdated = BudgetScenarioMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetScenario(budgetScenarioToBeUpdated, budgetScenarioToBeUpdated.getBudgetScenarioId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBudgetScenario(@RequestBody BudgetScenario budgetScenarioToBeUpdated,
			@PathVariable String budgetScenarioId) throws Exception {

		budgetScenarioToBeUpdated.setBudgetScenarioId(budgetScenarioId);

		UpdateBudgetScenario command = new UpdateBudgetScenario(budgetScenarioToBeUpdated);

		try {
			if(((BudgetScenarioUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetScenarioId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetScenarioId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetScenarioId", budgetScenarioId);
		try {

			Object foundBudgetScenario = findBudgetScenariosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetScenario);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetScenarioId}")
	public ResponseEntity<Object> deleteBudgetScenarioByIdUpdated(@PathVariable String budgetScenarioId) throws Exception {
		DeleteBudgetScenario command = new DeleteBudgetScenario(budgetScenarioId);

		try {
			if (((BudgetScenarioDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetScenario could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetScenario/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
