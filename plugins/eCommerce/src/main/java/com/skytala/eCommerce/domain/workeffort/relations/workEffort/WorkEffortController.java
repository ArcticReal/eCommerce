package com.skytala.eCommerce.domain.workeffort.relations.workEffort;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.AddWorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.DeleteWorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.UpdateWorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.WorkEffortMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.WorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.FindWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEfforts")
public class WorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffort
	 * @return a List with the WorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffort>> findWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortsBy query = new FindWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffort> workEfforts =((WorkEffortFound) Scheduler.execute(query).data()).getWorkEfforts();

		return ResponseEntity.ok().body(workEfforts);

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
	public ResponseEntity<WorkEffort> createWorkEffort(HttpServletRequest request) throws Exception {

		WorkEffort workEffortToBeAdded = new WorkEffort();
		try {
			workEffortToBeAdded = WorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkEffort(workEffortToBeAdded);

	}

	/**
	 * creates a new WorkEffort entry in the ofbiz database
	 * 
	 * @param workEffortToBeAdded
	 *            the WorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffort> createWorkEffort(@RequestBody WorkEffort workEffortToBeAdded) throws Exception {

		AddWorkEffort command = new AddWorkEffort(workEffortToBeAdded);
		WorkEffort workEffort = ((WorkEffortAdded) Scheduler.execute(command).data()).getAddedWorkEffort();
		
		if (workEffort != null) 
			return successful(workEffort);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffort with the specific Id
	 * 
	 * @param workEffortToBeUpdated
	 *            the WorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffort(@RequestBody WorkEffort workEffortToBeUpdated,
			@PathVariable String workEffortId) throws Exception {

		workEffortToBeUpdated.setWorkEffortId(workEffortId);

		UpdateWorkEffort command = new UpdateWorkEffort(workEffortToBeUpdated);

		try {
			if(((WorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortId}")
	public ResponseEntity<WorkEffort> findById(@PathVariable String workEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortId", workEffortId);
		try {

			List<WorkEffort> foundWorkEffort = findWorkEffortsBy(requestParams).getBody();
			if(foundWorkEffort.size()==1){				return successful(foundWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortId}")
	public ResponseEntity<String> deleteWorkEffortByIdUpdated(@PathVariable String workEffortId) throws Exception {
		DeleteWorkEffort command = new DeleteWorkEffort(workEffortId);

		try {
			if (((WorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
