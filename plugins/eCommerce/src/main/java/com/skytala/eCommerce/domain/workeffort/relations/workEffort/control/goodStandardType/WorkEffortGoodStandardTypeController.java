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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<WorkEffortGoodStandardType>> findWorkEffortGoodStandardTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortGoodStandardTypesBy query = new FindWorkEffortGoodStandardTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortGoodStandardType> workEffortGoodStandardTypes =((WorkEffortGoodStandardTypeFound) Scheduler.execute(query).data()).getWorkEffortGoodStandardTypes();

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
	public ResponseEntity<WorkEffortGoodStandardType> createWorkEffortGoodStandardType(HttpServletRequest request) throws Exception {

		WorkEffortGoodStandardType workEffortGoodStandardTypeToBeAdded = new WorkEffortGoodStandardType();
		try {
			workEffortGoodStandardTypeToBeAdded = WorkEffortGoodStandardTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WorkEffortGoodStandardType> createWorkEffortGoodStandardType(@RequestBody WorkEffortGoodStandardType workEffortGoodStandardTypeToBeAdded) throws Exception {

		AddWorkEffortGoodStandardType command = new AddWorkEffortGoodStandardType(workEffortGoodStandardTypeToBeAdded);
		WorkEffortGoodStandardType workEffortGoodStandardType = ((WorkEffortGoodStandardTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortGoodStandardType();
		
		if (workEffortGoodStandardType != null) 
			return successful(workEffortGoodStandardType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortGoodStandardType(@RequestBody WorkEffortGoodStandardType workEffortGoodStandardTypeToBeUpdated,
			@PathVariable String workEffortGoodStdTypeId) throws Exception {

		workEffortGoodStandardTypeToBeUpdated.setWorkEffortGoodStdTypeId(workEffortGoodStdTypeId);

		UpdateWorkEffortGoodStandardType command = new UpdateWorkEffortGoodStandardType(workEffortGoodStandardTypeToBeUpdated);

		try {
			if(((WorkEffortGoodStandardTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortGoodStandardTypeId}")
	public ResponseEntity<WorkEffortGoodStandardType> findById(@PathVariable String workEffortGoodStandardTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortGoodStandardTypeId", workEffortGoodStandardTypeId);
		try {

			List<WorkEffortGoodStandardType> foundWorkEffortGoodStandardType = findWorkEffortGoodStandardTypesBy(requestParams).getBody();
			if(foundWorkEffortGoodStandardType.size()==1){				return successful(foundWorkEffortGoodStandardType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortGoodStandardTypeId}")
	public ResponseEntity<String> deleteWorkEffortGoodStandardTypeByIdUpdated(@PathVariable String workEffortGoodStandardTypeId) throws Exception {
		DeleteWorkEffortGoodStandardType command = new DeleteWorkEffortGoodStandardType(workEffortGoodStandardTypeId);

		try {
			if (((WorkEffortGoodStandardTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
