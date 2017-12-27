package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayOrbital;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayOrbital.AddPaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayOrbital.DeletePaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayOrbital.UpdatePaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital.PaymentGatewayOrbitalAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital.PaymentGatewayOrbitalDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital.PaymentGatewayOrbitalFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital.PaymentGatewayOrbitalUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayOrbital.PaymentGatewayOrbitalMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayOrbital.PaymentGatewayOrbital;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayOrbital.FindPaymentGatewayOrbitalsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayOrbitals")
public class PaymentGatewayOrbitalController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayOrbitalController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayOrbital
	 * @return a List with the PaymentGatewayOrbitals
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayOrbital>> findPaymentGatewayOrbitalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayOrbitalsBy query = new FindPaymentGatewayOrbitalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayOrbital> paymentGatewayOrbitals =((PaymentGatewayOrbitalFound) Scheduler.execute(query).data()).getPaymentGatewayOrbitals();

		return ResponseEntity.ok().body(paymentGatewayOrbitals);

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
	public ResponseEntity<PaymentGatewayOrbital> createPaymentGatewayOrbital(HttpServletRequest request) throws Exception {

		PaymentGatewayOrbital paymentGatewayOrbitalToBeAdded = new PaymentGatewayOrbital();
		try {
			paymentGatewayOrbitalToBeAdded = PaymentGatewayOrbitalMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentGatewayOrbital(paymentGatewayOrbitalToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayOrbital entry in the ofbiz database
	 * 
	 * @param paymentGatewayOrbitalToBeAdded
	 *            the PaymentGatewayOrbital thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayOrbital> createPaymentGatewayOrbital(@RequestBody PaymentGatewayOrbital paymentGatewayOrbitalToBeAdded) throws Exception {

		AddPaymentGatewayOrbital command = new AddPaymentGatewayOrbital(paymentGatewayOrbitalToBeAdded);
		PaymentGatewayOrbital paymentGatewayOrbital = ((PaymentGatewayOrbitalAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayOrbital();
		
		if (paymentGatewayOrbital != null) 
			return successful(paymentGatewayOrbital);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayOrbital with the specific Id
	 * 
	 * @param paymentGatewayOrbitalToBeUpdated
	 *            the PaymentGatewayOrbital thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayOrbital(@RequestBody PaymentGatewayOrbital paymentGatewayOrbitalToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayOrbitalToBeUpdated.setnull(null);

		UpdatePaymentGatewayOrbital command = new UpdatePaymentGatewayOrbital(paymentGatewayOrbitalToBeUpdated);

		try {
			if(((PaymentGatewayOrbitalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayOrbitalId}")
	public ResponseEntity<PaymentGatewayOrbital> findById(@PathVariable String paymentGatewayOrbitalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayOrbitalId", paymentGatewayOrbitalId);
		try {

			List<PaymentGatewayOrbital> foundPaymentGatewayOrbital = findPaymentGatewayOrbitalsBy(requestParams).getBody();
			if(foundPaymentGatewayOrbital.size()==1){				return successful(foundPaymentGatewayOrbital.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayOrbitalId}")
	public ResponseEntity<String> deletePaymentGatewayOrbitalByIdUpdated(@PathVariable String paymentGatewayOrbitalId) throws Exception {
		DeletePaymentGatewayOrbital command = new DeletePaymentGatewayOrbital(paymentGatewayOrbitalId);

		try {
			if (((PaymentGatewayOrbitalDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
