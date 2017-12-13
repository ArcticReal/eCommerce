package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.creditCardType;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.creditCardType.AddCreditCardTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.creditCardType.DeleteCreditCardTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.creditCardType.UpdateCreditCardTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType.CreditCardTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType.CreditCardTypeGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType.CreditCardTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType.CreditCardTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.creditCardType.CreditCardTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.creditCardType.CreditCardTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.creditCardType.FindCreditCardTypeGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/glAccount/creditCardTypeGlAccounts")
public class CreditCardTypeGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CreditCardTypeGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CreditCardTypeGlAccount
	 * @return a List with the CreditCardTypeGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCreditCardTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCreditCardTypeGlAccountsBy query = new FindCreditCardTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CreditCardTypeGlAccount> creditCardTypeGlAccounts =((CreditCardTypeGlAccountFound) Scheduler.execute(query).data()).getCreditCardTypeGlAccounts();

		if (creditCardTypeGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(creditCardTypeGlAccounts.get(0));
		}

		return ResponseEntity.ok().body(creditCardTypeGlAccounts);

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
	public ResponseEntity<Object> createCreditCardTypeGlAccount(HttpServletRequest request) throws Exception {

		CreditCardTypeGlAccount creditCardTypeGlAccountToBeAdded = new CreditCardTypeGlAccount();
		try {
			creditCardTypeGlAccountToBeAdded = CreditCardTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCreditCardTypeGlAccount(creditCardTypeGlAccountToBeAdded);

	}

	/**
	 * creates a new CreditCardTypeGlAccount entry in the ofbiz database
	 * 
	 * @param creditCardTypeGlAccountToBeAdded
	 *            the CreditCardTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCreditCardTypeGlAccount(@RequestBody CreditCardTypeGlAccount creditCardTypeGlAccountToBeAdded) throws Exception {

		AddCreditCardTypeGlAccount command = new AddCreditCardTypeGlAccount(creditCardTypeGlAccountToBeAdded);
		CreditCardTypeGlAccount creditCardTypeGlAccount = ((CreditCardTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedCreditCardTypeGlAccount();
		
		if (creditCardTypeGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(creditCardTypeGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CreditCardTypeGlAccount could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateCreditCardTypeGlAccount(HttpServletRequest request) throws Exception {

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

		CreditCardTypeGlAccount creditCardTypeGlAccountToBeUpdated = new CreditCardTypeGlAccount();

		try {
			creditCardTypeGlAccountToBeUpdated = CreditCardTypeGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCreditCardTypeGlAccount(creditCardTypeGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CreditCardTypeGlAccount with the specific Id
	 * 
	 * @param creditCardTypeGlAccountToBeUpdated
	 *            the CreditCardTypeGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCreditCardTypeGlAccount(@RequestBody CreditCardTypeGlAccount creditCardTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		creditCardTypeGlAccountToBeUpdated.setnull(null);

		UpdateCreditCardTypeGlAccount command = new UpdateCreditCardTypeGlAccount(creditCardTypeGlAccountToBeUpdated);

		try {
			if(((CreditCardTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{creditCardTypeGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String creditCardTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("creditCardTypeGlAccountId", creditCardTypeGlAccountId);
		try {

			Object foundCreditCardTypeGlAccount = findCreditCardTypeGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCreditCardTypeGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{creditCardTypeGlAccountId}")
	public ResponseEntity<Object> deleteCreditCardTypeGlAccountByIdUpdated(@PathVariable String creditCardTypeGlAccountId) throws Exception {
		DeleteCreditCardTypeGlAccount command = new DeleteCreditCardTypeGlAccount(creditCardTypeGlAccountId);

		try {
			if (((CreditCardTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CreditCardTypeGlAccount could not be deleted");

	}

}
