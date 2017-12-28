package com.skytala.eCommerce.domain.accounting.relations.budget.control.itemType;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemType.AddBudgetItemType;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemType.DeleteBudgetItemType;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemType.UpdateBudgetItemType;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemType.BudgetItemTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemType.BudgetItemTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemType.BudgetItemTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemType.BudgetItemTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemType.BudgetItemTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemType.BudgetItemType;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.itemType.FindBudgetItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetItemTypes")
public class BudgetItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItemType
	 * @return a List with the BudgetItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetItemType>> findBudgetItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemTypesBy query = new FindBudgetItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItemType> budgetItemTypes =((BudgetItemTypeFound) Scheduler.execute(query).data()).getBudgetItemTypes();

		return ResponseEntity.ok().body(budgetItemTypes);

	}

	/**
	 * creates a new BudgetItemType entry in the ofbiz database
	 * 
	 * @param budgetItemTypeToBeAdded
	 *            the BudgetItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetItemType> createBudgetItemType(@RequestBody BudgetItemType budgetItemTypeToBeAdded) throws Exception {

		AddBudgetItemType command = new AddBudgetItemType(budgetItemTypeToBeAdded);
		BudgetItemType budgetItemType = ((BudgetItemTypeAdded) Scheduler.execute(command).data()).getAddedBudgetItemType();
		
		if (budgetItemType != null) 
			return successful(budgetItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetItemType with the specific Id
	 * 
	 * @param budgetItemTypeToBeUpdated
	 *            the BudgetItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetItemType(@RequestBody BudgetItemType budgetItemTypeToBeUpdated,
			@PathVariable String budgetItemTypeId) throws Exception {

		budgetItemTypeToBeUpdated.setBudgetItemTypeId(budgetItemTypeId);

		UpdateBudgetItemType command = new UpdateBudgetItemType(budgetItemTypeToBeUpdated);

		try {
			if(((BudgetItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetItemTypeId}")
	public ResponseEntity<BudgetItemType> findById(@PathVariable String budgetItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemTypeId", budgetItemTypeId);
		try {

			List<BudgetItemType> foundBudgetItemType = findBudgetItemTypesBy(requestParams).getBody();
			if(foundBudgetItemType.size()==1){				return successful(foundBudgetItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetItemTypeId}")
	public ResponseEntity<String> deleteBudgetItemTypeByIdUpdated(@PathVariable String budgetItemTypeId) throws Exception {
		DeleteBudgetItemType command = new DeleteBudgetItemType(budgetItemTypeId);

		try {
			if (((BudgetItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
