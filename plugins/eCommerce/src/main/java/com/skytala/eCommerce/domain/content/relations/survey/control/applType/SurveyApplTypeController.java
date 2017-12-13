package com.skytala.eCommerce.domain.content.relations.survey.control.applType;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.applType.AddSurveyApplType;
import com.skytala.eCommerce.domain.content.relations.survey.command.applType.DeleteSurveyApplType;
import com.skytala.eCommerce.domain.content.relations.survey.command.applType.UpdateSurveyApplType;
import com.skytala.eCommerce.domain.content.relations.survey.event.applType.SurveyApplTypeAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.applType.SurveyApplTypeDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.applType.SurveyApplTypeFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.applType.SurveyApplTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.applType.SurveyApplTypeMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.applType.SurveyApplType;
import com.skytala.eCommerce.domain.content.relations.survey.query.applType.FindSurveyApplTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/survey/surveyApplTypes")
public class SurveyApplTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyApplTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyApplType
	 * @return a List with the SurveyApplTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findSurveyApplTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyApplTypesBy query = new FindSurveyApplTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyApplType> surveyApplTypes =((SurveyApplTypeFound) Scheduler.execute(query).data()).getSurveyApplTypes();

		if (surveyApplTypes.size() == 1) {
			return ResponseEntity.ok().body(surveyApplTypes.get(0));
		}

		return ResponseEntity.ok().body(surveyApplTypes);

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
	public ResponseEntity<Object> createSurveyApplType(HttpServletRequest request) throws Exception {

		SurveyApplType surveyApplTypeToBeAdded = new SurveyApplType();
		try {
			surveyApplTypeToBeAdded = SurveyApplTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyApplType(surveyApplTypeToBeAdded);

	}

	/**
	 * creates a new SurveyApplType entry in the ofbiz database
	 * 
	 * @param surveyApplTypeToBeAdded
	 *            the SurveyApplType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyApplType(@RequestBody SurveyApplType surveyApplTypeToBeAdded) throws Exception {

		AddSurveyApplType command = new AddSurveyApplType(surveyApplTypeToBeAdded);
		SurveyApplType surveyApplType = ((SurveyApplTypeAdded) Scheduler.execute(command).data()).getAddedSurveyApplType();
		
		if (surveyApplType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyApplType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyApplType could not be created.");
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
	public boolean updateSurveyApplType(HttpServletRequest request) throws Exception {

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

		SurveyApplType surveyApplTypeToBeUpdated = new SurveyApplType();

		try {
			surveyApplTypeToBeUpdated = SurveyApplTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyApplType(surveyApplTypeToBeUpdated, surveyApplTypeToBeUpdated.getSurveyApplTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyApplType with the specific Id
	 * 
	 * @param surveyApplTypeToBeUpdated
	 *            the SurveyApplType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyApplTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyApplType(@RequestBody SurveyApplType surveyApplTypeToBeUpdated,
			@PathVariable String surveyApplTypeId) throws Exception {

		surveyApplTypeToBeUpdated.setSurveyApplTypeId(surveyApplTypeId);

		UpdateSurveyApplType command = new UpdateSurveyApplType(surveyApplTypeToBeUpdated);

		try {
			if(((SurveyApplTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{surveyApplTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyApplTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyApplTypeId", surveyApplTypeId);
		try {

			Object foundSurveyApplType = findSurveyApplTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyApplType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{surveyApplTypeId}")
	public ResponseEntity<Object> deleteSurveyApplTypeByIdUpdated(@PathVariable String surveyApplTypeId) throws Exception {
		DeleteSurveyApplType command = new DeleteSurveyApplType(surveyApplTypeId);

		try {
			if (((SurveyApplTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyApplType could not be deleted");

	}

}
