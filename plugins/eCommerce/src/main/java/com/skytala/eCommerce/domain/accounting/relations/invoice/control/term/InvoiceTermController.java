package com.skytala.eCommerce.domain.accounting.relations.invoice.control.term;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.term.AddInvoiceTerm;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.term.DeleteInvoiceTerm;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.term.UpdateInvoiceTerm;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.term.InvoiceTermAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.term.InvoiceTermDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.term.InvoiceTermFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.term.InvoiceTermUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.term.InvoiceTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.term.InvoiceTerm;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.term.FindInvoiceTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceTerms")
public class InvoiceTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceTerm
	 * @return a List with the InvoiceTerms
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceTerm>> findInvoiceTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTermsBy query = new FindInvoiceTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceTerm> invoiceTerms =((InvoiceTermFound) Scheduler.execute(query).data()).getInvoiceTerms();

		return ResponseEntity.ok().body(invoiceTerms);

	}

	/**
	 * creates a new InvoiceTerm entry in the ofbiz database
	 * 
	 * @param invoiceTermToBeAdded
	 *            the InvoiceTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceTerm> createInvoiceTerm(@RequestBody InvoiceTerm invoiceTermToBeAdded) throws Exception {

		AddInvoiceTerm command = new AddInvoiceTerm(invoiceTermToBeAdded);
		InvoiceTerm invoiceTerm = ((InvoiceTermAdded) Scheduler.execute(command).data()).getAddedInvoiceTerm();
		
		if (invoiceTerm != null) 
			return successful(invoiceTerm);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceTerm with the specific Id
	 * 
	 * @param invoiceTermToBeUpdated
	 *            the InvoiceTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceTermId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceTerm(@RequestBody InvoiceTerm invoiceTermToBeUpdated,
			@PathVariable String invoiceTermId) throws Exception {

		invoiceTermToBeUpdated.setInvoiceTermId(invoiceTermId);

		UpdateInvoiceTerm command = new UpdateInvoiceTerm(invoiceTermToBeUpdated);

		try {
			if(((InvoiceTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceTermId}")
	public ResponseEntity<InvoiceTerm> findById(@PathVariable String invoiceTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTermId", invoiceTermId);
		try {

			List<InvoiceTerm> foundInvoiceTerm = findInvoiceTermsBy(requestParams).getBody();
			if(foundInvoiceTerm.size()==1){				return successful(foundInvoiceTerm.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceTermId}")
	public ResponseEntity<String> deleteInvoiceTermByIdUpdated(@PathVariable String invoiceTermId) throws Exception {
		DeleteInvoiceTerm command = new DeleteInvoiceTerm(invoiceTermId);

		try {
			if (((InvoiceTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
