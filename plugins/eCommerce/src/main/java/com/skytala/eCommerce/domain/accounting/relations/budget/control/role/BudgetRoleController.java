package com.skytala.eCommerce.domain.accounting.relations.budget.control.role;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.role.AddBudgetRole;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.role.DeleteBudgetRole;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.role.UpdateBudgetRole;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.role.BudgetRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.role.BudgetRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.role.BudgetRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.role.BudgetRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.role.BudgetRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.role.BudgetRole;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.role.FindBudgetRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/budgetRoles")
public class BudgetRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetRole
	 * @return a List with the BudgetRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BudgetRole>> findBudgetRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetRolesBy query = new FindBudgetRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetRole> budgetRoles =((BudgetRoleFound) Scheduler.execute(query).data()).getBudgetRoles();

		return ResponseEntity.ok().body(budgetRoles);

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
	public ResponseEntity<BudgetRole> createBudgetRole(HttpServletRequest request) throws Exception {

		BudgetRole budgetRoleToBeAdded = new BudgetRole();
		try {
			budgetRoleToBeAdded = BudgetRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createBudgetRole(budgetRoleToBeAdded);

	}

	/**
	 * creates a new BudgetRole entry in the ofbiz database
	 * 
	 * @param budgetRoleToBeAdded
	 *            the BudgetRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetRole> createBudgetRole(@RequestBody BudgetRole budgetRoleToBeAdded) throws Exception {

		AddBudgetRole command = new AddBudgetRole(budgetRoleToBeAdded);
		BudgetRole budgetRole = ((BudgetRoleAdded) Scheduler.execute(command).data()).getAddedBudgetRole();
		
		if (budgetRole != null) 
			return successful(budgetRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BudgetRole with the specific Id
	 * 
	 * @param budgetRoleToBeUpdated
	 *            the BudgetRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBudgetRole(@RequestBody BudgetRole budgetRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		budgetRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateBudgetRole command = new UpdateBudgetRole(budgetRoleToBeUpdated);

		try {
			if(((BudgetRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetRoleId}")
	public ResponseEntity<BudgetRole> findById(@PathVariable String budgetRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetRoleId", budgetRoleId);
		try {

			List<BudgetRole> foundBudgetRole = findBudgetRolesBy(requestParams).getBody();
			if(foundBudgetRole.size()==1){				return successful(foundBudgetRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetRoleId}")
	public ResponseEntity<String> deleteBudgetRoleByIdUpdated(@PathVariable String budgetRoleId) throws Exception {
		DeleteBudgetRole command = new DeleteBudgetRole(budgetRoleId);

		try {
			if (((BudgetRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
