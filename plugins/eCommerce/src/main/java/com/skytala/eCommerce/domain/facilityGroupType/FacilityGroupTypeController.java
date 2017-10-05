package com.skytala.eCommerce.domain.facilityGroupType;

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
import com.skytala.eCommerce.domain.facilityGroupType.command.AddFacilityGroupType;
import com.skytala.eCommerce.domain.facilityGroupType.command.DeleteFacilityGroupType;
import com.skytala.eCommerce.domain.facilityGroupType.command.UpdateFacilityGroupType;
import com.skytala.eCommerce.domain.facilityGroupType.event.FacilityGroupTypeAdded;
import com.skytala.eCommerce.domain.facilityGroupType.event.FacilityGroupTypeDeleted;
import com.skytala.eCommerce.domain.facilityGroupType.event.FacilityGroupTypeFound;
import com.skytala.eCommerce.domain.facilityGroupType.event.FacilityGroupTypeUpdated;
import com.skytala.eCommerce.domain.facilityGroupType.mapper.FacilityGroupTypeMapper;
import com.skytala.eCommerce.domain.facilityGroupType.model.FacilityGroupType;
import com.skytala.eCommerce.domain.facilityGroupType.query.FindFacilityGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityGroupTypes")
public class FacilityGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupType
	 * @return a List with the FacilityGroupTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupTypesBy query = new FindFacilityGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupType> facilityGroupTypes =((FacilityGroupTypeFound) Scheduler.execute(query).data()).getFacilityGroupTypes();

		if (facilityGroupTypes.size() == 1) {
			return ResponseEntity.ok().body(facilityGroupTypes.get(0));
		}

		return ResponseEntity.ok().body(facilityGroupTypes);

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
	public ResponseEntity<Object> createFacilityGroupType(HttpServletRequest request) throws Exception {

		FacilityGroupType facilityGroupTypeToBeAdded = new FacilityGroupType();
		try {
			facilityGroupTypeToBeAdded = FacilityGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityGroupType(facilityGroupTypeToBeAdded);

	}

	/**
	 * creates a new FacilityGroupType entry in the ofbiz database
	 * 
	 * @param facilityGroupTypeToBeAdded
	 *            the FacilityGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityGroupType(@RequestBody FacilityGroupType facilityGroupTypeToBeAdded) throws Exception {

		AddFacilityGroupType command = new AddFacilityGroupType(facilityGroupTypeToBeAdded);
		FacilityGroupType facilityGroupType = ((FacilityGroupTypeAdded) Scheduler.execute(command).data()).getAddedFacilityGroupType();
		
		if (facilityGroupType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityGroupType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityGroupType could not be created.");
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
	public boolean updateFacilityGroupType(HttpServletRequest request) throws Exception {

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

		FacilityGroupType facilityGroupTypeToBeUpdated = new FacilityGroupType();

		try {
			facilityGroupTypeToBeUpdated = FacilityGroupTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityGroupType(facilityGroupTypeToBeUpdated, facilityGroupTypeToBeUpdated.getFacilityGroupTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityGroupType with the specific Id
	 * 
	 * @param facilityGroupTypeToBeUpdated
	 *            the FacilityGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{facilityGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityGroupType(@RequestBody FacilityGroupType facilityGroupTypeToBeUpdated,
			@PathVariable String facilityGroupTypeId) throws Exception {

		facilityGroupTypeToBeUpdated.setFacilityGroupTypeId(facilityGroupTypeId);

		UpdateFacilityGroupType command = new UpdateFacilityGroupType(facilityGroupTypeToBeUpdated);

		try {
			if(((FacilityGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityGroupTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupTypeId", facilityGroupTypeId);
		try {

			Object foundFacilityGroupType = findFacilityGroupTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityGroupType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityGroupTypeId}")
	public ResponseEntity<Object> deleteFacilityGroupTypeByIdUpdated(@PathVariable String facilityGroupTypeId) throws Exception {
		DeleteFacilityGroupType command = new DeleteFacilityGroupType(facilityGroupTypeId);

		try {
			if (((FacilityGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityGroupType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityGroupType/\" plus one of the following: "
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
