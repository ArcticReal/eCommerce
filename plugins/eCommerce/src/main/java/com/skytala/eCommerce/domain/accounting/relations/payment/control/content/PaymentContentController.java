package com.skytala.eCommerce.domain.accounting.relations.payment.control.content;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.content.AddPaymentContent;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.content.DeletePaymentContent;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.content.UpdatePaymentContent;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.content.PaymentContentAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.content.PaymentContentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.content.PaymentContentFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.content.PaymentContentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.content.PaymentContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.content.PaymentContent;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.content.FindPaymentContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentContents")
public class PaymentContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentContent
	 * @return a List with the PaymentContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentContent>> findPaymentContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentContentsBy query = new FindPaymentContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentContent> paymentContents =((PaymentContentFound) Scheduler.execute(query).data()).getPaymentContents();

		return ResponseEntity.ok().body(paymentContents);

	}

	/**
	 * creates a new PaymentContent entry in the ofbiz database
	 * 
	 * @param paymentContentToBeAdded
	 *            the PaymentContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentContent> createPaymentContent(@RequestBody PaymentContent paymentContentToBeAdded) throws Exception {

		AddPaymentContent command = new AddPaymentContent(paymentContentToBeAdded);
		PaymentContent paymentContent = ((PaymentContentAdded) Scheduler.execute(command).data()).getAddedPaymentContent();
		
		if (paymentContent != null) 
			return successful(paymentContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentContent with the specific Id
	 * 
	 * @param paymentContentToBeUpdated
	 *            the PaymentContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentContent(@RequestBody PaymentContent paymentContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentContentToBeUpdated.setnull(null);

		UpdatePaymentContent command = new UpdatePaymentContent(paymentContentToBeUpdated);

		try {
			if(((PaymentContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentContentId}")
	public ResponseEntity<PaymentContent> findById(@PathVariable String paymentContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentContentId", paymentContentId);
		try {

			List<PaymentContent> foundPaymentContent = findPaymentContentsBy(requestParams).getBody();
			if(foundPaymentContent.size()==1){				return successful(foundPaymentContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentContentId}")
	public ResponseEntity<String> deletePaymentContentByIdUpdated(@PathVariable String paymentContentId) throws Exception {
		DeletePaymentContent command = new DeletePaymentContent(paymentContentId);

		try {
			if (((PaymentContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
