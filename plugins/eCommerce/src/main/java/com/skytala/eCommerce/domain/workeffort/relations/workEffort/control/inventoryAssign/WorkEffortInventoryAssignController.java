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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortInventoryAssigns")
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
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortInventoryAssign>> findWorkEffortInventoryAssignsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortInventoryAssignsBy query = new FindWorkEffortInventoryAssignsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortInventoryAssign> workEffortInventoryAssigns =((WorkEffortInventoryAssignFound) Scheduler.execute(query).data()).getWorkEffortInventoryAssigns();

		return ResponseEntity.ok().body(workEffortInventoryAssigns);

	}

	/**
	 * creates a new WorkEffortInventoryAssign entry in the ofbiz database
	 * 
	 * @param workEffortInventoryAssignToBeAdded
	 *            the WorkEffortInventoryAssign thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortInventoryAssign> createWorkEffortInventoryAssign(@RequestBody WorkEffortInventoryAssign workEffortInventoryAssignToBeAdded) throws Exception {

		AddWorkEffortInventoryAssign command = new AddWorkEffortInventoryAssign(workEffortInventoryAssignToBeAdded);
		WorkEffortInventoryAssign workEffortInventoryAssign = ((WorkEffortInventoryAssignAdded) Scheduler.execute(command).data()).getAddedWorkEffortInventoryAssign();
		
		if (workEffortInventoryAssign != null) 
			return successful(workEffortInventoryAssign);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortInventoryAssign(@RequestBody WorkEffortInventoryAssign workEffortInventoryAssignToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortInventoryAssignToBeUpdated.setnull(null);

		UpdateWorkEffortInventoryAssign command = new UpdateWorkEffortInventoryAssign(workEffortInventoryAssignToBeUpdated);

		try {
			if(((WorkEffortInventoryAssignUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortInventoryAssignId}")
	public ResponseEntity<WorkEffortInventoryAssign> findById(@PathVariable String workEffortInventoryAssignId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortInventoryAssignId", workEffortInventoryAssignId);
		try {

			List<WorkEffortInventoryAssign> foundWorkEffortInventoryAssign = findWorkEffortInventoryAssignsBy(requestParams).getBody();
			if(foundWorkEffortInventoryAssign.size()==1){				return successful(foundWorkEffortInventoryAssign.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortInventoryAssignId}")
	public ResponseEntity<String> deleteWorkEffortInventoryAssignByIdUpdated(@PathVariable String workEffortInventoryAssignId) throws Exception {
		DeleteWorkEffortInventoryAssign command = new DeleteWorkEffortInventoryAssign(workEffortInventoryAssignId);

		try {
			if (((WorkEffortInventoryAssignDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
