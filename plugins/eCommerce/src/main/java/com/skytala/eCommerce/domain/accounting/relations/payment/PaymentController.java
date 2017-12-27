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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payments")
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
	@GetMapping("/find")
	public ResponseEntity<List<Payment>> findPaymentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentsBy query = new FindPaymentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Payment> payments =((PaymentFound) Scheduler.execute(query).data()).getPayments();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Payment> createPayment(HttpServletRequest request) throws Exception {

		Payment paymentToBeAdded = new Payment();
		try {
			paymentToBeAdded = PaymentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<Payment> createPayment(@RequestBody Payment paymentToBeAdded) throws Exception {

		AddPayment command = new AddPayment(paymentToBeAdded);
		Payment payment = ((PaymentAdded) Scheduler.execute(command).data()).getAddedPayment();
		
		if (payment != null) 
			return successful(payment);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePayment(@RequestBody Payment paymentToBeUpdated,
			@PathVariable String paymentId) throws Exception {

		paymentToBeUpdated.setPaymentId(paymentId);

		UpdatePayment command = new UpdatePayment(paymentToBeUpdated);

		try {
			if(((PaymentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<Payment> findById(@PathVariable String paymentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentId", paymentId);
		try {

			List<Payment> foundPayment = findPaymentsBy(requestParams).getBody();
			if(foundPayment.size()==1){				return successful(foundPayment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<String> deletePaymentByIdUpdated(@PathVariable String paymentId) throws Exception {
		DeletePayment command = new DeletePayment(paymentId);

		try {
			if (((PaymentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
