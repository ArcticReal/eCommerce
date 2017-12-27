package com.skytala.eCommerce.domain.marketing.relations.trackingCode.control.orderReturn;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.orderReturn.AddTrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.orderReturn.DeleteTrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.command.orderReturn.UpdateTrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.orderReturn.TrackingCodeOrderReturnMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.orderReturn.TrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.orderReturn.FindTrackingCodeOrderReturnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/trackingCode/trackingCodeOrderReturns")
public class TrackingCodeOrderReturnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrackingCodeOrderReturnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrackingCodeOrderReturn
	 * @return a List with the TrackingCodeOrderReturns
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TrackingCodeOrderReturn>> findTrackingCodeOrderReturnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeOrderReturnsBy query = new FindTrackingCodeOrderReturnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeOrderReturn> trackingCodeOrderReturns =((TrackingCodeOrderReturnFound) Scheduler.execute(query).data()).getTrackingCodeOrderReturns();

		return ResponseEntity.ok().body(trackingCodeOrderReturns);

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
	public ResponseEntity<TrackingCodeOrderReturn> createTrackingCodeOrderReturn(HttpServletRequest request) throws Exception {

		TrackingCodeOrderReturn trackingCodeOrderReturnToBeAdded = new TrackingCodeOrderReturn();
		try {
			trackingCodeOrderReturnToBeAdded = TrackingCodeOrderReturnMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createTrackingCodeOrderReturn(trackingCodeOrderReturnToBeAdded);

	}

	/**
	 * creates a new TrackingCodeOrderReturn entry in the ofbiz database
	 * 
	 * @param trackingCodeOrderReturnToBeAdded
	 *            the TrackingCodeOrderReturn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrackingCodeOrderReturn> createTrackingCodeOrderReturn(@RequestBody TrackingCodeOrderReturn trackingCodeOrderReturnToBeAdded) throws Exception {

		AddTrackingCodeOrderReturn command = new AddTrackingCodeOrderReturn(trackingCodeOrderReturnToBeAdded);
		TrackingCodeOrderReturn trackingCodeOrderReturn = ((TrackingCodeOrderReturnAdded) Scheduler.execute(command).data()).getAddedTrackingCodeOrderReturn();
		
		if (trackingCodeOrderReturn != null) 
			return successful(trackingCodeOrderReturn);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TrackingCodeOrderReturn with the specific Id
	 * 
	 * @param trackingCodeOrderReturnToBeUpdated
	 *            the TrackingCodeOrderReturn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTrackingCodeOrderReturn(@RequestBody TrackingCodeOrderReturn trackingCodeOrderReturnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		trackingCodeOrderReturnToBeUpdated.setnull(null);

		UpdateTrackingCodeOrderReturn command = new UpdateTrackingCodeOrderReturn(trackingCodeOrderReturnToBeUpdated);

		try {
			if(((TrackingCodeOrderReturnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trackingCodeOrderReturnId}")
	public ResponseEntity<TrackingCodeOrderReturn> findById(@PathVariable String trackingCodeOrderReturnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeOrderReturnId", trackingCodeOrderReturnId);
		try {

			List<TrackingCodeOrderReturn> foundTrackingCodeOrderReturn = findTrackingCodeOrderReturnsBy(requestParams).getBody();
			if(foundTrackingCodeOrderReturn.size()==1){				return successful(foundTrackingCodeOrderReturn.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trackingCodeOrderReturnId}")
	public ResponseEntity<String> deleteTrackingCodeOrderReturnByIdUpdated(@PathVariable String trackingCodeOrderReturnId) throws Exception {
		DeleteTrackingCodeOrderReturn command = new DeleteTrackingCodeOrderReturn(trackingCodeOrderReturnId);

		try {
			if (((TrackingCodeOrderReturnDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
