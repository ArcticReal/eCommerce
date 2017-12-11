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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findFinAccountTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTypeGlAccountsBy query = new FindFinAccountTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTypeGlAccount> finAccountTypeGlAccounts =((FinAccountTypeGlAccountFound) Scheduler.execute(query).data()).getFinAccountTypeGlAccounts();

		if (finAccountTypeGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(finAccountTypeGlAccounts.get(0));
		}

		return ResponseEntity.ok().body(finAccountTypeGlAccounts);

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
	public ResponseEntity<Object> createFinAccountTypeGlAccount(HttpServletRequest request) throws Exception {

		FinAccountTypeGlAccount finAccountTypeGlAccountToBeAdded = new FinAccountTypeGlAccount();
		try {
			finAccountTypeGlAccountToBeAdded = FinAccountTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountTypeGlAccount(finAccountTypeGlAccountToBeAdded);

	}

	/**
	 * creates a new FinAccountTypeGlAccount entry in the ofbiz database
	 * 
	 * @param finAccountTypeGlAccountToBeAdded
	 *            the FinAccountTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountTypeGlAccount(@RequestBody FinAccountTypeGlAccount finAccountTypeGlAccountToBeAdded) throws Exception {

		AddFinAccountTypeGlAccount command = new AddFinAccountTypeGlAccount(finAccountTypeGlAccountToBeAdded);
		FinAccountTypeGlAccount finAccountTypeGlAccount = ((FinAccountTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedFinAccountTypeGlAccount();
		
		if (finAccountTypeGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountTypeGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountTypeGlAccount could not be created.");
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
	public boolean updateFinAccountTypeGlAccount(HttpServletRequest request) throws Exception {

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

		FinAccountTypeGlAccount finAccountTypeGlAccountToBeUpdated = new FinAccountTypeGlAccount();

		try {
			finAccountTypeGlAccountToBeUpdated = FinAccountTypeGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountTypeGlAccount(finAccountTypeGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccountTypeGlAccount(@RequestBody FinAccountTypeGlAccount finAccountTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		finAccountTypeGlAccountToBeUpdated.setnull(null);

		UpdateFinAccountTypeGlAccount command = new UpdateFinAccountTypeGlAccount(finAccountTypeGlAccountToBeUpdated);

		try {
			if(((FinAccountTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{finAccountTypeGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTypeGlAccountId", finAccountTypeGlAccountId);
		try {

			Object foundFinAccountTypeGlAccount = findFinAccountTypeGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountTypeGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{finAccountTypeGlAccountId}")
	public ResponseEntity<Object> deleteFinAccountTypeGlAccountByIdUpdated(@PathVariable String finAccountTypeGlAccountId) throws Exception {
		DeleteFinAccountTypeGlAccount command = new DeleteFinAccountTypeGlAccount(finAccountTypeGlAccountId);

		try {
			if (((FinAccountTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountTypeGlAccount could not be deleted");

	}

}
