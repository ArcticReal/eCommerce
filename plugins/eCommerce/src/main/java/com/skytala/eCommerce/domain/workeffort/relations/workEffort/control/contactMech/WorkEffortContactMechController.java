package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.contactMech;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contactMech.AddWorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contactMech.DeleteWorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.contactMech.UpdateWorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contactMech.WorkEffortContactMechMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.contactMech.FindWorkEffortContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortContactMechs")
public class WorkEffortContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortContactMech
	 * @return a List with the WorkEffortContactMechs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortContactMechsBy query = new FindWorkEffortContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortContactMech> workEffortContactMechs =((WorkEffortContactMechFound) Scheduler.execute(query).data()).getWorkEffortContactMechs();

		if (workEffortContactMechs.size() == 1) {
			return ResponseEntity.ok().body(workEffortContactMechs.get(0));
		}

		return ResponseEntity.ok().body(workEffortContactMechs);

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
	public ResponseEntity<Object> createWorkEffortContactMech(HttpServletRequest request) throws Exception {

		WorkEffortContactMech workEffortContactMechToBeAdded = new WorkEffortContactMech();
		try {
			workEffortContactMechToBeAdded = WorkEffortContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortContactMech(workEffortContactMechToBeAdded);

	}

	/**
	 * creates a new WorkEffortContactMech entry in the ofbiz database
	 * 
	 * @param workEffortContactMechToBeAdded
	 *            the WorkEffortContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortContactMech(@RequestBody WorkEffortContactMech workEffortContactMechToBeAdded) throws Exception {

		AddWorkEffortContactMech command = new AddWorkEffortContactMech(workEffortContactMechToBeAdded);
		WorkEffortContactMech workEffortContactMech = ((WorkEffortContactMechAdded) Scheduler.execute(command).data()).getAddedWorkEffortContactMech();
		
		if (workEffortContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortContactMech could not be created.");
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
	public boolean updateWorkEffortContactMech(HttpServletRequest request) throws Exception {

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

		WorkEffortContactMech workEffortContactMechToBeUpdated = new WorkEffortContactMech();

		try {
			workEffortContactMechToBeUpdated = WorkEffortContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortContactMech(workEffortContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortContactMech with the specific Id
	 * 
	 * @param workEffortContactMechToBeUpdated
	 *            the WorkEffortContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortContactMech(@RequestBody WorkEffortContactMech workEffortContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortContactMechToBeUpdated.setnull(null);

		UpdateWorkEffortContactMech command = new UpdateWorkEffortContactMech(workEffortContactMechToBeUpdated);

		try {
			if(((WorkEffortContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortContactMechId", workEffortContactMechId);
		try {

			Object foundWorkEffortContactMech = findWorkEffortContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortContactMechId}")
	public ResponseEntity<Object> deleteWorkEffortContactMechByIdUpdated(@PathVariable String workEffortContactMechId) throws Exception {
		DeleteWorkEffortContactMech command = new DeleteWorkEffortContactMech(workEffortContactMechId);

		try {
			if (((WorkEffortContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortContactMech could not be deleted");

	}

}
