package com.skytala.eCommerce.domain.accounting.relations.invoice.control.contactMech;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech.AddInvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech.DeleteInvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech.UpdateInvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contactMech.InvoiceContactMechMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contactMech.InvoiceContactMech;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.contactMech.FindInvoiceContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceContactMechs")
public class InvoiceContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceContactMech
	 * @return a List with the InvoiceContactMechs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceContactMechsBy query = new FindInvoiceContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceContactMech> invoiceContactMechs =((InvoiceContactMechFound) Scheduler.execute(query).data()).getInvoiceContactMechs();

		if (invoiceContactMechs.size() == 1) {
			return ResponseEntity.ok().body(invoiceContactMechs.get(0));
		}

		return ResponseEntity.ok().body(invoiceContactMechs);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createInvoiceContactMech(HttpServletRequest request) throws Exception {

		InvoiceContactMech invoiceContactMechToBeAdded = new InvoiceContactMech();
		try {
			invoiceContactMechToBeAdded = InvoiceContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceContactMech(invoiceContactMechToBeAdded);

	}

	/**
	 * creates a new InvoiceContactMech entry in the ofbiz database
	 * 
	 * @param invoiceContactMechToBeAdded
	 *            the InvoiceContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceContactMech(@RequestBody InvoiceContactMech invoiceContactMechToBeAdded) throws Exception {

		AddInvoiceContactMech command = new AddInvoiceContactMech(invoiceContactMechToBeAdded);
		InvoiceContactMech invoiceContactMech = ((InvoiceContactMechAdded) Scheduler.execute(command).data()).getAddedInvoiceContactMech();
		
		if (invoiceContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceContactMech could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateInvoiceContactMech(HttpServletRequest request) throws Exception {

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

		InvoiceContactMech invoiceContactMechToBeUpdated = new InvoiceContactMech();

		try {
			invoiceContactMechToBeUpdated = InvoiceContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceContactMech(invoiceContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceContactMech with the specific Id
	 * 
	 * @param invoiceContactMechToBeUpdated
	 *            the InvoiceContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceContactMech(@RequestBody InvoiceContactMech invoiceContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceContactMechToBeUpdated.setnull(null);

		UpdateInvoiceContactMech command = new UpdateInvoiceContactMech(invoiceContactMechToBeUpdated);

		try {
			if(((InvoiceContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceContactMechId", invoiceContactMechId);
		try {

			Object foundInvoiceContactMech = findInvoiceContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceContactMechId}")
	public ResponseEntity<Object> deleteInvoiceContactMechByIdUpdated(@PathVariable String invoiceContactMechId) throws Exception {
		DeleteInvoiceContactMech command = new DeleteInvoiceContactMech(invoiceContactMechId);

		try {
			if (((InvoiceContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceContactMech could not be deleted");

	}

}
