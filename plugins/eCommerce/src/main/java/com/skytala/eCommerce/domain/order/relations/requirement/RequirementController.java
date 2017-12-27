package com.skytala.eCommerce.domain.order.relations.requirement;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.AddRequirement;
import com.skytala.eCommerce.domain.order.relations.requirement.command.DeleteRequirement;
import com.skytala.eCommerce.domain.order.relations.requirement.command.UpdateRequirement;
import com.skytala.eCommerce.domain.order.relations.requirement.event.RequirementAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.RequirementDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.RequirementFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.RequirementUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.RequirementMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.Requirement;
import com.skytala.eCommerce.domain.order.relations.requirement.query.FindRequirementsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirements")
public class RequirementController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Requirement
	 * @return a List with the Requirements
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Requirement>> findRequirementsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementsBy query = new FindRequirementsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Requirement> requirements =((RequirementFound) Scheduler.execute(query).data()).getRequirements();

		return ResponseEntity.ok().body(requirements);

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
	public ResponseEntity<Requirement> createRequirement(HttpServletRequest request) throws Exception {

		Requirement requirementToBeAdded = new Requirement();
		try {
			requirementToBeAdded = RequirementMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createRequirement(requirementToBeAdded);

	}

	/**
	 * creates a new Requirement entry in the ofbiz database
	 * 
	 * @param requirementToBeAdded
	 *            the Requirement thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Requirement> createRequirement(@RequestBody Requirement requirementToBeAdded) throws Exception {

		AddRequirement command = new AddRequirement(requirementToBeAdded);
		Requirement requirement = ((RequirementAdded) Scheduler.execute(command).data()).getAddedRequirement();
		
		if (requirement != null) 
			return successful(requirement);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Requirement with the specific Id
	 * 
	 * @param requirementToBeUpdated
	 *            the Requirement thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{requirementId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRequirement(@RequestBody Requirement requirementToBeUpdated,
			@PathVariable String requirementId) throws Exception {

		requirementToBeUpdated.setRequirementId(requirementId);

		UpdateRequirement command = new UpdateRequirement(requirementToBeUpdated);

		try {
			if(((RequirementUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementId}")
	public ResponseEntity<Requirement> findById(@PathVariable String requirementId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementId", requirementId);
		try {

			List<Requirement> foundRequirement = findRequirementsBy(requestParams).getBody();
			if(foundRequirement.size()==1){				return successful(foundRequirement.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementId}")
	public ResponseEntity<String> deleteRequirementByIdUpdated(@PathVariable String requirementId) throws Exception {
		DeleteRequirement command = new DeleteRequirement(requirementId);

		try {
			if (((RequirementDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
