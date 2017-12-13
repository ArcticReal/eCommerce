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
	public ResponseEntity<Object> findWorkEffortKeywordsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortKeywordsBy query = new FindWorkEffortKeywordsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortKeyword> workEffortKeywords =((WorkEffortKeywordFound) Scheduler.execute(query).data()).getWorkEffortKeywords();

		if (workEffortKeywords.size() == 1) {
			return ResponseEntity.ok().body(workEffortKeywords.get(0));
		}

		return ResponseEntity.ok().body(workEffortKeywords);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createWorkEffortKeyword(HttpServletRequest request) throws Exception {

		WorkEffortKeyword workEffortKeywordToBeAdded = new WorkEffortKeyword();
		try {
			workEffortKeywordToBeAdded = WorkEffortKeywordMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortKeyword(workEffortKeywordToBeAdded);

	}

	/**
	 * creates a new WorkEffortKeyword entry in the ofbiz database
	 * 
	 * @param workEffortKeywordToBeAdded
	 *            the WorkEffortKeyword thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortKeyword(@RequestBody WorkEffortKeyword workEffortKeywordToBeAdded) throws Exception {

		AddWorkEffortKeyword command = new AddWorkEffortKeyword(workEffortKeywordToBeAdded);
		WorkEffortKeyword workEffortKeyword = ((WorkEffortKeywordAdded) Scheduler.execute(command).data()).getAddedWorkEffortKeyword();
		
		if (workEffortKeyword != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortKeyword);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortKeyword could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateWorkEffortKeyword(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		WorkEffortKeyword workEffortKeywordToBeUpdated = new WorkEffortKeyword();

		try {
			workEffortKeywordToBeUpdated = WorkEffortKeywordMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortKeyword(workEffortKeywordToBeUpdated, workEffortKeywordToBeUpdated.getKeyword()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkEffortKeyword(@RequestBody WorkEffortKeyword workEffortKeywordToBeUpdated,
			@PathVariable String keyword) throws Exception {

		workEffortKeywordToBeUpdated.setKeyword(keyword);

		UpdateWorkEffortKeyword command = new UpdateWorkEffortKeyword(workEffortKeywordToBeUpdated);

		try {
			if(((WorkEffortKeywordUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortKeywordId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortKeywordId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortKeywordId", workEffortKeywordId);
		try {

			Object foundWorkEffortKeyword = findWorkEffortKeywordsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortKeyword);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortKeywordId}")
	public ResponseEntity<Object> deleteWorkEffortKeywordByIdUpdated(@PathVariable String workEffortKeywordId) throws Exception {
		DeleteWorkEffortKeyword command = new DeleteWorkEffortKeyword(workEffortKeywordId);

		try {
			if (((WorkEffortKeywordDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortKeyword could not be deleted");

	}

}
