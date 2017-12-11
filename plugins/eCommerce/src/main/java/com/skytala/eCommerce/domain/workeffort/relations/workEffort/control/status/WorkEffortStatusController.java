package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.status;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status.AddWorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status.DeleteWorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status.UpdateWorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.status.WorkEffortStatusMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.status.FindWorkEffortStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortStatuss")
public class WorkEffortStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortStatus
	 * @return a List with the WorkEffortStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortStatussBy query = new FindWorkEffortStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortStatus> workEffortStatuss =((WorkEffortStatusFound) Scheduler.execute(query).data()).getWorkEffortStatuss();

		if (workEffortStatuss.size() == 1) {
			return ResponseEntity.ok().body(workEffortStatuss.get(0));
		}

		return ResponseEntity.ok().body(workEffortStatuss);

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
	public ResponseEntity<Object> createWorkEffortStatus(HttpServletRequest request) throws Exception {

		WorkEffortStatus workEffortStatusToBeAdded = new WorkEffortStatus();
		try {
			workEffortStatusToBeAdded = WorkEffortStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortStatus(workEffortStatusToBeAdded);

	}

	/**
	 * creates a new WorkEffortStatus entry in the ofbiz database
	 * 
	 * @param workEffortStatusToBeAdded
	 *            the WorkEffortStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortStatus(@RequestBody WorkEffortStatus workEffortStatusToBeAdded) throws Exception {

		AddWorkEffortStatus command = new AddWorkEffortStatus(workEffortStatusToBeAdded);
		WorkEffortStatus workEffortStatus = ((WorkEffortStatusAdded) Scheduler.execute(command).data()).getAddedWorkEffortStatus();
		
		if (workEffortStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortStatus could not be created.");
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
	public boolean updateWorkEffortStatus(HttpServletRequest request) throws Exception {

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

		WorkEffortStatus workEffortStatusToBeUpdated = new WorkEffortStatus();

		try {
			workEffortStatusToBeUpdated = WorkEffortStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortStatus(workEffortStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortStatus with the specific Id
	 * 
	 * @param workEffortStatusToBeUpdated
	 *            the WorkEffortStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortStatus(@RequestBody WorkEffortStatus workEffortStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortStatusToBeUpdated.setnull(null);

		UpdateWorkEffortStatus command = new UpdateWorkEffortStatus(workEffortStatusToBeUpdated);

		try {
			if(((WorkEffortStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortStatusId", workEffortStatusId);
		try {

			Object foundWorkEffortStatus = findWorkEffortStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortStatusId}")
	public ResponseEntity<Object> deleteWorkEffortStatusByIdUpdated(@PathVariable String workEffortStatusId) throws Exception {
		DeleteWorkEffortStatus command = new DeleteWorkEffortStatus(workEffortStatusId);

		try {
			if (((WorkEffortStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortStatus could not be deleted");

	}

}
