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
	public ResponseEntity<Object> findPaymentGatewayAuthorizeNetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayAuthorizeNetsBy query = new FindPaymentGatewayAuthorizeNetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayAuthorizeNet> paymentGatewayAuthorizeNets =((PaymentGatewayAuthorizeNetFound) Scheduler.execute(query).data()).getPaymentGatewayAuthorizeNets();

		if (paymentGatewayAuthorizeNets.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayAuthorizeNets.get(0));
		}

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
	public ResponseEntity<Object> createPaymentGatewayAuthorizeNet(HttpServletRequest request) throws Exception {

		PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeAdded = new PaymentGatewayAuthorizeNet();
		try {
			paymentGatewayAuthorizeNetToBeAdded = PaymentGatewayAuthorizeNetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPaymentGatewayAuthorizeNet(@RequestBody PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeAdded) throws Exception {

		AddPaymentGatewayAuthorizeNet command = new AddPaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetToBeAdded);
		PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNet = ((PaymentGatewayAuthorizeNetAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayAuthorizeNet();
		
		if (paymentGatewayAuthorizeNet != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayAuthorizeNet);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayAuthorizeNet could not be created.");
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
	public boolean updatePaymentGatewayAuthorizeNet(HttpServletRequest request) throws Exception {

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

		PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeUpdated = new PaymentGatewayAuthorizeNet();

		try {
			paymentGatewayAuthorizeNetToBeUpdated = PaymentGatewayAuthorizeNetMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayAuthorizeNet(@RequestBody PaymentGatewayAuthorizeNet paymentGatewayAuthorizeNetToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayAuthorizeNetToBeUpdated.setnull(null);

		UpdatePaymentGatewayAuthorizeNet command = new UpdatePaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetToBeUpdated);

		try {
			if(((PaymentGatewayAuthorizeNetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentGatewayAuthorizeNetId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayAuthorizeNetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayAuthorizeNetId", paymentGatewayAuthorizeNetId);
		try {

			Object foundPaymentGatewayAuthorizeNet = findPaymentGatewayAuthorizeNetsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayAuthorizeNet);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentGatewayAuthorizeNetId}")
	public ResponseEntity<Object> deletePaymentGatewayAuthorizeNetByIdUpdated(@PathVariable String paymentGatewayAuthorizeNetId) throws Exception {
		DeletePaymentGatewayAuthorizeNet command = new DeletePaymentGatewayAuthorizeNet(paymentGatewayAuthorizeNetId);

		try {
			if (((PaymentGatewayAuthorizeNetDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayAuthorizeNet could not be deleted");

	}

}
