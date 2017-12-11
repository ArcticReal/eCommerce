package com.skytala.eCommerce.domain.accounting.relations.deduction;

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
import com.skytala.eCommerce.domain.accounting.relations.deduction.command.AddDeduction;
import com.skytala.eCommerce.domain.accounting.relations.deduction.command.DeleteDeduction;
import com.skytala.eCommerce.domain.accounting.relations.deduction.command.UpdateDeduction;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.DeductionAdded;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.DeductionDeleted;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.DeductionFound;
import com.skytala.eCommerce.domain.accounting.relations.deduction.event.DeductionUpdated;
import com.skytala.eCommerce.domain.accounting.relations.deduction.mapper.DeductionMapper;
import com.skytala.eCommerce.domain.accounting.relations.deduction.model.Deduction;
import com.skytala.eCommerce.domain.accounting.relations.deduction.query.FindDeductionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/deductions")
public class DeductionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DeductionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Deduction
	 * @return a List with the Deductions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDeductionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeductionsBy query = new FindDeductionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Deduction> deductions =((DeductionFound) Scheduler.execute(query).data()).getDeductions();

		if (deductions.size() == 1) {
			return ResponseEntity.ok().body(deductions.get(0));
		}

		return ResponseEntity.ok().body(deductions);

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
	public ResponseEntity<Object> createDeduction(HttpServletRequest request) throws Exception {

		Deduction deductionToBeAdded = new Deduction();
		try {
			deductionToBeAdded = DeductionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDeduction(deductionToBeAdded);

	}

	/**
	 * creates a new Deduction entry in the ofbiz database
	 * 
	 * @param deductionToBeAdded
	 *            the Deduction thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDeduction(@RequestBody Deduction deductionToBeAdded) throws Exception {

		AddDeduction command = new AddDeduction(deductionToBeAdded);
		Deduction deduction = ((DeductionAdded) Scheduler.execute(command).data()).getAddedDeduction();
		
		if (deduction != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(deduction);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Deduction could not be created.");
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
	public boolean updateDeduction(HttpServletRequest request) throws Exception {

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

		Deduction deductionToBeUpdated = new Deduction();

		try {
			deductionToBeUpdated = DeductionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDeduction(deductionToBeUpdated, deductionToBeUpdated.getDeductionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Deduction with the specific Id
	 * 
	 * @param deductionToBeUpdated
	 *            the Deduction thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{deductionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateDeduction(@RequestBody Deduction deductionToBeUpdated,
			@PathVariable String deductionId) throws Exception {

		deductionToBeUpdated.setDeductionId(deductionId);

		UpdateDeduction command = new UpdateDeduction(deductionToBeUpdated);

		try {
			if(((DeductionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{deductionId}")
	public ResponseEntity<Object> findById(@PathVariable String deductionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deductionId", deductionId);
		try {

			Object foundDeduction = findDeductionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDeduction);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{deductionId}")
	public ResponseEntity<Object> deleteDeductionByIdUpdated(@PathVariable String deductionId) throws Exception {
		DeleteDeduction command = new DeleteDeduction(deductionId);

		try {
			if (((DeductionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Deduction could not be deleted");

	}

}
