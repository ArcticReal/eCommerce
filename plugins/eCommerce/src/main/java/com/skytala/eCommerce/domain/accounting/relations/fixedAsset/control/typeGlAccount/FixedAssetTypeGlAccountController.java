package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.typeGlAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeGlAccount.AddFixedAssetTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeGlAccount.DeleteFixedAssetTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeGlAccount.UpdateFixedAssetTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount.FixedAssetTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount.FixedAssetTypeGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount.FixedAssetTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount.FixedAssetTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeGlAccount.FixedAssetTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.typeGlAccount.FindFixedAssetTypeGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetTypeGlAccounts")
public class FixedAssetTypeGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetTypeGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetTypeGlAccount
	 * @return a List with the FixedAssetTypeGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findFixedAssetTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetTypeGlAccountsBy query = new FindFixedAssetTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetTypeGlAccount> fixedAssetTypeGlAccounts =((FixedAssetTypeGlAccountFound) Scheduler.execute(query).data()).getFixedAssetTypeGlAccounts();

		if (fixedAssetTypeGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(fixedAssetTypeGlAccounts.get(0));
		}

		return ResponseEntity.ok().body(fixedAssetTypeGlAccounts);

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
	public ResponseEntity<Object> createFixedAssetTypeGlAccount(HttpServletRequest request) throws Exception {

		FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeAdded = new FixedAssetTypeGlAccount();
		try {
			fixedAssetTypeGlAccountToBeAdded = FixedAssetTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFixedAssetTypeGlAccount(fixedAssetTypeGlAccountToBeAdded);

	}

	/**
	 * creates a new FixedAssetTypeGlAccount entry in the ofbiz database
	 * 
	 * @param fixedAssetTypeGlAccountToBeAdded
	 *            the FixedAssetTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFixedAssetTypeGlAccount(@RequestBody FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeAdded) throws Exception {

		AddFixedAssetTypeGlAccount command = new AddFixedAssetTypeGlAccount(fixedAssetTypeGlAccountToBeAdded);
		FixedAssetTypeGlAccount fixedAssetTypeGlAccount = ((FixedAssetTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedFixedAssetTypeGlAccount();
		
		if (fixedAssetTypeGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(fixedAssetTypeGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FixedAssetTypeGlAccount could not be created.");
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
	public boolean updateFixedAssetTypeGlAccount(HttpServletRequest request) throws Exception {

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

		FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeUpdated = new FixedAssetTypeGlAccount();

		try {
			fixedAssetTypeGlAccountToBeUpdated = FixedAssetTypeGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFixedAssetTypeGlAccount(fixedAssetTypeGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FixedAssetTypeGlAccount with the specific Id
	 * 
	 * @param fixedAssetTypeGlAccountToBeUpdated
	 *            the FixedAssetTypeGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFixedAssetTypeGlAccount(@RequestBody FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetTypeGlAccountToBeUpdated.setnull(null);

		UpdateFixedAssetTypeGlAccount command = new UpdateFixedAssetTypeGlAccount(fixedAssetTypeGlAccountToBeUpdated);

		try {
			if(((FixedAssetTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{fixedAssetTypeGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String fixedAssetTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetTypeGlAccountId", fixedAssetTypeGlAccountId);
		try {

			Object foundFixedAssetTypeGlAccount = findFixedAssetTypeGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFixedAssetTypeGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{fixedAssetTypeGlAccountId}")
	public ResponseEntity<Object> deleteFixedAssetTypeGlAccountByIdUpdated(@PathVariable String fixedAssetTypeGlAccountId) throws Exception {
		DeleteFixedAssetTypeGlAccount command = new DeleteFixedAssetTypeGlAccount(fixedAssetTypeGlAccountId);

		try {
			if (((FixedAssetTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FixedAssetTypeGlAccount could not be deleted");

	}

}
