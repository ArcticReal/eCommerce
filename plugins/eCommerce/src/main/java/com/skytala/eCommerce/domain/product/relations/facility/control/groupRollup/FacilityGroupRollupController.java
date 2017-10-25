package com.skytala.eCommerce.domain.product.relations.facility.control.groupRollup;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRollup.AddFacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRollup.DeleteFacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRollup.UpdateFacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupRollup.FacilityGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupRollup.FacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupRollup.FindFacilityGroupRollupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityGroupRollups")
public class FacilityGroupRollupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupRollupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupRollup
	 * @return a List with the FacilityGroupRollups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityGroupRollupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupRollupsBy query = new FindFacilityGroupRollupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupRollup> facilityGroupRollups =((FacilityGroupRollupFound) Scheduler.execute(query).data()).getFacilityGroupRollups();

		if (facilityGroupRollups.size() == 1) {
			return ResponseEntity.ok().body(facilityGroupRollups.get(0));
		}

		return ResponseEntity.ok().body(facilityGroupRollups);

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
	public ResponseEntity<Object> createFacilityGroupRollup(HttpServletRequest request) throws Exception {

		FacilityGroupRollup facilityGroupRollupToBeAdded = new FacilityGroupRollup();
		try {
			facilityGroupRollupToBeAdded = FacilityGroupRollupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityGroupRollup(facilityGroupRollupToBeAdded);

	}

	/**
	 * creates a new FacilityGroupRollup entry in the ofbiz database
	 * 
	 * @param facilityGroupRollupToBeAdded
	 *            the FacilityGroupRollup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityGroupRollup(@RequestBody FacilityGroupRollup facilityGroupRollupToBeAdded) throws Exception {

		AddFacilityGroupRollup command = new AddFacilityGroupRollup(facilityGroupRollupToBeAdded);
		FacilityGroupRollup facilityGroupRollup = ((FacilityGroupRollupAdded) Scheduler.execute(command).data()).getAddedFacilityGroupRollup();
		
		if (facilityGroupRollup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityGroupRollup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityGroupRollup could not be created.");
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
	public boolean updateFacilityGroupRollup(HttpServletRequest request) throws Exception {

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

		FacilityGroupRollup facilityGroupRollupToBeUpdated = new FacilityGroupRollup();

		try {
			facilityGroupRollupToBeUpdated = FacilityGroupRollupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityGroupRollup(facilityGroupRollupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityGroupRollup with the specific Id
	 * 
	 * @param facilityGroupRollupToBeUpdated
	 *            the FacilityGroupRollup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityGroupRollup(@RequestBody FacilityGroupRollup facilityGroupRollupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityGroupRollupToBeUpdated.setnull(null);

		UpdateFacilityGroupRollup command = new UpdateFacilityGroupRollup(facilityGroupRollupToBeUpdated);

		try {
			if(((FacilityGroupRollupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityGroupRollupId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityGroupRollupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupRollupId", facilityGroupRollupId);
		try {

			Object foundFacilityGroupRollup = findFacilityGroupRollupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityGroupRollup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityGroupRollupId}")
	public ResponseEntity<Object> deleteFacilityGroupRollupByIdUpdated(@PathVariable String facilityGroupRollupId) throws Exception {
		DeleteFacilityGroupRollup command = new DeleteFacilityGroupRollup(facilityGroupRollupId);

		try {
			if (((FacilityGroupRollupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityGroupRollup could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityGroupRollup/\" plus one of the following: "
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
