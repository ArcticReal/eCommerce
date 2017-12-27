package com.skytala.eCommerce.domain.accounting.relations.payment.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.type.AddPaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.type.DeletePaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.type.UpdatePaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.type.PaymentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.type.FindPaymentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentTypes")
public class PaymentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentType
	 * @return a List with the PaymentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentType>> findPaymentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentTypesBy query = new FindPaymentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentType> paymentTypes =((PaymentTypeFound) Scheduler.execute(query).data()).getPaymentTypes();

		return ResponseEntity.ok().body(paymentTypes);

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
	public ResponseEntity<PaymentType> createPaymentType(HttpServletRequest request) throws Exception {

		PaymentType paymentTypeToBeAdded = new PaymentType();
		try {
			paymentTypeToBeAdded = PaymentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentType(paymentTypeToBeAdded);

	}

	/**
	 * creates a new PaymentType entry in the ofbiz database
	 * 
	 * @param paymentTypeToBeAdded
	 *            the PaymentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentType> createPaymentType(@RequestBody PaymentType paymentTypeToBeAdded) throws Exception {

		AddPaymentType command = new AddPaymentType(paymentTypeToBeAdded);
		PaymentType paymentType = ((PaymentTypeAdded) Scheduler.execute(command).data()).getAddedPaymentType();
		
		if (paymentType != null) 
			return successful(paymentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentType with the specific Id
	 * 
	 * @param paymentTypeToBeUpdated
	 *            the PaymentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentType(@RequestBody PaymentType paymentTypeToBeUpdated,
			@PathVariable String paymentTypeId) throws Exception {

		paymentTypeToBeUpdated.setPaymentTypeId(paymentTypeId);

		UpdatePaymentType command = new UpdatePaymentType(paymentTypeToBeUpdated);

		try {
			if(((PaymentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentTypeId}")
	public ResponseEntity<PaymentType> findById(@PathVariable String paymentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentTypeId", paymentTypeId);
		try {

			List<PaymentType> foundPaymentType = findPaymentTypesBy(requestParams).getBody();
			if(foundPaymentType.size()==1){				return successful(foundPaymentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentTypeId}")
	public ResponseEntity<String> deletePaymentTypeByIdUpdated(@PathVariable String paymentTypeId) throws Exception {
		DeletePaymentType command = new DeletePaymentType(paymentTypeId);

		try {
			if (((PaymentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
