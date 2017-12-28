package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewaySecurePay;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySecurePay.AddPaymentGatewaySecurePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySecurePay.DeletePaymentGatewaySecurePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySecurePay.UpdatePaymentGatewaySecurePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay.PaymentGatewaySecurePayAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay.PaymentGatewaySecurePayDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay.PaymentGatewaySecurePayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySecurePay.PaymentGatewaySecurePayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewaySecurePay.PaymentGatewaySecurePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySecurePay.PaymentGatewaySecurePay;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewaySecurePay.FindPaymentGatewaySecurePaysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewaySecurePays")
public class PaymentGatewaySecurePayController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewaySecurePayController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewaySecurePay
	 * @return a List with the PaymentGatewaySecurePays
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewaySecurePay>> findPaymentGatewaySecurePaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewaySecurePaysBy query = new FindPaymentGatewaySecurePaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewaySecurePay> paymentGatewaySecurePays =((PaymentGatewaySecurePayFound) Scheduler.execute(query).data()).getPaymentGatewaySecurePays();

		return ResponseEntity.ok().body(paymentGatewaySecurePays);

	}

	/**
	 * creates a new PaymentGatewaySecurePay entry in the ofbiz database
	 * 
	 * @param paymentGatewaySecurePayToBeAdded
	 *            the PaymentGatewaySecurePay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewaySecurePay> createPaymentGatewaySecurePay(@RequestBody PaymentGatewaySecurePay paymentGatewaySecurePayToBeAdded) throws Exception {

		AddPaymentGatewaySecurePay command = new AddPaymentGatewaySecurePay(paymentGatewaySecurePayToBeAdded);
		PaymentGatewaySecurePay paymentGatewaySecurePay = ((PaymentGatewaySecurePayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewaySecurePay();
		
		if (paymentGatewaySecurePay != null) 
			return successful(paymentGatewaySecurePay);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewaySecurePay with the specific Id
	 * 
	 * @param paymentGatewaySecurePayToBeUpdated
	 *            the PaymentGatewaySecurePay thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewaySecurePay(@RequestBody PaymentGatewaySecurePay paymentGatewaySecurePayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewaySecurePayToBeUpdated.setnull(null);

		UpdatePaymentGatewaySecurePay command = new UpdatePaymentGatewaySecurePay(paymentGatewaySecurePayToBeUpdated);

		try {
			if(((PaymentGatewaySecurePayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewaySecurePayId}")
	public ResponseEntity<PaymentGatewaySecurePay> findById(@PathVariable String paymentGatewaySecurePayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewaySecurePayId", paymentGatewaySecurePayId);
		try {

			List<PaymentGatewaySecurePay> foundPaymentGatewaySecurePay = findPaymentGatewaySecurePaysBy(requestParams).getBody();
			if(foundPaymentGatewaySecurePay.size()==1){				return successful(foundPaymentGatewaySecurePay.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewaySecurePayId}")
	public ResponseEntity<String> deletePaymentGatewaySecurePayByIdUpdated(@PathVariable String paymentGatewaySecurePayId) throws Exception {
		DeletePaymentGatewaySecurePay command = new DeletePaymentGatewaySecurePay(paymentGatewaySecurePayId);

		try {
			if (((PaymentGatewaySecurePayDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
