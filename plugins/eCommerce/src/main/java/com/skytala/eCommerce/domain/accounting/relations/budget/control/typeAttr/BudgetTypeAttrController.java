package com.skytala.eCommerce.domain.accounting.relations.budget.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr.AddBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr.DeleteBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr.UpdateBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.typeAttr.BudgetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.typeAttr.BudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.typeAttr.FindBudgetTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetTypeAttrs")
public class BudgetTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetTypeAttr
	 * @return a List with the BudgetTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetTypeAttr>> findBudgetTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetTypeAttrsBy query = new FindBudgetTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetTypeAttr> budgetTypeAttrs =((BudgetTypeAttrFound) Scheduler.execute(query).data()).getBudgetTypeAttrs();

		return ResponseEntity.ok().body(budgetTypeAttrs);

	}

	/**
	 * creates a new BudgetTypeAttr entry in the ofbiz database
	 * 
	 * @param budgetTypeAttrToBeAdded
	 *            the BudgetTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetTypeAttr> createBudgetTypeAttr(@RequestBody BudgetTypeAttr budgetTypeAttrToBeAdded) throws Exception {

		AddBudgetTypeAttr command = new AddBudgetTypeAttr(budgetTypeAttrToBeAdded);
		BudgetTypeAttr budgetTypeAttr = ((BudgetTypeAttrAdded) Scheduler.execute(command).data()).getAddedBudgetTypeAttr();
		
		if (budgetTypeAttr != null) 
			return successful(budgetTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetTypeAttr with the specific Id
	 * 
	 * @param budgetTypeAttrToBeUpdated
	 *            the BudgetTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetTypeAttr(@RequestBody BudgetTypeAttr budgetTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		budgetTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateBudgetTypeAttr command = new UpdateBudgetTypeAttr(budgetTypeAttrToBeUpdated);

		try {
			if(((BudgetTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetTypeAttrId}")
	public ResponseEntity<BudgetTypeAttr> findById(@PathVariable String budgetTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetTypeAttrId", budgetTypeAttrId);
		try {

			List<BudgetTypeAttr> foundBudgetTypeAttr = findBudgetTypeAttrsBy(requestParams).getBody();
			if(foundBudgetTypeAttr.size()==1){				return successful(foundBudgetTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetTypeAttrId}")
	public ResponseEntity<String> deleteBudgetTypeAttrByIdUpdated(@PathVariable String budgetTypeAttrId) throws Exception {
		DeleteBudgetTypeAttr command = new DeleteBudgetTypeAttr(budgetTypeAttrId);

		try {
			if (((BudgetTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
