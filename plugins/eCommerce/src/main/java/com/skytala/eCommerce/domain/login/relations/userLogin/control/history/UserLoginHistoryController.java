package com.skytala.eCommerce.domain.login.relations.userLogin.control.history;

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
import com.skytala.eCommerce.domain.login.relations.userLogin.command.history.AddUserLoginHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.history.DeleteUserLoginHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.history.UpdateUserLoginHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryDeleted;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.history.UserLoginHistoryMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.query.history.FindUserLoginHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/login/userLogin/userLoginHistorys")
public class UserLoginHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UserLoginHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UserLoginHistory
	 * @return a List with the UserLoginHistorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findUserLoginHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginHistorysBy query = new FindUserLoginHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginHistory> userLoginHistorys =((UserLoginHistoryFound) Scheduler.execute(query).data()).getUserLoginHistorys();

		if (userLoginHistorys.size() == 1) {
			return ResponseEntity.ok().body(userLoginHistorys.get(0));
		}

		return ResponseEntity.ok().body(userLoginHistorys);

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
	public ResponseEntity<Object> createUserLoginHistory(HttpServletRequest request) throws Exception {

		UserLoginHistory userLoginHistoryToBeAdded = new UserLoginHistory();
		try {
			userLoginHistoryToBeAdded = UserLoginHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createUserLoginHistory(userLoginHistoryToBeAdded);

	}

	/**
	 * creates a new UserLoginHistory entry in the ofbiz database
	 * 
	 * @param userLoginHistoryToBeAdded
	 *            the UserLoginHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createUserLoginHistory(@RequestBody UserLoginHistory userLoginHistoryToBeAdded) throws Exception {

		AddUserLoginHistory command = new AddUserLoginHistory(userLoginHistoryToBeAdded);
		UserLoginHistory userLoginHistory = ((UserLoginHistoryAdded) Scheduler.execute(command).data()).getAddedUserLoginHistory();
		
		if (userLoginHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(userLoginHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("UserLoginHistory could not be created.");
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
	public boolean updateUserLoginHistory(HttpServletRequest request) throws Exception {

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

		UserLoginHistory userLoginHistoryToBeUpdated = new UserLoginHistory();

		try {
			userLoginHistoryToBeUpdated = UserLoginHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUserLoginHistory(userLoginHistoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the UserLoginHistory with the specific Id
	 * 
	 * @param userLoginHistoryToBeUpdated
	 *            the UserLoginHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserLoginHistory(@RequestBody UserLoginHistory userLoginHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginHistoryToBeUpdated.setnull(null);

		UpdateUserLoginHistory command = new UpdateUserLoginHistory(userLoginHistoryToBeUpdated);

		try {
			if(((UserLoginHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userLoginHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String userLoginHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginHistoryId", userLoginHistoryId);
		try {

			Object foundUserLoginHistory = findUserLoginHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundUserLoginHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userLoginHistoryId}")
	public ResponseEntity<Object> deleteUserLoginHistoryByIdUpdated(@PathVariable String userLoginHistoryId) throws Exception {
		DeleteUserLoginHistory command = new DeleteUserLoginHistory(userLoginHistoryId);

		try {
			if (((UserLoginHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UserLoginHistory could not be deleted");

	}

}
