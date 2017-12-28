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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<JobRequisition>> findJobRequisitionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindJobRequisitionsBy query = new FindJobRequisitionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<JobRequisition> jobRequisitions =((JobRequisitionFound) Scheduler.execute(query).data()).getJobRequisitions();

		return ResponseEntity.ok().body(jobRequisitions);

	}

	/**
	 * creates a new JobRequisition entry in the ofbiz database
	 * 
	 * @param jobRequisitionToBeAdded
	 *            the JobRequisition thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<JobRequisition> createJobRequisition(@RequestBody JobRequisition jobRequisitionToBeAdded) throws Exception {

		AddJobRequisition command = new AddJobRequisition(jobRequisitionToBeAdded);
		JobRequisition jobRequisition = ((JobRequisitionAdded) Scheduler.execute(command).data()).getAddedJobRequisition();
		
		if (jobRequisition != null) 
			return successful(jobRequisition);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateJobRequisition(@RequestBody JobRequisition jobRequisitionToBeUpdated,
			@PathVariable String jobRequisitionId) throws Exception {

		jobRequisitionToBeUpdated.setJobRequisitionId(jobRequisitionId);

		UpdateJobRequisition command = new UpdateJobRequisition(jobRequisitionToBeUpdated);

		try {
			if(((JobRequisitionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{jobRequisitionId}")
	public ResponseEntity<JobRequisition> findById(@PathVariable String jobRequisitionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("jobRequisitionId", jobRequisitionId);
		try {

			List<JobRequisition> foundJobRequisition = findJobRequisitionsBy(requestParams).getBody();
			if(foundJobRequisition.size()==1){				return successful(foundJobRequisition.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{jobRequisitionId}")
	public ResponseEntity<String> deleteJobRequisitionByIdUpdated(@PathVariable String jobRequisitionId) throws Exception {
		DeleteJobRequisition command = new DeleteJobRequisition(jobRequisitionId);

		try {
			if (((JobRequisitionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
