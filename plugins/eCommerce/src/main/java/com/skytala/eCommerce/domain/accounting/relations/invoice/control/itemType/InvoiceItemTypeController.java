package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemType;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemType.AddInvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemType.DeleteInvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemType.UpdateInvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType.InvoiceItemTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType.InvoiceItemTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType.InvoiceItemTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemType.InvoiceItemTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemType.InvoiceItemTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemType.InvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemType.FindInvoiceItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemTypes")
public class InvoiceItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemType
	 * @return a List with the InvoiceItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItemType>> findInvoiceItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypesBy query = new FindInvoiceItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemType> invoiceItemTypes =((InvoiceItemTypeFound) Scheduler.execute(query).data()).getInvoiceItemTypes();

		return ResponseEntity.ok().body(invoiceItemTypes);

	}

	/**
	 * creates a new InvoiceItemType entry in the ofbiz database
	 * 
	 * @param invoiceItemTypeToBeAdded
	 *            the InvoiceItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItemType> createInvoiceItemType(@RequestBody InvoiceItemType invoiceItemTypeToBeAdded) throws Exception {

		AddInvoiceItemType command = new AddInvoiceItemType(invoiceItemTypeToBeAdded);
		InvoiceItemType invoiceItemType = ((InvoiceItemTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceItemType();
		
		if (invoiceItemType != null) 
			return successful(invoiceItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItemType with the specific Id
	 * 
	 * @param invoiceItemTypeToBeUpdated
	 *            the InvoiceItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItemType(@RequestBody InvoiceItemType invoiceItemTypeToBeUpdated,
			@PathVariable String invoiceItemTypeId) throws Exception {

		invoiceItemTypeToBeUpdated.setInvoiceItemTypeId(invoiceItemTypeId);

		UpdateInvoiceItemType command = new UpdateInvoiceItemType(invoiceItemTypeToBeUpdated);

		try {
			if(((InvoiceItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemTypeId}")
	public ResponseEntity<InvoiceItemType> findById(@PathVariable String invoiceItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeId", invoiceItemTypeId);
		try {

			List<InvoiceItemType> foundInvoiceItemType = findInvoiceItemTypesBy(requestParams).getBody();
			if(foundInvoiceItemType.size()==1){				return successful(foundInvoiceItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemTypeId}")
	public ResponseEntity<String> deleteInvoiceItemTypeByIdUpdated(@PathVariable String invoiceItemTypeId) throws Exception {
		DeleteInvoiceItemType command = new DeleteInvoiceItemType(invoiceItemTypeId);

		try {
			if (((InvoiceItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
