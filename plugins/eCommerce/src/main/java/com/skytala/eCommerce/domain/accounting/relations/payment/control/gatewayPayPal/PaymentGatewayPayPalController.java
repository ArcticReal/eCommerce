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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/paymentGatewayPayPals")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentGatewayPayPalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentGatewayPayPalsBy query = new FindPaymentGatewayPayPalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentGatewayPayPal> paymentGatewayPayPals =((PaymentGatewayPayPalFound) Scheduler.execute(query).data()).getPaymentGatewayPayPals();

		if (paymentGatewayPayPals.size() == 1) {
			return ResponseEntity.ok().body(paymentGatewayPayPals.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPaymentGatewayPayPal(HttpServletRequest request) throws Exception {

		PaymentGatewayPayPal paymentGatewayPayPalToBeAdded = new PaymentGatewayPayPal();
		try {
			paymentGatewayPayPalToBeAdded = PaymentGatewayPayPalMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPaymentGatewayPayPal(@RequestBody PaymentGatewayPayPal paymentGatewayPayPalToBeAdded) throws Exception {

		AddPaymentGatewayPayPal command = new AddPaymentGatewayPayPal(paymentGatewayPayPalToBeAdded);
		PaymentGatewayPayPal paymentGatewayPayPal = ((PaymentGatewayPayPalAdded) Scheduler.execute(command).data()).getAddedPaymentGatewayPayPal();
		
		if (paymentGatewayPayPal != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentGatewayPayPal);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentGatewayPayPal could not be created.");
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
	public boolean updatePaymentGatewayPayPal(HttpServletRequest request) throws Exception {

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

		PaymentGatewayPayPal paymentGatewayPayPalToBeUpdated = new PaymentGatewayPayPal();

		try {
			paymentGatewayPayPalToBeUpdated = PaymentGatewayPayPalMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentGatewayPayPal(paymentGatewayPayPalToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentGatewayPayPal(@RequestBody PaymentGatewayPayPal paymentGatewayPayPalToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentGatewayPayPalToBeUpdated.setnull(null);

		UpdatePaymentGatewayPayPal command = new UpdatePaymentGatewayPayPal(paymentGatewayPayPalToBeUpdated);

		try {
			if(((PaymentGatewayPayPalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentGatewayPayPalId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentGatewayPayPalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentGatewayPayPalId", paymentGatewayPayPalId);
		try {

			Object foundPaymentGatewayPayPal = findPaymentGatewayPayPalsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentGatewayPayPal);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentGatewayPayPalId}")
	public ResponseEntity<Object> deletePaymentGatewayPayPalByIdUpdated(@PathVariable String paymentGatewayPayPalId) throws Exception {
		DeletePaymentGatewayPayPal command = new DeletePaymentGatewayPayPal(paymentGatewayPayPalId);

		try {
			if (((PaymentGatewayPayPalDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentGatewayPayPal could not be deleted");

	}

}
