package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayWorldPay;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayWorldPay.AddPaymentGatewayWorldPay;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayWorldPay.DeletePaymentGatewayWorldPay;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayWorldPay.UpdatePaymentGatewayWorldPay;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay.PaymentGatewayWorldPayAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay.PaymentGatewayWorldPayDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay.PaymentGatewayWorldPayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay.PaymentGatewayWorldPayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayWorldPay.PaymentGatewayWorldPayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayWorldPay.FindPaymentGatewayWorldPaysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayWorldPays")
public class PaymentGatewayWorldPayController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayWorldPayController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayWorldPay
	 * @return a List with the PaymentGatewayWorldPays
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayWorldPay>> findPaymentGatewayWorldPaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayWorldPaysBy query = new FindPaymentGatewayWorldPaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayWorldPay> paymentGatewayWorldPays =((PaymentGatewayWorldPayFound) Scheduler.execute(query).data()).getPaymentGatewayWorldPays();

		return ResponseEntity.ok().body(paymentGatewayWorldPays);

	}

	/**
	 * creates a new PaymentGatewayWorldPay entry in the ofbiz database
	 * 
	 * @param paymentGatewayWorldPayToBeAdded
	 *            the PaymentGatewayWorldPay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayWorldPay> createPaymentGatewayWorldPay(@RequestBody PaymentGatewayWorldPay paymentGatewayWorldPayToBeAdded) throws Exception {

		AddPaymentGatewayWorldPay command = new AddPaymentGatewayWorldPay(paymentGatewayWorldPayToBeAdded);
		PaymentGatewayWorldPay paymentGatewayWorldPay = ((PaymentGatewayWorldPayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayWorldPay();
		
		if (paymentGatewayWorldPay != null) 
			return successful(paymentGatewayWorldPay);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayWorldPay with the specific Id
	 * 
	 * @param paymentGatewayWorldPayToBeUpdated
	 *            the PaymentGatewayWorldPay thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayWorldPay(@RequestBody PaymentGatewayWorldPay paymentGatewayWorldPayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayWorldPayToBeUpdated.setnull(null);

		UpdatePaymentGatewayWorldPay command = new UpdatePaymentGatewayWorldPay(paymentGatewayWorldPayToBeUpdated);

		try {
			if(((PaymentGatewayWorldPayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayWorldPayId}")
	public ResponseEntity<PaymentGatewayWorldPay> findById(@PathVariable String paymentGatewayWorldPayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayWorldPayId", paymentGatewayWorldPayId);
		try {

			List<PaymentGatewayWorldPay> foundPaymentGatewayWorldPay = findPaymentGatewayWorldPaysBy(requestParams).getBody();
			if(foundPaymentGatewayWorldPay.size()==1){				return successful(foundPaymentGatewayWorldPay.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayWorldPayId}")
	public ResponseEntity<String> deletePaymentGatewayWorldPayByIdUpdated(@PathVariable String paymentGatewayWorldPayId) throws Exception {
		DeletePaymentGatewayWorldPay command = new DeletePaymentGatewayWorldPay(paymentGatewayWorldPayId);

		try {
			if (((PaymentGatewayWorldPayDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
