package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.role;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.role.AddGlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.role.DeleteGlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.role.UpdateGlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role.GlAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role.GlAccountRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role.GlAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role.GlAccountRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.role.GlAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.role.GlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.role.FindGlAccountRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountRoles")
public class GlAccountRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountRole
	 * @return a List with the GlAccountRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountRole>> findGlAccountRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountRolesBy query = new FindGlAccountRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountRole> glAccountRoles =((GlAccountRoleFound) Scheduler.execute(query).data()).getGlAccountRoles();

		return ResponseEntity.ok().body(glAccountRoles);

	}

	/**
	 * creates a new GlAccountRole entry in the ofbiz database
	 * 
	 * @param glAccountRoleToBeAdded
	 *            the GlAccountRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountRole> createGlAccountRole(@RequestBody GlAccountRole glAccountRoleToBeAdded) throws Exception {

		AddGlAccountRole command = new AddGlAccountRole(glAccountRoleToBeAdded);
		GlAccountRole glAccountRole = ((GlAccountRoleAdded) Scheduler.execute(command).data()).getAddedGlAccountRole();
		
		if (glAccountRole != null) 
			return successful(glAccountRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountRole with the specific Id
	 * 
	 * @param glAccountRoleToBeUpdated
	 *            the GlAccountRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountRole(@RequestBody GlAccountRole glAccountRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		glAccountRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateGlAccountRole command = new UpdateGlAccountRole(glAccountRoleToBeUpdated);

		try {
			if(((GlAccountRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountRoleId}")
	public ResponseEntity<GlAccountRole> findById(@PathVariable String glAccountRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountRoleId", glAccountRoleId);
		try {

			List<GlAccountRole> foundGlAccountRole = findGlAccountRolesBy(requestParams).getBody();
			if(foundGlAccountRole.size()==1){				return successful(foundGlAccountRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountRoleId}")
	public ResponseEntity<String> deleteGlAccountRoleByIdUpdated(@PathVariable String glAccountRoleId) throws Exception {
		DeleteGlAccountRole command = new DeleteGlAccountRole(glAccountRoleId);

		try {
			if (((GlAccountRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
