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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetRolesBy query = new FindBudgetRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetRole> budgetRoles =((BudgetRoleFound) Scheduler.execute(query).data()).getBudgetRoles();

		if (budgetRoles.size() == 1) {
			return ResponseEntity.ok().body(budgetRoles.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createBudgetRole(HttpServletRequest request) throws Exception {

		BudgetRole budgetRoleToBeAdded = new BudgetRole();
		try {
			budgetRoleToBeAdded = BudgetRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createBudgetRole(@RequestBody BudgetRole budgetRoleToBeAdded) throws Exception {

		AddBudgetRole command = new AddBudgetRole(budgetRoleToBeAdded);
		BudgetRole budgetRole = ((BudgetRoleAdded) Scheduler.execute(command).data()).getAddedBudgetRole();
		
		if (budgetRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetRole could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateBudgetRole(HttpServletRequest request) throws Exception {

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

		BudgetRole budgetRoleToBeUpdated = new BudgetRole();

		try {
			budgetRoleToBeUpdated = BudgetRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetRole(budgetRoleToBeUpdated, budgetRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateBudgetRole(@RequestBody BudgetRole budgetRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		budgetRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateBudgetRole command = new UpdateBudgetRole(budgetRoleToBeUpdated);

		try {
			if(((BudgetRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetRoleId", budgetRoleId);
		try {

			Object foundBudgetRole = findBudgetRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetRoleId}")
	public ResponseEntity<Object> deleteBudgetRoleByIdUpdated(@PathVariable String budgetRoleId) throws Exception {
		DeleteBudgetRole command = new DeleteBudgetRole(budgetRoleId);

		try {
			if (((BudgetRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetRole could not be deleted");

	}

}
