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
	public ResponseEntity<Object> findFinAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountsBy query = new FindFinAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccount> finAccounts =((FinAccountFound) Scheduler.execute(query).data()).getFinAccounts();

		if (finAccounts.size() == 1) {
			return ResponseEntity.ok().body(finAccounts.get(0));
		}

		return ResponseEntity.ok().body(finAccounts);

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
	public ResponseEntity<Object> createFinAccount(HttpServletRequest request) throws Exception {

		FinAccount finAccountToBeAdded = new FinAccount();
		try {
			finAccountToBeAdded = FinAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccount(finAccountToBeAdded);

	}

	/**
	 * creates a new FinAccount entry in the ofbiz database
	 * 
	 * @param finAccountToBeAdded
	 *            the FinAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccount(@RequestBody FinAccount finAccountToBeAdded) throws Exception {

		AddFinAccount command = new AddFinAccount(finAccountToBeAdded);
		FinAccount finAccount = ((FinAccountAdded) Scheduler.execute(command).data()).getAddedFinAccount();
		
		if (finAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccount could not be created.");
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
	public boolean updateFinAccount(HttpServletRequest request) throws Exception {

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

		FinAccount finAccountToBeUpdated = new FinAccount();

		try {
			finAccountToBeUpdated = FinAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccount(finAccountToBeUpdated, finAccountToBeUpdated.getFinAccountId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccount(@RequestBody FinAccount finAccountToBeUpdated,
			@PathVariable String finAccountId) throws Exception {

		finAccountToBeUpdated.setFinAccountId(finAccountId);

		UpdateFinAccount command = new UpdateFinAccount(finAccountToBeUpdated);

		try {
			if(((FinAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{finAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountId", finAccountId);
		try {

			Object foundFinAccount = findFinAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{finAccountId}")
	public ResponseEntity<Object> deleteFinAccountByIdUpdated(@PathVariable String finAccountId) throws Exception {
		DeleteFinAccount command = new DeleteFinAccount(finAccountId);

		try {
			if (((FinAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccount could not be deleted");

	}

}
