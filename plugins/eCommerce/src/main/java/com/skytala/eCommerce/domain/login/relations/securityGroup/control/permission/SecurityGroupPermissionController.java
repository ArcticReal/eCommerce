package com.skytala.eCommerce.domain.login.relations.securityGroup.control.permission;

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
import com.skytala.eCommerce.domain.login.relations.securityGroup.command.permission.AddSecurityGroupPermission;
import com.skytala.eCommerce.domain.login.relations.securityGroup.command.permission.DeleteSecurityGroupPermission;
import com.skytala.eCommerce.domain.login.relations.securityGroup.command.permission.UpdateSecurityGroupPermission;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission.SecurityGroupPermissionAdded;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission.SecurityGroupPermissionDeleted;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission.SecurityGroupPermissionFound;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission.SecurityGroupPermissionUpdated;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.permission.SecurityGroupPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;
import com.skytala.eCommerce.domain.login.relations.securityGroup.query.permission.FindSecurityGroupPermissionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/login/securityGroup/securityGroupPermissions")
public class SecurityGroupPermissionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SecurityGroupPermissionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SecurityGroupPermission
	 * @return a List with the SecurityGroupPermissions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SecurityGroupPermission>> findSecurityGroupPermissionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSecurityGroupPermissionsBy query = new FindSecurityGroupPermissionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SecurityGroupPermission> securityGroupPermissions =((SecurityGroupPermissionFound) Scheduler.execute(query).data()).getSecurityGroupPermissions();

		return ResponseEntity.ok().body(securityGroupPermissions);

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
	public ResponseEntity<SecurityGroupPermission> createSecurityGroupPermission(HttpServletRequest request) throws Exception {

		SecurityGroupPermission securityGroupPermissionToBeAdded = new SecurityGroupPermission();
		try {
			securityGroupPermissionToBeAdded = SecurityGroupPermissionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSecurityGroupPermission(securityGroupPermissionToBeAdded);

	}

	/**
	 * creates a new SecurityGroupPermission entry in the ofbiz database
	 * 
	 * @param securityGroupPermissionToBeAdded
	 *            the SecurityGroupPermission thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SecurityGroupPermission> createSecurityGroupPermission(@RequestBody SecurityGroupPermission securityGroupPermissionToBeAdded) throws Exception {

		AddSecurityGroupPermission command = new AddSecurityGroupPermission(securityGroupPermissionToBeAdded);
		SecurityGroupPermission securityGroupPermission = ((SecurityGroupPermissionAdded) Scheduler.execute(command).data()).getAddedSecurityGroupPermission();
		
		if (securityGroupPermission != null) 
			return successful(securityGroupPermission);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SecurityGroupPermission with the specific Id
	 * 
	 * @param securityGroupPermissionToBeUpdated
	 *            the SecurityGroupPermission thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{permissionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSecurityGroupPermission(@RequestBody SecurityGroupPermission securityGroupPermissionToBeUpdated,
			@PathVariable String permissionId) throws Exception {

		securityGroupPermissionToBeUpdated.setPermissionId(permissionId);

		UpdateSecurityGroupPermission command = new UpdateSecurityGroupPermission(securityGroupPermissionToBeUpdated);

		try {
			if(((SecurityGroupPermissionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{securityGroupPermissionId}")
	public ResponseEntity<SecurityGroupPermission> findById(@PathVariable String securityGroupPermissionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("securityGroupPermissionId", securityGroupPermissionId);
		try {

			List<SecurityGroupPermission> foundSecurityGroupPermission = findSecurityGroupPermissionsBy(requestParams).getBody();
			if(foundSecurityGroupPermission.size()==1){				return successful(foundSecurityGroupPermission.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{securityGroupPermissionId}")
	public ResponseEntity<String> deleteSecurityGroupPermissionByIdUpdated(@PathVariable String securityGroupPermissionId) throws Exception {
		DeleteSecurityGroupPermission command = new DeleteSecurityGroupPermission(securityGroupPermissionId);

		try {
			if (((SecurityGroupPermissionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
