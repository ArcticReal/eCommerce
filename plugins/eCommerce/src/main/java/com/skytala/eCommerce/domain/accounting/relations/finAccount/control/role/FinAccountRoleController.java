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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findFinAccountRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountRolesBy query = new FindFinAccountRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountRole> finAccountRoles =((FinAccountRoleFound) Scheduler.execute(query).data()).getFinAccountRoles();

		if (finAccountRoles.size() == 1) {
			return ResponseEntity.ok().body(finAccountRoles.get(0));
		}

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
	public ResponseEntity<Object> createFinAccountRole(HttpServletRequest request) throws Exception {

		FinAccountRole finAccountRoleToBeAdded = new FinAccountRole();
		try {
			finAccountRoleToBeAdded = FinAccountRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createFinAccountRole(@RequestBody FinAccountRole finAccountRoleToBeAdded) throws Exception {

		AddFinAccountRole command = new AddFinAccountRole(finAccountRoleToBeAdded);
		FinAccountRole finAccountRole = ((FinAccountRoleAdded) Scheduler.execute(command).data()).getAddedFinAccountRole();
		
		if (finAccountRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountRole could not be created.");
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
	public boolean updateFinAccountRole(HttpServletRequest request) throws Exception {

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

		FinAccountRole finAccountRoleToBeUpdated = new FinAccountRole();

		try {
			finAccountRoleToBeUpdated = FinAccountRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountRole(finAccountRoleToBeUpdated, finAccountRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccountRole(@RequestBody FinAccountRole finAccountRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		finAccountRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateFinAccountRole command = new UpdateFinAccountRole(finAccountRoleToBeUpdated);

		try {
			if(((FinAccountRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{finAccountRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountRoleId", finAccountRoleId);
		try {

			Object foundFinAccountRole = findFinAccountRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{finAccountRoleId}")
	public ResponseEntity<Object> deleteFinAccountRoleByIdUpdated(@PathVariable String finAccountRoleId) throws Exception {
		DeleteFinAccountRole command = new DeleteFinAccountRole(finAccountRoleId);

		try {
			if (((FinAccountRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountRole could not be deleted");

	}

}
