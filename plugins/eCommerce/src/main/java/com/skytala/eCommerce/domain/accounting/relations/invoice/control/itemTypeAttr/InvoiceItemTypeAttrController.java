package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemTypeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeAttr.AddInvoiceItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeAttr.DeleteInvoiceItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeAttr.UpdateInvoiceItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr.InvoiceItemTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr.InvoiceItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr.InvoiceItemTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr.InvoiceItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeAttr.InvoiceItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeAttr.InvoiceItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemTypeAttr.FindInvoiceItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemTypeAttrs")
public class InvoiceItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemTypeAttr
	 * @return a List with the InvoiceItemTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItemTypeAttr>> findInvoiceItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypeAttrsBy query = new FindInvoiceItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemTypeAttr> invoiceItemTypeAttrs =((InvoiceItemTypeAttrFound) Scheduler.execute(query).data()).getInvoiceItemTypeAttrs();

		return ResponseEntity.ok().body(invoiceItemTypeAttrs);

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
	public ResponseEntity<InvoiceItemTypeAttr> createInvoiceItemTypeAttr(HttpServletRequest request) throws Exception {

		InvoiceItemTypeAttr invoiceItemTypeAttrToBeAdded = new InvoiceItemTypeAttr();
		try {
			invoiceItemTypeAttrToBeAdded = InvoiceItemTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceItemTypeAttr(invoiceItemTypeAttrToBeAdded);

	}

	/**
	 * creates a new InvoiceItemTypeAttr entry in the ofbiz database
	 * 
	 * @param invoiceItemTypeAttrToBeAdded
	 *            the InvoiceItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItemTypeAttr> createInvoiceItemTypeAttr(@RequestBody InvoiceItemTypeAttr invoiceItemTypeAttrToBeAdded) throws Exception {

		AddInvoiceItemTypeAttr command = new AddInvoiceItemTypeAttr(invoiceItemTypeAttrToBeAdded);
		InvoiceItemTypeAttr invoiceItemTypeAttr = ((InvoiceItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedInvoiceItemTypeAttr();
		
		if (invoiceItemTypeAttr != null) 
			return successful(invoiceItemTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItemTypeAttr with the specific Id
	 * 
	 * @param invoiceItemTypeAttrToBeUpdated
	 *            the InvoiceItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItemTypeAttr(@RequestBody InvoiceItemTypeAttr invoiceItemTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		invoiceItemTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateInvoiceItemTypeAttr command = new UpdateInvoiceItemTypeAttr(invoiceItemTypeAttrToBeUpdated);

		try {
			if(((InvoiceItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemTypeAttrId}")
	public ResponseEntity<InvoiceItemTypeAttr> findById(@PathVariable String invoiceItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeAttrId", invoiceItemTypeAttrId);
		try {

			List<InvoiceItemTypeAttr> foundInvoiceItemTypeAttr = findInvoiceItemTypeAttrsBy(requestParams).getBody();
			if(foundInvoiceItemTypeAttr.size()==1){				return successful(foundInvoiceItemTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemTypeAttrId}")
	public ResponseEntity<String> deleteInvoiceItemTypeAttrByIdUpdated(@PathVariable String invoiceItemTypeAttrId) throws Exception {
		DeleteInvoiceItemTypeAttr command = new DeleteInvoiceItemTypeAttr(invoiceItemTypeAttrId);

		try {
			if (((InvoiceItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
