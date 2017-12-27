package com.skytala.eCommerce.domain.party.relations.roleType;

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
import com.skytala.eCommerce.domain.party.relations.roleType.command.AddRoleType;
import com.skytala.eCommerce.domain.party.relations.roleType.command.DeleteRoleType;
import com.skytala.eCommerce.domain.party.relations.roleType.command.UpdateRoleType;
import com.skytala.eCommerce.domain.party.relations.roleType.event.RoleTypeAdded;
import com.skytala.eCommerce.domain.party.relations.roleType.event.RoleTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.roleType.event.RoleTypeFound;
import com.skytala.eCommerce.domain.party.relations.roleType.event.RoleTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.roleType.mapper.RoleTypeMapper;
import com.skytala.eCommerce.domain.party.relations.roleType.model.RoleType;
import com.skytala.eCommerce.domain.party.relations.roleType.query.FindRoleTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/roleTypes")
public class RoleTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RoleTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RoleType
	 * @return a List with the RoleTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RoleType>> findRoleTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRoleTypesBy query = new FindRoleTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RoleType> roleTypes =((RoleTypeFound) Scheduler.execute(query).data()).getRoleTypes();

		return ResponseEntity.ok().body(roleTypes);

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
	public ResponseEntity<RoleType> createRoleType(HttpServletRequest request) throws Exception {

		RoleType roleTypeToBeAdded = new RoleType();
		try {
			roleTypeToBeAdded = RoleTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createRoleType(roleTypeToBeAdded);

	}

	/**
	 * creates a new RoleType entry in the ofbiz database
	 * 
	 * @param roleTypeToBeAdded
	 *            the RoleType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoleType> createRoleType(@RequestBody RoleType roleTypeToBeAdded) throws Exception {

		AddRoleType command = new AddRoleType(roleTypeToBeAdded);
		RoleType roleType = ((RoleTypeAdded) Scheduler.execute(command).data()).getAddedRoleType();
		
		if (roleType != null) 
			return successful(roleType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RoleType with the specific Id
	 * 
	 * @param roleTypeToBeUpdated
	 *            the RoleType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRoleType(@RequestBody RoleType roleTypeToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		roleTypeToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateRoleType command = new UpdateRoleType(roleTypeToBeUpdated);

		try {
			if(((RoleTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{roleTypeId}")
	public ResponseEntity<RoleType> findById(@PathVariable String roleTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("roleTypeId", roleTypeId);
		try {

			List<RoleType> foundRoleType = findRoleTypesBy(requestParams).getBody();
			if(foundRoleType.size()==1){				return successful(foundRoleType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{roleTypeId}")
	public ResponseEntity<String> deleteRoleTypeByIdUpdated(@PathVariable String roleTypeId) throws Exception {
		DeleteRoleType command = new DeleteRoleType(roleTypeId);

		try {
			if (((RoleTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
