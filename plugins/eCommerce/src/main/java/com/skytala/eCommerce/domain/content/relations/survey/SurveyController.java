package com.skytala.eCommerce.domain.content.relations.survey;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.AddSurvey;
import com.skytala.eCommerce.domain.content.relations.survey.command.DeleteSurvey;
import com.skytala.eCommerce.domain.content.relations.survey.command.UpdateSurvey;
import com.skytala.eCommerce.domain.content.relations.survey.event.SurveyAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.SurveyDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.SurveyFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.SurveyUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.SurveyMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.Survey;
import com.skytala.eCommerce.domain.content.relations.survey.query.FindSurveysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Survey
	 * @return a List with the Surveys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveysBy query = new FindSurveysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Survey> surveys =((SurveyFound) Scheduler.execute(query).data()).getSurveys();

		if (surveys.size() == 1) {
			return ResponseEntity.ok().body(surveys.get(0));
		}

		return ResponseEntity.ok().body(surveys);

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
	public ResponseEntity<Object> createSurvey(HttpServletRequest request) throws Exception {

		Survey surveyToBeAdded = new Survey();
		try {
			surveyToBeAdded = SurveyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurvey(surveyToBeAdded);

	}

	/**
	 * creates a new Survey entry in the ofbiz database
	 * 
	 * @param surveyToBeAdded
	 *            the Survey thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurvey(@RequestBody Survey surveyToBeAdded) throws Exception {

		AddSurvey command = new AddSurvey(surveyToBeAdded);
		Survey survey = ((SurveyAdded) Scheduler.execute(command).data()).getAddedSurvey();
		
		if (survey != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(survey);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Survey could not be created.");
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
	public boolean updateSurvey(HttpServletRequest request) throws Exception {

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

		Survey surveyToBeUpdated = new Survey();

		try {
			surveyToBeUpdated = SurveyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurvey(surveyToBeUpdated, surveyToBeUpdated.getSurveyId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Survey with the specific Id
	 * 
	 * @param surveyToBeUpdated
	 *            the Survey thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurvey(@RequestBody Survey surveyToBeUpdated,
			@PathVariable String surveyId) throws Exception {

		surveyToBeUpdated.setSurveyId(surveyId);

		UpdateSurvey command = new UpdateSurvey(surveyToBeUpdated);

		try {
			if(((SurveyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyId", surveyId);
		try {

			Object foundSurvey = findSurveysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurvey);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyId}")
	public ResponseEntity<Object> deleteSurveyByIdUpdated(@PathVariable String surveyId) throws Exception {
		DeleteSurvey command = new DeleteSurvey(surveyId);

		try {
			if (((SurveyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Survey could not be deleted");

	}

}
