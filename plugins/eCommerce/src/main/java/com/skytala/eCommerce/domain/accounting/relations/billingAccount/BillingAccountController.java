package com.skytala.eCommerce.domain.accounting.relations.billingAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.AddBillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.DeleteBillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.UpdateBillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.BillingAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.FindBillingAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/billingAccounts")
public class BillingAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BillingAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BillingAccount
	 * @return a List with the BillingAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findBillingAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountsBy query = new FindBillingAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccount> billingAccounts =((BillingAccountFound) Scheduler.execute(query).data()).getBillingAccounts();

		if (billingAccounts.size() == 1) {
			return ResponseEntity.ok().body(billingAccounts.get(0));
		}

		return ResponseEntity.ok().body(billingAccounts);

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
	public ResponseEntity<Object> createBillingAccount(HttpServletRequest request) throws Exception {

		BillingAccount billingAccountToBeAdded = new BillingAccount();
		try {
			billingAccountToBeAdded = BillingAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBillingAccount(billingAccountToBeAdded);

	}

	/**
	 * creates a new BillingAccount entry in the ofbiz database
	 * 
	 * @param billingAccountToBeAdded
	 *            the BillingAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBillingAccount(@RequestBody BillingAccount billingAccountToBeAdded) throws Exception {

		AddBillingAccount command = new AddBillingAccount(billingAccountToBeAdded);
		BillingAccount billingAccount = ((BillingAccountAdded) Scheduler.execute(command).data()).getAddedBillingAccount();
		
		if (billingAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(billingAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BillingAccount could not be created.");
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
	public boolean updateBillingAccount(HttpServletRequest request) throws Exception {

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

		BillingAccount billingAccountToBeUpdated = new BillingAccount();

		try {
			billingAccountToBeUpdated = BillingAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBillingAccount(billingAccountToBeUpdated, billingAccountToBeUpdated.getBillingAccountId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BillingAccount with the specific Id
	 * 
	 * @param billingAccountToBeUpdated
	 *            the BillingAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{billingAccountId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBillingAccount(@RequestBody BillingAccount billingAccountToBeUpdated,
			@PathVariable String billingAccountId) throws Exception {

		billingAccountToBeUpdated.setBillingAccountId(billingAccountId);

		UpdateBillingAccount command = new UpdateBillingAccount(billingAccountToBeUpdated);

		try {
			if(((BillingAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{billingAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String billingAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountId", billingAccountId);
		try {

			Object foundBillingAccount = findBillingAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBillingAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{billingAccountId}")
	public ResponseEntity<Object> deleteBillingAccountByIdUpdated(@PathVariable String billingAccountId) throws Exception {
		DeleteBillingAccount command = new DeleteBillingAccount(billingAccountId);

		try {
			if (((BillingAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BillingAccount could not be deleted");

	}

}
