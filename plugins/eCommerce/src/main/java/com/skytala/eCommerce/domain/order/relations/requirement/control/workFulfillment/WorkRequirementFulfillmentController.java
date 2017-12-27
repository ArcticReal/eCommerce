package com.skytala.eCommerce.domain.order.relations.requirement.control.workFulfillment;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.workFulfillment.AddWorkRequirementFulfillment;
import com.skytala.eCommerce.domain.order.relations.requirement.command.workFulfillment.DeleteWorkRequirementFulfillment;
import com.skytala.eCommerce.domain.order.relations.requirement.command.workFulfillment.UpdateWorkRequirementFulfillment;
import com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment.WorkRequirementFulfillmentAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment.WorkRequirementFulfillmentDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment.WorkRequirementFulfillmentFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment.WorkRequirementFulfillmentUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.workFulfillment.WorkRequirementFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;
import com.skytala.eCommerce.domain.order.relations.requirement.query.workFulfillment.FindWorkRequirementFulfillmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirement/workRequirementFulfillments")
public class WorkRequirementFulfillmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkRequirementFulfillmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkRequirementFulfillment
	 * @return a List with the WorkRequirementFulfillments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkRequirementFulfillment>> findWorkRequirementFulfillmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkRequirementFulfillmentsBy query = new FindWorkRequirementFulfillmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkRequirementFulfillment> workRequirementFulfillments =((WorkRequirementFulfillmentFound) Scheduler.execute(query).data()).getWorkRequirementFulfillments();

		return ResponseEntity.ok().body(workRequirementFulfillments);

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
	public ResponseEntity<WorkRequirementFulfillment> createWorkRequirementFulfillment(HttpServletRequest request) throws Exception {

		WorkRequirementFulfillment workRequirementFulfillmentToBeAdded = new WorkRequirementFulfillment();
		try {
			workRequirementFulfillmentToBeAdded = WorkRequirementFulfillmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkRequirementFulfillment(workRequirementFulfillmentToBeAdded);

	}

	/**
	 * creates a new WorkRequirementFulfillment entry in the ofbiz database
	 * 
	 * @param workRequirementFulfillmentToBeAdded
	 *            the WorkRequirementFulfillment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkRequirementFulfillment> createWorkRequirementFulfillment(@RequestBody WorkRequirementFulfillment workRequirementFulfillmentToBeAdded) throws Exception {

		AddWorkRequirementFulfillment command = new AddWorkRequirementFulfillment(workRequirementFulfillmentToBeAdded);
		WorkRequirementFulfillment workRequirementFulfillment = ((WorkRequirementFulfillmentAdded) Scheduler.execute(command).data()).getAddedWorkRequirementFulfillment();
		
		if (workRequirementFulfillment != null) 
			return successful(workRequirementFulfillment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkRequirementFulfillment with the specific Id
	 * 
	 * @param workRequirementFulfillmentToBeUpdated
	 *            the WorkRequirementFulfillment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkRequirementFulfillment(@RequestBody WorkRequirementFulfillment workRequirementFulfillmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workRequirementFulfillmentToBeUpdated.setnull(null);

		UpdateWorkRequirementFulfillment command = new UpdateWorkRequirementFulfillment(workRequirementFulfillmentToBeUpdated);

		try {
			if(((WorkRequirementFulfillmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workRequirementFulfillmentId}")
	public ResponseEntity<WorkRequirementFulfillment> findById(@PathVariable String workRequirementFulfillmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workRequirementFulfillmentId", workRequirementFulfillmentId);
		try {

			List<WorkRequirementFulfillment> foundWorkRequirementFulfillment = findWorkRequirementFulfillmentsBy(requestParams).getBody();
			if(foundWorkRequirementFulfillment.size()==1){				return successful(foundWorkRequirementFulfillment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workRequirementFulfillmentId}")
	public ResponseEntity<String> deleteWorkRequirementFulfillmentByIdUpdated(@PathVariable String workRequirementFulfillmentId) throws Exception {
		DeleteWorkRequirementFulfillment command = new DeleteWorkRequirementFulfillment(workRequirementFulfillmentId);

		try {
			if (((WorkRequirementFulfillmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
