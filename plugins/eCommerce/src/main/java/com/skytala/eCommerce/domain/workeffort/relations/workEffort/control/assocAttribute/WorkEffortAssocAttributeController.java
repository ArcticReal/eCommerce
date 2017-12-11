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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortAssocAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocAttributesBy query = new FindWorkEffortAssocAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssocAttribute> workEffortAssocAttributes =((WorkEffortAssocAttributeFound) Scheduler.execute(query).data()).getWorkEffortAssocAttributes();

		if (workEffortAssocAttributes.size() == 1) {
			return ResponseEntity.ok().body(workEffortAssocAttributes.get(0));
		}

		return ResponseEntity.ok().body(workEffortAssocAttributes);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createWorkEffortAssocAttribute(HttpServletRequest request) throws Exception {

		WorkEffortAssocAttribute workEffortAssocAttributeToBeAdded = new WorkEffortAssocAttribute();
		try {
			workEffortAssocAttributeToBeAdded = WorkEffortAssocAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortAssocAttribute(workEffortAssocAttributeToBeAdded);

	}

	/**
	 * creates a new WorkEffortAssocAttribute entry in the ofbiz database
	 * 
	 * @param workEffortAssocAttributeToBeAdded
	 *            the WorkEffortAssocAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortAssocAttribute(@RequestBody WorkEffortAssocAttribute workEffortAssocAttributeToBeAdded) throws Exception {

		AddWorkEffortAssocAttribute command = new AddWorkEffortAssocAttribute(workEffortAssocAttributeToBeAdded);
		WorkEffortAssocAttribute workEffortAssocAttribute = ((WorkEffortAssocAttributeAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssocAttribute();
		
		if (workEffortAssocAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortAssocAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortAssocAttribute could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateWorkEffortAssocAttribute(HttpServletRequest request) throws Exception {

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

		WorkEffortAssocAttribute workEffortAssocAttributeToBeUpdated = new WorkEffortAssocAttribute();

		try {
			workEffortAssocAttributeToBeUpdated = WorkEffortAssocAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortAssocAttribute(workEffortAssocAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkEffortAssocAttribute(@RequestBody WorkEffortAssocAttribute workEffortAssocAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortAssocAttributeToBeUpdated.setnull(null);

		UpdateWorkEffortAssocAttribute command = new UpdateWorkEffortAssocAttribute(workEffortAssocAttributeToBeUpdated);

		try {
			if(((WorkEffortAssocAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortAssocAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortAssocAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocAttributeId", workEffortAssocAttributeId);
		try {

			Object foundWorkEffortAssocAttribute = findWorkEffortAssocAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortAssocAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortAssocAttributeId}")
	public ResponseEntity<Object> deleteWorkEffortAssocAttributeByIdUpdated(@PathVariable String workEffortAssocAttributeId) throws Exception {
		DeleteWorkEffortAssocAttribute command = new DeleteWorkEffortAssocAttribute(workEffortAssocAttributeId);

		try {
			if (((WorkEffortAssocAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortAssocAttribute could not be deleted");

	}

}
