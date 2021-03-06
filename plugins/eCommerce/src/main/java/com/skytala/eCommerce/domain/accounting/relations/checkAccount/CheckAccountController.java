package com.skytala.eCommerce.domain.accounting.relations.checkAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.command.AddCheckAccount;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.command.DeleteCheckAccount;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.command.UpdateCheckAccount;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.event.CheckAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.event.CheckAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.event.CheckAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.event.CheckAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.mapper.CheckAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.query.FindCheckAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/checkAccounts")
public class CheckAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CheckAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CheckAccount
	 * @return a List with the CheckAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CheckAccount>> findCheckAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCheckAccountsBy query = new FindCheckAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CheckAccount> checkAccounts =((CheckAccountFound) Scheduler.execute(query).data()).getCheckAccounts();

		return ResponseEntity.ok().body(checkAccounts);

	}

	/**
	 * creates a new CheckAccount entry in the ofbiz database
	 * 
	 * @param checkAccountToBeAdded
	 *            the CheckAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CheckAccount> createCheckAccount(@RequestBody CheckAccount checkAccountToBeAdded) throws Exception {

		AddCheckAccount command = new AddCheckAccount(checkAccountToBeAdded);
		CheckAccount checkAccount = ((CheckAccountAdded) Scheduler.execute(command).data()).getAddedCheckAccount();
		
		if (checkAccount != null) 
			return successful(checkAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CheckAccount with the specific Id
	 * 
	 * @param checkAccountToBeUpdated
	 *            the CheckAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCheckAccount(@RequestBody CheckAccount checkAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		checkAccountToBeUpdated.setnull(null);

		UpdateCheckAccount command = new UpdateCheckAccount(checkAccountToBeUpdated);

		try {
			if(((CheckAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{checkAccountId}")
	public ResponseEntity<CheckAccount> findById(@PathVariable String checkAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("checkAccountId", checkAccountId);
		try {

			List<CheckAccount> foundCheckAccount = findCheckAccountsBy(requestParams).getBody();
			if(foundCheckAccount.size()==1){				return successful(foundCheckAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{checkAccountId}")
	public ResponseEntity<String> deleteCheckAccountByIdUpdated(@PathVariable String checkAccountId) throws Exception {
		DeleteCheckAccount command = new DeleteCheckAccount(checkAccountId);

		try {
			if (((CheckAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
