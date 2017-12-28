package com.skytala.eCommerce.domain.party.relations.party.control.identificationType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.identificationType.AddPartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.identificationType.DeletePartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.identificationType.UpdatePartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.identificationType.PartyIdentificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.identificationType.PartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.query.identificationType.FindPartyIdentificationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyIdentificationTypes")
public class PartyIdentificationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyIdentificationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyIdentificationType
	 * @return a List with the PartyIdentificationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyIdentificationType>> findPartyIdentificationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyIdentificationTypesBy query = new FindPartyIdentificationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyIdentificationType> partyIdentificationTypes =((PartyIdentificationTypeFound) Scheduler.execute(query).data()).getPartyIdentificationTypes();

		return ResponseEntity.ok().body(partyIdentificationTypes);

	}

	/**
	 * creates a new PartyIdentificationType entry in the ofbiz database
	 * 
	 * @param partyIdentificationTypeToBeAdded
	 *            the PartyIdentificationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyIdentificationType> createPartyIdentificationType(@RequestBody PartyIdentificationType partyIdentificationTypeToBeAdded) throws Exception {

		AddPartyIdentificationType command = new AddPartyIdentificationType(partyIdentificationTypeToBeAdded);
		PartyIdentificationType partyIdentificationType = ((PartyIdentificationTypeAdded) Scheduler.execute(command).data()).getAddedPartyIdentificationType();
		
		if (partyIdentificationType != null) 
			return successful(partyIdentificationType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyIdentificationType with the specific Id
	 * 
	 * @param partyIdentificationTypeToBeUpdated
	 *            the PartyIdentificationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyIdentificationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyIdentificationType(@RequestBody PartyIdentificationType partyIdentificationTypeToBeUpdated,
			@PathVariable String partyIdentificationTypeId) throws Exception {

		partyIdentificationTypeToBeUpdated.setPartyIdentificationTypeId(partyIdentificationTypeId);

		UpdatePartyIdentificationType command = new UpdatePartyIdentificationType(partyIdentificationTypeToBeUpdated);

		try {
			if(((PartyIdentificationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyIdentificationTypeId}")
	public ResponseEntity<PartyIdentificationType> findById(@PathVariable String partyIdentificationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyIdentificationTypeId", partyIdentificationTypeId);
		try {

			List<PartyIdentificationType> foundPartyIdentificationType = findPartyIdentificationTypesBy(requestParams).getBody();
			if(foundPartyIdentificationType.size()==1){				return successful(foundPartyIdentificationType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyIdentificationTypeId}")
	public ResponseEntity<String> deletePartyIdentificationTypeByIdUpdated(@PathVariable String partyIdentificationTypeId) throws Exception {
		DeletePartyIdentificationType command = new DeletePartyIdentificationType(partyIdentificationTypeId);

		try {
			if (((PartyIdentificationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
