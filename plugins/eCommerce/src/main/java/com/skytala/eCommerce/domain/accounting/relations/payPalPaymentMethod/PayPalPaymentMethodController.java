package com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod;

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
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.command.AddPayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.command.DeletePayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.command.UpdatePayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event.PayPalPaymentMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event.PayPalPaymentMethodDeleted;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event.PayPalPaymentMethodFound;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event.PayPalPaymentMethodUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.mapper.PayPalPaymentMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.model.PayPalPaymentMethod;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.query.FindPayPalPaymentMethodsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/payPalPaymentMethods")
public class PayPalPaymentMethodController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PayPalPaymentMethodController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PayPalPaymentMethod
	 * @return a List with the PayPalPaymentMethods
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPayPalPaymentMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayPalPaymentMethodsBy query = new FindPayPalPaymentMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayPalPaymentMethod> payPalPaymentMethods =((PayPalPaymentMethodFound) Scheduler.execute(query).data()).getPayPalPaymentMethods();

		if (payPalPaymentMethods.size() == 1) {
			return ResponseEntity.ok().body(payPalPaymentMethods.get(0));
		}

		return ResponseEntity.ok().body(payPalPaymentMethods);

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
	public ResponseEntity<Object> createPayPalPaymentMethod(HttpServletRequest request) throws Exception {

		PayPalPaymentMethod payPalPaymentMethodToBeAdded = new PayPalPaymentMethod();
		try {
			payPalPaymentMethodToBeAdded = PayPalPaymentMethodMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPayPalPaymentMethod(payPalPaymentMethodToBeAdded);

	}

	/**
	 * creates a new PayPalPaymentMethod entry in the ofbiz database
	 * 
	 * @param payPalPaymentMethodToBeAdded
	 *            the PayPalPaymentMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPayPalPaymentMethod(@RequestBody PayPalPaymentMethod payPalPaymentMethodToBeAdded) throws Exception {

		AddPayPalPaymentMethod command = new AddPayPalPaymentMethod(payPalPaymentMethodToBeAdded);
		PayPalPaymentMethod payPalPaymentMethod = ((PayPalPaymentMethodAdded) Scheduler.execute(command).data()).getAddedPayPalPaymentMethod();
		
		if (payPalPaymentMethod != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(payPalPaymentMethod);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PayPalPaymentMethod could not be created.");
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
	public boolean updatePayPalPaymentMethod(HttpServletRequest request) throws Exception {

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

		PayPalPaymentMethod payPalPaymentMethodToBeUpdated = new PayPalPaymentMethod();

		try {
			payPalPaymentMethodToBeUpdated = PayPalPaymentMethodMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePayPalPaymentMethod(payPalPaymentMethodToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PayPalPaymentMethod with the specific Id
	 * 
	 * @param payPalPaymentMethodToBeUpdated
	 *            the PayPalPaymentMethod thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePayPalPaymentMethod(@RequestBody PayPalPaymentMethod payPalPaymentMethodToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		payPalPaymentMethodToBeUpdated.setnull(null);

		UpdatePayPalPaymentMethod command = new UpdatePayPalPaymentMethod(payPalPaymentMethodToBeUpdated);

		try {
			if(((PayPalPaymentMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{payPalPaymentMethodId}")
	public ResponseEntity<Object> findById(@PathVariable String payPalPaymentMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payPalPaymentMethodId", payPalPaymentMethodId);
		try {

			Object foundPayPalPaymentMethod = findPayPalPaymentMethodsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPayPalPaymentMethod);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{payPalPaymentMethodId}")
	public ResponseEntity<Object> deletePayPalPaymentMethodByIdUpdated(@PathVariable String payPalPaymentMethodId) throws Exception {
		DeletePayPalPaymentMethod command = new DeletePayPalPaymentMethod(payPalPaymentMethodId);

		try {
			if (((PayPalPaymentMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PayPalPaymentMethod could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/payPalPaymentMethod/\" plus one of the following: "
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
