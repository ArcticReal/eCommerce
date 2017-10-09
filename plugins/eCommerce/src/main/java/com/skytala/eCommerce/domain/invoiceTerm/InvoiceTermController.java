package com.skytala.eCommerce.domain.invoiceTerm;

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
import com.skytala.eCommerce.domain.invoiceTerm.command.AddInvoiceTerm;
import com.skytala.eCommerce.domain.invoiceTerm.command.DeleteInvoiceTerm;
import com.skytala.eCommerce.domain.invoiceTerm.command.UpdateInvoiceTerm;
import com.skytala.eCommerce.domain.invoiceTerm.event.InvoiceTermAdded;
import com.skytala.eCommerce.domain.invoiceTerm.event.InvoiceTermDeleted;
import com.skytala.eCommerce.domain.invoiceTerm.event.InvoiceTermFound;
import com.skytala.eCommerce.domain.invoiceTerm.event.InvoiceTermUpdated;
import com.skytala.eCommerce.domain.invoiceTerm.mapper.InvoiceTermMapper;
import com.skytala.eCommerce.domain.invoiceTerm.model.InvoiceTerm;
import com.skytala.eCommerce.domain.invoiceTerm.query.FindInvoiceTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceTerms")
public class InvoiceTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceTerm
	 * @return a List with the InvoiceTerms
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTermsBy query = new FindInvoiceTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceTerm> invoiceTerms =((InvoiceTermFound) Scheduler.execute(query).data()).getInvoiceTerms();

		if (invoiceTerms.size() == 1) {
			return ResponseEntity.ok().body(invoiceTerms.get(0));
		}

		return ResponseEntity.ok().body(invoiceTerms);

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
	public ResponseEntity<Object> createInvoiceTerm(HttpServletRequest request) throws Exception {

		InvoiceTerm invoiceTermToBeAdded = new InvoiceTerm();
		try {
			invoiceTermToBeAdded = InvoiceTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceTerm(invoiceTermToBeAdded);

	}

	/**
	 * creates a new InvoiceTerm entry in the ofbiz database
	 * 
	 * @param invoiceTermToBeAdded
	 *            the InvoiceTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceTerm(@RequestBody InvoiceTerm invoiceTermToBeAdded) throws Exception {

		AddInvoiceTerm command = new AddInvoiceTerm(invoiceTermToBeAdded);
		InvoiceTerm invoiceTerm = ((InvoiceTermAdded) Scheduler.execute(command).data()).getAddedInvoiceTerm();
		
		if (invoiceTerm != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceTerm);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceTerm could not be created.");
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
	public boolean updateInvoiceTerm(HttpServletRequest request) throws Exception {

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

		InvoiceTerm invoiceTermToBeUpdated = new InvoiceTerm();

		try {
			invoiceTermToBeUpdated = InvoiceTermMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceTerm(invoiceTermToBeUpdated, invoiceTermToBeUpdated.getInvoiceTermId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceTerm with the specific Id
	 * 
	 * @param invoiceTermToBeUpdated
	 *            the InvoiceTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceTermId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceTerm(@RequestBody InvoiceTerm invoiceTermToBeUpdated,
			@PathVariable String invoiceTermId) throws Exception {

		invoiceTermToBeUpdated.setInvoiceTermId(invoiceTermId);

		UpdateInvoiceTerm command = new UpdateInvoiceTerm(invoiceTermToBeUpdated);

		try {
			if(((InvoiceTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceTermId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTermId", invoiceTermId);
		try {

			Object foundInvoiceTerm = findInvoiceTermsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceTerm);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceTermId}")
	public ResponseEntity<Object> deleteInvoiceTermByIdUpdated(@PathVariable String invoiceTermId) throws Exception {
		DeleteInvoiceTerm command = new DeleteInvoiceTerm(invoiceTermId);

		try {
			if (((InvoiceTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceTerm could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceTerm/\" plus one of the following: "
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