package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.transBox;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.transBox.AddWorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.transBox.DeleteWorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.transBox.UpdateWorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.transBox.WorkEffortTransBoxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.transBox.FindWorkEffortTransBoxsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortTransBoxs")
public class WorkEffortTransBoxController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortTransBoxController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortTransBox
	 * @return a List with the WorkEffortTransBoxs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortTransBox>> findWorkEffortTransBoxsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortTransBoxsBy query = new FindWorkEffortTransBoxsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortTransBox> workEffortTransBoxs =((WorkEffortTransBoxFound) Scheduler.execute(query).data()).getWorkEffortTransBoxs();

		return ResponseEntity.ok().body(workEffortTransBoxs);

	}

	/**
	 * creates a new WorkEffortTransBox entry in the ofbiz database
	 * 
	 * @param workEffortTransBoxToBeAdded
	 *            the WorkEffortTransBox thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortTransBox> createWorkEffortTransBox(@RequestBody WorkEffortTransBox workEffortTransBoxToBeAdded) throws Exception {

		AddWorkEffortTransBox command = new AddWorkEffortTransBox(workEffortTransBoxToBeAdded);
		WorkEffortTransBox workEffortTransBox = ((WorkEffortTransBoxAdded) Scheduler.execute(command).data()).getAddedWorkEffortTransBox();
		
		if (workEffortTransBox != null) 
			return successful(workEffortTransBox);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortTransBox with the specific Id
	 * 
	 * @param workEffortTransBoxToBeUpdated
	 *            the WorkEffortTransBox thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortTransBox(@RequestBody WorkEffortTransBox workEffortTransBoxToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortTransBoxToBeUpdated.setnull(null);

		UpdateWorkEffortTransBox command = new UpdateWorkEffortTransBox(workEffortTransBoxToBeUpdated);

		try {
			if(((WorkEffortTransBoxUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortTransBoxId}")
	public ResponseEntity<WorkEffortTransBox> findById(@PathVariable String workEffortTransBoxId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortTransBoxId", workEffortTransBoxId);
		try {

			List<WorkEffortTransBox> foundWorkEffortTransBox = findWorkEffortTransBoxsBy(requestParams).getBody();
			if(foundWorkEffortTransBox.size()==1){				return successful(foundWorkEffortTransBox.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortTransBoxId}")
	public ResponseEntity<String> deleteWorkEffortTransBoxByIdUpdated(@PathVariable String workEffortTransBoxId) throws Exception {
		DeleteWorkEffortTransBox command = new DeleteWorkEffortTransBox(workEffortTransBoxId);

		try {
			if (((WorkEffortTransBoxDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
