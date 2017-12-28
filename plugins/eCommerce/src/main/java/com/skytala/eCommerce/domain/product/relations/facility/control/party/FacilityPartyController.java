package com.skytala.eCommerce.domain.product.relations.facility.control.party;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.party.AddFacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.command.party.DeleteFacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.command.party.UpdateFacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.party.FacilityPartyMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.party.FacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.query.party.FindFacilityPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityPartys")
public class FacilityPartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityPartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityParty
	 * @return a List with the FacilityPartys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityParty>> findFacilityPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityPartysBy query = new FindFacilityPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityParty> facilityPartys =((FacilityPartyFound) Scheduler.execute(query).data()).getFacilityPartys();

		return ResponseEntity.ok().body(facilityPartys);

	}

	/**
	 * creates a new FacilityParty entry in the ofbiz database
	 * 
	 * @param facilityPartyToBeAdded
	 *            the FacilityParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityParty> createFacilityParty(@RequestBody FacilityParty facilityPartyToBeAdded) throws Exception {

		AddFacilityParty command = new AddFacilityParty(facilityPartyToBeAdded);
		FacilityParty facilityParty = ((FacilityPartyAdded) Scheduler.execute(command).data()).getAddedFacilityParty();
		
		if (facilityParty != null) 
			return successful(facilityParty);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityParty with the specific Id
	 * 
	 * @param facilityPartyToBeUpdated
	 *            the FacilityParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityParty(@RequestBody FacilityParty facilityPartyToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityPartyToBeUpdated.setnull(null);

		UpdateFacilityParty command = new UpdateFacilityParty(facilityPartyToBeUpdated);

		try {
			if(((FacilityPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityPartyId}")
	public ResponseEntity<FacilityParty> findById(@PathVariable String facilityPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityPartyId", facilityPartyId);
		try {

			List<FacilityParty> foundFacilityParty = findFacilityPartysBy(requestParams).getBody();
			if(foundFacilityParty.size()==1){				return successful(foundFacilityParty.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityPartyId}")
	public ResponseEntity<String> deleteFacilityPartyByIdUpdated(@PathVariable String facilityPartyId) throws Exception {
		DeleteFacilityParty command = new DeleteFacilityParty(facilityPartyId);

		try {
			if (((FacilityPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
