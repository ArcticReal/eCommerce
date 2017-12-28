package com.skytala.eCommerce.domain.party.relations.contactMech.control.validRole;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.validRole.AddValidContactMechRole;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.validRole.DeleteValidContactMechRole;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.validRole.UpdateValidContactMechRole;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole.ValidContactMechRoleAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole.ValidContactMechRoleDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole.ValidContactMechRoleFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole.ValidContactMechRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.validRole.ValidContactMechRoleMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.validRole.FindValidContactMechRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/validContactMechRoles")
public class ValidContactMechRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ValidContactMechRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ValidContactMechRole
	 * @return a List with the ValidContactMechRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ValidContactMechRole>> findValidContactMechRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindValidContactMechRolesBy query = new FindValidContactMechRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ValidContactMechRole> validContactMechRoles =((ValidContactMechRoleFound) Scheduler.execute(query).data()).getValidContactMechRoles();

		return ResponseEntity.ok().body(validContactMechRoles);

	}

	/**
	 * creates a new ValidContactMechRole entry in the ofbiz database
	 * 
	 * @param validContactMechRoleToBeAdded
	 *            the ValidContactMechRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ValidContactMechRole> createValidContactMechRole(@RequestBody ValidContactMechRole validContactMechRoleToBeAdded) throws Exception {

		AddValidContactMechRole command = new AddValidContactMechRole(validContactMechRoleToBeAdded);
		ValidContactMechRole validContactMechRole = ((ValidContactMechRoleAdded) Scheduler.execute(command).data()).getAddedValidContactMechRole();
		
		if (validContactMechRole != null) 
			return successful(validContactMechRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ValidContactMechRole with the specific Id
	 * 
	 * @param validContactMechRoleToBeUpdated
	 *            the ValidContactMechRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateValidContactMechRole(@RequestBody ValidContactMechRole validContactMechRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		validContactMechRoleToBeUpdated.setnull(null);

		UpdateValidContactMechRole command = new UpdateValidContactMechRole(validContactMechRoleToBeUpdated);

		try {
			if(((ValidContactMechRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{validContactMechRoleId}")
	public ResponseEntity<ValidContactMechRole> findById(@PathVariable String validContactMechRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("validContactMechRoleId", validContactMechRoleId);
		try {

			List<ValidContactMechRole> foundValidContactMechRole = findValidContactMechRolesBy(requestParams).getBody();
			if(foundValidContactMechRole.size()==1){				return successful(foundValidContactMechRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{validContactMechRoleId}")
	public ResponseEntity<String> deleteValidContactMechRoleByIdUpdated(@PathVariable String validContactMechRoleId) throws Exception {
		DeleteValidContactMechRole command = new DeleteValidContactMechRole(validContactMechRoleId);

		try {
			if (((ValidContactMechRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
