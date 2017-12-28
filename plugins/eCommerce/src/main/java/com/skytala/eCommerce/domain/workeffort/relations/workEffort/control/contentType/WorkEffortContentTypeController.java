package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.contentType;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contentType.AddWorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contentType.DeleteWorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contentType.UpdateWorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contentType.WorkEffortContentTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contentType.WorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.contentType.FindWorkEffortContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortContentTypes")
public class WorkEffortContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortContentType
	 * @return a List with the WorkEffortContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortContentType>> findWorkEffortContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortContentTypesBy query = new FindWorkEffortContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortContentType> workEffortContentTypes =((WorkEffortContentTypeFound) Scheduler.execute(query).data()).getWorkEffortContentTypes();

		return ResponseEntity.ok().body(workEffortContentTypes);

	}

	/**
	 * creates a new WorkEffortContentType entry in the ofbiz database
	 * 
	 * @param workEffortContentTypeToBeAdded
	 *            the WorkEffortContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortContentType> createWorkEffortContentType(@RequestBody WorkEffortContentType workEffortContentTypeToBeAdded) throws Exception {

		AddWorkEffortContentType command = new AddWorkEffortContentType(workEffortContentTypeToBeAdded);
		WorkEffortContentType workEffortContentType = ((WorkEffortContentTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortContentType();
		
		if (workEffortContentType != null) 
			return successful(workEffortContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortContentType with the specific Id
	 * 
	 * @param workEffortContentTypeToBeUpdated
	 *            the WorkEffortContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortContentType(@RequestBody WorkEffortContentType workEffortContentTypeToBeUpdated,
			@PathVariable String workEffortContentTypeId) throws Exception {

		workEffortContentTypeToBeUpdated.setWorkEffortContentTypeId(workEffortContentTypeId);

		UpdateWorkEffortContentType command = new UpdateWorkEffortContentType(workEffortContentTypeToBeUpdated);

		try {
			if(((WorkEffortContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortContentTypeId}")
	public ResponseEntity<WorkEffortContentType> findById(@PathVariable String workEffortContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortContentTypeId", workEffortContentTypeId);
		try {

			List<WorkEffortContentType> foundWorkEffortContentType = findWorkEffortContentTypesBy(requestParams).getBody();
			if(foundWorkEffortContentType.size()==1){				return successful(foundWorkEffortContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortContentTypeId}")
	public ResponseEntity<String> deleteWorkEffortContentTypeByIdUpdated(@PathVariable String workEffortContentTypeId) throws Exception {
		DeleteWorkEffortContentType command = new DeleteWorkEffortContentType(workEffortContentTypeId);

		try {
			if (((WorkEffortContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
