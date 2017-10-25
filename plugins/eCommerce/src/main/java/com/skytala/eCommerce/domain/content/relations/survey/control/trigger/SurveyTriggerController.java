package com.skytala.eCommerce.domain.content.relations.survey.control.trigger;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.trigger.AddSurveyTrigger;
import com.skytala.eCommerce.domain.content.relations.survey.command.trigger.DeleteSurveyTrigger;
import com.skytala.eCommerce.domain.content.relations.survey.command.trigger.UpdateSurveyTrigger;
import com.skytala.eCommerce.domain.content.relations.survey.event.trigger.SurveyTriggerAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.trigger.SurveyTriggerDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.trigger.SurveyTriggerFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.trigger.SurveyTriggerUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.trigger.SurveyTriggerMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.trigger.SurveyTrigger;
import com.skytala.eCommerce.domain.content.relations.survey.query.trigger.FindSurveyTriggersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyTriggers")
public class SurveyTriggerController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyTriggerController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyTrigger
	 * @return a List with the SurveyTriggers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyTriggersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyTriggersBy query = new FindSurveyTriggersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyTrigger> surveyTriggers =((SurveyTriggerFound) Scheduler.execute(query).data()).getSurveyTriggers();

		if (surveyTriggers.size() == 1) {
			return ResponseEntity.ok().body(surveyTriggers.get(0));
		}

		return ResponseEntity.ok().body(surveyTriggers);

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
	public ResponseEntity<Object> createSurveyTrigger(HttpServletRequest request) throws Exception {

		SurveyTrigger surveyTriggerToBeAdded = new SurveyTrigger();
		try {
			surveyTriggerToBeAdded = SurveyTriggerMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyTrigger(surveyTriggerToBeAdded);

	}

	/**
	 * creates a new SurveyTrigger entry in the ofbiz database
	 * 
	 * @param surveyTriggerToBeAdded
	 *            the SurveyTrigger thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyTrigger(@RequestBody SurveyTrigger surveyTriggerToBeAdded) throws Exception {

		AddSurveyTrigger command = new AddSurveyTrigger(surveyTriggerToBeAdded);
		SurveyTrigger surveyTrigger = ((SurveyTriggerAdded) Scheduler.execute(command).data()).getAddedSurveyTrigger();
		
		if (surveyTrigger != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyTrigger);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyTrigger could not be created.");
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
	public boolean updateSurveyTrigger(HttpServletRequest request) throws Exception {

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

		SurveyTrigger surveyTriggerToBeUpdated = new SurveyTrigger();

		try {
			surveyTriggerToBeUpdated = SurveyTriggerMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyTrigger(surveyTriggerToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyTrigger with the specific Id
	 * 
	 * @param surveyTriggerToBeUpdated
	 *            the SurveyTrigger thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyTrigger(@RequestBody SurveyTrigger surveyTriggerToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		surveyTriggerToBeUpdated.setnull(null);

		UpdateSurveyTrigger command = new UpdateSurveyTrigger(surveyTriggerToBeUpdated);

		try {
			if(((SurveyTriggerUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyTriggerId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyTriggerId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyTriggerId", surveyTriggerId);
		try {

			Object foundSurveyTrigger = findSurveyTriggersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyTrigger);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyTriggerId}")
	public ResponseEntity<Object> deleteSurveyTriggerByIdUpdated(@PathVariable String surveyTriggerId) throws Exception {
		DeleteSurveyTrigger command = new DeleteSurveyTrigger(surveyTriggerId);

		try {
			if (((SurveyTriggerDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyTrigger could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/surveyTrigger/\" plus one of the following: "
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
