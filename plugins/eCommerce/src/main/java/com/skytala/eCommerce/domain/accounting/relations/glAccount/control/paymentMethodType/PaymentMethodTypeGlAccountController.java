package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.paymentMethodType;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType.AddPaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType.DeletePaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.paymentMethodType.UpdatePaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentMethodType.PaymentMethodTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType.PaymentMethodTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.paymentMethodType.FindPaymentMethodTypeGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/paymentMethodTypeGlAccounts")
public class PaymentMethodTypeGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PaymentMethodTypeGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PaymentMethodTypeGlAccount
	 * @return a List with the PaymentMethodTypeGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PaymentMethodTypeGlAccount>> findPaymentMethodTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPaymentMethodTypeGlAccountsBy query = new FindPaymentMethodTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PaymentMethodTypeGlAccount> paymentMethodTypeGlAccounts =((PaymentMethodTypeGlAccountFound) Scheduler.execute(query).data()).getPaymentMethodTypeGlAccounts();

		return ResponseEntity.ok().body(paymentMethodTypeGlAccounts);

	}

	/**
	 * creates a new PaymentMethodTypeGlAccount entry in the ofbiz database
	 * 
	 * @param paymentMethodTypeGlAccountToBeAdded
	 *            the PaymentMethodTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PaymentMethodTypeGlAccount> createPaymentMethodTypeGlAccount(@RequestBody PaymentMethodTypeGlAccount paymentMethodTypeGlAccountToBeAdded) throws Exception {

		AddPaymentMethodTypeGlAccount command = new AddPaymentMethodTypeGlAccount(paymentMethodTypeGlAccountToBeAdded);
		PaymentMethodTypeGlAccount paymentMethodTypeGlAccount = ((PaymentMethodTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedPaymentMethodTypeGlAccount();
		
		if (paymentMethodTypeGlAccount != null) 
			return successful(paymentMethodTypeGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PaymentMethodTypeGlAccount with the specific Id
	 * 
	 * @param paymentMethodTypeGlAccountToBeUpdated
	 *            the PaymentMethodTypeGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePaymentMethodTypeGlAccount(@RequestBody PaymentMethodTypeGlAccount paymentMethodTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		paymentMethodTypeGlAccountToBeUpdated.setnull(null);

		UpdatePaymentMethodTypeGlAccount command = new UpdatePaymentMethodTypeGlAccount(paymentMethodTypeGlAccountToBeUpdated);

		try {
			if(((PaymentMethodTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{paymentMethodTypeGlAccountId}")
	public ResponseEntity<PaymentMethodTypeGlAccount> findById(@PathVariable String paymentMethodTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("paymentMethodTypeGlAccountId", paymentMethodTypeGlAccountId);
		try {

			List<PaymentMethodTypeGlAccount> foundPaymentMethodTypeGlAccount = findPaymentMethodTypeGlAccountsBy(requestParams).getBody();
			if(foundPaymentMethodTypeGlAccount.size()==1){				return successful(foundPaymentMethodTypeGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{paymentMethodTypeGlAccountId}")
	public ResponseEntity<String> deletePaymentMethodTypeGlAccountByIdUpdated(@PathVariable String paymentMethodTypeGlAccountId) throws Exception {
		DeletePaymentMethodTypeGlAccount command = new DeletePaymentMethodTypeGlAccount(paymentMethodTypeGlAccountId);

		try {
			if (((PaymentMethodTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
