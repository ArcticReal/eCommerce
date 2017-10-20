package com.skytala.eCommerce.domain.accounting.relations.payment;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.AddPayment;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.DeletePayment;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.UpdatePayment;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.PaymentAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.PaymentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.PaymentFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.PaymentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.PaymentMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.Payment;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.FindPaymentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/payments")
public class PaymentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Payment
	 * @return a List with the Payments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentsBy query = new FindPaymentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Payment> payments =((PaymentFound) Scheduler.execute(query).data()).getPayments();

		if (payments.size() == 1) {
			return ResponseEntity.ok().body(payments.get(0));
		}

		return ResponseEntity.ok().body(payments);

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
	public ResponseEntity<Object> createPayment(HttpServletRequest request) throws Exception {

		Payment paymentToBeAdded = new Payment();
		try {
			paymentToBeAdded = PaymentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPayment(paymentToBeAdded);

	}

	/**
	 * creates a new Payment entry in the ofbiz database
	 * 
	 * @param paymentToBeAdded
	 *            the Payment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPayment(@RequestBody Payment paymentToBeAdded) throws Exception {

		AddPayment command = new AddPayment(paymentToBeAdded);
		Payment payment = ((PaymentAdded) Scheduler.execute(command).data()).getAddedPayment();
		
		if (payment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(payment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Payment could not be created.");
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
	public boolean updatePayment(HttpServletRequest request) throws Exception {

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

		Payment paymentToBeUpdated = new Payment();

		try {
			paymentToBeUpdated = PaymentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePayment(paymentToBeUpdated, paymentToBeUpdated.getPaymentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Payment with the specific Id
	 * 
	 * @param paymentToBeUpdated
	 *            the Payment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePayment(@RequestBody Payment paymentToBeUpdated,
			@PathVariable String paymentId) throws Exception {

		paymentToBeUpdated.setPaymentId(paymentId);

		UpdatePayment command = new UpdatePayment(paymentToBeUpdated);

		try {
			if(((PaymentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentId", paymentId);
		try {

			Object foundPayment = findPaymentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPayment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentId}")
	public ResponseEntity<Object> deletePaymentByIdUpdated(@PathVariable String paymentId) throws Exception {
		DeletePayment command = new DeletePayment(paymentId);

		try {
			if (((PaymentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Payment could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/payment/\" plus one of the following: "
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
