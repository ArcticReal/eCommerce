package com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.command.AddWorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.command.DeleteWorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.command.UpdateWorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event.WorkEffortPartyAssignmentAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event.WorkEffortPartyAssignmentDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event.WorkEffortPartyAssignmentFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event.WorkEffortPartyAssignmentUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.mapper.WorkEffortPartyAssignmentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.model.WorkEffortPartyAssignment;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.query.FindWorkEffortPartyAssignmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortPartyAssignments")
public class WorkEffortPartyAssignmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortPartyAssignmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortPartyAssignment
	 * @return a List with the WorkEffortPartyAssignments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortPartyAssignmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortPartyAssignmentsBy query = new FindWorkEffortPartyAssignmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortPartyAssignment> workEffortPartyAssignments =((WorkEffortPartyAssignmentFound) Scheduler.execute(query).data()).getWorkEffortPartyAssignments();

		if (workEffortPartyAssignments.size() == 1) {
			return ResponseEntity.ok().body(workEffortPartyAssignments.get(0));
		}

		return ResponseEntity.ok().body(workEffortPartyAssignments);

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
	public ResponseEntity<Object> createWorkEffortPartyAssignment(HttpServletRequest request) throws Exception {

		WorkEffortPartyAssignment workEffortPartyAssignmentToBeAdded = new WorkEffortPartyAssignment();
		try {
			workEffortPartyAssignmentToBeAdded = WorkEffortPartyAssignmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortPartyAssignment(workEffortPartyAssignmentToBeAdded);

	}

	/**
	 * creates a new WorkEffortPartyAssignment entry in the ofbiz database
	 * 
	 * @param workEffortPartyAssignmentToBeAdded
	 *            the WorkEffortPartyAssignment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortPartyAssignment(@RequestBody WorkEffortPartyAssignment workEffortPartyAssignmentToBeAdded) throws Exception {

		AddWorkEffortPartyAssignment command = new AddWorkEffortPartyAssignment(workEffortPartyAssignmentToBeAdded);
		WorkEffortPartyAssignment workEffortPartyAssignment = ((WorkEffortPartyAssignmentAdded) Scheduler.execute(command).data()).getAddedWorkEffortPartyAssignment();
		
		if (workEffortPartyAssignment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortPartyAssignment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortPartyAssignment could not be created.");
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
	public boolean updateWorkEffortPartyAssignment(HttpServletRequest request) throws Exception {

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

		WorkEffortPartyAssignment workEffortPartyAssignmentToBeUpdated = new WorkEffortPartyAssignment();

		try {
			workEffortPartyAssignmentToBeUpdated = WorkEffortPartyAssignmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortPartyAssignment(workEffortPartyAssignmentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortPartyAssignment with the specific Id
	 * 
	 * @param workEffortPartyAssignmentToBeUpdated
	 *            the WorkEffortPartyAssignment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortPartyAssignment(@RequestBody WorkEffortPartyAssignment workEffortPartyAssignmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortPartyAssignmentToBeUpdated.setnull(null);

		UpdateWorkEffortPartyAssignment command = new UpdateWorkEffortPartyAssignment(workEffortPartyAssignmentToBeUpdated);

		try {
			if(((WorkEffortPartyAssignmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortPartyAssignmentId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortPartyAssignmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortPartyAssignmentId", workEffortPartyAssignmentId);
		try {

			Object foundWorkEffortPartyAssignment = findWorkEffortPartyAssignmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortPartyAssignment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortPartyAssignmentId}")
	public ResponseEntity<Object> deleteWorkEffortPartyAssignmentByIdUpdated(@PathVariable String workEffortPartyAssignmentId) throws Exception {
		DeleteWorkEffortPartyAssignment command = new DeleteWorkEffortPartyAssignment(workEffortPartyAssignmentId);

		try {
			if (((WorkEffortPartyAssignmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortPartyAssignment could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortPartyAssignment/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
