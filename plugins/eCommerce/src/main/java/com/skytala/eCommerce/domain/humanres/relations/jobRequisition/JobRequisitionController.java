package com.skytala.eCommerce.domain.humanres.relations.jobRequisition;

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
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.command.AddJobRequisition;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.command.DeleteJobRequisition;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.command.UpdateJobRequisition;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event.JobRequisitionAdded;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event.JobRequisitionDeleted;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event.JobRequisitionFound;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event.JobRequisitionUpdated;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.mapper.JobRequisitionMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.query.FindJobRequisitionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/jobRequisitions")
public class JobRequisitionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public JobRequisitionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a JobRequisition
	 * @return a List with the JobRequisitions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findJobRequisitionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindJobRequisitionsBy query = new FindJobRequisitionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<JobRequisition> jobRequisitions =((JobRequisitionFound) Scheduler.execute(query).data()).getJobRequisitions();

		if (jobRequisitions.size() == 1) {
			return ResponseEntity.ok().body(jobRequisitions.get(0));
		}

		return ResponseEntity.ok().body(jobRequisitions);

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
	public ResponseEntity<Object> createJobRequisition(HttpServletRequest request) throws Exception {

		JobRequisition jobRequisitionToBeAdded = new JobRequisition();
		try {
			jobRequisitionToBeAdded = JobRequisitionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createJobRequisition(jobRequisitionToBeAdded);

	}

	/**
	 * creates a new JobRequisition entry in the ofbiz database
	 * 
	 * @param jobRequisitionToBeAdded
	 *            the JobRequisition thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createJobRequisition(@RequestBody JobRequisition jobRequisitionToBeAdded) throws Exception {

		AddJobRequisition command = new AddJobRequisition(jobRequisitionToBeAdded);
		JobRequisition jobRequisition = ((JobRequisitionAdded) Scheduler.execute(command).data()).getAddedJobRequisition();
		
		if (jobRequisition != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(jobRequisition);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("JobRequisition could not be created.");
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
	public boolean updateJobRequisition(HttpServletRequest request) throws Exception {

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

		JobRequisition jobRequisitionToBeUpdated = new JobRequisition();

		try {
			jobRequisitionToBeUpdated = JobRequisitionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateJobRequisition(jobRequisitionToBeUpdated, jobRequisitionToBeUpdated.getJobRequisitionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the JobRequisition with the specific Id
	 * 
	 * @param jobRequisitionToBeUpdated
	 *            the JobRequisition thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{jobRequisitionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateJobRequisition(@RequestBody JobRequisition jobRequisitionToBeUpdated,
			@PathVariable String jobRequisitionId) throws Exception {

		jobRequisitionToBeUpdated.setJobRequisitionId(jobRequisitionId);

		UpdateJobRequisition command = new UpdateJobRequisition(jobRequisitionToBeUpdated);

		try {
			if(((JobRequisitionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{jobRequisitionId}")
	public ResponseEntity<Object> findById(@PathVariable String jobRequisitionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("jobRequisitionId", jobRequisitionId);
		try {

			Object foundJobRequisition = findJobRequisitionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundJobRequisition);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{jobRequisitionId}")
	public ResponseEntity<Object> deleteJobRequisitionByIdUpdated(@PathVariable String jobRequisitionId) throws Exception {
		DeleteJobRequisition command = new DeleteJobRequisition(jobRequisitionId);

		try {
			if (((JobRequisitionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("JobRequisition could not be deleted");

	}

}
