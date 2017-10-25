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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/salarySteps")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalaryStepsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalaryStepsBy query = new FindSalaryStepsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalaryStep> salarySteps =((SalaryStepFound) Scheduler.execute(query).data()).getSalarySteps();

		if (salarySteps.size() == 1) {
			return ResponseEntity.ok().body(salarySteps.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSalaryStep(HttpServletRequest request) throws Exception {

		SalaryStep salaryStepToBeAdded = new SalaryStep();
		try {
			salaryStepToBeAdded = SalaryStepMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createSalaryStep(@RequestBody SalaryStep salaryStepToBeAdded) throws Exception {

		AddSalaryStep command = new AddSalaryStep(salaryStepToBeAdded);
		SalaryStep salaryStep = ((SalaryStepAdded) Scheduler.execute(command).data()).getAddedSalaryStep();
		
		if (salaryStep != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salaryStep);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalaryStep could not be created.");
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
	public boolean updateSalaryStep(HttpServletRequest request) throws Exception {

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

		SalaryStep salaryStepToBeUpdated = new SalaryStep();

		try {
			salaryStepToBeUpdated = SalaryStepMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalaryStep(salaryStepToBeUpdated, salaryStepToBeUpdated.getSalaryStepSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSalaryStep(@RequestBody SalaryStep salaryStepToBeUpdated,
			@PathVariable String salaryStepSeqId) throws Exception {

		salaryStepToBeUpdated.setSalaryStepSeqId(salaryStepSeqId);

		UpdateSalaryStep command = new UpdateSalaryStep(salaryStepToBeUpdated);

		try {
			if(((SalaryStepUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salaryStepId}")
	public ResponseEntity<Object> findById(@PathVariable String salaryStepId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salaryStepId", salaryStepId);
		try {

			Object foundSalaryStep = findSalaryStepsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalaryStep);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salaryStepId}")
	public ResponseEntity<Object> deleteSalaryStepByIdUpdated(@PathVariable String salaryStepId) throws Exception {
		DeleteSalaryStep command = new DeleteSalaryStep(salaryStepId);

		try {
			if (((SalaryStepDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalaryStep could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/salaryStep/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
