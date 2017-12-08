package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.transBox;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.transBox.AddWorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.transBox.DeleteWorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.transBox.UpdateWorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.transBox.WorkEffortTransBoxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.transBox.FindWorkEffortTransBoxsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortTransBoxs")
public class WorkEffortTransBoxController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortTransBoxController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortTransBox
	 * @return a List with the WorkEffortTransBoxs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortTransBoxsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortTransBoxsBy query = new FindWorkEffortTransBoxsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortTransBox> workEffortTransBoxs =((WorkEffortTransBoxFound) Scheduler.execute(query).data()).getWorkEffortTransBoxs();

		if (workEffortTransBoxs.size() == 1) {
			return ResponseEntity.ok().body(workEffortTransBoxs.get(0));
		}

		return ResponseEntity.ok().body(workEffortTransBoxs);

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
	public ResponseEntity<Object> createWorkEffortTransBox(HttpServletRequest request) throws Exception {

		WorkEffortTransBox workEffortTransBoxToBeAdded = new WorkEffortTransBox();
		try {
			workEffortTransBoxToBeAdded = WorkEffortTransBoxMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortTransBox(workEffortTransBoxToBeAdded);

	}

	/**
	 * creates a new WorkEffortTransBox entry in the ofbiz database
	 * 
	 * @param workEffortTransBoxToBeAdded
	 *            the WorkEffortTransBox thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortTransBox(@RequestBody WorkEffortTransBox workEffortTransBoxToBeAdded) throws Exception {

		AddWorkEffortTransBox command = new AddWorkEffortTransBox(workEffortTransBoxToBeAdded);
		WorkEffortTransBox workEffortTransBox = ((WorkEffortTransBoxAdded) Scheduler.execute(command).data()).getAddedWorkEffortTransBox();
		
		if (workEffortTransBox != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortTransBox);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortTransBox could not be created.");
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
	public boolean updateWorkEffortTransBox(HttpServletRequest request) throws Exception {

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

		WorkEffortTransBox workEffortTransBoxToBeUpdated = new WorkEffortTransBox();

		try {
			workEffortTransBoxToBeUpdated = WorkEffortTransBoxMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortTransBox(workEffortTransBoxToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortTransBox with the specific Id
	 * 
	 * @param workEffortTransBoxToBeUpdated
	 *            the WorkEffortTransBox thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortTransBox(@RequestBody WorkEffortTransBox workEffortTransBoxToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortTransBoxToBeUpdated.setnull(null);

		UpdateWorkEffortTransBox command = new UpdateWorkEffortTransBox(workEffortTransBoxToBeUpdated);

		try {
			if(((WorkEffortTransBoxUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortTransBoxId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortTransBoxId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortTransBoxId", workEffortTransBoxId);
		try {

			Object foundWorkEffortTransBox = findWorkEffortTransBoxsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortTransBox);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortTransBoxId}")
	public ResponseEntity<Object> deleteWorkEffortTransBoxByIdUpdated(@PathVariable String workEffortTransBoxId) throws Exception {
		DeleteWorkEffortTransBox command = new DeleteWorkEffortTransBox(workEffortTransBoxId);

		try {
			if (((WorkEffortTransBoxDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortTransBox could not be deleted");

	}

}
