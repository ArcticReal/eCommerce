package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.command.AddInvoiceTermAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.command.DeleteInvoiceTermAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.command.UpdateInvoiceTermAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event.InvoiceTermAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event.InvoiceTermAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event.InvoiceTermAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event.InvoiceTermAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.mapper.InvoiceTermAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.query.FindInvoiceTermAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceTermAttributes")
public class InvoiceTermAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceTermAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceTermAttribute
	 * @return a List with the InvoiceTermAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTermAttributesBy query = new FindInvoiceTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceTermAttribute> invoiceTermAttributes =((InvoiceTermAttributeFound) Scheduler.execute(query).data()).getInvoiceTermAttributes();

		if (invoiceTermAttributes.size() == 1) {
			return ResponseEntity.ok().body(invoiceTermAttributes.get(0));
		}

		return ResponseEntity.ok().body(invoiceTermAttributes);

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
	public ResponseEntity<Object> createInvoiceTermAttribute(HttpServletRequest request) throws Exception {

		InvoiceTermAttribute invoiceTermAttributeToBeAdded = new InvoiceTermAttribute();
		try {
			invoiceTermAttributeToBeAdded = InvoiceTermAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceTermAttribute(invoiceTermAttributeToBeAdded);

	}

	/**
	 * creates a new InvoiceTermAttribute entry in the ofbiz database
	 * 
	 * @param invoiceTermAttributeToBeAdded
	 *            the InvoiceTermAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceTermAttribute(@RequestBody InvoiceTermAttribute invoiceTermAttributeToBeAdded) throws Exception {

		AddInvoiceTermAttribute command = new AddInvoiceTermAttribute(invoiceTermAttributeToBeAdded);
		InvoiceTermAttribute invoiceTermAttribute = ((InvoiceTermAttributeAdded) Scheduler.execute(command).data()).getAddedInvoiceTermAttribute();
		
		if (invoiceTermAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceTermAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceTermAttribute could not be created.");
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
	public boolean updateInvoiceTermAttribute(HttpServletRequest request) throws Exception {

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

		InvoiceTermAttribute invoiceTermAttributeToBeUpdated = new InvoiceTermAttribute();

		try {
			invoiceTermAttributeToBeUpdated = InvoiceTermAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceTermAttribute(invoiceTermAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceTermAttribute with the specific Id
	 * 
	 * @param invoiceTermAttributeToBeUpdated
	 *            the InvoiceTermAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceTermAttribute(@RequestBody InvoiceTermAttribute invoiceTermAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceTermAttributeToBeUpdated.setnull(null);

		UpdateInvoiceTermAttribute command = new UpdateInvoiceTermAttribute(invoiceTermAttributeToBeUpdated);

		try {
			if(((InvoiceTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceTermAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTermAttributeId", invoiceTermAttributeId);
		try {

			Object foundInvoiceTermAttribute = findInvoiceTermAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceTermAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceTermAttributeId}")
	public ResponseEntity<Object> deleteInvoiceTermAttributeByIdUpdated(@PathVariable String invoiceTermAttributeId) throws Exception {
		DeleteInvoiceTermAttribute command = new DeleteInvoiceTermAttribute(invoiceTermAttributeId);

		try {
			if (((InvoiceTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceTermAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceTermAttribute/\" plus one of the following: "
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
