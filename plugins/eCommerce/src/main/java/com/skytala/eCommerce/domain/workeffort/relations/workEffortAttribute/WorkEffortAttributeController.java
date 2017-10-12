package com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.command.AddWorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.command.DeleteWorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.command.UpdateWorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.event.WorkEffortAttributeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.event.WorkEffortAttributeDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.event.WorkEffortAttributeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.event.WorkEffortAttributeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.mapper.WorkEffortAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.model.WorkEffortAttribute;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAttribute.query.FindWorkEffortAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortAttributes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAttributesBy query = new FindWorkEffortAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAttribute> workEffortAttributes =((WorkEffortAttributeFound) Scheduler.execute(query).data()).getWorkEffortAttributes();

		if (workEffortAttributes.size() == 1) {
			return ResponseEntity.ok().body(workEffortAttributes.get(0));
		}

		return ResponseEntity.ok().body(workEffortAttributes);

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
	public ResponseEntity<Object> createWorkEffortAttribute(HttpServletRequest request) throws Exception {

		WorkEffortAttribute workEffortAttributeToBeAdded = new WorkEffortAttribute();
		try {
			workEffortAttributeToBeAdded = WorkEffortAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortAttribute(workEffortAttributeToBeAdded);

	}

	/**
	 * creates a new WorkEffortAttribute entry in the ofbiz database
	 * 
	 * @param workEffortAttributeToBeAdded
	 *            the WorkEffortAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortAttribute(@RequestBody WorkEffortAttribute workEffortAttributeToBeAdded) throws Exception {

		AddWorkEffortAttribute command = new AddWorkEffortAttribute(workEffortAttributeToBeAdded);
		WorkEffortAttribute workEffortAttribute = ((WorkEffortAttributeAdded) Scheduler.execute(command).data()).getAddedWorkEffortAttribute();
		
		if (workEffortAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortAttribute could not be created.");
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
	public boolean updateWorkEffortAttribute(HttpServletRequest request) throws Exception {

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

		WorkEffortAttribute workEffortAttributeToBeUpdated = new WorkEffortAttribute();

		try {
			workEffortAttributeToBeUpdated = WorkEffortAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortAttribute(workEffortAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortAttribute with the specific Id
	 * 
	 * @param workEffortAttributeToBeUpdated
	 *            the WorkEffortAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortAttribute(@RequestBody WorkEffortAttribute workEffortAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortAttributeToBeUpdated.setnull(null);

		UpdateWorkEffortAttribute command = new UpdateWorkEffortAttribute(workEffortAttributeToBeUpdated);

		try {
			if(((WorkEffortAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAttributeId", workEffortAttributeId);
		try {

			Object foundWorkEffortAttribute = findWorkEffortAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortAttributeId}")
	public ResponseEntity<Object> deleteWorkEffortAttributeByIdUpdated(@PathVariable String workEffortAttributeId) throws Exception {
		DeleteWorkEffortAttribute command = new DeleteWorkEffortAttribute(workEffortAttributeId);

		try {
			if (((WorkEffortAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortAttribute/\" plus one of the following: "
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
