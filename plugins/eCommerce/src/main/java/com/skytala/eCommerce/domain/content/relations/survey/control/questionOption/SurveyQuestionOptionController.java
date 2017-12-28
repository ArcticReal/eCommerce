package com.skytala.eCommerce.domain.content.relations.survey.control.questionOption;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.questionOption.AddSurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionOption.DeleteSurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionOption.UpdateSurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionOption.SurveyQuestionOptionAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionOption.SurveyQuestionOptionDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionOption.SurveyQuestionOptionFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionOption.SurveyQuestionOptionUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionOption.SurveyQuestionOptionMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.survey.query.questionOption.FindSurveyQuestionOptionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyQuestionOptions")
public class SurveyQuestionOptionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionOptionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestionOption
	 * @return a List with the SurveyQuestionOptions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyQuestionOption>> findSurveyQuestionOptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionOptionsBy query = new FindSurveyQuestionOptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionOption> surveyQuestionOptions =((SurveyQuestionOptionFound) Scheduler.execute(query).data()).getSurveyQuestionOptions();

		return ResponseEntity.ok().body(surveyQuestionOptions);

	}

	/**
	 * creates a new SurveyQuestionOption entry in the ofbiz database
	 * 
	 * @param surveyQuestionOptionToBeAdded
	 *            the SurveyQuestionOption thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyQuestionOption> createSurveyQuestionOption(@RequestBody SurveyQuestionOption surveyQuestionOptionToBeAdded) throws Exception {

		AddSurveyQuestionOption command = new AddSurveyQuestionOption(surveyQuestionOptionToBeAdded);
		SurveyQuestionOption surveyQuestionOption = ((SurveyQuestionOptionAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionOption();
		
		if (surveyQuestionOption != null) 
			return successful(surveyQuestionOption);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyQuestionOption with the specific Id
	 * 
	 * @param surveyQuestionOptionToBeUpdated
	 *            the SurveyQuestionOption thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyOptionSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyQuestionOption(@RequestBody SurveyQuestionOption surveyQuestionOptionToBeUpdated,
			@PathVariable String surveyOptionSeqId) throws Exception {

		surveyQuestionOptionToBeUpdated.setSurveyOptionSeqId(surveyOptionSeqId);

		UpdateSurveyQuestionOption command = new UpdateSurveyQuestionOption(surveyQuestionOptionToBeUpdated);

		try {
			if(((SurveyQuestionOptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyQuestionOptionId}")
	public ResponseEntity<SurveyQuestionOption> findById(@PathVariable String surveyQuestionOptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionOptionId", surveyQuestionOptionId);
		try {

			List<SurveyQuestionOption> foundSurveyQuestionOption = findSurveyQuestionOptionsBy(requestParams).getBody();
			if(foundSurveyQuestionOption.size()==1){				return successful(foundSurveyQuestionOption.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyQuestionOptionId}")
	public ResponseEntity<String> deleteSurveyQuestionOptionByIdUpdated(@PathVariable String surveyQuestionOptionId) throws Exception {
		DeleteSurveyQuestionOption command = new DeleteSurveyQuestionOption(surveyQuestionOptionId);

		try {
			if (((SurveyQuestionOptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
