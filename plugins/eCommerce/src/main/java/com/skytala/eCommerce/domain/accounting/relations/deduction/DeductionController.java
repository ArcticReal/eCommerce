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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<Deduction>> findDeductionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeductionsBy query = new FindDeductionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Deduction> deductions =((DeductionFound) Scheduler.execute(query).data()).getDeductions();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Deduction> createDeduction(HttpServletRequest request) throws Exception {

		Deduction deductionToBeAdded = new Deduction();
		try {
			deductionToBeAdded = DeductionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<Deduction> createDeduction(@RequestBody Deduction deductionToBeAdded) throws Exception {

		AddDeduction command = new AddDeduction(deductionToBeAdded);
		Deduction deduction = ((DeductionAdded) Scheduler.execute(command).data()).getAddedDeduction();
		
		if (deduction != null) 
			return successful(deduction);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateDeduction(@RequestBody Deduction deductionToBeUpdated,
			@PathVariable String deductionId) throws Exception {

		deductionToBeUpdated.setDeductionId(deductionId);

		UpdateDeduction command = new UpdateDeduction(deductionToBeUpdated);

		try {
			if(((DeductionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{deductionId}")
	public ResponseEntity<Deduction> findById(@PathVariable String deductionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deductionId", deductionId);
		try {

			List<Deduction> foundDeduction = findDeductionsBy(requestParams).getBody();
			if(foundDeduction.size()==1){				return successful(foundDeduction.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{deductionId}")
	public ResponseEntity<String> deleteDeductionByIdUpdated(@PathVariable String deductionId) throws Exception {
		DeleteDeduction command = new DeleteDeduction(deductionId);

		try {
			if (((DeductionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
