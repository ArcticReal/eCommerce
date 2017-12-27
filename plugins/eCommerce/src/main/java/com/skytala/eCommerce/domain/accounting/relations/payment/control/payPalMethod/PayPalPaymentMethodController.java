package com.skytala.eCommerce.domain.accounting.relations.payment.control.payPalMethod;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.payPalMethod.AddPayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.payPalMethod.DeletePayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.payPalMethod.UpdatePayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod.PayPalPaymentMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod.PayPalPaymentMethodDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod.PayPalPaymentMethodFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod.PayPalPaymentMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.payPalMethod.PayPalPaymentMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.payPalMethod.PayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.payPalMethod.FindPayPalPaymentMethodsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/payPalPaymentMethods")
public class PayPalPaymentMethodController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PayPalPaymentMethodController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PayPalPaymentMethod
	 * @return a List with the PayPalPaymentMethods
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PayPalPaymentMethod>> findPayPalPaymentMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayPalPaymentMethodsBy query = new FindPayPalPaymentMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayPalPaymentMethod> payPalPaymentMethods =((PayPalPaymentMethodFound) Scheduler.execute(query).data()).getPayPalPaymentMethods();

		return ResponseEntity.ok().body(payPalPaymentMethods);

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
	public ResponseEntity<PayPalPaymentMethod> createPayPalPaymentMethod(HttpServletRequest request) throws Exception {

		PayPalPaymentMethod payPalPaymentMethodToBeAdded = new PayPalPaymentMethod();
		try {
			payPalPaymentMethodToBeAdded = PayPalPaymentMethodMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPayPalPaymentMethod(payPalPaymentMethodToBeAdded);

	}

	/**
	 * creates a new PayPalPaymentMethod entry in the ofbiz database
	 * 
	 * @param payPalPaymentMethodToBeAdded
	 *            the PayPalPaymentMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PayPalPaymentMethod> createPayPalPaymentMethod(@RequestBody PayPalPaymentMethod payPalPaymentMethodToBeAdded) throws Exception {

		AddPayPalPaymentMethod command = new AddPayPalPaymentMethod(payPalPaymentMethodToBeAdded);
		PayPalPaymentMethod payPalPaymentMethod = ((PayPalPaymentMethodAdded) Scheduler.execute(command).data()).getAddedPayPalPaymentMethod();
		
		if (payPalPaymentMethod != null) 
			return successful(payPalPaymentMethod);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PayPalPaymentMethod with the specific Id
	 * 
	 * @param payPalPaymentMethodToBeUpdated
	 *            the PayPalPaymentMethod thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePayPalPaymentMethod(@RequestBody PayPalPaymentMethod payPalPaymentMethodToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		payPalPaymentMethodToBeUpdated.setnull(null);

		UpdatePayPalPaymentMethod command = new UpdatePayPalPaymentMethod(payPalPaymentMethodToBeUpdated);

		try {
			if(((PayPalPaymentMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{payPalPaymentMethodId}")
	public ResponseEntity<PayPalPaymentMethod> findById(@PathVariable String payPalPaymentMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payPalPaymentMethodId", payPalPaymentMethodId);
		try {

			List<PayPalPaymentMethod> foundPayPalPaymentMethod = findPayPalPaymentMethodsBy(requestParams).getBody();
			if(foundPayPalPaymentMethod.size()==1){				return successful(foundPayPalPaymentMethod.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{payPalPaymentMethodId}")
	public ResponseEntity<String> deletePayPalPaymentMethodByIdUpdated(@PathVariable String payPalPaymentMethodId) throws Exception {
		DeletePayPalPaymentMethod command = new DeletePayPalPaymentMethod(payPalPaymentMethodId);

		try {
			if (((PayPalPaymentMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
