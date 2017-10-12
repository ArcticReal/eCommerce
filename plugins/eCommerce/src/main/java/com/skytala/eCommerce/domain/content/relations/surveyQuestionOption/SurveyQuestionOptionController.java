package com.skytala.eCommerce.domain.content.relations.surveyQuestionOption;

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
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.command.AddSurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.command.DeleteSurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.command.UpdateSurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event.SurveyQuestionOptionAdded;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event.SurveyQuestionOptionDeleted;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event.SurveyQuestionOptionFound;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.event.SurveyQuestionOptionUpdated;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.mapper.SurveyQuestionOptionMapper;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.model.SurveyQuestionOption;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionOption.query.FindSurveyQuestionOptionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyQuestionOptions")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyQuestionOptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionOptionsBy query = new FindSurveyQuestionOptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionOption> surveyQuestionOptions =((SurveyQuestionOptionFound) Scheduler.execute(query).data()).getSurveyQuestionOptions();

		if (surveyQuestionOptions.size() == 1) {
			return ResponseEntity.ok().body(surveyQuestionOptions.get(0));
		}

		return ResponseEntity.ok().body(surveyQuestionOptions);

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
	public ResponseEntity<Object> createSurveyQuestionOption(HttpServletRequest request) throws Exception {

		SurveyQuestionOption surveyQuestionOptionToBeAdded = new SurveyQuestionOption();
		try {
			surveyQuestionOptionToBeAdded = SurveyQuestionOptionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyQuestionOption(surveyQuestionOptionToBeAdded);

	}

	/**
	 * creates a new SurveyQuestionOption entry in the ofbiz database
	 * 
	 * @param surveyQuestionOptionToBeAdded
	 *            the SurveyQuestionOption thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyQuestionOption(@RequestBody SurveyQuestionOption surveyQuestionOptionToBeAdded) throws Exception {

		AddSurveyQuestionOption command = new AddSurveyQuestionOption(surveyQuestionOptionToBeAdded);
		SurveyQuestionOption surveyQuestionOption = ((SurveyQuestionOptionAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionOption();
		
		if (surveyQuestionOption != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyQuestionOption);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyQuestionOption could not be created.");
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
	public boolean updateSurveyQuestionOption(HttpServletRequest request) throws Exception {

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

		SurveyQuestionOption surveyQuestionOptionToBeUpdated = new SurveyQuestionOption();

		try {
			surveyQuestionOptionToBeUpdated = SurveyQuestionOptionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyQuestionOption(surveyQuestionOptionToBeUpdated, surveyQuestionOptionToBeUpdated.getSurveyOptionSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSurveyQuestionOption(@RequestBody SurveyQuestionOption surveyQuestionOptionToBeUpdated,
			@PathVariable String surveyOptionSeqId) throws Exception {

		surveyQuestionOptionToBeUpdated.setSurveyOptionSeqId(surveyOptionSeqId);

		UpdateSurveyQuestionOption command = new UpdateSurveyQuestionOption(surveyQuestionOptionToBeUpdated);

		try {
			if(((SurveyQuestionOptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyQuestionOptionId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyQuestionOptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionOptionId", surveyQuestionOptionId);
		try {

			Object foundSurveyQuestionOption = findSurveyQuestionOptionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyQuestionOption);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyQuestionOptionId}")
	public ResponseEntity<Object> deleteSurveyQuestionOptionByIdUpdated(@PathVariable String surveyQuestionOptionId) throws Exception {
		DeleteSurveyQuestionOption command = new DeleteSurveyQuestionOption(surveyQuestionOptionId);

		try {
			if (((SurveyQuestionOptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyQuestionOption could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/surveyQuestionOption/\" plus one of the following: "
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
