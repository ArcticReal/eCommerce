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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/securityPermissions")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSecurityPermissionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSecurityPermissionsBy query = new FindSecurityPermissionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SecurityPermission> securityPermissions =((SecurityPermissionFound) Scheduler.execute(query).data()).getSecurityPermissions();

		if (securityPermissions.size() == 1) {
			return ResponseEntity.ok().body(securityPermissions.get(0));
		}

		return ResponseEntity.ok().body(securityPermissions);

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
	public ResponseEntity<Object> createSecurityPermission(HttpServletRequest request) throws Exception {

		SecurityPermission securityPermissionToBeAdded = new SecurityPermission();
		try {
			securityPermissionToBeAdded = SecurityPermissionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSecurityPermission(securityPermissionToBeAdded);

	}

	/**
	 * creates a new SecurityPermission entry in the ofbiz database
	 * 
	 * @param securityPermissionToBeAdded
	 *            the SecurityPermission thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSecurityPermission(@RequestBody SecurityPermission securityPermissionToBeAdded) throws Exception {

		AddSecurityPermission command = new AddSecurityPermission(securityPermissionToBeAdded);
		SecurityPermission securityPermission = ((SecurityPermissionAdded) Scheduler.execute(command).data()).getAddedSecurityPermission();
		
		if (securityPermission != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(securityPermission);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SecurityPermission could not be created.");
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
	public boolean updateSecurityPermission(HttpServletRequest request) throws Exception {

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

		SecurityPermission securityPermissionToBeUpdated = new SecurityPermission();

		try {
			securityPermissionToBeUpdated = SecurityPermissionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSecurityPermission(securityPermissionToBeUpdated, securityPermissionToBeUpdated.getPermissionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSecurityPermission(@RequestBody SecurityPermission securityPermissionToBeUpdated,
			@PathVariable String permissionId) throws Exception {

		securityPermissionToBeUpdated.setPermissionId(permissionId);

		UpdateSecurityPermission command = new UpdateSecurityPermission(securityPermissionToBeUpdated);

		try {
			if(((SecurityPermissionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{securityPermissionId}")
	public ResponseEntity<Object> findById(@PathVariable String securityPermissionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("securityPermissionId", securityPermissionId);
		try {

			Object foundSecurityPermission = findSecurityPermissionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSecurityPermission);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{securityPermissionId}")
	public ResponseEntity<Object> deleteSecurityPermissionByIdUpdated(@PathVariable String securityPermissionId) throws Exception {
		DeleteSecurityPermission command = new DeleteSecurityPermission(securityPermissionId);

		try {
			if (((SecurityPermissionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SecurityPermission could not be deleted");

	}

}
