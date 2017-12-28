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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyTriggers")
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
	@GetMapping("/find")
	public ResponseEntity<List<SurveyTrigger>> findSurveyTriggersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyTriggersBy query = new FindSurveyTriggersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyTrigger> surveyTriggers =((SurveyTriggerFound) Scheduler.execute(query).data()).getSurveyTriggers();

		return ResponseEntity.ok().body(surveyTriggers);

	}

	/**
	 * creates a new SurveyTrigger entry in the ofbiz database
	 * 
	 * @param surveyTriggerToBeAdded
	 *            the SurveyTrigger thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyTrigger> createSurveyTrigger(@RequestBody SurveyTrigger surveyTriggerToBeAdded) throws Exception {

		AddSurveyTrigger command = new AddSurveyTrigger(surveyTriggerToBeAdded);
		SurveyTrigger surveyTrigger = ((SurveyTriggerAdded) Scheduler.execute(command).data()).getAddedSurveyTrigger();
		
		if (surveyTrigger != null) 
			return successful(surveyTrigger);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSurveyTrigger(@RequestBody SurveyTrigger surveyTriggerToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		surveyTriggerToBeUpdated.setnull(null);

		UpdateSurveyTrigger command = new UpdateSurveyTrigger(surveyTriggerToBeUpdated);

		try {
			if(((SurveyTriggerUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyTriggerId}")
	public ResponseEntity<SurveyTrigger> findById(@PathVariable String surveyTriggerId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyTriggerId", surveyTriggerId);
		try {

			List<SurveyTrigger> foundSurveyTrigger = findSurveyTriggersBy(requestParams).getBody();
			if(foundSurveyTrigger.size()==1){				return successful(foundSurveyTrigger.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyTriggerId}")
	public ResponseEntity<String> deleteSurveyTriggerByIdUpdated(@PathVariable String surveyTriggerId) throws Exception {
		DeleteSurveyTrigger command = new DeleteSurveyTrigger(surveyTriggerId);

		try {
			if (((SurveyTriggerDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
