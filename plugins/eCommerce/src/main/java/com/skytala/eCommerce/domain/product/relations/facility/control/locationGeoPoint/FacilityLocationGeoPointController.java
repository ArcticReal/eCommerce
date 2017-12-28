package com.skytala.eCommerce.domain.product.relations.facility.control.locationGeoPoint;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.locationGeoPoint.AddFacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facility.command.locationGeoPoint.DeleteFacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facility.command.locationGeoPoint.UpdateFacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint.FacilityLocationGeoPointAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint.FacilityLocationGeoPointDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint.FacilityLocationGeoPointFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint.FacilityLocationGeoPointUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.locationGeoPoint.FacilityLocationGeoPointMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint.FacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facility.query.locationGeoPoint.FindFacilityLocationGeoPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityLocationGeoPoints")
public class FacilityLocationGeoPointController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityLocationGeoPointController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityLocationGeoPoint
	 * @return a List with the FacilityLocationGeoPoints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityLocationGeoPoint>> findFacilityLocationGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityLocationGeoPointsBy query = new FindFacilityLocationGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityLocationGeoPoint> facilityLocationGeoPoints =((FacilityLocationGeoPointFound) Scheduler.execute(query).data()).getFacilityLocationGeoPoints();

		return ResponseEntity.ok().body(facilityLocationGeoPoints);

	}

	/**
	 * creates a new FacilityLocationGeoPoint entry in the ofbiz database
	 * 
	 * @param facilityLocationGeoPointToBeAdded
	 *            the FacilityLocationGeoPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityLocationGeoPoint> createFacilityLocationGeoPoint(@RequestBody FacilityLocationGeoPoint facilityLocationGeoPointToBeAdded) throws Exception {

		AddFacilityLocationGeoPoint command = new AddFacilityLocationGeoPoint(facilityLocationGeoPointToBeAdded);
		FacilityLocationGeoPoint facilityLocationGeoPoint = ((FacilityLocationGeoPointAdded) Scheduler.execute(command).data()).getAddedFacilityLocationGeoPoint();
		
		if (facilityLocationGeoPoint != null) 
			return successful(facilityLocationGeoPoint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityLocationGeoPoint with the specific Id
	 * 
	 * @param facilityLocationGeoPointToBeUpdated
	 *            the FacilityLocationGeoPoint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{locationSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityLocationGeoPoint(@RequestBody FacilityLocationGeoPoint facilityLocationGeoPointToBeUpdated,
			@PathVariable String locationSeqId) throws Exception {

		facilityLocationGeoPointToBeUpdated.setLocationSeqId(locationSeqId);

		UpdateFacilityLocationGeoPoint command = new UpdateFacilityLocationGeoPoint(facilityLocationGeoPointToBeUpdated);

		try {
			if(((FacilityLocationGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityLocationGeoPointId}")
	public ResponseEntity<FacilityLocationGeoPoint> findById(@PathVariable String facilityLocationGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityLocationGeoPointId", facilityLocationGeoPointId);
		try {

			List<FacilityLocationGeoPoint> foundFacilityLocationGeoPoint = findFacilityLocationGeoPointsBy(requestParams).getBody();
			if(foundFacilityLocationGeoPoint.size()==1){				return successful(foundFacilityLocationGeoPoint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityLocationGeoPointId}")
	public ResponseEntity<String> deleteFacilityLocationGeoPointByIdUpdated(@PathVariable String facilityLocationGeoPointId) throws Exception {
		DeleteFacilityLocationGeoPoint command = new DeleteFacilityLocationGeoPoint(facilityLocationGeoPointId);

		try {
			if (((FacilityLocationGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
