package com.skytala.eCommerce.domain.party.relations.party.control.carrierAccount;

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
import com.skytala.eCommerce.domain.party.relations.party.command.carrierAccount.AddPartyCarrierAccount;
import com.skytala.eCommerce.domain.party.relations.party.command.carrierAccount.DeletePartyCarrierAccount;
import com.skytala.eCommerce.domain.party.relations.party.command.carrierAccount.UpdatePartyCarrierAccount;
import com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount.PartyCarrierAccountAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount.PartyCarrierAccountDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount.PartyCarrierAccountFound;
import com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount.PartyCarrierAccountUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.carrierAccount.PartyCarrierAccountMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount.PartyCarrierAccount;
import com.skytala.eCommerce.domain.party.relations.party.query.carrierAccount.FindPartyCarrierAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyCarrierAccounts")
public class PartyCarrierAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyCarrierAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyCarrierAccount
	 * @return a List with the PartyCarrierAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyCarrierAccount>> findPartyCarrierAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyCarrierAccountsBy query = new FindPartyCarrierAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyCarrierAccount> partyCarrierAccounts =((PartyCarrierAccountFound) Scheduler.execute(query).data()).getPartyCarrierAccounts();

		return ResponseEntity.ok().body(partyCarrierAccounts);

	}

	/**
	 * creates a new PartyCarrierAccount entry in the ofbiz database
	 * 
	 * @param partyCarrierAccountToBeAdded
	 *            the PartyCarrierAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyCarrierAccount> createPartyCarrierAccount(@RequestBody PartyCarrierAccount partyCarrierAccountToBeAdded) throws Exception {

		AddPartyCarrierAccount command = new AddPartyCarrierAccount(partyCarrierAccountToBeAdded);
		PartyCarrierAccount partyCarrierAccount = ((PartyCarrierAccountAdded) Scheduler.execute(command).data()).getAddedPartyCarrierAccount();
		
		if (partyCarrierAccount != null) 
			return successful(partyCarrierAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyCarrierAccount with the specific Id
	 * 
	 * @param partyCarrierAccountToBeUpdated
	 *            the PartyCarrierAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyCarrierAccount(@RequestBody PartyCarrierAccount partyCarrierAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyCarrierAccountToBeUpdated.setnull(null);

		UpdatePartyCarrierAccount command = new UpdatePartyCarrierAccount(partyCarrierAccountToBeUpdated);

		try {
			if(((PartyCarrierAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyCarrierAccountId}")
	public ResponseEntity<PartyCarrierAccount> findById(@PathVariable String partyCarrierAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyCarrierAccountId", partyCarrierAccountId);
		try {

			List<PartyCarrierAccount> foundPartyCarrierAccount = findPartyCarrierAccountsBy(requestParams).getBody();
			if(foundPartyCarrierAccount.size()==1){				return successful(foundPartyCarrierAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyCarrierAccountId}")
	public ResponseEntity<String> deletePartyCarrierAccountByIdUpdated(@PathVariable String partyCarrierAccountId) throws Exception {
		DeletePartyCarrierAccount command = new DeletePartyCarrierAccount(partyCarrierAccountId);

		try {
			if (((PartyCarrierAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
