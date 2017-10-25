package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.paymentMethodType;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType.AddPaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType.DeletePaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType.UpdatePaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentMethodType.PaymentMethodTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType.PaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.paymentMethodType.FindPaymentMethodTypeGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/paymentMethodTypeGlAccounts")
public class PaymentMethodTypeGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentMethodTypeGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentMethodTypeGlAccount
	 * @return a List with the PaymentMethodTypeGlAccounts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPaymentMethodTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentMethodTypeGlAccountsBy query = new FindPaymentMethodTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentMethodTypeGlAccount> paymentMethodTypeGlAccounts =((PaymentMethodTypeGlAccountFound) Scheduler.execute(query).data()).getPaymentMethodTypeGlAccounts();

		if (paymentMethodTypeGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(paymentMethodTypeGlAccounts.get(0));
		}

		return ResponseEntity.ok().body(paymentMethodTypeGlAccounts);

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
	public ResponseEntity<Object> createPaymentMethodTypeGlAccount(HttpServletRequest request) throws Exception {

		PaymentMethodTypeGlAccount paymentMethodTypeGlAccountToBeAdded = new PaymentMethodTypeGlAccount();
		try {
			paymentMethodTypeGlAccountToBeAdded = PaymentMethodTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPaymentMethodTypeGlAccount(paymentMethodTypeGlAccountToBeAdded);

	}

	/**
	 * creates a new PaymentMethodTypeGlAccount entry in the ofbiz database
	 * 
	 * @param paymentMethodTypeGlAccountToBeAdded
	 *            the PaymentMethodTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPaymentMethodTypeGlAccount(@RequestBody PaymentMethodTypeGlAccount paymentMethodTypeGlAccountToBeAdded) throws Exception {

		AddPaymentMethodTypeGlAccount command = new AddPaymentMethodTypeGlAccount(paymentMethodTypeGlAccountToBeAdded);
		PaymentMethodTypeGlAccount paymentMethodTypeGlAccount = ((PaymentMethodTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedPaymentMethodTypeGlAccount();
		
		if (paymentMethodTypeGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(paymentMethodTypeGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PaymentMethodTypeGlAccount could not be created.");
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
	public boolean updatePaymentMethodTypeGlAccount(HttpServletRequest request) throws Exception {

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

		PaymentMethodTypeGlAccount paymentMethodTypeGlAccountToBeUpdated = new PaymentMethodTypeGlAccount();

		try {
			paymentMethodTypeGlAccountToBeUpdated = PaymentMethodTypeGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePaymentMethodTypeGlAccount(paymentMethodTypeGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PaymentMethodTypeGlAccount with the specific Id
	 * 
	 * @param paymentMethodTypeGlAccountToBeUpdated
	 *            the PaymentMethodTypeGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePaymentMethodTypeGlAccount(@RequestBody PaymentMethodTypeGlAccount paymentMethodTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentMethodTypeGlAccountToBeUpdated.setnull(null);

		UpdatePaymentMethodTypeGlAccount command = new UpdatePaymentMethodTypeGlAccount(paymentMethodTypeGlAccountToBeUpdated);

		try {
			if(((PaymentMethodTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentMethodTypeGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String paymentMethodTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentMethodTypeGlAccountId", paymentMethodTypeGlAccountId);
		try {

			Object foundPaymentMethodTypeGlAccount = findPaymentMethodTypeGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPaymentMethodTypeGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{paymentMethodTypeGlAccountId}")
	public ResponseEntity<Object> deletePaymentMethodTypeGlAccountByIdUpdated(@PathVariable String paymentMethodTypeGlAccountId) throws Exception {
		DeletePaymentMethodTypeGlAccount command = new DeletePaymentMethodTypeGlAccount(paymentMethodTypeGlAccountId);

		try {
			if (((PaymentMethodTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PaymentMethodTypeGlAccount could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/paymentMethodTypeGlAccount/\" plus one of the following: "
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
