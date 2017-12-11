package com.skytala.eCommerce.domain.login.relations.userLogin.control.securityGroup;

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
import com.skytala.eCommerce.domain.login.relations.userLogin.command.securityGroup.AddUserLoginSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.securityGroup.DeleteUserLoginSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.securityGroup.UpdateUserLoginSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupDeleted;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityGroup.UserLoginSecurityGroupMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.userLogin.query.securityGroup.FindUserLoginSecurityGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/login/userLogin/userLoginSecurityGroups")
public class UserLoginSecurityGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UserLoginSecurityGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UserLoginSecurityGroup
	 * @return a List with the UserLoginSecurityGroups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findUserLoginSecurityGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginSecurityGroupsBy query = new FindUserLoginSecurityGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginSecurityGroup> userLoginSecurityGroups =((UserLoginSecurityGroupFound) Scheduler.execute(query).data()).getUserLoginSecurityGroups();

		if (userLoginSecurityGroups.size() == 1) {
			return ResponseEntity.ok().body(userLoginSecurityGroups.get(0));
		}

		return ResponseEntity.ok().body(userLoginSecurityGroups);

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
	public ResponseEntity<Object> createUserLoginSecurityGroup(HttpServletRequest request) throws Exception {

		UserLoginSecurityGroup userLoginSecurityGroupToBeAdded = new UserLoginSecurityGroup();
		try {
			userLoginSecurityGroupToBeAdded = UserLoginSecurityGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createUserLoginSecurityGroup(userLoginSecurityGroupToBeAdded);

	}

	/**
	 * creates a new UserLoginSecurityGroup entry in the ofbiz database
	 * 
	 * @param userLoginSecurityGroupToBeAdded
	 *            the UserLoginSecurityGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createUserLoginSecurityGroup(@RequestBody UserLoginSecurityGroup userLoginSecurityGroupToBeAdded) throws Exception {

		AddUserLoginSecurityGroup command = new AddUserLoginSecurityGroup(userLoginSecurityGroupToBeAdded);
		UserLoginSecurityGroup userLoginSecurityGroup = ((UserLoginSecurityGroupAdded) Scheduler.execute(command).data()).getAddedUserLoginSecurityGroup();
		
		if (userLoginSecurityGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(userLoginSecurityGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("UserLoginSecurityGroup could not be created.");
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
	public boolean updateUserLoginSecurityGroup(HttpServletRequest request) throws Exception {

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

		UserLoginSecurityGroup userLoginSecurityGroupToBeUpdated = new UserLoginSecurityGroup();

		try {
			userLoginSecurityGroupToBeUpdated = UserLoginSecurityGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUserLoginSecurityGroup(userLoginSecurityGroupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the UserLoginSecurityGroup with the specific Id
	 * 
	 * @param userLoginSecurityGroupToBeUpdated
	 *            the UserLoginSecurityGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserLoginSecurityGroup(@RequestBody UserLoginSecurityGroup userLoginSecurityGroupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginSecurityGroupToBeUpdated.setnull(null);

		UpdateUserLoginSecurityGroup command = new UpdateUserLoginSecurityGroup(userLoginSecurityGroupToBeUpdated);

		try {
			if(((UserLoginSecurityGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userLoginSecurityGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String userLoginSecurityGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginSecurityGroupId", userLoginSecurityGroupId);
		try {

			Object foundUserLoginSecurityGroup = findUserLoginSecurityGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundUserLoginSecurityGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userLoginSecurityGroupId}")
	public ResponseEntity<Object> deleteUserLoginSecurityGroupByIdUpdated(@PathVariable String userLoginSecurityGroupId) throws Exception {
		DeleteUserLoginSecurityGroup command = new DeleteUserLoginSecurityGroup(userLoginSecurityGroupId);

		try {
			if (((UserLoginSecurityGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UserLoginSecurityGroup could not be deleted");

	}

}