package com.skytala.eCommerce.domain.product.relations.facility.control.groupRole;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRole.AddFacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRole.DeleteFacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRole.UpdateFacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRole.FacilityGroupRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupRole.FacilityGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupRole.FacilityGroupRole;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupRole.FindFacilityGroupRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityGroupRoles")
public class FacilityGroupRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupRole
	 * @return a List with the FacilityGroupRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityGroupRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupRolesBy query = new FindFacilityGroupRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupRole> facilityGroupRoles =((FacilityGroupRoleFound) Scheduler.execute(query).data()).getFacilityGroupRoles();

		if (facilityGroupRoles.size() == 1) {
			return ResponseEntity.ok().body(facilityGroupRoles.get(0));
		}

		return ResponseEntity.ok().body(facilityGroupRoles);

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
	public ResponseEntity<Object> createFacilityGroupRole(HttpServletRequest request) throws Exception {

		FacilityGroupRole facilityGroupRoleToBeAdded = new FacilityGroupRole();
		try {
			facilityGroupRoleToBeAdded = FacilityGroupRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityGroupRole(facilityGroupRoleToBeAdded);

	}

	/**
	 * creates a new FacilityGroupRole entry in the ofbiz database
	 * 
	 * @param facilityGroupRoleToBeAdded
	 *            the FacilityGroupRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityGroupRole(@RequestBody FacilityGroupRole facilityGroupRoleToBeAdded) throws Exception {

		AddFacilityGroupRole command = new AddFacilityGroupRole(facilityGroupRoleToBeAdded);
		FacilityGroupRole facilityGroupRole = ((FacilityGroupRoleAdded) Scheduler.execute(command).data()).getAddedFacilityGroupRole();
		
		if (facilityGroupRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityGroupRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityGroupRole could not be created.");
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
	public boolean updateFacilityGroupRole(HttpServletRequest request) throws Exception {

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

		FacilityGroupRole facilityGroupRoleToBeUpdated = new FacilityGroupRole();

		try {
			facilityGroupRoleToBeUpdated = FacilityGroupRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityGroupRole(facilityGroupRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityGroupRole with the specific Id
	 * 
	 * @param facilityGroupRoleToBeUpdated
	 *            the FacilityGroupRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityGroupRole(@RequestBody FacilityGroupRole facilityGroupRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityGroupRoleToBeUpdated.setnull(null);

		UpdateFacilityGroupRole command = new UpdateFacilityGroupRole(facilityGroupRoleToBeUpdated);

		try {
			if(((FacilityGroupRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityGroupRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityGroupRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupRoleId", facilityGroupRoleId);
		try {

			Object foundFacilityGroupRole = findFacilityGroupRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityGroupRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityGroupRoleId}")
	public ResponseEntity<Object> deleteFacilityGroupRoleByIdUpdated(@PathVariable String facilityGroupRoleId) throws Exception {
		DeleteFacilityGroupRole command = new DeleteFacilityGroupRole(facilityGroupRoleId);

		try {
			if (((FacilityGroupRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityGroupRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityGroupRole/\" plus one of the following: "
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
