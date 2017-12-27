package com.skytala.eCommerce.domain.party.relations.agreement.control.type;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.type.AddAgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.type.DeleteAgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.type.UpdateAgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.type.AgreementTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.type.AgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.query.type.FindAgreementTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementTypes")
public class AgreementTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementType
	 * @return a List with the AgreementTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementType>> findAgreementTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTypesBy query = new FindAgreementTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementType> agreementTypes =((AgreementTypeFound) Scheduler.execute(query).data()).getAgreementTypes();

		return ResponseEntity.ok().body(agreementTypes);

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
	public ResponseEntity<AgreementType> createAgreementType(HttpServletRequest request) throws Exception {

		AgreementType agreementTypeToBeAdded = new AgreementType();
		try {
			agreementTypeToBeAdded = AgreementTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAgreementType(agreementTypeToBeAdded);

	}

	/**
	 * creates a new AgreementType entry in the ofbiz database
	 * 
	 * @param agreementTypeToBeAdded
	 *            the AgreementType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementType> createAgreementType(@RequestBody AgreementType agreementTypeToBeAdded) throws Exception {

		AddAgreementType command = new AddAgreementType(agreementTypeToBeAdded);
		AgreementType agreementType = ((AgreementTypeAdded) Scheduler.execute(command).data()).getAddedAgreementType();
		
		if (agreementType != null) 
			return successful(agreementType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementType with the specific Id
	 * 
	 * @param agreementTypeToBeUpdated
	 *            the AgreementType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementType(@RequestBody AgreementType agreementTypeToBeUpdated,
			@PathVariable String agreementTypeId) throws Exception {

		agreementTypeToBeUpdated.setAgreementTypeId(agreementTypeId);

		UpdateAgreementType command = new UpdateAgreementType(agreementTypeToBeUpdated);

		try {
			if(((AgreementTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementTypeId}")
	public ResponseEntity<AgreementType> findById(@PathVariable String agreementTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTypeId", agreementTypeId);
		try {

			List<AgreementType> foundAgreementType = findAgreementTypesBy(requestParams).getBody();
			if(foundAgreementType.size()==1){				return successful(foundAgreementType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementTypeId}")
	public ResponseEntity<String> deleteAgreementTypeByIdUpdated(@PathVariable String agreementTypeId) throws Exception {
		DeleteAgreementType command = new DeleteAgreementType(agreementTypeId);

		try {
			if (((AgreementTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
