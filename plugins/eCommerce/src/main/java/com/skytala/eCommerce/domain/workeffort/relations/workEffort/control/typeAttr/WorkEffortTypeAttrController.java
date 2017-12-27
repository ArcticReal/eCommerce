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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortTypeAttrs")
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
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortTypeAttr>> findWorkEffortTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortTypeAttrsBy query = new FindWorkEffortTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortTypeAttr> workEffortTypeAttrs =((WorkEffortTypeAttrFound) Scheduler.execute(query).data()).getWorkEffortTypeAttrs();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<WorkEffortTypeAttr> createWorkEffortTypeAttr(HttpServletRequest request) throws Exception {

		WorkEffortTypeAttr workEffortTypeAttrToBeAdded = new WorkEffortTypeAttr();
		try {
			workEffortTypeAttrToBeAdded = WorkEffortTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WorkEffortTypeAttr> createWorkEffortTypeAttr(@RequestBody WorkEffortTypeAttr workEffortTypeAttrToBeAdded) throws Exception {

		AddWorkEffortTypeAttr command = new AddWorkEffortTypeAttr(workEffortTypeAttrToBeAdded);
		WorkEffortTypeAttr workEffortTypeAttr = ((WorkEffortTypeAttrAdded) Scheduler.execute(command).data()).getAddedWorkEffortTypeAttr();
		
		if (workEffortTypeAttr != null) 
			return successful(workEffortTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortTypeAttr with the specific Id
	 * 
	 * @param workEffortTypeAttrToBeUpdated
	 *            the WorkEffortTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortTypeAttr(@RequestBody WorkEffortTypeAttr workEffortTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		workEffortTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateWorkEffortTypeAttr command = new UpdateWorkEffortTypeAttr(workEffortTypeAttrToBeUpdated);

		try {
			if(((WorkEffortTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortTypeAttrId}")
	public ResponseEntity<WorkEffortTypeAttr> findById(@PathVariable String workEffortTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortTypeAttrId", workEffortTypeAttrId);
		try {

			List<WorkEffortTypeAttr> foundWorkEffortTypeAttr = findWorkEffortTypeAttrsBy(requestParams).getBody();
			if(foundWorkEffortTypeAttr.size()==1){				return successful(foundWorkEffortTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortTypeAttrId}")
	public ResponseEntity<String> deleteWorkEffortTypeAttrByIdUpdated(@PathVariable String workEffortTypeAttrId) throws Exception {
		DeleteWorkEffortTypeAttr command = new DeleteWorkEffortTypeAttr(workEffortTypeAttrId);

		try {
			if (((WorkEffortTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
