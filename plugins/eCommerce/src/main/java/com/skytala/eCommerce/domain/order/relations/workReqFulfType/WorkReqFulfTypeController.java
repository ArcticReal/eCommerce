package com.skytala.eCommerce.domain.order.relations.workReqFulfType;

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
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.command.AddWorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.command.DeleteWorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.command.UpdateWorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeAdded;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeFound;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.mapper.WorkReqFulfTypeMapper;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.model.WorkReqFulfType;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.query.FindWorkReqFulfTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/workReqFulfTypes")
public class WorkReqFulfTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkReqFulfTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkReqFulfType
	 * @return a List with the WorkReqFulfTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkReqFulfType>> findWorkReqFulfTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkReqFulfTypesBy query = new FindWorkReqFulfTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkReqFulfType> workReqFulfTypes =((WorkReqFulfTypeFound) Scheduler.execute(query).data()).getWorkReqFulfTypes();

		return ResponseEntity.ok().body(workReqFulfTypes);

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
	public ResponseEntity<WorkReqFulfType> createWorkReqFulfType(HttpServletRequest request) throws Exception {

		WorkReqFulfType workReqFulfTypeToBeAdded = new WorkReqFulfType();
		try {
			workReqFulfTypeToBeAdded = WorkReqFulfTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkReqFulfType(workReqFulfTypeToBeAdded);

	}

	/**
	 * creates a new WorkReqFulfType entry in the ofbiz database
	 * 
	 * @param workReqFulfTypeToBeAdded
	 *            the WorkReqFulfType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkReqFulfType> createWorkReqFulfType(@RequestBody WorkReqFulfType workReqFulfTypeToBeAdded) throws Exception {

		AddWorkReqFulfType command = new AddWorkReqFulfType(workReqFulfTypeToBeAdded);
		WorkReqFulfType workReqFulfType = ((WorkReqFulfTypeAdded) Scheduler.execute(command).data()).getAddedWorkReqFulfType();
		
		if (workReqFulfType != null) 
			return successful(workReqFulfType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkReqFulfType with the specific Id
	 * 
	 * @param workReqFulfTypeToBeUpdated
	 *            the WorkReqFulfType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{workReqFulfTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkReqFulfType(@RequestBody WorkReqFulfType workReqFulfTypeToBeUpdated,
			@PathVariable String workReqFulfTypeId) throws Exception {

		workReqFulfTypeToBeUpdated.setWorkReqFulfTypeId(workReqFulfTypeId);

		UpdateWorkReqFulfType command = new UpdateWorkReqFulfType(workReqFulfTypeToBeUpdated);

		try {
			if(((WorkReqFulfTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workReqFulfTypeId}")
	public ResponseEntity<WorkReqFulfType> findById(@PathVariable String workReqFulfTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workReqFulfTypeId", workReqFulfTypeId);
		try {

			List<WorkReqFulfType> foundWorkReqFulfType = findWorkReqFulfTypesBy(requestParams).getBody();
			if(foundWorkReqFulfType.size()==1){				return successful(foundWorkReqFulfType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workReqFulfTypeId}")
	public ResponseEntity<String> deleteWorkReqFulfTypeByIdUpdated(@PathVariable String workReqFulfTypeId) throws Exception {
		DeleteWorkReqFulfType command = new DeleteWorkReqFulfType(workReqFulfTypeId);

		try {
			if (((WorkReqFulfTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
