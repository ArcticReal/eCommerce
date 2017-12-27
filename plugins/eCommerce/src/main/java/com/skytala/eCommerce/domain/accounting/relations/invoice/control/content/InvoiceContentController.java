package com.skytala.eCommerce.domain.accounting.relations.invoice.control.content;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.content.AddInvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.content.DeleteInvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.content.UpdateInvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.content.InvoiceContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.content.FindInvoiceContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceContents")
public class InvoiceContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceContent
	 * @return a List with the InvoiceContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceContent>> findInvoiceContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceContentsBy query = new FindInvoiceContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceContent> invoiceContents =((InvoiceContentFound) Scheduler.execute(query).data()).getInvoiceContents();

		return ResponseEntity.ok().body(invoiceContents);

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
	public ResponseEntity<InvoiceContent> createInvoiceContent(HttpServletRequest request) throws Exception {

		InvoiceContent invoiceContentToBeAdded = new InvoiceContent();
		try {
			invoiceContentToBeAdded = InvoiceContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceContent(invoiceContentToBeAdded);

	}

	/**
	 * creates a new InvoiceContent entry in the ofbiz database
	 * 
	 * @param invoiceContentToBeAdded
	 *            the InvoiceContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceContent> createInvoiceContent(@RequestBody InvoiceContent invoiceContentToBeAdded) throws Exception {

		AddInvoiceContent command = new AddInvoiceContent(invoiceContentToBeAdded);
		InvoiceContent invoiceContent = ((InvoiceContentAdded) Scheduler.execute(command).data()).getAddedInvoiceContent();
		
		if (invoiceContent != null) 
			return successful(invoiceContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceContent with the specific Id
	 * 
	 * @param invoiceContentToBeUpdated
	 *            the InvoiceContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceContent(@RequestBody InvoiceContent invoiceContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceContentToBeUpdated.setnull(null);

		UpdateInvoiceContent command = new UpdateInvoiceContent(invoiceContentToBeUpdated);

		try {
			if(((InvoiceContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceContentId}")
	public ResponseEntity<InvoiceContent> findById(@PathVariable String invoiceContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceContentId", invoiceContentId);
		try {

			List<InvoiceContent> foundInvoiceContent = findInvoiceContentsBy(requestParams).getBody();
			if(foundInvoiceContent.size()==1){				return successful(foundInvoiceContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceContentId}")
	public ResponseEntity<String> deleteInvoiceContentByIdUpdated(@PathVariable String invoiceContentId) throws Exception {
		DeleteInvoiceContent command = new DeleteInvoiceContent(invoiceContentId);

		try {
			if (((InvoiceContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
