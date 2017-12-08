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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/requirementBudgetAllocations")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRequirementBudgetAllocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementBudgetAllocationsBy query = new FindRequirementBudgetAllocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementBudgetAllocation> requirementBudgetAllocations =((RequirementBudgetAllocationFound) Scheduler.execute(query).data()).getRequirementBudgetAllocations();

		if (requirementBudgetAllocations.size() == 1) {
			return ResponseEntity.ok().body(requirementBudgetAllocations.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createRequirementBudgetAllocation(HttpServletRequest request) throws Exception {

		RequirementBudgetAllocation requirementBudgetAllocationToBeAdded = new RequirementBudgetAllocation();
		try {
			requirementBudgetAllocationToBeAdded = RequirementBudgetAllocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createRequirementBudgetAllocation(@RequestBody RequirementBudgetAllocation requirementBudgetAllocationToBeAdded) throws Exception {

		AddRequirementBudgetAllocation command = new AddRequirementBudgetAllocation(requirementBudgetAllocationToBeAdded);
		RequirementBudgetAllocation requirementBudgetAllocation = ((RequirementBudgetAllocationAdded) Scheduler.execute(command).data()).getAddedRequirementBudgetAllocation();
		
		if (requirementBudgetAllocation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(requirementBudgetAllocation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RequirementBudgetAllocation could not be created.");
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
	public boolean updateRequirementBudgetAllocation(HttpServletRequest request) throws Exception {

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

		RequirementBudgetAllocation requirementBudgetAllocationToBeUpdated = new RequirementBudgetAllocation();

		try {
			requirementBudgetAllocationToBeUpdated = RequirementBudgetAllocationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRequirementBudgetAllocation(requirementBudgetAllocationToBeUpdated, requirementBudgetAllocationToBeUpdated.getBudgetItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateRequirementBudgetAllocation(@RequestBody RequirementBudgetAllocation requirementBudgetAllocationToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		requirementBudgetAllocationToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdateRequirementBudgetAllocation command = new UpdateRequirementBudgetAllocation(requirementBudgetAllocationToBeUpdated);

		try {
			if(((RequirementBudgetAllocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{requirementBudgetAllocationId}")
	public ResponseEntity<Object> findById(@PathVariable String requirementBudgetAllocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementBudgetAllocationId", requirementBudgetAllocationId);
		try {

			Object foundRequirementBudgetAllocation = findRequirementBudgetAllocationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRequirementBudgetAllocation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{requirementBudgetAllocationId}")
	public ResponseEntity<Object> deleteRequirementBudgetAllocationByIdUpdated(@PathVariable String requirementBudgetAllocationId) throws Exception {
		DeleteRequirementBudgetAllocation command = new DeleteRequirementBudgetAllocation(requirementBudgetAllocationId);

		try {
			if (((RequirementBudgetAllocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RequirementBudgetAllocation could not be deleted");

	}

}
