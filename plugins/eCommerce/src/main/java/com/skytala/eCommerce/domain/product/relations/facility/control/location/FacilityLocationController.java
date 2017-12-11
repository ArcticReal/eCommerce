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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findFacilityLocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityLocationsBy query = new FindFacilityLocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityLocation> facilityLocations =((FacilityLocationFound) Scheduler.execute(query).data()).getFacilityLocations();

		if (facilityLocations.size() == 1) {
			return ResponseEntity.ok().body(facilityLocations.get(0));
		}

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
	public ResponseEntity<Object> createFacilityLocation(HttpServletRequest request) throws Exception {

		FacilityLocation facilityLocationToBeAdded = new FacilityLocation();
		try {
			facilityLocationToBeAdded = FacilityLocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createFacilityLocation(@RequestBody FacilityLocation facilityLocationToBeAdded) throws Exception {

		AddFacilityLocation command = new AddFacilityLocation(facilityLocationToBeAdded);
		FacilityLocation facilityLocation = ((FacilityLocationAdded) Scheduler.execute(command).data()).getAddedFacilityLocation();
		
		if (facilityLocation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityLocation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityLocation could not be created.");
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
	public boolean updateFacilityLocation(HttpServletRequest request) throws Exception {

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

		FacilityLocation facilityLocationToBeUpdated = new FacilityLocation();

		try {
			facilityLocationToBeUpdated = FacilityLocationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityLocation(facilityLocationToBeUpdated, facilityLocationToBeUpdated.getLocationSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFacilityLocation(@RequestBody FacilityLocation facilityLocationToBeUpdated,
			@PathVariable String locationSeqId) throws Exception {

		facilityLocationToBeUpdated.setLocationSeqId(locationSeqId);

		UpdateFacilityLocation command = new UpdateFacilityLocation(facilityLocationToBeUpdated);

		try {
			if(((FacilityLocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{facilityLocationId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityLocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityLocationId", facilityLocationId);
		try {

			Object foundFacilityLocation = findFacilityLocationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityLocation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{facilityLocationId}")
	public ResponseEntity<Object> deleteFacilityLocationByIdUpdated(@PathVariable String facilityLocationId) throws Exception {
		DeleteFacilityLocation command = new DeleteFacilityLocation(facilityLocationId);

		try {
			if (((FacilityLocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityLocation could not be deleted");

	}

}
