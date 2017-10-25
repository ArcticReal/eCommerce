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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/invoiceItemTypeGlAccounts")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceItemTypeGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypeGlAccountsBy query = new FindInvoiceItemTypeGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemTypeGlAccount> invoiceItemTypeGlAccounts =((InvoiceItemTypeGlAccountFound) Scheduler.execute(query).data()).getInvoiceItemTypeGlAccounts();

		if (invoiceItemTypeGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(invoiceItemTypeGlAccounts.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createInvoiceItemTypeGlAccount(HttpServletRequest request) throws Exception {

		InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeAdded = new InvoiceItemTypeGlAccount();
		try {
			invoiceItemTypeGlAccountToBeAdded = InvoiceItemTypeGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createInvoiceItemTypeGlAccount(@RequestBody InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeAdded) throws Exception {

		AddInvoiceItemTypeGlAccount command = new AddInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountToBeAdded);
		InvoiceItemTypeGlAccount invoiceItemTypeGlAccount = ((InvoiceItemTypeGlAccountAdded) Scheduler.execute(command).data()).getAddedInvoiceItemTypeGlAccount();
		
		if (invoiceItemTypeGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItemTypeGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItemTypeGlAccount could not be created.");
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
	public boolean updateInvoiceItemTypeGlAccount(HttpServletRequest request) throws Exception {

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

		InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeUpdated = new InvoiceItemTypeGlAccount();

		try {
			invoiceItemTypeGlAccountToBeUpdated = InvoiceItemTypeGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInvoiceItemTypeGlAccount(@RequestBody InvoiceItemTypeGlAccount invoiceItemTypeGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceItemTypeGlAccountToBeUpdated.setnull(null);

		UpdateInvoiceItemTypeGlAccount command = new UpdateInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountToBeUpdated);

		try {
			if(((InvoiceItemTypeGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceItemTypeGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemTypeGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeGlAccountId", invoiceItemTypeGlAccountId);
		try {

			Object foundInvoiceItemTypeGlAccount = findInvoiceItemTypeGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItemTypeGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceItemTypeGlAccountId}")
	public ResponseEntity<Object> deleteInvoiceItemTypeGlAccountByIdUpdated(@PathVariable String invoiceItemTypeGlAccountId) throws Exception {
		DeleteInvoiceItemTypeGlAccount command = new DeleteInvoiceItemTypeGlAccount(invoiceItemTypeGlAccountId);

		try {
			if (((InvoiceItemTypeGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItemTypeGlAccount could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceItemTypeGlAccount/\" plus one of the following: "
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
