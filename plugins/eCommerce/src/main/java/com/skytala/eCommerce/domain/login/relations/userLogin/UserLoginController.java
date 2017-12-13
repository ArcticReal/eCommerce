package com.skytala.eCommerce.domain.login.relations.userLogin;

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
import com.skytala.eCommerce.domain.login.relations.userLogin.command.AddUserLogin;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.DeleteUserLogin;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.UpdateUserLogin;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginDeleted;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.UserLoginMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.UserLogin;
import com.skytala.eCommerce.domain.login.relations.userLogin.query.FindUserLoginsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/login/userLogins")
public class UserLoginController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UserLoginController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UserLogin
	 * @return a List with the UserLogins
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<UserLogin>> findUserLoginsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginsBy query = new FindUserLoginsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLogin> userLogins =((UserLoginFound) Scheduler.execute(query).data()).getUserLogins();

		return ResponseEntity.ok().body(userLogins);

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
	public ResponseEntity<UserLogin> createUserLogin(HttpServletRequest request) throws Exception {

		UserLogin userLoginToBeAdded = new UserLogin();
		try {
			userLoginToBeAdded = UserLoginMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createUserLogin(userLoginToBeAdded);

	}

	/**
	 * creates a new UserLogin entry in the ofbiz database
	 * 
	 * @param userLoginToBeAdded
	 *            the UserLogin thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserLogin> createUserLogin(@RequestBody UserLogin userLoginToBeAdded) throws Exception {

		AddUserLogin command = new AddUserLogin(userLoginToBeAdded);
		UserLogin userLogin = ((UserLoginAdded) Scheduler.execute(command).data()).getAddedUserLogin();
		
		if (userLogin != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(userLogin);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
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
	public boolean updateUserLogin(HttpServletRequest request) throws Exception {

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

		UserLogin userLoginToBeUpdated = new UserLogin();

		try {
			userLoginToBeUpdated = UserLoginMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUserLogin(userLoginToBeUpdated, userLoginToBeUpdated.getUserLoginId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the UserLogin with the specific Id
	 * 
	 * @param userLoginToBeUpdated
	 *            the UserLogin thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{userLoginId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserLogin(@RequestBody UserLogin userLoginToBeUpdated,
			@PathVariable String userLoginId) throws Exception {

		userLoginToBeUpdated.setUserLoginId(userLoginId);

		UpdateUserLogin command = new UpdateUserLogin(userLoginToBeUpdated);

		try {
			if(((UserLoginUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userLoginId}")
	public ResponseEntity<UserLogin> findById(@PathVariable String userLoginId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginId", userLoginId);
		try {

			List<UserLogin> foundUserLogins = findUserLoginsBy(requestParams).getBody();
			if(foundUserLogins.size()==1)
				return successful(foundUserLogins.get(0));
			else
				return notFound();

		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userLoginId}")
	public ResponseEntity<Object> deleteUserLoginByIdUpdated(@PathVariable String userLoginId) throws Exception {
		DeleteUserLogin command = new DeleteUserLogin(userLoginId);

		try {
			if (((UserLoginDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UserLogin could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/userLogin/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
