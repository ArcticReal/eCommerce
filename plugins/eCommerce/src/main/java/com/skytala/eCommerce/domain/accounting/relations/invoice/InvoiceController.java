package com.skytala.eCommerce.domain.accounting.relations.invoice;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.AddInvoice;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.DeleteInvoice;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.UpdateInvoice;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.InvoiceAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.InvoiceDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.InvoiceFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.InvoiceUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.InvoiceMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.Invoice;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.FindInvoicesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/invoices")
public class InvoiceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Invoice
	 * @return a List with the Invoices
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoicesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoicesBy query = new FindInvoicesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Invoice> invoices =((InvoiceFound) Scheduler.execute(query).data()).getInvoices();

		if (invoices.size() == 1) {
			return ResponseEntity.ok().body(invoices.get(0));
		}

		return ResponseEntity.ok().body(invoices);

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
	public ResponseEntity<Object> createInvoice(HttpServletRequest request) throws Exception {

		Invoice invoiceToBeAdded = new Invoice();
		try {
			invoiceToBeAdded = InvoiceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoice(invoiceToBeAdded);

	}

	/**
	 * creates a new Invoice entry in the ofbiz database
	 * 
	 * @param invoiceToBeAdded
	 *            the Invoice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoiceToBeAdded) throws Exception {

		AddInvoice command = new AddInvoice(invoiceToBeAdded);
		Invoice invoice = ((InvoiceAdded) Scheduler.execute(command).data()).getAddedInvoice();
		
		if (invoice != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoice);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Invoice could not be created.");
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
	public boolean updateInvoice(HttpServletRequest request) throws Exception {

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

		Invoice invoiceToBeUpdated = new Invoice();

		try {
			invoiceToBeUpdated = InvoiceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoice(invoiceToBeUpdated, invoiceToBeUpdated.getInvoiceId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Invoice with the specific Id
	 * 
	 * @param invoiceToBeUpdated
	 *            the Invoice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoice(@RequestBody Invoice invoiceToBeUpdated,
			@PathVariable String invoiceId) throws Exception {

		invoiceToBeUpdated.setInvoiceId(invoiceId);

		UpdateInvoice command = new UpdateInvoice(invoiceToBeUpdated);

		try {
			if(((InvoiceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceId", invoiceId);
		try {

			Object foundInvoice = findInvoicesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoice);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceId}")
	public ResponseEntity<Object> deleteInvoiceByIdUpdated(@PathVariable String invoiceId) throws Exception {
		DeleteInvoice command = new DeleteInvoice(invoiceId);

		try {
			if (((InvoiceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Invoice could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoice/\" plus one of the following: "
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
