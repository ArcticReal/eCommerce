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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<CreditCardTypeGlAccount>> findCreditCardTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCreditCardTypeGlAccountsBy query = new FindCreditCardTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CreditCardTypeGlAccount> creditCardTypeGlAccounts =((CreditCardTypeGlAccountFound) Scheduler.execute(query).data()).getCreditCardTypeGlAccounts();

		return ResponseEntity.ok().body(creditCardTypeGlAccounts);

	}

	/**
	 * creates a new CreditCardTypeGlAccount entry in the ofbiz database
	 * 
	 * @param creditCardTypeGlAccountToBeAdded
	 *            the CreditCardTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CreditCardTypeGlAccount> createCreditCardTypeGlAccount(@RequestBody CreditCardTypeGlAccount creditCardTypeGlAccountToBeAdded) throws Exception {

		AddCreditCardTypeGlAccount command = new AddCreditCardTypeGlAccount(creditCardTypeGlAccountToBeAdded);
		CreditCardTypeGlAccount creditCardTypeGlAccount = ((CreditCardTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedCreditCardTypeGlAccount();
		
		if (creditCardTypeGlAccount != null) 
			return successful(creditCardTypeGlAccount);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCreditCardTypeGlAccount(@RequestBody CreditCardTypeGlAccount creditCardTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		creditCardTypeGlAccountToBeUpdated.setnull(null);

		UpdateCreditCardTypeGlAccount command = new UpdateCreditCardTypeGlAccount(creditCardTypeGlAccountToBeUpdated);

		try {
			if(((CreditCardTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{creditCardTypeGlAccountId}")
	public ResponseEntity<CreditCardTypeGlAccount> findById(@PathVariable String creditCardTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("creditCardTypeGlAccountId", creditCardTypeGlAccountId);
		try {

			List<CreditCardTypeGlAccount> foundCreditCardTypeGlAccount = findCreditCardTypeGlAccountsBy(requestParams).getBody();
			if(foundCreditCardTypeGlAccount.size()==1){				return successful(foundCreditCardTypeGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{creditCardTypeGlAccountId}")
	public ResponseEntity<String> deleteCreditCardTypeGlAccountByIdUpdated(@PathVariable String creditCardTypeGlAccountId) throws Exception {
		DeleteCreditCardTypeGlAccount command = new DeleteCreditCardTypeGlAccount(creditCardTypeGlAccountId);

		try {
			if (((CreditCardTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
