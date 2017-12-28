package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.assoc;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assoc.AddWorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assoc.DeleteWorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assoc.UpdateWorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assoc.WorkEffortAssocMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assoc.WorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assoc.FindWorkEffortAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAssocs")
public class WorkEffortAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAssoc
	 * @return a List with the WorkEffortAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortAssoc>> findWorkEffortAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocsBy query = new FindWorkEffortAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssoc> workEffortAssocs =((WorkEffortAssocFound) Scheduler.execute(query).data()).getWorkEffortAssocs();

		return ResponseEntity.ok().body(workEffortAssocs);

	}

	/**
	 * creates a new WorkEffortAssoc entry in the ofbiz database
	 * 
	 * @param workEffortAssocToBeAdded
	 *            the WorkEffortAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortAssoc> createWorkEffortAssoc(@RequestBody WorkEffortAssoc workEffortAssocToBeAdded) throws Exception {

		AddWorkEffortAssoc command = new AddWorkEffortAssoc(workEffortAssocToBeAdded);
		WorkEffortAssoc workEffortAssoc = ((WorkEffortAssocAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssoc();
		
		if (workEffortAssoc != null) 
			return successful(workEffortAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortAssoc with the specific Id
	 * 
	 * @param workEffortAssocToBeUpdated
	 *            the WorkEffortAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortAssoc(@RequestBody WorkEffortAssoc workEffortAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortAssocToBeUpdated.setnull(null);

		UpdateWorkEffortAssoc command = new UpdateWorkEffortAssoc(workEffortAssocToBeUpdated);

		try {
			if(((WorkEffortAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortAssocId}")
	public ResponseEntity<WorkEffortAssoc> findById(@PathVariable String workEffortAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocId", workEffortAssocId);
		try {

			List<WorkEffortAssoc> foundWorkEffortAssoc = findWorkEffortAssocsBy(requestParams).getBody();
			if(foundWorkEffortAssoc.size()==1){				return successful(foundWorkEffortAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortAssocId}")
	public ResponseEntity<String> deleteWorkEffortAssocByIdUpdated(@PathVariable String workEffortAssocId) throws Exception {
		DeleteWorkEffortAssoc command = new DeleteWorkEffortAssoc(workEffortAssocId);

		try {
			if (((WorkEffortAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
