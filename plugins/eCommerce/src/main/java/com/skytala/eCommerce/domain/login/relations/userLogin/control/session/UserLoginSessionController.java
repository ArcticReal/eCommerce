package com.skytala.eCommerce.domain.login.relations.userLogin.control.session;

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
import com.skytala.eCommerce.domain.login.relations.userLogin.command.session.AddUserLoginSession;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.session.DeleteUserLoginSession;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.session.UpdateUserLoginSession;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionDeleted;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.session.UserLoginSessionMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
import com.skytala.eCommerce.domain.login.relations.userLogin.query.session.FindUserLoginSessionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/login/userLogin/userLoginSessions")
public class UserLoginSessionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UserLoginSessionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UserLoginSession
	 * @return a List with the UserLoginSessions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<UserLoginSession>> findUserLoginSessionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginSessionsBy query = new FindUserLoginSessionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginSession> userLoginSessions =((UserLoginSessionFound) Scheduler.execute(query).data()).getUserLoginSessions();

		return ResponseEntity.ok().body(userLoginSessions);

	}

	/**
	 * creates a new UserLoginSession entry in the ofbiz database
	 * 
	 * @param userLoginSessionToBeAdded
	 *            the UserLoginSession thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserLoginSession> createUserLoginSession(@RequestBody UserLoginSession userLoginSessionToBeAdded) throws Exception {

		AddUserLoginSession command = new AddUserLoginSession(userLoginSessionToBeAdded);
		UserLoginSession userLoginSession = ((UserLoginSessionAdded) Scheduler.execute(command).data()).getAddedUserLoginSession();
		
		if (userLoginSession != null) 
			return successful(userLoginSession);
		else 
			return conflict(null);
	}

	/**
	 * Updates the UserLoginSession with the specific Id
	 * 
	 * @param userLoginSessionToBeUpdated
	 *            the UserLoginSession thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateUserLoginSession(@RequestBody UserLoginSession userLoginSessionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginSessionToBeUpdated.setnull(null);

		UpdateUserLoginSession command = new UpdateUserLoginSession(userLoginSessionToBeUpdated);

		try {
			if(((UserLoginSessionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{userLoginSessionId}")
	public ResponseEntity<UserLoginSession> findById(@PathVariable String userLoginSessionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginSessionId", userLoginSessionId);
		try {

			List<UserLoginSession> foundUserLoginSession = findUserLoginSessionsBy(requestParams).getBody();
			if(foundUserLoginSession.size()==1){				return successful(foundUserLoginSession.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{userLoginSessionId}")
	public ResponseEntity<String> deleteUserLoginSessionByIdUpdated(@PathVariable String userLoginSessionId) throws Exception {
		DeleteUserLoginSession command = new DeleteUserLoginSession(userLoginSessionId);

		try {
			if (((UserLoginSessionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
