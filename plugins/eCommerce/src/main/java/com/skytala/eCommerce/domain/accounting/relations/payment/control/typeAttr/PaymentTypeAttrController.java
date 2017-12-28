package com.skytala.eCommerce.domain.accounting.relations.payment.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.typeAttr.AddPaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.typeAttr.DeletePaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.typeAttr.UpdatePaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.typeAttr.PaymentTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.typeAttr.PaymentTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.typeAttr.PaymentTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.typeAttr.FindPaymentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentTypeAttrs")
public class PaymentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentTypeAttr
	 * @return a List with the PaymentTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentTypeAttr>> findPaymentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentTypeAttrsBy query = new FindPaymentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentTypeAttr> paymentTypeAttrs =((PaymentTypeAttrFound) Scheduler.execute(query).data()).getPaymentTypeAttrs();

		return ResponseEntity.ok().body(paymentTypeAttrs);

	}

	/**
	 * creates a new PaymentTypeAttr entry in the ofbiz database
	 * 
	 * @param paymentTypeAttrToBeAdded
	 *            the PaymentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentTypeAttr> createPaymentTypeAttr(@RequestBody PaymentTypeAttr paymentTypeAttrToBeAdded) throws Exception {

		AddPaymentTypeAttr command = new AddPaymentTypeAttr(paymentTypeAttrToBeAdded);
		PaymentTypeAttr paymentTypeAttr = ((PaymentTypeAttrAdded) Scheduler.execute(command).data()).getAddedPaymentTypeAttr();
		
		if (paymentTypeAttr != null) 
			return successful(paymentTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentTypeAttr with the specific Id
	 * 
	 * @param paymentTypeAttrToBeUpdated
	 *            the PaymentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentTypeAttr(@RequestBody PaymentTypeAttr paymentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		paymentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdatePaymentTypeAttr command = new UpdatePaymentTypeAttr(paymentTypeAttrToBeUpdated);

		try {
			if(((PaymentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentTypeAttrId}")
	public ResponseEntity<PaymentTypeAttr> findById(@PathVariable String paymentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentTypeAttrId", paymentTypeAttrId);
		try {

			List<PaymentTypeAttr> foundPaymentTypeAttr = findPaymentTypeAttrsBy(requestParams).getBody();
			if(foundPaymentTypeAttr.size()==1){				return successful(foundPaymentTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentTypeAttrId}")
	public ResponseEntity<String> deletePaymentTypeAttrByIdUpdated(@PathVariable String paymentTypeAttrId) throws Exception {
		DeletePaymentTypeAttr command = new DeletePaymentTypeAttr(paymentTypeAttrId);

		try {
			if (((PaymentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
