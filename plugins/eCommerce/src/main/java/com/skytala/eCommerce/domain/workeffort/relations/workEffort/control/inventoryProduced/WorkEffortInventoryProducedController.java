package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.inventoryProduced;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced.AddWorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced.DeleteWorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.inventoryProduced.UpdateWorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.inventoryProduced.WorkEffortInventoryProducedUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryProduced.WorkEffortInventoryProducedMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.inventoryProduced.FindWorkEffortInventoryProducedsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortInventoryProduceds")
public class WorkEffortInventoryProducedController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortInventoryProducedController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortInventoryProduced
	 * @return a List with the WorkEffortInventoryProduceds
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortInventoryProducedsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortInventoryProducedsBy query = new FindWorkEffortInventoryProducedsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortInventoryProduced> workEffortInventoryProduceds =((WorkEffortInventoryProducedFound) Scheduler.execute(query).data()).getWorkEffortInventoryProduceds();

		if (workEffortInventoryProduceds.size() == 1) {
			return ResponseEntity.ok().body(workEffortInventoryProduceds.get(0));
		}

		return ResponseEntity.ok().body(workEffortInventoryProduceds);

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
	public ResponseEntity<Object> createWorkEffortInventoryProduced(HttpServletRequest request) throws Exception {

		WorkEffortInventoryProduced workEffortInventoryProducedToBeAdded = new WorkEffortInventoryProduced();
		try {
			workEffortInventoryProducedToBeAdded = WorkEffortInventoryProducedMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortInventoryProduced(workEffortInventoryProducedToBeAdded);

	}

	/**
	 * creates a new WorkEffortInventoryProduced entry in the ofbiz database
	 * 
	 * @param workEffortInventoryProducedToBeAdded
	 *            the WorkEffortInventoryProduced thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortInventoryProduced(@RequestBody WorkEffortInventoryProduced workEffortInventoryProducedToBeAdded) throws Exception {

		AddWorkEffortInventoryProduced command = new AddWorkEffortInventoryProduced(workEffortInventoryProducedToBeAdded);
		WorkEffortInventoryProduced workEffortInventoryProduced = ((WorkEffortInventoryProducedAdded) Scheduler.execute(command).data()).getAddedWorkEffortInventoryProduced();
		
		if (workEffortInventoryProduced != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortInventoryProduced);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortInventoryProduced could not be created.");
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
	public boolean updateWorkEffortInventoryProduced(HttpServletRequest request) throws Exception {

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

		WorkEffortInventoryProduced workEffortInventoryProducedToBeUpdated = new WorkEffortInventoryProduced();

		try {
			workEffortInventoryProducedToBeUpdated = WorkEffortInventoryProducedMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortInventoryProduced(workEffortInventoryProducedToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortInventoryProduced with the specific Id
	 * 
	 * @param workEffortInventoryProducedToBeUpdated
	 *            the WorkEffortInventoryProduced thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortInventoryProduced(@RequestBody WorkEffortInventoryProduced workEffortInventoryProducedToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortInventoryProducedToBeUpdated.setnull(null);

		UpdateWorkEffortInventoryProduced command = new UpdateWorkEffortInventoryProduced(workEffortInventoryProducedToBeUpdated);

		try {
			if(((WorkEffortInventoryProducedUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortInventoryProducedId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortInventoryProducedId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortInventoryProducedId", workEffortInventoryProducedId);
		try {

			Object foundWorkEffortInventoryProduced = findWorkEffortInventoryProducedsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortInventoryProduced);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortInventoryProducedId}")
	public ResponseEntity<Object> deleteWorkEffortInventoryProducedByIdUpdated(@PathVariable String workEffortInventoryProducedId) throws Exception {
		DeleteWorkEffortInventoryProduced command = new DeleteWorkEffortInventoryProduced(workEffortInventoryProducedId);

		try {
			if (((WorkEffortInventoryProducedDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortInventoryProduced could not be deleted");

	}

}
