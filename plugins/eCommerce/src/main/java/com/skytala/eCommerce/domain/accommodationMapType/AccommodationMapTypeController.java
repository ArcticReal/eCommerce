package com.skytala.eCommerce.domain.accommodationMapType;

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
import com.skytala.eCommerce.domain.accommodationMapType.command.AddAccommodationMapType;
import com.skytala.eCommerce.domain.accommodationMapType.command.DeleteAccommodationMapType;
import com.skytala.eCommerce.domain.accommodationMapType.command.UpdateAccommodationMapType;
import com.skytala.eCommerce.domain.accommodationMapType.event.AccommodationMapTypeAdded;
import com.skytala.eCommerce.domain.accommodationMapType.event.AccommodationMapTypeDeleted;
import com.skytala.eCommerce.domain.accommodationMapType.event.AccommodationMapTypeFound;
import com.skytala.eCommerce.domain.accommodationMapType.event.AccommodationMapTypeUpdated;
import com.skytala.eCommerce.domain.accommodationMapType.mapper.AccommodationMapTypeMapper;
import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
import com.skytala.eCommerce.domain.accommodationMapType.query.FindAccommodationMapTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accommodationMapTypes")
public class AccommodationMapTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationMapTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationMapType
	 * @return a List with the AccommodationMapTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAccommodationMapTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationMapTypesBy query = new FindAccommodationMapTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationMapType> accommodationMapTypes =((AccommodationMapTypeFound) Scheduler.execute(query).data()).getAccommodationMapTypes();

		if (accommodationMapTypes.size() == 1) {
			return ResponseEntity.ok().body(accommodationMapTypes.get(0));
		}

		return ResponseEntity.ok().body(accommodationMapTypes);

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
	public ResponseEntity<Object> createAccommodationMapType(HttpServletRequest request) throws Exception {

		AccommodationMapType accommodationMapTypeToBeAdded = new AccommodationMapType();
		try {
			accommodationMapTypeToBeAdded = AccommodationMapTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAccommodationMapType(accommodationMapTypeToBeAdded);

	}

	/**
	 * creates a new AccommodationMapType entry in the ofbiz database
	 * 
	 * @param accommodationMapTypeToBeAdded
	 *            the AccommodationMapType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAccommodationMapType(@RequestBody AccommodationMapType accommodationMapTypeToBeAdded) throws Exception {

		AddAccommodationMapType command = new AddAccommodationMapType(accommodationMapTypeToBeAdded);
		AccommodationMapType accommodationMapType = ((AccommodationMapTypeAdded) Scheduler.execute(command).data()).getAddedAccommodationMapType();
		
		if (accommodationMapType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(accommodationMapType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AccommodationMapType could not be created.");
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
	public boolean updateAccommodationMapType(HttpServletRequest request) throws Exception {

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

		AccommodationMapType accommodationMapTypeToBeUpdated = new AccommodationMapType();

		try {
			accommodationMapTypeToBeUpdated = AccommodationMapTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAccommodationMapType(accommodationMapTypeToBeUpdated, accommodationMapTypeToBeUpdated.getAccommodationMapTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AccommodationMapType with the specific Id
	 * 
	 * @param accommodationMapTypeToBeUpdated
	 *            the AccommodationMapType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationMapTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAccommodationMapType(@RequestBody AccommodationMapType accommodationMapTypeToBeUpdated,
			@PathVariable String accommodationMapTypeId) throws Exception {

		accommodationMapTypeToBeUpdated.setAccommodationMapTypeId(accommodationMapTypeId);

		UpdateAccommodationMapType command = new UpdateAccommodationMapType(accommodationMapTypeToBeUpdated);

		try {
			if(((AccommodationMapTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{accommodationMapTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String accommodationMapTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationMapTypeId", accommodationMapTypeId);
		try {

			Object foundAccommodationMapType = findAccommodationMapTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAccommodationMapType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{accommodationMapTypeId}")
	public ResponseEntity<Object> deleteAccommodationMapTypeByIdUpdated(@PathVariable String accommodationMapTypeId) throws Exception {
		DeleteAccommodationMapType command = new DeleteAccommodationMapType(accommodationMapTypeId);

		try {
			if (((AccommodationMapTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AccommodationMapType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/accommodationMapType/\" plus one of the following: "
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
