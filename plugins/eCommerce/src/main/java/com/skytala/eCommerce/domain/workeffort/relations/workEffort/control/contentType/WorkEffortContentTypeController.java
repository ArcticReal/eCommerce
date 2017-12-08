package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.contentType;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contentType.AddWorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contentType.DeleteWorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contentType.UpdateWorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType.WorkEffortContentTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contentType.WorkEffortContentTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contentType.WorkEffortContentType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.contentType.FindWorkEffortContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortContentTypes")
public class WorkEffortContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortContentType
	 * @return a List with the WorkEffortContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortContentTypesBy query = new FindWorkEffortContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortContentType> workEffortContentTypes =((WorkEffortContentTypeFound) Scheduler.execute(query).data()).getWorkEffortContentTypes();

		if (workEffortContentTypes.size() == 1) {
			return ResponseEntity.ok().body(workEffortContentTypes.get(0));
		}

		return ResponseEntity.ok().body(workEffortContentTypes);

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
	public ResponseEntity<Object> createWorkEffortContentType(HttpServletRequest request) throws Exception {

		WorkEffortContentType workEffortContentTypeToBeAdded = new WorkEffortContentType();
		try {
			workEffortContentTypeToBeAdded = WorkEffortContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortContentType(workEffortContentTypeToBeAdded);

	}

	/**
	 * creates a new WorkEffortContentType entry in the ofbiz database
	 * 
	 * @param workEffortContentTypeToBeAdded
	 *            the WorkEffortContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortContentType(@RequestBody WorkEffortContentType workEffortContentTypeToBeAdded) throws Exception {

		AddWorkEffortContentType command = new AddWorkEffortContentType(workEffortContentTypeToBeAdded);
		WorkEffortContentType workEffortContentType = ((WorkEffortContentTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortContentType();
		
		if (workEffortContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortContentType could not be created.");
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
	public boolean updateWorkEffortContentType(HttpServletRequest request) throws Exception {

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

		WorkEffortContentType workEffortContentTypeToBeUpdated = new WorkEffortContentType();

		try {
			workEffortContentTypeToBeUpdated = WorkEffortContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortContentType(workEffortContentTypeToBeUpdated, workEffortContentTypeToBeUpdated.getWorkEffortContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortContentType with the specific Id
	 * 
	 * @param workEffortContentTypeToBeUpdated
	 *            the WorkEffortContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortContentType(@RequestBody WorkEffortContentType workEffortContentTypeToBeUpdated,
			@PathVariable String workEffortContentTypeId) throws Exception {

		workEffortContentTypeToBeUpdated.setWorkEffortContentTypeId(workEffortContentTypeId);

		UpdateWorkEffortContentType command = new UpdateWorkEffortContentType(workEffortContentTypeToBeUpdated);

		try {
			if(((WorkEffortContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortContentTypeId", workEffortContentTypeId);
		try {

			Object foundWorkEffortContentType = findWorkEffortContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortContentTypeId}")
	public ResponseEntity<Object> deleteWorkEffortContentTypeByIdUpdated(@PathVariable String workEffortContentTypeId) throws Exception {
		DeleteWorkEffortContentType command = new DeleteWorkEffortContentType(workEffortContentTypeId);

		try {
			if (((WorkEffortContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortContentType could not be deleted");

	}

}
