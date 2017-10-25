package com.skytala.eCommerce.domain.product.relations.facility.control.party;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.party.AddFacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.command.party.DeleteFacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.command.party.UpdateFacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.party.FacilityPartyMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.party.FacilityParty;
import com.skytala.eCommerce.domain.product.relations.facility.query.party.FindFacilityPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityPartys")
public class FacilityPartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityPartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityParty
	 * @return a List with the FacilityPartys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityPartysBy query = new FindFacilityPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityParty> facilityPartys =((FacilityPartyFound) Scheduler.execute(query).data()).getFacilityPartys();

		if (facilityPartys.size() == 1) {
			return ResponseEntity.ok().body(facilityPartys.get(0));
		}

		return ResponseEntity.ok().body(facilityPartys);

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
	public ResponseEntity<Object> createFacilityParty(HttpServletRequest request) throws Exception {

		FacilityParty facilityPartyToBeAdded = new FacilityParty();
		try {
			facilityPartyToBeAdded = FacilityPartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityParty(facilityPartyToBeAdded);

	}

	/**
	 * creates a new FacilityParty entry in the ofbiz database
	 * 
	 * @param facilityPartyToBeAdded
	 *            the FacilityParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityParty(@RequestBody FacilityParty facilityPartyToBeAdded) throws Exception {

		AddFacilityParty command = new AddFacilityParty(facilityPartyToBeAdded);
		FacilityParty facilityParty = ((FacilityPartyAdded) Scheduler.execute(command).data()).getAddedFacilityParty();
		
		if (facilityParty != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityParty);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityParty could not be created.");
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
	public boolean updateFacilityParty(HttpServletRequest request) throws Exception {

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

		FacilityParty facilityPartyToBeUpdated = new FacilityParty();

		try {
			facilityPartyToBeUpdated = FacilityPartyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityParty(facilityPartyToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityParty with the specific Id
	 * 
	 * @param facilityPartyToBeUpdated
	 *            the FacilityParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityParty(@RequestBody FacilityParty facilityPartyToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityPartyToBeUpdated.setnull(null);

		UpdateFacilityParty command = new UpdateFacilityParty(facilityPartyToBeUpdated);

		try {
			if(((FacilityPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityPartyId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityPartyId", facilityPartyId);
		try {

			Object foundFacilityParty = findFacilityPartysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityParty);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityPartyId}")
	public ResponseEntity<Object> deleteFacilityPartyByIdUpdated(@PathVariable String facilityPartyId) throws Exception {
		DeleteFacilityParty command = new DeleteFacilityParty(facilityPartyId);

		try {
			if (((FacilityPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityParty could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityParty/\" plus one of the following: "
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
