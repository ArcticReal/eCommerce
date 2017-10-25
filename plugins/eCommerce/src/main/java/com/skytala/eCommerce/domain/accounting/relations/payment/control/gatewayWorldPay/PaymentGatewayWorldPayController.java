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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/paymentGatewayWorldPays")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayWorldPaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayWorldPaysBy query = new FindPaymentGatewayWorldPaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayWorldPay> paymentGatewayWorldPays =((PaymentGatewayWorldPayFound) Scheduler.execute(query).data()).getPaymentGatewayWorldPays();

		if (paymentGatewayWorldPays.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayWorldPays.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayWorldPays);

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
	public ResponseEntity<Object> createPaymentGatewayWorldPay(HttpServletRequest request) throws Exception {

		PaymentGatewayWorldPay paymentGatewayWorldPayToBeAdded = new PaymentGatewayWorldPay();
		try {
			paymentGatewayWorldPayToBeAdded = PaymentGatewayWorldPayMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayWorldPay(paymentGatewayWorldPayToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayWorldPay entry in the ofbiz database
	 * 
	 * @param paymentGatewayWorldPayToBeAdded
	 *            the PaymentGatewayWorldPay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayWorldPay(@RequestBody PaymentGatewayWorldPay paymentGatewayWorldPayToBeAdded) throws Exception {

		AddPaymentGatewayWorldPay command = new AddPaymentGatewayWorldPay(paymentGatewayWorldPayToBeAdded);
		PaymentGatewayWorldPay paymentGatewayWorldPay = ((PaymentGatewayWorldPayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayWorldPay();
		
		if (paymentGatewayWorldPay != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayWorldPay);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayWorldPay could not be created.");
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
	public boolean updatePaymentGatewayWorldPay(HttpServletRequest request) throws Exception {

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

		PaymentGatewayWorldPay paymentGatewayWorldPayToBeUpdated = new PaymentGatewayWorldPay();

		try {
			paymentGatewayWorldPayToBeUpdated = PaymentGatewayWorldPayMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayWorldPay(paymentGatewayWorldPayToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayWorldPay(@RequestBody PaymentGatewayWorldPay paymentGatewayWorldPayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayWorldPayToBeUpdated.setnull(null);

		UpdatePaymentGatewayWorldPay command = new UpdatePaymentGatewayWorldPay(paymentGatewayWorldPayToBeUpdated);

		try {
			if(((PaymentGatewayWorldPayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayWorldPayId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayWorldPayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayWorldPayId", paymentGatewayWorldPayId);
		try {

			Object foundPaymentGatewayWorldPay = findPaymentGatewayWorldPaysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayWorldPay);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayWorldPayId}")
	public ResponseEntity<Object> deletePaymentGatewayWorldPayByIdUpdated(@PathVariable String paymentGatewayWorldPayId) throws Exception {
		DeletePaymentGatewayWorldPay command = new DeletePaymentGatewayWorldPay(paymentGatewayWorldPayId);

		try {
			if (((PaymentGatewayWorldPayDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayWorldPay could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayWorldPay/\" plus one of the following: "
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
