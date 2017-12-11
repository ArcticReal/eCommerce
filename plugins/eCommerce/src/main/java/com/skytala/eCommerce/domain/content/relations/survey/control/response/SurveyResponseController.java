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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findSurveyResponsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyResponsesBy query = new FindSurveyResponsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyResponse> surveyResponses =((SurveyResponseFound) Scheduler.execute(query).data()).getSurveyResponses();

		if (surveyResponses.size() == 1) {
			return ResponseEntity.ok().body(surveyResponses.get(0));
		}

		return ResponseEntity.ok().body(surveyResponses);

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
	public ResponseEntity<Object> createSurveyResponse(HttpServletRequest request) throws Exception {

		SurveyResponse surveyResponseToBeAdded = new SurveyResponse();
		try {
			surveyResponseToBeAdded = SurveyResponseMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyResponse(surveyResponseToBeAdded);

	}

	/**
	 * creates a new SurveyResponse entry in the ofbiz database
	 * 
	 * @param surveyResponseToBeAdded
	 *            the SurveyResponse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyResponse(@RequestBody SurveyResponse surveyResponseToBeAdded) throws Exception {

		AddSurveyResponse command = new AddSurveyResponse(surveyResponseToBeAdded);
		SurveyResponse surveyResponse = ((SurveyResponseAdded) Scheduler.execute(command).data()).getAddedSurveyResponse();
		
		if (surveyResponse != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyResponse);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyResponse could not be created.");
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
	public boolean updateSurveyResponse(HttpServletRequest request) throws Exception {

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

		SurveyResponse surveyResponseToBeUpdated = new SurveyResponse();

		try {
			surveyResponseToBeUpdated = SurveyResponseMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyResponse(surveyResponseToBeUpdated, surveyResponseToBeUpdated.getSurveyResponseId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSurveyResponse(@RequestBody SurveyResponse surveyResponseToBeUpdated,
			@PathVariable String surveyResponseId) throws Exception {

		surveyResponseToBeUpdated.setSurveyResponseId(surveyResponseId);

		UpdateSurveyResponse command = new UpdateSurveyResponse(surveyResponseToBeUpdated);

		try {
			if(((SurveyResponseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{surveyResponseId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyResponseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyResponseId", surveyResponseId);
		try {

			Object foundSurveyResponse = findSurveyResponsesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyResponse);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{surveyResponseId}")
	public ResponseEntity<Object> deleteSurveyResponseByIdUpdated(@PathVariable String surveyResponseId) throws Exception {
		DeleteSurveyResponse command = new DeleteSurveyResponse(surveyResponseId);

		try {
			if (((SurveyResponseDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyResponse could not be deleted");

	}

}
