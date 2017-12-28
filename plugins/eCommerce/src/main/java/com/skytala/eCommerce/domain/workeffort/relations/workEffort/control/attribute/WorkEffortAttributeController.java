package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.attribute;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.attribute.AddWorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.attribute.DeleteWorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.attribute.UpdateWorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute.WorkEffortAttributeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute.WorkEffortAttributeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute.WorkEffortAttributeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute.WorkEffortAttributeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.attribute.WorkEffortAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.attribute.WorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.attribute.FindWorkEffortAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAttributes")
public class WorkEffortAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAttribute
	 * @return a List with the WorkEffortAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortAttribute>> findWorkEffortAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAttributesBy query = new FindWorkEffortAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAttribute> workEffortAttributes =((WorkEffortAttributeFound) Scheduler.execute(query).data()).getWorkEffortAttributes();

		return ResponseEntity.ok().body(workEffortAttributes);

	}

	/**
	 * creates a new WorkEffortAttribute entry in the ofbiz database
	 * 
	 * @param workEffortAttributeToBeAdded
	 *            the WorkEffortAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortAttribute> createWorkEffortAttribute(@RequestBody WorkEffortAttribute workEffortAttributeToBeAdded) throws Exception {

		AddWorkEffortAttribute command = new AddWorkEffortAttribute(workEffortAttributeToBeAdded);
		WorkEffortAttribute workEffortAttribute = ((WorkEffortAttributeAdded) Scheduler.execute(command).data()).getAddedWorkEffortAttribute();
		
		if (workEffortAttribute != null) 
			return successful(workEffortAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortAttribute with the specific Id
	 * 
	 * @param workEffortAttributeToBeUpdated
	 *            the WorkEffortAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortAttribute(@RequestBody WorkEffortAttribute workEffortAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		workEffortAttributeToBeUpdated.setAttrName(attrName);

		UpdateWorkEffortAttribute command = new UpdateWorkEffortAttribute(workEffortAttributeToBeUpdated);

		try {
			if(((WorkEffortAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortAttributeId}")
	public ResponseEntity<WorkEffortAttribute> findById(@PathVariable String workEffortAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAttributeId", workEffortAttributeId);
		try {

			List<WorkEffortAttribute> foundWorkEffortAttribute = findWorkEffortAttributesBy(requestParams).getBody();
			if(foundWorkEffortAttribute.size()==1){				return successful(foundWorkEffortAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortAttributeId}")
	public ResponseEntity<String> deleteWorkEffortAttributeByIdUpdated(@PathVariable String workEffortAttributeId) throws Exception {
		DeleteWorkEffortAttribute command = new DeleteWorkEffortAttribute(workEffortAttributeId);

		try {
			if (((WorkEffortAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
