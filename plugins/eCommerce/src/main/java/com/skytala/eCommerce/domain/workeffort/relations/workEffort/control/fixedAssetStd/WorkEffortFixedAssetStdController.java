package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.fixedAssetStd;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd.AddWorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd.DeleteWorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd.UpdateWorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetStd.WorkEffortFixedAssetStdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.fixedAssetStd.FindWorkEffortFixedAssetStdsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/workEffortFixedAssetStds")
public class WorkEffortFixedAssetStdController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortFixedAssetStdController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortFixedAssetStd
	 * @return a List with the WorkEffortFixedAssetStds
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortFixedAssetStdsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortFixedAssetStdsBy query = new FindWorkEffortFixedAssetStdsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortFixedAssetStd> workEffortFixedAssetStds =((WorkEffortFixedAssetStdFound) Scheduler.execute(query).data()).getWorkEffortFixedAssetStds();

		if (workEffortFixedAssetStds.size() == 1) {
			return ResponseEntity.ok().body(workEffortFixedAssetStds.get(0));
		}

		return ResponseEntity.ok().body(workEffortFixedAssetStds);

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
	public ResponseEntity<Object> createWorkEffortFixedAssetStd(HttpServletRequest request) throws Exception {

		WorkEffortFixedAssetStd workEffortFixedAssetStdToBeAdded = new WorkEffortFixedAssetStd();
		try {
			workEffortFixedAssetStdToBeAdded = WorkEffortFixedAssetStdMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortFixedAssetStd(workEffortFixedAssetStdToBeAdded);

	}

	/**
	 * creates a new WorkEffortFixedAssetStd entry in the ofbiz database
	 * 
	 * @param workEffortFixedAssetStdToBeAdded
	 *            the WorkEffortFixedAssetStd thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortFixedAssetStd(@RequestBody WorkEffortFixedAssetStd workEffortFixedAssetStdToBeAdded) throws Exception {

		AddWorkEffortFixedAssetStd command = new AddWorkEffortFixedAssetStd(workEffortFixedAssetStdToBeAdded);
		WorkEffortFixedAssetStd workEffortFixedAssetStd = ((WorkEffortFixedAssetStdAdded) Scheduler.execute(command).data()).getAddedWorkEffortFixedAssetStd();
		
		if (workEffortFixedAssetStd != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortFixedAssetStd);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortFixedAssetStd could not be created.");
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
	public boolean updateWorkEffortFixedAssetStd(HttpServletRequest request) throws Exception {

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

		WorkEffortFixedAssetStd workEffortFixedAssetStdToBeUpdated = new WorkEffortFixedAssetStd();

		try {
			workEffortFixedAssetStdToBeUpdated = WorkEffortFixedAssetStdMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortFixedAssetStd(workEffortFixedAssetStdToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortFixedAssetStd with the specific Id
	 * 
	 * @param workEffortFixedAssetStdToBeUpdated
	 *            the WorkEffortFixedAssetStd thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortFixedAssetStd(@RequestBody WorkEffortFixedAssetStd workEffortFixedAssetStdToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortFixedAssetStdToBeUpdated.setnull(null);

		UpdateWorkEffortFixedAssetStd command = new UpdateWorkEffortFixedAssetStd(workEffortFixedAssetStdToBeUpdated);

		try {
			if(((WorkEffortFixedAssetStdUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortFixedAssetStdId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortFixedAssetStdId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortFixedAssetStdId", workEffortFixedAssetStdId);
		try {

			Object foundWorkEffortFixedAssetStd = findWorkEffortFixedAssetStdsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortFixedAssetStd);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortFixedAssetStdId}")
	public ResponseEntity<Object> deleteWorkEffortFixedAssetStdByIdUpdated(@PathVariable String workEffortFixedAssetStdId) throws Exception {
		DeleteWorkEffortFixedAssetStd command = new DeleteWorkEffortFixedAssetStd(workEffortFixedAssetStdId);

		try {
			if (((WorkEffortFixedAssetStdDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortFixedAssetStd could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortFixedAssetStd/\" plus one of the following: "
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
