package com.skytala.eCommerce.domain.workeffort.relations.workEffort;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.AddWorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.DeleteWorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.UpdateWorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.WorkEffortMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.WorkEffort;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.FindWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEfforts")
public class WorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffort
	 * @return a List with the WorkEfforts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortsBy query = new FindWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffort> workEfforts =((WorkEffortFound) Scheduler.execute(query).data()).getWorkEfforts();

		if (workEfforts.size() == 1) {
			return ResponseEntity.ok().body(workEfforts.get(0));
		}

		return ResponseEntity.ok().body(workEfforts);

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
	public ResponseEntity<Object> createWorkEffort(HttpServletRequest request) throws Exception {

		WorkEffort workEffortToBeAdded = new WorkEffort();
		try {
			workEffortToBeAdded = WorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffort(workEffortToBeAdded);

	}

	/**
	 * creates a new WorkEffort entry in the ofbiz database
	 * 
	 * @param workEffortToBeAdded
	 *            the WorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffort(@RequestBody WorkEffort workEffortToBeAdded) throws Exception {

		AddWorkEffort command = new AddWorkEffort(workEffortToBeAdded);
		WorkEffort workEffort = ((WorkEffortAdded) Scheduler.execute(command).data()).getAddedWorkEffort();
		
		if (workEffort != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffort);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffort could not be created.");
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
	public boolean updateWorkEffort(HttpServletRequest request) throws Exception {

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

		WorkEffort workEffortToBeUpdated = new WorkEffort();

		try {
			workEffortToBeUpdated = WorkEffortMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffort(workEffortToBeUpdated, workEffortToBeUpdated.getWorkEffortId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffort with the specific Id
	 * 
	 * @param workEffortToBeUpdated
	 *            the WorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffort(@RequestBody WorkEffort workEffortToBeUpdated,
			@PathVariable String workEffortId) throws Exception {

		workEffortToBeUpdated.setWorkEffortId(workEffortId);

		UpdateWorkEffort command = new UpdateWorkEffort(workEffortToBeUpdated);

		try {
			if(((WorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortId", workEffortId);
		try {

			Object foundWorkEffort = findWorkEffortsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffort);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortId}")
	public ResponseEntity<Object> deleteWorkEffortByIdUpdated(@PathVariable String workEffortId) throws Exception {
		DeleteWorkEffort command = new DeleteWorkEffort(workEffortId);

		try {
			if (((WorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffort could not be deleted");

	}

}
