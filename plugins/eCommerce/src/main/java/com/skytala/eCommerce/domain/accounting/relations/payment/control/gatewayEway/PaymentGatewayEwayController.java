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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayEwaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayEwaysBy query = new FindPaymentGatewayEwaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayEway> paymentGatewayEways =((PaymentGatewayEwayFound) Scheduler.execute(query).data()).getPaymentGatewayEways();

		if (paymentGatewayEways.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayEways.get(0));
		}

		return ResponseEntity.ok().body(paymentGatewayEways);

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
	public ResponseEntity<Object> createPaymentGatewayEway(HttpServletRequest request) throws Exception {

		PaymentGatewayEway paymentGatewayEwayToBeAdded = new PaymentGatewayEway();
		try {
			paymentGatewayEwayToBeAdded = PaymentGatewayEwayMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentGatewayEway(paymentGatewayEwayToBeAdded);

	}

	/**
	 * creates a new PaymentGatewayEway entry in the ofbiz database
	 * 
	 * @param paymentGatewayEwayToBeAdded
	 *            the PaymentGatewayEway thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentGatewayEway(@RequestBody PaymentGatewayEway paymentGatewayEwayToBeAdded) throws Exception {

		AddPaymentGatewayEway command = new AddPaymentGatewayEway(paymentGatewayEwayToBeAdded);
		PaymentGatewayEway paymentGatewayEway = ((PaymentGatewayEwayAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayEway();
		
		if (paymentGatewayEway != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayEway);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayEway could not be created.");
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
	public boolean updatePaymentGatewayEway(HttpServletRequest request) throws Exception {

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

		PaymentGatewayEway paymentGatewayEwayToBeUpdated = new PaymentGatewayEway();

		try {
			paymentGatewayEwayToBeUpdated = PaymentGatewayEwayMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayEway(paymentGatewayEwayToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayEway(@RequestBody PaymentGatewayEway paymentGatewayEwayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayEwayToBeUpdated.setnull(null);

		UpdatePaymentGatewayEway command = new UpdatePaymentGatewayEway(paymentGatewayEwayToBeUpdated);

		try {
			if(((PaymentGatewayEwayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayEwayId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayEwayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayEwayId", paymentGatewayEwayId);
		try {

			Object foundPaymentGatewayEway = findPaymentGatewayEwaysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayEway);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayEwayId}")
	public ResponseEntity<Object> deletePaymentGatewayEwayByIdUpdated(@PathVariable String paymentGatewayEwayId) throws Exception {
		DeletePaymentGatewayEway command = new DeletePaymentGatewayEway(paymentGatewayEwayId);

		try {
			if (((PaymentGatewayEwayDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayEway could not be deleted");

	}

}
