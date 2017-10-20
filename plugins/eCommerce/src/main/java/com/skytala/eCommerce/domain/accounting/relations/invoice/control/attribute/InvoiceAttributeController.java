package com.skytala.eCommerce.domain.accounting.relations.invoice.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute.AddInvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute.DeleteInvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute.UpdateInvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.attribute.InvoiceAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.attribute.InvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.attribute.FindInvoiceAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/invoiceAttributes")
public class InvoiceAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceAttribute
	 * @return a List with the InvoiceAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceAttributesBy query = new FindInvoiceAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceAttribute> invoiceAttributes =((InvoiceAttributeFound) Scheduler.execute(query).data()).getInvoiceAttributes();

		if (invoiceAttributes.size() == 1) {
			return ResponseEntity.ok().body(invoiceAttributes.get(0));
		}

		return ResponseEntity.ok().body(invoiceAttributes);

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
	public ResponseEntity<Object> createInvoiceAttribute(HttpServletRequest request) throws Exception {

		InvoiceAttribute invoiceAttributeToBeAdded = new InvoiceAttribute();
		try {
			invoiceAttributeToBeAdded = InvoiceAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceAttribute(invoiceAttributeToBeAdded);

	}

	/**
	 * creates a new InvoiceAttribute entry in the ofbiz database
	 * 
	 * @param invoiceAttributeToBeAdded
	 *            the InvoiceAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceAttribute(@RequestBody InvoiceAttribute invoiceAttributeToBeAdded) throws Exception {

		AddInvoiceAttribute command = new AddInvoiceAttribute(invoiceAttributeToBeAdded);
		InvoiceAttribute invoiceAttribute = ((InvoiceAttributeAdded) Scheduler.execute(command).data()).getAddedInvoiceAttribute();
		
		if (invoiceAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceAttribute could not be created.");
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
	public boolean updateInvoiceAttribute(HttpServletRequest request) throws Exception {

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

		InvoiceAttribute invoiceAttributeToBeUpdated = new InvoiceAttribute();

		try {
			invoiceAttributeToBeUpdated = InvoiceAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceAttribute(invoiceAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceAttribute with the specific Id
	 * 
	 * @param invoiceAttributeToBeUpdated
	 *            the InvoiceAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceAttribute(@RequestBody InvoiceAttribute invoiceAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceAttributeToBeUpdated.setnull(null);

		UpdateInvoiceAttribute command = new UpdateInvoiceAttribute(invoiceAttributeToBeUpdated);

		try {
			if(((InvoiceAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceAttributeId", invoiceAttributeId);
		try {

			Object foundInvoiceAttribute = findInvoiceAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceAttributeId}")
	public ResponseEntity<Object> deleteInvoiceAttributeByIdUpdated(@PathVariable String invoiceAttributeId) throws Exception {
		DeleteInvoiceAttribute command = new DeleteInvoiceAttribute(invoiceAttributeId);

		try {
			if (((InvoiceAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceAttribute could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceAttribute/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
