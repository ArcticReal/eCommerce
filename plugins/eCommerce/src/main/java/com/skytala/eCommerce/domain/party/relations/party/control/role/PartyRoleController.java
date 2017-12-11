package com.skytala.eCommerce.domain.party.relations.party.control.role;

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
import com.skytala.eCommerce.domain.party.relations.party.command.role.AddPartyRole;
import com.skytala.eCommerce.domain.party.relations.party.command.role.DeletePartyRole;
import com.skytala.eCommerce.domain.party.relations.party.command.role.UpdatePartyRole;
import com.skytala.eCommerce.domain.party.relations.party.event.role.PartyRoleAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.role.PartyRoleDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.role.PartyRoleFound;
import com.skytala.eCommerce.domain.party.relations.party.event.role.PartyRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.role.PartyRoleMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.role.PartyRole;
import com.skytala.eCommerce.domain.party.relations.party.query.role.FindPartyRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyRoles")
public class PartyRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyRole
	 * @return a List with the PartyRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRolesBy query = new FindPartyRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRole> partyRoles =((PartyRoleFound) Scheduler.execute(query).data()).getPartyRoles();

		if (partyRoles.size() == 1) {
			return ResponseEntity.ok().body(partyRoles.get(0));
		}

		return ResponseEntity.ok().body(partyRoles);

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
	public ResponseEntity<Object> createPartyRole(HttpServletRequest request) throws Exception {

		PartyRole partyRoleToBeAdded = new PartyRole();
		try {
			partyRoleToBeAdded = PartyRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyRole(partyRoleToBeAdded);

	}

	/**
	 * creates a new PartyRole entry in the ofbiz database
	 * 
	 * @param partyRoleToBeAdded
	 *            the PartyRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyRole(@RequestBody PartyRole partyRoleToBeAdded) throws Exception {

		AddPartyRole command = new AddPartyRole(partyRoleToBeAdded);
		PartyRole partyRole = ((PartyRoleAdded) Scheduler.execute(command).data()).getAddedPartyRole();
		
		if (partyRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyRole could not be created.");
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
	public boolean updatePartyRole(HttpServletRequest request) throws Exception {

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

		PartyRole partyRoleToBeUpdated = new PartyRole();

		try {
			partyRoleToBeUpdated = PartyRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyRole(partyRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyRole with the specific Id
	 * 
	 * @param partyRoleToBeUpdated
	 *            the PartyRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyRole(@RequestBody PartyRole partyRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyRoleToBeUpdated.setnull(null);

		UpdatePartyRole command = new UpdatePartyRole(partyRoleToBeUpdated);

		try {
			if(((PartyRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String partyRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRoleId", partyRoleId);
		try {

			Object foundPartyRole = findPartyRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyRoleId}")
	public ResponseEntity<Object> deletePartyRoleByIdUpdated(@PathVariable String partyRoleId) throws Exception {
		DeletePartyRole command = new DeletePartyRole(partyRoleId);

		try {
			if (((PartyRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyRole could not be deleted");

	}

}
