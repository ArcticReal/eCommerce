package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.varianceReason;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason.AddVarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason.DeleteVarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason.UpdateVarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.varianceReason.VarianceReasonGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.varianceReason.FindVarianceReasonGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/varianceReasonGlAccounts")
public class VarianceReasonGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VarianceReasonGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a VarianceReasonGlAccount
	 * @return a List with the VarianceReasonGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<VarianceReasonGlAccount>> findVarianceReasonGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVarianceReasonGlAccountsBy query = new FindVarianceReasonGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VarianceReasonGlAccount> varianceReasonGlAccounts =((VarianceReasonGlAccountFound) Scheduler.execute(query).data()).getVarianceReasonGlAccounts();

		return ResponseEntity.ok().body(varianceReasonGlAccounts);

	}

	/**
	 * creates a new VarianceReasonGlAccount entry in the ofbiz database
	 * 
	 * @param varianceReasonGlAccountToBeAdded
	 *            the VarianceReasonGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<VarianceReasonGlAccount> createVarianceReasonGlAccount(@RequestBody VarianceReasonGlAccount varianceReasonGlAccountToBeAdded) throws Exception {

		AddVarianceReasonGlAccount command = new AddVarianceReasonGlAccount(varianceReasonGlAccountToBeAdded);
		VarianceReasonGlAccount varianceReasonGlAccount = ((VarianceReasonGlAccountAdded) Scheduler.execute(command).data()).getAddedVarianceReasonGlAccount();
		
		if (varianceReasonGlAccount != null) 
			return successful(varianceReasonGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the VarianceReasonGlAccount with the specific Id
	 * 
	 * @param varianceReasonGlAccountToBeUpdated
	 *            the VarianceReasonGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateVarianceReasonGlAccount(@RequestBody VarianceReasonGlAccount varianceReasonGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		varianceReasonGlAccountToBeUpdated.setnull(null);

		UpdateVarianceReasonGlAccount command = new UpdateVarianceReasonGlAccount(varianceReasonGlAccountToBeUpdated);

		try {
			if(((VarianceReasonGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{varianceReasonGlAccountId}")
	public ResponseEntity<VarianceReasonGlAccount> findById(@PathVariable String varianceReasonGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("varianceReasonGlAccountId", varianceReasonGlAccountId);
		try {

			List<VarianceReasonGlAccount> foundVarianceReasonGlAccount = findVarianceReasonGlAccountsBy(requestParams).getBody();
			if(foundVarianceReasonGlAccount.size()==1){				return successful(foundVarianceReasonGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{varianceReasonGlAccountId}")
	public ResponseEntity<String> deleteVarianceReasonGlAccountByIdUpdated(@PathVariable String varianceReasonGlAccountId) throws Exception {
		DeleteVarianceReasonGlAccount command = new DeleteVarianceReasonGlAccount(varianceReasonGlAccountId);

		try {
			if (((VarianceReasonGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
