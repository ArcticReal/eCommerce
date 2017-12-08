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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/paymentGatewaySecurePays")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewaySecurePaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewaySecurePaysBy query = new FindPaymentGatewaySecurePaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewaySecurePay> paymentGatewaySecurePays =((PaymentGatewaySecurePayFound) Scheduler.execute(query).data()).getPaymentGatewaySecurePays();

		if (paymentGatewaySecurePays.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewaySecurePays.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewaySecurePays);

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
	public ResponseEntity<Object> createPaymentGatewaySecurePay(HttpServletRequest request) throws Exception {

		PaymentGatewaySecurePay paymentGatewaySecurePayToBeAdded = new PaymentGatewaySecurePay();
		try {
			paymentGatewaySecurePayToBeAdded = PaymentGatewaySecurePayMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewaySecurePay(paymentGatewaySecurePayToBeAdded);

	}

	/**
	 * creates a new PaymentGatewaySecurePay entry in the ofbiz database
	 * 
	 * @param paymentGatewaySecurePayToBeAdded
	 *            the PaymentGatewaySecurePay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewaySecurePay(@RequestBody PaymentGatewaySecurePay paymentGatewaySecurePayToBeAdded) throws Exception {

		AddPaymentGatewaySecurePay command = new AddPaymentGatewaySecurePay(paymentGatewaySecurePayToBeAdded);
		PaymentGatewaySecurePay paymentGatewaySecurePay = ((PaymentGatewaySecurePayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewaySecurePay();
		
		if (paymentGatewaySecurePay != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewaySecurePay);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewaySecurePay could not be created.");
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
	public boolean updatePaymentGatewaySecurePay(HttpServletRequest request) throws Exception {

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

		PaymentGatewaySecurePay paymentGatewaySecurePayToBeUpdated = new PaymentGatewaySecurePay();

		try {
			paymentGatewaySecurePayToBeUpdated = PaymentGatewaySecurePayMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewaySecurePay(paymentGatewaySecurePayToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewaySecurePay(@RequestBody PaymentGatewaySecurePay paymentGatewaySecurePayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewaySecurePayToBeUpdated.setnull(null);

		UpdatePaymentGatewaySecurePay command = new UpdatePaymentGatewaySecurePay(paymentGatewaySecurePayToBeUpdated);

		try {
			if(((PaymentGatewaySecurePayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewaySecurePayId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewaySecurePayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewaySecurePayId", paymentGatewaySecurePayId);
		try {

			Object foundPaymentGatewaySecurePay = findPaymentGatewaySecurePaysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewaySecurePay);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewaySecurePayId}")
	public ResponseEntity<Object> deletePaymentGatewaySecurePayByIdUpdated(@PathVariable String paymentGatewaySecurePayId) throws Exception {
		DeletePaymentGatewaySecurePay command = new DeletePaymentGatewaySecurePay(paymentGatewaySecurePayId);

		try {
			if (((PaymentGatewaySecurePayDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewaySecurePay could not be deleted");

	}

}
