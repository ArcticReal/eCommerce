package com.skytala.eCommerce.domain.party.relations.agreement.control.role;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.role.AddAgreementRole;
import com.skytala.eCommerce.domain.party.relations.agreement.command.role.DeleteAgreementRole;
import com.skytala.eCommerce.domain.party.relations.agreement.command.role.UpdateAgreementRole;
import com.skytala.eCommerce.domain.party.relations.agreement.event.role.AgreementRoleAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.role.AgreementRoleDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.role.AgreementRoleFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.role.AgreementRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.role.AgreementRoleMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.role.AgreementRole;
import com.skytala.eCommerce.domain.party.relations.agreement.query.role.FindAgreementRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/agreement/agreementRoles")
public class AgreementRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementRole
	 * @return a List with the AgreementRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findAgreementRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementRolesBy query = new FindAgreementRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementRole> agreementRoles =((AgreementRoleFound) Scheduler.execute(query).data()).getAgreementRoles();

		if (agreementRoles.size() == 1) {
			return ResponseEntity.ok().body(agreementRoles.get(0));
		}

		return ResponseEntity.ok().body(agreementRoles);

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
	public ResponseEntity<Object> createAgreementRole(HttpServletRequest request) throws Exception {

		AgreementRole agreementRoleToBeAdded = new AgreementRole();
		try {
			agreementRoleToBeAdded = AgreementRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementRole(agreementRoleToBeAdded);

	}

	/**
	 * creates a new AgreementRole entry in the ofbiz database
	 * 
	 * @param agreementRoleToBeAdded
	 *            the AgreementRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementRole(@RequestBody AgreementRole agreementRoleToBeAdded) throws Exception {

		AddAgreementRole command = new AddAgreementRole(agreementRoleToBeAdded);
		AgreementRole agreementRole = ((AgreementRoleAdded) Scheduler.execute(command).data()).getAddedAgreementRole();
		
		if (agreementRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementRole could not be created.");
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
	public boolean updateAgreementRole(HttpServletRequest request) throws Exception {

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

		AgreementRole agreementRoleToBeUpdated = new AgreementRole();

		try {
			agreementRoleToBeUpdated = AgreementRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementRole(agreementRoleToBeUpdated, agreementRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementRole with the specific Id
	 * 
	 * @param agreementRoleToBeUpdated
	 *            the AgreementRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementRole(@RequestBody AgreementRole agreementRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		agreementRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateAgreementRole command = new UpdateAgreementRole(agreementRoleToBeUpdated);

		try {
			if(((AgreementRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{agreementRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementRoleId", agreementRoleId);
		try {

			Object foundAgreementRole = findAgreementRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{agreementRoleId}")
	public ResponseEntity<Object> deleteAgreementRoleByIdUpdated(@PathVariable String agreementRoleId) throws Exception {
		DeleteAgreementRole command = new DeleteAgreementRole(agreementRoleId);

		try {
			if (((AgreementRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementRole could not be deleted");

	}

}
