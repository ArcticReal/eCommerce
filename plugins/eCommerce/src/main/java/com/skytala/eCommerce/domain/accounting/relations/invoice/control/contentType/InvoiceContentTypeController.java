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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/invoiceContentTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceContentTypesBy query = new FindInvoiceContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceContentType> invoiceContentTypes =((InvoiceContentTypeFound) Scheduler.execute(query).data()).getInvoiceContentTypes();

		if (invoiceContentTypes.size() == 1) {
			return ResponseEntity.ok().body(invoiceContentTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createInvoiceContentType(HttpServletRequest request) throws Exception {

		InvoiceContentType invoiceContentTypeToBeAdded = new InvoiceContentType();
		try {
			invoiceContentTypeToBeAdded = InvoiceContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createInvoiceContentType(@RequestBody InvoiceContentType invoiceContentTypeToBeAdded) throws Exception {

		AddInvoiceContentType command = new AddInvoiceContentType(invoiceContentTypeToBeAdded);
		InvoiceContentType invoiceContentType = ((InvoiceContentTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceContentType();
		
		if (invoiceContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceContentType could not be created.");
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
	public boolean updateInvoiceContentType(HttpServletRequest request) throws Exception {

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

		InvoiceContentType invoiceContentTypeToBeUpdated = new InvoiceContentType();

		try {
			invoiceContentTypeToBeUpdated = InvoiceContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceContentType(invoiceContentTypeToBeUpdated, invoiceContentTypeToBeUpdated.getInvoiceContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInvoiceContentType(@RequestBody InvoiceContentType invoiceContentTypeToBeUpdated,
			@PathVariable String invoiceContentTypeId) throws Exception {

		invoiceContentTypeToBeUpdated.setInvoiceContentTypeId(invoiceContentTypeId);

		UpdateInvoiceContentType command = new UpdateInvoiceContentType(invoiceContentTypeToBeUpdated);

		try {
			if(((InvoiceContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceContentTypeId", invoiceContentTypeId);
		try {

			Object foundInvoiceContentType = findInvoiceContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceContentTypeId}")
	public ResponseEntity<Object> deleteInvoiceContentTypeByIdUpdated(@PathVariable String invoiceContentTypeId) throws Exception {
		DeleteInvoiceContentType command = new DeleteInvoiceContentType(invoiceContentTypeId);

		try {
			if (((InvoiceContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceContentType could not be deleted");

	}

}
