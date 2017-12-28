package com.skytala.eCommerce.domain.accounting.relations.budget.control.reviewResultType;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.reviewResultType.AddBudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.reviewResultType.DeleteBudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.reviewResultType.UpdateBudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType.BudgetReviewResultTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType.BudgetReviewResultTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType.BudgetReviewResultTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType.BudgetReviewResultTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.reviewResultType.BudgetReviewResultTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType.BudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.reviewResultType.FindBudgetReviewResultTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetReviewResultTypes")
public class BudgetReviewResultTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetReviewResultTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetReviewResultType
	 * @return a List with the BudgetReviewResultTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetReviewResultType>> findBudgetReviewResultTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetReviewResultTypesBy query = new FindBudgetReviewResultTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetReviewResultType> budgetReviewResultTypes =((BudgetReviewResultTypeFound) Scheduler.execute(query).data()).getBudgetReviewResultTypes();

		return ResponseEntity.ok().body(budgetReviewResultTypes);

	}

	/**
	 * creates a new BudgetReviewResultType entry in the ofbiz database
	 * 
	 * @param budgetReviewResultTypeToBeAdded
	 *            the BudgetReviewResultType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetReviewResultType> createBudgetReviewResultType(@RequestBody BudgetReviewResultType budgetReviewResultTypeToBeAdded) throws Exception {

		AddBudgetReviewResultType command = new AddBudgetReviewResultType(budgetReviewResultTypeToBeAdded);
		BudgetReviewResultType budgetReviewResultType = ((BudgetReviewResultTypeAdded) Scheduler.execute(command).data()).getAddedBudgetReviewResultType();
		
		if (budgetReviewResultType != null) 
			return successful(budgetReviewResultType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetReviewResultType with the specific Id
	 * 
	 * @param budgetReviewResultTypeToBeUpdated
	 *            the BudgetReviewResultType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetReviewResultTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetReviewResultType(@RequestBody BudgetReviewResultType budgetReviewResultTypeToBeUpdated,
			@PathVariable String budgetReviewResultTypeId) throws Exception {

		budgetReviewResultTypeToBeUpdated.setBudgetReviewResultTypeId(budgetReviewResultTypeId);

		UpdateBudgetReviewResultType command = new UpdateBudgetReviewResultType(budgetReviewResultTypeToBeUpdated);

		try {
			if(((BudgetReviewResultTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetReviewResultTypeId}")
	public ResponseEntity<BudgetReviewResultType> findById(@PathVariable String budgetReviewResultTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetReviewResultTypeId", budgetReviewResultTypeId);
		try {

			List<BudgetReviewResultType> foundBudgetReviewResultType = findBudgetReviewResultTypesBy(requestParams).getBody();
			if(foundBudgetReviewResultType.size()==1){				return successful(foundBudgetReviewResultType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetReviewResultTypeId}")
	public ResponseEntity<String> deleteBudgetReviewResultTypeByIdUpdated(@PathVariable String budgetReviewResultTypeId) throws Exception {
		DeleteBudgetReviewResultType command = new DeleteBudgetReviewResultType(budgetReviewResultTypeId);

		try {
			if (((BudgetReviewResultTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
