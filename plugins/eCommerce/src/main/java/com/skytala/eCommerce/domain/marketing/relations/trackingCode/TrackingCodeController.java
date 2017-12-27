package com.skytala.eCommerce.domain.marketing.relations.trackingCode;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.AddTrackingCode;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.DeleteTrackingCode;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.UpdateTrackingCode;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.TrackingCodeAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.TrackingCodeDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.TrackingCodeFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.TrackingCodeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.TrackingCodeMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.TrackingCode;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.FindTrackingCodesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/trackingCodes")
public class TrackingCodeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrackingCodeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrackingCode
	 * @return a List with the TrackingCodes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TrackingCode>> findTrackingCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodesBy query = new FindTrackingCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCode> trackingCodes =((TrackingCodeFound) Scheduler.execute(query).data()).getTrackingCodes();

		return ResponseEntity.ok().body(trackingCodes);

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
	public ResponseEntity<TrackingCode> createTrackingCode(HttpServletRequest request) throws Exception {

		TrackingCode trackingCodeToBeAdded = new TrackingCode();
		try {
			trackingCodeToBeAdded = TrackingCodeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createTrackingCode(trackingCodeToBeAdded);

	}

	/**
	 * creates a new TrackingCode entry in the ofbiz database
	 * 
	 * @param trackingCodeToBeAdded
	 *            the TrackingCode thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrackingCode> createTrackingCode(@RequestBody TrackingCode trackingCodeToBeAdded) throws Exception {

		AddTrackingCode command = new AddTrackingCode(trackingCodeToBeAdded);
		TrackingCode trackingCode = ((TrackingCodeAdded) Scheduler.execute(command).data()).getAddedTrackingCode();
		
		if (trackingCode != null) 
			return successful(trackingCode);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TrackingCode with the specific Id
	 * 
	 * @param trackingCodeToBeUpdated
	 *            the TrackingCode thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{trackingCodeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTrackingCode(@RequestBody TrackingCode trackingCodeToBeUpdated,
			@PathVariable String trackingCodeId) throws Exception {

		trackingCodeToBeUpdated.setTrackingCodeId(trackingCodeId);

		UpdateTrackingCode command = new UpdateTrackingCode(trackingCodeToBeUpdated);

		try {
			if(((TrackingCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trackingCodeId}")
	public ResponseEntity<TrackingCode> findById(@PathVariable String trackingCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeId", trackingCodeId);
		try {

			List<TrackingCode> foundTrackingCode = findTrackingCodesBy(requestParams).getBody();
			if(foundTrackingCode.size()==1){				return successful(foundTrackingCode.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trackingCodeId}")
	public ResponseEntity<String> deleteTrackingCodeByIdUpdated(@PathVariable String trackingCodeId) throws Exception {
		DeleteTrackingCode command = new DeleteTrackingCode(trackingCodeId);

		try {
			if (((TrackingCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
