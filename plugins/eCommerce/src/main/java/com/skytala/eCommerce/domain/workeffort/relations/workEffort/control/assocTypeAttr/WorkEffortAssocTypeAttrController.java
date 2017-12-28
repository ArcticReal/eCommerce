package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.assocTypeAttr;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr.AddWorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr.DeleteWorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr.UpdateWorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocTypeAttr.WorkEffortAssocTypeAttrMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocTypeAttr.WorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assocTypeAttr.FindWorkEffortAssocTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAssocTypeAttrs")
public class WorkEffortAssocTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAssocTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAssocTypeAttr
	 * @return a List with the WorkEffortAssocTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortAssocTypeAttr>> findWorkEffortAssocTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocTypeAttrsBy query = new FindWorkEffortAssocTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssocTypeAttr> workEffortAssocTypeAttrs =((WorkEffortAssocTypeAttrFound) Scheduler.execute(query).data()).getWorkEffortAssocTypeAttrs();

		return ResponseEntity.ok().body(workEffortAssocTypeAttrs);

	}

	/**
	 * creates a new WorkEffortAssocTypeAttr entry in the ofbiz database
	 * 
	 * @param workEffortAssocTypeAttrToBeAdded
	 *            the WorkEffortAssocTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortAssocTypeAttr> createWorkEffortAssocTypeAttr(@RequestBody WorkEffortAssocTypeAttr workEffortAssocTypeAttrToBeAdded) throws Exception {

		AddWorkEffortAssocTypeAttr command = new AddWorkEffortAssocTypeAttr(workEffortAssocTypeAttrToBeAdded);
		WorkEffortAssocTypeAttr workEffortAssocTypeAttr = ((WorkEffortAssocTypeAttrAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssocTypeAttr();
		
		if (workEffortAssocTypeAttr != null) 
			return successful(workEffortAssocTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortAssocTypeAttr with the specific Id
	 * 
	 * @param workEffortAssocTypeAttrToBeUpdated
	 *            the WorkEffortAssocTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortAssocTypeAttr(@RequestBody WorkEffortAssocTypeAttr workEffortAssocTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		workEffortAssocTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateWorkEffortAssocTypeAttr command = new UpdateWorkEffortAssocTypeAttr(workEffortAssocTypeAttrToBeUpdated);

		try {
			if(((WorkEffortAssocTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortAssocTypeAttrId}")
	public ResponseEntity<WorkEffortAssocTypeAttr> findById(@PathVariable String workEffortAssocTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocTypeAttrId", workEffortAssocTypeAttrId);
		try {

			List<WorkEffortAssocTypeAttr> foundWorkEffortAssocTypeAttr = findWorkEffortAssocTypeAttrsBy(requestParams).getBody();
			if(foundWorkEffortAssocTypeAttr.size()==1){				return successful(foundWorkEffortAssocTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortAssocTypeAttrId}")
	public ResponseEntity<String> deleteWorkEffortAssocTypeAttrByIdUpdated(@PathVariable String workEffortAssocTypeAttrId) throws Exception {
		DeleteWorkEffortAssocTypeAttr command = new DeleteWorkEffortAssocTypeAttr(workEffortAssocTypeAttrId);

		try {
			if (((WorkEffortAssocTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
