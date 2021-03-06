package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.skillStandard;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard.AddWorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard.DeleteWorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.skillStandard.UpdateWorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.skillStandard.WorkEffortSkillStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.skillStandard.FindWorkEffortSkillStandardsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortSkillStandards")
public class WorkEffortSkillStandardController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortSkillStandardController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortSkillStandard
	 * @return a List with the WorkEffortSkillStandards
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortSkillStandard>> findWorkEffortSkillStandardsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSkillStandardsBy query = new FindWorkEffortSkillStandardsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSkillStandard> workEffortSkillStandards =((WorkEffortSkillStandardFound) Scheduler.execute(query).data()).getWorkEffortSkillStandards();

		return ResponseEntity.ok().body(workEffortSkillStandards);

	}

	/**
	 * creates a new WorkEffortSkillStandard entry in the ofbiz database
	 * 
	 * @param workEffortSkillStandardToBeAdded
	 *            the WorkEffortSkillStandard thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortSkillStandard> createWorkEffortSkillStandard(@RequestBody WorkEffortSkillStandard workEffortSkillStandardToBeAdded) throws Exception {

		AddWorkEffortSkillStandard command = new AddWorkEffortSkillStandard(workEffortSkillStandardToBeAdded);
		WorkEffortSkillStandard workEffortSkillStandard = ((WorkEffortSkillStandardAdded) Scheduler.execute(command).data()).getAddedWorkEffortSkillStandard();
		
		if (workEffortSkillStandard != null) 
			return successful(workEffortSkillStandard);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortSkillStandard with the specific Id
	 * 
	 * @param workEffortSkillStandardToBeUpdated
	 *            the WorkEffortSkillStandard thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortSkillStandard(@RequestBody WorkEffortSkillStandard workEffortSkillStandardToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortSkillStandardToBeUpdated.setnull(null);

		UpdateWorkEffortSkillStandard command = new UpdateWorkEffortSkillStandard(workEffortSkillStandardToBeUpdated);

		try {
			if(((WorkEffortSkillStandardUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortSkillStandardId}")
	public ResponseEntity<WorkEffortSkillStandard> findById(@PathVariable String workEffortSkillStandardId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSkillStandardId", workEffortSkillStandardId);
		try {

			List<WorkEffortSkillStandard> foundWorkEffortSkillStandard = findWorkEffortSkillStandardsBy(requestParams).getBody();
			if(foundWorkEffortSkillStandard.size()==1){				return successful(foundWorkEffortSkillStandard.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortSkillStandardId}")
	public ResponseEntity<String> deleteWorkEffortSkillStandardByIdUpdated(@PathVariable String workEffortSkillStandardId) throws Exception {
		DeleteWorkEffortSkillStandard command = new DeleteWorkEffortSkillStandard(workEffortSkillStandardId);

		try {
			if (((WorkEffortSkillStandardDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
