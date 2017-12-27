package com.skytala.eCommerce.domain.accounting.relations.invoice.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.type.AddInvoiceType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.type.DeleteInvoiceType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.type.UpdateInvoiceType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.type.InvoiceTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.type.InvoiceTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.type.InvoiceTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.type.InvoiceTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.type.InvoiceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.type.InvoiceType;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.type.FindInvoiceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceTypes")
public class InvoiceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceType
	 * @return a List with the InvoiceTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceType>> findInvoiceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTypesBy query = new FindInvoiceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceType> invoiceTypes =((InvoiceTypeFound) Scheduler.execute(query).data()).getInvoiceTypes();

		return ResponseEntity.ok().body(invoiceTypes);

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
	public ResponseEntity<InvoiceType> createInvoiceType(HttpServletRequest request) throws Exception {

		InvoiceType invoiceTypeToBeAdded = new InvoiceType();
		try {
			invoiceTypeToBeAdded = InvoiceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceType(invoiceTypeToBeAdded);

	}

	/**
	 * creates a new InvoiceType entry in the ofbiz database
	 * 
	 * @param invoiceTypeToBeAdded
	 *            the InvoiceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceType> createInvoiceType(@RequestBody InvoiceType invoiceTypeToBeAdded) throws Exception {

		AddInvoiceType command = new AddInvoiceType(invoiceTypeToBeAdded);
		InvoiceType invoiceType = ((InvoiceTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceType();
		
		if (invoiceType != null) 
			return successful(invoiceType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceType with the specific Id
	 * 
	 * @param invoiceTypeToBeUpdated
	 *            the InvoiceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceType(@RequestBody InvoiceType invoiceTypeToBeUpdated,
			@PathVariable String invoiceTypeId) throws Exception {

		invoiceTypeToBeUpdated.setInvoiceTypeId(invoiceTypeId);

		UpdateInvoiceType command = new UpdateInvoiceType(invoiceTypeToBeUpdated);

		try {
			if(((InvoiceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceTypeId}")
	public ResponseEntity<InvoiceType> findById(@PathVariable String invoiceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTypeId", invoiceTypeId);
		try {

			List<InvoiceType> foundInvoiceType = findInvoiceTypesBy(requestParams).getBody();
			if(foundInvoiceType.size()==1){				return successful(foundInvoiceType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceTypeId}")
	public ResponseEntity<String> deleteInvoiceTypeByIdUpdated(@PathVariable String invoiceTypeId) throws Exception {
		DeleteInvoiceType command = new DeleteInvoiceType(invoiceTypeId);

		try {
			if (((InvoiceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
