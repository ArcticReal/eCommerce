package com.skytala.eCommerce.domain.product.relations.facility.control.groupMember;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupMember.AddFacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupMember.DeleteFacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupMember.UpdateFacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupMember.FacilityGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupMember.FindFacilityGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityGroupMembers")
public class FacilityGroupMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupMember
	 * @return a List with the FacilityGroupMembers
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupMembersBy query = new FindFacilityGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupMember> facilityGroupMembers =((FacilityGroupMemberFound) Scheduler.execute(query).data()).getFacilityGroupMembers();

		if (facilityGroupMembers.size() == 1) {
			return ResponseEntity.ok().body(facilityGroupMembers.get(0));
		}

		return ResponseEntity.ok().body(facilityGroupMembers);

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
	public ResponseEntity<Object> createFacilityGroupMember(HttpServletRequest request) throws Exception {

		FacilityGroupMember facilityGroupMemberToBeAdded = new FacilityGroupMember();
		try {
			facilityGroupMemberToBeAdded = FacilityGroupMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityGroupMember(facilityGroupMemberToBeAdded);

	}

	/**
	 * creates a new FacilityGroupMember entry in the ofbiz database
	 * 
	 * @param facilityGroupMemberToBeAdded
	 *            the FacilityGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityGroupMember(@RequestBody FacilityGroupMember facilityGroupMemberToBeAdded) throws Exception {

		AddFacilityGroupMember command = new AddFacilityGroupMember(facilityGroupMemberToBeAdded);
		FacilityGroupMember facilityGroupMember = ((FacilityGroupMemberAdded) Scheduler.execute(command).data()).getAddedFacilityGroupMember();
		
		if (facilityGroupMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityGroupMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityGroupMember could not be created.");
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
	public boolean updateFacilityGroupMember(HttpServletRequest request) throws Exception {

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

		FacilityGroupMember facilityGroupMemberToBeUpdated = new FacilityGroupMember();

		try {
			facilityGroupMemberToBeUpdated = FacilityGroupMemberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityGroupMember(facilityGroupMemberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityGroupMember with the specific Id
	 * 
	 * @param facilityGroupMemberToBeUpdated
	 *            the FacilityGroupMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityGroupMember(@RequestBody FacilityGroupMember facilityGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityGroupMemberToBeUpdated.setnull(null);

		UpdateFacilityGroupMember command = new UpdateFacilityGroupMember(facilityGroupMemberToBeUpdated);

		try {
			if(((FacilityGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityGroupMemberId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupMemberId", facilityGroupMemberId);
		try {

			Object foundFacilityGroupMember = findFacilityGroupMembersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityGroupMember);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityGroupMemberId}")
	public ResponseEntity<Object> deleteFacilityGroupMemberByIdUpdated(@PathVariable String facilityGroupMemberId) throws Exception {
		DeleteFacilityGroupMember command = new DeleteFacilityGroupMember(facilityGroupMemberId);

		try {
			if (((FacilityGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityGroupMember could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityGroupMember/\" plus one of the following: "
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
