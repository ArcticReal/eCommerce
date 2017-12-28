package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.searchResult;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchResult.AddWorkEffortSearchResult;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchResult.DeleteWorkEffortSearchResult;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchResult.UpdateWorkEffortSearchResult;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult.WorkEffortSearchResultAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult.WorkEffortSearchResultDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult.WorkEffortSearchResultFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult.WorkEffortSearchResultUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.searchResult.WorkEffortSearchResultMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchResult.WorkEffortSearchResult;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.searchResult.FindWorkEffortSearchResultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortSearchResults")
public class WorkEffortSearchResultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortSearchResultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortSearchResult
	 * @return a List with the WorkEffortSearchResults
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortSearchResult>> findWorkEffortSearchResultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSearchResultsBy query = new FindWorkEffortSearchResultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSearchResult> workEffortSearchResults =((WorkEffortSearchResultFound) Scheduler.execute(query).data()).getWorkEffortSearchResults();

		return ResponseEntity.ok().body(workEffortSearchResults);

	}

	/**
	 * creates a new WorkEffortSearchResult entry in the ofbiz database
	 * 
	 * @param workEffortSearchResultToBeAdded
	 *            the WorkEffortSearchResult thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortSearchResult> createWorkEffortSearchResult(@RequestBody WorkEffortSearchResult workEffortSearchResultToBeAdded) throws Exception {

		AddWorkEffortSearchResult command = new AddWorkEffortSearchResult(workEffortSearchResultToBeAdded);
		WorkEffortSearchResult workEffortSearchResult = ((WorkEffortSearchResultAdded) Scheduler.execute(command).data()).getAddedWorkEffortSearchResult();
		
		if (workEffortSearchResult != null) 
			return successful(workEffortSearchResult);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortSearchResult with the specific Id
	 * 
	 * @param workEffortSearchResultToBeUpdated
	 *            the WorkEffortSearchResult thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortSearchResultId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortSearchResult(@RequestBody WorkEffortSearchResult workEffortSearchResultToBeUpdated,
			@PathVariable String workEffortSearchResultId) throws Exception {

		workEffortSearchResultToBeUpdated.setWorkEffortSearchResultId(workEffortSearchResultId);

		UpdateWorkEffortSearchResult command = new UpdateWorkEffortSearchResult(workEffortSearchResultToBeUpdated);

		try {
			if(((WorkEffortSearchResultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortSearchResultId}")
	public ResponseEntity<WorkEffortSearchResult> findById(@PathVariable String workEffortSearchResultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSearchResultId", workEffortSearchResultId);
		try {

			List<WorkEffortSearchResult> foundWorkEffortSearchResult = findWorkEffortSearchResultsBy(requestParams).getBody();
			if(foundWorkEffortSearchResult.size()==1){				return successful(foundWorkEffortSearchResult.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortSearchResultId}")
	public ResponseEntity<String> deleteWorkEffortSearchResultByIdUpdated(@PathVariable String workEffortSearchResultId) throws Exception {
		DeleteWorkEffortSearchResult command = new DeleteWorkEffortSearchResult(workEffortSearchResultId);

		try {
			if (((WorkEffortSearchResultDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
