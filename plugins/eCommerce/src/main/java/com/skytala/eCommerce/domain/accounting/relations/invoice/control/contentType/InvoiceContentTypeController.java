package com.skytala.eCommerce.domain.accounting.relations.invoice.control.contentType;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contentType.AddInvoiceContentType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contentType.DeleteInvoiceContentType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contentType.UpdateInvoiceContentType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contentType.InvoiceContentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.contentType.FindInvoiceContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceContentTypes")
public class InvoiceContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceContentType
	 * @return a List with the InvoiceContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceContentType>> findInvoiceContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceContentTypesBy query = new FindInvoiceContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceContentType> invoiceContentTypes =((InvoiceContentTypeFound) Scheduler.execute(query).data()).getInvoiceContentTypes();

		return ResponseEntity.ok().body(invoiceContentTypes);

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
	public ResponseEntity<InvoiceContentType> createInvoiceContentType(HttpServletRequest request) throws Exception {

		InvoiceContentType invoiceContentTypeToBeAdded = new InvoiceContentType();
		try {
			invoiceContentTypeToBeAdded = InvoiceContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceContentType(invoiceContentTypeToBeAdded);

	}

	/**
	 * creates a new InvoiceContentType entry in the ofbiz database
	 * 
	 * @param invoiceContentTypeToBeAdded
	 *            the InvoiceContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceContentType> createInvoiceContentType(@RequestBody InvoiceContentType invoiceContentTypeToBeAdded) throws Exception {

		AddInvoiceContentType command = new AddInvoiceContentType(invoiceContentTypeToBeAdded);
		InvoiceContentType invoiceContentType = ((InvoiceContentTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceContentType();
		
		if (invoiceContentType != null) 
			return successful(invoiceContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceContentType with the specific Id
	 * 
	 * @param invoiceContentTypeToBeUpdated
	 *            the InvoiceContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceContentType(@RequestBody InvoiceContentType invoiceContentTypeToBeUpdated,
			@PathVariable String invoiceContentTypeId) throws Exception {

		invoiceContentTypeToBeUpdated.setInvoiceContentTypeId(invoiceContentTypeId);

		UpdateInvoiceContentType command = new UpdateInvoiceContentType(invoiceContentTypeToBeUpdated);

		try {
			if(((InvoiceContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceContentTypeId}")
	public ResponseEntity<InvoiceContentType> findById(@PathVariable String invoiceContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceContentTypeId", invoiceContentTypeId);
		try {

			List<InvoiceContentType> foundInvoiceContentType = findInvoiceContentTypesBy(requestParams).getBody();
			if(foundInvoiceContentType.size()==1){				return successful(foundInvoiceContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceContentTypeId}")
	public ResponseEntity<String> deleteInvoiceContentTypeByIdUpdated(@PathVariable String invoiceContentTypeId) throws Exception {
		DeleteInvoiceContentType command = new DeleteInvoiceContentType(invoiceContentTypeId);

		try {
			if (((InvoiceContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
