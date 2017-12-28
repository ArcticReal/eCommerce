package com.skytala.eCommerce.domain.accounting.relations.eftAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.command.AddEftAccount;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.command.DeleteEftAccount;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.command.UpdateEftAccount;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.mapper.EftAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.query.FindEftAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/eftAccounts")
public class EftAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EftAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EftAccount
	 * @return a List with the EftAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EftAccount>> findEftAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEftAccountsBy query = new FindEftAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EftAccount> eftAccounts =((EftAccountFound) Scheduler.execute(query).data()).getEftAccounts();

		return ResponseEntity.ok().body(eftAccounts);

	}

	/**
	 * creates a new EftAccount entry in the ofbiz database
	 * 
	 * @param eftAccountToBeAdded
	 *            the EftAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EftAccount> createEftAccount(@RequestBody EftAccount eftAccountToBeAdded) throws Exception {

		AddEftAccount command = new AddEftAccount(eftAccountToBeAdded);
		EftAccount eftAccount = ((EftAccountAdded) Scheduler.execute(command).data()).getAddedEftAccount();
		
		if (eftAccount != null) 
			return successful(eftAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EftAccount with the specific Id
	 * 
	 * @param eftAccountToBeUpdated
	 *            the EftAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEftAccount(@RequestBody EftAccount eftAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		eftAccountToBeUpdated.setnull(null);

		UpdateEftAccount command = new UpdateEftAccount(eftAccountToBeUpdated);

		try {
			if(((EftAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{eftAccountId}")
	public ResponseEntity<EftAccount> findById(@PathVariable String eftAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("eftAccountId", eftAccountId);
		try {

			List<EftAccount> foundEftAccount = findEftAccountsBy(requestParams).getBody();
			if(foundEftAccount.size()==1){				return successful(foundEftAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{eftAccountId}")
	public ResponseEntity<String> deleteEftAccountByIdUpdated(@PathVariable String eftAccountId) throws Exception {
		DeleteEftAccount command = new DeleteEftAccount(eftAccountId);

		try {
			if (((EftAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
