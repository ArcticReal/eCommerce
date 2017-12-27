package com.skytala.eCommerce.domain.shipment.relations.picklist.control.role;

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
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.role.AddPicklistRole;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.role.DeletePicklistRole;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.role.UpdatePicklistRole;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.role.PicklistRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.role.PicklistRoleDeleted;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.role.PicklistRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.role.PicklistRoleUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.role.PicklistRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.role.PicklistRole;
import com.skytala.eCommerce.domain.shipment.relations.picklist.query.role.FindPicklistRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/picklist/picklistRoles")
public class PicklistRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PicklistRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PicklistRole
	 * @return a List with the PicklistRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PicklistRole>> findPicklistRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistRolesBy query = new FindPicklistRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistRole> picklistRoles =((PicklistRoleFound) Scheduler.execute(query).data()).getPicklistRoles();

		return ResponseEntity.ok().body(picklistRoles);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PicklistRole> createPicklistRole(HttpServletRequest request) throws Exception {

		PicklistRole picklistRoleToBeAdded = new PicklistRole();
		try {
			picklistRoleToBeAdded = PicklistRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPicklistRole(picklistRoleToBeAdded);

	}

	/**
	 * creates a new PicklistRole entry in the ofbiz database
	 * 
	 * @param picklistRoleToBeAdded
	 *            the PicklistRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PicklistRole> createPicklistRole(@RequestBody PicklistRole picklistRoleToBeAdded) throws Exception {

		AddPicklistRole command = new AddPicklistRole(picklistRoleToBeAdded);
		PicklistRole picklistRole = ((PicklistRoleAdded) Scheduler.execute(command).data()).getAddedPicklistRole();
		
		if (picklistRole != null) 
			return successful(picklistRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PicklistRole with the specific Id
	 * 
	 * @param picklistRoleToBeUpdated
	 *            the PicklistRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePicklistRole(@RequestBody PicklistRole picklistRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		picklistRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePicklistRole command = new UpdatePicklistRole(picklistRoleToBeUpdated);

		try {
			if(((PicklistRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{picklistRoleId}")
	public ResponseEntity<PicklistRole> findById(@PathVariable String picklistRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistRoleId", picklistRoleId);
		try {

			List<PicklistRole> foundPicklistRole = findPicklistRolesBy(requestParams).getBody();
			if(foundPicklistRole.size()==1){				return successful(foundPicklistRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{picklistRoleId}")
	public ResponseEntity<String> deletePicklistRoleByIdUpdated(@PathVariable String picklistRoleId) throws Exception {
		DeletePicklistRole command = new DeletePicklistRole(picklistRoleId);

		try {
			if (((PicklistRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
