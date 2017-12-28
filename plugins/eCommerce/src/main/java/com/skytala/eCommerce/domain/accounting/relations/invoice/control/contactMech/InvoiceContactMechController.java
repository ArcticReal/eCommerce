package com.skytala.eCommerce.domain.accounting.relations.invoice.control.contactMech;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech.AddInvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech.DeleteInvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech.UpdateInvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contactMech.InvoiceContactMechMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contactMech.InvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.contactMech.FindInvoiceContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceContactMechs")
public class InvoiceContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceContactMech
	 * @return a List with the InvoiceContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceContactMech>> findInvoiceContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceContactMechsBy query = new FindInvoiceContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceContactMech> invoiceContactMechs =((InvoiceContactMechFound) Scheduler.execute(query).data()).getInvoiceContactMechs();

		return ResponseEntity.ok().body(invoiceContactMechs);

	}

	/**
	 * creates a new InvoiceContactMech entry in the ofbiz database
	 * 
	 * @param invoiceContactMechToBeAdded
	 *            the InvoiceContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceContactMech> createInvoiceContactMech(@RequestBody InvoiceContactMech invoiceContactMechToBeAdded) throws Exception {

		AddInvoiceContactMech command = new AddInvoiceContactMech(invoiceContactMechToBeAdded);
		InvoiceContactMech invoiceContactMech = ((InvoiceContactMechAdded) Scheduler.execute(command).data()).getAddedInvoiceContactMech();
		
		if (invoiceContactMech != null) 
			return successful(invoiceContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceContactMech with the specific Id
	 * 
	 * @param invoiceContactMechToBeUpdated
	 *            the InvoiceContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceContactMech(@RequestBody InvoiceContactMech invoiceContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceContactMechToBeUpdated.setnull(null);

		UpdateInvoiceContactMech command = new UpdateInvoiceContactMech(invoiceContactMechToBeUpdated);

		try {
			if(((InvoiceContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceContactMechId}")
	public ResponseEntity<InvoiceContactMech> findById(@PathVariable String invoiceContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceContactMechId", invoiceContactMechId);
		try {

			List<InvoiceContactMech> foundInvoiceContactMech = findInvoiceContactMechsBy(requestParams).getBody();
			if(foundInvoiceContactMech.size()==1){				return successful(foundInvoiceContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceContactMechId}")
	public ResponseEntity<String> deleteInvoiceContactMechByIdUpdated(@PathVariable String invoiceContactMechId) throws Exception {
		DeleteInvoiceContactMech command = new DeleteInvoiceContactMech(invoiceContactMechId);

		try {
			if (((InvoiceContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
