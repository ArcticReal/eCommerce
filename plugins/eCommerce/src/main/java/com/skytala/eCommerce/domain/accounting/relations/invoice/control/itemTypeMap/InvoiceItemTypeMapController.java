package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemTypeMap;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap.AddInvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap.DeleteInvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap.UpdateInvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeMap.InvoiceItemTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemTypeMap.FindInvoiceItemTypeMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceItemTypeMaps")
public class InvoiceItemTypeMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemTypeMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemTypeMap
	 * @return a List with the InvoiceItemTypeMaps
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceItemTypeMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypeMapsBy query = new FindInvoiceItemTypeMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemTypeMap> invoiceItemTypeMaps =((InvoiceItemTypeMapFound) Scheduler.execute(query).data()).getInvoiceItemTypeMaps();

		if (invoiceItemTypeMaps.size() == 1) {
			return ResponseEntity.ok().body(invoiceItemTypeMaps.get(0));
		}

		return ResponseEntity.ok().body(invoiceItemTypeMaps);

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
	public ResponseEntity<Object> createInvoiceItemTypeMap(HttpServletRequest request) throws Exception {

		InvoiceItemTypeMap invoiceItemTypeMapToBeAdded = new InvoiceItemTypeMap();
		try {
			invoiceItemTypeMapToBeAdded = InvoiceItemTypeMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceItemTypeMap(invoiceItemTypeMapToBeAdded);

	}

	/**
	 * creates a new InvoiceItemTypeMap entry in the ofbiz database
	 * 
	 * @param invoiceItemTypeMapToBeAdded
	 *            the InvoiceItemTypeMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceItemTypeMap(@RequestBody InvoiceItemTypeMap invoiceItemTypeMapToBeAdded) throws Exception {

		AddInvoiceItemTypeMap command = new AddInvoiceItemTypeMap(invoiceItemTypeMapToBeAdded);
		InvoiceItemTypeMap invoiceItemTypeMap = ((InvoiceItemTypeMapAdded) Scheduler.execute(command).data()).getAddedInvoiceItemTypeMap();
		
		if (invoiceItemTypeMap != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceItemTypeMap);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceItemTypeMap could not be created.");
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
	public boolean updateInvoiceItemTypeMap(HttpServletRequest request) throws Exception {

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

		InvoiceItemTypeMap invoiceItemTypeMapToBeUpdated = new InvoiceItemTypeMap();

		try {
			invoiceItemTypeMapToBeUpdated = InvoiceItemTypeMapMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceItemTypeMap(invoiceItemTypeMapToBeUpdated, invoiceItemTypeMapToBeUpdated.getInvoiceItemMapKey()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceItemTypeMap with the specific Id
	 * 
	 * @param invoiceItemTypeMapToBeUpdated
	 *            the InvoiceItemTypeMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemMapKey}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceItemTypeMap(@RequestBody InvoiceItemTypeMap invoiceItemTypeMapToBeUpdated,
			@PathVariable String invoiceItemMapKey) throws Exception {

		invoiceItemTypeMapToBeUpdated.setInvoiceItemMapKey(invoiceItemMapKey);

		UpdateInvoiceItemTypeMap command = new UpdateInvoiceItemTypeMap(invoiceItemTypeMapToBeUpdated);

		try {
			if(((InvoiceItemTypeMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceItemTypeMapId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceItemTypeMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeMapId", invoiceItemTypeMapId);
		try {

			Object foundInvoiceItemTypeMap = findInvoiceItemTypeMapsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceItemTypeMap);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceItemTypeMapId}")
	public ResponseEntity<Object> deleteInvoiceItemTypeMapByIdUpdated(@PathVariable String invoiceItemTypeMapId) throws Exception {
		DeleteInvoiceItemTypeMap command = new DeleteInvoiceItemTypeMap(invoiceItemTypeMapId);

		try {
			if (((InvoiceItemTypeMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceItemTypeMap could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceItemTypeMap/\" plus one of the following: "
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
