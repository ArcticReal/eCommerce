package com.skytala.eCommerce.domain.content.relations.survey.control.questionCategory;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.questionCategory.AddSurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionCategory.DeleteSurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.survey.command.questionCategory.UpdateSurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionCategory.SurveyQuestionCategoryAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionCategory.SurveyQuestionCategoryDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionCategory.SurveyQuestionCategoryFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionCategory.SurveyQuestionCategoryUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionCategory.SurveyQuestionCategoryMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionCategory.SurveyQuestionCategory;
import com.skytala.eCommerce.domain.content.relations.survey.query.questionCategory.FindSurveyQuestionCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyQuestionCategorys")
public class SurveyQuestionCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyQuestionCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyQuestionCategory
	 * @return a List with the SurveyQuestionCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyQuestionCategory>> findSurveyQuestionCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionCategorysBy query = new FindSurveyQuestionCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionCategory> surveyQuestionCategorys =((SurveyQuestionCategoryFound) Scheduler.execute(query).data()).getSurveyQuestionCategorys();

		return ResponseEntity.ok().body(surveyQuestionCategorys);

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
	public ResponseEntity<SurveyQuestionCategory> createSurveyQuestionCategory(HttpServletRequest request) throws Exception {

		SurveyQuestionCategory surveyQuestionCategoryToBeAdded = new SurveyQuestionCategory();
		try {
			surveyQuestionCategoryToBeAdded = SurveyQuestionCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSurveyQuestionCategory(surveyQuestionCategoryToBeAdded);

	}

	/**
	 * creates a new SurveyQuestionCategory entry in the ofbiz database
	 * 
	 * @param surveyQuestionCategoryToBeAdded
	 *            the SurveyQuestionCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyQuestionCategory> createSurveyQuestionCategory(@RequestBody SurveyQuestionCategory surveyQuestionCategoryToBeAdded) throws Exception {

		AddSurveyQuestionCategory command = new AddSurveyQuestionCategory(surveyQuestionCategoryToBeAdded);
		SurveyQuestionCategory surveyQuestionCategory = ((SurveyQuestionCategoryAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionCategory();
		
		if (surveyQuestionCategory != null) 
			return successful(surveyQuestionCategory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyQuestionCategory with the specific Id
	 * 
	 * @param surveyQuestionCategoryToBeUpdated
	 *            the SurveyQuestionCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyQuestionCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyQuestionCategory(@RequestBody SurveyQuestionCategory surveyQuestionCategoryToBeUpdated,
			@PathVariable String surveyQuestionCategoryId) throws Exception {

		surveyQuestionCategoryToBeUpdated.setSurveyQuestionCategoryId(surveyQuestionCategoryId);

		UpdateSurveyQuestionCategory command = new UpdateSurveyQuestionCategory(surveyQuestionCategoryToBeUpdated);

		try {
			if(((SurveyQuestionCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyQuestionCategoryId}")
	public ResponseEntity<SurveyQuestionCategory> findById(@PathVariable String surveyQuestionCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionCategoryId", surveyQuestionCategoryId);
		try {

			List<SurveyQuestionCategory> foundSurveyQuestionCategory = findSurveyQuestionCategorysBy(requestParams).getBody();
			if(foundSurveyQuestionCategory.size()==1){				return successful(foundSurveyQuestionCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyQuestionCategoryId}")
	public ResponseEntity<String> deleteSurveyQuestionCategoryByIdUpdated(@PathVariable String surveyQuestionCategoryId) throws Exception {
		DeleteSurveyQuestionCategory command = new DeleteSurveyQuestionCategory(surveyQuestionCategoryId);

		try {
			if (((SurveyQuestionCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
