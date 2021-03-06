package com.skytala.eCommerce.domain.accounting.relations.payment.control.contentType;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.contentType.AddPaymentContentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.contentType.DeletePaymentContentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.contentType.UpdatePaymentContentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.contentType.PaymentContentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.contentType.PaymentContentTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.contentType.PaymentContentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.contentType.PaymentContentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.contentType.PaymentContentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.contentType.PaymentContentType;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.contentType.FindPaymentContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentContentTypes")
public class PaymentContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentContentType
	 * @return a List with the PaymentContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentContentType>> findPaymentContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentContentTypesBy query = new FindPaymentContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentContentType> paymentContentTypes =((PaymentContentTypeFound) Scheduler.execute(query).data()).getPaymentContentTypes();

		return ResponseEntity.ok().body(paymentContentTypes);

	}

	/**
	 * creates a new PaymentContentType entry in the ofbiz database
	 * 
	 * @param paymentContentTypeToBeAdded
	 *            the PaymentContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentContentType> createPaymentContentType(@RequestBody PaymentContentType paymentContentTypeToBeAdded) throws Exception {

		AddPaymentContentType command = new AddPaymentContentType(paymentContentTypeToBeAdded);
		PaymentContentType paymentContentType = ((PaymentContentTypeAdded) Scheduler.execute(command).data()).getAddedPaymentContentType();
		
		if (paymentContentType != null) 
			return successful(paymentContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentContentType with the specific Id
	 * 
	 * @param paymentContentTypeToBeUpdated
	 *            the PaymentContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{paymentContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentContentType(@RequestBody PaymentContentType paymentContentTypeToBeUpdated,
			@PathVariable String paymentContentTypeId) throws Exception {

		paymentContentTypeToBeUpdated.setPaymentContentTypeId(paymentContentTypeId);

		UpdatePaymentContentType command = new UpdatePaymentContentType(paymentContentTypeToBeUpdated);

		try {
			if(((PaymentContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentContentTypeId}")
	public ResponseEntity<PaymentContentType> findById(@PathVariable String paymentContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentContentTypeId", paymentContentTypeId);
		try {

			List<PaymentContentType> foundPaymentContentType = findPaymentContentTypesBy(requestParams).getBody();
			if(foundPaymentContentType.size()==1){				return successful(foundPaymentContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentContentTypeId}")
	public ResponseEntity<String> deletePaymentContentTypeByIdUpdated(@PathVariable String paymentContentTypeId) throws Exception {
		DeletePaymentContentType command = new DeletePaymentContentType(paymentContentTypeId);

		try {
			if (((PaymentContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
