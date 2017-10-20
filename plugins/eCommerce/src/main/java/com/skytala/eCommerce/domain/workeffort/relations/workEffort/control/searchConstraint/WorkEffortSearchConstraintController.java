package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.searchConstraint;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint.AddWorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint.DeleteWorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint.UpdateWorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.searchConstraint.WorkEffortSearchConstraintMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.searchConstraint.FindWorkEffortSearchConstraintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/workEffortSearchConstraints")
public class WorkEffortSearchConstraintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortSearchConstraintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortSearchConstraint
	 * @return a List with the WorkEffortSearchConstraints
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortSearchConstraintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortSearchConstraintsBy query = new FindWorkEffortSearchConstraintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortSearchConstraint> workEffortSearchConstraints =((WorkEffortSearchConstraintFound) Scheduler.execute(query).data()).getWorkEffortSearchConstraints();

		if (workEffortSearchConstraints.size() == 1) {
			return ResponseEntity.ok().body(workEffortSearchConstraints.get(0));
		}

		return ResponseEntity.ok().body(workEffortSearchConstraints);

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
	public ResponseEntity<Object> createWorkEffortSearchConstraint(HttpServletRequest request) throws Exception {

		WorkEffortSearchConstraint workEffortSearchConstraintToBeAdded = new WorkEffortSearchConstraint();
		try {
			workEffortSearchConstraintToBeAdded = WorkEffortSearchConstraintMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortSearchConstraint(workEffortSearchConstraintToBeAdded);

	}

	/**
	 * creates a new WorkEffortSearchConstraint entry in the ofbiz database
	 * 
	 * @param workEffortSearchConstraintToBeAdded
	 *            the WorkEffortSearchConstraint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortSearchConstraint(@RequestBody WorkEffortSearchConstraint workEffortSearchConstraintToBeAdded) throws Exception {

		AddWorkEffortSearchConstraint command = new AddWorkEffortSearchConstraint(workEffortSearchConstraintToBeAdded);
		WorkEffortSearchConstraint workEffortSearchConstraint = ((WorkEffortSearchConstraintAdded) Scheduler.execute(command).data()).getAddedWorkEffortSearchConstraint();
		
		if (workEffortSearchConstraint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortSearchConstraint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortSearchConstraint could not be created.");
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
	public boolean updateWorkEffortSearchConstraint(HttpServletRequest request) throws Exception {

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

		WorkEffortSearchConstraint workEffortSearchConstraintToBeUpdated = new WorkEffortSearchConstraint();

		try {
			workEffortSearchConstraintToBeUpdated = WorkEffortSearchConstraintMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortSearchConstraint(workEffortSearchConstraintToBeUpdated, workEffortSearchConstraintToBeUpdated.getConstraintSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortSearchConstraint with the specific Id
	 * 
	 * @param workEffortSearchConstraintToBeUpdated
	 *            the WorkEffortSearchConstraint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{constraintSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortSearchConstraint(@RequestBody WorkEffortSearchConstraint workEffortSearchConstraintToBeUpdated,
			@PathVariable String constraintSeqId) throws Exception {

		workEffortSearchConstraintToBeUpdated.setConstraintSeqId(constraintSeqId);

		UpdateWorkEffortSearchConstraint command = new UpdateWorkEffortSearchConstraint(workEffortSearchConstraintToBeUpdated);

		try {
			if(((WorkEffortSearchConstraintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortSearchConstraintId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortSearchConstraintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortSearchConstraintId", workEffortSearchConstraintId);
		try {

			Object foundWorkEffortSearchConstraint = findWorkEffortSearchConstraintsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortSearchConstraint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortSearchConstraintId}")
	public ResponseEntity<Object> deleteWorkEffortSearchConstraintByIdUpdated(@PathVariable String workEffortSearchConstraintId) throws Exception {
		DeleteWorkEffortSearchConstraint command = new DeleteWorkEffortSearchConstraint(workEffortSearchConstraintId);

		try {
			if (((WorkEffortSearchConstraintDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortSearchConstraint could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortSearchConstraint/\" plus one of the following: "
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
