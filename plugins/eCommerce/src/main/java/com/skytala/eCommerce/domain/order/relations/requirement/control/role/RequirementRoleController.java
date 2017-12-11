package com.skytala.eCommerce.domain.order.relations.requirement.control.role;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.role.AddRequirementRole;
import com.skytala.eCommerce.domain.order.relations.requirement.command.role.DeleteRequirementRole;
import com.skytala.eCommerce.domain.order.relations.requirement.command.role.UpdateRequirementRole;
import com.skytala.eCommerce.domain.order.relations.requirement.event.role.RequirementRoleAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.role.RequirementRoleDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.role.RequirementRoleFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.role.RequirementRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.role.RequirementRoleMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.role.RequirementRole;
import com.skytala.eCommerce.domain.order.relations.requirement.query.role.FindRequirementRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/requirement/requirementRoles")
public class RequirementRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementRole
	 * @return a List with the RequirementRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findRequirementRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementRolesBy query = new FindRequirementRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementRole> requirementRoles =((RequirementRoleFound) Scheduler.execute(query).data()).getRequirementRoles();

		if (requirementRoles.size() == 1) {
			return ResponseEntity.ok().body(requirementRoles.get(0));
		}

		return ResponseEntity.ok().body(requirementRoles);

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
	public ResponseEntity<Object> createRequirementRole(HttpServletRequest request) throws Exception {

		RequirementRole requirementRoleToBeAdded = new RequirementRole();
		try {
			requirementRoleToBeAdded = RequirementRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRequirementRole(requirementRoleToBeAdded);

	}

	/**
	 * creates a new RequirementRole entry in the ofbiz database
	 * 
	 * @param requirementRoleToBeAdded
	 *            the RequirementRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRequirementRole(@RequestBody RequirementRole requirementRoleToBeAdded) throws Exception {

		AddRequirementRole command = new AddRequirementRole(requirementRoleToBeAdded);
		RequirementRole requirementRole = ((RequirementRoleAdded) Scheduler.execute(command).data()).getAddedRequirementRole();
		
		if (requirementRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(requirementRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RequirementRole could not be created.");
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
	public boolean updateRequirementRole(HttpServletRequest request) throws Exception {

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

		RequirementRole requirementRoleToBeUpdated = new RequirementRole();

		try {
			requirementRoleToBeUpdated = RequirementRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRequirementRole(requirementRoleToBeUpdated, requirementRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RequirementRole with the specific Id
	 * 
	 * @param requirementRoleToBeUpdated
	 *            the RequirementRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRequirementRole(@RequestBody RequirementRole requirementRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		requirementRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateRequirementRole command = new UpdateRequirementRole(requirementRoleToBeUpdated);

		try {
			if(((RequirementRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{requirementRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String requirementRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementRoleId", requirementRoleId);
		try {

			Object foundRequirementRole = findRequirementRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRequirementRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{requirementRoleId}")
	public ResponseEntity<Object> deleteRequirementRoleByIdUpdated(@PathVariable String requirementRoleId) throws Exception {
		DeleteRequirementRole command = new DeleteRequirementRole(requirementRoleId);

		try {
			if (((RequirementRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RequirementRole could not be deleted");

	}

}
