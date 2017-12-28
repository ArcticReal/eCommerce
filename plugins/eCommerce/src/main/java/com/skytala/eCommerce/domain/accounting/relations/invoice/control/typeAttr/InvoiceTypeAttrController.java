package com.skytala.eCommerce.domain.accounting.relations.invoice.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr.AddInvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr.DeleteInvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr.UpdateInvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.typeAttr.InvoiceTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.typeAttr.InvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.typeAttr.FindInvoiceTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceTypeAttrs")
public class InvoiceTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceTypeAttr
	 * @return a List with the InvoiceTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceTypeAttr>> findInvoiceTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTypeAttrsBy query = new FindInvoiceTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceTypeAttr> invoiceTypeAttrs =((InvoiceTypeAttrFound) Scheduler.execute(query).data()).getInvoiceTypeAttrs();

		return ResponseEntity.ok().body(invoiceTypeAttrs);

	}

	/**
	 * creates a new InvoiceTypeAttr entry in the ofbiz database
	 * 
	 * @param invoiceTypeAttrToBeAdded
	 *            the InvoiceTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceTypeAttr> createInvoiceTypeAttr(@RequestBody InvoiceTypeAttr invoiceTypeAttrToBeAdded) throws Exception {

		AddInvoiceTypeAttr command = new AddInvoiceTypeAttr(invoiceTypeAttrToBeAdded);
		InvoiceTypeAttr invoiceTypeAttr = ((InvoiceTypeAttrAdded) Scheduler.execute(command).data()).getAddedInvoiceTypeAttr();
		
		if (invoiceTypeAttr != null) 
			return successful(invoiceTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceTypeAttr with the specific Id
	 * 
	 * @param invoiceTypeAttrToBeUpdated
	 *            the InvoiceTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceTypeAttr(@RequestBody InvoiceTypeAttr invoiceTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		invoiceTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateInvoiceTypeAttr command = new UpdateInvoiceTypeAttr(invoiceTypeAttrToBeUpdated);

		try {
			if(((InvoiceTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceTypeAttrId}")
	public ResponseEntity<InvoiceTypeAttr> findById(@PathVariable String invoiceTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTypeAttrId", invoiceTypeAttrId);
		try {

			List<InvoiceTypeAttr> foundInvoiceTypeAttr = findInvoiceTypeAttrsBy(requestParams).getBody();
			if(foundInvoiceTypeAttr.size()==1){				return successful(foundInvoiceTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceTypeAttrId}")
	public ResponseEntity<String> deleteInvoiceTypeAttrByIdUpdated(@PathVariable String invoiceTypeAttrId) throws Exception {
		DeleteInvoiceTypeAttr command = new DeleteInvoiceTypeAttr(invoiceTypeAttrId);

		try {
			if (((InvoiceTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
