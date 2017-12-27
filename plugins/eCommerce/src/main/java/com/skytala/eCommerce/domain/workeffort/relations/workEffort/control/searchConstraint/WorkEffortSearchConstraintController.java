package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.searchConstraint;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint.AddWorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint.DeleteWorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint.UpdateWorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.searchConstraint.WorkEffortSearchConstraintMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.searchConstraint.FindWorkEffortSearchConstraintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortSearchConstraints")
public class WorkEffortSearchConstraintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortSearchConstraintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortSearchConstraint
	 * @return a List with the WorkEffortSearchConstraints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortSearchConstraint>> findWorkEffortSearchConstraintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSearchConstraintsBy query = new FindWorkEffortSearchConstraintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSearchConstraint> workEffortSearchConstraints =((WorkEffortSearchConstraintFound) Scheduler.execute(query).data()).getWorkEffortSearchConstraints();

		return ResponseEntity.ok().body(workEffortSearchConstraints);

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
	public ResponseEntity<WorkEffortSearchConstraint> createWorkEffortSearchConstraint(HttpServletRequest request) throws Exception {

		WorkEffortSearchConstraint workEffortSearchConstraintToBeAdded = new WorkEffortSearchConstraint();
		try {
			workEffortSearchConstraintToBeAdded = WorkEffortSearchConstraintMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkEffortSearchConstraint(workEffortSearchConstraintToBeAdded);

	}

	/**
	 * creates a new WorkEffortSearchConstraint entry in the ofbiz database
	 * 
	 * @param workEffortSearchConstraintToBeAdded
	 *            the WorkEffortSearchConstraint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortSearchConstraint> createWorkEffortSearchConstraint(@RequestBody WorkEffortSearchConstraint workEffortSearchConstraintToBeAdded) throws Exception {

		AddWorkEffortSearchConstraint command = new AddWorkEffortSearchConstraint(workEffortSearchConstraintToBeAdded);
		WorkEffortSearchConstraint workEffortSearchConstraint = ((WorkEffortSearchConstraintAdded) Scheduler.execute(command).data()).getAddedWorkEffortSearchConstraint();
		
		if (workEffortSearchConstraint != null) 
			return successful(workEffortSearchConstraint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortSearchConstraint with the specific Id
	 * 
	 * @param workEffortSearchConstraintToBeUpdated
	 *            the WorkEffortSearchConstraint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{constraintSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortSearchConstraint(@RequestBody WorkEffortSearchConstraint workEffortSearchConstraintToBeUpdated,
			@PathVariable String constraintSeqId) throws Exception {

		workEffortSearchConstraintToBeUpdated.setConstraintSeqId(constraintSeqId);

		UpdateWorkEffortSearchConstraint command = new UpdateWorkEffortSearchConstraint(workEffortSearchConstraintToBeUpdated);

		try {
			if(((WorkEffortSearchConstraintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortSearchConstraintId}")
	public ResponseEntity<WorkEffortSearchConstraint> findById(@PathVariable String workEffortSearchConstraintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSearchConstraintId", workEffortSearchConstraintId);
		try {

			List<WorkEffortSearchConstraint> foundWorkEffortSearchConstraint = findWorkEffortSearchConstraintsBy(requestParams).getBody();
			if(foundWorkEffortSearchConstraint.size()==1){				return successful(foundWorkEffortSearchConstraint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortSearchConstraintId}")
	public ResponseEntity<String> deleteWorkEffortSearchConstraintByIdUpdated(@PathVariable String workEffortSearchConstraintId) throws Exception {
		DeleteWorkEffortSearchConstraint command = new DeleteWorkEffortSearchConstraint(workEffortSearchConstraintId);

		try {
			if (((WorkEffortSearchConstraintDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
