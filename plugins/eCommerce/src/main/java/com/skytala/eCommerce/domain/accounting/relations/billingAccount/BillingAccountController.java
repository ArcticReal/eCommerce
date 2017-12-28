package com.skytala.eCommerce.domain.accounting.relations.billingAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.AddBillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.DeleteBillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.UpdateBillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.BillingAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.FindBillingAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/billingAccounts")
public class BillingAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BillingAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BillingAccount
	 * @return a List with the BillingAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BillingAccount>> findBillingAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountsBy query = new FindBillingAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccount> billingAccounts =((BillingAccountFound) Scheduler.execute(query).data()).getBillingAccounts();

		return ResponseEntity.ok().body(billingAccounts);

	}

	/**
	 * creates a new BillingAccount entry in the ofbiz database
	 * 
	 * @param billingAccountToBeAdded
	 *            the BillingAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BillingAccount> createBillingAccount(@RequestBody BillingAccount billingAccountToBeAdded) throws Exception {

		AddBillingAccount command = new AddBillingAccount(billingAccountToBeAdded);
		BillingAccount billingAccount = ((BillingAccountAdded) Scheduler.execute(command).data()).getAddedBillingAccount();
		
		if (billingAccount != null) 
			return successful(billingAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BillingAccount with the specific Id
	 * 
	 * @param billingAccountToBeUpdated
	 *            the BillingAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{billingAccountId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBillingAccount(@RequestBody BillingAccount billingAccountToBeUpdated,
			@PathVariable String billingAccountId) throws Exception {

		billingAccountToBeUpdated.setBillingAccountId(billingAccountId);

		UpdateBillingAccount command = new UpdateBillingAccount(billingAccountToBeUpdated);

		try {
			if(((BillingAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{billingAccountId}")
	public ResponseEntity<BillingAccount> findById(@PathVariable String billingAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountId", billingAccountId);
		try {

			List<BillingAccount> foundBillingAccount = findBillingAccountsBy(requestParams).getBody();
			if(foundBillingAccount.size()==1){				return successful(foundBillingAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{billingAccountId}")
	public ResponseEntity<String> deleteBillingAccountByIdUpdated(@PathVariable String billingAccountId) throws Exception {
		DeleteBillingAccount command = new DeleteBillingAccount(billingAccountId);

		try {
			if (((BillingAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
