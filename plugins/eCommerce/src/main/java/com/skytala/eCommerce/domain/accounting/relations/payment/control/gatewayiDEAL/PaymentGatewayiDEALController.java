package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayiDEAL;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayiDEAL.AddPaymentGatewayiDEAL;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayiDEAL.DeletePaymentGatewayiDEAL;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayiDEAL.UpdatePaymentGatewayiDEAL;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL.PaymentGatewayiDEALAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL.PaymentGatewayiDEALDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL.PaymentGatewayiDEALFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL.PaymentGatewayiDEALUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayiDEAL.PaymentGatewayiDEALMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayiDEAL.FindPaymentGatewayiDEALsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/paymentGatewayiDEALs")
public class PaymentGatewayiDEALController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayiDEALController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayiDEAL
	 * @return a List with the PaymentGatewayiDEALs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayiDEALsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayiDEALsBy query = new FindPaymentGatewayiDEALsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayiDEAL> paymentGatewayiDEALs =((PaymentGatewayiDEALFound) Scheduler.execute(query).data()).getPaymentGatewayiDEALs();

		if (paymentGatewayiDEALs.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayiDEALs.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayiDEALs);

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
	public ResponseEntity<Object> createPaymentGatewayiDEAL(HttpServletRequest request) throws Exception {

		PaymentGatewayiDEAL paymentGatewayiDEALToBeAdded = new PaymentGatewayiDEAL();
		try {
			paymentGatewayiDEALToBeAdded = PaymentGatewayiDEALMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayiDEAL(paymentGatewayiDEALToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayiDEAL entry in the ofbiz database
	 * 
	 * @param paymentGatewayiDEALToBeAdded
	 *            the PaymentGatewayiDEAL thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayiDEAL(@RequestBody PaymentGatewayiDEAL paymentGatewayiDEALToBeAdded) throws Exception {

		AddPaymentGatewayiDEAL command = new AddPaymentGatewayiDEAL(paymentGatewayiDEALToBeAdded);
		PaymentGatewayiDEAL paymentGatewayiDEAL = ((PaymentGatewayiDEALAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayiDEAL();
		
		if (paymentGatewayiDEAL != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayiDEAL);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayiDEAL could not be created.");
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
	public boolean updatePaymentGatewayiDEAL(HttpServletRequest request) throws Exception {

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

		PaymentGatewayiDEAL paymentGatewayiDEALToBeUpdated = new PaymentGatewayiDEAL();

		try {
			paymentGatewayiDEALToBeUpdated = PaymentGatewayiDEALMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayiDEAL(paymentGatewayiDEALToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentGatewayiDEAL with the specific Id
	 * 
	 * @param paymentGatewayiDEALToBeUpdated
	 *            the PaymentGatewayiDEAL thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentGatewayiDEAL(@RequestBody PaymentGatewayiDEAL paymentGatewayiDEALToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayiDEALToBeUpdated.setnull(null);

		UpdatePaymentGatewayiDEAL command = new UpdatePaymentGatewayiDEAL(paymentGatewayiDEALToBeUpdated);

		try {
			if(((PaymentGatewayiDEALUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayiDEALId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayiDEALId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayiDEALId", paymentGatewayiDEALId);
		try {

			Object foundPaymentGatewayiDEAL = findPaymentGatewayiDEALsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayiDEAL);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayiDEALId}")
	public ResponseEntity<Object> deletePaymentGatewayiDEALByIdUpdated(@PathVariable String paymentGatewayiDEALId) throws Exception {
		DeletePaymentGatewayiDEAL command = new DeletePaymentGatewayiDEAL(paymentGatewayiDEALId);

		try {
			if (((PaymentGatewayiDEALDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayiDEAL could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentGatewayiDEAL/\" plus one of the following: "
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
