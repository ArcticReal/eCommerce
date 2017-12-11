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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findUserLoginSessionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginSessionsBy query = new FindUserLoginSessionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginSession> userLoginSessions =((UserLoginSessionFound) Scheduler.execute(query).data()).getUserLoginSessions();

		if (userLoginSessions.size() == 1) {
			return ResponseEntity.ok().body(userLoginSessions.get(0));
		}

		return ResponseEntity.ok().body(userLoginSessions);

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
	public ResponseEntity<Object> createUserLoginSession(HttpServletRequest request) throws Exception {

		UserLoginSession userLoginSessionToBeAdded = new UserLoginSession();
		try {
			userLoginSessionToBeAdded = UserLoginSessionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createUserLoginSession(userLoginSessionToBeAdded);

	}

	/**
	 * creates a new UserLoginSession entry in the ofbiz database
	 * 
	 * @param userLoginSessionToBeAdded
	 *            the UserLoginSession thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createUserLoginSession(@RequestBody UserLoginSession userLoginSessionToBeAdded) throws Exception {

		AddUserLoginSession command = new AddUserLoginSession(userLoginSessionToBeAdded);
		UserLoginSession userLoginSession = ((UserLoginSessionAdded) Scheduler.execute(command).data()).getAddedUserLoginSession();
		
		if (userLoginSession != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(userLoginSession);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("UserLoginSession could not be created.");
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
	public boolean updateUserLoginSession(HttpServletRequest request) throws Exception {

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

		UserLoginSession userLoginSessionToBeUpdated = new UserLoginSession();

		try {
			userLoginSessionToBeUpdated = UserLoginSessionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUserLoginSession(userLoginSessionToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateUserLoginSession(@RequestBody UserLoginSession userLoginSessionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginSessionToBeUpdated.setnull(null);

		UpdateUserLoginSession command = new UpdateUserLoginSession(userLoginSessionToBeUpdated);

		try {
			if(((UserLoginSessionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{userLoginSessionId}")
	public ResponseEntity<Object> findById(@PathVariable String userLoginSessionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginSessionId", userLoginSessionId);
		try {

			Object foundUserLoginSession = findUserLoginSessionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundUserLoginSession);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{userLoginSessionId}")
	public ResponseEntity<Object> deleteUserLoginSessionByIdUpdated(@PathVariable String userLoginSessionId) throws Exception {
		DeleteUserLoginSession command = new DeleteUserLoginSession(userLoginSessionId);

		try {
			if (((UserLoginSessionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UserLoginSession could not be deleted");

	}

}
