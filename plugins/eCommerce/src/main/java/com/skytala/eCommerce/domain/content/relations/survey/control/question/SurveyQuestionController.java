package com.skytala.eCommerce.domain.content.relations.survey.control.question;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.question.AddSurveyQuestion;
import com.skytala.eCommerce.domain.content.relations.survey.command.question.DeleteSurveyQuestion;
import com.skytala.eCommerce.domain.content.relations.survey.command.question.UpdateSurveyQuestion;
import com.skytala.eCommerce.domain.content.relations.survey.event.question.SurveyQuestionAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.question.SurveyQuestionDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.question.SurveyQuestionFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.question.SurveyQuestionUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.question.SurveyQuestionMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.question.SurveyQuestion;
import com.skytala.eCommerce.domain.content.relations.survey.query.question.FindSurveyQuestionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyQuestions")
public class SurveyQuestionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestion
	 * @return a List with the SurveyQuestions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyQuestion>> findSurveyQuestionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionsBy query = new FindSurveyQuestionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestion> surveyQuestions =((SurveyQuestionFound) Scheduler.execute(query).data()).getSurveyQuestions();

		return ResponseEntity.ok().body(surveyQuestions);

	}

	/**
	 * creates a new SurveyQuestion entry in the ofbiz database
	 * 
	 * @param surveyQuestionToBeAdded
	 *            the SurveyQuestion thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyQuestion> createSurveyQuestion(@RequestBody SurveyQuestion surveyQuestionToBeAdded) throws Exception {

		AddSurveyQuestion command = new AddSurveyQuestion(surveyQuestionToBeAdded);
		SurveyQuestion surveyQuestion = ((SurveyQuestionAdded) Scheduler.execute(command).data()).getAddedSurveyQuestion();
		
		if (surveyQuestion != null) 
			return successful(surveyQuestion);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyQuestion with the specific Id
	 * 
	 * @param surveyQuestionToBeUpdated
	 *            the SurveyQuestion thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyQuestionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyQuestion(@RequestBody SurveyQuestion surveyQuestionToBeUpdated,
			@PathVariable String surveyQuestionId) throws Exception {

		surveyQuestionToBeUpdated.setSurveyQuestionId(surveyQuestionId);

		UpdateSurveyQuestion command = new UpdateSurveyQuestion(surveyQuestionToBeUpdated);

		try {
			if(((SurveyQuestionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyQuestionId}")
	public ResponseEntity<SurveyQuestion> findById(@PathVariable String surveyQuestionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionId", surveyQuestionId);
		try {

			List<SurveyQuestion> foundSurveyQuestion = findSurveyQuestionsBy(requestParams).getBody();
			if(foundSurveyQuestion.size()==1){				return successful(foundSurveyQuestion.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyQuestionId}")
	public ResponseEntity<String> deleteSurveyQuestionByIdUpdated(@PathVariable String surveyQuestionId) throws Exception {
		DeleteSurveyQuestion command = new DeleteSurveyQuestion(surveyQuestionId);

		try {
			if (((SurveyQuestionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
