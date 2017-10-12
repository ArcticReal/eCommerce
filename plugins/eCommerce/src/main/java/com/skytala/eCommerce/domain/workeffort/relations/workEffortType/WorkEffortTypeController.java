package com.skytala.eCommerce.domain.workeffort.relations.workEffortType;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.command.AddWorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.command.DeleteWorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.command.UpdateWorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event.WorkEffortTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event.WorkEffortTypeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event.WorkEffortTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event.WorkEffortTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.mapper.WorkEffortTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.model.WorkEffortType;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.query.FindWorkEffortTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortTypes")
public class WorkEffortTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortType
	 * @return a List with the WorkEffortTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortTypesBy query = new FindWorkEffortTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortType> workEffortTypes =((WorkEffortTypeFound) Scheduler.execute(query).data()).getWorkEffortTypes();

		if (workEffortTypes.size() == 1) {
			return ResponseEntity.ok().body(workEffortTypes.get(0));
		}

		return ResponseEntity.ok().body(workEffortTypes);

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
	public ResponseEntity<Object> createWorkEffortType(HttpServletRequest request) throws Exception {

		WorkEffortType workEffortTypeToBeAdded = new WorkEffortType();
		try {
			workEffortTypeToBeAdded = WorkEffortTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortType(workEffortTypeToBeAdded);

	}

	/**
	 * creates a new WorkEffortType entry in the ofbiz database
	 * 
	 * @param workEffortTypeToBeAdded
	 *            the WorkEffortType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortType(@RequestBody WorkEffortType workEffortTypeToBeAdded) throws Exception {

		AddWorkEffortType command = new AddWorkEffortType(workEffortTypeToBeAdded);
		WorkEffortType workEffortType = ((WorkEffortTypeAdded) Scheduler.execute(command).data()).getAddedWorkEffortType();
		
		if (workEffortType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortType could not be created.");
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
	public boolean updateWorkEffortType(HttpServletRequest request) throws Exception {

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

		WorkEffortType workEffortTypeToBeUpdated = new WorkEffortType();

		try {
			workEffortTypeToBeUpdated = WorkEffortTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortType(workEffortTypeToBeUpdated, workEffortTypeToBeUpdated.getWorkEffortTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortType with the specific Id
	 * 
	 * @param workEffortTypeToBeUpdated
	 *            the WorkEffortType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workEffortTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortType(@RequestBody WorkEffortType workEffortTypeToBeUpdated,
			@PathVariable String workEffortTypeId) throws Exception {

		workEffortTypeToBeUpdated.setWorkEffortTypeId(workEffortTypeId);

		UpdateWorkEffortType command = new UpdateWorkEffortType(workEffortTypeToBeUpdated);

		try {
			if(((WorkEffortTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortTypeId", workEffortTypeId);
		try {

			Object foundWorkEffortType = findWorkEffortTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortTypeId}")
	public ResponseEntity<Object> deleteWorkEffortTypeByIdUpdated(@PathVariable String workEffortTypeId) throws Exception {
		DeleteWorkEffortType command = new DeleteWorkEffortType(workEffortTypeId);

		try {
			if (((WorkEffortTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortType/\" plus one of the following: "
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
