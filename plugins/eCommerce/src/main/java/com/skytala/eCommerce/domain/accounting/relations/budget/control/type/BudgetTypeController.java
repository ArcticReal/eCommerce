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
	public ResponseEntity<Object> findBudgetTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetTypesBy query = new FindBudgetTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetType> budgetTypes =((BudgetTypeFound) Scheduler.execute(query).data()).getBudgetTypes();

		if (budgetTypes.size() == 1) {
			return ResponseEntity.ok().body(budgetTypes.get(0));
		}

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
	public ResponseEntity<Object> createBudgetType(HttpServletRequest request) throws Exception {

		BudgetType budgetTypeToBeAdded = new BudgetType();
		try {
			budgetTypeToBeAdded = BudgetTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createBudgetType(@RequestBody BudgetType budgetTypeToBeAdded) throws Exception {

		AddBudgetType command = new AddBudgetType(budgetTypeToBeAdded);
		BudgetType budgetType = ((BudgetTypeAdded) Scheduler.execute(command).data()).getAddedBudgetType();
		
		if (budgetType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetType could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateBudgetType(HttpServletRequest request) throws Exception {

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

		BudgetType budgetTypeToBeUpdated = new BudgetType();

		try {
			budgetTypeToBeUpdated = BudgetTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetType(budgetTypeToBeUpdated, budgetTypeToBeUpdated.getBudgetTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBudgetType(@RequestBody BudgetType budgetTypeToBeUpdated,
			@PathVariable String budgetTypeId) throws Exception {

		budgetTypeToBeUpdated.setBudgetTypeId(budgetTypeId);

		UpdateBudgetType command = new UpdateBudgetType(budgetTypeToBeUpdated);

		try {
			if(((BudgetTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{budgetTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetTypeId", budgetTypeId);
		try {

			Object foundBudgetType = findBudgetTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{budgetTypeId}")
	public ResponseEntity<Object> deleteBudgetTypeByIdUpdated(@PathVariable String budgetTypeId) throws Exception {
		DeleteBudgetType command = new DeleteBudgetType(budgetTypeId);

		try {
			if (((BudgetTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetType could not be deleted");

	}

}
