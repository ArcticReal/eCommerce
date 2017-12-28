package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemAssocType;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssocType.AddInvoiceItemAssocType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssocType.DeleteInvoiceItemAssocType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssocType.UpdateInvoiceItemAssocType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType.InvoiceItemAssocTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType.InvoiceItemAssocTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType.InvoiceItemAssocTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType.InvoiceItemAssocTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssocType.InvoiceItemAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssocType.InvoiceItemAssocType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemAssocType.FindInvoiceItemAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemAssocTypes")
public class InvoiceItemAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemAssocType
	 * @return a List with the InvoiceItemAssocTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItemAssocType>> findInvoiceItemAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemAssocTypesBy query = new FindInvoiceItemAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemAssocType> invoiceItemAssocTypes =((InvoiceItemAssocTypeFound) Scheduler.execute(query).data()).getInvoiceItemAssocTypes();

		return ResponseEntity.ok().body(invoiceItemAssocTypes);

	}

	/**
	 * creates a new InvoiceItemAssocType entry in the ofbiz database
	 * 
	 * @param invoiceItemAssocTypeToBeAdded
	 *            the InvoiceItemAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItemAssocType> createInvoiceItemAssocType(@RequestBody InvoiceItemAssocType invoiceItemAssocTypeToBeAdded) throws Exception {

		AddInvoiceItemAssocType command = new AddInvoiceItemAssocType(invoiceItemAssocTypeToBeAdded);
		InvoiceItemAssocType invoiceItemAssocType = ((InvoiceItemAssocTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceItemAssocType();
		
		if (invoiceItemAssocType != null) 
			return successful(invoiceItemAssocType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItemAssocType with the specific Id
	 * 
	 * @param invoiceItemAssocTypeToBeUpdated
	 *            the InvoiceItemAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItemAssocType(@RequestBody InvoiceItemAssocType invoiceItemAssocTypeToBeUpdated,
			@PathVariable String invoiceItemAssocTypeId) throws Exception {

		invoiceItemAssocTypeToBeUpdated.setInvoiceItemAssocTypeId(invoiceItemAssocTypeId);

		UpdateInvoiceItemAssocType command = new UpdateInvoiceItemAssocType(invoiceItemAssocTypeToBeUpdated);

		try {
			if(((InvoiceItemAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemAssocTypeId}")
	public ResponseEntity<InvoiceItemAssocType> findById(@PathVariable String invoiceItemAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemAssocTypeId", invoiceItemAssocTypeId);
		try {

			List<InvoiceItemAssocType> foundInvoiceItemAssocType = findInvoiceItemAssocTypesBy(requestParams).getBody();
			if(foundInvoiceItemAssocType.size()==1){				return successful(foundInvoiceItemAssocType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemAssocTypeId}")
	public ResponseEntity<String> deleteInvoiceItemAssocTypeByIdUpdated(@PathVariable String invoiceItemAssocTypeId) throws Exception {
		DeleteInvoiceItemAssocType command = new DeleteInvoiceItemAssocType(invoiceItemAssocTypeId);

		try {
			if (((InvoiceItemAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
