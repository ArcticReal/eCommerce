package com.skytala.eCommerce.domain.product.relations.facility.control.group;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.group.AddFacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.command.group.DeleteFacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.command.group.UpdateFacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.group.FacilityGroupMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.group.FacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.query.group.FindFacilityGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityGroups")
public class FacilityGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroup
	 * @return a List with the FacilityGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityGroup>> findFacilityGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupsBy query = new FindFacilityGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroup> facilityGroups =((FacilityGroupFound) Scheduler.execute(query).data()).getFacilityGroups();

		return ResponseEntity.ok().body(facilityGroups);

	}

	/**
	 * creates a new FacilityGroup entry in the ofbiz database
	 * 
	 * @param facilityGroupToBeAdded
	 *            the FacilityGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityGroup> createFacilityGroup(@RequestBody FacilityGroup facilityGroupToBeAdded) throws Exception {

		AddFacilityGroup command = new AddFacilityGroup(facilityGroupToBeAdded);
		FacilityGroup facilityGroup = ((FacilityGroupAdded) Scheduler.execute(command).data()).getAddedFacilityGroup();
		
		if (facilityGroup != null) 
			return successful(facilityGroup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityGroup with the specific Id
	 * 
	 * @param facilityGroupToBeUpdated
	 *            the FacilityGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{facilityGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityGroup(@RequestBody FacilityGroup facilityGroupToBeUpdated,
			@PathVariable String facilityGroupId) throws Exception {

		facilityGroupToBeUpdated.setFacilityGroupId(facilityGroupId);

		UpdateFacilityGroup command = new UpdateFacilityGroup(facilityGroupToBeUpdated);

		try {
			if(((FacilityGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityGroupId}")
	public ResponseEntity<FacilityGroup> findById(@PathVariable String facilityGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupId", facilityGroupId);
		try {

			List<FacilityGroup> foundFacilityGroup = findFacilityGroupsBy(requestParams).getBody();
			if(foundFacilityGroup.size()==1){				return successful(foundFacilityGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityGroupId}")
	public ResponseEntity<String> deleteFacilityGroupByIdUpdated(@PathVariable String facilityGroupId) throws Exception {
		DeleteFacilityGroup command = new DeleteFacilityGroup(facilityGroupId);

		try {
			if (((FacilityGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
