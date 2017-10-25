package com.skytala.eCommerce.domain.accounting.relations.invoice.control.role;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.role.AddInvoiceRole;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.role.DeleteInvoiceRole;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.role.UpdateInvoiceRole;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.role.InvoiceRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.role.InvoiceRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.role.InvoiceRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.role.InvoiceRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.role.InvoiceRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.role.InvoiceRole;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.role.FindInvoiceRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceRoles")
public class InvoiceRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceRole
	 * @return a List with the InvoiceRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceRolesBy query = new FindInvoiceRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceRole> invoiceRoles =((InvoiceRoleFound) Scheduler.execute(query).data()).getInvoiceRoles();

		if (invoiceRoles.size() == 1) {
			return ResponseEntity.ok().body(invoiceRoles.get(0));
		}

		return ResponseEntity.ok().body(invoiceRoles);

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
	public ResponseEntity<Object> createInvoiceRole(HttpServletRequest request) throws Exception {

		InvoiceRole invoiceRoleToBeAdded = new InvoiceRole();
		try {
			invoiceRoleToBeAdded = InvoiceRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceRole(invoiceRoleToBeAdded);

	}

	/**
	 * creates a new InvoiceRole entry in the ofbiz database
	 * 
	 * @param invoiceRoleToBeAdded
	 *            the InvoiceRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceRole(@RequestBody InvoiceRole invoiceRoleToBeAdded) throws Exception {

		AddInvoiceRole command = new AddInvoiceRole(invoiceRoleToBeAdded);
		InvoiceRole invoiceRole = ((InvoiceRoleAdded) Scheduler.execute(command).data()).getAddedInvoiceRole();
		
		if (invoiceRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceRole could not be created.");
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
	public boolean updateInvoiceRole(HttpServletRequest request) throws Exception {

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

		InvoiceRole invoiceRoleToBeUpdated = new InvoiceRole();

		try {
			invoiceRoleToBeUpdated = InvoiceRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceRole(invoiceRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceRole with the specific Id
	 * 
	 * @param invoiceRoleToBeUpdated
	 *            the InvoiceRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceRole(@RequestBody InvoiceRole invoiceRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceRoleToBeUpdated.setnull(null);

		UpdateInvoiceRole command = new UpdateInvoiceRole(invoiceRoleToBeUpdated);

		try {
			if(((InvoiceRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceRoleId", invoiceRoleId);
		try {

			Object foundInvoiceRole = findInvoiceRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceRoleId}")
	public ResponseEntity<Object> deleteInvoiceRoleByIdUpdated(@PathVariable String invoiceRoleId) throws Exception {
		DeleteInvoiceRole command = new DeleteInvoiceRole(invoiceRoleId);

		try {
			if (((InvoiceRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/invoiceRole/\" plus one of the following: "
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
