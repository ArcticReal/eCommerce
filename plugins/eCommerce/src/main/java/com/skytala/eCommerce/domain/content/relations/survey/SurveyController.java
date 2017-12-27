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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/surveys")
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
	@GetMapping("/find")
	public ResponseEntity<List<Survey>> findSurveysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveysBy query = new FindSurveysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Survey> surveys =((SurveyFound) Scheduler.execute(query).data()).getSurveys();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Survey> createSurvey(HttpServletRequest request) throws Exception {

		Survey surveyToBeAdded = new Survey();
		try {
			surveyToBeAdded = SurveyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<Survey> createSurvey(@RequestBody Survey surveyToBeAdded) throws Exception {

		AddSurvey command = new AddSurvey(surveyToBeAdded);
		Survey survey = ((SurveyAdded) Scheduler.execute(command).data()).getAddedSurvey();
		
		if (survey != null) 
			return successful(survey);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSurvey(@RequestBody Survey surveyToBeUpdated,
			@PathVariable String surveyId) throws Exception {

		surveyToBeUpdated.setSurveyId(surveyId);

		UpdateSurvey command = new UpdateSurvey(surveyToBeUpdated);

		try {
			if(((SurveyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyId}")
	public ResponseEntity<Survey> findById(@PathVariable String surveyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyId", surveyId);
		try {

			List<Survey> foundSurvey = findSurveysBy(requestParams).getBody();
			if(foundSurvey.size()==1){				return successful(foundSurvey.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyId}")
	public ResponseEntity<String> deleteSurveyByIdUpdated(@PathVariable String surveyId) throws Exception {
		DeleteSurvey command = new DeleteSurvey(surveyId);

		try {
			if (((SurveyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
