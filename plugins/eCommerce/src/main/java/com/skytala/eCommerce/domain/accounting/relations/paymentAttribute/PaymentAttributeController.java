package com.skytala.eCommerce.domain.accounting.relations.paymentAttribute;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.command.AddPaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.command.DeletePaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.command.UpdatePaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event.PaymentAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event.PaymentAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event.PaymentAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event.PaymentAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.mapper.PaymentAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.model.PaymentAttribute;
import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.query.FindPaymentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/paymentAttributes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentAttributesBy query = new FindPaymentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentAttribute> paymentAttributes =((PaymentAttributeFound) Scheduler.execute(query).data()).getPaymentAttributes();

		if (paymentAttributes.size() == 1) {
			return ResponseEntity.ok().body(paymentAttributes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPaymentAttribute(HttpServletRequest request) throws Exception {

		PaymentAttribute paymentAttributeToBeAdded = new PaymentAttribute();
		try {
			paymentAttributeToBeAdded = PaymentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPaymentAttribute(@RequestBody PaymentAttribute paymentAttributeToBeAdded) throws Exception {

		AddPaymentAttribute command = new AddPaymentAttribute(paymentAttributeToBeAdded);
		PaymentAttribute paymentAttribute = ((PaymentAttributeAdded) Scheduler.execute(command).data()).getAddedPaymentAttribute();
		
		if (paymentAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentAttribute could not be created.");
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
	public boolean updatePaymentAttribute(HttpServletRequest request) throws Exception {

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

		PaymentAttribute paymentAttributeToBeUpdated = new PaymentAttribute();

		try {
			paymentAttributeToBeUpdated = PaymentAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentAttribute(paymentAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentAttribute with the specific Id
	 * 
	 * @param paymentAttributeToBeUpdated
	 *            the PaymentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentAttribute(@RequestBody PaymentAttribute paymentAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentAttributeToBeUpdated.setnull(null);

		UpdatePaymentAttribute command = new UpdatePaymentAttribute(paymentAttributeToBeUpdated);

		try {
			if(((PaymentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentAttributeId", paymentAttributeId);
		try {

			Object foundPaymentAttribute = findPaymentAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentAttributeId}")
	public ResponseEntity<Object> deletePaymentAttributeByIdUpdated(@PathVariable String paymentAttributeId) throws Exception {
		DeletePaymentAttribute command = new DeletePaymentAttribute(paymentAttributeId);

		try {
			if (((PaymentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentAttribute could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentAttribute/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}