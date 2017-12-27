package com.skytala.eCommerce.domain.order.relations.requirement.control.status;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.status.AddRequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirement.command.status.DeleteRequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirement.command.status.UpdateRequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirement.event.status.RequirementStatusAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.status.RequirementStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.status.RequirementStatusFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.status.RequirementStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.status.RequirementStatusMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirement.query.status.FindRequirementStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirement/requirementStatuss")
public class RequirementStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementStatus
	 * @return a List with the RequirementStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RequirementStatus>> findRequirementStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementStatussBy query = new FindRequirementStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementStatus> requirementStatuss =((RequirementStatusFound) Scheduler.execute(query).data()).getRequirementStatuss();

		return ResponseEntity.ok().body(requirementStatuss);

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
	public ResponseEntity<RequirementStatus> createRequirementStatus(HttpServletRequest request) throws Exception {

		RequirementStatus requirementStatusToBeAdded = new RequirementStatus();
		try {
			requirementStatusToBeAdded = RequirementStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createRequirementStatus(requirementStatusToBeAdded);

	}

	/**
	 * creates a new RequirementStatus entry in the ofbiz database
	 * 
	 * @param requirementStatusToBeAdded
	 *            the RequirementStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequirementStatus> createRequirementStatus(@RequestBody RequirementStatus requirementStatusToBeAdded) throws Exception {

		AddRequirementStatus command = new AddRequirementStatus(requirementStatusToBeAdded);
		RequirementStatus requirementStatus = ((RequirementStatusAdded) Scheduler.execute(command).data()).getAddedRequirementStatus();
		
		if (requirementStatus != null) 
			return successful(requirementStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RequirementStatus with the specific Id
	 * 
	 * @param requirementStatusToBeUpdated
	 *            the RequirementStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRequirementStatus(@RequestBody RequirementStatus requirementStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		requirementStatusToBeUpdated.setnull(null);

		UpdateRequirementStatus command = new UpdateRequirementStatus(requirementStatusToBeUpdated);

		try {
			if(((RequirementStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementStatusId}")
	public ResponseEntity<RequirementStatus> findById(@PathVariable String requirementStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementStatusId", requirementStatusId);
		try {

			List<RequirementStatus> foundRequirementStatus = findRequirementStatussBy(requestParams).getBody();
			if(foundRequirementStatus.size()==1){				return successful(foundRequirementStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementStatusId}")
	public ResponseEntity<String> deleteRequirementStatusByIdUpdated(@PathVariable String requirementStatusId) throws Exception {
		DeleteRequirementStatus command = new DeleteRequirementStatus(requirementStatusId);

		try {
			if (((RequirementStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
