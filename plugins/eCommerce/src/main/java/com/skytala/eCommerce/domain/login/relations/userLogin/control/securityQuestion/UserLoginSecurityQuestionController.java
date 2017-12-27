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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<UserLoginSecurityQuestion>> findUserLoginSecurityQuestionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindUserLoginSecurityQuestionsBy query = new FindUserLoginSecurityQuestionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<UserLoginSecurityQuestion> userLoginSecurityQuestions =((UserLoginSecurityQuestionFound) Scheduler.execute(query).data()).getUserLoginSecurityQuestions();

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
	public ResponseEntity<UserLoginSecurityQuestion> createUserLoginSecurityQuestion(HttpServletRequest request) throws Exception {

		UserLoginSecurityQuestion userLoginSecurityQuestionToBeAdded = new UserLoginSecurityQuestion();
		try {
			userLoginSecurityQuestionToBeAdded = UserLoginSecurityQuestionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<UserLoginSecurityQuestion> createUserLoginSecurityQuestion(@RequestBody UserLoginSecurityQuestion userLoginSecurityQuestionToBeAdded) throws Exception {

		AddUserLoginSecurityQuestion command = new AddUserLoginSecurityQuestion(userLoginSecurityQuestionToBeAdded);
		UserLoginSecurityQuestion userLoginSecurityQuestion = ((UserLoginSecurityQuestionAdded) Scheduler.execute(command).data()).getAddedUserLoginSecurityQuestion();
		
		if (userLoginSecurityQuestion != null) 
			return successful(userLoginSecurityQuestion);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateUserLoginSecurityQuestion(@RequestBody UserLoginSecurityQuestion userLoginSecurityQuestionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		userLoginSecurityQuestionToBeUpdated.setnull(null);

		UpdateUserLoginSecurityQuestion command = new UpdateUserLoginSecurityQuestion(userLoginSecurityQuestionToBeUpdated);

		try {
			if(((UserLoginSecurityQuestionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{userLoginSecurityQuestionId}")
	public ResponseEntity<UserLoginSecurityQuestion> findById(@PathVariable String userLoginSecurityQuestionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("userLoginSecurityQuestionId", userLoginSecurityQuestionId);
		try {

			List<UserLoginSecurityQuestion> foundUserLoginSecurityQuestion = findUserLoginSecurityQuestionsBy(requestParams).getBody();
			if(foundUserLoginSecurityQuestion.size()==1){				return successful(foundUserLoginSecurityQuestion.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{userLoginSecurityQuestionId}")
	public ResponseEntity<String> deleteUserLoginSecurityQuestionByIdUpdated(@PathVariable String userLoginSecurityQuestionId) throws Exception {
		DeleteUserLoginSecurityQuestion command = new DeleteUserLoginSecurityQuestion(userLoginSecurityQuestionId);

		try {
			if (((UserLoginSecurityQuestionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
