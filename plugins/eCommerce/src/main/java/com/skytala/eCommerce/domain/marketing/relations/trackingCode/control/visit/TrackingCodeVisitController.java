package com.skytala.eCommerce.domain.marketing.relations.trackingCode.control.visit;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.visit.AddTrackingCodeVisit;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.visit.DeleteTrackingCodeVisit;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.visit.UpdateTrackingCodeVisit;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit.TrackingCodeVisitAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit.TrackingCodeVisitDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit.TrackingCodeVisitFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.visit.TrackingCodeVisitUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.visit.TrackingCodeVisitMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit.TrackingCodeVisit;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.visit.FindTrackingCodeVisitsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketing/trackingCode/trackingCodeVisits")
public class TrackingCodeVisitController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrackingCodeVisitController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrackingCodeVisit
	 * @return a List with the TrackingCodeVisits
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrackingCodeVisitsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeVisitsBy query = new FindTrackingCodeVisitsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeVisit> trackingCodeVisits =((TrackingCodeVisitFound) Scheduler.execute(query).data()).getTrackingCodeVisits();

		if (trackingCodeVisits.size() == 1) {
			return ResponseEntity.ok().body(trackingCodeVisits.get(0));
		}

		return ResponseEntity.ok().body(trackingCodeVisits);

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
	public ResponseEntity<Object> createTrackingCodeVisit(HttpServletRequest request) throws Exception {

		TrackingCodeVisit trackingCodeVisitToBeAdded = new TrackingCodeVisit();
		try {
			trackingCodeVisitToBeAdded = TrackingCodeVisitMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTrackingCodeVisit(trackingCodeVisitToBeAdded);

	}

	/**
	 * creates a new TrackingCodeVisit entry in the ofbiz database
	 * 
	 * @param trackingCodeVisitToBeAdded
	 *            the TrackingCodeVisit thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTrackingCodeVisit(@RequestBody TrackingCodeVisit trackingCodeVisitToBeAdded) throws Exception {

		AddTrackingCodeVisit command = new AddTrackingCodeVisit(trackingCodeVisitToBeAdded);
		TrackingCodeVisit trackingCodeVisit = ((TrackingCodeVisitAdded) Scheduler.execute(command).data()).getAddedTrackingCodeVisit();
		
		if (trackingCodeVisit != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trackingCodeVisit);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrackingCodeVisit could not be created.");
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
	public boolean updateTrackingCodeVisit(HttpServletRequest request) throws Exception {

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

		TrackingCodeVisit trackingCodeVisitToBeUpdated = new TrackingCodeVisit();

		try {
			trackingCodeVisitToBeUpdated = TrackingCodeVisitMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrackingCodeVisit(trackingCodeVisitToBeUpdated, trackingCodeVisitToBeUpdated.getVisitId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TrackingCodeVisit with the specific Id
	 * 
	 * @param trackingCodeVisitToBeUpdated
	 *            the TrackingCodeVisit thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{visitId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTrackingCodeVisit(@RequestBody TrackingCodeVisit trackingCodeVisitToBeUpdated,
			@PathVariable String visitId) throws Exception {

		trackingCodeVisitToBeUpdated.setVisitId(visitId);

		UpdateTrackingCodeVisit command = new UpdateTrackingCodeVisit(trackingCodeVisitToBeUpdated);

		try {
			if(((TrackingCodeVisitUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trackingCodeVisitId}")
	public ResponseEntity<Object> findById(@PathVariable String trackingCodeVisitId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeVisitId", trackingCodeVisitId);
		try {

			Object foundTrackingCodeVisit = findTrackingCodeVisitsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrackingCodeVisit);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trackingCodeVisitId}")
	public ResponseEntity<Object> deleteTrackingCodeVisitByIdUpdated(@PathVariable String trackingCodeVisitId) throws Exception {
		DeleteTrackingCodeVisit command = new DeleteTrackingCodeVisit(trackingCodeVisitId);

		try {
			if (((TrackingCodeVisitDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrackingCodeVisit could not be deleted");

	}

}
