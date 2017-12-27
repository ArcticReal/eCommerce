package com.skytala.eCommerce.domain.order.relations.requirement.control.budgetAllocation;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.budgetAllocation.AddRequirementBudgetAllocation;
import com.skytala.eCommerce.domain.order.relations.requirement.command.budgetAllocation.DeleteRequirementBudgetAllocation;
import com.skytala.eCommerce.domain.order.relations.requirement.command.budgetAllocation.UpdateRequirementBudgetAllocation;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.budgetAllocation.RequirementBudgetAllocationMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;
import com.skytala.eCommerce.domain.order.relations.requirement.query.budgetAllocation.FindRequirementBudgetAllocationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirement/requirementBudgetAllocations")
public class RequirementBudgetAllocationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementBudgetAllocationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementBudgetAllocation
	 * @return a List with the RequirementBudgetAllocations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RequirementBudgetAllocation>> findRequirementBudgetAllocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementBudgetAllocationsBy query = new FindRequirementBudgetAllocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementBudgetAllocation> requirementBudgetAllocations =((RequirementBudgetAllocationFound) Scheduler.execute(query).data()).getRequirementBudgetAllocations();

		return ResponseEntity.ok().body(requirementBudgetAllocations);

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
	public ResponseEntity<RequirementBudgetAllocation> createRequirementBudgetAllocation(HttpServletRequest request) throws Exception {

		RequirementBudgetAllocation requirementBudgetAllocationToBeAdded = new RequirementBudgetAllocation();
		try {
			requirementBudgetAllocationToBeAdded = RequirementBudgetAllocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createRequirementBudgetAllocation(requirementBudgetAllocationToBeAdded);

	}

	/**
	 * creates a new RequirementBudgetAllocation entry in the ofbiz database
	 * 
	 * @param requirementBudgetAllocationToBeAdded
	 *            the RequirementBudgetAllocation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequirementBudgetAllocation> createRequirementBudgetAllocation(@RequestBody RequirementBudgetAllocation requirementBudgetAllocationToBeAdded) throws Exception {

		AddRequirementBudgetAllocation command = new AddRequirementBudgetAllocation(requirementBudgetAllocationToBeAdded);
		RequirementBudgetAllocation requirementBudgetAllocation = ((RequirementBudgetAllocationAdded) Scheduler.execute(command).data()).getAddedRequirementBudgetAllocation();
		
		if (requirementBudgetAllocation != null) 
			return successful(requirementBudgetAllocation);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RequirementBudgetAllocation with the specific Id
	 * 
	 * @param requirementBudgetAllocationToBeUpdated
	 *            the RequirementBudgetAllocation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRequirementBudgetAllocation(@RequestBody RequirementBudgetAllocation requirementBudgetAllocationToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		requirementBudgetAllocationToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdateRequirementBudgetAllocation command = new UpdateRequirementBudgetAllocation(requirementBudgetAllocationToBeUpdated);

		try {
			if(((RequirementBudgetAllocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementBudgetAllocationId}")
	public ResponseEntity<RequirementBudgetAllocation> findById(@PathVariable String requirementBudgetAllocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementBudgetAllocationId", requirementBudgetAllocationId);
		try {

			List<RequirementBudgetAllocation> foundRequirementBudgetAllocation = findRequirementBudgetAllocationsBy(requestParams).getBody();
			if(foundRequirementBudgetAllocation.size()==1){				return successful(foundRequirementBudgetAllocation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementBudgetAllocationId}")
	public ResponseEntity<String> deleteRequirementBudgetAllocationByIdUpdated(@PathVariable String requirementBudgetAllocationId) throws Exception {
		DeleteRequirementBudgetAllocation command = new DeleteRequirementBudgetAllocation(requirementBudgetAllocationId);

		try {
			if (((RequirementBudgetAllocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
