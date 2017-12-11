package com.skytala.eCommerce.domain.login.relations.userLogin.control.passwordHistory;

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
import com.skytala.eCommerce.domain.login.relations.userLogin.command.passwordHistory.AddUserLoginPasswordHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.passwordHistory.DeleteUserLoginPasswordHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.passwordHistory.UpdateUserLoginPasswordHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryDeleted;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.passwordHistory.UserLoginPasswordHistoryMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;
import com.skytala.eCommerce.domain.login.relations.userLogin.query.passwordHistory.FindUserLoginPasswordHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/login/userLogin/userLoginPasswordHistorys")
public class UserLoginPasswordHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UserLoginPasswordHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UserLoginPasswordHistory
	 * @return a List with the UserLoginPasswordHistorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findUserLoginPasswordHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginPasswordHistorysBy query = new FindUserLoginPasswordHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginPasswordHistory> userLoginPasswordHistorys =((UserLoginPasswordHistoryFound) Scheduler.execute(query).data()).getUserLoginPasswordHistorys();

		if (userLoginPasswordHistorys.size() == 1) {
			return ResponseEntity.ok().body(userLoginPasswordHistorys.get(0));
		}

		return ResponseEntity.ok().body(userLoginPasswordHistorys);

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
	public ResponseEntity<Object> createUserLoginPasswordHistory(HttpServletRequest request) throws Exception {

		UserLoginPasswordHistory userLoginPasswordHistoryToBeAdded = new UserLoginPasswordHistory();
		try {
			userLoginPasswordHistoryToBeAdded = UserLoginPasswordHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createUserLoginPasswordHistory(userLoginPasswordHistoryToBeAdded);

	}

	/**
	 * creates a new UserLoginPasswordHistory entry in the ofbiz database
	 * 
	 * @param userLoginPasswordHistoryToBeAdded
	 *            the UserLoginPasswordHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createUserLoginPasswordHistory(@RequestBody UserLoginPasswordHistory userLoginPasswordHistoryToBeAdded) throws Exception {

		AddUserLoginPasswordHistory command = new AddUserLoginPasswordHistory(userLoginPasswordHistoryToBeAdded);
		UserLoginPasswordHistory userLoginPasswordHistory = ((UserLoginPasswordHistoryAdded) Scheduler.execute(command).data()).getAddedUserLoginPasswordHistory();
		
		if (userLoginPasswordHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(userLoginPasswordHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("UserLoginPasswordHistory could not be created.");
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
	public boolean updateUserLoginPasswordHistory(HttpServletRequest request) throws Exception {

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

		UserLoginPasswordHistory userLoginPasswordHistoryToBeUpdated = new UserLoginPasswordHistory();

		try {
			userLoginPasswordHistoryToBeUpdated = UserLoginPasswordHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUserLoginPasswordHistory(userLoginPasswordHistoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the UserLoginPasswordHistory with the specific Id
	 * 
	 * @param userLoginPasswordHistoryToBeUpdated
	 *            the UserLoginPasswordHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserLoginPasswordHistory(@RequestBody UserLoginPasswordHistory userLoginPasswordHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginPasswordHistoryToBeUpdated.setnull(null);

		UpdateUserLoginPasswordHistory command = new UpdateUserLoginPasswordHistory(userLoginPasswordHistoryToBeUpdated);

		try {
			if(((UserLoginPasswordHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{userLoginPasswordHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String userLoginPasswordHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginPasswordHistoryId", userLoginPasswordHistoryId);
		try {

			Object foundUserLoginPasswordHistory = findUserLoginPasswordHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundUserLoginPasswordHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{userLoginPasswordHistoryId}")
	public ResponseEntity<Object> deleteUserLoginPasswordHistoryByIdUpdated(@PathVariable String userLoginPasswordHistoryId) throws Exception {
		DeleteUserLoginPasswordHistory command = new DeleteUserLoginPasswordHistory(userLoginPasswordHistoryId);

		try {
			if (((UserLoginPasswordHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UserLoginPasswordHistory could not be deleted");

	}

}
