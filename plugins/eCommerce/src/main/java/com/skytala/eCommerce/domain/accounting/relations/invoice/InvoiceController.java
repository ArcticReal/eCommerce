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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoices")
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
	@GetMapping("/find")
	public ResponseEntity<List<Invoice>> findInvoicesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoicesBy query = new FindInvoicesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Invoice> invoices =((InvoiceFound) Scheduler.execute(query).data()).getInvoices();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Invoice> createInvoice(HttpServletRequest request) throws Exception {

		Invoice invoiceToBeAdded = new Invoice();
		try {
			invoiceToBeAdded = InvoiceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoiceToBeAdded) throws Exception {

		AddInvoice command = new AddInvoice(invoiceToBeAdded);
		Invoice invoice = ((InvoiceAdded) Scheduler.execute(command).data()).getAddedInvoice();
		
		if (invoice != null) 
			return successful(invoice);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateInvoice(@RequestBody Invoice invoiceToBeUpdated,
			@PathVariable String invoiceId) throws Exception {

		invoiceToBeUpdated.setInvoiceId(invoiceId);

		UpdateInvoice command = new UpdateInvoice(invoiceToBeUpdated);

		try {
			if(((InvoiceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceId}")
	public ResponseEntity<Invoice> findById(@PathVariable String invoiceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceId", invoiceId);
		try {

			List<Invoice> foundInvoice = findInvoicesBy(requestParams).getBody();
			if(foundInvoice.size()==1){				return successful(foundInvoice.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceId}")
	public ResponseEntity<String> deleteInvoiceByIdUpdated(@PathVariable String invoiceId) throws Exception {
		DeleteInvoice command = new DeleteInvoice(invoiceId);

		try {
			if (((InvoiceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
