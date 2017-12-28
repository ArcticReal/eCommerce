package com.skytala.eCommerce.domain.accounting.relations.budget.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.attribute.AddBudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.attribute.DeleteBudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.attribute.UpdateBudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.attribute.BudgetAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.attribute.FindBudgetAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetAttributes")
public class BudgetAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetAttribute
	 * @return a List with the BudgetAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetAttribute>> findBudgetAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetAttributesBy query = new FindBudgetAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetAttribute> budgetAttributes =((BudgetAttributeFound) Scheduler.execute(query).data()).getBudgetAttributes();

		return ResponseEntity.ok().body(budgetAttributes);

	}

	/**
	 * creates a new BudgetAttribute entry in the ofbiz database
	 * 
	 * @param budgetAttributeToBeAdded
	 *            the BudgetAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetAttribute> createBudgetAttribute(@RequestBody BudgetAttribute budgetAttributeToBeAdded) throws Exception {

		AddBudgetAttribute command = new AddBudgetAttribute(budgetAttributeToBeAdded);
		BudgetAttribute budgetAttribute = ((BudgetAttributeAdded) Scheduler.execute(command).data()).getAddedBudgetAttribute();
		
		if (budgetAttribute != null) 
			return successful(budgetAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetAttribute with the specific Id
	 * 
	 * @param budgetAttributeToBeUpdated
	 *            the BudgetAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetAttribute(@RequestBody BudgetAttribute budgetAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		budgetAttributeToBeUpdated.setAttrName(attrName);

		UpdateBudgetAttribute command = new UpdateBudgetAttribute(budgetAttributeToBeUpdated);

		try {
			if(((BudgetAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetAttributeId}")
	public ResponseEntity<BudgetAttribute> findById(@PathVariable String budgetAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetAttributeId", budgetAttributeId);
		try {

			List<BudgetAttribute> foundBudgetAttribute = findBudgetAttributesBy(requestParams).getBody();
			if(foundBudgetAttribute.size()==1){				return successful(foundBudgetAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetAttributeId}")
	public ResponseEntity<String> deleteBudgetAttributeByIdUpdated(@PathVariable String budgetAttributeId) throws Exception {
		DeleteBudgetAttribute command = new DeleteBudgetAttribute(budgetAttributeId);

		try {
			if (((BudgetAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
