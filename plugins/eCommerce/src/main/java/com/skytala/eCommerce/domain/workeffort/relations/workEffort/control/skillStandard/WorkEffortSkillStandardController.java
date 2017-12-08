package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.skillStandard;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard.AddWorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard.DeleteWorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard.UpdateWorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.skillStandard.WorkEffortSkillStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.skillStandard.FindWorkEffortSkillStandardsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortSkillStandards")
public class WorkEffortSkillStandardController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortSkillStandardController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortSkillStandard
	 * @return a List with the WorkEffortSkillStandards
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortSkillStandardsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSkillStandardsBy query = new FindWorkEffortSkillStandardsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSkillStandard> workEffortSkillStandards =((WorkEffortSkillStandardFound) Scheduler.execute(query).data()).getWorkEffortSkillStandards();

		if (workEffortSkillStandards.size() == 1) {
			return ResponseEntity.ok().body(workEffortSkillStandards.get(0));
		}

		return ResponseEntity.ok().body(workEffortSkillStandards);

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
	public ResponseEntity<Object> createWorkEffortSkillStandard(HttpServletRequest request) throws Exception {

		WorkEffortSkillStandard workEffortSkillStandardToBeAdded = new WorkEffortSkillStandard();
		try {
			workEffortSkillStandardToBeAdded = WorkEffortSkillStandardMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortSkillStandard(workEffortSkillStandardToBeAdded);

	}

	/**
	 * creates a new WorkEffortSkillStandard entry in the ofbiz database
	 * 
	 * @param workEffortSkillStandardToBeAdded
	 *            the WorkEffortSkillStandard thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortSkillStandard(@RequestBody WorkEffortSkillStandard workEffortSkillStandardToBeAdded) throws Exception {

		AddWorkEffortSkillStandard command = new AddWorkEffortSkillStandard(workEffortSkillStandardToBeAdded);
		WorkEffortSkillStandard workEffortSkillStandard = ((WorkEffortSkillStandardAdded) Scheduler.execute(command).data()).getAddedWorkEffortSkillStandard();
		
		if (workEffortSkillStandard != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortSkillStandard);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortSkillStandard could not be created.");
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
	public boolean updateWorkEffortSkillStandard(HttpServletRequest request) throws Exception {

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

		WorkEffortSkillStandard workEffortSkillStandardToBeUpdated = new WorkEffortSkillStandard();

		try {
			workEffortSkillStandardToBeUpdated = WorkEffortSkillStandardMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortSkillStandard(workEffortSkillStandardToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortSkillStandard with the specific Id
	 * 
	 * @param workEffortSkillStandardToBeUpdated
	 *            the WorkEffortSkillStandard thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortSkillStandard(@RequestBody WorkEffortSkillStandard workEffortSkillStandardToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortSkillStandardToBeUpdated.setnull(null);

		UpdateWorkEffortSkillStandard command = new UpdateWorkEffortSkillStandard(workEffortSkillStandardToBeUpdated);

		try {
			if(((WorkEffortSkillStandardUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortSkillStandardId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortSkillStandardId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSkillStandardId", workEffortSkillStandardId);
		try {

			Object foundWorkEffortSkillStandard = findWorkEffortSkillStandardsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortSkillStandard);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortSkillStandardId}")
	public ResponseEntity<Object> deleteWorkEffortSkillStandardByIdUpdated(@PathVariable String workEffortSkillStandardId) throws Exception {
		DeleteWorkEffortSkillStandard command = new DeleteWorkEffortSkillStandard(workEffortSkillStandardId);

		try {
			if (((WorkEffortSkillStandardDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortSkillStandard could not be deleted");

	}

}
