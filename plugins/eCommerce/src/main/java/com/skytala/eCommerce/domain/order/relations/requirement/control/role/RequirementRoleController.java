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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<RequirementRole>> findRequirementRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementRolesBy query = new FindRequirementRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementRole> requirementRoles =((RequirementRoleFound) Scheduler.execute(query).data()).getRequirementRoles();

		return ResponseEntity.ok().body(requirementRoles);

	}

	/**
	 * creates a new RequirementRole entry in the ofbiz database
	 * 
	 * @param requirementRoleToBeAdded
	 *            the RequirementRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequirementRole> createRequirementRole(@RequestBody RequirementRole requirementRoleToBeAdded) throws Exception {

		AddRequirementRole command = new AddRequirementRole(requirementRoleToBeAdded);
		RequirementRole requirementRole = ((RequirementRoleAdded) Scheduler.execute(command).data()).getAddedRequirementRole();
		
		if (requirementRole != null) 
			return successful(requirementRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateRequirementRole(@RequestBody RequirementRole requirementRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		requirementRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateRequirementRole command = new UpdateRequirementRole(requirementRoleToBeUpdated);

		try {
			if(((RequirementRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementRoleId}")
	public ResponseEntity<RequirementRole> findById(@PathVariable String requirementRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementRoleId", requirementRoleId);
		try {

			List<RequirementRole> foundRequirementRole = findRequirementRolesBy(requestParams).getBody();
			if(foundRequirementRole.size()==1){				return successful(foundRequirementRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementRoleId}")
	public ResponseEntity<String> deleteRequirementRoleByIdUpdated(@PathVariable String requirementRoleId) throws Exception {
		DeleteRequirementRole command = new DeleteRequirementRole(requirementRoleId);

		try {
			if (((RequirementRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
