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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findWorkEffortIcalDatasBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortIcalDatasBy query = new FindWorkEffortIcalDatasBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortIcalData> workEffortIcalDatas =((WorkEffortIcalDataFound) Scheduler.execute(query).data()).getWorkEffortIcalDatas();

		if (workEffortIcalDatas.size() == 1) {
			return ResponseEntity.ok().body(workEffortIcalDatas.get(0));
		}

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
	public ResponseEntity<Object> createWorkEffortIcalData(HttpServletRequest request) throws Exception {

		WorkEffortIcalData workEffortIcalDataToBeAdded = new WorkEffortIcalData();
		try {
			workEffortIcalDataToBeAdded = WorkEffortIcalDataMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createWorkEffortIcalData(@RequestBody WorkEffortIcalData workEffortIcalDataToBeAdded) throws Exception {

		AddWorkEffortIcalData command = new AddWorkEffortIcalData(workEffortIcalDataToBeAdded);
		WorkEffortIcalData workEffortIcalData = ((WorkEffortIcalDataAdded) Scheduler.execute(command).data()).getAddedWorkEffortIcalData();
		
		if (workEffortIcalData != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortIcalData);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortIcalData could not be created.");
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
	public boolean updateWorkEffortIcalData(HttpServletRequest request) throws Exception {

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

		WorkEffortIcalData workEffortIcalDataToBeUpdated = new WorkEffortIcalData();

		try {
			workEffortIcalDataToBeUpdated = WorkEffortIcalDataMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortIcalData(workEffortIcalDataToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkEffortIcalData(@RequestBody WorkEffortIcalData workEffortIcalDataToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortIcalDataToBeUpdated.setnull(null);

		UpdateWorkEffortIcalData command = new UpdateWorkEffortIcalData(workEffortIcalDataToBeUpdated);

		try {
			if(((WorkEffortIcalDataUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortIcalDataId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortIcalDataId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortIcalDataId", workEffortIcalDataId);
		try {

			Object foundWorkEffortIcalData = findWorkEffortIcalDatasBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortIcalData);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortIcalDataId}")
	public ResponseEntity<Object> deleteWorkEffortIcalDataByIdUpdated(@PathVariable String workEffortIcalDataId) throws Exception {
		DeleteWorkEffortIcalData command = new DeleteWorkEffortIcalData(workEffortIcalDataId);

		try {
			if (((WorkEffortIcalDataDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortIcalData could not be deleted");

	}

}
