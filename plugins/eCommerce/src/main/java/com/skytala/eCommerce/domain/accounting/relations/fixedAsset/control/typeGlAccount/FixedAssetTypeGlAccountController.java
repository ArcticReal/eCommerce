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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<FixedAssetTypeGlAccount>> findFixedAssetTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetTypeGlAccountsBy query = new FindFixedAssetTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetTypeGlAccount> fixedAssetTypeGlAccounts =((FixedAssetTypeGlAccountFound) Scheduler.execute(query).data()).getFixedAssetTypeGlAccounts();

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
	public ResponseEntity<FixedAssetTypeGlAccount> createFixedAssetTypeGlAccount(HttpServletRequest request) throws Exception {

		FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeAdded = new FixedAssetTypeGlAccount();
		try {
			fixedAssetTypeGlAccountToBeAdded = FixedAssetTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<FixedAssetTypeGlAccount> createFixedAssetTypeGlAccount(@RequestBody FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeAdded) throws Exception {

		AddFixedAssetTypeGlAccount command = new AddFixedAssetTypeGlAccount(fixedAssetTypeGlAccountToBeAdded);
		FixedAssetTypeGlAccount fixedAssetTypeGlAccount = ((FixedAssetTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedFixedAssetTypeGlAccount();
		
		if (fixedAssetTypeGlAccount != null) 
			return successful(fixedAssetTypeGlAccount);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFixedAssetTypeGlAccount(@RequestBody FixedAssetTypeGlAccount fixedAssetTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetTypeGlAccountToBeUpdated.setnull(null);

		UpdateFixedAssetTypeGlAccount command = new UpdateFixedAssetTypeGlAccount(fixedAssetTypeGlAccountToBeUpdated);

		try {
			if(((FixedAssetTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetTypeGlAccountId}")
	public ResponseEntity<FixedAssetTypeGlAccount> findById(@PathVariable String fixedAssetTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetTypeGlAccountId", fixedAssetTypeGlAccountId);
		try {

			List<FixedAssetTypeGlAccount> foundFixedAssetTypeGlAccount = findFixedAssetTypeGlAccountsBy(requestParams).getBody();
			if(foundFixedAssetTypeGlAccount.size()==1){				return successful(foundFixedAssetTypeGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetTypeGlAccountId}")
	public ResponseEntity<String> deleteFixedAssetTypeGlAccountByIdUpdated(@PathVariable String fixedAssetTypeGlAccountId) throws Exception {
		DeleteFixedAssetTypeGlAccount command = new DeleteFixedAssetTypeGlAccount(fixedAssetTypeGlAccountId);

		try {
			if (((FixedAssetTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
