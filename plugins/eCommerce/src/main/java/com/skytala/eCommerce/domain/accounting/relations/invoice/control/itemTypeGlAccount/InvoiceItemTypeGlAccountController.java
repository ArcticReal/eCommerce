package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemTypeGlAccount;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeGlAccount.AddInvoiceItemTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeGlAccount.DeleteInvoiceItemTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeGlAccount.UpdateInvoiceItemTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount.InvoiceItemTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount.InvoiceItemTypeGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount.InvoiceItemTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount.InvoiceItemTypeGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeGlAccount.InvoiceItemTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount.InvoiceItemTypeGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemTypeGlAccount.FindInvoiceItemTypeGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemTypeGlAccounts")
public class InvoiceItemTypeGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemTypeGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemTypeGlAccount
	 * @return a List with the InvoiceItemTypeGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItemTypeGlAccount>> findInvoiceItemTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypeGlAccountsBy query = new FindInvoiceItemTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemTypeGlAccount> invoiceItemTypeGlAccounts =((InvoiceItemTypeGlAccountFound) Scheduler.execute(query).data()).getInvoiceItemTypeGlAccounts();

		return ResponseEntity.ok().body(invoiceItemTypeGlAccounts);

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
	public ResponseEntity<InvoiceItemTypeGlAccount> createInvoiceItemTypeGlAccount(HttpServletRequest request) throws Exception {

		InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeAdded = new InvoiceItemTypeGlAccount();
		try {
			invoiceItemTypeGlAccountToBeAdded = InvoiceItemTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountToBeAdded);

	}

	/**
	 * creates a new InvoiceItemTypeGlAccount entry in the ofbiz database
	 * 
	 * @param invoiceItemTypeGlAccountToBeAdded
	 *            the InvoiceItemTypeGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItemTypeGlAccount> createInvoiceItemTypeGlAccount(@RequestBody InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeAdded) throws Exception {

		AddInvoiceItemTypeGlAccount command = new AddInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountToBeAdded);
		InvoiceItemTypeGlAccount invoiceItemTypeGlAccount = ((InvoiceItemTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedInvoiceItemTypeGlAccount();
		
		if (invoiceItemTypeGlAccount != null) 
			return successful(invoiceItemTypeGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItemTypeGlAccount with the specific Id
	 * 
	 * @param invoiceItemTypeGlAccountToBeUpdated
	 *            the InvoiceItemTypeGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItemTypeGlAccount(@RequestBody InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceItemTypeGlAccountToBeUpdated.setnull(null);

		UpdateInvoiceItemTypeGlAccount command = new UpdateInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountToBeUpdated);

		try {
			if(((InvoiceItemTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemTypeGlAccountId}")
	public ResponseEntity<InvoiceItemTypeGlAccount> findById(@PathVariable String invoiceItemTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeGlAccountId", invoiceItemTypeGlAccountId);
		try {

			List<InvoiceItemTypeGlAccount> foundInvoiceItemTypeGlAccount = findInvoiceItemTypeGlAccountsBy(requestParams).getBody();
			if(foundInvoiceItemTypeGlAccount.size()==1){				return successful(foundInvoiceItemTypeGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemTypeGlAccountId}")
	public ResponseEntity<String> deleteInvoiceItemTypeGlAccountByIdUpdated(@PathVariable String invoiceItemTypeGlAccountId) throws Exception {
		DeleteInvoiceItemTypeGlAccount command = new DeleteInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountId);

		try {
			if (((InvoiceItemTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
