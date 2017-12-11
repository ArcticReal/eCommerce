package com.skytala.eCommerce.domain.content.relations.survey.control.multiRespColumn;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn.AddSurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn.DeleteSurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn.UpdateSurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.multiRespColumn.SurveyMultiRespColumnMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;
import com.skytala.eCommerce.domain.content.relations.survey.query.multiRespColumn.FindSurveyMultiRespColumnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/survey/surveyMultiRespColumns")
public class SurveyMultiRespColumnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyMultiRespColumnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyMultiRespColumn
	 * @return a List with the SurveyMultiRespColumns
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyMultiRespColumnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyMultiRespColumnsBy query = new FindSurveyMultiRespColumnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyMultiRespColumn> surveyMultiRespColumns =((SurveyMultiRespColumnFound) Scheduler.execute(query).data()).getSurveyMultiRespColumns();

		if (surveyMultiRespColumns.size() == 1) {
			return ResponseEntity.ok().body(surveyMultiRespColumns.get(0));
		}

		return ResponseEntity.ok().body(surveyMultiRespColumns);

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
	public ResponseEntity<Object> createSurveyMultiRespColumn(HttpServletRequest request) throws Exception {

		SurveyMultiRespColumn surveyMultiRespColumnToBeAdded = new SurveyMultiRespColumn();
		try {
			surveyMultiRespColumnToBeAdded = SurveyMultiRespColumnMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyMultiRespColumn(surveyMultiRespColumnToBeAdded);

	}

	/**
	 * creates a new SurveyMultiRespColumn entry in the ofbiz database
	 * 
	 * @param surveyMultiRespColumnToBeAdded
	 *            the SurveyMultiRespColumn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyMultiRespColumn(@RequestBody SurveyMultiRespColumn surveyMultiRespColumnToBeAdded) throws Exception {

		AddSurveyMultiRespColumn command = new AddSurveyMultiRespColumn(surveyMultiRespColumnToBeAdded);
		SurveyMultiRespColumn surveyMultiRespColumn = ((SurveyMultiRespColumnAdded) Scheduler.execute(command).data()).getAddedSurveyMultiRespColumn();
		
		if (surveyMultiRespColumn != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyMultiRespColumn);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyMultiRespColumn could not be created.");
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
	public boolean updateSurveyMultiRespColumn(HttpServletRequest request) throws Exception {

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

		SurveyMultiRespColumn surveyMultiRespColumnToBeUpdated = new SurveyMultiRespColumn();

		try {
			surveyMultiRespColumnToBeUpdated = SurveyMultiRespColumnMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyMultiRespColumn(surveyMultiRespColumnToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyMultiRespColumn with the specific Id
	 * 
	 * @param surveyMultiRespColumnToBeUpdated
	 *            the SurveyMultiRespColumn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyMultiRespColumn(@RequestBody SurveyMultiRespColumn surveyMultiRespColumnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		surveyMultiRespColumnToBeUpdated.setnull(null);

		UpdateSurveyMultiRespColumn command = new UpdateSurveyMultiRespColumn(surveyMultiRespColumnToBeUpdated);

		try {
			if(((SurveyMultiRespColumnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyMultiRespColumnId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyMultiRespColumnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyMultiRespColumnId", surveyMultiRespColumnId);
		try {

			Object foundSurveyMultiRespColumn = findSurveyMultiRespColumnsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyMultiRespColumn);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyMultiRespColumnId}")
	public ResponseEntity<Object> deleteSurveyMultiRespColumnByIdUpdated(@PathVariable String surveyMultiRespColumnId) throws Exception {
		DeleteSurveyMultiRespColumn command = new DeleteSurveyMultiRespColumn(surveyMultiRespColumnId);

		try {
			if (((SurveyMultiRespColumnDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyMultiRespColumn could not be deleted");

	}

}
