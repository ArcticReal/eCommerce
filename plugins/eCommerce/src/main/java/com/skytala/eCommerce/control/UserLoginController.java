package com.skytala.eCommerce.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddUserLogin;
import com.skytala.eCommerce.command.DeleteUserLogin;
import com.skytala.eCommerce.command.UpdateUserLogin;
import com.skytala.eCommerce.entity.UserLogin;
import com.skytala.eCommerce.entity.UserLoginMapper;
import com.skytala.eCommerce.event.UserLoginAdded;
import com.skytala.eCommerce.event.UserLoginDeleted;
import com.skytala.eCommerce.event.UserLoginFound;
import com.skytala.eCommerce.event.UserLoginUpdated;
import com.skytala.eCommerce.query.FindUserLoginsBy;

@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

	private static int requestTicketId = 0;
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<UserLogin>> queryReturnVal = new HashMap<>();
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
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public List<UserLogin> findUserLoginsBy(@RequestParam Map<String, String> allRequestParams) {

		FindUserLoginsBy query = new FindUserLoginsBy(allRequestParams);

		int usedTicketId;

		synchronized (UserLoginController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(UserLoginFound.class,
				event -> sendUserLoginsFoundMessage(((UserLoginFound) event).getUserLogins(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}
		return queryReturnVal.remove(usedTicketId);

	}

	public void sendUserLoginsFoundMessage(List<UserLogin> userLogins, int usedTicketId) {
		queryReturnVal.put(usedTicketId, userLogins);
	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/x-www-form-urlencoded")
	public boolean createUserLogin(HttpServletRequest request) {

		UserLogin userLoginToBeAdded = new UserLogin();
		try {
			userLoginToBeAdded = UserLoginMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
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
	public boolean createUserLogin(UserLogin userLoginToBeAdded) {

		AddUserLogin com = new AddUserLogin(userLoginToBeAdded);
		int usedTicketId;

		synchronized (UserLoginController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(UserLoginAdded.class,
				event -> sendUserLoginChangedMessage(((UserLoginAdded) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);

	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateUserLogin(HttpServletRequest request) {

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

		return updateUserLogin(userLoginToBeUpdated);

	}

	/**
	 * Updates the UserLogin with the specific Id
	 * 
	 * @param userLoginToBeUpdated the UserLogin thats to be updated
	 * @return true on success, false on fail
	 */
	public boolean updateUserLogin(UserLogin userLoginToBeUpdated) {

		UpdateUserLogin com = new UpdateUserLogin(userLoginToBeUpdated);

		int usedTicketId;

		synchronized (UserLoginController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(UserLoginUpdated.class,
				event -> sendUserLoginChangedMessage(((UserLoginUpdated) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	/**
	 * removes a UserLogin from the database
	 * 
	 * @param userLoginId:
	 *            the id of the UserLogin thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deleteuserLoginById(@RequestParam(value = "userLoginId") String userLoginId) {

		DeleteUserLogin com = new DeleteUserLogin(userLoginId);

		int usedTicketId;

		synchronized (UserLoginController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(UserLoginDeleted.class,
				event -> sendUserLoginChangedMessage(((UserLoginDeleted) event).isSuccess(), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return commandReturnVal.remove(usedTicketId);
	}

	public void sendUserLoginChangedMessage(boolean success, int usedTicketId) {
		commandReturnVal.put(usedTicketId, success);
	}

	@RequestMapping(value = (" * "))
	public String returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			return "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri + "\"!\n"
					+ "Please use " + validRequests.get(usedRequest) + "!";

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

		return returnVal;

	}
}
