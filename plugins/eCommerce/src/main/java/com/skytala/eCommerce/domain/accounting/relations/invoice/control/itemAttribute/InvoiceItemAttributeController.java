package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemAttribute;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute.AddInvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute.DeleteInvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute.UpdateInvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAttribute.InvoiceItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemAttribute.FindInvoiceItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemAttributes")
public class InvoiceItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemAttribute
	 * @return a List with the InvoiceItemAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItemAttribute>> findInvoiceItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemAttributesBy query = new FindInvoiceItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemAttribute> invoiceItemAttributes =((InvoiceItemAttributeFound) Scheduler.execute(query).data()).getInvoiceItemAttributes();

		return ResponseEntity.ok().body(invoiceItemAttributes);

	}

	/**
	 * creates a new InvoiceItemAttribute entry in the ofbiz database
	 * 
	 * @param invoiceItemAttributeToBeAdded
	 *            the InvoiceItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItemAttribute> createInvoiceItemAttribute(@RequestBody InvoiceItemAttribute invoiceItemAttributeToBeAdded) throws Exception {

		AddInvoiceItemAttribute command = new AddInvoiceItemAttribute(invoiceItemAttributeToBeAdded);
		InvoiceItemAttribute invoiceItemAttribute = ((InvoiceItemAttributeAdded) Scheduler.execute(command).data()).getAddedInvoiceItemAttribute();
		
		if (invoiceItemAttribute != null) 
			return successful(invoiceItemAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItemAttribute with the specific Id
	 * 
	 * @param invoiceItemAttributeToBeUpdated
	 *            the InvoiceItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItemAttribute(@RequestBody InvoiceItemAttribute invoiceItemAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceItemAttributeToBeUpdated.setnull(null);

		UpdateInvoiceItemAttribute command = new UpdateInvoiceItemAttribute(invoiceItemAttributeToBeUpdated);

		try {
			if(((InvoiceItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemAttributeId}")
	public ResponseEntity<InvoiceItemAttribute> findById(@PathVariable String invoiceItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemAttributeId", invoiceItemAttributeId);
		try {

			List<InvoiceItemAttribute> foundInvoiceItemAttribute = findInvoiceItemAttributesBy(requestParams).getBody();
			if(foundInvoiceItemAttribute.size()==1){				return successful(foundInvoiceItemAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemAttributeId}")
	public ResponseEntity<String> deleteInvoiceItemAttributeByIdUpdated(@PathVariable String invoiceItemAttributeId) throws Exception {
		DeleteInvoiceItemAttribute command = new DeleteInvoiceItemAttribute(invoiceItemAttributeId);

		try {
			if (((InvoiceItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
