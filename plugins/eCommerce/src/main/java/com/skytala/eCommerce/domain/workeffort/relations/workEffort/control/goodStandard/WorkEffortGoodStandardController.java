package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.goodStandard;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandard.AddWorkEffortGoodStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandard.DeleteWorkEffortGoodStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandard.UpdateWorkEffortGoodStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard.WorkEffortGoodStandardAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard.WorkEffortGoodStandardDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard.WorkEffortGoodStandardFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandard.WorkEffortGoodStandardUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandard.WorkEffortGoodStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandard.WorkEffortGoodStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.goodStandard.FindWorkEffortGoodStandardsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortGoodStandards")
public class WorkEffortGoodStandardController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortGoodStandardController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortGoodStandard
	 * @return a List with the WorkEffortGoodStandards
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortGoodStandard>> findWorkEffortGoodStandardsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortGoodStandardsBy query = new FindWorkEffortGoodStandardsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortGoodStandard> workEffortGoodStandards =((WorkEffortGoodStandardFound) Scheduler.execute(query).data()).getWorkEffortGoodStandards();

		return ResponseEntity.ok().body(workEffortGoodStandards);

	}

	/**
	 * creates a new WorkEffortGoodStandard entry in the ofbiz database
	 * 
	 * @param workEffortGoodStandardToBeAdded
	 *            the WorkEffortGoodStandard thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortGoodStandard> createWorkEffortGoodStandard(@RequestBody WorkEffortGoodStandard workEffortGoodStandardToBeAdded) throws Exception {

		AddWorkEffortGoodStandard command = new AddWorkEffortGoodStandard(workEffortGoodStandardToBeAdded);
		WorkEffortGoodStandard workEffortGoodStandard = ((WorkEffortGoodStandardAdded) Scheduler.execute(command).data()).getAddedWorkEffortGoodStandard();
		
		if (workEffortGoodStandard != null) 
			return successful(workEffortGoodStandard);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortGoodStandard with the specific Id
	 * 
	 * @param workEffortGoodStandardToBeUpdated
	 *            the WorkEffortGoodStandard thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortGoodStandard(@RequestBody WorkEffortGoodStandard workEffortGoodStandardToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortGoodStandardToBeUpdated.setnull(null);

		UpdateWorkEffortGoodStandard command = new UpdateWorkEffortGoodStandard(workEffortGoodStandardToBeUpdated);

		try {
			if(((WorkEffortGoodStandardUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortGoodStandardId}")
	public ResponseEntity<WorkEffortGoodStandard> findById(@PathVariable String workEffortGoodStandardId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortGoodStandardId", workEffortGoodStandardId);
		try {

			List<WorkEffortGoodStandard> foundWorkEffortGoodStandard = findWorkEffortGoodStandardsBy(requestParams).getBody();
			if(foundWorkEffortGoodStandard.size()==1){				return successful(foundWorkEffortGoodStandard.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortGoodStandardId}")
	public ResponseEntity<String> deleteWorkEffortGoodStandardByIdUpdated(@PathVariable String workEffortGoodStandardId) throws Exception {
		DeleteWorkEffortGoodStandard command = new DeleteWorkEffortGoodStandard(workEffortGoodStandardId);

		try {
			if (((WorkEffortGoodStandardDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
