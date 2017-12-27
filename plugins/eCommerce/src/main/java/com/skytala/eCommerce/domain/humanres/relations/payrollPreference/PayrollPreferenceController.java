package com.skytala.eCommerce.domain.humanres.relations.payrollPreference;

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
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.command.AddPayrollPreference;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.command.DeletePayrollPreference;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.command.UpdatePayrollPreference;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceAdded;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceDeleted;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceFound;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceUpdated;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.mapper.PayrollPreferenceMapper;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.query.FindPayrollPreferencesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/payrollPreferences")
public class PayrollPreferenceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PayrollPreferenceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PayrollPreference
	 * @return a List with the PayrollPreferences
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PayrollPreference>> findPayrollPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayrollPreferencesBy query = new FindPayrollPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayrollPreference> payrollPreferences =((PayrollPreferenceFound) Scheduler.execute(query).data()).getPayrollPreferences();

		return ResponseEntity.ok().body(payrollPreferences);

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
	public ResponseEntity<PayrollPreference> createPayrollPreference(HttpServletRequest request) throws Exception {

		PayrollPreference payrollPreferenceToBeAdded = new PayrollPreference();
		try {
			payrollPreferenceToBeAdded = PayrollPreferenceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPayrollPreference(payrollPreferenceToBeAdded);

	}

	/**
	 * creates a new PayrollPreference entry in the ofbiz database
	 * 
	 * @param payrollPreferenceToBeAdded
	 *            the PayrollPreference thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PayrollPreference> createPayrollPreference(@RequestBody PayrollPreference payrollPreferenceToBeAdded) throws Exception {

		AddPayrollPreference command = new AddPayrollPreference(payrollPreferenceToBeAdded);
		PayrollPreference payrollPreference = ((PayrollPreferenceAdded) Scheduler.execute(command).data()).getAddedPayrollPreference();
		
		if (payrollPreference != null) 
			return successful(payrollPreference);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PayrollPreference with the specific Id
	 * 
	 * @param payrollPreferenceToBeUpdated
	 *            the PayrollPreference thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePayrollPreference(@RequestBody PayrollPreference payrollPreferenceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		payrollPreferenceToBeUpdated.setnull(null);

		UpdatePayrollPreference command = new UpdatePayrollPreference(payrollPreferenceToBeUpdated);

		try {
			if(((PayrollPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{payrollPreferenceId}")
	public ResponseEntity<PayrollPreference> findById(@PathVariable String payrollPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payrollPreferenceId", payrollPreferenceId);
		try {

			List<PayrollPreference> foundPayrollPreference = findPayrollPreferencesBy(requestParams).getBody();
			if(foundPayrollPreference.size()==1){				return successful(foundPayrollPreference.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{payrollPreferenceId}")
	public ResponseEntity<String> deletePayrollPreferenceByIdUpdated(@PathVariable String payrollPreferenceId) throws Exception {
		DeletePayrollPreference command = new DeletePayrollPreference(payrollPreferenceId);

		try {
			if (((PayrollPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
