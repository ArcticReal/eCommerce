package com.skytala.eCommerce.domain.accounting.relations.finAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.AddFinAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.DeleteFinAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.UpdateFinAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.FinAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.FindFinAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccounts")
public class FinAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccount
	 * @return a List with the FinAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccount>> findFinAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountsBy query = new FindFinAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccount> finAccounts =((FinAccountFound) Scheduler.execute(query).data()).getFinAccounts();

		return ResponseEntity.ok().body(finAccounts);

	}

	/**
	 * creates a new FinAccount entry in the ofbiz database
	 * 
	 * @param finAccountToBeAdded
	 *            the FinAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccount> createFinAccount(@RequestBody FinAccount finAccountToBeAdded) throws Exception {

		AddFinAccount command = new AddFinAccount(finAccountToBeAdded);
		FinAccount finAccount = ((FinAccountAdded) Scheduler.execute(command).data()).getAddedFinAccount();
		
		if (finAccount != null) 
			return successful(finAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccount with the specific Id
	 * 
	 * @param finAccountToBeUpdated
	 *            the FinAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{finAccountId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccount(@RequestBody FinAccount finAccountToBeUpdated,
			@PathVariable String finAccountId) throws Exception {

		finAccountToBeUpdated.setFinAccountId(finAccountId);

		UpdateFinAccount command = new UpdateFinAccount(finAccountToBeUpdated);

		try {
			if(((FinAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountId}")
	public ResponseEntity<FinAccount> findById(@PathVariable String finAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountId", finAccountId);
		try {

			List<FinAccount> foundFinAccount = findFinAccountsBy(requestParams).getBody();
			if(foundFinAccount.size()==1){				return successful(foundFinAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountId}")
	public ResponseEntity<String> deleteFinAccountByIdUpdated(@PathVariable String finAccountId) throws Exception {
		DeleteFinAccount command = new DeleteFinAccount(finAccountId);

		try {
			if (((FinAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
