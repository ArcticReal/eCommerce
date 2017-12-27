package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.role;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.role.AddFinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.role.DeleteFinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.role.UpdateFinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role.FinAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role.FinAccountRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role.FinAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role.FinAccountRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.role.FinAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.role.FinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.role.FindFinAccountRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountRoles")
public class FinAccountRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountRole
	 * @return a List with the FinAccountRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountRole>> findFinAccountRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountRolesBy query = new FindFinAccountRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountRole> finAccountRoles =((FinAccountRoleFound) Scheduler.execute(query).data()).getFinAccountRoles();

		return ResponseEntity.ok().body(finAccountRoles);

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
	public ResponseEntity<FinAccountRole> createFinAccountRole(HttpServletRequest request) throws Exception {

		FinAccountRole finAccountRoleToBeAdded = new FinAccountRole();
		try {
			finAccountRoleToBeAdded = FinAccountRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFinAccountRole(finAccountRoleToBeAdded);

	}

	/**
	 * creates a new FinAccountRole entry in the ofbiz database
	 * 
	 * @param finAccountRoleToBeAdded
	 *            the FinAccountRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountRole> createFinAccountRole(@RequestBody FinAccountRole finAccountRoleToBeAdded) throws Exception {

		AddFinAccountRole command = new AddFinAccountRole(finAccountRoleToBeAdded);
		FinAccountRole finAccountRole = ((FinAccountRoleAdded) Scheduler.execute(command).data()).getAddedFinAccountRole();
		
		if (finAccountRole != null) 
			return successful(finAccountRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountRole with the specific Id
	 * 
	 * @param finAccountRoleToBeUpdated
	 *            the FinAccountRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountRole(@RequestBody FinAccountRole finAccountRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		finAccountRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateFinAccountRole command = new UpdateFinAccountRole(finAccountRoleToBeUpdated);

		try {
			if(((FinAccountRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountRoleId}")
	public ResponseEntity<FinAccountRole> findById(@PathVariable String finAccountRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountRoleId", finAccountRoleId);
		try {

			List<FinAccountRole> foundFinAccountRole = findFinAccountRolesBy(requestParams).getBody();
			if(foundFinAccountRole.size()==1){				return successful(foundFinAccountRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountRoleId}")
	public ResponseEntity<String> deleteFinAccountRoleByIdUpdated(@PathVariable String finAccountRoleId) throws Exception {
		DeleteFinAccountRole command = new DeleteFinAccountRole(finAccountRoleId);

		try {
			if (((FinAccountRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
