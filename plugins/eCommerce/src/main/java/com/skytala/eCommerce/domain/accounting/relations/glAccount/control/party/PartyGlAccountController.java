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
	public ResponseEntity<Object> findPartyGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyGlAccountsBy query = new FindPartyGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyGlAccount> partyGlAccounts =((PartyGlAccountFound) Scheduler.execute(query).data()).getPartyGlAccounts();

		if (partyGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(partyGlAccounts.get(0));
		}

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
	public ResponseEntity<Object> createPartyGlAccount(HttpServletRequest request) throws Exception {

		PartyGlAccount partyGlAccountToBeAdded = new PartyGlAccount();
		try {
			partyGlAccountToBeAdded = PartyGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPartyGlAccount(@RequestBody PartyGlAccount partyGlAccountToBeAdded) throws Exception {

		AddPartyGlAccount command = new AddPartyGlAccount(partyGlAccountToBeAdded);
		PartyGlAccount partyGlAccount = ((PartyGlAccountAdded) Scheduler.execute(command).data()).getAddedPartyGlAccount();
		
		if (partyGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyGlAccount could not be created.");
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
	public boolean updatePartyGlAccount(HttpServletRequest request) throws Exception {

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

		PartyGlAccount partyGlAccountToBeUpdated = new PartyGlAccount();

		try {
			partyGlAccountToBeUpdated = PartyGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyGlAccount(partyGlAccountToBeUpdated, partyGlAccountToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyGlAccount(@RequestBody PartyGlAccount partyGlAccountToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		partyGlAccountToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePartyGlAccount command = new UpdatePartyGlAccount(partyGlAccountToBeUpdated);

		try {
			if(((PartyGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String partyGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyGlAccountId", partyGlAccountId);
		try {

			Object foundPartyGlAccount = findPartyGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyGlAccountId}")
	public ResponseEntity<Object> deletePartyGlAccountByIdUpdated(@PathVariable String partyGlAccountId) throws Exception {
		DeletePartyGlAccount command = new DeletePartyGlAccount(partyGlAccountId);

		try {
			if (((PartyGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyGlAccount could not be deleted");

	}

}
