package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayResponse;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayResponse.AddPaymentGatewayResponse;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayResponse.DeletePaymentGatewayResponse;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayResponse.UpdatePaymentGatewayResponse;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayResponse.PaymentGatewayResponseAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayResponse.PaymentGatewayResponseDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayResponse.PaymentGatewayResponseFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayResponse.PaymentGatewayResponseUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayResponse.PaymentGatewayResponseMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayResponse.PaymentGatewayResponse;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayResponse.FindPaymentGatewayResponsesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayResponses")
public class PaymentGatewayResponseController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayResponseController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayResponse
	 * @return a List with the PaymentGatewayResponses
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPaymentGatewayResponsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayResponsesBy query = new FindPaymentGatewayResponsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayResponse> paymentGatewayResponses =((PaymentGatewayResponseFound) Scheduler.execute(query).data()).getPaymentGatewayResponses();

		if (paymentGatewayResponses.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayResponses.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayResponses);

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
	public ResponseEntity<Object> createPaymentGatewayResponse(HttpServletRequest request) throws Exception {

		PaymentGatewayResponse paymentGatewayResponseToBeAdded = new PaymentGatewayResponse();
		try {
			paymentGatewayResponseToBeAdded = PaymentGatewayResponseMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayResponse(paymentGatewayResponseToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayResponse entry in the ofbiz database
	 * 
	 * @param paymentGatewayResponseToBeAdded
	 *            the PaymentGatewayResponse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayResponse(@RequestBody PaymentGatewayResponse paymentGatewayResponseToBeAdded) throws Exception {

		AddPaymentGatewayResponse command = new AddPaymentGatewayResponse(paymentGatewayResponseToBeAdded);
		PaymentGatewayResponse paymentGatewayResponse = ((PaymentGatewayResponseAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayResponse();
		
		if (paymentGatewayResponse != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayResponse);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayResponse could not be created.");
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
	public boolean updatePaymentGatewayResponse(HttpServletRequest request) throws Exception {

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

		PaymentGatewayResponse paymentGatewayResponseToBeUpdated = new PaymentGatewayResponse();

		try {
			paymentGatewayResponseToBeUpdated = PaymentGatewayResponseMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayResponse(paymentGatewayResponseToBeUpdated, paymentGatewayResponseToBeUpdated.getPaymentGatewayResponseId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentGatewayResponse with the specific Id
	 * 
	 * @param paymentGatewayResponseToBeUpdated
	 *            the PaymentGatewayResponse thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentGatewayResponseId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentGatewayResponse(@RequestBody PaymentGatewayResponse paymentGatewayResponseToBeUpdated,
			@PathVariable String paymentGatewayResponseId) throws Exception {

		paymentGatewayResponseToBeUpdated.setPaymentGatewayResponseId(paymentGatewayResponseId);

		UpdatePaymentGatewayResponse command = new UpdatePaymentGatewayResponse(paymentGatewayResponseToBeUpdated);

		try {
			if(((PaymentGatewayResponseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentGatewayResponseId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayResponseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayResponseId", paymentGatewayResponseId);
		try {

			Object foundPaymentGatewayResponse = findPaymentGatewayResponsesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayResponse);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentGatewayResponseId}")
	public ResponseEntity<Object> deletePaymentGatewayResponseByIdUpdated(@PathVariable String paymentGatewayResponseId) throws Exception {
		DeletePaymentGatewayResponse command = new DeletePaymentGatewayResponse(paymentGatewayResponseId);

		try {
			if (((PaymentGatewayResponseDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayResponse could not be deleted");

	}

}
