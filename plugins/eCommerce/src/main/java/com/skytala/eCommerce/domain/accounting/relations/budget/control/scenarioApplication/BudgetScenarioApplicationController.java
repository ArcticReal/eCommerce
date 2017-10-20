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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/budgetScenarioApplications")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetScenarioApplicationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetScenarioApplicationsBy query = new FindBudgetScenarioApplicationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetScenarioApplication> budgetScenarioApplications =((BudgetScenarioApplicationFound) Scheduler.execute(query).data()).getBudgetScenarioApplications();

		if (budgetScenarioApplications.size() == 1) {
			return ResponseEntity.ok().body(budgetScenarioApplications.get(0));
		}

		return ResponseEntity.ok().body(budgetScenarioApplications);

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
	public ResponseEntity<Object> createBudgetScenarioApplication(HttpServletRequest request) throws Exception {

		BudgetScenarioApplication budgetScenarioApplicationToBeAdded = new BudgetScenarioApplication();
		try {
			budgetScenarioApplicationToBeAdded = BudgetScenarioApplicationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetScenarioApplication(budgetScenarioApplicationToBeAdded);

	}

	/**
	 * creates a new BudgetScenarioApplication entry in the ofbiz database
	 * 
	 * @param budgetScenarioApplicationToBeAdded
	 *            the BudgetScenarioApplication thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetScenarioApplication(@RequestBody BudgetScenarioApplication budgetScenarioApplicationToBeAdded) throws Exception {

		AddBudgetScenarioApplication command = new AddBudgetScenarioApplication(budgetScenarioApplicationToBeAdded);
		BudgetScenarioApplication budgetScenarioApplication = ((BudgetScenarioApplicationAdded) Scheduler.execute(command).data()).getAddedBudgetScenarioApplication();
		
		if (budgetScenarioApplication != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetScenarioApplication);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetScenarioApplication could not be created.");
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
	public boolean updateBudgetScenarioApplication(HttpServletRequest request) throws Exception {

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

		BudgetScenarioApplication budgetScenarioApplicationToBeUpdated = new BudgetScenarioApplication();

		try {
			budgetScenarioApplicationToBeUpdated = BudgetScenarioApplicationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetScenarioApplication(budgetScenarioApplicationToBeUpdated, budgetScenarioApplicationToBeUpdated.getBudgetScenarioApplicId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBudgetScenarioApplication(@RequestBody BudgetScenarioApplication budgetScenarioApplicationToBeUpdated,
			@PathVariable String budgetScenarioApplicId) throws Exception {

		budgetScenarioApplicationToBeUpdated.setBudgetScenarioApplicId(budgetScenarioApplicId);

		UpdateBudgetScenarioApplication command = new UpdateBudgetScenarioApplication(budgetScenarioApplicationToBeUpdated);

		try {
			if(((BudgetScenarioApplicationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetScenarioApplicationId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetScenarioApplicationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetScenarioApplicationId", budgetScenarioApplicationId);
		try {

			Object foundBudgetScenarioApplication = findBudgetScenarioApplicationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetScenarioApplication);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetScenarioApplicationId}")
	public ResponseEntity<Object> deleteBudgetScenarioApplicationByIdUpdated(@PathVariable String budgetScenarioApplicationId) throws Exception {
		DeleteBudgetScenarioApplication command = new DeleteBudgetScenarioApplication(budgetScenarioApplicationId);

		try {
			if (((BudgetScenarioApplicationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetScenarioApplication could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetScenarioApplication/\" plus one of the following: "
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
