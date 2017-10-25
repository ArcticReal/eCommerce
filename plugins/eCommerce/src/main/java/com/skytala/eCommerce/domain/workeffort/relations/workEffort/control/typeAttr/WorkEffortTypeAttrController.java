package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.typeAttr;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.typeAttr.AddWorkEffortTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.typeAttr.DeleteWorkEffortTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.typeAttr.UpdateWorkEffortTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr.WorkEffortTypeAttrAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr.WorkEffortTypeAttrDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr.WorkEffortTypeAttrFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr.WorkEffortTypeAttrUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.typeAttr.WorkEffortTypeAttrMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.typeAttr.WorkEffortTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.typeAttr.FindWorkEffortTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortTypeAttrs")
public class WorkEffortTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortTypeAttr
	 * @return a List with the WorkEffortTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortTypeAttrsBy query = new FindWorkEffortTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortTypeAttr> workEffortTypeAttrs =((WorkEffortTypeAttrFound) Scheduler.execute(query).data()).getWorkEffortTypeAttrs();

		if (workEffortTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(workEffortTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(workEffortTypeAttrs);

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
	public ResponseEntity<Object> createWorkEffortTypeAttr(HttpServletRequest request) throws Exception {

		WorkEffortTypeAttr workEffortTypeAttrToBeAdded = new WorkEffortTypeAttr();
		try {
			workEffortTypeAttrToBeAdded = WorkEffortTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortTypeAttr(workEffortTypeAttrToBeAdded);

	}

	/**
	 * creates a new WorkEffortTypeAttr entry in the ofbiz database
	 * 
	 * @param workEffortTypeAttrToBeAdded
	 *            the WorkEffortTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortTypeAttr(@RequestBody WorkEffortTypeAttr workEffortTypeAttrToBeAdded) throws Exception {

		AddWorkEffortTypeAttr command = new AddWorkEffortTypeAttr(workEffortTypeAttrToBeAdded);
		WorkEffortTypeAttr workEffortTypeAttr = ((WorkEffortTypeAttrAdded) Scheduler.execute(command).data()).getAddedWorkEffortTypeAttr();
		
		if (workEffortTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortTypeAttr could not be created.");
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
	public boolean updateWorkEffortTypeAttr(HttpServletRequest request) throws Exception {

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

		WorkEffortTypeAttr workEffortTypeAttrToBeUpdated = new WorkEffortTypeAttr();

		try {
			workEffortTypeAttrToBeUpdated = WorkEffortTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortTypeAttr(workEffortTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortTypeAttr with the specific Id
	 * 
	 * @param workEffortTypeAttrToBeUpdated
	 *            the WorkEffortTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortTypeAttr(@RequestBody WorkEffortTypeAttr workEffortTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortTypeAttrToBeUpdated.setnull(null);

		UpdateWorkEffortTypeAttr command = new UpdateWorkEffortTypeAttr(workEffortTypeAttrToBeUpdated);

		try {
			if(((WorkEffortTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortTypeAttrId", workEffortTypeAttrId);
		try {

			Object foundWorkEffortTypeAttr = findWorkEffortTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortTypeAttrId}")
	public ResponseEntity<Object> deleteWorkEffortTypeAttrByIdUpdated(@PathVariable String workEffortTypeAttrId) throws Exception {
		DeleteWorkEffortTypeAttr command = new DeleteWorkEffortTypeAttr(workEffortTypeAttrId);

		try {
			if (((WorkEffortTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortTypeAttr/\" plus one of the following: "
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
