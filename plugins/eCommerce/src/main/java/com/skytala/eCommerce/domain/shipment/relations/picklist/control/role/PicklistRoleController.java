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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findPicklistRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistRolesBy query = new FindPicklistRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistRole> picklistRoles =((PicklistRoleFound) Scheduler.execute(query).data()).getPicklistRoles();

		if (picklistRoles.size() == 1) {
			return ResponseEntity.ok().body(picklistRoles.get(0));
		}

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
	public ResponseEntity<Object> createPicklistRole(HttpServletRequest request) throws Exception {

		PicklistRole picklistRoleToBeAdded = new PicklistRole();
		try {
			picklistRoleToBeAdded = PicklistRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPicklistRole(@RequestBody PicklistRole picklistRoleToBeAdded) throws Exception {

		AddPicklistRole command = new AddPicklistRole(picklistRoleToBeAdded);
		PicklistRole picklistRole = ((PicklistRoleAdded) Scheduler.execute(command).data()).getAddedPicklistRole();
		
		if (picklistRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(picklistRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PicklistRole could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePicklistRole(HttpServletRequest request) throws Exception {

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

		PicklistRole picklistRoleToBeUpdated = new PicklistRole();

		try {
			picklistRoleToBeUpdated = PicklistRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePicklistRole(picklistRoleToBeUpdated, picklistRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePicklistRole(@RequestBody PicklistRole picklistRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		picklistRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePicklistRole command = new UpdatePicklistRole(picklistRoleToBeUpdated);

		try {
			if(((PicklistRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{picklistRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String picklistRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistRoleId", picklistRoleId);
		try {

			Object foundPicklistRole = findPicklistRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPicklistRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{picklistRoleId}")
	public ResponseEntity<Object> deletePicklistRoleByIdUpdated(@PathVariable String picklistRoleId) throws Exception {
		DeletePicklistRole command = new DeletePicklistRole(picklistRoleId);

		try {
			if (((PicklistRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PicklistRole could not be deleted");

	}

}
