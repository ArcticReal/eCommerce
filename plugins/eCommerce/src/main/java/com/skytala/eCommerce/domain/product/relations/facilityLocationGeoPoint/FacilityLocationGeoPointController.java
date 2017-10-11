package com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.command.AddFacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.command.DeleteFacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.command.UpdateFacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event.FacilityLocationGeoPointAdded;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event.FacilityLocationGeoPointDeleted;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event.FacilityLocationGeoPointFound;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event.FacilityLocationGeoPointUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.mapper.FacilityLocationGeoPointMapper;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.model.FacilityLocationGeoPoint;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.query.FindFacilityLocationGeoPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityLocationGeoPoints")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityLocationGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityLocationGeoPointsBy query = new FindFacilityLocationGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityLocationGeoPoint> facilityLocationGeoPoints =((FacilityLocationGeoPointFound) Scheduler.execute(query).data()).getFacilityLocationGeoPoints();

		if (facilityLocationGeoPoints.size() == 1) {
			return ResponseEntity.ok().body(facilityLocationGeoPoints.get(0));
		}

		return ResponseEntity.ok().body(facilityLocationGeoPoints);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createFacilityLocationGeoPoint(HttpServletRequest request) throws Exception {

		FacilityLocationGeoPoint facilityLocationGeoPointToBeAdded = new FacilityLocationGeoPoint();
		try {
			facilityLocationGeoPointToBeAdded = FacilityLocationGeoPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityLocationGeoPoint(facilityLocationGeoPointToBeAdded);

	}

	/**
	 * creates a new FacilityLocationGeoPoint entry in the ofbiz database
	 * 
	 * @param facilityLocationGeoPointToBeAdded
	 *            the FacilityLocationGeoPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityLocationGeoPoint(@RequestBody FacilityLocationGeoPoint facilityLocationGeoPointToBeAdded) throws Exception {

		AddFacilityLocationGeoPoint command = new AddFacilityLocationGeoPoint(facilityLocationGeoPointToBeAdded);
		FacilityLocationGeoPoint facilityLocationGeoPoint = ((FacilityLocationGeoPointAdded) Scheduler.execute(command).data()).getAddedFacilityLocationGeoPoint();
		
		if (facilityLocationGeoPoint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityLocationGeoPoint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityLocationGeoPoint could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateFacilityLocationGeoPoint(HttpServletRequest request) throws Exception {

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

		FacilityLocationGeoPoint facilityLocationGeoPointToBeUpdated = new FacilityLocationGeoPoint();

		try {
			facilityLocationGeoPointToBeUpdated = FacilityLocationGeoPointMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityLocationGeoPoint(facilityLocationGeoPointToBeUpdated, facilityLocationGeoPointToBeUpdated.getLocationSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFacilityLocationGeoPoint(@RequestBody FacilityLocationGeoPoint facilityLocationGeoPointToBeUpdated,
			@PathVariable String locationSeqId) throws Exception {

		facilityLocationGeoPointToBeUpdated.setLocationSeqId(locationSeqId);

		UpdateFacilityLocationGeoPoint command = new UpdateFacilityLocationGeoPoint(facilityLocationGeoPointToBeUpdated);

		try {
			if(((FacilityLocationGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityLocationGeoPointId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityLocationGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityLocationGeoPointId", facilityLocationGeoPointId);
		try {

			Object foundFacilityLocationGeoPoint = findFacilityLocationGeoPointsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityLocationGeoPoint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityLocationGeoPointId}")
	public ResponseEntity<Object> deleteFacilityLocationGeoPointByIdUpdated(@PathVariable String facilityLocationGeoPointId) throws Exception {
		DeleteFacilityLocationGeoPoint command = new DeleteFacilityLocationGeoPoint(facilityLocationGeoPointId);

		try {
			if (((FacilityLocationGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityLocationGeoPoint could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityLocationGeoPoint/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
