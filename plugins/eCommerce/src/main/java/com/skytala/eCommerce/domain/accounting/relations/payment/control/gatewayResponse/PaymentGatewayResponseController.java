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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PaymentGatewayResponse>> findPaymentGatewayResponsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayResponsesBy query = new FindPaymentGatewayResponsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayResponse> paymentGatewayResponses =((PaymentGatewayResponseFound) Scheduler.execute(query).data()).getPaymentGatewayResponses();

		return ResponseEntity.ok().body(paymentGatewayResponses);

	}

	/**
	 * creates a new PaymentGatewayResponse entry in the ofbiz database
	 * 
	 * @param paymentGatewayResponseToBeAdded
	 *            the PaymentGatewayResponse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayResponse> createPaymentGatewayResponse(@RequestBody PaymentGatewayResponse paymentGatewayResponseToBeAdded) throws Exception {

		AddPaymentGatewayResponse command = new AddPaymentGatewayResponse(paymentGatewayResponseToBeAdded);
		PaymentGatewayResponse paymentGatewayResponse = ((PaymentGatewayResponseAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayResponse();
		
		if (paymentGatewayResponse != null) 
			return successful(paymentGatewayResponse);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePaymentGatewayResponse(@RequestBody PaymentGatewayResponse paymentGatewayResponseToBeUpdated,
			@PathVariable String paymentGatewayResponseId) throws Exception {

		paymentGatewayResponseToBeUpdated.setPaymentGatewayResponseId(paymentGatewayResponseId);

		UpdatePaymentGatewayResponse command = new UpdatePaymentGatewayResponse(paymentGatewayResponseToBeUpdated);

		try {
			if(((PaymentGatewayResponseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayResponseId}")
	public ResponseEntity<PaymentGatewayResponse> findById(@PathVariable String paymentGatewayResponseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayResponseId", paymentGatewayResponseId);
		try {

			List<PaymentGatewayResponse> foundPaymentGatewayResponse = findPaymentGatewayResponsesBy(requestParams).getBody();
			if(foundPaymentGatewayResponse.size()==1){				return successful(foundPaymentGatewayResponse.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayResponseId}")
	public ResponseEntity<String> deletePaymentGatewayResponseByIdUpdated(@PathVariable String paymentGatewayResponseId) throws Exception {
		DeletePaymentGatewayResponse command = new DeletePaymentGatewayResponse(paymentGatewayResponseId);

		try {
			if (((PaymentGatewayResponseDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
