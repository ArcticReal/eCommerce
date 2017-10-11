package com.skytala.eCommerce.domain.party.relations.communicationEventRole;

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
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.command.AddCommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.command.DeleteCommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.command.UpdateCommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.event.CommunicationEventRoleAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.event.CommunicationEventRoleDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.event.CommunicationEventRoleFound;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.event.CommunicationEventRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.mapper.CommunicationEventRoleMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.model.CommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.query.FindCommunicationEventRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/communicationEventRoles")
public class CommunicationEventRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventRole
	 * @return a List with the CommunicationEventRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventRolesBy query = new FindCommunicationEventRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventRole> communicationEventRoles =((CommunicationEventRoleFound) Scheduler.execute(query).data()).getCommunicationEventRoles();

		if (communicationEventRoles.size() == 1) {
			return ResponseEntity.ok().body(communicationEventRoles.get(0));
		}

		return ResponseEntity.ok().body(communicationEventRoles);

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
	public ResponseEntity<Object> createCommunicationEventRole(HttpServletRequest request) throws Exception {

		CommunicationEventRole communicationEventRoleToBeAdded = new CommunicationEventRole();
		try {
			communicationEventRoleToBeAdded = CommunicationEventRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventRole(communicationEventRoleToBeAdded);

	}

	/**
	 * creates a new CommunicationEventRole entry in the ofbiz database
	 * 
	 * @param communicationEventRoleToBeAdded
	 *            the CommunicationEventRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventRole(@RequestBody CommunicationEventRole communicationEventRoleToBeAdded) throws Exception {

		AddCommunicationEventRole command = new AddCommunicationEventRole(communicationEventRoleToBeAdded);
		CommunicationEventRole communicationEventRole = ((CommunicationEventRoleAdded) Scheduler.execute(command).data()).getAddedCommunicationEventRole();
		
		if (communicationEventRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventRole could not be created.");
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
	public boolean updateCommunicationEventRole(HttpServletRequest request) throws Exception {

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

		CommunicationEventRole communicationEventRoleToBeUpdated = new CommunicationEventRole();

		try {
			communicationEventRoleToBeUpdated = CommunicationEventRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventRole(communicationEventRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommunicationEventRole with the specific Id
	 * 
	 * @param communicationEventRoleToBeUpdated
	 *            the CommunicationEventRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommunicationEventRole(@RequestBody CommunicationEventRole communicationEventRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventRoleToBeUpdated.setnull(null);

		UpdateCommunicationEventRole command = new UpdateCommunicationEventRole(communicationEventRoleToBeUpdated);

		try {
			if(((CommunicationEventRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventRoleId", communicationEventRoleId);
		try {

			Object foundCommunicationEventRole = findCommunicationEventRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventRoleId}")
	public ResponseEntity<Object> deleteCommunicationEventRoleByIdUpdated(@PathVariable String communicationEventRoleId) throws Exception {
		DeleteCommunicationEventRole command = new DeleteCommunicationEventRole(communicationEventRoleId);

		try {
			if (((CommunicationEventRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/communicationEventRole/\" plus one of the following: "
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
