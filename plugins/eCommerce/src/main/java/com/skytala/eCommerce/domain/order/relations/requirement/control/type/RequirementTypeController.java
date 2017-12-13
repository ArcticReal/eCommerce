package com.skytala.eCommerce.domain.order.relations.requirement.control.type;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.type.AddRequirementType;
import com.skytala.eCommerce.domain.order.relations.requirement.command.type.DeleteRequirementType;
import com.skytala.eCommerce.domain.order.relations.requirement.command.type.UpdateRequirementType;
import com.skytala.eCommerce.domain.order.relations.requirement.event.type.RequirementTypeAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.type.RequirementTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.type.RequirementTypeFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.type.RequirementTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.type.RequirementTypeMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.type.RequirementType;
import com.skytala.eCommerce.domain.order.relations.requirement.query.type.FindRequirementTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/requirement/requirementTypes")
public class RequirementTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementType
	 * @return a List with the RequirementTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findRequirementTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementTypesBy query = new FindRequirementTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementType> requirementTypes =((RequirementTypeFound) Scheduler.execute(query).data()).getRequirementTypes();

		if (requirementTypes.size() == 1) {
			return ResponseEntity.ok().body(requirementTypes.get(0));
		}

		return ResponseEntity.ok().body(requirementTypes);

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
	public ResponseEntity<Object> createRequirementType(HttpServletRequest request) throws Exception {

		RequirementType requirementTypeToBeAdded = new RequirementType();
		try {
			requirementTypeToBeAdded = RequirementTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRequirementType(requirementTypeToBeAdded);

	}

	/**
	 * creates a new RequirementType entry in the ofbiz database
	 * 
	 * @param requirementTypeToBeAdded
	 *            the RequirementType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRequirementType(@RequestBody RequirementType requirementTypeToBeAdded) throws Exception {

		AddRequirementType command = new AddRequirementType(requirementTypeToBeAdded);
		RequirementType requirementType = ((RequirementTypeAdded) Scheduler.execute(command).data()).getAddedRequirementType();
		
		if (requirementType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(requirementType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RequirementType could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateRequirementType(HttpServletRequest request) throws Exception {

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

		RequirementType requirementTypeToBeUpdated = new RequirementType();

		try {
			requirementTypeToBeUpdated = RequirementTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRequirementType(requirementTypeToBeUpdated, requirementTypeToBeUpdated.getRequirementTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RequirementType with the specific Id
	 * 
	 * @param requirementTypeToBeUpdated
	 *            the RequirementType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{requirementTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRequirementType(@RequestBody RequirementType requirementTypeToBeUpdated,
			@PathVariable String requirementTypeId) throws Exception {

		requirementTypeToBeUpdated.setRequirementTypeId(requirementTypeId);

		UpdateRequirementType command = new UpdateRequirementType(requirementTypeToBeUpdated);

		try {
			if(((RequirementTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{requirementTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String requirementTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementTypeId", requirementTypeId);
		try {

			Object foundRequirementType = findRequirementTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRequirementType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{requirementTypeId}")
	public ResponseEntity<Object> deleteRequirementTypeByIdUpdated(@PathVariable String requirementTypeId) throws Exception {
		DeleteRequirementType command = new DeleteRequirementType(requirementTypeId);

		try {
			if (((RequirementTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RequirementType could not be deleted");

	}

}
