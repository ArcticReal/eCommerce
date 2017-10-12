package com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer;

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
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.command.AddSurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.command.DeleteSurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.command.UpdateSurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.event.SurveyResponseAnswerAdded;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.event.SurveyResponseAnswerDeleted;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.event.SurveyResponseAnswerFound;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.event.SurveyResponseAnswerUpdated;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.mapper.SurveyResponseAnswerMapper;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.model.SurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.query.FindSurveyResponseAnswersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyResponseAnswers")
public class SurveyResponseAnswerController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyResponseAnswerController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyResponseAnswer
	 * @return a List with the SurveyResponseAnswers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyResponseAnswersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyResponseAnswersBy query = new FindSurveyResponseAnswersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyResponseAnswer> surveyResponseAnswers =((SurveyResponseAnswerFound) Scheduler.execute(query).data()).getSurveyResponseAnswers();

		if (surveyResponseAnswers.size() == 1) {
			return ResponseEntity.ok().body(surveyResponseAnswers.get(0));
		}

		return ResponseEntity.ok().body(surveyResponseAnswers);

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
	public ResponseEntity<Object> createSurveyResponseAnswer(HttpServletRequest request) throws Exception {

		SurveyResponseAnswer surveyResponseAnswerToBeAdded = new SurveyResponseAnswer();
		try {
			surveyResponseAnswerToBeAdded = SurveyResponseAnswerMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyResponseAnswer(surveyResponseAnswerToBeAdded);

	}

	/**
	 * creates a new SurveyResponseAnswer entry in the ofbiz database
	 * 
	 * @param surveyResponseAnswerToBeAdded
	 *            the SurveyResponseAnswer thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyResponseAnswer(@RequestBody SurveyResponseAnswer surveyResponseAnswerToBeAdded) throws Exception {

		AddSurveyResponseAnswer command = new AddSurveyResponseAnswer(surveyResponseAnswerToBeAdded);
		SurveyResponseAnswer surveyResponseAnswer = ((SurveyResponseAnswerAdded) Scheduler.execute(command).data()).getAddedSurveyResponseAnswer();
		
		if (surveyResponseAnswer != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyResponseAnswer);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyResponseAnswer could not be created.");
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
	public boolean updateSurveyResponseAnswer(HttpServletRequest request) throws Exception {

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

		SurveyResponseAnswer surveyResponseAnswerToBeUpdated = new SurveyResponseAnswer();

		try {
			surveyResponseAnswerToBeUpdated = SurveyResponseAnswerMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyResponseAnswer(surveyResponseAnswerToBeUpdated, surveyResponseAnswerToBeUpdated.getSurveyMultiRespColId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyResponseAnswer with the specific Id
	 * 
	 * @param surveyResponseAnswerToBeUpdated
	 *            the SurveyResponseAnswer thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyMultiRespColId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyResponseAnswer(@RequestBody SurveyResponseAnswer surveyResponseAnswerToBeUpdated,
			@PathVariable String surveyMultiRespColId) throws Exception {

		surveyResponseAnswerToBeUpdated.setSurveyMultiRespColId(surveyMultiRespColId);

		UpdateSurveyResponseAnswer command = new UpdateSurveyResponseAnswer(surveyResponseAnswerToBeUpdated);

		try {
			if(((SurveyResponseAnswerUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyResponseAnswerId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyResponseAnswerId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyResponseAnswerId", surveyResponseAnswerId);
		try {

			Object foundSurveyResponseAnswer = findSurveyResponseAnswersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyResponseAnswer);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyResponseAnswerId}")
	public ResponseEntity<Object> deleteSurveyResponseAnswerByIdUpdated(@PathVariable String surveyResponseAnswerId) throws Exception {
		DeleteSurveyResponseAnswer command = new DeleteSurveyResponseAnswer(surveyResponseAnswerId);

		try {
			if (((SurveyResponseAnswerDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyResponseAnswer could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/surveyResponseAnswer/\" plus one of the following: "
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
