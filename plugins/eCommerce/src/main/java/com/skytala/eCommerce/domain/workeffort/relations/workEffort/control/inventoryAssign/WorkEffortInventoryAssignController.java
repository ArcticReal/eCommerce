package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.inventoryAssign;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryAssign.AddWorkEffortInventoryAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryAssign.DeleteWorkEffortInventoryAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryAssign.UpdateWorkEffortInventoryAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign.WorkEffortInventoryAssignAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign.WorkEffortInventoryAssignDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign.WorkEffortInventoryAssignFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryAssign.WorkEffortInventoryAssignUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryAssign.WorkEffortInventoryAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryAssign.WorkEffortInventoryAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.inventoryAssign.FindWorkEffortInventoryAssignsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortInventoryAssigns")
public class WorkEffortInventoryAssignController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortInventoryAssignController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortInventoryAssign
	 * @return a List with the WorkEffortInventoryAssigns
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortInventoryAssignsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortInventoryAssignsBy query = new FindWorkEffortInventoryAssignsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortInventoryAssign> workEffortInventoryAssigns =((WorkEffortInventoryAssignFound) Scheduler.execute(query).data()).getWorkEffortInventoryAssigns();

		if (workEffortInventoryAssigns.size() == 1) {
			return ResponseEntity.ok().body(workEffortInventoryAssigns.get(0));
		}

		return ResponseEntity.ok().body(workEffortInventoryAssigns);

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
	public ResponseEntity<Object> createWorkEffortInventoryAssign(HttpServletRequest request) throws Exception {

		WorkEffortInventoryAssign workEffortInventoryAssignToBeAdded = new WorkEffortInventoryAssign();
		try {
			workEffortInventoryAssignToBeAdded = WorkEffortInventoryAssignMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortInventoryAssign(workEffortInventoryAssignToBeAdded);

	}

	/**
	 * creates a new WorkEffortInventoryAssign entry in the ofbiz database
	 * 
	 * @param workEffortInventoryAssignToBeAdded
	 *            the WorkEffortInventoryAssign thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortInventoryAssign(@RequestBody WorkEffortInventoryAssign workEffortInventoryAssignToBeAdded) throws Exception {

		AddWorkEffortInventoryAssign command = new AddWorkEffortInventoryAssign(workEffortInventoryAssignToBeAdded);
		WorkEffortInventoryAssign workEffortInventoryAssign = ((WorkEffortInventoryAssignAdded) Scheduler.execute(command).data()).getAddedWorkEffortInventoryAssign();
		
		if (workEffortInventoryAssign != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortInventoryAssign);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortInventoryAssign could not be created.");
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
	public boolean updateWorkEffortInventoryAssign(HttpServletRequest request) throws Exception {

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

		WorkEffortInventoryAssign workEffortInventoryAssignToBeUpdated = new WorkEffortInventoryAssign();

		try {
			workEffortInventoryAssignToBeUpdated = WorkEffortInventoryAssignMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortInventoryAssign(workEffortInventoryAssignToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortInventoryAssign with the specific Id
	 * 
	 * @param workEffortInventoryAssignToBeUpdated
	 *            the WorkEffortInventoryAssign thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortInventoryAssign(@RequestBody WorkEffortInventoryAssign workEffortInventoryAssignToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortInventoryAssignToBeUpdated.setnull(null);

		UpdateWorkEffortInventoryAssign command = new UpdateWorkEffortInventoryAssign(workEffortInventoryAssignToBeUpdated);

		try {
			if(((WorkEffortInventoryAssignUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortInventoryAssignId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortInventoryAssignId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortInventoryAssignId", workEffortInventoryAssignId);
		try {

			Object foundWorkEffortInventoryAssign = findWorkEffortInventoryAssignsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortInventoryAssign);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortInventoryAssignId}")
	public ResponseEntity<Object> deleteWorkEffortInventoryAssignByIdUpdated(@PathVariable String workEffortInventoryAssignId) throws Exception {
		DeleteWorkEffortInventoryAssign command = new DeleteWorkEffortInventoryAssign(workEffortInventoryAssignId);

		try {
			if (((WorkEffortInventoryAssignDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortInventoryAssign could not be deleted");

	}

}
