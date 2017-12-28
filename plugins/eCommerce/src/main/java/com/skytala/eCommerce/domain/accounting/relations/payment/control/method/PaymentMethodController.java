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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PaymentMethod>> findPaymentMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentMethodsBy query = new FindPaymentMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentMethod> paymentMethods =((PaymentMethodFound) Scheduler.execute(query).data()).getPaymentMethods();

		return ResponseEntity.ok().body(paymentMethods);

	}

	/**
	 * creates a new PaymentMethod entry in the ofbiz database
	 * 
	 * @param paymentMethodToBeAdded
	 *            the PaymentMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethod paymentMethodToBeAdded) throws Exception {

		AddPaymentMethod command = new AddPaymentMethod(paymentMethodToBeAdded);
		PaymentMethod paymentMethod = ((PaymentMethodAdded) Scheduler.execute(command).data()).getAddedPaymentMethod();
		
		if (paymentMethod != null) 
			return successful(paymentMethod);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePaymentMethod(@RequestBody PaymentMethod paymentMethodToBeUpdated,
			@PathVariable String paymentMethodId) throws Exception {

		paymentMethodToBeUpdated.setPaymentMethodId(paymentMethodId);

		UpdatePaymentMethod command = new UpdatePaymentMethod(paymentMethodToBeUpdated);

		try {
			if(((PaymentMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentMethodId}")
	public ResponseEntity<PaymentMethod> findById(@PathVariable String paymentMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentMethodId", paymentMethodId);
		try {

			List<PaymentMethod> foundPaymentMethod = findPaymentMethodsBy(requestParams).getBody();
			if(foundPaymentMethod.size()==1){				return successful(foundPaymentMethod.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentMethodId}")
	public ResponseEntity<String> deletePaymentMethodByIdUpdated(@PathVariable String paymentMethodId) throws Exception {
		DeletePaymentMethod command = new DeletePaymentMethod(paymentMethodId);

		try {
			if (((PaymentMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
