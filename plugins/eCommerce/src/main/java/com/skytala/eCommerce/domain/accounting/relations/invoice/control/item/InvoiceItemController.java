package com.skytala.eCommerce.domain.accounting.relations.invoice.control.item;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.item.AddInvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.item.DeleteInvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.item.UpdateInvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.item.InvoiceItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.item.InvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.item.FindInvoiceItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItems")
public class InvoiceItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItem
	 * @return a List with the InvoiceItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItem>> findInvoiceItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemsBy query = new FindInvoiceItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItem> invoiceItems =((InvoiceItemFound) Scheduler.execute(query).data()).getInvoiceItems();

		return ResponseEntity.ok().body(invoiceItems);

	}

	/**
	 * creates a new InvoiceItem entry in the ofbiz database
	 * 
	 * @param invoiceItemToBeAdded
	 *            the InvoiceItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItem> createInvoiceItem(@RequestBody InvoiceItem invoiceItemToBeAdded) throws Exception {

		AddInvoiceItem command = new AddInvoiceItem(invoiceItemToBeAdded);
		InvoiceItem invoiceItem = ((InvoiceItemAdded) Scheduler.execute(command).data()).getAddedInvoiceItem();
		
		if (invoiceItem != null) 
			return successful(invoiceItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItem with the specific Id
	 * 
	 * @param invoiceItemToBeUpdated
	 *            the InvoiceItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItem(@RequestBody InvoiceItem invoiceItemToBeUpdated,
			@PathVariable String invoiceItemSeqId) throws Exception {

		invoiceItemToBeUpdated.setInvoiceItemSeqId(invoiceItemSeqId);

		UpdateInvoiceItem command = new UpdateInvoiceItem(invoiceItemToBeUpdated);

		try {
			if(((InvoiceItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemId}")
	public ResponseEntity<InvoiceItem> findById(@PathVariable String invoiceItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemId", invoiceItemId);
		try {

			List<InvoiceItem> foundInvoiceItem = findInvoiceItemsBy(requestParams).getBody();
			if(foundInvoiceItem.size()==1){				return successful(foundInvoiceItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemId}")
	public ResponseEntity<String> deleteInvoiceItemByIdUpdated(@PathVariable String invoiceItemId) throws Exception {
		DeleteInvoiceItem command = new DeleteInvoiceItem(invoiceItemId);

		try {
			if (((InvoiceItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
