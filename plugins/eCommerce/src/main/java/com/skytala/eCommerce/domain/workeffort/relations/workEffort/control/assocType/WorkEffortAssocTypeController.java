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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<WorkEffortAssocType>> findWorkEffortAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocTypesBy query = new FindWorkEffortAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssocType> workEffortAssocTypes =((WorkEffortAssocTypeFound) Scheduler.execute(query).data()).getWorkEffortAssocTypes();

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
	public ResponseEntity<WorkEffortAssocType> createWorkEffortAssocType(HttpServletRequest request) throws Exception {

		WorkEffortAssocType workEffortAssocTypeToBeAdded = new WorkEffortAssocType();
		try {
			workEffortAssocTypeToBeAdded = WorkEffortAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WorkEffortAssocType> createWorkEffortAssocType(@RequestBody WorkEffortAssocType workEffortAssocTypeToBeAdded) throws Exception {

		AddWorkEffortAssocType command = new AddWorkEffortAssocType(workEffortAssocTypeToBeAdded);
		WorkEffortAssocType workEffortAssocType = ((WorkEffortAssocTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssocType();
		
		if (workEffortAssocType != null) 
			return successful(workEffortAssocType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortAssocType(@RequestBody WorkEffortAssocType workEffortAssocTypeToBeUpdated,
			@PathVariable String workEffortAssocTypeId) throws Exception {

		workEffortAssocTypeToBeUpdated.setWorkEffortAssocTypeId(workEffortAssocTypeId);

		UpdateWorkEffortAssocType command = new UpdateWorkEffortAssocType(workEffortAssocTypeToBeUpdated);

		try {
			if(((WorkEffortAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortAssocTypeId}")
	public ResponseEntity<WorkEffortAssocType> findById(@PathVariable String workEffortAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocTypeId", workEffortAssocTypeId);
		try {

			List<WorkEffortAssocType> foundWorkEffortAssocType = findWorkEffortAssocTypesBy(requestParams).getBody();
			if(foundWorkEffortAssocType.size()==1){				return successful(foundWorkEffortAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortAssocTypeId}")
	public ResponseEntity<String> deleteWorkEffortAssocTypeByIdUpdated(@PathVariable String workEffortAssocTypeId) throws Exception {
		DeleteWorkEffortAssocType command = new DeleteWorkEffortAssocType(workEffortAssocTypeId);

		try {
			if (((WorkEffortAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
