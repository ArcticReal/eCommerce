package com.skytala.eCommerce.domain.content.relations.survey.control.page;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.page.AddSurveyPage;
import com.skytala.eCommerce.domain.content.relations.survey.command.page.DeleteSurveyPage;
import com.skytala.eCommerce.domain.content.relations.survey.command.page.UpdateSurveyPage;
import com.skytala.eCommerce.domain.content.relations.survey.event.page.SurveyPageAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.page.SurveyPageDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.page.SurveyPageFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.page.SurveyPageUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.page.SurveyPageMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;
import com.skytala.eCommerce.domain.content.relations.survey.query.page.FindSurveyPagesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/survey/surveyPages")
public class SurveyPageController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyPageController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyPage
	 * @return a List with the SurveyPages
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SurveyPage>> findSurveyPagesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyPagesBy query = new FindSurveyPagesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyPage> surveyPages =((SurveyPageFound) Scheduler.execute(query).data()).getSurveyPages();

		return ResponseEntity.ok().body(surveyPages);

	}

	/**
	 * creates a new SurveyPage entry in the ofbiz database
	 * 
	 * @param surveyPageToBeAdded
	 *            the SurveyPage thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SurveyPage> createSurveyPage(@RequestBody SurveyPage surveyPageToBeAdded) throws Exception {

		AddSurveyPage command = new AddSurveyPage(surveyPageToBeAdded);
		SurveyPage surveyPage = ((SurveyPageAdded) Scheduler.execute(command).data()).getAddedSurveyPage();
		
		if (surveyPage != null) 
			return successful(surveyPage);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SurveyPage with the specific Id
	 * 
	 * @param surveyPageToBeUpdated
	 *            the SurveyPage thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyPageSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSurveyPage(@RequestBody SurveyPage surveyPageToBeUpdated,
			@PathVariable String surveyPageSeqId) throws Exception {

		surveyPageToBeUpdated.setSurveyPageSeqId(surveyPageSeqId);

		UpdateSurveyPage command = new UpdateSurveyPage(surveyPageToBeUpdated);

		try {
			if(((SurveyPageUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{surveyPageId}")
	public ResponseEntity<SurveyPage> findById(@PathVariable String surveyPageId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyPageId", surveyPageId);
		try {

			List<SurveyPage> foundSurveyPage = findSurveyPagesBy(requestParams).getBody();
			if(foundSurveyPage.size()==1){				return successful(foundSurveyPage.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{surveyPageId}")
	public ResponseEntity<String> deleteSurveyPageByIdUpdated(@PathVariable String surveyPageId) throws Exception {
		DeleteSurveyPage command = new DeleteSurveyPage(surveyPageId);

		try {
			if (((SurveyPageDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
