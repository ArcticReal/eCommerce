package com.skytala.eCommerce.domain.accounting.relations.invoice.control.content;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.content.AddInvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.content.DeleteInvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.content.UpdateInvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.content.InvoiceContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.content.FindInvoiceContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/invoiceContents")
public class InvoiceContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceContent
	 * @return a List with the InvoiceContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceContentsBy query = new FindInvoiceContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceContent> invoiceContents =((InvoiceContentFound) Scheduler.execute(query).data()).getInvoiceContents();

		if (invoiceContents.size() == 1) {
			return ResponseEntity.ok().body(invoiceContents.get(0));
		}

		return ResponseEntity.ok().body(invoiceContents);

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
	public ResponseEntity<Object> createInvoiceContent(HttpServletRequest request) throws Exception {

		InvoiceContent invoiceContentToBeAdded = new InvoiceContent();
		try {
			invoiceContentToBeAdded = InvoiceContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceContent(invoiceContentToBeAdded);

	}

	/**
	 * creates a new InvoiceContent entry in the ofbiz database
	 * 
	 * @param invoiceContentToBeAdded
	 *            the InvoiceContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceContent(@RequestBody InvoiceContent invoiceContentToBeAdded) throws Exception {

		AddInvoiceContent command = new AddInvoiceContent(invoiceContentToBeAdded);
		InvoiceContent invoiceContent = ((InvoiceContentAdded) Scheduler.execute(command).data()).getAddedInvoiceContent();
		
		if (invoiceContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceContent could not be created.");
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
	public boolean updateInvoiceContent(HttpServletRequest request) throws Exception {

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

		InvoiceContent invoiceContentToBeUpdated = new InvoiceContent();

		try {
			invoiceContentToBeUpdated = InvoiceContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceContent(invoiceContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceContent with the specific Id
	 * 
	 * @param invoiceContentToBeUpdated
	 *            the InvoiceContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceContent(@RequestBody InvoiceContent invoiceContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceContentToBeUpdated.setnull(null);

		UpdateInvoiceContent command = new UpdateInvoiceContent(invoiceContentToBeUpdated);

		try {
			if(((InvoiceContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceContentId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceContentId", invoiceContentId);
		try {

			Object foundInvoiceContent = findInvoiceContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceContentId}")
	public ResponseEntity<Object> deleteInvoiceContentByIdUpdated(@PathVariable String invoiceContentId) throws Exception {
		DeleteInvoiceContent command = new DeleteInvoiceContent(invoiceContentId);

		try {
			if (((InvoiceContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceContent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceContent/\" plus one of the following: "
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