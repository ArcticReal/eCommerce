package com.skytala.eCommerce.domain.content.relations.survey.control.questionAppl;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.questionAppl.AddSurveyQuestionAppl;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionAppl.DeleteSurveyQuestionAppl;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionAppl.UpdateSurveyQuestionAppl;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionAppl.SurveyQuestionApplAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionAppl.SurveyQuestionApplDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionAppl.SurveyQuestionApplFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionAppl.SurveyQuestionApplUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionAppl.SurveyQuestionApplMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionAppl.SurveyQuestionAppl;
import com.skytala.eCommerce.domain.content.relations.survey.query.questionAppl.FindSurveyQuestionApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyQuestionAppls")
public class SurveyQuestionApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestionAppl
	 * @return a List with the SurveyQuestionAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyQuestionAppl>> findSurveyQuestionApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionApplsBy query = new FindSurveyQuestionApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionAppl> surveyQuestionAppls =((SurveyQuestionApplFound) Scheduler.execute(query).data()).getSurveyQuestionAppls();

		return ResponseEntity.ok().body(surveyQuestionAppls);

	}

	/**
	 * creates a new SurveyQuestionAppl entry in the ofbiz database
	 * 
	 * @param surveyQuestionApplToBeAdded
	 *            the SurveyQuestionAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyQuestionAppl> createSurveyQuestionAppl(@RequestBody SurveyQuestionAppl surveyQuestionApplToBeAdded) throws Exception {

		AddSurveyQuestionAppl command = new AddSurveyQuestionAppl(surveyQuestionApplToBeAdded);
		SurveyQuestionAppl surveyQuestionAppl = ((SurveyQuestionApplAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionAppl();
		
		if (surveyQuestionAppl != null) 
			return successful(surveyQuestionAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyQuestionAppl with the specific Id
	 * 
	 * @param surveyQuestionApplToBeUpdated
	 *            the SurveyQuestionAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyQuestionAppl(@RequestBody SurveyQuestionAppl surveyQuestionApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		surveyQuestionApplToBeUpdated.setnull(null);

		UpdateSurveyQuestionAppl command = new UpdateSurveyQuestionAppl(surveyQuestionApplToBeUpdated);

		try {
			if(((SurveyQuestionApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyQuestionApplId}")
	public ResponseEntity<SurveyQuestionAppl> findById(@PathVariable String surveyQuestionApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionApplId", surveyQuestionApplId);
		try {

			List<SurveyQuestionAppl> foundSurveyQuestionAppl = findSurveyQuestionApplsBy(requestParams).getBody();
			if(foundSurveyQuestionAppl.size()==1){				return successful(foundSurveyQuestionAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyQuestionApplId}")
	public ResponseEntity<String> deleteSurveyQuestionApplByIdUpdated(@PathVariable String surveyQuestionApplId) throws Exception {
		DeleteSurveyQuestionAppl command = new DeleteSurveyQuestionAppl(surveyQuestionApplId);

		try {
			if (((SurveyQuestionApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
