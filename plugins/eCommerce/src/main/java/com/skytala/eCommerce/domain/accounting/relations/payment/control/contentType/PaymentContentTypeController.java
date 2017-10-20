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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/paymentContentTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentContentTypesBy query = new FindPaymentContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentContentType> paymentContentTypes =((PaymentContentTypeFound) Scheduler.execute(query).data()).getPaymentContentTypes();

		if (paymentContentTypes.size() == 1) {
			return ResponseEntity.ok().body(paymentContentTypes.get(0));
		}

		return ResponseEntity.ok().body(paymentContentTypes);

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
	public ResponseEntity<Object> createPaymentContentType(HttpServletRequest request) throws Exception {

		PaymentContentType paymentContentTypeToBeAdded = new PaymentContentType();
		try {
			paymentContentTypeToBeAdded = PaymentContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentContentType(paymentContentTypeToBeAdded);

	}

	/**
	 * creates a new PaymentContentType entry in the ofbiz database
	 * 
	 * @param paymentContentTypeToBeAdded
	 *            the PaymentContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentContentType(@RequestBody PaymentContentType paymentContentTypeToBeAdded) throws Exception {

		AddPaymentContentType command = new AddPaymentContentType(paymentContentTypeToBeAdded);
		PaymentContentType paymentContentType = ((PaymentContentTypeAdded) Scheduler.execute(command).data()).getAddedPaymentContentType();
		
		if (paymentContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentContentType could not be created.");
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
	public boolean updatePaymentContentType(HttpServletRequest request) throws Exception {

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

		PaymentContentType paymentContentTypeToBeUpdated = new PaymentContentType();

		try {
			paymentContentTypeToBeUpdated = PaymentContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentContentType(paymentContentTypeToBeUpdated, paymentContentTypeToBeUpdated.getPaymentContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePaymentContentType(@RequestBody PaymentContentType paymentContentTypeToBeUpdated,
			@PathVariable String paymentContentTypeId) throws Exception {

		paymentContentTypeToBeUpdated.setPaymentContentTypeId(paymentContentTypeId);

		UpdatePaymentContentType command = new UpdatePaymentContentType(paymentContentTypeToBeUpdated);

		try {
			if(((PaymentContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentContentTypeId", paymentContentTypeId);
		try {

			Object foundPaymentContentType = findPaymentContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentContentTypeId}")
	public ResponseEntity<Object> deletePaymentContentTypeByIdUpdated(@PathVariable String paymentContentTypeId) throws Exception {
		DeletePaymentContentType command = new DeletePaymentContentType(paymentContentTypeId);

		try {
			if (((PaymentContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentContentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentContentType/\" plus one of the following: "
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
