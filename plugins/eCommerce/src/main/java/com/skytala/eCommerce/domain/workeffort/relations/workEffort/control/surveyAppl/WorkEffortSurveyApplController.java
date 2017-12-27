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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortSurveyAppl>> findWorkEffortSurveyApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSurveyApplsBy query = new FindWorkEffortSurveyApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSurveyAppl> workEffortSurveyAppls =((WorkEffortSurveyApplFound) Scheduler.execute(query).data()).getWorkEffortSurveyAppls();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<WorkEffortSurveyAppl> createWorkEffortSurveyAppl(HttpServletRequest request) throws Exception {

		WorkEffortSurveyAppl workEffortSurveyApplToBeAdded = new WorkEffortSurveyAppl();
		try {
			workEffortSurveyApplToBeAdded = WorkEffortSurveyApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WorkEffortSurveyAppl> createWorkEffortSurveyAppl(@RequestBody WorkEffortSurveyAppl workEffortSurveyApplToBeAdded) throws Exception {

		AddWorkEffortSurveyAppl command = new AddWorkEffortSurveyAppl(workEffortSurveyApplToBeAdded);
		WorkEffortSurveyAppl workEffortSurveyAppl = ((WorkEffortSurveyApplAdded) Scheduler.execute(command).data()).getAddedWorkEffortSurveyAppl();
		
		if (workEffortSurveyAppl != null) 
			return successful(workEffortSurveyAppl);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortSurveyAppl(@RequestBody WorkEffortSurveyAppl workEffortSurveyApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortSurveyApplToBeUpdated.setnull(null);

		UpdateWorkEffortSurveyAppl command = new UpdateWorkEffortSurveyAppl(workEffortSurveyApplToBeUpdated);

		try {
			if(((WorkEffortSurveyApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortSurveyApplId}")
	public ResponseEntity<WorkEffortSurveyAppl> findById(@PathVariable String workEffortSurveyApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSurveyApplId", workEffortSurveyApplId);
		try {

			List<WorkEffortSurveyAppl> foundWorkEffortSurveyAppl = findWorkEffortSurveyApplsBy(requestParams).getBody();
			if(foundWorkEffortSurveyAppl.size()==1){				return successful(foundWorkEffortSurveyAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortSurveyApplId}")
	public ResponseEntity<String> deleteWorkEffortSurveyApplByIdUpdated(@PathVariable String workEffortSurveyApplId) throws Exception {
		DeleteWorkEffortSurveyAppl command = new DeleteWorkEffortSurveyAppl(workEffortSurveyApplId);

		try {
			if (((WorkEffortSurveyApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
