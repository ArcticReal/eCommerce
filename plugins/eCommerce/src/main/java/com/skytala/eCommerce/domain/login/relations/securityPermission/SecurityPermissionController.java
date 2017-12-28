package com.skytala.eCommerce.domain.login.relations.securityPermission;

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
import com.skytala.eCommerce.domain.login.relations.securityPermission.command.AddSecurityPermission;
import com.skytala.eCommerce.domain.login.relations.securityPermission.command.DeleteSecurityPermission;
import com.skytala.eCommerce.domain.login.relations.securityPermission.command.UpdateSecurityPermission;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionAdded;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionDeleted;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionFound;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionUpdated;
import com.skytala.eCommerce.domain.login.relations.securityPermission.mapper.SecurityPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
import com.skytala.eCommerce.domain.login.relations.securityPermission.query.FindSecurityPermissionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/login/securityPermissions")
public class SecurityPermissionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SecurityPermissionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SecurityPermission
	 * @return a List with the SecurityPermissions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SecurityPermission>> findSecurityPermissionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSecurityPermissionsBy query = new FindSecurityPermissionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SecurityPermission> securityPermissions =((SecurityPermissionFound) Scheduler.execute(query).data()).getSecurityPermissions();

		return ResponseEntity.ok().body(securityPermissions);

	}

	/**
	 * creates a new SecurityPermission entry in the ofbiz database
	 * 
	 * @param securityPermissionToBeAdded
	 *            the SecurityPermission thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SecurityPermission> createSecurityPermission(@RequestBody SecurityPermission securityPermissionToBeAdded) throws Exception {

		AddSecurityPermission command = new AddSecurityPermission(securityPermissionToBeAdded);
		SecurityPermission securityPermission = ((SecurityPermissionAdded) Scheduler.execute(command).data()).getAddedSecurityPermission();
		
		if (securityPermission != null) 
			return successful(securityPermission);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SecurityPermission with the specific Id
	 * 
	 * @param securityPermissionToBeUpdated
	 *            the SecurityPermission thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{permissionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSecurityPermission(@RequestBody SecurityPermission securityPermissionToBeUpdated,
			@PathVariable String permissionId) throws Exception {

		securityPermissionToBeUpdated.setPermissionId(permissionId);

		UpdateSecurityPermission command = new UpdateSecurityPermission(securityPermissionToBeUpdated);

		try {
			if(((SecurityPermissionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{securityPermissionId}")
	public ResponseEntity<SecurityPermission> findById(@PathVariable String securityPermissionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("securityPermissionId", securityPermissionId);
		try {

			List<SecurityPermission> foundSecurityPermission = findSecurityPermissionsBy(requestParams).getBody();
			if(foundSecurityPermission.size()==1){				return successful(foundSecurityPermission.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{securityPermissionId}")
	public ResponseEntity<String> deleteSecurityPermissionByIdUpdated(@PathVariable String securityPermissionId) throws Exception {
		DeleteSecurityPermission command = new DeleteSecurityPermission(securityPermissionId);

		try {
			if (((SecurityPermissionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
