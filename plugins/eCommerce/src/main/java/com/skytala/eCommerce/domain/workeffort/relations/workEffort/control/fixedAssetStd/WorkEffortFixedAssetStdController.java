package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.fixedAssetStd;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd.AddWorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd.DeleteWorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd.UpdateWorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetStd.WorkEffortFixedAssetStdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.fixedAssetStd.FindWorkEffortFixedAssetStdsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortFixedAssetStds")
public class WorkEffortFixedAssetStdController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortFixedAssetStdController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortFixedAssetStd
	 * @return a List with the WorkEffortFixedAssetStds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortFixedAssetStd>> findWorkEffortFixedAssetStdsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortFixedAssetStdsBy query = new FindWorkEffortFixedAssetStdsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortFixedAssetStd> workEffortFixedAssetStds =((WorkEffortFixedAssetStdFound) Scheduler.execute(query).data()).getWorkEffortFixedAssetStds();

		return ResponseEntity.ok().body(workEffortFixedAssetStds);

	}

	/**
	 * creates a new WorkEffortFixedAssetStd entry in the ofbiz database
	 * 
	 * @param workEffortFixedAssetStdToBeAdded
	 *            the WorkEffortFixedAssetStd thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortFixedAssetStd> createWorkEffortFixedAssetStd(@RequestBody WorkEffortFixedAssetStd workEffortFixedAssetStdToBeAdded) throws Exception {

		AddWorkEffortFixedAssetStd command = new AddWorkEffortFixedAssetStd(workEffortFixedAssetStdToBeAdded);
		WorkEffortFixedAssetStd workEffortFixedAssetStd = ((WorkEffortFixedAssetStdAdded) Scheduler.execute(command).data()).getAddedWorkEffortFixedAssetStd();
		
		if (workEffortFixedAssetStd != null) 
			return successful(workEffortFixedAssetStd);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortFixedAssetStd with the specific Id
	 * 
	 * @param workEffortFixedAssetStdToBeUpdated
	 *            the WorkEffortFixedAssetStd thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortFixedAssetStd(@RequestBody WorkEffortFixedAssetStd workEffortFixedAssetStdToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortFixedAssetStdToBeUpdated.setnull(null);

		UpdateWorkEffortFixedAssetStd command = new UpdateWorkEffortFixedAssetStd(workEffortFixedAssetStdToBeUpdated);

		try {
			if(((WorkEffortFixedAssetStdUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortFixedAssetStdId}")
	public ResponseEntity<WorkEffortFixedAssetStd> findById(@PathVariable String workEffortFixedAssetStdId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortFixedAssetStdId", workEffortFixedAssetStdId);
		try {

			List<WorkEffortFixedAssetStd> foundWorkEffortFixedAssetStd = findWorkEffortFixedAssetStdsBy(requestParams).getBody();
			if(foundWorkEffortFixedAssetStd.size()==1){				return successful(foundWorkEffortFixedAssetStd.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortFixedAssetStdId}")
	public ResponseEntity<String> deleteWorkEffortFixedAssetStdByIdUpdated(@PathVariable String workEffortFixedAssetStdId) throws Exception {
		DeleteWorkEffortFixedAssetStd command = new DeleteWorkEffortFixedAssetStd(workEffortFixedAssetStdId);

		try {
			if (((WorkEffortFixedAssetStdDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
