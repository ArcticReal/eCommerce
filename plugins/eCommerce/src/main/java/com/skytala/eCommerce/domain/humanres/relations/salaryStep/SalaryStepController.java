package com.skytala.eCommerce.domain.humanres.relations.salaryStep;

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
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.command.AddSalaryStep;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.command.DeleteSalaryStep;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.command.UpdateSalaryStep;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepAdded;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepDeleted;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepFound;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepUpdated;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.mapper.SalaryStepMapper;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.query.FindSalaryStepsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/salarySteps")
public class SalaryStepController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalaryStepController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalaryStep
	 * @return a List with the SalarySteps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalaryStep>> findSalaryStepsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalaryStepsBy query = new FindSalaryStepsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalaryStep> salarySteps =((SalaryStepFound) Scheduler.execute(query).data()).getSalarySteps();

		return ResponseEntity.ok().body(salarySteps);

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
	public ResponseEntity<SalaryStep> createSalaryStep(HttpServletRequest request) throws Exception {

		SalaryStep salaryStepToBeAdded = new SalaryStep();
		try {
			salaryStepToBeAdded = SalaryStepMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSalaryStep(salaryStepToBeAdded);

	}

	/**
	 * creates a new SalaryStep entry in the ofbiz database
	 * 
	 * @param salaryStepToBeAdded
	 *            the SalaryStep thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalaryStep> createSalaryStep(@RequestBody SalaryStep salaryStepToBeAdded) throws Exception {

		AddSalaryStep command = new AddSalaryStep(salaryStepToBeAdded);
		SalaryStep salaryStep = ((SalaryStepAdded) Scheduler.execute(command).data()).getAddedSalaryStep();
		
		if (salaryStep != null) 
			return successful(salaryStep);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalaryStep with the specific Id
	 * 
	 * @param salaryStepToBeUpdated
	 *            the SalaryStep thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{salaryStepSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalaryStep(@RequestBody SalaryStep salaryStepToBeUpdated,
			@PathVariable String salaryStepSeqId) throws Exception {

		salaryStepToBeUpdated.setSalaryStepSeqId(salaryStepSeqId);

		UpdateSalaryStep command = new UpdateSalaryStep(salaryStepToBeUpdated);

		try {
			if(((SalaryStepUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salaryStepId}")
	public ResponseEntity<SalaryStep> findById(@PathVariable String salaryStepId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salaryStepId", salaryStepId);
		try {

			List<SalaryStep> foundSalaryStep = findSalaryStepsBy(requestParams).getBody();
			if(foundSalaryStep.size()==1){				return successful(foundSalaryStep.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salaryStepId}")
	public ResponseEntity<String> deleteSalaryStepByIdUpdated(@PathVariable String salaryStepId) throws Exception {
		DeleteSalaryStep command = new DeleteSalaryStep(salaryStepId);

		try {
			if (((SalaryStepDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
