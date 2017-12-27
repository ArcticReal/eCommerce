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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<AgreementRole>> findAgreementRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementRolesBy query = new FindAgreementRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementRole> agreementRoles =((AgreementRoleFound) Scheduler.execute(query).data()).getAgreementRoles();

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
	public ResponseEntity<AgreementRole> createAgreementRole(HttpServletRequest request) throws Exception {

		AgreementRole agreementRoleToBeAdded = new AgreementRole();
		try {
			agreementRoleToBeAdded = AgreementRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<AgreementRole> createAgreementRole(@RequestBody AgreementRole agreementRoleToBeAdded) throws Exception {

		AddAgreementRole command = new AddAgreementRole(agreementRoleToBeAdded);
		AgreementRole agreementRole = ((AgreementRoleAdded) Scheduler.execute(command).data()).getAddedAgreementRole();
		
		if (agreementRole != null) 
			return successful(agreementRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementRole(@RequestBody AgreementRole agreementRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		agreementRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateAgreementRole command = new UpdateAgreementRole(agreementRoleToBeUpdated);

		try {
			if(((AgreementRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementRoleId}")
	public ResponseEntity<AgreementRole> findById(@PathVariable String agreementRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementRoleId", agreementRoleId);
		try {

			List<AgreementRole> foundAgreementRole = findAgreementRolesBy(requestParams).getBody();
			if(foundAgreementRole.size()==1){				return successful(foundAgreementRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementRoleId}")
	public ResponseEntity<String> deleteAgreementRoleByIdUpdated(@PathVariable String agreementRoleId) throws Exception {
		DeleteAgreementRole command = new DeleteAgreementRole(agreementRoleId);

		try {
			if (((AgreementRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
