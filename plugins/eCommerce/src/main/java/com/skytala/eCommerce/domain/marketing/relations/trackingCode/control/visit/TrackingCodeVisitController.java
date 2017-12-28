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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<TrackingCodeVisit>> findTrackingCodeVisitsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeVisitsBy query = new FindTrackingCodeVisitsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeVisit> trackingCodeVisits =((TrackingCodeVisitFound) Scheduler.execute(query).data()).getTrackingCodeVisits();

		return ResponseEntity.ok().body(trackingCodeVisits);

	}

	/**
	 * creates a new TrackingCodeVisit entry in the ofbiz database
	 * 
	 * @param trackingCodeVisitToBeAdded
	 *            the TrackingCodeVisit thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrackingCodeVisit> createTrackingCodeVisit(@RequestBody TrackingCodeVisit trackingCodeVisitToBeAdded) throws Exception {

		AddTrackingCodeVisit command = new AddTrackingCodeVisit(trackingCodeVisitToBeAdded);
		TrackingCodeVisit trackingCodeVisit = ((TrackingCodeVisitAdded) Scheduler.execute(command).data()).getAddedTrackingCodeVisit();
		
		if (trackingCodeVisit != null) 
			return successful(trackingCodeVisit);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTrackingCodeVisit(@RequestBody TrackingCodeVisit trackingCodeVisitToBeUpdated,
			@PathVariable String visitId) throws Exception {

		trackingCodeVisitToBeUpdated.setVisitId(visitId);

		UpdateTrackingCodeVisit command = new UpdateTrackingCodeVisit(trackingCodeVisitToBeUpdated);

		try {
			if(((TrackingCodeVisitUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trackingCodeVisitId}")
	public ResponseEntity<TrackingCodeVisit> findById(@PathVariable String trackingCodeVisitId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeVisitId", trackingCodeVisitId);
		try {

			List<TrackingCodeVisit> foundTrackingCodeVisit = findTrackingCodeVisitsBy(requestParams).getBody();
			if(foundTrackingCodeVisit.size()==1){				return successful(foundTrackingCodeVisit.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trackingCodeVisitId}")
	public ResponseEntity<String> deleteTrackingCodeVisitByIdUpdated(@PathVariable String trackingCodeVisitId) throws Exception {
		DeleteTrackingCodeVisit command = new DeleteTrackingCodeVisit(trackingCodeVisitId);

		try {
			if (((TrackingCodeVisitDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
