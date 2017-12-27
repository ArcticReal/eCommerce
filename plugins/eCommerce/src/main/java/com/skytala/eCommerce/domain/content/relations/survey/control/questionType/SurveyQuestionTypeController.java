package com.skytala.eCommerce.domain.content.relations.survey.control.questionType;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.questionType.AddSurveyQuestionType;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionType.DeleteSurveyQuestionType;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionType.UpdateSurveyQuestionType;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionType.SurveyQuestionTypeAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionType.SurveyQuestionTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionType.SurveyQuestionTypeFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionType.SurveyQuestionTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionType.SurveyQuestionTypeMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionType.SurveyQuestionType;
import com.skytala.eCommerce.domain.content.relations.survey.query.questionType.FindSurveyQuestionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyQuestionTypes")
public class SurveyQuestionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestionType
	 * @return a List with the SurveyQuestionTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyQuestionType>> findSurveyQuestionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionTypesBy query = new FindSurveyQuestionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionType> surveyQuestionTypes =((SurveyQuestionTypeFound) Scheduler.execute(query).data()).getSurveyQuestionTypes();

		return ResponseEntity.ok().body(surveyQuestionTypes);

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
	public ResponseEntity<SurveyQuestionType> createSurveyQuestionType(HttpServletRequest request) throws Exception {

		SurveyQuestionType surveyQuestionTypeToBeAdded = new SurveyQuestionType();
		try {
			surveyQuestionTypeToBeAdded = SurveyQuestionTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSurveyQuestionType(surveyQuestionTypeToBeAdded);

	}

	/**
	 * creates a new SurveyQuestionType entry in the ofbiz database
	 * 
	 * @param surveyQuestionTypeToBeAdded
	 *            the SurveyQuestionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyQuestionType> createSurveyQuestionType(@RequestBody SurveyQuestionType surveyQuestionTypeToBeAdded) throws Exception {

		AddSurveyQuestionType command = new AddSurveyQuestionType(surveyQuestionTypeToBeAdded);
		SurveyQuestionType surveyQuestionType = ((SurveyQuestionTypeAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionType();
		
		if (surveyQuestionType != null) 
			return successful(surveyQuestionType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyQuestionType with the specific Id
	 * 
	 * @param surveyQuestionTypeToBeUpdated
	 *            the SurveyQuestionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyQuestionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyQuestionType(@RequestBody SurveyQuestionType surveyQuestionTypeToBeUpdated,
			@PathVariable String surveyQuestionTypeId) throws Exception {

		surveyQuestionTypeToBeUpdated.setSurveyQuestionTypeId(surveyQuestionTypeId);

		UpdateSurveyQuestionType command = new UpdateSurveyQuestionType(surveyQuestionTypeToBeUpdated);

		try {
			if(((SurveyQuestionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyQuestionTypeId}")
	public ResponseEntity<SurveyQuestionType> findById(@PathVariable String surveyQuestionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionTypeId", surveyQuestionTypeId);
		try {

			List<SurveyQuestionType> foundSurveyQuestionType = findSurveyQuestionTypesBy(requestParams).getBody();
			if(foundSurveyQuestionType.size()==1){				return successful(foundSurveyQuestionType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyQuestionTypeId}")
	public ResponseEntity<String> deleteSurveyQuestionTypeByIdUpdated(@PathVariable String surveyQuestionTypeId) throws Exception {
		DeleteSurveyQuestionType command = new DeleteSurveyQuestionType(surveyQuestionTypeId);

		try {
			if (((SurveyQuestionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
