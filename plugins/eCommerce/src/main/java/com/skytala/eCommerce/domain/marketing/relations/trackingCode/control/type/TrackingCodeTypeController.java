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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/trackingCodeTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrackingCodeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrackingCodeTypesBy query = new FindTrackingCodeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrackingCodeType> trackingCodeTypes =((TrackingCodeTypeFound) Scheduler.execute(query).data()).getTrackingCodeTypes();

		if (trackingCodeTypes.size() == 1) {
			return ResponseEntity.ok().body(trackingCodeTypes.get(0));
		}

		return ResponseEntity.ok().body(trackingCodeTypes);

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
	public ResponseEntity<Object> createTrackingCodeType(HttpServletRequest request) throws Exception {

		TrackingCodeType trackingCodeTypeToBeAdded = new TrackingCodeType();
		try {
			trackingCodeTypeToBeAdded = TrackingCodeTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTrackingCodeType(trackingCodeTypeToBeAdded);

	}

	/**
	 * creates a new TrackingCodeType entry in the ofbiz database
	 * 
	 * @param trackingCodeTypeToBeAdded
	 *            the TrackingCodeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTrackingCodeType(@RequestBody TrackingCodeType trackingCodeTypeToBeAdded) throws Exception {

		AddTrackingCodeType command = new AddTrackingCodeType(trackingCodeTypeToBeAdded);
		TrackingCodeType trackingCodeType = ((TrackingCodeTypeAdded) Scheduler.execute(command).data()).getAddedTrackingCodeType();
		
		if (trackingCodeType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trackingCodeType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrackingCodeType could not be created.");
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
	public boolean updateTrackingCodeType(HttpServletRequest request) throws Exception {

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

		TrackingCodeType trackingCodeTypeToBeUpdated = new TrackingCodeType();

		try {
			trackingCodeTypeToBeUpdated = TrackingCodeTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrackingCodeType(trackingCodeTypeToBeUpdated, trackingCodeTypeToBeUpdated.getTrackingCodeTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTrackingCodeType(@RequestBody TrackingCodeType trackingCodeTypeToBeUpdated,
			@PathVariable String trackingCodeTypeId) throws Exception {

		trackingCodeTypeToBeUpdated.setTrackingCodeTypeId(trackingCodeTypeId);

		UpdateTrackingCodeType command = new UpdateTrackingCodeType(trackingCodeTypeToBeUpdated);

		try {
			if(((TrackingCodeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trackingCodeTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String trackingCodeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trackingCodeTypeId", trackingCodeTypeId);
		try {

			Object foundTrackingCodeType = findTrackingCodeTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrackingCodeType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trackingCodeTypeId}")
	public ResponseEntity<Object> deleteTrackingCodeTypeByIdUpdated(@PathVariable String trackingCodeTypeId) throws Exception {
		DeleteTrackingCodeType command = new DeleteTrackingCodeType(trackingCodeTypeId);

		try {
			if (((TrackingCodeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrackingCodeType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/trackingCodeType/\" plus one of the following: "
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
