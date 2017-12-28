package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.type;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.type.AddWorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.type.DeleteWorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.type.UpdateWorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type.WorkEffortTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type.WorkEffortTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type.WorkEffortTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.type.WorkEffortTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.type.WorkEffortTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.type.WorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.type.FindWorkEffortTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortTypes")
public class WorkEffortTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortType
	 * @return a List with the WorkEffortTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortType>> findWorkEffortTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortTypesBy query = new FindWorkEffortTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortType> workEffortTypes =((WorkEffortTypeFound) Scheduler.execute(query).data()).getWorkEffortTypes();

		return ResponseEntity.ok().body(workEffortTypes);

	}

	/**
	 * creates a new WorkEffortType entry in the ofbiz database
	 * 
	 * @param workEffortTypeToBeAdded
	 *            the WorkEffortType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortType> createWorkEffortType(@RequestBody WorkEffortType workEffortTypeToBeAdded) throws Exception {

		AddWorkEffortType command = new AddWorkEffortType(workEffortTypeToBeAdded);
		WorkEffortType workEffortType = ((WorkEffortTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortType();
		
		if (workEffortType != null) 
			return successful(workEffortType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortType with the specific Id
	 * 
	 * @param workEffortTypeToBeUpdated
	 *            the WorkEffortType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortType(@RequestBody WorkEffortType workEffortTypeToBeUpdated,
			@PathVariable String workEffortTypeId) throws Exception {

		workEffortTypeToBeUpdated.setWorkEffortTypeId(workEffortTypeId);

		UpdateWorkEffortType command = new UpdateWorkEffortType(workEffortTypeToBeUpdated);

		try {
			if(((WorkEffortTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortTypeId}")
	public ResponseEntity<WorkEffortType> findById(@PathVariable String workEffortTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortTypeId", workEffortTypeId);
		try {

			List<WorkEffortType> foundWorkEffortType = findWorkEffortTypesBy(requestParams).getBody();
			if(foundWorkEffortType.size()==1){				return successful(foundWorkEffortType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortTypeId}")
	public ResponseEntity<String> deleteWorkEffortTypeByIdUpdated(@PathVariable String workEffortTypeId) throws Exception {
		DeleteWorkEffortType command = new DeleteWorkEffortType(workEffortTypeId);

		try {
			if (((WorkEffortTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
