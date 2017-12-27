package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.party;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.party.AddPartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.party.DeletePartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.party.UpdatePartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party.PartyGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party.PartyGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party.PartyGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party.PartyGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.party.PartyGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.party.PartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.party.FindPartyGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/partyGlAccounts")
public class PartyGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyGlAccount
	 * @return a List with the PartyGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyGlAccount>> findPartyGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyGlAccountsBy query = new FindPartyGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyGlAccount> partyGlAccounts =((PartyGlAccountFound) Scheduler.execute(query).data()).getPartyGlAccounts();

		return ResponseEntity.ok().body(partyGlAccounts);

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
	public ResponseEntity<PartyGlAccount> createPartyGlAccount(HttpServletRequest request) throws Exception {

		PartyGlAccount partyGlAccountToBeAdded = new PartyGlAccount();
		try {
			partyGlAccountToBeAdded = PartyGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyGlAccount(partyGlAccountToBeAdded);

	}

	/**
	 * creates a new PartyGlAccount entry in the ofbiz database
	 * 
	 * @param partyGlAccountToBeAdded
	 *            the PartyGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyGlAccount> createPartyGlAccount(@RequestBody PartyGlAccount partyGlAccountToBeAdded) throws Exception {

		AddPartyGlAccount command = new AddPartyGlAccount(partyGlAccountToBeAdded);
		PartyGlAccount partyGlAccount = ((PartyGlAccountAdded) Scheduler.execute(command).data()).getAddedPartyGlAccount();
		
		if (partyGlAccount != null) 
			return successful(partyGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyGlAccount with the specific Id
	 * 
	 * @param partyGlAccountToBeUpdated
	 *            the PartyGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyGlAccount(@RequestBody PartyGlAccount partyGlAccountToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		partyGlAccountToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePartyGlAccount command = new UpdatePartyGlAccount(partyGlAccountToBeUpdated);

		try {
			if(((PartyGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyGlAccountId}")
	public ResponseEntity<PartyGlAccount> findById(@PathVariable String partyGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyGlAccountId", partyGlAccountId);
		try {

			List<PartyGlAccount> foundPartyGlAccount = findPartyGlAccountsBy(requestParams).getBody();
			if(foundPartyGlAccount.size()==1){				return successful(foundPartyGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyGlAccountId}")
	public ResponseEntity<String> deletePartyGlAccountByIdUpdated(@PathVariable String partyGlAccountId) throws Exception {
		DeletePartyGlAccount command = new DeletePartyGlAccount(partyGlAccountId);

		try {
			if (((PartyGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
