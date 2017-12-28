package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.content;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.content.AddWorkEffortContent;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.content.DeleteWorkEffortContent;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.content.UpdateWorkEffortContent;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.content.WorkEffortContentAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.content.WorkEffortContentDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.content.WorkEffortContentFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.content.WorkEffortContentUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.content.WorkEffortContentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.content.WorkEffortContent;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.content.FindWorkEffortContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortContents")
public class WorkEffortContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortContent
	 * @return a List with the WorkEffortContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortContent>> findWorkEffortContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortContentsBy query = new FindWorkEffortContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortContent> workEffortContents =((WorkEffortContentFound) Scheduler.execute(query).data()).getWorkEffortContents();

		return ResponseEntity.ok().body(workEffortContents);

	}

	/**
	 * creates a new WorkEffortContent entry in the ofbiz database
	 * 
	 * @param workEffortContentToBeAdded
	 *            the WorkEffortContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortContent> createWorkEffortContent(@RequestBody WorkEffortContent workEffortContentToBeAdded) throws Exception {

		AddWorkEffortContent command = new AddWorkEffortContent(workEffortContentToBeAdded);
		WorkEffortContent workEffortContent = ((WorkEffortContentAdded) Scheduler.execute(command).data()).getAddedWorkEffortContent();
		
		if (workEffortContent != null) 
			return successful(workEffortContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortContent with the specific Id
	 * 
	 * @param workEffortContentToBeUpdated
	 *            the WorkEffortContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortContent(@RequestBody WorkEffortContent workEffortContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortContentToBeUpdated.setnull(null);

		UpdateWorkEffortContent command = new UpdateWorkEffortContent(workEffortContentToBeUpdated);

		try {
			if(((WorkEffortContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortContentId}")
	public ResponseEntity<WorkEffortContent> findById(@PathVariable String workEffortContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortContentId", workEffortContentId);
		try {

			List<WorkEffortContent> foundWorkEffortContent = findWorkEffortContentsBy(requestParams).getBody();
			if(foundWorkEffortContent.size()==1){				return successful(foundWorkEffortContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortContentId}")
	public ResponseEntity<String> deleteWorkEffortContentByIdUpdated(@PathVariable String workEffortContentId) throws Exception {
		DeleteWorkEffortContent command = new DeleteWorkEffortContent(workEffortContentId);

		try {
			if (((WorkEffortContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
