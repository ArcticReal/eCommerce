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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<UserLoginHistory>> findUserLoginHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginHistorysBy query = new FindUserLoginHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginHistory> userLoginHistorys =((UserLoginHistoryFound) Scheduler.execute(query).data()).getUserLoginHistorys();

		return ResponseEntity.ok().body(userLoginHistorys);

	}

	/**
	 * creates a new UserLoginHistory entry in the ofbiz database
	 * 
	 * @param userLoginHistoryToBeAdded
	 *            the UserLoginHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserLoginHistory> createUserLoginHistory(@RequestBody UserLoginHistory userLoginHistoryToBeAdded) throws Exception {

		AddUserLoginHistory command = new AddUserLoginHistory(userLoginHistoryToBeAdded);
		UserLoginHistory userLoginHistory = ((UserLoginHistoryAdded) Scheduler.execute(command).data()).getAddedUserLoginHistory();
		
		if (userLoginHistory != null) 
			return successful(userLoginHistory);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateUserLoginHistory(@RequestBody UserLoginHistory userLoginHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginHistoryToBeUpdated.setnull(null);

		UpdateUserLoginHistory command = new UpdateUserLoginHistory(userLoginHistoryToBeUpdated);

		try {
			if(((UserLoginHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{userLoginHistoryId}")
	public ResponseEntity<UserLoginHistory> findById(@PathVariable String userLoginHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginHistoryId", userLoginHistoryId);
		try {

			List<UserLoginHistory> foundUserLoginHistory = findUserLoginHistorysBy(requestParams).getBody();
			if(foundUserLoginHistory.size()==1){				return successful(foundUserLoginHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{userLoginHistoryId}")
	public ResponseEntity<String> deleteUserLoginHistoryByIdUpdated(@PathVariable String userLoginHistoryId) throws Exception {
		DeleteUserLoginHistory command = new DeleteUserLoginHistory(userLoginHistoryId);

		try {
			if (((UserLoginHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
