package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.typeGlAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeGlAccount.AddFinAccountTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeGlAccount.DeleteFinAccountTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeGlAccount.UpdateFinAccountTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount.FinAccountTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount.FinAccountTypeGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount.FinAccountTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount.FinAccountTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.typeGlAccount.FinAccountTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeGlAccount.FinAccountTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.typeGlAccount.FindFinAccountTypeGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTypeGlAccounts")
public class FinAccountTypeGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTypeGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTypeGlAccount
	 * @return a List with the FinAccountTypeGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountTypeGlAccount>> findFinAccountTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTypeGlAccountsBy query = new FindFinAccountTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTypeGlAccount> finAccountTypeGlAccounts =((FinAccountTypeGlAccountFound) Scheduler.execute(query).data()).getFinAccountTypeGlAccounts();

		return ResponseEntity.ok().body(finAccountTypeGlAccounts);

	}

	/**
	 * creates a new FinAccountTypeGlAccount entry in the ofbiz database
	 * 
	 * @param finAccountTypeGlAccountToBeAdded
	 *            the FinAccountTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountTypeGlAccount> createFinAccountTypeGlAccount(@RequestBody FinAccountTypeGlAccount finAccountTypeGlAccountToBeAdded) throws Exception {

		AddFinAccountTypeGlAccount command = new AddFinAccountTypeGlAccount(finAccountTypeGlAccountToBeAdded);
		FinAccountTypeGlAccount finAccountTypeGlAccount = ((FinAccountTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedFinAccountTypeGlAccount();
		
		if (finAccountTypeGlAccount != null) 
			return successful(finAccountTypeGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountTypeGlAccount with the specific Id
	 * 
	 * @param finAccountTypeGlAccountToBeUpdated
	 *            the FinAccountTypeGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountTypeGlAccount(@RequestBody FinAccountTypeGlAccount finAccountTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		finAccountTypeGlAccountToBeUpdated.setnull(null);

		UpdateFinAccountTypeGlAccount command = new UpdateFinAccountTypeGlAccount(finAccountTypeGlAccountToBeUpdated);

		try {
			if(((FinAccountTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTypeGlAccountId}")
	public ResponseEntity<FinAccountTypeGlAccount> findById(@PathVariable String finAccountTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTypeGlAccountId", finAccountTypeGlAccountId);
		try {

			List<FinAccountTypeGlAccount> foundFinAccountTypeGlAccount = findFinAccountTypeGlAccountsBy(requestParams).getBody();
			if(foundFinAccountTypeGlAccount.size()==1){				return successful(foundFinAccountTypeGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTypeGlAccountId}")
	public ResponseEntity<String> deleteFinAccountTypeGlAccountByIdUpdated(@PathVariable String finAccountTypeGlAccountId) throws Exception {
		DeleteFinAccountTypeGlAccount command = new DeleteFinAccountTypeGlAccount(finAccountTypeGlAccountId);

		try {
			if (((FinAccountTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
