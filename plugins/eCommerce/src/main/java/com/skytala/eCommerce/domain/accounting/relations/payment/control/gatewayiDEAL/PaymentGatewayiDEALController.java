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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayiDEALs")
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
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayiDEAL>> findPaymentGatewayiDEALsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayiDEALsBy query = new FindPaymentGatewayiDEALsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayiDEAL> paymentGatewayiDEALs =((PaymentGatewayiDEALFound) Scheduler.execute(query).data()).getPaymentGatewayiDEALs();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PaymentGatewayiDEAL> createPaymentGatewayiDEAL(HttpServletRequest request) throws Exception {

		PaymentGatewayiDEAL paymentGatewayiDEALToBeAdded = new PaymentGatewayiDEAL();
		try {
			paymentGatewayiDEALToBeAdded = PaymentGatewayiDEALMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PaymentGatewayiDEAL> createPaymentGatewayiDEAL(@RequestBody PaymentGatewayiDEAL paymentGatewayiDEALToBeAdded) throws Exception {

		AddPaymentGatewayiDEAL command = new AddPaymentGatewayiDEAL(paymentGatewayiDEALToBeAdded);
		PaymentGatewayiDEAL paymentGatewayiDEAL = ((PaymentGatewayiDEALAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayiDEAL();
		
		if (paymentGatewayiDEAL != null) 
			return successful(paymentGatewayiDEAL);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePaymentGatewayiDEAL(@RequestBody PaymentGatewayiDEAL paymentGatewayiDEALToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayiDEALToBeUpdated.setnull(null);

		UpdatePaymentGatewayiDEAL command = new UpdatePaymentGatewayiDEAL(paymentGatewayiDEALToBeUpdated);

		try {
			if(((PaymentGatewayiDEALUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayiDEALId}")
	public ResponseEntity<PaymentGatewayiDEAL> findById(@PathVariable String paymentGatewayiDEALId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayiDEALId", paymentGatewayiDEALId);
		try {

			List<PaymentGatewayiDEAL> foundPaymentGatewayiDEAL = findPaymentGatewayiDEALsBy(requestParams).getBody();
			if(foundPaymentGatewayiDEAL.size()==1){				return successful(foundPaymentGatewayiDEAL.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayiDEALId}")
	public ResponseEntity<String> deletePaymentGatewayiDEALByIdUpdated(@PathVariable String paymentGatewayiDEALId) throws Exception {
		DeletePaymentGatewayiDEAL command = new DeletePaymentGatewayiDEAL(paymentGatewayiDEALId);

		try {
			if (((PaymentGatewayiDEALDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
