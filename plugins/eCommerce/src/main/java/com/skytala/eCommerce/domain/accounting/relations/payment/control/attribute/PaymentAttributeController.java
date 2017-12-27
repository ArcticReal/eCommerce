package com.skytala.eCommerce.domain.accounting.relations.payment.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.payment.command.attribute.AddPaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.attribute.DeletePaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.payment.command.attribute.UpdatePaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.attribute.PaymentAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.payment.query.attribute.FindPaymentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/payment/paymentAttributes")
public class PaymentAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentAttribute
	 * @return a List with the PaymentAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentAttribute>> findPaymentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentAttributesBy query = new FindPaymentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentAttribute> paymentAttributes =((PaymentAttributeFound) Scheduler.execute(query).data()).getPaymentAttributes();

		return ResponseEntity.ok().body(paymentAttributes);

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
	public ResponseEntity<PaymentAttribute> createPaymentAttribute(HttpServletRequest request) throws Exception {

		PaymentAttribute paymentAttributeToBeAdded = new PaymentAttribute();
		try {
			paymentAttributeToBeAdded = PaymentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPaymentAttribute(paymentAttributeToBeAdded);

	}

	/**
	 * creates a new PaymentAttribute entry in the ofbiz database
	 * 
	 * @param paymentAttributeToBeAdded
	 *            the PaymentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentAttribute> createPaymentAttribute(@RequestBody PaymentAttribute paymentAttributeToBeAdded) throws Exception {

		AddPaymentAttribute command = new AddPaymentAttribute(paymentAttributeToBeAdded);
		PaymentAttribute paymentAttribute = ((PaymentAttributeAdded) Scheduler.execute(command).data()).getAddedPaymentAttribute();
		
		if (paymentAttribute != null) 
			return successful(paymentAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentAttribute with the specific Id
	 * 
	 * @param paymentAttributeToBeUpdated
	 *            the PaymentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentAttribute(@RequestBody PaymentAttribute paymentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		paymentAttributeToBeUpdated.setAttrName(attrName);

		UpdatePaymentAttribute command = new UpdatePaymentAttribute(paymentAttributeToBeUpdated);

		try {
			if(((PaymentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentAttributeId}")
	public ResponseEntity<PaymentAttribute> findById(@PathVariable String paymentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentAttributeId", paymentAttributeId);
		try {

			List<PaymentAttribute> foundPaymentAttribute = findPaymentAttributesBy(requestParams).getBody();
			if(foundPaymentAttribute.size()==1){				return successful(foundPaymentAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentAttributeId}")
	public ResponseEntity<String> deletePaymentAttributeByIdUpdated(@PathVariable String paymentAttributeId) throws Exception {
		DeletePaymentAttribute command = new DeletePaymentAttribute(paymentAttributeId);

		try {
			if (((PaymentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
