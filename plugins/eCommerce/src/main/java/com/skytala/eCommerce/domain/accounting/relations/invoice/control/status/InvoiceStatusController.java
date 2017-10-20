package com.skytala.eCommerce.domain.accounting.relations.invoice.control.status;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.status.AddInvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.status.DeleteInvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.status.UpdateInvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.status.InvoiceStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.status.InvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.status.FindInvoiceStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/invoiceStatuss")
public class InvoiceStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceStatus
	 * @return a List with the InvoiceStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceStatussBy query = new FindInvoiceStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceStatus> invoiceStatuss =((InvoiceStatusFound) Scheduler.execute(query).data()).getInvoiceStatuss();

		if (invoiceStatuss.size() == 1) {
			return ResponseEntity.ok().body(invoiceStatuss.get(0));
		}

		return ResponseEntity.ok().body(invoiceStatuss);

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
	public ResponseEntity<Object> createInvoiceStatus(HttpServletRequest request) throws Exception {

		InvoiceStatus invoiceStatusToBeAdded = new InvoiceStatus();
		try {
			invoiceStatusToBeAdded = InvoiceStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceStatus(invoiceStatusToBeAdded);

	}

	/**
	 * creates a new InvoiceStatus entry in the ofbiz database
	 * 
	 * @param invoiceStatusToBeAdded
	 *            the InvoiceStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceStatus(@RequestBody InvoiceStatus invoiceStatusToBeAdded) throws Exception {

		AddInvoiceStatus command = new AddInvoiceStatus(invoiceStatusToBeAdded);
		InvoiceStatus invoiceStatus = ((InvoiceStatusAdded) Scheduler.execute(command).data()).getAddedInvoiceStatus();
		
		if (invoiceStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceStatus could not be created.");
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
	public boolean updateInvoiceStatus(HttpServletRequest request) throws Exception {

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

		InvoiceStatus invoiceStatusToBeUpdated = new InvoiceStatus();

		try {
			invoiceStatusToBeUpdated = InvoiceStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceStatus(invoiceStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceStatus with the specific Id
	 * 
	 * @param invoiceStatusToBeUpdated
	 *            the InvoiceStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceStatus(@RequestBody InvoiceStatus invoiceStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceStatusToBeUpdated.setnull(null);

		UpdateInvoiceStatus command = new UpdateInvoiceStatus(invoiceStatusToBeUpdated);

		try {
			if(((InvoiceStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceStatusId", invoiceStatusId);
		try {

			Object foundInvoiceStatus = findInvoiceStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceStatusId}")
	public ResponseEntity<Object> deleteInvoiceStatusByIdUpdated(@PathVariable String invoiceStatusId) throws Exception {
		DeleteInvoiceStatus command = new DeleteInvoiceStatus(invoiceStatusId);

		try {
			if (((InvoiceStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceStatus/\" plus one of the following: "
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
