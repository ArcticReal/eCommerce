package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.assocType;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocType.AddWorkEffortAssocType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocType.DeleteWorkEffortAssocType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocType.UpdateWorkEffortAssocType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType.WorkEffortAssocTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType.WorkEffortAssocTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType.WorkEffortAssocTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType.WorkEffortAssocTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocType.WorkEffortAssocTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assocType.FindWorkEffortAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAssocTypes")
public class WorkEffortAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAssocType
	 * @return a List with the WorkEffortAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWorkEffortAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocTypesBy query = new FindWorkEffortAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssocType> workEffortAssocTypes =((WorkEffortAssocTypeFound) Scheduler.execute(query).data()).getWorkEffortAssocTypes();

		if (workEffortAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(workEffortAssocTypes.get(0));
		}

		return ResponseEntity.ok().body(workEffortAssocTypes);

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
	public ResponseEntity<Object> createWorkEffortAssocType(HttpServletRequest request) throws Exception {

		WorkEffortAssocType workEffortAssocTypeToBeAdded = new WorkEffortAssocType();
		try {
			workEffortAssocTypeToBeAdded = WorkEffortAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortAssocType(workEffortAssocTypeToBeAdded);

	}

	/**
	 * creates a new WorkEffortAssocType entry in the ofbiz database
	 * 
	 * @param workEffortAssocTypeToBeAdded
	 *            the WorkEffortAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortAssocType(@RequestBody WorkEffortAssocType workEffortAssocTypeToBeAdded) throws Exception {

		AddWorkEffortAssocType command = new AddWorkEffortAssocType(workEffortAssocTypeToBeAdded);
		WorkEffortAssocType workEffortAssocType = ((WorkEffortAssocTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssocType();
		
		if (workEffortAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortAssocType could not be created.");
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
	public boolean updateWorkEffortAssocType(HttpServletRequest request) throws Exception {

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

		WorkEffortAssocType workEffortAssocTypeToBeUpdated = new WorkEffortAssocType();

		try {
			workEffortAssocTypeToBeUpdated = WorkEffortAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortAssocType(workEffortAssocTypeToBeUpdated, workEffortAssocTypeToBeUpdated.getWorkEffortAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortAssocType with the specific Id
	 * 
	 * @param workEffortAssocTypeToBeUpdated
	 *            the WorkEffortAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortAssocType(@RequestBody WorkEffortAssocType workEffortAssocTypeToBeUpdated,
			@PathVariable String workEffortAssocTypeId) throws Exception {

		workEffortAssocTypeToBeUpdated.setWorkEffortAssocTypeId(workEffortAssocTypeId);

		UpdateWorkEffortAssocType command = new UpdateWorkEffortAssocType(workEffortAssocTypeToBeUpdated);

		try {
			if(((WorkEffortAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocTypeId", workEffortAssocTypeId);
		try {

			Object foundWorkEffortAssocType = findWorkEffortAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortAssocTypeId}")
	public ResponseEntity<Object> deleteWorkEffortAssocTypeByIdUpdated(@PathVariable String workEffortAssocTypeId) throws Exception {
		DeleteWorkEffortAssocType command = new DeleteWorkEffortAssocType(workEffortAssocTypeId);

		try {
			if (((WorkEffortAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortAssocType could not be deleted");

	}

}
