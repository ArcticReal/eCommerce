package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.purposeType;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.purposeType.AddWorkEffortPurposeType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.purposeType.DeleteWorkEffortPurposeType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.purposeType.UpdateWorkEffortPurposeType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType.WorkEffortPurposeTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType.WorkEffortPurposeTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType.WorkEffortPurposeTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType.WorkEffortPurposeTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.purposeType.WorkEffortPurposeTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.purposeType.WorkEffortPurposeType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.purposeType.FindWorkEffortPurposeTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortPurposeTypes")
public class WorkEffortPurposeTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortPurposeTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortPurposeType
	 * @return a List with the WorkEffortPurposeTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortPurposeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortPurposeTypesBy query = new FindWorkEffortPurposeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortPurposeType> workEffortPurposeTypes =((WorkEffortPurposeTypeFound) Scheduler.execute(query).data()).getWorkEffortPurposeTypes();

		if (workEffortPurposeTypes.size() == 1) {
			return ResponseEntity.ok().body(workEffortPurposeTypes.get(0));
		}

		return ResponseEntity.ok().body(workEffortPurposeTypes);

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
	public ResponseEntity<Object> createWorkEffortPurposeType(HttpServletRequest request) throws Exception {

		WorkEffortPurposeType workEffortPurposeTypeToBeAdded = new WorkEffortPurposeType();
		try {
			workEffortPurposeTypeToBeAdded = WorkEffortPurposeTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortPurposeType(workEffortPurposeTypeToBeAdded);

	}

	/**
	 * creates a new WorkEffortPurposeType entry in the ofbiz database
	 * 
	 * @param workEffortPurposeTypeToBeAdded
	 *            the WorkEffortPurposeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortPurposeType(@RequestBody WorkEffortPurposeType workEffortPurposeTypeToBeAdded) throws Exception {

		AddWorkEffortPurposeType command = new AddWorkEffortPurposeType(workEffortPurposeTypeToBeAdded);
		WorkEffortPurposeType workEffortPurposeType = ((WorkEffortPurposeTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortPurposeType();
		
		if (workEffortPurposeType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortPurposeType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortPurposeType could not be created.");
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
	public boolean updateWorkEffortPurposeType(HttpServletRequest request) throws Exception {

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

		WorkEffortPurposeType workEffortPurposeTypeToBeUpdated = new WorkEffortPurposeType();

		try {
			workEffortPurposeTypeToBeUpdated = WorkEffortPurposeTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortPurposeType(workEffortPurposeTypeToBeUpdated, workEffortPurposeTypeToBeUpdated.getWorkEffortPurposeTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortPurposeType with the specific Id
	 * 
	 * @param workEffortPurposeTypeToBeUpdated
	 *            the WorkEffortPurposeType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortPurposeTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortPurposeType(@RequestBody WorkEffortPurposeType workEffortPurposeTypeToBeUpdated,
			@PathVariable String workEffortPurposeTypeId) throws Exception {

		workEffortPurposeTypeToBeUpdated.setWorkEffortPurposeTypeId(workEffortPurposeTypeId);

		UpdateWorkEffortPurposeType command = new UpdateWorkEffortPurposeType(workEffortPurposeTypeToBeUpdated);

		try {
			if(((WorkEffortPurposeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortPurposeTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortPurposeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortPurposeTypeId", workEffortPurposeTypeId);
		try {

			Object foundWorkEffortPurposeType = findWorkEffortPurposeTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortPurposeType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortPurposeTypeId}")
	public ResponseEntity<Object> deleteWorkEffortPurposeTypeByIdUpdated(@PathVariable String workEffortPurposeTypeId) throws Exception {
		DeleteWorkEffortPurposeType command = new DeleteWorkEffortPurposeType(workEffortPurposeTypeId);

		try {
			if (((WorkEffortPurposeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortPurposeType could not be deleted");

	}

}
