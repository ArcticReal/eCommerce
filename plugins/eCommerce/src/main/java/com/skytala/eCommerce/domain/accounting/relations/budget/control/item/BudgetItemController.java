package com.skytala.eCommerce.domain.accounting.relations.budget.control.item;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.item.AddBudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.item.DeleteBudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.item.UpdateBudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.item.BudgetItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.item.FindBudgetItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetItems")
public class BudgetItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItem
	 * @return a List with the BudgetItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetItem>> findBudgetItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemsBy query = new FindBudgetItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItem> budgetItems =((BudgetItemFound) Scheduler.execute(query).data()).getBudgetItems();

		return ResponseEntity.ok().body(budgetItems);

	}

	/**
	 * creates a new BudgetItem entry in the ofbiz database
	 * 
	 * @param budgetItemToBeAdded
	 *            the BudgetItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetItem> createBudgetItem(@RequestBody BudgetItem budgetItemToBeAdded) throws Exception {

		AddBudgetItem command = new AddBudgetItem(budgetItemToBeAdded);
		BudgetItem budgetItem = ((BudgetItemAdded) Scheduler.execute(command).data()).getAddedBudgetItem();
		
		if (budgetItem != null) 
			return successful(budgetItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetItem with the specific Id
	 * 
	 * @param budgetItemToBeUpdated
	 *            the BudgetItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetItem(@RequestBody BudgetItem budgetItemToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		budgetItemToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdateBudgetItem command = new UpdateBudgetItem(budgetItemToBeUpdated);

		try {
			if(((BudgetItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetItemId}")
	public ResponseEntity<BudgetItem> findById(@PathVariable String budgetItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemId", budgetItemId);
		try {

			List<BudgetItem> foundBudgetItem = findBudgetItemsBy(requestParams).getBody();
			if(foundBudgetItem.size()==1){				return successful(foundBudgetItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetItemId}")
	public ResponseEntity<String> deleteBudgetItemByIdUpdated(@PathVariable String budgetItemId) throws Exception {
		DeleteBudgetItem command = new DeleteBudgetItem(budgetItemId);

		try {
			if (((BudgetItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
