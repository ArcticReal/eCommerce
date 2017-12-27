package com.skytala.eCommerce.domain.party.relations.communicationEvent.control.role;

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
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.role.AddCommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.role.DeleteCommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.role.UpdateCommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role.CommunicationEventRoleAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role.CommunicationEventRoleDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role.CommunicationEventRoleFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role.CommunicationEventRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.role.CommunicationEventRoleMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.role.CommunicationEventRole;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.query.role.FindCommunicationEventRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/communicationEvent/communicationEventRoles")
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
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventRole>> findCommunicationEventRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventRolesBy query = new FindCommunicationEventRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventRole> communicationEventRoles =((CommunicationEventRoleFound) Scheduler.execute(query).data()).getCommunicationEventRoles();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<CommunicationEventRole> createCommunicationEventRole(HttpServletRequest request) throws Exception {

		CommunicationEventRole communicationEventRoleToBeAdded = new CommunicationEventRole();
		try {
			communicationEventRoleToBeAdded = CommunicationEventRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<CommunicationEventRole> createCommunicationEventRole(@RequestBody CommunicationEventRole communicationEventRoleToBeAdded) throws Exception {

		AddCommunicationEventRole command = new AddCommunicationEventRole(communicationEventRoleToBeAdded);
		CommunicationEventRole communicationEventRole = ((CommunicationEventRoleAdded) Scheduler.execute(command).data()).getAddedCommunicationEventRole();
		
		if (communicationEventRole != null) 
			return successful(communicationEventRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommunicationEventRole with the specific Id
	 * 
	 * @param communicationEventRoleToBeUpdated
	 *            the CommunicationEventRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommunicationEventRole(@RequestBody CommunicationEventRole communicationEventRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		communicationEventRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateCommunicationEventRole command = new UpdateCommunicationEventRole(communicationEventRoleToBeUpdated);

		try {
			if(((CommunicationEventRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventRoleId}")
	public ResponseEntity<CommunicationEventRole> findById(@PathVariable String communicationEventRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventRoleId", communicationEventRoleId);
		try {

			List<CommunicationEventRole> foundCommunicationEventRole = findCommunicationEventRolesBy(requestParams).getBody();
			if(foundCommunicationEventRole.size()==1){				return successful(foundCommunicationEventRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventRoleId}")
	public ResponseEntity<String> deleteCommunicationEventRoleByIdUpdated(@PathVariable String communicationEventRoleId) throws Exception {
		DeleteCommunicationEventRole command = new DeleteCommunicationEventRole(communicationEventRoleId);

		try {
			if (((CommunicationEventRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
