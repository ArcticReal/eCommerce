package com.skytala.eCommerce.domain.accounting.relations.payment.control.application;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.application.AddPaymentApplication;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.application.DeletePaymentApplication;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.application.UpdatePaymentApplication;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.application.PaymentApplicationAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.application.PaymentApplicationDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.application.PaymentApplicationFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.application.PaymentApplicationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.application.PaymentApplicationMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.application.PaymentApplication;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.application.FindPaymentApplicationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/payment/paymentApplications")
public class PaymentApplicationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentApplicationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentApplication
	 * @return a List with the PaymentApplications
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentApplicationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentApplicationsBy query = new FindPaymentApplicationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentApplication> paymentApplications =((PaymentApplicationFound) Scheduler.execute(query).data()).getPaymentApplications();

		if (paymentApplications.size() == 1) {
			return ResponseEntity.ok().body(paymentApplications.get(0));
		}

		return ResponseEntity.ok().body(paymentApplications);

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
	public ResponseEntity<Object> createPaymentApplication(HttpServletRequest request) throws Exception {

		PaymentApplication paymentApplicationToBeAdded = new PaymentApplication();
		try {
			paymentApplicationToBeAdded = PaymentApplicationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentApplication(paymentApplicationToBeAdded);

	}

	/**
	 * creates a new PaymentApplication entry in the ofbiz database
	 * 
	 * @param paymentApplicationToBeAdded
	 *            the PaymentApplication thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentApplication(@RequestBody PaymentApplication paymentApplicationToBeAdded) throws Exception {

		AddPaymentApplication command = new AddPaymentApplication(paymentApplicationToBeAdded);
		PaymentApplication paymentApplication = ((PaymentApplicationAdded) Scheduler.execute(command).data()).getAddedPaymentApplication();
		
		if (paymentApplication != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentApplication);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentApplication could not be created.");
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
	public boolean updatePaymentApplication(HttpServletRequest request) throws Exception {

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

		PaymentApplication paymentApplicationToBeUpdated = new PaymentApplication();

		try {
			paymentApplicationToBeUpdated = PaymentApplicationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentApplication(paymentApplicationToBeUpdated, paymentApplicationToBeUpdated.getPaymentApplicationId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentApplication with the specific Id
	 * 
	 * @param paymentApplicationToBeUpdated
	 *            the PaymentApplication thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentApplicationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentApplication(@RequestBody PaymentApplication paymentApplicationToBeUpdated,
			@PathVariable String paymentApplicationId) throws Exception {

		paymentApplicationToBeUpdated.setPaymentApplicationId(paymentApplicationId);

		UpdatePaymentApplication command = new UpdatePaymentApplication(paymentApplicationToBeUpdated);

		try {
			if(((PaymentApplicationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentApplicationId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentApplicationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentApplicationId", paymentApplicationId);
		try {

			Object foundPaymentApplication = findPaymentApplicationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentApplication);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentApplicationId}")
	public ResponseEntity<Object> deletePaymentApplicationByIdUpdated(@PathVariable String paymentApplicationId) throws Exception {
		DeletePaymentApplication command = new DeletePaymentApplication(paymentApplicationId);

		try {
			if (((PaymentApplicationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentApplication could not be deleted");

	}

}
