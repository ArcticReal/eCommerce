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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/trackingCodes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrackingCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodesBy query = new FindTrackingCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCode> trackingCodes =((TrackingCodeFound) Scheduler.execute(query).data()).getTrackingCodes();

		if (trackingCodes.size() == 1) {
			return ResponseEntity.ok().body(trackingCodes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTrackingCode(HttpServletRequest request) throws Exception {

		TrackingCode trackingCodeToBeAdded = new TrackingCode();
		try {
			trackingCodeToBeAdded = TrackingCodeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createTrackingCode(@RequestBody TrackingCode trackingCodeToBeAdded) throws Exception {

		AddTrackingCode command = new AddTrackingCode(trackingCodeToBeAdded);
		TrackingCode trackingCode = ((TrackingCodeAdded) Scheduler.execute(command).data()).getAddedTrackingCode();
		
		if (trackingCode != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trackingCode);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrackingCode could not be created.");
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
	public boolean updateTrackingCode(HttpServletRequest request) throws Exception {

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

		TrackingCode trackingCodeToBeUpdated = new TrackingCode();

		try {
			trackingCodeToBeUpdated = TrackingCodeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrackingCode(trackingCodeToBeUpdated, trackingCodeToBeUpdated.getTrackingCodeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTrackingCode(@RequestBody TrackingCode trackingCodeToBeUpdated,
			@PathVariable String trackingCodeId) throws Exception {

		trackingCodeToBeUpdated.setTrackingCodeId(trackingCodeId);

		UpdateTrackingCode command = new UpdateTrackingCode(trackingCodeToBeUpdated);

		try {
			if(((TrackingCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trackingCodeId}")
	public ResponseEntity<Object> findById(@PathVariable String trackingCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeId", trackingCodeId);
		try {

			Object foundTrackingCode = findTrackingCodesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrackingCode);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trackingCodeId}")
	public ResponseEntity<Object> deleteTrackingCodeByIdUpdated(@PathVariable String trackingCodeId) throws Exception {
		DeleteTrackingCode command = new DeleteTrackingCode(trackingCodeId);

		try {
			if (((TrackingCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrackingCode could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/trackingCode/\" plus one of the following: "
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
