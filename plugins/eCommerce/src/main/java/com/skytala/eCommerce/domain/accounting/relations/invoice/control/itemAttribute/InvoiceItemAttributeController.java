package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemAttribute;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute.AddInvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute.DeleteInvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAttribute.UpdateInvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAttribute.InvoiceItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemAttribute.FindInvoiceItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceItemAttributes")
public class InvoiceItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemAttribute
	 * @return a List with the InvoiceItemAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemAttributesBy query = new FindInvoiceItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemAttribute> invoiceItemAttributes =((InvoiceItemAttributeFound) Scheduler.execute(query).data()).getInvoiceItemAttributes();

		if (invoiceItemAttributes.size() == 1) {
			return ResponseEntity.ok().body(invoiceItemAttributes.get(0));
		}

		return ResponseEntity.ok().body(invoiceItemAttributes);

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
	public ResponseEntity<Object> createInvoiceItemAttribute(HttpServletRequest request) throws Exception {

		InvoiceItemAttribute invoiceItemAttributeToBeAdded = new InvoiceItemAttribute();
		try {
			invoiceItemAttributeToBeAdded = InvoiceItemAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceItemAttribute(invoiceItemAttributeToBeAdded);

	}

	/**
	 * creates a new InvoiceItemAttribute entry in the ofbiz database
	 * 
	 * @param invoiceItemAttributeToBeAdded
	 *            the InvoiceItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceItemAttribute(@RequestBody InvoiceItemAttribute invoiceItemAttributeToBeAdded) throws Exception {

		AddInvoiceItemAttribute command = new AddInvoiceItemAttribute(invoiceItemAttributeToBeAdded);
		InvoiceItemAttribute invoiceItemAttribute = ((InvoiceItemAttributeAdded) Scheduler.execute(command).data()).getAddedInvoiceItemAttribute();
		
		if (invoiceItemAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItemAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItemAttribute could not be created.");
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
	public boolean updateInvoiceItemAttribute(HttpServletRequest request) throws Exception {

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

		InvoiceItemAttribute invoiceItemAttributeToBeUpdated = new InvoiceItemAttribute();

		try {
			invoiceItemAttributeToBeUpdated = InvoiceItemAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItemAttribute(invoiceItemAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceItemAttribute with the specific Id
	 * 
	 * @param invoiceItemAttributeToBeUpdated
	 *            the InvoiceItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceItemAttribute(@RequestBody InvoiceItemAttribute invoiceItemAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceItemAttributeToBeUpdated.setnull(null);

		UpdateInvoiceItemAttribute command = new UpdateInvoiceItemAttribute(invoiceItemAttributeToBeUpdated);

		try {
			if(((InvoiceItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceItemAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemAttributeId", invoiceItemAttributeId);
		try {

			Object foundInvoiceItemAttribute = findInvoiceItemAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItemAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceItemAttributeId}")
	public ResponseEntity<Object> deleteInvoiceItemAttributeByIdUpdated(@PathVariable String invoiceItemAttributeId) throws Exception {
		DeleteInvoiceItemAttribute command = new DeleteInvoiceItemAttribute(invoiceItemAttributeId);

		try {
			if (((InvoiceItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItemAttribute could not be deleted");

	}

}
