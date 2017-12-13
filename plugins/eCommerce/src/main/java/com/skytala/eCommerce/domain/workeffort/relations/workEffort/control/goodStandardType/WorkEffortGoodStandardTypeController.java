package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.goodStandardType;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandardType.AddWorkEffortGoodStandardType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandardType.DeleteWorkEffortGoodStandardType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandardType.UpdateWorkEffortGoodStandardType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType.WorkEffortGoodStandardTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType.WorkEffortGoodStandardTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType.WorkEffortGoodStandardTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType.WorkEffortGoodStandardTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandardType.WorkEffortGoodStandardTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.goodStandardType.FindWorkEffortGoodStandardTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortGoodStandardTypes")
public class WorkEffortGoodStandardTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortGoodStandardTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortGoodStandardType
	 * @return a List with the WorkEffortGoodStandardTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWorkEffortGoodStandardTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortGoodStandardTypesBy query = new FindWorkEffortGoodStandardTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortGoodStandardType> workEffortGoodStandardTypes =((WorkEffortGoodStandardTypeFound) Scheduler.execute(query).data()).getWorkEffortGoodStandardTypes();

		if (workEffortGoodStandardTypes.size() == 1) {
			return ResponseEntity.ok().body(workEffortGoodStandardTypes.get(0));
		}

		return ResponseEntity.ok().body(workEffortGoodStandardTypes);

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
	public ResponseEntity<Object> createWorkEffortGoodStandardType(HttpServletRequest request) throws Exception {

		WorkEffortGoodStandardType workEffortGoodStandardTypeToBeAdded = new WorkEffortGoodStandardType();
		try {
			workEffortGoodStandardTypeToBeAdded = WorkEffortGoodStandardTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortGoodStandardType(workEffortGoodStandardTypeToBeAdded);

	}

	/**
	 * creates a new WorkEffortGoodStandardType entry in the ofbiz database
	 * 
	 * @param workEffortGoodStandardTypeToBeAdded
	 *            the WorkEffortGoodStandardType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortGoodStandardType(@RequestBody WorkEffortGoodStandardType workEffortGoodStandardTypeToBeAdded) throws Exception {

		AddWorkEffortGoodStandardType command = new AddWorkEffortGoodStandardType(workEffortGoodStandardTypeToBeAdded);
		WorkEffortGoodStandardType workEffortGoodStandardType = ((WorkEffortGoodStandardTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortGoodStandardType();
		
		if (workEffortGoodStandardType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortGoodStandardType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortGoodStandardType could not be created.");
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
	public boolean updateWorkEffortGoodStandardType(HttpServletRequest request) throws Exception {

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

		WorkEffortGoodStandardType workEffortGoodStandardTypeToBeUpdated = new WorkEffortGoodStandardType();

		try {
			workEffortGoodStandardTypeToBeUpdated = WorkEffortGoodStandardTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortGoodStandardType(workEffortGoodStandardTypeToBeUpdated, workEffortGoodStandardTypeToBeUpdated.getWorkEffortGoodStdTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortGoodStandardType with the specific Id
	 * 
	 * @param workEffortGoodStandardTypeToBeUpdated
	 *            the WorkEffortGoodStandardType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortGoodStdTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortGoodStandardType(@RequestBody WorkEffortGoodStandardType workEffortGoodStandardTypeToBeUpdated,
			@PathVariable String workEffortGoodStdTypeId) throws Exception {

		workEffortGoodStandardTypeToBeUpdated.setWorkEffortGoodStdTypeId(workEffortGoodStdTypeId);

		UpdateWorkEffortGoodStandardType command = new UpdateWorkEffortGoodStandardType(workEffortGoodStandardTypeToBeUpdated);

		try {
			if(((WorkEffortGoodStandardTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortGoodStandardTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortGoodStandardTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortGoodStandardTypeId", workEffortGoodStandardTypeId);
		try {

			Object foundWorkEffortGoodStandardType = findWorkEffortGoodStandardTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortGoodStandardType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortGoodStandardTypeId}")
	public ResponseEntity<Object> deleteWorkEffortGoodStandardTypeByIdUpdated(@PathVariable String workEffortGoodStandardTypeId) throws Exception {
		DeleteWorkEffortGoodStandardType command = new DeleteWorkEffortGoodStandardType(workEffortGoodStandardTypeId);

		try {
			if (((WorkEffortGoodStandardTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortGoodStandardType could not be deleted");

	}

}
