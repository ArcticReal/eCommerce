package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.assocAttribute;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocAttribute.AddWorkEffortAssocAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocAttribute.DeleteWorkEffortAssocAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocAttribute.UpdateWorkEffortAssocAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocAttribute.WorkEffortAssocAttributeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocAttribute.WorkEffortAssocAttributeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocAttribute.WorkEffortAssocAttributeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocAttribute.WorkEffortAssocAttributeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocAttribute.WorkEffortAssocAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocAttribute.WorkEffortAssocAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assocAttribute.FindWorkEffortAssocAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAssocAttributes")
public class WorkEffortAssocAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAssocAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAssocAttribute
	 * @return a List with the WorkEffortAssocAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortAssocAttribute>> findWorkEffortAssocAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocAttributesBy query = new FindWorkEffortAssocAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssocAttribute> workEffortAssocAttributes =((WorkEffortAssocAttributeFound) Scheduler.execute(query).data()).getWorkEffortAssocAttributes();

		return ResponseEntity.ok().body(workEffortAssocAttributes);

	}

	/**
	 * creates a new WorkEffortAssocAttribute entry in the ofbiz database
	 * 
	 * @param workEffortAssocAttributeToBeAdded
	 *            the WorkEffortAssocAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortAssocAttribute> createWorkEffortAssocAttribute(@RequestBody WorkEffortAssocAttribute workEffortAssocAttributeToBeAdded) throws Exception {

		AddWorkEffortAssocAttribute command = new AddWorkEffortAssocAttribute(workEffortAssocAttributeToBeAdded);
		WorkEffortAssocAttribute workEffortAssocAttribute = ((WorkEffortAssocAttributeAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssocAttribute();
		
		if (workEffortAssocAttribute != null) 
			return successful(workEffortAssocAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortAssocAttribute with the specific Id
	 * 
	 * @param workEffortAssocAttributeToBeUpdated
	 *            the WorkEffortAssocAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortAssocAttribute(@RequestBody WorkEffortAssocAttribute workEffortAssocAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortAssocAttributeToBeUpdated.setnull(null);

		UpdateWorkEffortAssocAttribute command = new UpdateWorkEffortAssocAttribute(workEffortAssocAttributeToBeUpdated);

		try {
			if(((WorkEffortAssocAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortAssocAttributeId}")
	public ResponseEntity<WorkEffortAssocAttribute> findById(@PathVariable String workEffortAssocAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocAttributeId", workEffortAssocAttributeId);
		try {

			List<WorkEffortAssocAttribute> foundWorkEffortAssocAttribute = findWorkEffortAssocAttributesBy(requestParams).getBody();
			if(foundWorkEffortAssocAttribute.size()==1){				return successful(foundWorkEffortAssocAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortAssocAttributeId}")
	public ResponseEntity<String> deleteWorkEffortAssocAttributeByIdUpdated(@PathVariable String workEffortAssocAttributeId) throws Exception {
		DeleteWorkEffortAssocAttribute command = new DeleteWorkEffortAssocAttribute(workEffortAssocAttributeId);

		try {
			if (((WorkEffortAssocAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
