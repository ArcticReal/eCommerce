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
	public ResponseEntity<Object> findPaymentGatewaySagePaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewaySagePaysBy query = new FindPaymentGatewaySagePaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewaySagePay> paymentGatewaySagePays =((PaymentGatewaySagePayFound) Scheduler.execute(query).data()).getPaymentGatewaySagePays();

		if (paymentGatewaySagePays.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewaySagePays.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewaySagePays);

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
	public ResponseEntity<Object> createPaymentGatewaySagePay(HttpServletRequest request) throws Exception {

		PaymentGatewaySagePay paymentGatewaySagePayToBeAdded = new PaymentGatewaySagePay();
		try {
			paymentGatewaySagePayToBeAdded = PaymentGatewaySagePayMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewaySagePay(paymentGatewaySagePayToBeAdded);

	}

	/**
	 * creates a new PaymentGatewaySagePay entry in the ofbiz database
	 * 
	 * @param paymentGatewaySagePayToBeAdded
	 *            the PaymentGatewaySagePay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewaySagePay(@RequestBody PaymentGatewaySagePay paymentGatewaySagePayToBeAdded) throws Exception {

		AddPaymentGatewaySagePay command = new AddPaymentGatewaySagePay(paymentGatewaySagePayToBeAdded);
		PaymentGatewaySagePay paymentGatewaySagePay = ((PaymentGatewaySagePayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewaySagePay();
		
		if (paymentGatewaySagePay != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewaySagePay);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewaySagePay could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePaymentGatewaySagePay(HttpServletRequest request) throws Exception {

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

		PaymentGatewaySagePay paymentGatewaySagePayToBeUpdated = new PaymentGatewaySagePay();

		try {
			paymentGatewaySagePayToBeUpdated = PaymentGatewaySagePayMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewaySagePay(paymentGatewaySagePayToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewaySagePay(@RequestBody PaymentGatewaySagePay paymentGatewaySagePayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewaySagePayToBeUpdated.setnull(null);

		UpdatePaymentGatewaySagePay command = new UpdatePaymentGatewaySagePay(paymentGatewaySagePayToBeUpdated);

		try {
			if(((PaymentGatewaySagePayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentGatewaySagePayId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewaySagePayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewaySagePayId", paymentGatewaySagePayId);
		try {

			Object foundPaymentGatewaySagePay = findPaymentGatewaySagePaysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewaySagePay);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentGatewaySagePayId}")
	public ResponseEntity<Object> deletePaymentGatewaySagePayByIdUpdated(@PathVariable String paymentGatewaySagePayId) throws Exception {
		DeletePaymentGatewaySagePay command = new DeletePaymentGatewaySagePay(paymentGatewaySagePayId);

		try {
			if (((PaymentGatewaySagePayDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewaySagePay could not be deleted");

	}

}
