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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetRevisionImpacts")
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
	@GetMapping("/find")
	public ResponseEntity<List<BudgetRevisionImpact>> findBudgetRevisionImpactsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetRevisionImpactsBy query = new FindBudgetRevisionImpactsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetRevisionImpact> budgetRevisionImpacts =((BudgetRevisionImpactFound) Scheduler.execute(query).data()).getBudgetRevisionImpacts();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<BudgetRevisionImpact> createBudgetRevisionImpact(HttpServletRequest request) throws Exception {

		BudgetRevisionImpact budgetRevisionImpactToBeAdded = new BudgetRevisionImpact();
		try {
			budgetRevisionImpactToBeAdded = BudgetRevisionImpactMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<BudgetRevisionImpact> createBudgetRevisionImpact(@RequestBody BudgetRevisionImpact budgetRevisionImpactToBeAdded) throws Exception {

		AddBudgetRevisionImpact command = new AddBudgetRevisionImpact(budgetRevisionImpactToBeAdded);
		BudgetRevisionImpact budgetRevisionImpact = ((BudgetRevisionImpactAdded) Scheduler.execute(command).data()).getAddedBudgetRevisionImpact();
		
		if (budgetRevisionImpact != null) 
			return successful(budgetRevisionImpact);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateBudgetRevisionImpact(@RequestBody BudgetRevisionImpact budgetRevisionImpactToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetRevisionImpactToBeUpdated.setnull(null);

		UpdateBudgetRevisionImpact command = new UpdateBudgetRevisionImpact(budgetRevisionImpactToBeUpdated);

		try {
			if(((BudgetRevisionImpactUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetRevisionImpactId}")
	public ResponseEntity<BudgetRevisionImpact> findById(@PathVariable String budgetRevisionImpactId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetRevisionImpactId", budgetRevisionImpactId);
		try {

			List<BudgetRevisionImpact> foundBudgetRevisionImpact = findBudgetRevisionImpactsBy(requestParams).getBody();
			if(foundBudgetRevisionImpact.size()==1){				return successful(foundBudgetRevisionImpact.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetRevisionImpactId}")
	public ResponseEntity<String> deleteBudgetRevisionImpactByIdUpdated(@PathVariable String budgetRevisionImpactId) throws Exception {
		DeleteBudgetRevisionImpact command = new DeleteBudgetRevisionImpact(budgetRevisionImpactId);

		try {
			if (((BudgetRevisionImpactDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
