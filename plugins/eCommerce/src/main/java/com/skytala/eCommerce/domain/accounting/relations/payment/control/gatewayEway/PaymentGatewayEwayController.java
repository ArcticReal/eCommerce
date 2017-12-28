package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayEway;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayEway.AddPaymentGatewayEway;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayEway.DeletePaymentGatewayEway;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayEway.UpdatePaymentGatewayEway;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway.PaymentGatewayEwayAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway.PaymentGatewayEwayDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway.PaymentGatewayEwayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway.PaymentGatewayEwayUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayEway.PaymentGatewayEwayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayEway.PaymentGatewayEway;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayEway.FindPaymentGatewayEwaysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayEways")
public class PaymentGatewayEwayController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayEwayController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayEway
	 * @return a List with the PaymentGatewayEways
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayEway>> findPaymentGatewayEwaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayEwaysBy query = new FindPaymentGatewayEwaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayEway> paymentGatewayEways =((PaymentGatewayEwayFound) Scheduler.execute(query).data()).getPaymentGatewayEways();

		return ResponseEntity.ok().body(paymentGatewayEways);

	}

	/**
	 * creates a new PaymentGatewayEway entry in the ofbiz database
	 * 
	 * @param paymentGatewayEwayToBeAdded
	 *            the PaymentGatewayEway thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayEway> createPaymentGatewayEway(@RequestBody PaymentGatewayEway paymentGatewayEwayToBeAdded) throws Exception {

		AddPaymentGatewayEway command = new AddPaymentGatewayEway(paymentGatewayEwayToBeAdded);
		PaymentGatewayEway paymentGatewayEway = ((PaymentGatewayEwayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayEway();
		
		if (paymentGatewayEway != null) 
			return successful(paymentGatewayEway);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayEway with the specific Id
	 * 
	 * @param paymentGatewayEwayToBeUpdated
	 *            the PaymentGatewayEway thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayEway(@RequestBody PaymentGatewayEway paymentGatewayEwayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayEwayToBeUpdated.setnull(null);

		UpdatePaymentGatewayEway command = new UpdatePaymentGatewayEway(paymentGatewayEwayToBeUpdated);

		try {
			if(((PaymentGatewayEwayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayEwayId}")
	public ResponseEntity<PaymentGatewayEway> findById(@PathVariable String paymentGatewayEwayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayEwayId", paymentGatewayEwayId);
		try {

			List<PaymentGatewayEway> foundPaymentGatewayEway = findPaymentGatewayEwaysBy(requestParams).getBody();
			if(foundPaymentGatewayEway.size()==1){				return successful(foundPaymentGatewayEway.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayEwayId}")
	public ResponseEntity<String> deletePaymentGatewayEwayByIdUpdated(@PathVariable String paymentGatewayEwayId) throws Exception {
		DeletePaymentGatewayEway command = new DeletePaymentGatewayEway(paymentGatewayEwayId);

		try {
			if (((PaymentGatewayEwayDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
