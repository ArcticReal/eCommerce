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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findRoleTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRoleTypesBy query = new FindRoleTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RoleType> roleTypes =((RoleTypeFound) Scheduler.execute(query).data()).getRoleTypes();

		if (roleTypes.size() == 1) {
			return ResponseEntity.ok().body(roleTypes.get(0));
		}

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
	public ResponseEntity<Object> createRoleType(HttpServletRequest request) throws Exception {

		RoleType roleTypeToBeAdded = new RoleType();
		try {
			roleTypeToBeAdded = RoleTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createRoleType(@RequestBody RoleType roleTypeToBeAdded) throws Exception {

		AddRoleType command = new AddRoleType(roleTypeToBeAdded);
		RoleType roleType = ((RoleTypeAdded) Scheduler.execute(command).data()).getAddedRoleType();
		
		if (roleType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(roleType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RoleType could not be created.");
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
	public boolean updateRoleType(HttpServletRequest request) throws Exception {

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

		RoleType roleTypeToBeUpdated = new RoleType();

		try {
			roleTypeToBeUpdated = RoleTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRoleType(roleTypeToBeUpdated, roleTypeToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateRoleType(@RequestBody RoleType roleTypeToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		roleTypeToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateRoleType command = new UpdateRoleType(roleTypeToBeUpdated);

		try {
			if(((RoleTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{roleTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String roleTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("roleTypeId", roleTypeId);
		try {

			Object foundRoleType = findRoleTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRoleType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{roleTypeId}")
	public ResponseEntity<Object> deleteRoleTypeByIdUpdated(@PathVariable String roleTypeId) throws Exception {
		DeleteRoleType command = new DeleteRoleType(roleTypeId);

		try {
			if (((RoleTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RoleType could not be deleted");

	}

}
