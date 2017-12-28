package com.skytala.eCommerce.domain.order.relations.custRequest.control.requirement;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.requirement.AddRequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.requirement.DeleteRequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.requirement.UpdateRequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement.RequirementCustRequestAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement.RequirementCustRequestDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement.RequirementCustRequestFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement.RequirementCustRequestUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.requirement.RequirementCustRequestMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.requirement.RequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.requirement.FindRequirementCustRequestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/requirementCustRequests")
public class RequirementCustRequestController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementCustRequestController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementCustRequest
	 * @return a List with the RequirementCustRequests
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RequirementCustRequest>> findRequirementCustRequestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementCustRequestsBy query = new FindRequirementCustRequestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementCustRequest> requirementCustRequests =((RequirementCustRequestFound) Scheduler.execute(query).data()).getRequirementCustRequests();

		return ResponseEntity.ok().body(requirementCustRequests);

	}

	/**
	 * creates a new RequirementCustRequest entry in the ofbiz database
	 * 
	 * @param requirementCustRequestToBeAdded
	 *            the RequirementCustRequest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequirementCustRequest> createRequirementCustRequest(@RequestBody RequirementCustRequest requirementCustRequestToBeAdded) throws Exception {

		AddRequirementCustRequest command = new AddRequirementCustRequest(requirementCustRequestToBeAdded);
		RequirementCustRequest requirementCustRequest = ((RequirementCustRequestAdded) Scheduler.execute(command).data()).getAddedRequirementCustRequest();
		
		if (requirementCustRequest != null) 
			return successful(requirementCustRequest);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RequirementCustRequest with the specific Id
	 * 
	 * @param requirementCustRequestToBeUpdated
	 *            the RequirementCustRequest thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRequirementCustRequest(@RequestBody RequirementCustRequest requirementCustRequestToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		requirementCustRequestToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateRequirementCustRequest command = new UpdateRequirementCustRequest(requirementCustRequestToBeUpdated);

		try {
			if(((RequirementCustRequestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementCustRequestId}")
	public ResponseEntity<RequirementCustRequest> findById(@PathVariable String requirementCustRequestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementCustRequestId", requirementCustRequestId);
		try {

			List<RequirementCustRequest> foundRequirementCustRequest = findRequirementCustRequestsBy(requestParams).getBody();
			if(foundRequirementCustRequest.size()==1){				return successful(foundRequirementCustRequest.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementCustRequestId}")
	public ResponseEntity<String> deleteRequirementCustRequestByIdUpdated(@PathVariable String requirementCustRequestId) throws Exception {
		DeleteRequirementCustRequest command = new DeleteRequirementCustRequest(requirementCustRequestId);

		try {
			if (((RequirementCustRequestDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
