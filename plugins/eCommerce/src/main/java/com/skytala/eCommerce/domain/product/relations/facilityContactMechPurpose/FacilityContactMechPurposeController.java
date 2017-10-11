package com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose;

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
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.command.AddFacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.command.DeleteFacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.command.UpdateFacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event.FacilityContactMechPurposeAdded;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event.FacilityContactMechPurposeDeleted;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event.FacilityContactMechPurposeFound;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event.FacilityContactMechPurposeUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.mapper.FacilityContactMechPurposeMapper;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.model.FacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.query.FindFacilityContactMechPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityContactMechPurposes")
public class FacilityContactMechPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityContactMechPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityContactMechPurpose
	 * @return a List with the FacilityContactMechPurposes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityContactMechPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityContactMechPurposesBy query = new FindFacilityContactMechPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityContactMechPurpose> facilityContactMechPurposes =((FacilityContactMechPurposeFound) Scheduler.execute(query).data()).getFacilityContactMechPurposes();

		if (facilityContactMechPurposes.size() == 1) {
			return ResponseEntity.ok().body(facilityContactMechPurposes.get(0));
		}

		return ResponseEntity.ok().body(facilityContactMechPurposes);

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
	public ResponseEntity<Object> createFacilityContactMechPurpose(HttpServletRequest request) throws Exception {

		FacilityContactMechPurpose facilityContactMechPurposeToBeAdded = new FacilityContactMechPurpose();
		try {
			facilityContactMechPurposeToBeAdded = FacilityContactMechPurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityContactMechPurpose(facilityContactMechPurposeToBeAdded);

	}

	/**
	 * creates a new FacilityContactMechPurpose entry in the ofbiz database
	 * 
	 * @param facilityContactMechPurposeToBeAdded
	 *            the FacilityContactMechPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityContactMechPurpose(@RequestBody FacilityContactMechPurpose facilityContactMechPurposeToBeAdded) throws Exception {

		AddFacilityContactMechPurpose command = new AddFacilityContactMechPurpose(facilityContactMechPurposeToBeAdded);
		FacilityContactMechPurpose facilityContactMechPurpose = ((FacilityContactMechPurposeAdded) Scheduler.execute(command).data()).getAddedFacilityContactMechPurpose();
		
		if (facilityContactMechPurpose != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityContactMechPurpose);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityContactMechPurpose could not be created.");
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
	public boolean updateFacilityContactMechPurpose(HttpServletRequest request) throws Exception {

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

		FacilityContactMechPurpose facilityContactMechPurposeToBeUpdated = new FacilityContactMechPurpose();

		try {
			facilityContactMechPurposeToBeUpdated = FacilityContactMechPurposeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityContactMechPurpose(facilityContactMechPurposeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityContactMechPurpose with the specific Id
	 * 
	 * @param facilityContactMechPurposeToBeUpdated
	 *            the FacilityContactMechPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityContactMechPurpose(@RequestBody FacilityContactMechPurpose facilityContactMechPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityContactMechPurposeToBeUpdated.setnull(null);

		UpdateFacilityContactMechPurpose command = new UpdateFacilityContactMechPurpose(facilityContactMechPurposeToBeUpdated);

		try {
			if(((FacilityContactMechPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityContactMechPurposeId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityContactMechPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityContactMechPurposeId", facilityContactMechPurposeId);
		try {

			Object foundFacilityContactMechPurpose = findFacilityContactMechPurposesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityContactMechPurpose);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityContactMechPurposeId}")
	public ResponseEntity<Object> deleteFacilityContactMechPurposeByIdUpdated(@PathVariable String facilityContactMechPurposeId) throws Exception {
		DeleteFacilityContactMechPurpose command = new DeleteFacilityContactMechPurpose(facilityContactMechPurposeId);

		try {
			if (((FacilityContactMechPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityContactMechPurpose could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityContactMechPurpose/\" plus one of the following: "
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
