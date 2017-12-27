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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<UserLoginSecurityGroup>> findUserLoginSecurityGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginSecurityGroupsBy query = new FindUserLoginSecurityGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginSecurityGroup> userLoginSecurityGroups =((UserLoginSecurityGroupFound) Scheduler.execute(query).data()).getUserLoginSecurityGroups();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<UserLoginSecurityGroup> createUserLoginSecurityGroup(HttpServletRequest request) throws Exception {

		UserLoginSecurityGroup userLoginSecurityGroupToBeAdded = new UserLoginSecurityGroup();
		try {
			userLoginSecurityGroupToBeAdded = UserLoginSecurityGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<UserLoginSecurityGroup> createUserLoginSecurityGroup(@RequestBody UserLoginSecurityGroup userLoginSecurityGroupToBeAdded) throws Exception {

		AddUserLoginSecurityGroup command = new AddUserLoginSecurityGroup(userLoginSecurityGroupToBeAdded);
		UserLoginSecurityGroup userLoginSecurityGroup = ((UserLoginSecurityGroupAdded) Scheduler.execute(command).data()).getAddedUserLoginSecurityGroup();
		
		if (userLoginSecurityGroup != null) 
			return successful(userLoginSecurityGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateUserLoginSecurityGroup(@RequestBody UserLoginSecurityGroup userLoginSecurityGroupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginSecurityGroupToBeUpdated.setnull(null);

		UpdateUserLoginSecurityGroup command = new UpdateUserLoginSecurityGroup(userLoginSecurityGroupToBeUpdated);

		try {
			if(((UserLoginSecurityGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{userLoginSecurityGroupId}")
	public ResponseEntity<UserLoginSecurityGroup> findById(@PathVariable String userLoginSecurityGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginSecurityGroupId", userLoginSecurityGroupId);
		try {

			List<UserLoginSecurityGroup> foundUserLoginSecurityGroup = findUserLoginSecurityGroupsBy(requestParams).getBody();
			if(foundUserLoginSecurityGroup.size()==1){				return successful(foundUserLoginSecurityGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{userLoginSecurityGroupId}")
	public ResponseEntity<String> deleteUserLoginSecurityGroupByIdUpdated(@PathVariable String userLoginSecurityGroupId) throws Exception {
		DeleteUserLoginSecurityGroup command = new DeleteUserLoginSecurityGroup(userLoginSecurityGroupId);

		try {
			if (((UserLoginSecurityGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
