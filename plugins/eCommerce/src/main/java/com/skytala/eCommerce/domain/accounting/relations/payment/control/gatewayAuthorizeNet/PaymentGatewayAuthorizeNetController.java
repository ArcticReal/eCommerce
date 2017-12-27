package com.skytala.eCommerce.domain.accounting.relations.payment.control.gatewayAuthorizeNet;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayAuthorizeNet.AddPaymentGatewayAuthorizeNet;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayAuthorizeNet.DeletePaymentGatewayAuthorizeNet;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayAuthorizeNet.UpdatePaymentGatewayAuthorizeNet;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayAuthorizeNet.FindPaymentGatewayAuthorizeNetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentGatewayAuthorizeNets")
public class PaymentGatewayAuthorizeNetController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentGatewayAuthorizeNetController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentGatewayAuthorizeNet
	 * @return a List with the PaymentGatewayAuthorizeNets
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentGatewayAuthorizeNet>> findPaymentGatewayAuthorizeNetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayAuthorizeNetsBy query = new FindPaymentGatewayAuthorizeNetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayAuthorizeNet> paymentGatewayAuthorizeNets =((PaymentGatewayAuthorizeNetFound) Scheduler.execute(query).data()).getPaymentGatewayAuthorizeNets();

		return ResponseEntity.ok().body(paymentGatewayAuthorizeNets);

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
	public ResponseEntity<PaymentGatewayAuthorizeNet> createPaymentGatewayAuthorizeNet(HttpServletRequest request) throws Exception {

		PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeAdded = new PaymentGatewayAuthorizeNet();
		try {
			paymentGatewayAuthorizeNetToBeAdded = PaymentGatewayAuthorizeNetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayAuthorizeNet entry in the ofbiz database
	 * 
	 * @param paymentGatewayAuthorizeNetToBeAdded
	 *            the PaymentGatewayAuthorizeNet thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentGatewayAuthorizeNet> createPaymentGatewayAuthorizeNet(@RequestBody PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeAdded) throws Exception {

		AddPaymentGatewayAuthorizeNet command = new AddPaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetToBeAdded);
		PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNet = ((PaymentGatewayAuthorizeNetAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayAuthorizeNet();
		
		if (paymentGatewayAuthorizeNet != null) 
			return successful(paymentGatewayAuthorizeNet);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentGatewayAuthorizeNet with the specific Id
	 * 
	 * @param paymentGatewayAuthorizeNetToBeUpdated
	 *            the PaymentGatewayAuthorizeNet thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentGatewayAuthorizeNet(@RequestBody PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayAuthorizeNetToBeUpdated.setnull(null);

		UpdatePaymentGatewayAuthorizeNet command = new UpdatePaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetToBeUpdated);

		try {
			if(((PaymentGatewayAuthorizeNetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentGatewayAuthorizeNetId}")
	public ResponseEntity<PaymentGatewayAuthorizeNet> findById(@PathVariable String paymentGatewayAuthorizeNetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayAuthorizeNetId", paymentGatewayAuthorizeNetId);
		try {

			List<PaymentGatewayAuthorizeNet> foundPaymentGatewayAuthorizeNet = findPaymentGatewayAuthorizeNetsBy(requestParams).getBody();
			if(foundPaymentGatewayAuthorizeNet.size()==1){				return successful(foundPaymentGatewayAuthorizeNet.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentGatewayAuthorizeNetId}")
	public ResponseEntity<String> deletePaymentGatewayAuthorizeNetByIdUpdated(@PathVariable String paymentGatewayAuthorizeNetId) throws Exception {
		DeletePaymentGatewayAuthorizeNet command = new DeletePaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetId);

		try {
			if (((PaymentGatewayAuthorizeNetDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
