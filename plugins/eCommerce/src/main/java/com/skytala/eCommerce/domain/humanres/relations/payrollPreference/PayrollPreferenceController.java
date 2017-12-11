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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPayrollPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayrollPreferencesBy query = new FindPayrollPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayrollPreference> payrollPreferences =((PayrollPreferenceFound) Scheduler.execute(query).data()).getPayrollPreferences();

		if (payrollPreferences.size() == 1) {
			return ResponseEntity.ok().body(payrollPreferences.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPayrollPreference(HttpServletRequest request) throws Exception {

		PayrollPreference payrollPreferenceToBeAdded = new PayrollPreference();
		try {
			payrollPreferenceToBeAdded = PayrollPreferenceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPayrollPreference(@RequestBody PayrollPreference payrollPreferenceToBeAdded) throws Exception {

		AddPayrollPreference command = new AddPayrollPreference(payrollPreferenceToBeAdded);
		PayrollPreference payrollPreference = ((PayrollPreferenceAdded) Scheduler.execute(command).data()).getAddedPayrollPreference();
		
		if (payrollPreference != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(payrollPreference);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PayrollPreference could not be created.");
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
	public boolean updatePayrollPreference(HttpServletRequest request) throws Exception {

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

		PayrollPreference payrollPreferenceToBeUpdated = new PayrollPreference();

		try {
			payrollPreferenceToBeUpdated = PayrollPreferenceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePayrollPreference(payrollPreferenceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePayrollPreference(@RequestBody PayrollPreference payrollPreferenceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		payrollPreferenceToBeUpdated.setnull(null);

		UpdatePayrollPreference command = new UpdatePayrollPreference(payrollPreferenceToBeUpdated);

		try {
			if(((PayrollPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{payrollPreferenceId}")
	public ResponseEntity<Object> findById(@PathVariable String payrollPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payrollPreferenceId", payrollPreferenceId);
		try {

			Object foundPayrollPreference = findPayrollPreferencesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPayrollPreference);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{payrollPreferenceId}")
	public ResponseEntity<Object> deletePayrollPreferenceByIdUpdated(@PathVariable String payrollPreferenceId) throws Exception {
		DeletePayrollPreference command = new DeletePayrollPreference(payrollPreferenceId);

		try {
			if (((PayrollPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PayrollPreference could not be deleted");

	}

}
