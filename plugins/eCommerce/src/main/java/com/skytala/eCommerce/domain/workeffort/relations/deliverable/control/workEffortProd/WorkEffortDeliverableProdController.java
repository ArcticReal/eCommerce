package com.skytala.eCommerce.domain.workeffort.relations.deliverable.control.workEffortProd;

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
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.workEffortProd.AddWorkEffortDeliverableProd;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.workEffortProd.DeleteWorkEffortDeliverableProd;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.workEffortProd.UpdateWorkEffortDeliverableProd;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd.WorkEffortDeliverableProdAdded;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd.WorkEffortDeliverableProdDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd.WorkEffortDeliverableProdFound;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd.WorkEffortDeliverableProdUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper.workEffortProd.WorkEffortDeliverableProdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.workEffortProd.WorkEffortDeliverableProd;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.query.workEffortProd.FindWorkEffortDeliverableProdsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workeffort/deliverable/workEffortDeliverableProds")
public class WorkEffortDeliverableProdController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortDeliverableProdController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortDeliverableProd
	 * @return a List with the WorkEffortDeliverableProds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWorkEffortDeliverableProdsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortDeliverableProdsBy query = new FindWorkEffortDeliverableProdsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortDeliverableProd> workEffortDeliverableProds =((WorkEffortDeliverableProdFound) Scheduler.execute(query).data()).getWorkEffortDeliverableProds();

		if (workEffortDeliverableProds.size() == 1) {
			return ResponseEntity.ok().body(workEffortDeliverableProds.get(0));
		}

		return ResponseEntity.ok().body(workEffortDeliverableProds);

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
	public ResponseEntity<Object> createWorkEffortDeliverableProd(HttpServletRequest request) throws Exception {

		WorkEffortDeliverableProd workEffortDeliverableProdToBeAdded = new WorkEffortDeliverableProd();
		try {
			workEffortDeliverableProdToBeAdded = WorkEffortDeliverableProdMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortDeliverableProd(workEffortDeliverableProdToBeAdded);

	}

	/**
	 * creates a new WorkEffortDeliverableProd entry in the ofbiz database
	 * 
	 * @param workEffortDeliverableProdToBeAdded
	 *            the WorkEffortDeliverableProd thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortDeliverableProd(@RequestBody WorkEffortDeliverableProd workEffortDeliverableProdToBeAdded) throws Exception {

		AddWorkEffortDeliverableProd command = new AddWorkEffortDeliverableProd(workEffortDeliverableProdToBeAdded);
		WorkEffortDeliverableProd workEffortDeliverableProd = ((WorkEffortDeliverableProdAdded) Scheduler.execute(command).data()).getAddedWorkEffortDeliverableProd();
		
		if (workEffortDeliverableProd != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortDeliverableProd);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortDeliverableProd could not be created.");
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
	public boolean updateWorkEffortDeliverableProd(HttpServletRequest request) throws Exception {

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

		WorkEffortDeliverableProd workEffortDeliverableProdToBeUpdated = new WorkEffortDeliverableProd();

		try {
			workEffortDeliverableProdToBeUpdated = WorkEffortDeliverableProdMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortDeliverableProd(workEffortDeliverableProdToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortDeliverableProd with the specific Id
	 * 
	 * @param workEffortDeliverableProdToBeUpdated
	 *            the WorkEffortDeliverableProd thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortDeliverableProd(@RequestBody WorkEffortDeliverableProd workEffortDeliverableProdToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortDeliverableProdToBeUpdated.setnull(null);

		UpdateWorkEffortDeliverableProd command = new UpdateWorkEffortDeliverableProd(workEffortDeliverableProdToBeUpdated);

		try {
			if(((WorkEffortDeliverableProdUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortDeliverableProdId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortDeliverableProdId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortDeliverableProdId", workEffortDeliverableProdId);
		try {

			Object foundWorkEffortDeliverableProd = findWorkEffortDeliverableProdsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortDeliverableProd);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortDeliverableProdId}")
	public ResponseEntity<Object> deleteWorkEffortDeliverableProdByIdUpdated(@PathVariable String workEffortDeliverableProdId) throws Exception {
		DeleteWorkEffortDeliverableProd command = new DeleteWorkEffortDeliverableProd(workEffortDeliverableProdId);

		try {
			if (((WorkEffortDeliverableProdDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortDeliverableProd could not be deleted");

	}

}
