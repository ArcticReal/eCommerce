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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<PartyRole>> findPartyRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRolesBy query = new FindPartyRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRole> partyRoles =((PartyRoleFound) Scheduler.execute(query).data()).getPartyRoles();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PartyRole> createPartyRole(HttpServletRequest request) throws Exception {

		PartyRole partyRoleToBeAdded = new PartyRole();
		try {
			partyRoleToBeAdded = PartyRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PartyRole> createPartyRole(@RequestBody PartyRole partyRoleToBeAdded) throws Exception {

		AddPartyRole command = new AddPartyRole(partyRoleToBeAdded);
		PartyRole partyRole = ((PartyRoleAdded) Scheduler.execute(command).data()).getAddedPartyRole();
		
		if (partyRole != null) 
			return successful(partyRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyRole(@RequestBody PartyRole partyRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyRoleToBeUpdated.setnull(null);

		UpdatePartyRole command = new UpdatePartyRole(partyRoleToBeUpdated);

		try {
			if(((PartyRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyRoleId}")
	public ResponseEntity<PartyRole> findById(@PathVariable String partyRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRoleId", partyRoleId);
		try {

			List<PartyRole> foundPartyRole = findPartyRolesBy(requestParams).getBody();
			if(foundPartyRole.size()==1){				return successful(foundPartyRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyRoleId}")
	public ResponseEntity<String> deletePartyRoleByIdUpdated(@PathVariable String partyRoleId) throws Exception {
		DeletePartyRole command = new DeletePartyRole(partyRoleId);

		try {
			if (((PartyRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
