package com.skytala.eCommerce.domain.accounting.relations.invoiceItemType;

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
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.command.AddInvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.command.DeleteInvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.command.UpdateInvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event.InvoiceItemTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event.InvoiceItemTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event.InvoiceItemTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event.InvoiceItemTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.mapper.InvoiceItemTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.model.InvoiceItemType;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.query.FindInvoiceItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceItemTypes")
public class InvoiceItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemType
	 * @return a List with the InvoiceItemTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypesBy query = new FindInvoiceItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemType> invoiceItemTypes =((InvoiceItemTypeFound) Scheduler.execute(query).data()).getInvoiceItemTypes();

		if (invoiceItemTypes.size() == 1) {
			return ResponseEntity.ok().body(invoiceItemTypes.get(0));
		}

		return ResponseEntity.ok().body(invoiceItemTypes);

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
	public ResponseEntity<Object> createInvoiceItemType(HttpServletRequest request) throws Exception {

		InvoiceItemType invoiceItemTypeToBeAdded = new InvoiceItemType();
		try {
			invoiceItemTypeToBeAdded = InvoiceItemTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceItemType(invoiceItemTypeToBeAdded);

	}

	/**
	 * creates a new InvoiceItemType entry in the ofbiz database
	 * 
	 * @param invoiceItemTypeToBeAdded
	 *            the InvoiceItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceItemType(@RequestBody InvoiceItemType invoiceItemTypeToBeAdded) throws Exception {

		AddInvoiceItemType command = new AddInvoiceItemType(invoiceItemTypeToBeAdded);
		InvoiceItemType invoiceItemType = ((InvoiceItemTypeAdded) Scheduler.execute(command).data()).getAddedInvoiceItemType();
		
		if (invoiceItemType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItemType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItemType could not be created.");
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
	public boolean updateInvoiceItemType(HttpServletRequest request) throws Exception {

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

		InvoiceItemType invoiceItemTypeToBeUpdated = new InvoiceItemType();

		try {
			invoiceItemTypeToBeUpdated = InvoiceItemTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItemType(invoiceItemTypeToBeUpdated, invoiceItemTypeToBeUpdated.getInvoiceItemTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceItemType with the specific Id
	 * 
	 * @param invoiceItemTypeToBeUpdated
	 *            the InvoiceItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceItemType(@RequestBody InvoiceItemType invoiceItemTypeToBeUpdated,
			@PathVariable String invoiceItemTypeId) throws Exception {

		invoiceItemTypeToBeUpdated.setInvoiceItemTypeId(invoiceItemTypeId);

		UpdateInvoiceItemType command = new UpdateInvoiceItemType(invoiceItemTypeToBeUpdated);

		try {
			if(((InvoiceItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceItemTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeId", invoiceItemTypeId);
		try {

			Object foundInvoiceItemType = findInvoiceItemTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItemType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceItemTypeId}")
	public ResponseEntity<Object> deleteInvoiceItemTypeByIdUpdated(@PathVariable String invoiceItemTypeId) throws Exception {
		DeleteInvoiceItemType command = new DeleteInvoiceItemType(invoiceItemTypeId);

		try {
			if (((InvoiceItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItemType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceItemType/\" plus one of the following: "
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
