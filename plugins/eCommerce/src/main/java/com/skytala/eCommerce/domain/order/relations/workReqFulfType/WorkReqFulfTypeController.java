package com.skytala.eCommerce.domain.order.relations.workReqFulfType;

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
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.command.AddWorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.command.DeleteWorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.command.UpdateWorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeAdded;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeFound;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.mapper.WorkReqFulfTypeMapper;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.model.WorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.query.FindWorkReqFulfTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/workReqFulfTypes")
public class WorkReqFulfTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkReqFulfTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkReqFulfType
	 * @return a List with the WorkReqFulfTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWorkReqFulfTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkReqFulfTypesBy query = new FindWorkReqFulfTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkReqFulfType> workReqFulfTypes =((WorkReqFulfTypeFound) Scheduler.execute(query).data()).getWorkReqFulfTypes();

		if (workReqFulfTypes.size() == 1) {
			return ResponseEntity.ok().body(workReqFulfTypes.get(0));
		}

		return ResponseEntity.ok().body(workReqFulfTypes);

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
	public ResponseEntity<Object> createWorkReqFulfType(HttpServletRequest request) throws Exception {

		WorkReqFulfType workReqFulfTypeToBeAdded = new WorkReqFulfType();
		try {
			workReqFulfTypeToBeAdded = WorkReqFulfTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkReqFulfType(workReqFulfTypeToBeAdded);

	}

	/**
	 * creates a new WorkReqFulfType entry in the ofbiz database
	 * 
	 * @param workReqFulfTypeToBeAdded
	 *            the WorkReqFulfType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkReqFulfType(@RequestBody WorkReqFulfType workReqFulfTypeToBeAdded) throws Exception {

		AddWorkReqFulfType command = new AddWorkReqFulfType(workReqFulfTypeToBeAdded);
		WorkReqFulfType workReqFulfType = ((WorkReqFulfTypeAdded) Scheduler.execute(command).data()).getAddedWorkReqFulfType();
		
		if (workReqFulfType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workReqFulfType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkReqFulfType could not be created.");
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
	public boolean updateWorkReqFulfType(HttpServletRequest request) throws Exception {

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

		WorkReqFulfType workReqFulfTypeToBeUpdated = new WorkReqFulfType();

		try {
			workReqFulfTypeToBeUpdated = WorkReqFulfTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkReqFulfType(workReqFulfTypeToBeUpdated, workReqFulfTypeToBeUpdated.getWorkReqFulfTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkReqFulfType with the specific Id
	 * 
	 * @param workReqFulfTypeToBeUpdated
	 *            the WorkReqFulfType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workReqFulfTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkReqFulfType(@RequestBody WorkReqFulfType workReqFulfTypeToBeUpdated,
			@PathVariable String workReqFulfTypeId) throws Exception {

		workReqFulfTypeToBeUpdated.setWorkReqFulfTypeId(workReqFulfTypeId);

		UpdateWorkReqFulfType command = new UpdateWorkReqFulfType(workReqFulfTypeToBeUpdated);

		try {
			if(((WorkReqFulfTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workReqFulfTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String workReqFulfTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workReqFulfTypeId", workReqFulfTypeId);
		try {

			Object foundWorkReqFulfType = findWorkReqFulfTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkReqFulfType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workReqFulfTypeId}")
	public ResponseEntity<Object> deleteWorkReqFulfTypeByIdUpdated(@PathVariable String workReqFulfTypeId) throws Exception {
		DeleteWorkReqFulfType command = new DeleteWorkReqFulfType(workReqFulfTypeId);

		try {
			if (((WorkReqFulfTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkReqFulfType could not be deleted");

	}

}
