package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.assocTypeAttr;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr.AddWorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr.DeleteWorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr.UpdateWorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocTypeAttr.WorkEffortAssocTypeAttrMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocTypeAttr.WorkEffortAssocTypeAttr;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assocTypeAttr.FindWorkEffortAssocTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortAssocTypeAttrs")
public class WorkEffortAssocTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortAssocTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortAssocTypeAttr
	 * @return a List with the WorkEffortAssocTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWorkEffortAssocTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortAssocTypeAttrsBy query = new FindWorkEffortAssocTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortAssocTypeAttr> workEffortAssocTypeAttrs =((WorkEffortAssocTypeAttrFound) Scheduler.execute(query).data()).getWorkEffortAssocTypeAttrs();

		if (workEffortAssocTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(workEffortAssocTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(workEffortAssocTypeAttrs);

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
	public ResponseEntity<Object> createWorkEffortAssocTypeAttr(HttpServletRequest request) throws Exception {

		WorkEffortAssocTypeAttr workEffortAssocTypeAttrToBeAdded = new WorkEffortAssocTypeAttr();
		try {
			workEffortAssocTypeAttrToBeAdded = WorkEffortAssocTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortAssocTypeAttr(workEffortAssocTypeAttrToBeAdded);

	}

	/**
	 * creates a new WorkEffortAssocTypeAttr entry in the ofbiz database
	 * 
	 * @param workEffortAssocTypeAttrToBeAdded
	 *            the WorkEffortAssocTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortAssocTypeAttr(@RequestBody WorkEffortAssocTypeAttr workEffortAssocTypeAttrToBeAdded) throws Exception {

		AddWorkEffortAssocTypeAttr command = new AddWorkEffortAssocTypeAttr(workEffortAssocTypeAttrToBeAdded);
		WorkEffortAssocTypeAttr workEffortAssocTypeAttr = ((WorkEffortAssocTypeAttrAdded) Scheduler.execute(command).data()).getAddedWorkEffortAssocTypeAttr();
		
		if (workEffortAssocTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortAssocTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortAssocTypeAttr could not be created.");
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
	public boolean updateWorkEffortAssocTypeAttr(HttpServletRequest request) throws Exception {

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

		WorkEffortAssocTypeAttr workEffortAssocTypeAttrToBeUpdated = new WorkEffortAssocTypeAttr();

		try {
			workEffortAssocTypeAttrToBeUpdated = WorkEffortAssocTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortAssocTypeAttr(workEffortAssocTypeAttrToBeUpdated, workEffortAssocTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortAssocTypeAttr with the specific Id
	 * 
	 * @param workEffortAssocTypeAttrToBeUpdated
	 *            the WorkEffortAssocTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortAssocTypeAttr(@RequestBody WorkEffortAssocTypeAttr workEffortAssocTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		workEffortAssocTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateWorkEffortAssocTypeAttr command = new UpdateWorkEffortAssocTypeAttr(workEffortAssocTypeAttrToBeUpdated);

		try {
			if(((WorkEffortAssocTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortAssocTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortAssocTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortAssocTypeAttrId", workEffortAssocTypeAttrId);
		try {

			Object foundWorkEffortAssocTypeAttr = findWorkEffortAssocTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortAssocTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortAssocTypeAttrId}")
	public ResponseEntity<Object> deleteWorkEffortAssocTypeAttrByIdUpdated(@PathVariable String workEffortAssocTypeAttrId) throws Exception {
		DeleteWorkEffortAssocTypeAttr command = new DeleteWorkEffortAssocTypeAttr(workEffortAssocTypeAttrId);

		try {
			if (((WorkEffortAssocTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortAssocTypeAttr could not be deleted");

	}

}
