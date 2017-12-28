package com.skytala.eCommerce.domain.product.relations.facility.control.groupRole;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRole.AddFacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRole.DeleteFacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRole.UpdateFacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupRole.FacilityGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupRole.FacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupRole.FindFacilityGroupRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityGroupRoles")
public class FacilityGroupRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupRole
	 * @return a List with the FacilityGroupRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityGroupRole>> findFacilityGroupRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupRolesBy query = new FindFacilityGroupRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupRole> facilityGroupRoles =((FacilityGroupRoleFound) Scheduler.execute(query).data()).getFacilityGroupRoles();

		return ResponseEntity.ok().body(facilityGroupRoles);

	}

	/**
	 * creates a new FacilityGroupRole entry in the ofbiz database
	 * 
	 * @param facilityGroupRoleToBeAdded
	 *            the FacilityGroupRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityGroupRole> createFacilityGroupRole(@RequestBody FacilityGroupRole facilityGroupRoleToBeAdded) throws Exception {

		AddFacilityGroupRole command = new AddFacilityGroupRole(facilityGroupRoleToBeAdded);
		FacilityGroupRole facilityGroupRole = ((FacilityGroupRoleAdded) Scheduler.execute(command).data()).getAddedFacilityGroupRole();
		
		if (facilityGroupRole != null) 
			return successful(facilityGroupRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityGroupRole with the specific Id
	 * 
	 * @param facilityGroupRoleToBeUpdated
	 *            the FacilityGroupRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityGroupRole(@RequestBody FacilityGroupRole facilityGroupRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		facilityGroupRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateFacilityGroupRole command = new UpdateFacilityGroupRole(facilityGroupRoleToBeUpdated);

		try {
			if(((FacilityGroupRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityGroupRoleId}")
	public ResponseEntity<FacilityGroupRole> findById(@PathVariable String facilityGroupRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupRoleId", facilityGroupRoleId);
		try {

			List<FacilityGroupRole> foundFacilityGroupRole = findFacilityGroupRolesBy(requestParams).getBody();
			if(foundFacilityGroupRole.size()==1){				return successful(foundFacilityGroupRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityGroupRoleId}")
	public ResponseEntity<String> deleteFacilityGroupRoleByIdUpdated(@PathVariable String facilityGroupRoleId) throws Exception {
		DeleteFacilityGroupRole command = new DeleteFacilityGroupRole(facilityGroupRoleId);

		try {
			if (((FacilityGroupRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
