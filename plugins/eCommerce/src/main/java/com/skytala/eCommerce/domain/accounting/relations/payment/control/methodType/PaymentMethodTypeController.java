package com.skytala.eCommerce.domain.accounting.relations.payment.control.methodType;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.methodType.AddPaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.methodType.DeletePaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.methodType.UpdatePaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType.PaymentMethodTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.methodType.PaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.methodType.PaymentMethodType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.methodType.FindPaymentMethodTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentMethodTypes")
public class PaymentMethodTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentMethodTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentMethodType
	 * @return a List with the PaymentMethodTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentMethodType>> findPaymentMethodTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentMethodTypesBy query = new FindPaymentMethodTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentMethodType> paymentMethodTypes =((PaymentMethodTypeFound) Scheduler.execute(query).data()).getPaymentMethodTypes();

		return ResponseEntity.ok().body(paymentMethodTypes);

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
	public ResponseEntity<PaymentMethodType> createPaymentMethodType(HttpServletRequest request) throws Exception {

		PaymentMethodType paymentMethodTypeToBeAdded = new PaymentMethodType();
		try {
			paymentMethodTypeToBeAdded = PaymentMethodTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentMethodType(paymentMethodTypeToBeAdded);

	}

	/**
	 * creates a new PaymentMethodType entry in the ofbiz database
	 * 
	 * @param paymentMethodTypeToBeAdded
	 *            the PaymentMethodType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentMethodType> createPaymentMethodType(@RequestBody PaymentMethodType paymentMethodTypeToBeAdded) throws Exception {

		AddPaymentMethodType command = new AddPaymentMethodType(paymentMethodTypeToBeAdded);
		PaymentMethodType paymentMethodType = ((PaymentMethodTypeAdded) Scheduler.execute(command).data()).getAddedPaymentMethodType();
		
		if (paymentMethodType != null) 
			return successful(paymentMethodType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentMethodType with the specific Id
	 * 
	 * @param paymentMethodTypeToBeUpdated
	 *            the PaymentMethodType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentMethodTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentMethodType(@RequestBody PaymentMethodType paymentMethodTypeToBeUpdated,
			@PathVariable String paymentMethodTypeId) throws Exception {

		paymentMethodTypeToBeUpdated.setPaymentMethodTypeId(paymentMethodTypeId);

		UpdatePaymentMethodType command = new UpdatePaymentMethodType(paymentMethodTypeToBeUpdated);

		try {
			if(((PaymentMethodTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentMethodTypeId}")
	public ResponseEntity<PaymentMethodType> findById(@PathVariable String paymentMethodTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentMethodTypeId", paymentMethodTypeId);
		try {

			List<PaymentMethodType> foundPaymentMethodType = findPaymentMethodTypesBy(requestParams).getBody();
			if(foundPaymentMethodType.size()==1){				return successful(foundPaymentMethodType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentMethodTypeId}")
	public ResponseEntity<String> deletePaymentMethodTypeByIdUpdated(@PathVariable String paymentMethodTypeId) throws Exception {
		DeletePaymentMethodType command = new DeletePaymentMethodType(paymentMethodTypeId);

		try {
			if (((PaymentMethodTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
