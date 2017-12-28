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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<PaymentApplication>> findPaymentApplicationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentApplicationsBy query = new FindPaymentApplicationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentApplication> paymentApplications =((PaymentApplicationFound) Scheduler.execute(query).data()).getPaymentApplications();

		return ResponseEntity.ok().body(paymentApplications);

	}

	/**
	 * creates a new PaymentApplication entry in the ofbiz database
	 * 
	 * @param paymentApplicationToBeAdded
	 *            the PaymentApplication thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentApplication> createPaymentApplication(@RequestBody PaymentApplication paymentApplicationToBeAdded) throws Exception {

		AddPaymentApplication command = new AddPaymentApplication(paymentApplicationToBeAdded);
		PaymentApplication paymentApplication = ((PaymentApplicationAdded) Scheduler.execute(command).data()).getAddedPaymentApplication();
		
		if (paymentApplication != null) 
			return successful(paymentApplication);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePaymentApplication(@RequestBody PaymentApplication paymentApplicationToBeUpdated,
			@PathVariable String paymentApplicationId) throws Exception {

		paymentApplicationToBeUpdated.setPaymentApplicationId(paymentApplicationId);

		UpdatePaymentApplication command = new UpdatePaymentApplication(paymentApplicationToBeUpdated);

		try {
			if(((PaymentApplicationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentApplicationId}")
	public ResponseEntity<PaymentApplication> findById(@PathVariable String paymentApplicationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentApplicationId", paymentApplicationId);
		try {

			List<PaymentApplication> foundPaymentApplication = findPaymentApplicationsBy(requestParams).getBody();
			if(foundPaymentApplication.size()==1){				return successful(foundPaymentApplication.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentApplicationId}")
	public ResponseEntity<String> deletePaymentApplicationByIdUpdated(@PathVariable String paymentApplicationId) throws Exception {
		DeletePaymentApplication command = new DeletePaymentApplication(paymentApplicationId);

		try {
			if (((PaymentApplicationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
