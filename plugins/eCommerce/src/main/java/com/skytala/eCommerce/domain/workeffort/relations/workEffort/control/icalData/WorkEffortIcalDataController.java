package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.icalData;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.icalData.AddWorkEffortIcalData;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.icalData.DeleteWorkEffortIcalData;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.icalData.UpdateWorkEffortIcalData;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData.WorkEffortIcalDataAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData.WorkEffortIcalDataDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData.WorkEffortIcalDataFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData.WorkEffortIcalDataUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.icalData.WorkEffortIcalDataMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.icalData.WorkEffortIcalData;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.icalData.FindWorkEffortIcalDatasBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortIcalDatas")
public class WorkEffortIcalDataController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortIcalDataController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortIcalData
	 * @return a List with the WorkEffortIcalDatas
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortIcalData>> findWorkEffortIcalDatasBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortIcalDatasBy query = new FindWorkEffortIcalDatasBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortIcalData> workEffortIcalDatas =((WorkEffortIcalDataFound) Scheduler.execute(query).data()).getWorkEffortIcalDatas();

		return ResponseEntity.ok().body(workEffortIcalDatas);

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
	public ResponseEntity<WorkEffortIcalData> createWorkEffortIcalData(HttpServletRequest request) throws Exception {

		WorkEffortIcalData workEffortIcalDataToBeAdded = new WorkEffortIcalData();
		try {
			workEffortIcalDataToBeAdded = WorkEffortIcalDataMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkEffortIcalData(workEffortIcalDataToBeAdded);

	}

	/**
	 * creates a new WorkEffortIcalData entry in the ofbiz database
	 * 
	 * @param workEffortIcalDataToBeAdded
	 *            the WorkEffortIcalData thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortIcalData> createWorkEffortIcalData(@RequestBody WorkEffortIcalData workEffortIcalDataToBeAdded) throws Exception {

		AddWorkEffortIcalData command = new AddWorkEffortIcalData(workEffortIcalDataToBeAdded);
		WorkEffortIcalData workEffortIcalData = ((WorkEffortIcalDataAdded) Scheduler.execute(command).data()).getAddedWorkEffortIcalData();
		
		if (workEffortIcalData != null) 
			return successful(workEffortIcalData);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortIcalData with the specific Id
	 * 
	 * @param workEffortIcalDataToBeUpdated
	 *            the WorkEffortIcalData thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortIcalData(@RequestBody WorkEffortIcalData workEffortIcalDataToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortIcalDataToBeUpdated.setnull(null);

		UpdateWorkEffortIcalData command = new UpdateWorkEffortIcalData(workEffortIcalDataToBeUpdated);

		try {
			if(((WorkEffortIcalDataUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortIcalDataId}")
	public ResponseEntity<WorkEffortIcalData> findById(@PathVariable String workEffortIcalDataId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortIcalDataId", workEffortIcalDataId);
		try {

			List<WorkEffortIcalData> foundWorkEffortIcalData = findWorkEffortIcalDatasBy(requestParams).getBody();
			if(foundWorkEffortIcalData.size()==1){				return successful(foundWorkEffortIcalData.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortIcalDataId}")
	public ResponseEntity<String> deleteWorkEffortIcalDataByIdUpdated(@PathVariable String workEffortIcalDataId) throws Exception {
		DeleteWorkEffortIcalData command = new DeleteWorkEffortIcalData(workEffortIcalDataId);

		try {
			if (((WorkEffortIcalDataDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
