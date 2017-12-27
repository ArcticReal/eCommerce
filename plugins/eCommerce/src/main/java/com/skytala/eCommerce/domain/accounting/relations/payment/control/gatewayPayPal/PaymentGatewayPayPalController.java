package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayPayPal;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayPal.AddPaymentGatewayPayPal;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayPal.DeletePaymentGatewayPayPal;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayPal.UpdatePaymentGatewayPayPal;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayPal.PaymentGatewayPayPalAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayPal.PaymentGatewayPayPalDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayPal.PaymentGatewayPayPalFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayPal.PaymentGatewayPayPalUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayPal.PaymentGatewayPayPalMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayPal.PaymentGatewayPayPal;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayPayPal.FindPaymentGatewayPayPalsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayPayPals")
public class PaymentGatewayPayPalController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayPayPalController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayPayPal
	 * @return a List with the PaymentGatewayPayPals
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayPayPal>> findPaymentGatewayPayPalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayPayPalsBy query = new FindPaymentGatewayPayPalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayPayPal> paymentGatewayPayPals =((PaymentGatewayPayPalFound) Scheduler.execute(query).data()).getPaymentGatewayPayPals();

		return ResponseEntity.ok().body(paymentGatewayPayPals);

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
	public ResponseEntity<PaymentGatewayPayPal> createPaymentGatewayPayPal(HttpServletRequest request) throws Exception {

		PaymentGatewayPayPal paymentGatewayPayPalToBeAdded = new PaymentGatewayPayPal();
		try {
			paymentGatewayPayPalToBeAdded = PaymentGatewayPayPalMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentGatewayPayPal(paymentGatewayPayPalToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayPayPal entry in the ofbiz database
	 * 
	 * @param paymentGatewayPayPalToBeAdded
	 *            the PaymentGatewayPayPal thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayPayPal> createPaymentGatewayPayPal(@RequestBody PaymentGatewayPayPal paymentGatewayPayPalToBeAdded) throws Exception {

		AddPaymentGatewayPayPal command = new AddPaymentGatewayPayPal(paymentGatewayPayPalToBeAdded);
		PaymentGatewayPayPal paymentGatewayPayPal = ((PaymentGatewayPayPalAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayPayPal();
		
		if (paymentGatewayPayPal != null) 
			return successful(paymentGatewayPayPal);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayPayPal with the specific Id
	 * 
	 * @param paymentGatewayPayPalToBeUpdated
	 *            the PaymentGatewayPayPal thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayPayPal(@RequestBody PaymentGatewayPayPal paymentGatewayPayPalToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayPayPalToBeUpdated.setnull(null);

		UpdatePaymentGatewayPayPal command = new UpdatePaymentGatewayPayPal(paymentGatewayPayPalToBeUpdated);

		try {
			if(((PaymentGatewayPayPalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayPayPalId}")
	public ResponseEntity<PaymentGatewayPayPal> findById(@PathVariable String paymentGatewayPayPalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayPayPalId", paymentGatewayPayPalId);
		try {

			List<PaymentGatewayPayPal> foundPaymentGatewayPayPal = findPaymentGatewayPayPalsBy(requestParams).getBody();
			if(foundPaymentGatewayPayPal.size()==1){				return successful(foundPaymentGatewayPayPal.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayPayPalId}")
	public ResponseEntity<String> deletePaymentGatewayPayPalByIdUpdated(@PathVariable String paymentGatewayPayPalId) throws Exception {
		DeletePaymentGatewayPayPal command = new DeletePaymentGatewayPayPal(paymentGatewayPayPalId);

		try {
			if (((PaymentGatewayPayPalDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
