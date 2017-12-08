package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.costCalc;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc.AddWorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc.DeleteWorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.costCalc.UpdateWorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.costCalc.WorkEffortCostCalcMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc.WorkEffortCostCalc;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.costCalc.FindWorkEffortCostCalcsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortCostCalcs")
public class WorkEffortCostCalcController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortCostCalcController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortCostCalc
	 * @return a List with the WorkEffortCostCalcs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortCostCalcsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortCostCalcsBy query = new FindWorkEffortCostCalcsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortCostCalc> workEffortCostCalcs =((WorkEffortCostCalcFound) Scheduler.execute(query).data()).getWorkEffortCostCalcs();

		if (workEffortCostCalcs.size() == 1) {
			return ResponseEntity.ok().body(workEffortCostCalcs.get(0));
		}

		return ResponseEntity.ok().body(workEffortCostCalcs);

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
	public ResponseEntity<Object> createWorkEffortCostCalc(HttpServletRequest request) throws Exception {

		WorkEffortCostCalc workEffortCostCalcToBeAdded = new WorkEffortCostCalc();
		try {
			workEffortCostCalcToBeAdded = WorkEffortCostCalcMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortCostCalc(workEffortCostCalcToBeAdded);

	}

	/**
	 * creates a new WorkEffortCostCalc entry in the ofbiz database
	 * 
	 * @param workEffortCostCalcToBeAdded
	 *            the WorkEffortCostCalc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortCostCalc(@RequestBody WorkEffortCostCalc workEffortCostCalcToBeAdded) throws Exception {

		AddWorkEffortCostCalc command = new AddWorkEffortCostCalc(workEffortCostCalcToBeAdded);
		WorkEffortCostCalc workEffortCostCalc = ((WorkEffortCostCalcAdded) Scheduler.execute(command).data()).getAddedWorkEffortCostCalc();
		
		if (workEffortCostCalc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortCostCalc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortCostCalc could not be created.");
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
	public boolean updateWorkEffortCostCalc(HttpServletRequest request) throws Exception {

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

		WorkEffortCostCalc workEffortCostCalcToBeUpdated = new WorkEffortCostCalc();

		try {
			workEffortCostCalcToBeUpdated = WorkEffortCostCalcMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortCostCalc(workEffortCostCalcToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortCostCalc with the specific Id
	 * 
	 * @param workEffortCostCalcToBeUpdated
	 *            the WorkEffortCostCalc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortCostCalc(@RequestBody WorkEffortCostCalc workEffortCostCalcToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortCostCalcToBeUpdated.setnull(null);

		UpdateWorkEffortCostCalc command = new UpdateWorkEffortCostCalc(workEffortCostCalcToBeUpdated);

		try {
			if(((WorkEffortCostCalcUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortCostCalcId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortCostCalcId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortCostCalcId", workEffortCostCalcId);
		try {

			Object foundWorkEffortCostCalc = findWorkEffortCostCalcsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortCostCalc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortCostCalcId}")
	public ResponseEntity<Object> deleteWorkEffortCostCalcByIdUpdated(@PathVariable String workEffortCostCalcId) throws Exception {
		DeleteWorkEffortCostCalc command = new DeleteWorkEffortCostCalc(workEffortCostCalcId);

		try {
			if (((WorkEffortCostCalcDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortCostCalc could not be deleted");

	}

}
