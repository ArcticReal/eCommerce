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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortPurposeType>> findWorkEffortPurposeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortPurposeTypesBy query = new FindWorkEffortPurposeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortPurposeType> workEffortPurposeTypes =((WorkEffortPurposeTypeFound) Scheduler.execute(query).data()).getWorkEffortPurposeTypes();

		return ResponseEntity.ok().body(workEffortPurposeTypes);

	}

	/**
	 * creates a new WorkEffortPurposeType entry in the ofbiz database
	 * 
	 * @param workEffortPurposeTypeToBeAdded
	 *            the WorkEffortPurposeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortPurposeType> createWorkEffortPurposeType(@RequestBody WorkEffortPurposeType workEffortPurposeTypeToBeAdded) throws Exception {

		AddWorkEffortPurposeType command = new AddWorkEffortPurposeType(workEffortPurposeTypeToBeAdded);
		WorkEffortPurposeType workEffortPurposeType = ((WorkEffortPurposeTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortPurposeType();
		
		if (workEffortPurposeType != null) 
			return successful(workEffortPurposeType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortPurposeType(@RequestBody WorkEffortPurposeType workEffortPurposeTypeToBeUpdated,
			@PathVariable String workEffortPurposeTypeId) throws Exception {

		workEffortPurposeTypeToBeUpdated.setWorkEffortPurposeTypeId(workEffortPurposeTypeId);

		UpdateWorkEffortPurposeType command = new UpdateWorkEffortPurposeType(workEffortPurposeTypeToBeUpdated);

		try {
			if(((WorkEffortPurposeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortPurposeTypeId}")
	public ResponseEntity<WorkEffortPurposeType> findById(@PathVariable String workEffortPurposeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortPurposeTypeId", workEffortPurposeTypeId);
		try {

			List<WorkEffortPurposeType> foundWorkEffortPurposeType = findWorkEffortPurposeTypesBy(requestParams).getBody();
			if(foundWorkEffortPurposeType.size()==1){				return successful(foundWorkEffortPurposeType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortPurposeTypeId}")
	public ResponseEntity<String> deleteWorkEffortPurposeTypeByIdUpdated(@PathVariable String workEffortPurposeTypeId) throws Exception {
		DeleteWorkEffortPurposeType command = new DeleteWorkEffortPurposeType(workEffortPurposeTypeId);

		try {
			if (((WorkEffortPurposeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
