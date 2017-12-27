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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<UserLoginPasswordHistory>> findUserLoginPasswordHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginPasswordHistorysBy query = new FindUserLoginPasswordHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginPasswordHistory> userLoginPasswordHistorys =((UserLoginPasswordHistoryFound) Scheduler.execute(query).data()).getUserLoginPasswordHistorys();

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
	public ResponseEntity<UserLoginPasswordHistory> createUserLoginPasswordHistory(HttpServletRequest request) throws Exception {

		UserLoginPasswordHistory userLoginPasswordHistoryToBeAdded = new UserLoginPasswordHistory();
		try {
			userLoginPasswordHistoryToBeAdded = UserLoginPasswordHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<UserLoginPasswordHistory> createUserLoginPasswordHistory(@RequestBody UserLoginPasswordHistory userLoginPasswordHistoryToBeAdded) throws Exception {

		AddUserLoginPasswordHistory command = new AddUserLoginPasswordHistory(userLoginPasswordHistoryToBeAdded);
		UserLoginPasswordHistory userLoginPasswordHistory = ((UserLoginPasswordHistoryAdded) Scheduler.execute(command).data()).getAddedUserLoginPasswordHistory();
		
		if (userLoginPasswordHistory != null) 
			return successful(userLoginPasswordHistory);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateUserLoginPasswordHistory(@RequestBody UserLoginPasswordHistory userLoginPasswordHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginPasswordHistoryToBeUpdated.setnull(null);

		UpdateUserLoginPasswordHistory command = new UpdateUserLoginPasswordHistory(userLoginPasswordHistoryToBeUpdated);

		try {
			if(((UserLoginPasswordHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{userLoginPasswordHistoryId}")
	public ResponseEntity<UserLoginPasswordHistory> findById(@PathVariable String userLoginPasswordHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginPasswordHistoryId", userLoginPasswordHistoryId);
		try {

			List<UserLoginPasswordHistory> foundUserLoginPasswordHistory = findUserLoginPasswordHistorysBy(requestParams).getBody();
			if(foundUserLoginPasswordHistory.size()==1){				return successful(foundUserLoginPasswordHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{userLoginPasswordHistoryId}")
	public ResponseEntity<String> deleteUserLoginPasswordHistoryByIdUpdated(@PathVariable String userLoginPasswordHistoryId) throws Exception {
		DeleteUserLoginPasswordHistory command = new DeleteUserLoginPasswordHistory(userLoginPasswordHistoryId);

		try {
			if (((UserLoginPasswordHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
