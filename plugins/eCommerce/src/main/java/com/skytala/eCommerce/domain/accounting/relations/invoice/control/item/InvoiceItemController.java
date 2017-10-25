package com.skytala.eCommerce.domain.accounting.relations.invoice.control.item;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.item.AddInvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.item.DeleteInvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.item.UpdateInvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.item.InvoiceItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.item.InvoiceItem;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.item.FindInvoiceItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceItems")
public class InvoiceItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItem
	 * @return a List with the InvoiceItems
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemsBy query = new FindInvoiceItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItem> invoiceItems =((InvoiceItemFound) Scheduler.execute(query).data()).getInvoiceItems();

		if (invoiceItems.size() == 1) {
			return ResponseEntity.ok().body(invoiceItems.get(0));
		}

		return ResponseEntity.ok().body(invoiceItems);

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
	public ResponseEntity<Object> createInvoiceItem(HttpServletRequest request) throws Exception {

		InvoiceItem invoiceItemToBeAdded = new InvoiceItem();
		try {
			invoiceItemToBeAdded = InvoiceItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceItem(invoiceItemToBeAdded);

	}

	/**
	 * creates a new InvoiceItem entry in the ofbiz database
	 * 
	 * @param invoiceItemToBeAdded
	 *            the InvoiceItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceItem(@RequestBody InvoiceItem invoiceItemToBeAdded) throws Exception {

		AddInvoiceItem command = new AddInvoiceItem(invoiceItemToBeAdded);
		InvoiceItem invoiceItem = ((InvoiceItemAdded) Scheduler.execute(command).data()).getAddedInvoiceItem();
		
		if (invoiceItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItem could not be created.");
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
	public boolean updateInvoiceItem(HttpServletRequest request) throws Exception {

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

		InvoiceItem invoiceItemToBeUpdated = new InvoiceItem();

		try {
			invoiceItemToBeUpdated = InvoiceItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItem(invoiceItemToBeUpdated, invoiceItemToBeUpdated.getInvoiceItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceItem with the specific Id
	 * 
	 * @param invoiceItemToBeUpdated
	 *            the InvoiceItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceItem(@RequestBody InvoiceItem invoiceItemToBeUpdated,
			@PathVariable String invoiceItemSeqId) throws Exception {

		invoiceItemToBeUpdated.setInvoiceItemSeqId(invoiceItemSeqId);

		UpdateInvoiceItem command = new UpdateInvoiceItem(invoiceItemToBeUpdated);

		try {
			if(((InvoiceItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceItemId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemId", invoiceItemId);
		try {

			Object foundInvoiceItem = findInvoiceItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceItemId}")
	public ResponseEntity<Object> deleteInvoiceItemByIdUpdated(@PathVariable String invoiceItemId) throws Exception {
		DeleteInvoiceItem command = new DeleteInvoiceItem(invoiceItemId);

		try {
			if (((InvoiceItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItem could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceItem/\" plus one of the following: "
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
