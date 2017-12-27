package com.skytala.eCommerce.domain.product.relations.facility.control.location;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.location.AddFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.command.location.DeleteFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.command.location.UpdateFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.event.location.FacilityLocationAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.location.FacilityLocationDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.location.FacilityLocationFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.location.FacilityLocationUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.location.FacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.location.FacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.query.location.FindFacilityLocationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityLocations")
public class FacilityLocationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityLocationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityLocation
	 * @return a List with the FacilityLocations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityLocation>> findFacilityLocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityLocationsBy query = new FindFacilityLocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityLocation> facilityLocations =((FacilityLocationFound) Scheduler.execute(query).data()).getFacilityLocations();

		return ResponseEntity.ok().body(facilityLocations);

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
	public ResponseEntity<FacilityLocation> createFacilityLocation(HttpServletRequest request) throws Exception {

		FacilityLocation facilityLocationToBeAdded = new FacilityLocation();
		try {
			facilityLocationToBeAdded = FacilityLocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFacilityLocation(facilityLocationToBeAdded);

	}

	/**
	 * creates a new FacilityLocation entry in the ofbiz database
	 * 
	 * @param facilityLocationToBeAdded
	 *            the FacilityLocation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityLocation> createFacilityLocation(@RequestBody FacilityLocation facilityLocationToBeAdded) throws Exception {

		AddFacilityLocation command = new AddFacilityLocation(facilityLocationToBeAdded);
		FacilityLocation facilityLocation = ((FacilityLocationAdded) Scheduler.execute(command).data()).getAddedFacilityLocation();
		
		if (facilityLocation != null) 
			return successful(facilityLocation);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityLocation with the specific Id
	 * 
	 * @param facilityLocationToBeUpdated
	 *            the FacilityLocation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{locationSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityLocation(@RequestBody FacilityLocation facilityLocationToBeUpdated,
			@PathVariable String locationSeqId) throws Exception {

		facilityLocationToBeUpdated.setLocationSeqId(locationSeqId);

		UpdateFacilityLocation command = new UpdateFacilityLocation(facilityLocationToBeUpdated);

		try {
			if(((FacilityLocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityLocationId}")
	public ResponseEntity<FacilityLocation> findById(@PathVariable String facilityLocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityLocationId", facilityLocationId);
		try {

			List<FacilityLocation> foundFacilityLocation = findFacilityLocationsBy(requestParams).getBody();
			if(foundFacilityLocation.size()==1){				return successful(foundFacilityLocation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityLocationId}")
	public ResponseEntity<String> deleteFacilityLocationByIdUpdated(@PathVariable String facilityLocationId) throws Exception {
		DeleteFacilityLocation command = new DeleteFacilityLocation(facilityLocationId);

		try {
			if (((FacilityLocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
