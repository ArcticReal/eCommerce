package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.status;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status.AddWorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status.DeleteWorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status.UpdateWorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.status.WorkEffortStatusMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.status.FindWorkEffortStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortStatuss")
public class WorkEffortStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortStatus
	 * @return a List with the WorkEffortStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortStatus>> findWorkEffortStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortStatussBy query = new FindWorkEffortStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortStatus> workEffortStatuss =((WorkEffortStatusFound) Scheduler.execute(query).data()).getWorkEffortStatuss();

		return ResponseEntity.ok().body(workEffortStatuss);

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
	public ResponseEntity<WorkEffortStatus> createWorkEffortStatus(HttpServletRequest request) throws Exception {

		WorkEffortStatus workEffortStatusToBeAdded = new WorkEffortStatus();
		try {
			workEffortStatusToBeAdded = WorkEffortStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkEffortStatus(workEffortStatusToBeAdded);

	}

	/**
	 * creates a new WorkEffortStatus entry in the ofbiz database
	 * 
	 * @param workEffortStatusToBeAdded
	 *            the WorkEffortStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortStatus> createWorkEffortStatus(@RequestBody WorkEffortStatus workEffortStatusToBeAdded) throws Exception {

		AddWorkEffortStatus command = new AddWorkEffortStatus(workEffortStatusToBeAdded);
		WorkEffortStatus workEffortStatus = ((WorkEffortStatusAdded) Scheduler.execute(command).data()).getAddedWorkEffortStatus();
		
		if (workEffortStatus != null) 
			return successful(workEffortStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortStatus with the specific Id
	 * 
	 * @param workEffortStatusToBeUpdated
	 *            the WorkEffortStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortStatus(@RequestBody WorkEffortStatus workEffortStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortStatusToBeUpdated.setnull(null);

		UpdateWorkEffortStatus command = new UpdateWorkEffortStatus(workEffortStatusToBeUpdated);

		try {
			if(((WorkEffortStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortStatusId}")
	public ResponseEntity<WorkEffortStatus> findById(@PathVariable String workEffortStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortStatusId", workEffortStatusId);
		try {

			List<WorkEffortStatus> foundWorkEffortStatus = findWorkEffortStatussBy(requestParams).getBody();
			if(foundWorkEffortStatus.size()==1){				return successful(foundWorkEffortStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortStatusId}")
	public ResponseEntity<String> deleteWorkEffortStatusByIdUpdated(@PathVariable String workEffortStatusId) throws Exception {
		DeleteWorkEffortStatus command = new DeleteWorkEffortStatus(workEffortStatusId);

		try {
			if (((WorkEffortStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
