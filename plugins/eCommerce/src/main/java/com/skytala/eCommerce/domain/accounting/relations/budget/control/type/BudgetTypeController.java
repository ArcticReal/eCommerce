package com.skytala.eCommerce.domain.accounting.relations.budget.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.type.AddBudgetType;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.type.DeleteBudgetType;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.type.UpdateBudgetType;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.type.BudgetTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.type.BudgetTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.type.BudgetTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.type.BudgetTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.type.BudgetTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.type.BudgetType;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.type.FindBudgetTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetTypes")
public class BudgetTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetType
	 * @return a List with the BudgetTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetType>> findBudgetTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetTypesBy query = new FindBudgetTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetType> budgetTypes =((BudgetTypeFound) Scheduler.execute(query).data()).getBudgetTypes();

		return ResponseEntity.ok().body(budgetTypes);

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
	public ResponseEntity<BudgetType> createBudgetType(HttpServletRequest request) throws Exception {

		BudgetType budgetTypeToBeAdded = new BudgetType();
		try {
			budgetTypeToBeAdded = BudgetTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createBudgetType(budgetTypeToBeAdded);

	}

	/**
	 * creates a new BudgetType entry in the ofbiz database
	 * 
	 * @param budgetTypeToBeAdded
	 *            the BudgetType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetType> createBudgetType(@RequestBody BudgetType budgetTypeToBeAdded) throws Exception {

		AddBudgetType command = new AddBudgetType(budgetTypeToBeAdded);
		BudgetType budgetType = ((BudgetTypeAdded) Scheduler.execute(command).data()).getAddedBudgetType();
		
		if (budgetType != null) 
			return successful(budgetType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetType with the specific Id
	 * 
	 * @param budgetTypeToBeUpdated
	 *            the BudgetType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetType(@RequestBody BudgetType budgetTypeToBeUpdated,
			@PathVariable String budgetTypeId) throws Exception {

		budgetTypeToBeUpdated.setBudgetTypeId(budgetTypeId);

		UpdateBudgetType command = new UpdateBudgetType(budgetTypeToBeUpdated);

		try {
			if(((BudgetTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetTypeId}")
	public ResponseEntity<BudgetType> findById(@PathVariable String budgetTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetTypeId", budgetTypeId);
		try {

			List<BudgetType> foundBudgetType = findBudgetTypesBy(requestParams).getBody();
			if(foundBudgetType.size()==1){				return successful(foundBudgetType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetTypeId}")
	public ResponseEntity<String> deleteBudgetTypeByIdUpdated(@PathVariable String budgetTypeId) throws Exception {
		DeleteBudgetType command = new DeleteBudgetType(budgetTypeId);

		try {
			if (((BudgetTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
