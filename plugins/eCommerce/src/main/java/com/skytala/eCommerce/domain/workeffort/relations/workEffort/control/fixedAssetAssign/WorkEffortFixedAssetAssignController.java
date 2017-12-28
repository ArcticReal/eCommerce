package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.fixedAssetAssign;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetAssign.AddWorkEffortFixedAssetAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetAssign.DeleteWorkEffortFixedAssetAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetAssign.UpdateWorkEffortFixedAssetAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign.WorkEffortFixedAssetAssignAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign.WorkEffortFixedAssetAssignDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign.WorkEffortFixedAssetAssignFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign.WorkEffortFixedAssetAssignUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetAssign.WorkEffortFixedAssetAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetAssign.WorkEffortFixedAssetAssign;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.fixedAssetAssign.FindWorkEffortFixedAssetAssignsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortFixedAssetAssigns")
public class WorkEffortFixedAssetAssignController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortFixedAssetAssignController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortFixedAssetAssign
	 * @return a List with the WorkEffortFixedAssetAssigns
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortFixedAssetAssign>> findWorkEffortFixedAssetAssignsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortFixedAssetAssignsBy query = new FindWorkEffortFixedAssetAssignsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortFixedAssetAssign> workEffortFixedAssetAssigns =((WorkEffortFixedAssetAssignFound) Scheduler.execute(query).data()).getWorkEffortFixedAssetAssigns();

		return ResponseEntity.ok().body(workEffortFixedAssetAssigns);

	}

	/**
	 * creates a new WorkEffortFixedAssetAssign entry in the ofbiz database
	 * 
	 * @param workEffortFixedAssetAssignToBeAdded
	 *            the WorkEffortFixedAssetAssign thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortFixedAssetAssign> createWorkEffortFixedAssetAssign(@RequestBody WorkEffortFixedAssetAssign workEffortFixedAssetAssignToBeAdded) throws Exception {

		AddWorkEffortFixedAssetAssign command = new AddWorkEffortFixedAssetAssign(workEffortFixedAssetAssignToBeAdded);
		WorkEffortFixedAssetAssign workEffortFixedAssetAssign = ((WorkEffortFixedAssetAssignAdded) Scheduler.execute(command).data()).getAddedWorkEffortFixedAssetAssign();
		
		if (workEffortFixedAssetAssign != null) 
			return successful(workEffortFixedAssetAssign);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortFixedAssetAssign with the specific Id
	 * 
	 * @param workEffortFixedAssetAssignToBeUpdated
	 *            the WorkEffortFixedAssetAssign thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortFixedAssetAssign(@RequestBody WorkEffortFixedAssetAssign workEffortFixedAssetAssignToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortFixedAssetAssignToBeUpdated.setnull(null);

		UpdateWorkEffortFixedAssetAssign command = new UpdateWorkEffortFixedAssetAssign(workEffortFixedAssetAssignToBeUpdated);

		try {
			if(((WorkEffortFixedAssetAssignUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortFixedAssetAssignId}")
	public ResponseEntity<WorkEffortFixedAssetAssign> findById(@PathVariable String workEffortFixedAssetAssignId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortFixedAssetAssignId", workEffortFixedAssetAssignId);
		try {

			List<WorkEffortFixedAssetAssign> foundWorkEffortFixedAssetAssign = findWorkEffortFixedAssetAssignsBy(requestParams).getBody();
			if(foundWorkEffortFixedAssetAssign.size()==1){				return successful(foundWorkEffortFixedAssetAssign.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortFixedAssetAssignId}")
	public ResponseEntity<String> deleteWorkEffortFixedAssetAssignByIdUpdated(@PathVariable String workEffortFixedAssetAssignId) throws Exception {
		DeleteWorkEffortFixedAssetAssign command = new DeleteWorkEffortFixedAssetAssign(workEffortFixedAssetAssignId);

		try {
			if (((WorkEffortFixedAssetAssignDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
