package com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn;

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
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.command.AddTrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.command.DeleteTrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.command.UpdateTrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event.TrackingCodeOrderReturnAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event.TrackingCodeOrderReturnDeleted;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event.TrackingCodeOrderReturnFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.event.TrackingCodeOrderReturnUpdated;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.mapper.TrackingCodeOrderReturnMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.model.TrackingCodeOrderReturn;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeOrderReturn.query.FindTrackingCodeOrderReturnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/trackingCodeOrderReturns")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrackingCodeOrderReturnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeOrderReturnsBy query = new FindTrackingCodeOrderReturnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeOrderReturn> trackingCodeOrderReturns =((TrackingCodeOrderReturnFound) Scheduler.execute(query).data()).getTrackingCodeOrderReturns();

		if (trackingCodeOrderReturns.size() == 1) {
			return ResponseEntity.ok().body(trackingCodeOrderReturns.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTrackingCodeOrderReturn(HttpServletRequest request) throws Exception {

		TrackingCodeOrderReturn trackingCodeOrderReturnToBeAdded = new TrackingCodeOrderReturn();
		try {
			trackingCodeOrderReturnToBeAdded = TrackingCodeOrderReturnMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createTrackingCodeOrderReturn(@RequestBody TrackingCodeOrderReturn trackingCodeOrderReturnToBeAdded) throws Exception {

		AddTrackingCodeOrderReturn command = new AddTrackingCodeOrderReturn(trackingCodeOrderReturnToBeAdded);
		TrackingCodeOrderReturn trackingCodeOrderReturn = ((TrackingCodeOrderReturnAdded) Scheduler.execute(command).data()).getAddedTrackingCodeOrderReturn();
		
		if (trackingCodeOrderReturn != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trackingCodeOrderReturn);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrackingCodeOrderReturn could not be created.");
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
	public boolean updateTrackingCodeOrderReturn(HttpServletRequest request) throws Exception {

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

		TrackingCodeOrderReturn trackingCodeOrderReturnToBeUpdated = new TrackingCodeOrderReturn();

		try {
			trackingCodeOrderReturnToBeUpdated = TrackingCodeOrderReturnMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrackingCodeOrderReturn(trackingCodeOrderReturnToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTrackingCodeOrderReturn(@RequestBody TrackingCodeOrderReturn trackingCodeOrderReturnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		trackingCodeOrderReturnToBeUpdated.setnull(null);

		UpdateTrackingCodeOrderReturn command = new UpdateTrackingCodeOrderReturn(trackingCodeOrderReturnToBeUpdated);

		try {
			if(((TrackingCodeOrderReturnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trackingCodeOrderReturnId}")
	public ResponseEntity<Object> findById(@PathVariable String trackingCodeOrderReturnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeOrderReturnId", trackingCodeOrderReturnId);
		try {

			Object foundTrackingCodeOrderReturn = findTrackingCodeOrderReturnsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrackingCodeOrderReturn);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trackingCodeOrderReturnId}")
	public ResponseEntity<Object> deleteTrackingCodeOrderReturnByIdUpdated(@PathVariable String trackingCodeOrderReturnId) throws Exception {
		DeleteTrackingCodeOrderReturn command = new DeleteTrackingCodeOrderReturn(trackingCodeOrderReturnId);

		try {
			if (((TrackingCodeOrderReturnDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrackingCodeOrderReturn could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/trackingCodeOrderReturn/\" plus one of the following: "
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
