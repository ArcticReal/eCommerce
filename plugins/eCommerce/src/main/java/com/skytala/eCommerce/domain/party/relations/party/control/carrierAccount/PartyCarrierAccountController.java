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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/partyCarrierAccounts")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyCarrierAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyCarrierAccountsBy query = new FindPartyCarrierAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyCarrierAccount> partyCarrierAccounts =((PartyCarrierAccountFound) Scheduler.execute(query).data()).getPartyCarrierAccounts();

		if (partyCarrierAccounts.size() == 1) {
			return ResponseEntity.ok().body(partyCarrierAccounts.get(0));
		}

		return ResponseEntity.ok().body(partyCarrierAccounts);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPartyCarrierAccount(HttpServletRequest request) throws Exception {

		PartyCarrierAccount partyCarrierAccountToBeAdded = new PartyCarrierAccount();
		try {
			partyCarrierAccountToBeAdded = PartyCarrierAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyCarrierAccount(partyCarrierAccountToBeAdded);

	}

	/**
	 * creates a new PartyCarrierAccount entry in the ofbiz database
	 * 
	 * @param partyCarrierAccountToBeAdded
	 *            the PartyCarrierAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyCarrierAccount(@RequestBody PartyCarrierAccount partyCarrierAccountToBeAdded) throws Exception {

		AddPartyCarrierAccount command = new AddPartyCarrierAccount(partyCarrierAccountToBeAdded);
		PartyCarrierAccount partyCarrierAccount = ((PartyCarrierAccountAdded) Scheduler.execute(command).data()).getAddedPartyCarrierAccount();
		
		if (partyCarrierAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyCarrierAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyCarrierAccount could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePartyCarrierAccount(HttpServletRequest request) throws Exception {

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

		PartyCarrierAccount partyCarrierAccountToBeUpdated = new PartyCarrierAccount();

		try {
			partyCarrierAccountToBeUpdated = PartyCarrierAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyCarrierAccount(partyCarrierAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyCarrierAccount(@RequestBody PartyCarrierAccount partyCarrierAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyCarrierAccountToBeUpdated.setnull(null);

		UpdatePartyCarrierAccount command = new UpdatePartyCarrierAccount(partyCarrierAccountToBeUpdated);

		try {
			if(((PartyCarrierAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyCarrierAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String partyCarrierAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyCarrierAccountId", partyCarrierAccountId);
		try {

			Object foundPartyCarrierAccount = findPartyCarrierAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyCarrierAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyCarrierAccountId}")
	public ResponseEntity<Object> deletePartyCarrierAccountByIdUpdated(@PathVariable String partyCarrierAccountId) throws Exception {
		DeletePartyCarrierAccount command = new DeletePartyCarrierAccount(partyCarrierAccountId);

		try {
			if (((PartyCarrierAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyCarrierAccount could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyCarrierAccount/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
