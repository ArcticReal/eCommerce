package com.skytala.eCommerce.domain.accounting.relations.billingAccount.control.role;

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
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.role.AddBillingAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.role.DeleteBillingAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.role.UpdateBillingAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role.BillingAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role.BillingAccountRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role.BillingAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role.BillingAccountRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.role.BillingAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.role.BillingAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.role.FindBillingAccountRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/billingAccount/billingAccountRoles")
public class BillingAccountRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BillingAccountRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BillingAccountRole
	 * @return a List with the BillingAccountRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<BillingAccountRole>> findBillingAccountRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBillingAccountRolesBy query = new FindBillingAccountRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BillingAccountRole> billingAccountRoles =((BillingAccountRoleFound) Scheduler.execute(query).data()).getBillingAccountRoles();

		return ResponseEntity.ok().body(billingAccountRoles);

	}

	/**
	 * creates a new BillingAccountRole entry in the ofbiz database
	 * 
	 * @param billingAccountRoleToBeAdded
	 *            the BillingAccountRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BillingAccountRole> createBillingAccountRole(@RequestBody BillingAccountRole billingAccountRoleToBeAdded) throws Exception {

		AddBillingAccountRole command = new AddBillingAccountRole(billingAccountRoleToBeAdded);
		BillingAccountRole billingAccountRole = ((BillingAccountRoleAdded) Scheduler.execute(command).data()).getAddedBillingAccountRole();
		
		if (billingAccountRole != null) 
			return successful(billingAccountRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the BillingAccountRole with the specific Id
	 * 
	 * @param billingAccountRoleToBeUpdated
	 *            the BillingAccountRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateBillingAccountRole(@RequestBody BillingAccountRole billingAccountRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		billingAccountRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateBillingAccountRole command = new UpdateBillingAccountRole(billingAccountRoleToBeUpdated);

		try {
			if(((BillingAccountRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{billingAccountRoleId}")
	public ResponseEntity<BillingAccountRole> findById(@PathVariable String billingAccountRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("billingAccountRoleId", billingAccountRoleId);
		try {

			List<BillingAccountRole> foundBillingAccountRole = findBillingAccountRolesBy(requestParams).getBody();
			if(foundBillingAccountRole.size()==1){				return successful(foundBillingAccountRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{billingAccountRoleId}")
	public ResponseEntity<String> deleteBillingAccountRoleByIdUpdated(@PathVariable String billingAccountRoleId) throws Exception {
		DeleteBillingAccountRole command = new DeleteBillingAccountRole(billingAccountRoleId);

		try {
			if (((BillingAccountRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
