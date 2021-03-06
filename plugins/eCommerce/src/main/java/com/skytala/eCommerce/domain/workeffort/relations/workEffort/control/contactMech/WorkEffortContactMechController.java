package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.contactMech;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contactMech.AddWorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contactMech.DeleteWorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contactMech.UpdateWorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contactMech.WorkEffortContactMechMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.contactMech.FindWorkEffortContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortContactMechs")
public class WorkEffortContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortContactMech
	 * @return a List with the WorkEffortContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortContactMech>> findWorkEffortContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortContactMechsBy query = new FindWorkEffortContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortContactMech> workEffortContactMechs =((WorkEffortContactMechFound) Scheduler.execute(query).data()).getWorkEffortContactMechs();

		return ResponseEntity.ok().body(workEffortContactMechs);

	}

	/**
	 * creates a new WorkEffortContactMech entry in the ofbiz database
	 * 
	 * @param workEffortContactMechToBeAdded
	 *            the WorkEffortContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortContactMech> createWorkEffortContactMech(@RequestBody WorkEffortContactMech workEffortContactMechToBeAdded) throws Exception {

		AddWorkEffortContactMech command = new AddWorkEffortContactMech(workEffortContactMechToBeAdded);
		WorkEffortContactMech workEffortContactMech = ((WorkEffortContactMechAdded) Scheduler.execute(command).data()).getAddedWorkEffortContactMech();
		
		if (workEffortContactMech != null) 
			return successful(workEffortContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortContactMech with the specific Id
	 * 
	 * @param workEffortContactMechToBeUpdated
	 *            the WorkEffortContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortContactMech(@RequestBody WorkEffortContactMech workEffortContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortContactMechToBeUpdated.setnull(null);

		UpdateWorkEffortContactMech command = new UpdateWorkEffortContactMech(workEffortContactMechToBeUpdated);

		try {
			if(((WorkEffortContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortContactMechId}")
	public ResponseEntity<WorkEffortContactMech> findById(@PathVariable String workEffortContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortContactMechId", workEffortContactMechId);
		try {

			List<WorkEffortContactMech> foundWorkEffortContactMech = findWorkEffortContactMechsBy(requestParams).getBody();
			if(foundWorkEffortContactMech.size()==1){				return successful(foundWorkEffortContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortContactMechId}")
	public ResponseEntity<String> deleteWorkEffortContactMechByIdUpdated(@PathVariable String workEffortContactMechId) throws Exception {
		DeleteWorkEffortContactMech command = new DeleteWorkEffortContactMech(workEffortContactMechId);

		try {
			if (((WorkEffortContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
