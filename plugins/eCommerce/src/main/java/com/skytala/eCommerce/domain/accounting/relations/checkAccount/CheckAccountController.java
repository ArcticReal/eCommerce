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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCheckAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCheckAccountsBy query = new FindCheckAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CheckAccount> checkAccounts =((CheckAccountFound) Scheduler.execute(query).data()).getCheckAccounts();

		if (checkAccounts.size() == 1) {
			return ResponseEntity.ok().body(checkAccounts.get(0));
		}

		return ResponseEntity.ok().body(checkAccounts);

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
	public ResponseEntity<Object> createCheckAccount(HttpServletRequest request) throws Exception {

		CheckAccount checkAccountToBeAdded = new CheckAccount();
		try {
			checkAccountToBeAdded = CheckAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCheckAccount(checkAccountToBeAdded);

	}

	/**
	 * creates a new CheckAccount entry in the ofbiz database
	 * 
	 * @param checkAccountToBeAdded
	 *            the CheckAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCheckAccount(@RequestBody CheckAccount checkAccountToBeAdded) throws Exception {

		AddCheckAccount command = new AddCheckAccount(checkAccountToBeAdded);
		CheckAccount checkAccount = ((CheckAccountAdded) Scheduler.execute(command).data()).getAddedCheckAccount();
		
		if (checkAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(checkAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CheckAccount could not be created.");
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
	public boolean updateCheckAccount(HttpServletRequest request) throws Exception {

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

		CheckAccount checkAccountToBeUpdated = new CheckAccount();

		try {
			checkAccountToBeUpdated = CheckAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCheckAccount(checkAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCheckAccount(@RequestBody CheckAccount checkAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		checkAccountToBeUpdated.setnull(null);

		UpdateCheckAccount command = new UpdateCheckAccount(checkAccountToBeUpdated);

		try {
			if(((CheckAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{checkAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String checkAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("checkAccountId", checkAccountId);
		try {

			Object foundCheckAccount = findCheckAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCheckAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{checkAccountId}")
	public ResponseEntity<Object> deleteCheckAccountByIdUpdated(@PathVariable String checkAccountId) throws Exception {
		DeleteCheckAccount command = new DeleteCheckAccount(checkAccountId);

		try {
			if (((CheckAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CheckAccount could not be deleted");

	}

}
