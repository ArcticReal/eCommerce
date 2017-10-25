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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/paymentContents")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentContentsBy query = new FindPaymentContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentContent> paymentContents =((PaymentContentFound) Scheduler.execute(query).data()).getPaymentContents();

		if (paymentContents.size() == 1) {
			return ResponseEntity.ok().body(paymentContents.get(0));
		}

		return ResponseEntity.ok().body(paymentContents);

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
	public ResponseEntity<Object> createPaymentContent(HttpServletRequest request) throws Exception {

		PaymentContent paymentContentToBeAdded = new PaymentContent();
		try {
			paymentContentToBeAdded = PaymentContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentContent(paymentContentToBeAdded);

	}

	/**
	 * creates a new PaymentContent entry in the ofbiz database
	 * 
	 * @param paymentContentToBeAdded
	 *            the PaymentContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentContent(@RequestBody PaymentContent paymentContentToBeAdded) throws Exception {

		AddPaymentContent command = new AddPaymentContent(paymentContentToBeAdded);
		PaymentContent paymentContent = ((PaymentContentAdded) Scheduler.execute(command).data()).getAddedPaymentContent();
		
		if (paymentContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentContent could not be created.");
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
	public boolean updatePaymentContent(HttpServletRequest request) throws Exception {

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

		PaymentContent paymentContentToBeUpdated = new PaymentContent();

		try {
			paymentContentToBeUpdated = PaymentContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentContent(paymentContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentContent(@RequestBody PaymentContent paymentContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentContentToBeUpdated.setnull(null);

		UpdatePaymentContent command = new UpdatePaymentContent(paymentContentToBeUpdated);

		try {
			if(((PaymentContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentContentId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentContentId", paymentContentId);
		try {

			Object foundPaymentContent = findPaymentContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentContentId}")
	public ResponseEntity<Object> deletePaymentContentByIdUpdated(@PathVariable String paymentContentId) throws Exception {
		DeletePaymentContent command = new DeletePaymentContent(paymentContentId);

		try {
			if (((PaymentContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentContent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentContent/\" plus one of the following: "
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
