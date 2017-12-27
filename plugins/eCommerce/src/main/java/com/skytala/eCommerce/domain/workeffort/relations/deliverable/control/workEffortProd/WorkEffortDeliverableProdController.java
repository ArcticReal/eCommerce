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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<WorkEffortDeliverableProd>> findWorkEffortDeliverableProdsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortDeliverableProdsBy query = new FindWorkEffortDeliverableProdsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortDeliverableProd> workEffortDeliverableProds =((WorkEffortDeliverableProdFound) Scheduler.execute(query).data()).getWorkEffortDeliverableProds();

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
	public ResponseEntity<WorkEffortDeliverableProd> createWorkEffortDeliverableProd(HttpServletRequest request) throws Exception {

		WorkEffortDeliverableProd workEffortDeliverableProdToBeAdded = new WorkEffortDeliverableProd();
		try {
			workEffortDeliverableProdToBeAdded = WorkEffortDeliverableProdMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WorkEffortDeliverableProd> createWorkEffortDeliverableProd(@RequestBody WorkEffortDeliverableProd workEffortDeliverableProdToBeAdded) throws Exception {

		AddWorkEffortDeliverableProd command = new AddWorkEffortDeliverableProd(workEffortDeliverableProdToBeAdded);
		WorkEffortDeliverableProd workEffortDeliverableProd = ((WorkEffortDeliverableProdAdded) Scheduler.execute(command).data()).getAddedWorkEffortDeliverableProd();
		
		if (workEffortDeliverableProd != null) 
			return successful(workEffortDeliverableProd);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortDeliverableProd(@RequestBody WorkEffortDeliverableProd workEffortDeliverableProdToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortDeliverableProdToBeUpdated.setnull(null);

		UpdateWorkEffortDeliverableProd command = new UpdateWorkEffortDeliverableProd(workEffortDeliverableProdToBeUpdated);

		try {
			if(((WorkEffortDeliverableProdUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortDeliverableProdId}")
	public ResponseEntity<WorkEffortDeliverableProd> findById(@PathVariable String workEffortDeliverableProdId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortDeliverableProdId", workEffortDeliverableProdId);
		try {

			List<WorkEffortDeliverableProd> foundWorkEffortDeliverableProd = findWorkEffortDeliverableProdsBy(requestParams).getBody();
			if(foundWorkEffortDeliverableProd.size()==1){				return successful(foundWorkEffortDeliverableProd.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortDeliverableProdId}")
	public ResponseEntity<String> deleteWorkEffortDeliverableProdByIdUpdated(@PathVariable String workEffortDeliverableProdId) throws Exception {
		DeleteWorkEffortDeliverableProd command = new DeleteWorkEffortDeliverableProd(workEffortDeliverableProdId);

		try {
			if (((WorkEffortDeliverableProdDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
