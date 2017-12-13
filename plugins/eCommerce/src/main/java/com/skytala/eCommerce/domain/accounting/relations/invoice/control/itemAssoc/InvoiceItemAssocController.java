package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemAssoc;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssoc.AddInvoiceItemAssoc;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssoc.DeleteInvoiceItemAssoc;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssoc.UpdateInvoiceItemAssoc;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc.InvoiceItemAssocAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc.InvoiceItemAssocDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc.InvoiceItemAssocFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc.InvoiceItemAssocUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssoc.InvoiceItemAssocMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssoc.InvoiceItemAssoc;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemAssoc.FindInvoiceItemAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemAssocs")
public class InvoiceItemAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemAssoc
	 * @return a List with the InvoiceItemAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findInvoiceItemAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemAssocsBy query = new FindInvoiceItemAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemAssoc> invoiceItemAssocs =((InvoiceItemAssocFound) Scheduler.execute(query).data()).getInvoiceItemAssocs();

		if (invoiceItemAssocs.size() == 1) {
			return ResponseEntity.ok().body(invoiceItemAssocs.get(0));
		}

		return ResponseEntity.ok().body(invoiceItemAssocs);

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
	public ResponseEntity<Object> createInvoiceItemAssoc(HttpServletRequest request) throws Exception {

		InvoiceItemAssoc invoiceItemAssocToBeAdded = new InvoiceItemAssoc();
		try {
			invoiceItemAssocToBeAdded = InvoiceItemAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceItemAssoc(invoiceItemAssocToBeAdded);

	}

	/**
	 * creates a new InvoiceItemAssoc entry in the ofbiz database
	 * 
	 * @param invoiceItemAssocToBeAdded
	 *            the InvoiceItemAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceItemAssoc(@RequestBody InvoiceItemAssoc invoiceItemAssocToBeAdded) throws Exception {

		AddInvoiceItemAssoc command = new AddInvoiceItemAssoc(invoiceItemAssocToBeAdded);
		InvoiceItemAssoc invoiceItemAssoc = ((InvoiceItemAssocAdded) Scheduler.execute(command).data()).getAddedInvoiceItemAssoc();
		
		if (invoiceItemAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItemAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItemAssoc could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateInvoiceItemAssoc(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		InvoiceItemAssoc invoiceItemAssocToBeUpdated = new InvoiceItemAssoc();

		try {
			invoiceItemAssocToBeUpdated = InvoiceItemAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItemAssoc(invoiceItemAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceItemAssoc with the specific Id
	 * 
	 * @param invoiceItemAssocToBeUpdated
	 *            the InvoiceItemAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceItemAssoc(@RequestBody InvoiceItemAssoc invoiceItemAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceItemAssocToBeUpdated.setnull(null);

		UpdateInvoiceItemAssoc command = new UpdateInvoiceItemAssoc(invoiceItemAssocToBeUpdated);

		try {
			if(((InvoiceItemAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{invoiceItemAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemAssocId", invoiceItemAssocId);
		try {

			Object foundInvoiceItemAssoc = findInvoiceItemAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItemAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{invoiceItemAssocId}")
	public ResponseEntity<Object> deleteInvoiceItemAssocByIdUpdated(@PathVariable String invoiceItemAssocId) throws Exception {
		DeleteInvoiceItemAssoc command = new DeleteInvoiceItemAssoc(invoiceItemAssocId);

		try {
			if (((InvoiceItemAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItemAssoc could not be deleted");

	}

}
