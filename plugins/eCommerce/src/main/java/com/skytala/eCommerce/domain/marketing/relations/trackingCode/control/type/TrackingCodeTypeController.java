package com.skytala.eCommerce.domain.marketing.relations.trackingCode.control.type;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.type.AddTrackingCodeType;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.type.DeleteTrackingCodeType;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.type.UpdateTrackingCodeType;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type.TrackingCodeTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type.TrackingCodeTypeDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type.TrackingCodeTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type.TrackingCodeTypeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.type.TrackingCodeTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type.TrackingCodeType;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.type.FindTrackingCodeTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/trackingCode/trackingCodeTypes")
public class TrackingCodeTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrackingCodeTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrackingCodeType
	 * @return a List with the TrackingCodeTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TrackingCodeType>> findTrackingCodeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeTypesBy query = new FindTrackingCodeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeType> trackingCodeTypes =((TrackingCodeTypeFound) Scheduler.execute(query).data()).getTrackingCodeTypes();

		return ResponseEntity.ok().body(trackingCodeTypes);

	}

	/**
	 * creates a new TrackingCodeType entry in the ofbiz database
	 * 
	 * @param trackingCodeTypeToBeAdded
	 *            the TrackingCodeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrackingCodeType> createTrackingCodeType(@RequestBody TrackingCodeType trackingCodeTypeToBeAdded) throws Exception {

		AddTrackingCodeType command = new AddTrackingCodeType(trackingCodeTypeToBeAdded);
		TrackingCodeType trackingCodeType = ((TrackingCodeTypeAdded) Scheduler.execute(command).data()).getAddedTrackingCodeType();
		
		if (trackingCodeType != null) 
			return successful(trackingCodeType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TrackingCodeType with the specific Id
	 * 
	 * @param trackingCodeTypeToBeUpdated
	 *            the TrackingCodeType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{trackingCodeTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTrackingCodeType(@RequestBody TrackingCodeType trackingCodeTypeToBeUpdated,
			@PathVariable String trackingCodeTypeId) throws Exception {

		trackingCodeTypeToBeUpdated.setTrackingCodeTypeId(trackingCodeTypeId);

		UpdateTrackingCodeType command = new UpdateTrackingCodeType(trackingCodeTypeToBeUpdated);

		try {
			if(((TrackingCodeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trackingCodeTypeId}")
	public ResponseEntity<TrackingCodeType> findById(@PathVariable String trackingCodeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeTypeId", trackingCodeTypeId);
		try {

			List<TrackingCodeType> foundTrackingCodeType = findTrackingCodeTypesBy(requestParams).getBody();
			if(foundTrackingCodeType.size()==1){				return successful(foundTrackingCodeType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trackingCodeTypeId}")
	public ResponseEntity<String> deleteTrackingCodeTypeByIdUpdated(@PathVariable String trackingCodeTypeId) throws Exception {
		DeleteTrackingCodeType command = new DeleteTrackingCodeType(trackingCodeTypeId);

		try {
			if (((TrackingCodeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
