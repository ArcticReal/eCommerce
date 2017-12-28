package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayClearCommerce;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayClearCommerce.AddPaymentGatewayClearCommerce;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayClearCommerce.DeletePaymentGatewayClearCommerce;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayClearCommerce.UpdatePaymentGatewayClearCommerce;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce.PaymentGatewayClearCommerceAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce.PaymentGatewayClearCommerceDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce.PaymentGatewayClearCommerceFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce.PaymentGatewayClearCommerceUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayClearCommerce.PaymentGatewayClearCommerceMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayClearCommerce.PaymentGatewayClearCommerce;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayClearCommerce.FindPaymentGatewayClearCommercesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayClearCommerces")
public class PaymentGatewayClearCommerceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayClearCommerceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayClearCommerce
	 * @return a List with the PaymentGatewayClearCommerces
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayClearCommerce>> findPaymentGatewayClearCommercesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayClearCommercesBy query = new FindPaymentGatewayClearCommercesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayClearCommerce> paymentGatewayClearCommerces =((PaymentGatewayClearCommerceFound) Scheduler.execute(query).data()).getPaymentGatewayClearCommerces();

		return ResponseEntity.ok().body(paymentGatewayClearCommerces);

	}

	/**
	 * creates a new PaymentGatewayClearCommerce entry in the ofbiz database
	 * 
	 * @param paymentGatewayClearCommerceToBeAdded
	 *            the PaymentGatewayClearCommerce thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayClearCommerce> createPaymentGatewayClearCommerce(@RequestBody PaymentGatewayClearCommerce paymentGatewayClearCommerceToBeAdded) throws Exception {

		AddPaymentGatewayClearCommerce command = new AddPaymentGatewayClearCommerce(paymentGatewayClearCommerceToBeAdded);
		PaymentGatewayClearCommerce paymentGatewayClearCommerce = ((PaymentGatewayClearCommerceAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayClearCommerce();
		
		if (paymentGatewayClearCommerce != null) 
			return successful(paymentGatewayClearCommerce);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayClearCommerce with the specific Id
	 * 
	 * @param paymentGatewayClearCommerceToBeUpdated
	 *            the PaymentGatewayClearCommerce thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayClearCommerce(@RequestBody PaymentGatewayClearCommerce paymentGatewayClearCommerceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayClearCommerceToBeUpdated.setnull(null);

		UpdatePaymentGatewayClearCommerce command = new UpdatePaymentGatewayClearCommerce(paymentGatewayClearCommerceToBeUpdated);

		try {
			if(((PaymentGatewayClearCommerceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayClearCommerceId}")
	public ResponseEntity<PaymentGatewayClearCommerce> findById(@PathVariable String paymentGatewayClearCommerceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayClearCommerceId", paymentGatewayClearCommerceId);
		try {

			List<PaymentGatewayClearCommerce> foundPaymentGatewayClearCommerce = findPaymentGatewayClearCommercesBy(requestParams).getBody();
			if(foundPaymentGatewayClearCommerce.size()==1){				return successful(foundPaymentGatewayClearCommerce.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayClearCommerceId}")
	public ResponseEntity<String> deletePaymentGatewayClearCommerceByIdUpdated(@PathVariable String paymentGatewayClearCommerceId) throws Exception {
		DeletePaymentGatewayClearCommerce command = new DeletePaymentGatewayClearCommerce(paymentGatewayClearCommerceId);

		try {
			if (((PaymentGatewayClearCommerceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
