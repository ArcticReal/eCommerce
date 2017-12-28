package com.skytala.eCommerce.domain.humanres.relations.employment.control.appSourceType;

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
import com.skytala.eCommerce.domain.humanres.relations.employment.command.appSourceType.AddEmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employment.command.appSourceType.DeleteEmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employment.command.appSourceType.UpdateEmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType.EmploymentAppSourceTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType.EmploymentAppSourceTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType.EmploymentAppSourceTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType.EmploymentAppSourceTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.appSourceType.EmploymentAppSourceTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.appSourceType.EmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employment.query.appSourceType.FindEmploymentAppSourceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/employment/employmentAppSourceTypes")
public class EmploymentAppSourceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmploymentAppSourceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmploymentAppSourceType
	 * @return a List with the EmploymentAppSourceTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmploymentAppSourceType>> findEmploymentAppSourceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmploymentAppSourceTypesBy query = new FindEmploymentAppSourceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmploymentAppSourceType> employmentAppSourceTypes =((EmploymentAppSourceTypeFound) Scheduler.execute(query).data()).getEmploymentAppSourceTypes();

		return ResponseEntity.ok().body(employmentAppSourceTypes);

	}

	/**
	 * creates a new EmploymentAppSourceType entry in the ofbiz database
	 * 
	 * @param employmentAppSourceTypeToBeAdded
	 *            the EmploymentAppSourceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmploymentAppSourceType> createEmploymentAppSourceType(@RequestBody EmploymentAppSourceType employmentAppSourceTypeToBeAdded) throws Exception {

		AddEmploymentAppSourceType command = new AddEmploymentAppSourceType(employmentAppSourceTypeToBeAdded);
		EmploymentAppSourceType employmentAppSourceType = ((EmploymentAppSourceTypeAdded) Scheduler.execute(command).data()).getAddedEmploymentAppSourceType();
		
		if (employmentAppSourceType != null) 
			return successful(employmentAppSourceType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmploymentAppSourceType with the specific Id
	 * 
	 * @param employmentAppSourceTypeToBeUpdated
	 *            the EmploymentAppSourceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{employmentAppSourceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmploymentAppSourceType(@RequestBody EmploymentAppSourceType employmentAppSourceTypeToBeUpdated,
			@PathVariable String employmentAppSourceTypeId) throws Exception {

		employmentAppSourceTypeToBeUpdated.setEmploymentAppSourceTypeId(employmentAppSourceTypeId);

		UpdateEmploymentAppSourceType command = new UpdateEmploymentAppSourceType(employmentAppSourceTypeToBeUpdated);

		try {
			if(((EmploymentAppSourceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{employmentAppSourceTypeId}")
	public ResponseEntity<EmploymentAppSourceType> findById(@PathVariable String employmentAppSourceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("employmentAppSourceTypeId", employmentAppSourceTypeId);
		try {

			List<EmploymentAppSourceType> foundEmploymentAppSourceType = findEmploymentAppSourceTypesBy(requestParams).getBody();
			if(foundEmploymentAppSourceType.size()==1){				return successful(foundEmploymentAppSourceType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{employmentAppSourceTypeId}")
	public ResponseEntity<String> deleteEmploymentAppSourceTypeByIdUpdated(@PathVariable String employmentAppSourceTypeId) throws Exception {
		DeleteEmploymentAppSourceType command = new DeleteEmploymentAppSourceType(employmentAppSourceTypeId);

		try {
			if (((EmploymentAppSourceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
