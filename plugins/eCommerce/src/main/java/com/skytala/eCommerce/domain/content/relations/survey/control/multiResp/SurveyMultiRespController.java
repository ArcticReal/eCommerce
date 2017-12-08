package com.skytala.eCommerce.domain.content.relations.survey.control.multiResp;

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
import com.skytala.eCommerce.domain.content.relations.survey.command.multiResp.AddSurveyMultiResp;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiResp.DeleteSurveyMultiResp;
import com.skytala.eCommerce.domain.content.relations.survey.command.multiResp.UpdateSurveyMultiResp;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiResp.SurveyMultiRespAdded;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiResp.SurveyMultiRespDeleted;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiResp.SurveyMultiRespFound;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiResp.SurveyMultiRespUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.multiResp.SurveyMultiRespMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.multiResp.SurveyMultiResp;
import com.skytala.eCommerce.domain.content.relations.survey.query.multiResp.FindSurveyMultiRespsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/surveyMultiResps")
public class SurveyMultiRespController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SurveyMultiRespController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SurveyMultiResp
	 * @return a List with the SurveyMultiResps
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSurveyMultiRespsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSurveyMultiRespsBy query = new FindSurveyMultiRespsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SurveyMultiResp> surveyMultiResps =((SurveyMultiRespFound) Scheduler.execute(query).data()).getSurveyMultiResps();

		if (surveyMultiResps.size() == 1) {
			return ResponseEntity.ok().body(surveyMultiResps.get(0));
		}

		return ResponseEntity.ok().body(surveyMultiResps);

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
	public ResponseEntity<Object> createSurveyMultiResp(HttpServletRequest request) throws Exception {

		SurveyMultiResp surveyMultiRespToBeAdded = new SurveyMultiResp();
		try {
			surveyMultiRespToBeAdded = SurveyMultiRespMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSurveyMultiResp(surveyMultiRespToBeAdded);

	}

	/**
	 * creates a new SurveyMultiResp entry in the ofbiz database
	 * 
	 * @param surveyMultiRespToBeAdded
	 *            the SurveyMultiResp thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSurveyMultiResp(@RequestBody SurveyMultiResp surveyMultiRespToBeAdded) throws Exception {

		AddSurveyMultiResp command = new AddSurveyMultiResp(surveyMultiRespToBeAdded);
		SurveyMultiResp surveyMultiResp = ((SurveyMultiRespAdded) Scheduler.execute(command).data()).getAddedSurveyMultiResp();
		
		if (surveyMultiResp != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(surveyMultiResp);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SurveyMultiResp could not be created.");
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
	public boolean updateSurveyMultiResp(HttpServletRequest request) throws Exception {

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

		SurveyMultiResp surveyMultiRespToBeUpdated = new SurveyMultiResp();

		try {
			surveyMultiRespToBeUpdated = SurveyMultiRespMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSurveyMultiResp(surveyMultiRespToBeUpdated, surveyMultiRespToBeUpdated.getSurveyMultiRespId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SurveyMultiResp with the specific Id
	 * 
	 * @param surveyMultiRespToBeUpdated
	 *            the SurveyMultiResp thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{surveyMultiRespId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSurveyMultiResp(@RequestBody SurveyMultiResp surveyMultiRespToBeUpdated,
			@PathVariable String surveyMultiRespId) throws Exception {

		surveyMultiRespToBeUpdated.setSurveyMultiRespId(surveyMultiRespId);

		UpdateSurveyMultiResp command = new UpdateSurveyMultiResp(surveyMultiRespToBeUpdated);

		try {
			if(((SurveyMultiRespUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{surveyMultiRespId}")
	public ResponseEntity<Object> findById(@PathVariable String surveyMultiRespId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("surveyMultiRespId", surveyMultiRespId);
		try {

			Object foundSurveyMultiResp = findSurveyMultiRespsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSurveyMultiResp);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{surveyMultiRespId}")
	public ResponseEntity<Object> deleteSurveyMultiRespByIdUpdated(@PathVariable String surveyMultiRespId) throws Exception {
		DeleteSurveyMultiResp command = new DeleteSurveyMultiResp(surveyMultiRespId);

		try {
			if (((SurveyMultiRespDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SurveyMultiResp could not be deleted");

	}

}
