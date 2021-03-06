package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.inventoryProduced;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced.AddWorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced.DeleteWorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced.UpdateWorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryProduced.WorkEffortInventoryProducedMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.inventoryProduced.FindWorkEffortInventoryProducedsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortInventoryProduceds")
public class WorkEffortInventoryProducedController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortInventoryProducedController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortInventoryProduced
	 * @return a List with the WorkEffortInventoryProduceds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortInventoryProduced>> findWorkEffortInventoryProducedsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortInventoryProducedsBy query = new FindWorkEffortInventoryProducedsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortInventoryProduced> workEffortInventoryProduceds =((WorkEffortInventoryProducedFound) Scheduler.execute(query).data()).getWorkEffortInventoryProduceds();

		return ResponseEntity.ok().body(workEffortInventoryProduceds);

	}

	/**
	 * creates a new WorkEffortInventoryProduced entry in the ofbiz database
	 * 
	 * @param workEffortInventoryProducedToBeAdded
	 *            the WorkEffortInventoryProduced thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortInventoryProduced> createWorkEffortInventoryProduced(@RequestBody WorkEffortInventoryProduced workEffortInventoryProducedToBeAdded) throws Exception {

		AddWorkEffortInventoryProduced command = new AddWorkEffortInventoryProduced(workEffortInventoryProducedToBeAdded);
		WorkEffortInventoryProduced workEffortInventoryProduced = ((WorkEffortInventoryProducedAdded) Scheduler.execute(command).data()).getAddedWorkEffortInventoryProduced();
		
		if (workEffortInventoryProduced != null) 
			return successful(workEffortInventoryProduced);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortInventoryProduced with the specific Id
	 * 
	 * @param workEffortInventoryProducedToBeUpdated
	 *            the WorkEffortInventoryProduced thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortInventoryProduced(@RequestBody WorkEffortInventoryProduced workEffortInventoryProducedToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortInventoryProducedToBeUpdated.setnull(null);

		UpdateWorkEffortInventoryProduced command = new UpdateWorkEffortInventoryProduced(workEffortInventoryProducedToBeUpdated);

		try {
			if(((WorkEffortInventoryProducedUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortInventoryProducedId}")
	public ResponseEntity<WorkEffortInventoryProduced> findById(@PathVariable String workEffortInventoryProducedId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortInventoryProducedId", workEffortInventoryProducedId);
		try {

			List<WorkEffortInventoryProduced> foundWorkEffortInventoryProduced = findWorkEffortInventoryProducedsBy(requestParams).getBody();
			if(foundWorkEffortInventoryProduced.size()==1){				return successful(foundWorkEffortInventoryProduced.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortInventoryProducedId}")
	public ResponseEntity<String> deleteWorkEffortInventoryProducedByIdUpdated(@PathVariable String workEffortInventoryProducedId) throws Exception {
		DeleteWorkEffortInventoryProduced command = new DeleteWorkEffortInventoryProduced(workEffortInventoryProducedId);

		try {
			if (((WorkEffortInventoryProducedDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
