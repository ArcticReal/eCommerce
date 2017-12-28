package com.skytala.eCommerce.domain.content.relations.survey.control.response;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.response.AddSurveyResponse;
import com.skytala.eCommerce.domain.content.relations.survey.command.response.DeleteSurveyResponse;
import com.skytala.eCommerce.domain.content.relations.survey.command.response.UpdateSurveyResponse;
import com.skytala.eCommerce.domain.content.relations.survey.event.response.SurveyResponseAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.response.SurveyResponseDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.response.SurveyResponseFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.response.SurveyResponseUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.response.SurveyResponseMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.response.SurveyResponse;
import com.skytala.eCommerce.domain.content.relations.survey.query.response.FindSurveyResponsesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyResponses")
public class SurveyResponseController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyResponseController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyResponse
	 * @return a List with the SurveyResponses
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyResponse>> findSurveyResponsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyResponsesBy query = new FindSurveyResponsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyResponse> surveyResponses =((SurveyResponseFound) Scheduler.execute(query).data()).getSurveyResponses();

		return ResponseEntity.ok().body(surveyResponses);

	}

	/**
	 * creates a new SurveyResponse entry in the ofbiz database
	 * 
	 * @param surveyResponseToBeAdded
	 *            the SurveyResponse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyResponse> createSurveyResponse(@RequestBody SurveyResponse surveyResponseToBeAdded) throws Exception {

		AddSurveyResponse command = new AddSurveyResponse(surveyResponseToBeAdded);
		SurveyResponse surveyResponse = ((SurveyResponseAdded) Scheduler.execute(command).data()).getAddedSurveyResponse();
		
		if (surveyResponse != null) 
			return successful(surveyResponse);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyResponse with the specific Id
	 * 
	 * @param surveyResponseToBeUpdated
	 *            the SurveyResponse thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyResponseId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyResponse(@RequestBody SurveyResponse surveyResponseToBeUpdated,
			@PathVariable String surveyResponseId) throws Exception {

		surveyResponseToBeUpdated.setSurveyResponseId(surveyResponseId);

		UpdateSurveyResponse command = new UpdateSurveyResponse(surveyResponseToBeUpdated);

		try {
			if(((SurveyResponseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyResponseId}")
	public ResponseEntity<SurveyResponse> findById(@PathVariable String surveyResponseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyResponseId", surveyResponseId);
		try {

			List<SurveyResponse> foundSurveyResponse = findSurveyResponsesBy(requestParams).getBody();
			if(foundSurveyResponse.size()==1){				return successful(foundSurveyResponse.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyResponseId}")
	public ResponseEntity<String> deleteSurveyResponseByIdUpdated(@PathVariable String surveyResponseId) throws Exception {
		DeleteSurveyResponse command = new DeleteSurveyResponse(surveyResponseId);

		try {
			if (((SurveyResponseDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
