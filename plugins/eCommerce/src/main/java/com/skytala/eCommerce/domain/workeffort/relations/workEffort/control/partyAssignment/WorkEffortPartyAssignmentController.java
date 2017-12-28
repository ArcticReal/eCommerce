package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.partyAssignment;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.partyAssignment.AddWorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.partyAssignment.DeleteWorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.partyAssignment.UpdateWorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment.WorkEffortPartyAssignmentAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment.WorkEffortPartyAssignmentDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment.WorkEffortPartyAssignmentFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment.WorkEffortPartyAssignmentUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.partyAssignment.WorkEffortPartyAssignmentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.partyAssignment.WorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.partyAssignment.FindWorkEffortPartyAssignmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortPartyAssignments")
public class WorkEffortPartyAssignmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortPartyAssignmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortPartyAssignment
	 * @return a List with the WorkEffortPartyAssignments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortPartyAssignment>> findWorkEffortPartyAssignmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortPartyAssignmentsBy query = new FindWorkEffortPartyAssignmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortPartyAssignment> workEffortPartyAssignments =((WorkEffortPartyAssignmentFound) Scheduler.execute(query).data()).getWorkEffortPartyAssignments();

		return ResponseEntity.ok().body(workEffortPartyAssignments);

	}

	/**
	 * creates a new WorkEffortPartyAssignment entry in the ofbiz database
	 * 
	 * @param workEffortPartyAssignmentToBeAdded
	 *            the WorkEffortPartyAssignment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortPartyAssignment> createWorkEffortPartyAssignment(@RequestBody WorkEffortPartyAssignment workEffortPartyAssignmentToBeAdded) throws Exception {

		AddWorkEffortPartyAssignment command = new AddWorkEffortPartyAssignment(workEffortPartyAssignmentToBeAdded);
		WorkEffortPartyAssignment workEffortPartyAssignment = ((WorkEffortPartyAssignmentAdded) Scheduler.execute(command).data()).getAddedWorkEffortPartyAssignment();
		
		if (workEffortPartyAssignment != null) 
			return successful(workEffortPartyAssignment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortPartyAssignment with the specific Id
	 * 
	 * @param workEffortPartyAssignmentToBeUpdated
	 *            the WorkEffortPartyAssignment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortPartyAssignment(@RequestBody WorkEffortPartyAssignment workEffortPartyAssignmentToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		workEffortPartyAssignmentToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateWorkEffortPartyAssignment command = new UpdateWorkEffortPartyAssignment(workEffortPartyAssignmentToBeUpdated);

		try {
			if(((WorkEffortPartyAssignmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortPartyAssignmentId}")
	public ResponseEntity<WorkEffortPartyAssignment> findById(@PathVariable String workEffortPartyAssignmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortPartyAssignmentId", workEffortPartyAssignmentId);
		try {

			List<WorkEffortPartyAssignment> foundWorkEffortPartyAssignment = findWorkEffortPartyAssignmentsBy(requestParams).getBody();
			if(foundWorkEffortPartyAssignment.size()==1){				return successful(foundWorkEffortPartyAssignment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortPartyAssignmentId}")
	public ResponseEntity<String> deleteWorkEffortPartyAssignmentByIdUpdated(@PathVariable String workEffortPartyAssignmentId) throws Exception {
		DeleteWorkEffortPartyAssignment command = new DeleteWorkEffortPartyAssignment(workEffortPartyAssignmentId);

		try {
			if (((WorkEffortPartyAssignmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
