package com.skytala.eCommerce.domain.login.relations.userLogin.control.securityQuestion;

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
import com.skytala.eCommerce.domain.login.relations.userLogin.command.securityQuestion.AddUserLoginSecurityQuestion;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.securityQuestion.DeleteUserLoginSecurityQuestion;
import com.skytala.eCommerce.domain.login.relations.userLogin.command.securityQuestion.UpdateUserLoginSecurityQuestion;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionDeleted;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityQuestion.UserLoginSecurityQuestionMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;
import com.skytala.eCommerce.domain.login.relations.userLogin.query.securityQuestion.FindUserLoginSecurityQuestionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/login/userLogin/userLoginSecurityQuestions")
public class UserLoginSecurityQuestionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public UserLoginSecurityQuestionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a UserLoginSecurityQuestion
	 * @return a List with the UserLoginSecurityQuestions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findUserLoginSecurityQuestionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginSecurityQuestionsBy query = new FindUserLoginSecurityQuestionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginSecurityQuestion> userLoginSecurityQuestions =((UserLoginSecurityQuestionFound) Scheduler.execute(query).data()).getUserLoginSecurityQuestions();

		if (userLoginSecurityQuestions.size() == 1) {
			return ResponseEntity.ok().body(userLoginSecurityQuestions.get(0));
		}

		return ResponseEntity.ok().body(userLoginSecurityQuestions);

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
	public ResponseEntity<Object> createUserLoginSecurityQuestion(HttpServletRequest request) throws Exception {

		UserLoginSecurityQuestion userLoginSecurityQuestionToBeAdded = new UserLoginSecurityQuestion();
		try {
			userLoginSecurityQuestionToBeAdded = UserLoginSecurityQuestionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createUserLoginSecurityQuestion(userLoginSecurityQuestionToBeAdded);

	}

	/**
	 * creates a new UserLoginSecurityQuestion entry in the ofbiz database
	 * 
	 * @param userLoginSecurityQuestionToBeAdded
	 *            the UserLoginSecurityQuestion thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createUserLoginSecurityQuestion(@RequestBody UserLoginSecurityQuestion userLoginSecurityQuestionToBeAdded) throws Exception {

		AddUserLoginSecurityQuestion command = new AddUserLoginSecurityQuestion(userLoginSecurityQuestionToBeAdded);
		UserLoginSecurityQuestion userLoginSecurityQuestion = ((UserLoginSecurityQuestionAdded) Scheduler.execute(command).data()).getAddedUserLoginSecurityQuestion();
		
		if (userLoginSecurityQuestion != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(userLoginSecurityQuestion);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("UserLoginSecurityQuestion could not be created.");
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
	public boolean updateUserLoginSecurityQuestion(HttpServletRequest request) throws Exception {

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

		UserLoginSecurityQuestion userLoginSecurityQuestionToBeUpdated = new UserLoginSecurityQuestion();

		try {
			userLoginSecurityQuestionToBeUpdated = UserLoginSecurityQuestionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateUserLoginSecurityQuestion(userLoginSecurityQuestionToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the UserLoginSecurityQuestion with the specific Id
	 * 
	 * @param userLoginSecurityQuestionToBeUpdated
	 *            the UserLoginSecurityQuestion thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateUserLoginSecurityQuestion(@RequestBody UserLoginSecurityQuestion userLoginSecurityQuestionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginSecurityQuestionToBeUpdated.setnull(null);

		UpdateUserLoginSecurityQuestion command = new UpdateUserLoginSecurityQuestion(userLoginSecurityQuestionToBeUpdated);

		try {
			if(((UserLoginSecurityQuestionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{userLoginSecurityQuestionId}")
	public ResponseEntity<Object> findById(@PathVariable String userLoginSecurityQuestionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginSecurityQuestionId", userLoginSecurityQuestionId);
		try {

			Object foundUserLoginSecurityQuestion = findUserLoginSecurityQuestionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundUserLoginSecurityQuestion);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{userLoginSecurityQuestionId}")
	public ResponseEntity<Object> deleteUserLoginSecurityQuestionByIdUpdated(@PathVariable String userLoginSecurityQuestionId) throws Exception {
		DeleteUserLoginSecurityQuestion command = new DeleteUserLoginSecurityQuestion(userLoginSecurityQuestionId);

		try {
			if (((UserLoginSecurityQuestionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("UserLoginSecurityQuestion could not be deleted");

	}

}
