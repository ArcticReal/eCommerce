package com.skytala.eCommerce.domain.humanres.relations.jobInterview.control.type;

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
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.type.AddJobInterviewType;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.type.DeleteJobInterviewType;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.type.UpdateJobInterviewType;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type.JobInterviewTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type.JobInterviewTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type.JobInterviewTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type.JobInterviewTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.mapper.type.JobInterviewTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.query.type.FindJobInterviewTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/jobInterview/jobInterviewTypes")
public class JobInterviewTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public JobInterviewTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a JobInterviewType
	 * @return a List with the JobInterviewTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<JobInterviewType>> findJobInterviewTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindJobInterviewTypesBy query = new FindJobInterviewTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<JobInterviewType> jobInterviewTypes =((JobInterviewTypeFound) Scheduler.execute(query).data()).getJobInterviewTypes();

		return ResponseEntity.ok().body(jobInterviewTypes);

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
	public ResponseEntity<JobInterviewType> createJobInterviewType(HttpServletRequest request) throws Exception {

		JobInterviewType jobInterviewTypeToBeAdded = new JobInterviewType();
		try {
			jobInterviewTypeToBeAdded = JobInterviewTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createJobInterviewType(jobInterviewTypeToBeAdded);

	}

	/**
	 * creates a new JobInterviewType entry in the ofbiz database
	 * 
	 * @param jobInterviewTypeToBeAdded
	 *            the JobInterviewType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<JobInterviewType> createJobInterviewType(@RequestBody JobInterviewType jobInterviewTypeToBeAdded) throws Exception {

		AddJobInterviewType command = new AddJobInterviewType(jobInterviewTypeToBeAdded);
		JobInterviewType jobInterviewType = ((JobInterviewTypeAdded) Scheduler.execute(command).data()).getAddedJobInterviewType();
		
		if (jobInterviewType != null) 
			return successful(jobInterviewType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the JobInterviewType with the specific Id
	 * 
	 * @param jobInterviewTypeToBeUpdated
	 *            the JobInterviewType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{jobInterviewTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateJobInterviewType(@RequestBody JobInterviewType jobInterviewTypeToBeUpdated,
			@PathVariable String jobInterviewTypeId) throws Exception {

		jobInterviewTypeToBeUpdated.setJobInterviewTypeId(jobInterviewTypeId);

		UpdateJobInterviewType command = new UpdateJobInterviewType(jobInterviewTypeToBeUpdated);

		try {
			if(((JobInterviewTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{jobInterviewTypeId}")
	public ResponseEntity<JobInterviewType> findById(@PathVariable String jobInterviewTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("jobInterviewTypeId", jobInterviewTypeId);
		try {

			List<JobInterviewType> foundJobInterviewType = findJobInterviewTypesBy(requestParams).getBody();
			if(foundJobInterviewType.size()==1){				return successful(foundJobInterviewType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{jobInterviewTypeId}")
	public ResponseEntity<String> deleteJobInterviewTypeByIdUpdated(@PathVariable String jobInterviewTypeId) throws Exception {
		DeleteJobInterviewType command = new DeleteJobInterviewType(jobInterviewTypeId);

		try {
			if (((JobInterviewTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
