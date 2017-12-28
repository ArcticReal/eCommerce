package com.skytala.eCommerce.domain.party.relations.agreement.control.itemType;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemType.AddAgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemType.DeleteAgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemType.UpdateAgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemType.AgreementItemTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemType.AgreementItemTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemType.AgreementItemTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemType.AgreementItemTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemType.AgreementItemTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemType.AgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreement.query.itemType.FindAgreementItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementItemTypes")
public class AgreementItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementItemType
	 * @return a List with the AgreementItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementItemType>> findAgreementItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemTypesBy query = new FindAgreementItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItemType> agreementItemTypes =((AgreementItemTypeFound) Scheduler.execute(query).data()).getAgreementItemTypes();

		return ResponseEntity.ok().body(agreementItemTypes);

	}

	/**
	 * creates a new AgreementItemType entry in the ofbiz database
	 * 
	 * @param agreementItemTypeToBeAdded
	 *            the AgreementItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementItemType> createAgreementItemType(@RequestBody AgreementItemType agreementItemTypeToBeAdded) throws Exception {

		AddAgreementItemType command = new AddAgreementItemType(agreementItemTypeToBeAdded);
		AgreementItemType agreementItemType = ((AgreementItemTypeAdded) Scheduler.execute(command).data()).getAddedAgreementItemType();
		
		if (agreementItemType != null) 
			return successful(agreementItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementItemType with the specific Id
	 * 
	 * @param agreementItemTypeToBeUpdated
	 *            the AgreementItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementItemType(@RequestBody AgreementItemType agreementItemTypeToBeUpdated,
			@PathVariable String agreementItemTypeId) throws Exception {

		agreementItemTypeToBeUpdated.setAgreementItemTypeId(agreementItemTypeId);

		UpdateAgreementItemType command = new UpdateAgreementItemType(agreementItemTypeToBeUpdated);

		try {
			if(((AgreementItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementItemTypeId}")
	public ResponseEntity<AgreementItemType> findById(@PathVariable String agreementItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemTypeId", agreementItemTypeId);
		try {

			List<AgreementItemType> foundAgreementItemType = findAgreementItemTypesBy(requestParams).getBody();
			if(foundAgreementItemType.size()==1){				return successful(foundAgreementItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementItemTypeId}")
	public ResponseEntity<String> deleteAgreementItemTypeByIdUpdated(@PathVariable String agreementItemTypeId) throws Exception {
		DeleteAgreementItemType command = new DeleteAgreementItemType(agreementItemTypeId);

		try {
			if (((AgreementItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
