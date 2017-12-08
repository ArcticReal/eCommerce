package com.skytala.eCommerce.domain.accounting.relations.budget.control.revisionImpact;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.revisionImpact.AddBudgetRevisionImpact;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.revisionImpact.DeleteBudgetRevisionImpact;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.revisionImpact.UpdateBudgetRevisionImpact;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact.BudgetRevisionImpactAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact.BudgetRevisionImpactDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact.BudgetRevisionImpactFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact.BudgetRevisionImpactUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.revisionImpact.BudgetRevisionImpactMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.revisionImpact.BudgetRevisionImpact;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.revisionImpact.FindBudgetRevisionImpactsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetRevisionImpacts")
public class BudgetRevisionImpactController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetRevisionImpactController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetRevisionImpact
	 * @return a List with the BudgetRevisionImpacts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetRevisionImpactsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetRevisionImpactsBy query = new FindBudgetRevisionImpactsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetRevisionImpact> budgetRevisionImpacts =((BudgetRevisionImpactFound) Scheduler.execute(query).data()).getBudgetRevisionImpacts();

		if (budgetRevisionImpacts.size() == 1) {
			return ResponseEntity.ok().body(budgetRevisionImpacts.get(0));
		}

		return ResponseEntity.ok().body(budgetRevisionImpacts);

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
	public ResponseEntity<Object> createBudgetRevisionImpact(HttpServletRequest request) throws Exception {

		BudgetRevisionImpact budgetRevisionImpactToBeAdded = new BudgetRevisionImpact();
		try {
			budgetRevisionImpactToBeAdded = BudgetRevisionImpactMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetRevisionImpact(budgetRevisionImpactToBeAdded);

	}

	/**
	 * creates a new BudgetRevisionImpact entry in the ofbiz database
	 * 
	 * @param budgetRevisionImpactToBeAdded
	 *            the BudgetRevisionImpact thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetRevisionImpact(@RequestBody BudgetRevisionImpact budgetRevisionImpactToBeAdded) throws Exception {

		AddBudgetRevisionImpact command = new AddBudgetRevisionImpact(budgetRevisionImpactToBeAdded);
		BudgetRevisionImpact budgetRevisionImpact = ((BudgetRevisionImpactAdded) Scheduler.execute(command).data()).getAddedBudgetRevisionImpact();
		
		if (budgetRevisionImpact != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetRevisionImpact);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetRevisionImpact could not be created.");
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
	public boolean updateBudgetRevisionImpact(HttpServletRequest request) throws Exception {

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

		BudgetRevisionImpact budgetRevisionImpactToBeUpdated = new BudgetRevisionImpact();

		try {
			budgetRevisionImpactToBeUpdated = BudgetRevisionImpactMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetRevisionImpact(budgetRevisionImpactToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetRevisionImpact with the specific Id
	 * 
	 * @param budgetRevisionImpactToBeUpdated
	 *            the BudgetRevisionImpact thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetRevisionImpact(@RequestBody BudgetRevisionImpact budgetRevisionImpactToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetRevisionImpactToBeUpdated.setnull(null);

		UpdateBudgetRevisionImpact command = new UpdateBudgetRevisionImpact(budgetRevisionImpactToBeUpdated);

		try {
			if(((BudgetRevisionImpactUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetRevisionImpactId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetRevisionImpactId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetRevisionImpactId", budgetRevisionImpactId);
		try {

			Object foundBudgetRevisionImpact = findBudgetRevisionImpactsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetRevisionImpact);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetRevisionImpactId}")
	public ResponseEntity<Object> deleteBudgetRevisionImpactByIdUpdated(@PathVariable String budgetRevisionImpactId) throws Exception {
		DeleteBudgetRevisionImpact command = new DeleteBudgetRevisionImpact(budgetRevisionImpactId);

		try {
			if (((BudgetRevisionImpactDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetRevisionImpact could not be deleted");

	}

}
