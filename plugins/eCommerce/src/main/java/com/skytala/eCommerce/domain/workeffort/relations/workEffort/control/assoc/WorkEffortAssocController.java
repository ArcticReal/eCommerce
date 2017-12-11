package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.assoc;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assoc.AddWorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assoc.DeleteWorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assoc.UpdateWorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc.WorkEffortAssocUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assoc.WorkEffortAssocMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assoc.WorkEffortAssoc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assoc.FindWorkEffortAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAssocs")
public class WorkEffortAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAssoc
	 * @return a List with the WorkEffortAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWorkEffortAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocsBy query = new FindWorkEffortAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssoc> workEffortAssocs =((WorkEffortAssocFound) Scheduler.execute(query).data()).getWorkEffortAssocs();

		if (workEffortAssocs.size() == 1) {
			return ResponseEntity.ok().body(workEffortAssocs.get(0));
		}

		return ResponseEntity.ok().body(workEffortAssocs);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createWorkEffortAssoc(HttpServletRequest request) throws Exception {

		WorkEffortAssoc workEffortAssocToBeAdded = new WorkEffortAssoc();
		try {
			workEffortAssocToBeAdded = WorkEffortAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortAssoc(workEffortAssocToBeAdded);

	}

	/**
	 * creates a new WorkEffortAssoc entry in the ofbiz database
	 * 
	 * @param workEffortAssocToBeAdded
	 *            the WorkEffortAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortAssoc(@RequestBody WorkEffortAssoc workEffortAssocToBeAdded) throws Exception {

		AddWorkEffortAssoc command = new AddWorkEffortAssoc(workEffortAssocToBeAdded);
		WorkEffortAssoc workEffortAssoc = ((WorkEffortAssocAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssoc();
		
		if (workEffortAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortAssoc could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateWorkEffortAssoc(HttpServletRequest request) throws Exception {

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

		WorkEffortAssoc workEffortAssocToBeUpdated = new WorkEffortAssoc();

		try {
			workEffortAssocToBeUpdated = WorkEffortAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortAssoc(workEffortAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortAssoc with the specific Id
	 * 
	 * @param workEffortAssocToBeUpdated
	 *            the WorkEffortAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortAssoc(@RequestBody WorkEffortAssoc workEffortAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortAssocToBeUpdated.setnull(null);

		UpdateWorkEffortAssoc command = new UpdateWorkEffortAssoc(workEffortAssocToBeUpdated);

		try {
			if(((WorkEffortAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocId", workEffortAssocId);
		try {

			Object foundWorkEffortAssoc = findWorkEffortAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortAssocId}")
	public ResponseEntity<Object> deleteWorkEffortAssocByIdUpdated(@PathVariable String workEffortAssocId) throws Exception {
		DeleteWorkEffortAssoc command = new DeleteWorkEffortAssoc(workEffortAssocId);

		try {
			if (((WorkEffortAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortAssoc could not be deleted");

	}

}
