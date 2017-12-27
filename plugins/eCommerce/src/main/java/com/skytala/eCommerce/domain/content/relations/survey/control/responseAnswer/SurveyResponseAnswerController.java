package com.skytala.eCommerce.domain.content.relations.survey.control.responseAnswer;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.responseAnswer.AddSurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.survey.command.responseAnswer.DeleteSurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.survey.command.responseAnswer.UpdateSurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer.SurveyResponseAnswerAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer.SurveyResponseAnswerDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer.SurveyResponseAnswerFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer.SurveyResponseAnswerUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.responseAnswer.SurveyResponseAnswerMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.responseAnswer.SurveyResponseAnswer;
import com.skytala.eCommerce.domain.content.relations.survey.query.responseAnswer.FindSurveyResponseAnswersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyResponseAnswers")
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
	@GetMapping("/find")
	public ResponseEntity<List<SurveyResponseAnswer>> findSurveyResponseAnswersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyResponseAnswersBy query = new FindSurveyResponseAnswersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyResponseAnswer> surveyResponseAnswers =((SurveyResponseAnswerFound) Scheduler.execute(query).data()).getSurveyResponseAnswers();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SurveyResponseAnswer> createSurveyResponseAnswer(HttpServletRequest request) throws Exception {

		SurveyResponseAnswer surveyResponseAnswerToBeAdded = new SurveyResponseAnswer();
		try {
			surveyResponseAnswerToBeAdded = SurveyResponseAnswerMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SurveyResponseAnswer> createSurveyResponseAnswer(@RequestBody SurveyResponseAnswer surveyResponseAnswerToBeAdded) throws Exception {

		AddSurveyResponseAnswer command = new AddSurveyResponseAnswer(surveyResponseAnswerToBeAdded);
		SurveyResponseAnswer surveyResponseAnswer = ((SurveyResponseAnswerAdded) Scheduler.execute(command).data()).getAddedSurveyResponseAnswer();
		
		if (surveyResponseAnswer != null) 
			return successful(surveyResponseAnswer);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSurveyResponseAnswer(@RequestBody SurveyResponseAnswer surveyResponseAnswerToBeUpdated,
			@PathVariable String surveyMultiRespColId) throws Exception {

		surveyResponseAnswerToBeUpdated.setSurveyMultiRespColId(surveyMultiRespColId);

		UpdateSurveyResponseAnswer command = new UpdateSurveyResponseAnswer(surveyResponseAnswerToBeUpdated);

		try {
			if(((SurveyResponseAnswerUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyResponseAnswerId}")
	public ResponseEntity<SurveyResponseAnswer> findById(@PathVariable String surveyResponseAnswerId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyResponseAnswerId", surveyResponseAnswerId);
		try {

			List<SurveyResponseAnswer> foundSurveyResponseAnswer = findSurveyResponseAnswersBy(requestParams).getBody();
			if(foundSurveyResponseAnswer.size()==1){				return successful(foundSurveyResponseAnswer.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyResponseAnswerId}")
	public ResponseEntity<String> deleteSurveyResponseAnswerByIdUpdated(@PathVariable String surveyResponseAnswerId) throws Exception {
		DeleteSurveyResponseAnswer command = new DeleteSurveyResponseAnswer(surveyResponseAnswerId);

		try {
			if (((SurveyResponseAnswerDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
