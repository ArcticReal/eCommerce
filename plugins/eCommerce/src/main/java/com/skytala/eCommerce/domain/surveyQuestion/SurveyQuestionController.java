package com.skytala.eCommerce.domain.surveyQuestion;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.surveyQuestion.command.AddSurveyQuestion;
import com.skytala.eCommerce.domain.surveyQuestion.command.DeleteSurveyQuestion;
import com.skytala.eCommerce.domain.surveyQuestion.command.UpdateSurveyQuestion;
import com.skytala.eCommerce.domain.surveyQuestion.event.SurveyQuestionAdded;
import com.skytala.eCommerce.domain.surveyQuestion.event.SurveyQuestionDeleted;
import com.skytala.eCommerce.domain.surveyQuestion.event.SurveyQuestionFound;
import com.skytala.eCommerce.domain.surveyQuestion.event.SurveyQuestionUpdated;
import com.skytala.eCommerce.domain.surveyQuestion.mapper.SurveyQuestionMapper;
import com.skytala.eCommerce.domain.surveyQuestion.model.SurveyQuestion;
import com.skytala.eCommerce.domain.surveyQuestion.query.FindSurveyQuestionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyQuestions")
public class SurveyQuestionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestion
	 * @return a List with the SurveyQuestions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyQuestionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionsBy query = new FindSurveyQuestionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestion> surveyQuestions =((SurveyQuestionFound) Scheduler.execute(query).data()).getSurveyQuestions();

		if (surveyQuestions.size() == 1) {
			return ResponseEntity.ok().body(surveyQuestions.get(0));
		}

		return ResponseEntity.ok().body(surveyQuestions);

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
	public ResponseEntity<Object> createSurveyQuestion(HttpServletRequest request) throws Exception {

		SurveyQuestion surveyQuestionToBeAdded = new SurveyQuestion();
		try {
			surveyQuestionToBeAdded = SurveyQuestionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyQuestion(surveyQuestionToBeAdded);

	}

	/**
	 * creates a new SurveyQuestion entry in the ofbiz database
	 * 
	 * @param surveyQuestionToBeAdded
	 *            the SurveyQuestion thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyQuestion(@RequestBody SurveyQuestion surveyQuestionToBeAdded) throws Exception {

		AddSurveyQuestion command = new AddSurveyQuestion(surveyQuestionToBeAdded);
		SurveyQuestion surveyQuestion = ((SurveyQuestionAdded) Scheduler.execute(command).data()).getAddedSurveyQuestion();
		
		if (surveyQuestion != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyQuestion);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyQuestion could not be created.");
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
	public boolean updateSurveyQuestion(HttpServletRequest request) throws Exception {

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

		SurveyQuestion surveyQuestionToBeUpdated = new SurveyQuestion();

		try {
			surveyQuestionToBeUpdated = SurveyQuestionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyQuestion(surveyQuestionToBeUpdated, surveyQuestionToBeUpdated.getSurveyQuestionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyQuestion with the specific Id
	 * 
	 * @param surveyQuestionToBeUpdated
	 *            the SurveyQuestion thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyQuestionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyQuestion(@RequestBody SurveyQuestion surveyQuestionToBeUpdated,
			@PathVariable String surveyQuestionId) throws Exception {

		surveyQuestionToBeUpdated.setSurveyQuestionId(surveyQuestionId);

		UpdateSurveyQuestion command = new UpdateSurveyQuestion(surveyQuestionToBeUpdated);

		try {
			if(((SurveyQuestionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyQuestionId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyQuestionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionId", surveyQuestionId);
		try {

			Object foundSurveyQuestion = findSurveyQuestionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyQuestion);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyQuestionId}")
	public ResponseEntity<Object> deleteSurveyQuestionByIdUpdated(@PathVariable String surveyQuestionId) throws Exception {
		DeleteSurveyQuestion command = new DeleteSurveyQuestion(surveyQuestionId);

		try {
			if (((SurveyQuestionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyQuestion could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/surveyQuestion/\" plus one of the following: "
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
