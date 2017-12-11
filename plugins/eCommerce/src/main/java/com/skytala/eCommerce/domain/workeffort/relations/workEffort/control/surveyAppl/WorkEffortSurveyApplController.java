package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.surveyAppl;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.surveyAppl.AddWorkEffortSurveyAppl;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.surveyAppl.DeleteWorkEffortSurveyAppl;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.surveyAppl.UpdateWorkEffortSurveyAppl;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl.WorkEffortSurveyApplAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl.WorkEffortSurveyApplDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl.WorkEffortSurveyApplFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.surveyAppl.WorkEffortSurveyApplUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.surveyAppl.WorkEffortSurveyApplMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.surveyAppl.WorkEffortSurveyAppl;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.surveyAppl.FindWorkEffortSurveyApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortSurveyAppls")
public class WorkEffortSurveyApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortSurveyApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortSurveyAppl
	 * @return a List with the WorkEffortSurveyAppls
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortSurveyApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSurveyApplsBy query = new FindWorkEffortSurveyApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSurveyAppl> workEffortSurveyAppls =((WorkEffortSurveyApplFound) Scheduler.execute(query).data()).getWorkEffortSurveyAppls();

		if (workEffortSurveyAppls.size() == 1) {
			return ResponseEntity.ok().body(workEffortSurveyAppls.get(0));
		}

		return ResponseEntity.ok().body(workEffortSurveyAppls);

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
	public ResponseEntity<Object> createWorkEffortSurveyAppl(HttpServletRequest request) throws Exception {

		WorkEffortSurveyAppl workEffortSurveyApplToBeAdded = new WorkEffortSurveyAppl();
		try {
			workEffortSurveyApplToBeAdded = WorkEffortSurveyApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortSurveyAppl(workEffortSurveyApplToBeAdded);

	}

	/**
	 * creates a new WorkEffortSurveyAppl entry in the ofbiz database
	 * 
	 * @param workEffortSurveyApplToBeAdded
	 *            the WorkEffortSurveyAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortSurveyAppl(@RequestBody WorkEffortSurveyAppl workEffortSurveyApplToBeAdded) throws Exception {

		AddWorkEffortSurveyAppl command = new AddWorkEffortSurveyAppl(workEffortSurveyApplToBeAdded);
		WorkEffortSurveyAppl workEffortSurveyAppl = ((WorkEffortSurveyApplAdded) Scheduler.execute(command).data()).getAddedWorkEffortSurveyAppl();
		
		if (workEffortSurveyAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortSurveyAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortSurveyAppl could not be created.");
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
	public boolean updateWorkEffortSurveyAppl(HttpServletRequest request) throws Exception {

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

		WorkEffortSurveyAppl workEffortSurveyApplToBeUpdated = new WorkEffortSurveyAppl();

		try {
			workEffortSurveyApplToBeUpdated = WorkEffortSurveyApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortSurveyAppl(workEffortSurveyApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortSurveyAppl with the specific Id
	 * 
	 * @param workEffortSurveyApplToBeUpdated
	 *            the WorkEffortSurveyAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortSurveyAppl(@RequestBody WorkEffortSurveyAppl workEffortSurveyApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortSurveyApplToBeUpdated.setnull(null);

		UpdateWorkEffortSurveyAppl command = new UpdateWorkEffortSurveyAppl(workEffortSurveyApplToBeUpdated);

		try {
			if(((WorkEffortSurveyApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortSurveyApplId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortSurveyApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSurveyApplId", workEffortSurveyApplId);
		try {

			Object foundWorkEffortSurveyAppl = findWorkEffortSurveyApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortSurveyAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortSurveyApplId}")
	public ResponseEntity<Object> deleteWorkEffortSurveyApplByIdUpdated(@PathVariable String workEffortSurveyApplId) throws Exception {
		DeleteWorkEffortSurveyAppl command = new DeleteWorkEffortSurveyAppl(workEffortSurveyApplId);

		try {
			if (((WorkEffortSurveyApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortSurveyAppl could not be deleted");

	}

}
