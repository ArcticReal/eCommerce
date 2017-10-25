package com.skytala.eCommerce.domain.accounting.relations.accommodationMap;

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
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.AddAccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.DeleteAccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.command.UpdateAccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapDeleted;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.AccommodationMapUpdated;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.mapper.AccommodationMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.AccommodationMap;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.query.FindAccommodationMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accommodationMaps")
public class AccommodationMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AccommodationMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AccommodationMap
	 * @return a List with the AccommodationMaps
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAccommodationMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAccommodationMapsBy query = new FindAccommodationMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AccommodationMap> accommodationMaps =((AccommodationMapFound) Scheduler.execute(query).data()).getAccommodationMaps();

		if (accommodationMaps.size() == 1) {
			return ResponseEntity.ok().body(accommodationMaps.get(0));
		}

		return ResponseEntity.ok().body(accommodationMaps);

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
	public ResponseEntity<Object> createAccommodationMap(HttpServletRequest request) throws Exception {

		AccommodationMap accommodationMapToBeAdded = new AccommodationMap();
		try {
			accommodationMapToBeAdded = AccommodationMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAccommodationMap(accommodationMapToBeAdded);

	}

	/**
	 * creates a new AccommodationMap entry in the ofbiz database
	 * 
	 * @param accommodationMapToBeAdded
	 *            the AccommodationMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAccommodationMap(@RequestBody AccommodationMap accommodationMapToBeAdded) throws Exception {

		AddAccommodationMap command = new AddAccommodationMap(accommodationMapToBeAdded);
		AccommodationMap accommodationMap = ((AccommodationMapAdded) Scheduler.execute(command).data()).getAddedAccommodationMap();
		
		if (accommodationMap != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(accommodationMap);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AccommodationMap could not be created.");
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
	public boolean updateAccommodationMap(HttpServletRequest request) throws Exception {

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

		AccommodationMap accommodationMapToBeUpdated = new AccommodationMap();

		try {
			accommodationMapToBeUpdated = AccommodationMapMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAccommodationMap(accommodationMapToBeUpdated, accommodationMapToBeUpdated.getAccommodationMapId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AccommodationMap with the specific Id
	 * 
	 * @param accommodationMapToBeUpdated
	 *            the AccommodationMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{accommodationMapId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAccommodationMap(@RequestBody AccommodationMap accommodationMapToBeUpdated,
			@PathVariable String accommodationMapId) throws Exception {

		accommodationMapToBeUpdated.setAccommodationMapId(accommodationMapId);

		UpdateAccommodationMap command = new UpdateAccommodationMap(accommodationMapToBeUpdated);

		try {
			if(((AccommodationMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{accommodationMapId}")
	public ResponseEntity<Object> findById(@PathVariable String accommodationMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("accommodationMapId", accommodationMapId);
		try {

			Object foundAccommodationMap = findAccommodationMapsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAccommodationMap);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{accommodationMapId}")
	public ResponseEntity<Object> deleteAccommodationMapByIdUpdated(@PathVariable String accommodationMapId) throws Exception {
		DeleteAccommodationMap command = new DeleteAccommodationMap(accommodationMapId);

		try {
			if (((AccommodationMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AccommodationMap could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/accommodationMap/\" plus one of the following: "
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
