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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/invoiceItemAssocTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceItemAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemAssocTypesBy query = new FindInvoiceItemAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemAssocType> invoiceItemAssocTypes =((InvoiceItemAssocTypeFound) Scheduler.execute(query).data()).getInvoiceItemAssocTypes();

		if (invoiceItemAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(invoiceItemAssocTypes.get(0));
		}

		return ResponseEntity.ok().body(invoiceItemAssocTypes);

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
	public ResponseEntity<Object> createInvoiceItemAssocType(HttpServletRequest request) throws Exception {

		InvoiceItemAssocType invoiceItemAssocTypeToBeAdded = new InvoiceItemAssocType();
		try {
			invoiceItemAssocTypeToBeAdded = InvoiceItemAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceItemAssocType(invoiceItemAssocTypeToBeAdded);

	}

	/**
	 * creates a new InvoiceItemAssocType entry in the ofbiz database
	 * 
	 * @param invoiceItemAssocTypeToBeAdded
	 *            the InvoiceItemAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceItemAssocType(@RequestBody InvoiceItemAssocType invoiceItemAssocTypeToBeAdded) throws Exception {

		AddInvoiceItemAssocType command = new AddInvoiceItemAssocType(invoiceItemAssocTypeToBeAdded);
		InvoiceItemAssocType invoiceItemAssocType = ((InvoiceItemAssocTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceItemAssocType();
		
		if (invoiceItemAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItemAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItemAssocType could not be created.");
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
	public boolean updateInvoiceItemAssocType(HttpServletRequest request) throws Exception {

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

		InvoiceItemAssocType invoiceItemAssocTypeToBeUpdated = new InvoiceItemAssocType();

		try {
			invoiceItemAssocTypeToBeUpdated = InvoiceItemAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItemAssocType(invoiceItemAssocTypeToBeUpdated, invoiceItemAssocTypeToBeUpdated.getInvoiceItemAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInvoiceItemAssocType(@RequestBody InvoiceItemAssocType invoiceItemAssocTypeToBeUpdated,
			@PathVariable String invoiceItemAssocTypeId) throws Exception {

		invoiceItemAssocTypeToBeUpdated.setInvoiceItemAssocTypeId(invoiceItemAssocTypeId);

		UpdateInvoiceItemAssocType command = new UpdateInvoiceItemAssocType(invoiceItemAssocTypeToBeUpdated);

		try {
			if(((InvoiceItemAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceItemAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemAssocTypeId", invoiceItemAssocTypeId);
		try {

			Object foundInvoiceItemAssocType = findInvoiceItemAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItemAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceItemAssocTypeId}")
	public ResponseEntity<Object> deleteInvoiceItemAssocTypeByIdUpdated(@PathVariable String invoiceItemAssocTypeId) throws Exception {
		DeleteInvoiceItemAssocType command = new DeleteInvoiceItemAssocType(invoiceItemAssocTypeId);

		try {
			if (((InvoiceItemAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItemAssocType could not be deleted");

	}

}
