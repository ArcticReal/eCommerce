package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.command.AddPartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.command.DeletePartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.command.UpdatePartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event.PartyGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event.PartyGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event.PartyGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event.PartyGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.mapper.PartyGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.query.FindPartyGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyGlAccounts")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

		if (updatePartyGlAccount(partyGlAccountToBeUpdated, null).getStatusCode()
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
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyGlAccount(@RequestBody PartyGlAccount partyGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyGlAccountToBeUpdated.setnull(null);

		UpdatePartyGlAccount command = new UpdatePartyGlAccount(partyGlAccountToBeUpdated);

		try {
			if(((PartyGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyGlAccountId}")
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyGlAccountId}")
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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyGlAccount/\" plus one of the following: "
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
