package com.skytala.eCommerce.domain.accounting.relations.payment.control.method;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.method.AddPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.method.DeletePaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.method.UpdatePaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.method.PaymentMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.method.PaymentMethodDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.method.PaymentMethodFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.method.PaymentMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.method.PaymentMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.method.PaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.method.FindPaymentMethodsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/payment/paymentMethods")
public class PaymentMethodController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentMethodController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentMethod
	 * @return a List with the PaymentMethods
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPaymentMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentMethodsBy query = new FindPaymentMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentMethod> paymentMethods =((PaymentMethodFound) Scheduler.execute(query).data()).getPaymentMethods();

		if (paymentMethods.size() == 1) {
			return ResponseEntity.ok().body(paymentMethods.get(0));
		}

		return ResponseEntity.ok().body(paymentMethods);

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
	public ResponseEntity<Object> createPaymentMethod(HttpServletRequest request) throws Exception {

		PaymentMethod paymentMethodToBeAdded = new PaymentMethod();
		try {
			paymentMethodToBeAdded = PaymentMethodMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentMethod(paymentMethodToBeAdded);

	}

	/**
	 * creates a new PaymentMethod entry in the ofbiz database
	 * 
	 * @param paymentMethodToBeAdded
	 *            the PaymentMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentMethod(@RequestBody PaymentMethod paymentMethodToBeAdded) throws Exception {

		AddPaymentMethod command = new AddPaymentMethod(paymentMethodToBeAdded);
		PaymentMethod paymentMethod = ((PaymentMethodAdded) Scheduler.execute(command).data()).getAddedPaymentMethod();
		
		if (paymentMethod != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentMethod);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentMethod could not be created.");
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
	public boolean updatePaymentMethod(HttpServletRequest request) throws Exception {

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

		PaymentMethod paymentMethodToBeUpdated = new PaymentMethod();

		try {
			paymentMethodToBeUpdated = PaymentMethodMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentMethod(paymentMethodToBeUpdated, paymentMethodToBeUpdated.getPaymentMethodId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentMethod with the specific Id
	 * 
	 * @param paymentMethodToBeUpdated
	 *            the PaymentMethod thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentMethodId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentMethod(@RequestBody PaymentMethod paymentMethodToBeUpdated,
			@PathVariable String paymentMethodId) throws Exception {

		paymentMethodToBeUpdated.setPaymentMethodId(paymentMethodId);

		UpdatePaymentMethod command = new UpdatePaymentMethod(paymentMethodToBeUpdated);

		try {
			if(((PaymentMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{paymentMethodId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentMethodId", paymentMethodId);
		try {

			Object foundPaymentMethod = findPaymentMethodsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentMethod);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{paymentMethodId}")
	public ResponseEntity<Object> deletePaymentMethodByIdUpdated(@PathVariable String paymentMethodId) throws Exception {
		DeletePaymentMethod command = new DeletePaymentMethod(paymentMethodId);

		try {
			if (((PaymentMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentMethod could not be deleted");

	}

}
