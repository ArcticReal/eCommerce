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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findSurveyQuestionApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyQuestionApplsBy query = new FindSurveyQuestionApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyQuestionAppl> surveyQuestionAppls =((SurveyQuestionApplFound) Scheduler.execute(query).data()).getSurveyQuestionAppls();

		if (surveyQuestionAppls.size() == 1) {
			return ResponseEntity.ok().body(surveyQuestionAppls.get(0));
		}

		return ResponseEntity.ok().body(surveyQuestionAppls);

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
	public ResponseEntity<Object> createSurveyQuestionAppl(HttpServletRequest request) throws Exception {

		SurveyQuestionAppl surveyQuestionApplToBeAdded = new SurveyQuestionAppl();
		try {
			surveyQuestionApplToBeAdded = SurveyQuestionApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyQuestionAppl(surveyQuestionApplToBeAdded);

	}

	/**
	 * creates a new SurveyQuestionAppl entry in the ofbiz database
	 * 
	 * @param surveyQuestionApplToBeAdded
	 *            the SurveyQuestionAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyQuestionAppl(@RequestBody SurveyQuestionAppl surveyQuestionApplToBeAdded) throws Exception {

		AddSurveyQuestionAppl command = new AddSurveyQuestionAppl(surveyQuestionApplToBeAdded);
		SurveyQuestionAppl surveyQuestionAppl = ((SurveyQuestionApplAdded) Scheduler.execute(command).data()).getAddedSurveyQuestionAppl();
		
		if (surveyQuestionAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyQuestionAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyQuestionAppl could not be created.");
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
	public boolean updateSurveyQuestionAppl(HttpServletRequest request) throws Exception {

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

		SurveyQuestionAppl surveyQuestionApplToBeUpdated = new SurveyQuestionAppl();

		try {
			surveyQuestionApplToBeUpdated = SurveyQuestionApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyQuestionAppl(surveyQuestionApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSurveyQuestionAppl(@RequestBody SurveyQuestionAppl surveyQuestionApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		surveyQuestionApplToBeUpdated.setnull(null);

		UpdateSurveyQuestionAppl command = new UpdateSurveyQuestionAppl(surveyQuestionApplToBeUpdated);

		try {
			if(((SurveyQuestionApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{surveyQuestionApplId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyQuestionApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyQuestionApplId", surveyQuestionApplId);
		try {

			Object foundSurveyQuestionAppl = findSurveyQuestionApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyQuestionAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{surveyQuestionApplId}")
	public ResponseEntity<Object> deleteSurveyQuestionApplByIdUpdated(@PathVariable String surveyQuestionApplId) throws Exception {
		DeleteSurveyQuestionAppl command = new DeleteSurveyQuestionAppl(surveyQuestionApplId);

		try {
			if (((SurveyQuestionApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyQuestionAppl could not be deleted");

	}

}
