package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewaySagePay;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySagePay.AddPaymentGatewaySagePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySagePay.DeletePaymentGatewaySagePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySagePay.UpdatePaymentGatewaySagePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay.PaymentGatewaySagePayAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay.PaymentGatewaySagePayDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay.PaymentGatewaySagePayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay.PaymentGatewaySagePayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewaySagePay.PaymentGatewaySagePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySagePay.PaymentGatewaySagePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewaySagePay.FindPaymentGatewaySagePaysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewaySagePays")
public class PaymentGatewaySagePayController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewaySagePayController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewaySagePay
	 * @return a List with the PaymentGatewaySagePays
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewaySagePay>> findPaymentGatewaySagePaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewaySagePaysBy query = new FindPaymentGatewaySagePaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewaySagePay> paymentGatewaySagePays =((PaymentGatewaySagePayFound) Scheduler.execute(query).data()).getPaymentGatewaySagePays();

		return ResponseEntity.ok().body(paymentGatewaySagePays);

	}

	/**
	 * creates a new PaymentGatewaySagePay entry in the ofbiz database
	 * 
	 * @param paymentGatewaySagePayToBeAdded
	 *            the PaymentGatewaySagePay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewaySagePay> createPaymentGatewaySagePay(@RequestBody PaymentGatewaySagePay paymentGatewaySagePayToBeAdded) throws Exception {

		AddPaymentGatewaySagePay command = new AddPaymentGatewaySagePay(paymentGatewaySagePayToBeAdded);
		PaymentGatewaySagePay paymentGatewaySagePay = ((PaymentGatewaySagePayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewaySagePay();
		
		if (paymentGatewaySagePay != null) 
			return successful(paymentGatewaySagePay);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewaySagePay with the specific Id
	 * 
	 * @param paymentGatewaySagePayToBeUpdated
	 *            the PaymentGatewaySagePay thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewaySagePay(@RequestBody PaymentGatewaySagePay paymentGatewaySagePayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewaySagePayToBeUpdated.setnull(null);

		UpdatePaymentGatewaySagePay command = new UpdatePaymentGatewaySagePay(paymentGatewaySagePayToBeUpdated);

		try {
			if(((PaymentGatewaySagePayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewaySagePayId}")
	public ResponseEntity<PaymentGatewaySagePay> findById(@PathVariable String paymentGatewaySagePayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewaySagePayId", paymentGatewaySagePayId);
		try {

			List<PaymentGatewaySagePay> foundPaymentGatewaySagePay = findPaymentGatewaySagePaysBy(requestParams).getBody();
			if(foundPaymentGatewaySagePay.size()==1){				return successful(foundPaymentGatewaySagePay.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewaySagePayId}")
	public ResponseEntity<String> deletePaymentGatewaySagePayByIdUpdated(@PathVariable String paymentGatewaySagePayId) throws Exception {
		DeletePaymentGatewaySagePay command = new DeletePaymentGatewaySagePay(paymentGatewaySagePayId);

		try {
			if (((PaymentGatewaySagePayDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
