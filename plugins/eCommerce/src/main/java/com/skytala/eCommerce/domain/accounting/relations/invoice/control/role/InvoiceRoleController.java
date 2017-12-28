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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceRoles")
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
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceRole>> findInvoiceRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceRolesBy query = new FindInvoiceRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceRole> invoiceRoles =((InvoiceRoleFound) Scheduler.execute(query).data()).getInvoiceRoles();

		return ResponseEntity.ok().body(invoiceRoles);

	}

	/**
	 * creates a new InvoiceRole entry in the ofbiz database
	 * 
	 * @param invoiceRoleToBeAdded
	 *            the InvoiceRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceRole> createInvoiceRole(@RequestBody InvoiceRole invoiceRoleToBeAdded) throws Exception {

		AddInvoiceRole command = new AddInvoiceRole(invoiceRoleToBeAdded);
		InvoiceRole invoiceRole = ((InvoiceRoleAdded) Scheduler.execute(command).data()).getAddedInvoiceRole();
		
		if (invoiceRole != null) 
			return successful(invoiceRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceRole with the specific Id
	 * 
	 * @param invoiceRoleToBeUpdated
	 *            the InvoiceRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceRole(@RequestBody InvoiceRole invoiceRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		invoiceRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateInvoiceRole command = new UpdateInvoiceRole(invoiceRoleToBeUpdated);

		try {
			if(((InvoiceRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceRoleId}")
	public ResponseEntity<InvoiceRole> findById(@PathVariable String invoiceRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceRoleId", invoiceRoleId);
		try {

			List<InvoiceRole> foundInvoiceRole = findInvoiceRolesBy(requestParams).getBody();
			if(foundInvoiceRole.size()==1){				return successful(foundInvoiceRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceRoleId}")
	public ResponseEntity<String> deleteInvoiceRoleByIdUpdated(@PathVariable String invoiceRoleId) throws Exception {
		DeleteInvoiceRole command = new DeleteInvoiceRole(invoiceRoleId);

		try {
			if (((InvoiceRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
