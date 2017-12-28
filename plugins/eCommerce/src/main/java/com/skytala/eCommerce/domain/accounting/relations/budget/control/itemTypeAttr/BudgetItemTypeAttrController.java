package com.skytala.eCommerce.domain.accounting.relations.budget.control.itemTypeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemTypeAttr.AddBudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemTypeAttr.DeleteBudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemTypeAttr.UpdateBudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr.BudgetItemTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr.BudgetItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr.BudgetItemTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemTypeAttr.BudgetItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemTypeAttr.BudgetItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemTypeAttr.BudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.itemTypeAttr.FindBudgetItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetItemTypeAttrs")
public class BudgetItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItemTypeAttr
	 * @return a List with the BudgetItemTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetItemTypeAttr>> findBudgetItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemTypeAttrsBy query = new FindBudgetItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItemTypeAttr> budgetItemTypeAttrs =((BudgetItemTypeAttrFound) Scheduler.execute(query).data()).getBudgetItemTypeAttrs();

		return ResponseEntity.ok().body(budgetItemTypeAttrs);

	}

	/**
	 * creates a new BudgetItemTypeAttr entry in the ofbiz database
	 * 
	 * @param budgetItemTypeAttrToBeAdded
	 *            the BudgetItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetItemTypeAttr> createBudgetItemTypeAttr(@RequestBody BudgetItemTypeAttr budgetItemTypeAttrToBeAdded) throws Exception {

		AddBudgetItemTypeAttr command = new AddBudgetItemTypeAttr(budgetItemTypeAttrToBeAdded);
		BudgetItemTypeAttr budgetItemTypeAttr = ((BudgetItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedBudgetItemTypeAttr();
		
		if (budgetItemTypeAttr != null) 
			return successful(budgetItemTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetItemTypeAttr with the specific Id
	 * 
	 * @param budgetItemTypeAttrToBeUpdated
	 *            the BudgetItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetItemTypeAttr(@RequestBody BudgetItemTypeAttr budgetItemTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		budgetItemTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateBudgetItemTypeAttr command = new UpdateBudgetItemTypeAttr(budgetItemTypeAttrToBeUpdated);

		try {
			if(((BudgetItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetItemTypeAttrId}")
	public ResponseEntity<BudgetItemTypeAttr> findById(@PathVariable String budgetItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemTypeAttrId", budgetItemTypeAttrId);
		try {

			List<BudgetItemTypeAttr> foundBudgetItemTypeAttr = findBudgetItemTypeAttrsBy(requestParams).getBody();
			if(foundBudgetItemTypeAttr.size()==1){				return successful(foundBudgetItemTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetItemTypeAttrId}")
	public ResponseEntity<String> deleteBudgetItemTypeAttrByIdUpdated(@PathVariable String budgetItemTypeAttrId) throws Exception {
		DeleteBudgetItemTypeAttr command = new DeleteBudgetItemTypeAttr(budgetItemTypeAttrId);

		try {
			if (((BudgetItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
