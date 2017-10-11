package com.skytala.eCommerce.domain.product.relations.facilityContactMech;

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
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.command.AddFacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.command.DeleteFacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.command.UpdateFacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.event.FacilityContactMechAdded;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.event.FacilityContactMechDeleted;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.event.FacilityContactMechFound;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.event.FacilityContactMechUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.mapper.FacilityContactMechMapper;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.model.FacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.query.FindFacilityContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityContactMechs")
public class FacilityContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityContactMech
	 * @return a List with the FacilityContactMechs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityContactMechsBy query = new FindFacilityContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityContactMech> facilityContactMechs =((FacilityContactMechFound) Scheduler.execute(query).data()).getFacilityContactMechs();

		if (facilityContactMechs.size() == 1) {
			return ResponseEntity.ok().body(facilityContactMechs.get(0));
		}

		return ResponseEntity.ok().body(facilityContactMechs);

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
	public ResponseEntity<Object> createFacilityContactMech(HttpServletRequest request) throws Exception {

		FacilityContactMech facilityContactMechToBeAdded = new FacilityContactMech();
		try {
			facilityContactMechToBeAdded = FacilityContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityContactMech(facilityContactMechToBeAdded);

	}

	/**
	 * creates a new FacilityContactMech entry in the ofbiz database
	 * 
	 * @param facilityContactMechToBeAdded
	 *            the FacilityContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityContactMech(@RequestBody FacilityContactMech facilityContactMechToBeAdded) throws Exception {

		AddFacilityContactMech command = new AddFacilityContactMech(facilityContactMechToBeAdded);
		FacilityContactMech facilityContactMech = ((FacilityContactMechAdded) Scheduler.execute(command).data()).getAddedFacilityContactMech();
		
		if (facilityContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityContactMech could not be created.");
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
	public boolean updateFacilityContactMech(HttpServletRequest request) throws Exception {

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

		FacilityContactMech facilityContactMechToBeUpdated = new FacilityContactMech();

		try {
			facilityContactMechToBeUpdated = FacilityContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityContactMech(facilityContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityContactMech with the specific Id
	 * 
	 * @param facilityContactMechToBeUpdated
	 *            the FacilityContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityContactMech(@RequestBody FacilityContactMech facilityContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityContactMechToBeUpdated.setnull(null);

		UpdateFacilityContactMech command = new UpdateFacilityContactMech(facilityContactMechToBeUpdated);

		try {
			if(((FacilityContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityContactMechId", facilityContactMechId);
		try {

			Object foundFacilityContactMech = findFacilityContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityContactMechId}")
	public ResponseEntity<Object> deleteFacilityContactMechByIdUpdated(@PathVariable String facilityContactMechId) throws Exception {
		DeleteFacilityContactMech command = new DeleteFacilityContactMech(facilityContactMechId);

		try {
			if (((FacilityContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityContactMech could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityContactMech/\" plus one of the following: "
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
