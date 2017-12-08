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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/invoiceTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTypesBy query = new FindInvoiceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceType> invoiceTypes =((InvoiceTypeFound) Scheduler.execute(query).data()).getInvoiceTypes();

		if (invoiceTypes.size() == 1) {
			return ResponseEntity.ok().body(invoiceTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createInvoiceType(HttpServletRequest request) throws Exception {

		InvoiceType invoiceTypeToBeAdded = new InvoiceType();
		try {
			invoiceTypeToBeAdded = InvoiceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createInvoiceType(@RequestBody InvoiceType invoiceTypeToBeAdded) throws Exception {

		AddInvoiceType command = new AddInvoiceType(invoiceTypeToBeAdded);
		InvoiceType invoiceType = ((InvoiceTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceType();
		
		if (invoiceType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceType could not be created.");
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
	public boolean updateInvoiceType(HttpServletRequest request) throws Exception {

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

		InvoiceType invoiceTypeToBeUpdated = new InvoiceType();

		try {
			invoiceTypeToBeUpdated = InvoiceTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceType(invoiceTypeToBeUpdated, invoiceTypeToBeUpdated.getInvoiceTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInvoiceType(@RequestBody InvoiceType invoiceTypeToBeUpdated,
			@PathVariable String invoiceTypeId) throws Exception {

		invoiceTypeToBeUpdated.setInvoiceTypeId(invoiceTypeId);

		UpdateInvoiceType command = new UpdateInvoiceType(invoiceTypeToBeUpdated);

		try {
			if(((InvoiceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTypeId", invoiceTypeId);
		try {

			Object foundInvoiceType = findInvoiceTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceTypeId}")
	public ResponseEntity<Object> deleteInvoiceTypeByIdUpdated(@PathVariable String invoiceTypeId) throws Exception {
		DeleteInvoiceType command = new DeleteInvoiceType(invoiceTypeId);

		try {
			if (((InvoiceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceType could not be deleted");

	}

}
