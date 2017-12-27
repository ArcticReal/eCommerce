package com.skytala.eCommerce.domain.humanres.relations.employment;

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
import com.skytala.eCommerce.domain.humanres.relations.employment.command.AddEmployment;
import com.skytala.eCommerce.domain.humanres.relations.employment.command.DeleteEmployment;
import com.skytala.eCommerce.domain.humanres.relations.employment.command.UpdateEmployment;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentAdded;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentDeleted;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentFound;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentUpdated;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.EmploymentMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
import com.skytala.eCommerce.domain.humanres.relations.employment.query.FindEmploymentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/employments")
public class EmploymentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmploymentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Employment
	 * @return a List with the Employments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Employment>> findEmploymentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmploymentsBy query = new FindEmploymentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Employment> employments =((EmploymentFound) Scheduler.execute(query).data()).getEmployments();

		return ResponseEntity.ok().body(employments);

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
	public ResponseEntity<Employment> createEmployment(HttpServletRequest request) throws Exception {

		Employment employmentToBeAdded = new Employment();
		try {
			employmentToBeAdded = EmploymentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createEmployment(employmentToBeAdded);

	}

	/**
	 * creates a new Employment entry in the ofbiz database
	 * 
	 * @param employmentToBeAdded
	 *            the Employment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Employment> createEmployment(@RequestBody Employment employmentToBeAdded) throws Exception {

		AddEmployment command = new AddEmployment(employmentToBeAdded);
		Employment employment = ((EmploymentAdded) Scheduler.execute(command).data()).getAddedEmployment();
		
		if (employment != null) 
			return successful(employment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Employment with the specific Id
	 * 
	 * @param employmentToBeUpdated
	 *            the Employment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmployment(@RequestBody Employment employmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		employmentToBeUpdated.setnull(null);

		UpdateEmployment command = new UpdateEmployment(employmentToBeUpdated);

		try {
			if(((EmploymentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{employmentId}")
	public ResponseEntity<Employment> findById(@PathVariable String employmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("employmentId", employmentId);
		try {

			List<Employment> foundEmployment = findEmploymentsBy(requestParams).getBody();
			if(foundEmployment.size()==1){				return successful(foundEmployment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{employmentId}")
	public ResponseEntity<String> deleteEmploymentByIdUpdated(@PathVariable String employmentId) throws Exception {
		DeleteEmployment command = new DeleteEmployment(employmentId);

		try {
			if (((EmploymentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
