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
	public ResponseEntity<Object> findValidContactMechRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindValidContactMechRolesBy query = new FindValidContactMechRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ValidContactMechRole> validContactMechRoles =((ValidContactMechRoleFound) Scheduler.execute(query).data()).getValidContactMechRoles();

		if (validContactMechRoles.size() == 1) {
			return ResponseEntity.ok().body(validContactMechRoles.get(0));
		}

		return ResponseEntity.ok().body(validContactMechRoles);

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
	public ResponseEntity<Object> createValidContactMechRole(HttpServletRequest request) throws Exception {

		ValidContactMechRole validContactMechRoleToBeAdded = new ValidContactMechRole();
		try {
			validContactMechRoleToBeAdded = ValidContactMechRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createValidContactMechRole(validContactMechRoleToBeAdded);

	}

	/**
	 * creates a new ValidContactMechRole entry in the ofbiz database
	 * 
	 * @param validContactMechRoleToBeAdded
	 *            the ValidContactMechRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createValidContactMechRole(@RequestBody ValidContactMechRole validContactMechRoleToBeAdded) throws Exception {

		AddValidContactMechRole command = new AddValidContactMechRole(validContactMechRoleToBeAdded);
		ValidContactMechRole validContactMechRole = ((ValidContactMechRoleAdded) Scheduler.execute(command).data()).getAddedValidContactMechRole();
		
		if (validContactMechRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(validContactMechRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ValidContactMechRole could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateValidContactMechRole(HttpServletRequest request) throws Exception {

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

		ValidContactMechRole validContactMechRoleToBeUpdated = new ValidContactMechRole();

		try {
			validContactMechRoleToBeUpdated = ValidContactMechRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateValidContactMechRole(validContactMechRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateValidContactMechRole(@RequestBody ValidContactMechRole validContactMechRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		validContactMechRoleToBeUpdated.setnull(null);

		UpdateValidContactMechRole command = new UpdateValidContactMechRole(validContactMechRoleToBeUpdated);

		try {
			if(((ValidContactMechRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{validContactMechRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String validContactMechRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("validContactMechRoleId", validContactMechRoleId);
		try {

			Object foundValidContactMechRole = findValidContactMechRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundValidContactMechRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{validContactMechRoleId}")
	public ResponseEntity<Object> deleteValidContactMechRoleByIdUpdated(@PathVariable String validContactMechRoleId) throws Exception {
		DeleteValidContactMechRole command = new DeleteValidContactMechRole(validContactMechRoleId);

		try {
			if (((ValidContactMechRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ValidContactMechRole could not be deleted");

	}

}
