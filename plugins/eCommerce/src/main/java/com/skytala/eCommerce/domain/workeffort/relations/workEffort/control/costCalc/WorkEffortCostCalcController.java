package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.costCalc;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc.AddWorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc.DeleteWorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc.UpdateWorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.costCalc.WorkEffortCostCalcMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc.WorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.costCalc.FindWorkEffortCostCalcsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortCostCalcs")
public class WorkEffortCostCalcController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortCostCalcController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortCostCalc
	 * @return a List with the WorkEffortCostCalcs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortCostCalc>> findWorkEffortCostCalcsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortCostCalcsBy query = new FindWorkEffortCostCalcsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortCostCalc> workEffortCostCalcs =((WorkEffortCostCalcFound) Scheduler.execute(query).data()).getWorkEffortCostCalcs();

		return ResponseEntity.ok().body(workEffortCostCalcs);

	}

	/**
	 * creates a new WorkEffortCostCalc entry in the ofbiz database
	 * 
	 * @param workEffortCostCalcToBeAdded
	 *            the WorkEffortCostCalc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortCostCalc> createWorkEffortCostCalc(@RequestBody WorkEffortCostCalc workEffortCostCalcToBeAdded) throws Exception {

		AddWorkEffortCostCalc command = new AddWorkEffortCostCalc(workEffortCostCalcToBeAdded);
		WorkEffortCostCalc workEffortCostCalc = ((WorkEffortCostCalcAdded) Scheduler.execute(command).data()).getAddedWorkEffortCostCalc();
		
		if (workEffortCostCalc != null) 
			return successful(workEffortCostCalc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortCostCalc with the specific Id
	 * 
	 * @param workEffortCostCalcToBeUpdated
	 *            the WorkEffortCostCalc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortCostCalc(@RequestBody WorkEffortCostCalc workEffortCostCalcToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortCostCalcToBeUpdated.setnull(null);

		UpdateWorkEffortCostCalc command = new UpdateWorkEffortCostCalc(workEffortCostCalcToBeUpdated);

		try {
			if(((WorkEffortCostCalcUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortCostCalcId}")
	public ResponseEntity<WorkEffortCostCalc> findById(@PathVariable String workEffortCostCalcId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortCostCalcId", workEffortCostCalcId);
		try {

			List<WorkEffortCostCalc> foundWorkEffortCostCalc = findWorkEffortCostCalcsBy(requestParams).getBody();
			if(foundWorkEffortCostCalc.size()==1){				return successful(foundWorkEffortCostCalc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortCostCalcId}")
	public ResponseEntity<String> deleteWorkEffortCostCalcByIdUpdated(@PathVariable String workEffortCostCalcId) throws Exception {
		DeleteWorkEffortCostCalc command = new DeleteWorkEffortCostCalc(workEffortCostCalcId);

		try {
			if (((WorkEffortCostCalcDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
