package com.skytala.eCommerce.domain.product.relations.facility.control.groupMember;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupMember.AddFacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupMember.DeleteFacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupMember.UpdateFacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupMember.FacilityGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupMember.FindFacilityGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityGroupMembers")
public class FacilityGroupMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupMember
	 * @return a List with the FacilityGroupMembers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityGroupMember>> findFacilityGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupMembersBy query = new FindFacilityGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupMember> facilityGroupMembers =((FacilityGroupMemberFound) Scheduler.execute(query).data()).getFacilityGroupMembers();

		return ResponseEntity.ok().body(facilityGroupMembers);

	}

	/**
	 * creates a new FacilityGroupMember entry in the ofbiz database
	 * 
	 * @param facilityGroupMemberToBeAdded
	 *            the FacilityGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityGroupMember> createFacilityGroupMember(@RequestBody FacilityGroupMember facilityGroupMemberToBeAdded) throws Exception {

		AddFacilityGroupMember command = new AddFacilityGroupMember(facilityGroupMemberToBeAdded);
		FacilityGroupMember facilityGroupMember = ((FacilityGroupMemberAdded) Scheduler.execute(command).data()).getAddedFacilityGroupMember();
		
		if (facilityGroupMember != null) 
			return successful(facilityGroupMember);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityGroupMember with the specific Id
	 * 
	 * @param facilityGroupMemberToBeUpdated
	 *            the FacilityGroupMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityGroupMember(@RequestBody FacilityGroupMember facilityGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityGroupMemberToBeUpdated.setnull(null);

		UpdateFacilityGroupMember command = new UpdateFacilityGroupMember(facilityGroupMemberToBeUpdated);

		try {
			if(((FacilityGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityGroupMemberId}")
	public ResponseEntity<FacilityGroupMember> findById(@PathVariable String facilityGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupMemberId", facilityGroupMemberId);
		try {

			List<FacilityGroupMember> foundFacilityGroupMember = findFacilityGroupMembersBy(requestParams).getBody();
			if(foundFacilityGroupMember.size()==1){				return successful(foundFacilityGroupMember.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityGroupMemberId}")
	public ResponseEntity<String> deleteFacilityGroupMemberByIdUpdated(@PathVariable String facilityGroupMemberId) throws Exception {
		DeleteFacilityGroupMember command = new DeleteFacilityGroupMember(facilityGroupMemberId);

		try {
			if (((FacilityGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
