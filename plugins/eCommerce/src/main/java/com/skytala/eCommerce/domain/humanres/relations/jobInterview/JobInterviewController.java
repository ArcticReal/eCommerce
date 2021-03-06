package com.skytala.eCommerce.domain.humanres.relations.jobInterview;

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
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.AddJobInterview;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.DeleteJobInterview;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.UpdateJobInterview;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.JobInterviewAdded;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.JobInterviewDeleted;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.JobInterviewFound;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.JobInterviewUpdated;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.mapper.JobInterviewMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.JobInterview;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.query.FindJobInterviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/jobInterviews")
public class JobInterviewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public JobInterviewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a JobInterview
	 * @return a List with the JobInterviews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<JobInterview>> findJobInterviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindJobInterviewsBy query = new FindJobInterviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<JobInterview> jobInterviews =((JobInterviewFound) Scheduler.execute(query).data()).getJobInterviews();

		return ResponseEntity.ok().body(jobInterviews);

	}

	/**
	 * creates a new JobInterview entry in the ofbiz database
	 * 
	 * @param jobInterviewToBeAdded
	 *            the JobInterview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<JobInterview> createJobInterview(@RequestBody JobInterview jobInterviewToBeAdded) throws Exception {

		AddJobInterview command = new AddJobInterview(jobInterviewToBeAdded);
		JobInterview jobInterview = ((JobInterviewAdded) Scheduler.execute(command).data()).getAddedJobInterview();
		
		if (jobInterview != null) 
			return successful(jobInterview);
		else 
			return conflict(null);
	}

	/**
	 * Updates the JobInterview with the specific Id
	 * 
	 * @param jobInterviewToBeUpdated
	 *            the JobInterview thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{jobInterviewId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateJobInterview(@RequestBody JobInterview jobInterviewToBeUpdated,
			@PathVariable String jobInterviewId) throws Exception {

		jobInterviewToBeUpdated.setJobInterviewId(jobInterviewId);

		UpdateJobInterview command = new UpdateJobInterview(jobInterviewToBeUpdated);

		try {
			if(((JobInterviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{jobInterviewId}")
	public ResponseEntity<JobInterview> findById(@PathVariable String jobInterviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("jobInterviewId", jobInterviewId);
		try {

			List<JobInterview> foundJobInterview = findJobInterviewsBy(requestParams).getBody();
			if(foundJobInterview.size()==1){				return successful(foundJobInterview.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{jobInterviewId}")
	public ResponseEntity<String> deleteJobInterviewByIdUpdated(@PathVariable String jobInterviewId) throws Exception {
		DeleteJobInterview command = new DeleteJobInterview(jobInterviewId);

		try {
			if (((JobInterviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
