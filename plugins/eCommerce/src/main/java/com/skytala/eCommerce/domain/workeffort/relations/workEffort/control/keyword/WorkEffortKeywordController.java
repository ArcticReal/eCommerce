package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.keyword;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.keyword.AddWorkEffortKeyword;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.keyword.DeleteWorkEffortKeyword;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.keyword.UpdateWorkEffortKeyword;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword.WorkEffortKeywordAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword.WorkEffortKeywordDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword.WorkEffortKeywordFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword.WorkEffortKeywordUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.keyword.WorkEffortKeywordMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword.WorkEffortKeyword;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.keyword.FindWorkEffortKeywordsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortKeywords")
public class WorkEffortKeywordController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortKeywordController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortKeyword
	 * @return a List with the WorkEffortKeywords
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortKeyword>> findWorkEffortKeywordsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortKeywordsBy query = new FindWorkEffortKeywordsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortKeyword> workEffortKeywords =((WorkEffortKeywordFound) Scheduler.execute(query).data()).getWorkEffortKeywords();

		return ResponseEntity.ok().body(workEffortKeywords);

	}

	/**
	 * creates a new WorkEffortKeyword entry in the ofbiz database
	 * 
	 * @param workEffortKeywordToBeAdded
	 *            the WorkEffortKeyword thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortKeyword> createWorkEffortKeyword(@RequestBody WorkEffortKeyword workEffortKeywordToBeAdded) throws Exception {

		AddWorkEffortKeyword command = new AddWorkEffortKeyword(workEffortKeywordToBeAdded);
		WorkEffortKeyword workEffortKeyword = ((WorkEffortKeywordAdded) Scheduler.execute(command).data()).getAddedWorkEffortKeyword();
		
		if (workEffortKeyword != null) 
			return successful(workEffortKeyword);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortKeyword with the specific Id
	 * 
	 * @param workEffortKeywordToBeUpdated
	 *            the WorkEffortKeyword thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{keyword}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortKeyword(@RequestBody WorkEffortKeyword workEffortKeywordToBeUpdated,
			@PathVariable String keyword) throws Exception {

		workEffortKeywordToBeUpdated.setKeyword(keyword);

		UpdateWorkEffortKeyword command = new UpdateWorkEffortKeyword(workEffortKeywordToBeUpdated);

		try {
			if(((WorkEffortKeywordUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortKeywordId}")
	public ResponseEntity<WorkEffortKeyword> findById(@PathVariable String workEffortKeywordId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortKeywordId", workEffortKeywordId);
		try {

			List<WorkEffortKeyword> foundWorkEffortKeyword = findWorkEffortKeywordsBy(requestParams).getBody();
			if(foundWorkEffortKeyword.size()==1){				return successful(foundWorkEffortKeyword.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortKeywordId}")
	public ResponseEntity<String> deleteWorkEffortKeywordByIdUpdated(@PathVariable String workEffortKeywordId) throws Exception {
		DeleteWorkEffortKeyword command = new DeleteWorkEffortKeyword(workEffortKeywordId);

		try {
			if (((WorkEffortKeywordDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
