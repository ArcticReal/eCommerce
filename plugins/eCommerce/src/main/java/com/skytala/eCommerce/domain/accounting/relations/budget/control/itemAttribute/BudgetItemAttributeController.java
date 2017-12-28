package com.skytala.eCommerce.domain.accounting.relations.budget.control.itemAttribute;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute.AddBudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute.DeleteBudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute.UpdateBudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemAttribute.BudgetItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemAttribute.BudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.itemAttribute.FindBudgetItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetItemAttributes")
public class BudgetItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItemAttribute
	 * @return a List with the BudgetItemAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetItemAttribute>> findBudgetItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemAttributesBy query = new FindBudgetItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItemAttribute> budgetItemAttributes =((BudgetItemAttributeFound) Scheduler.execute(query).data()).getBudgetItemAttributes();

		return ResponseEntity.ok().body(budgetItemAttributes);

	}

	/**
	 * creates a new BudgetItemAttribute entry in the ofbiz database
	 * 
	 * @param budgetItemAttributeToBeAdded
	 *            the BudgetItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetItemAttribute> createBudgetItemAttribute(@RequestBody BudgetItemAttribute budgetItemAttributeToBeAdded) throws Exception {

		AddBudgetItemAttribute command = new AddBudgetItemAttribute(budgetItemAttributeToBeAdded);
		BudgetItemAttribute budgetItemAttribute = ((BudgetItemAttributeAdded) Scheduler.execute(command).data()).getAddedBudgetItemAttribute();
		
		if (budgetItemAttribute != null) 
			return successful(budgetItemAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetItemAttribute with the specific Id
	 * 
	 * @param budgetItemAttributeToBeUpdated
	 *            the BudgetItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetItemAttribute(@RequestBody BudgetItemAttribute budgetItemAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetItemAttributeToBeUpdated.setnull(null);

		UpdateBudgetItemAttribute command = new UpdateBudgetItemAttribute(budgetItemAttributeToBeUpdated);

		try {
			if(((BudgetItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetItemAttributeId}")
	public ResponseEntity<BudgetItemAttribute> findById(@PathVariable String budgetItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemAttributeId", budgetItemAttributeId);
		try {

			List<BudgetItemAttribute> foundBudgetItemAttribute = findBudgetItemAttributesBy(requestParams).getBody();
			if(foundBudgetItemAttribute.size()==1){				return successful(foundBudgetItemAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetItemAttributeId}")
	public ResponseEntity<String> deleteBudgetItemAttributeByIdUpdated(@PathVariable String budgetItemAttributeId) throws Exception {
		DeleteBudgetItemAttribute command = new DeleteBudgetItemAttribute(budgetItemAttributeId);

		try {
			if (((BudgetItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
