package com.skytala.eCommerce.domain.party.relations.party.control.classificationType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.classificationType.AddPartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.classificationType.DeletePartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.classificationType.UpdatePartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationType.PartyClassificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classificationType.PartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.query.classificationType.FindPartyClassificationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyClassificationTypes")
public class PartyClassificationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyClassificationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyClassificationType
	 * @return a List with the PartyClassificationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyClassificationType>> findPartyClassificationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyClassificationTypesBy query = new FindPartyClassificationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyClassificationType> partyClassificationTypes =((PartyClassificationTypeFound) Scheduler.execute(query).data()).getPartyClassificationTypes();

		return ResponseEntity.ok().body(partyClassificationTypes);

	}

	/**
	 * creates a new PartyClassificationType entry in the ofbiz database
	 * 
	 * @param partyClassificationTypeToBeAdded
	 *            the PartyClassificationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyClassificationType> createPartyClassificationType(@RequestBody PartyClassificationType partyClassificationTypeToBeAdded) throws Exception {

		AddPartyClassificationType command = new AddPartyClassificationType(partyClassificationTypeToBeAdded);
		PartyClassificationType partyClassificationType = ((PartyClassificationTypeAdded) Scheduler.execute(command).data()).getAddedPartyClassificationType();
		
		if (partyClassificationType != null) 
			return successful(partyClassificationType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyClassificationType with the specific Id
	 * 
	 * @param partyClassificationTypeToBeUpdated
	 *            the PartyClassificationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyClassificationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyClassificationType(@RequestBody PartyClassificationType partyClassificationTypeToBeUpdated,
			@PathVariable String partyClassificationTypeId) throws Exception {

		partyClassificationTypeToBeUpdated.setPartyClassificationTypeId(partyClassificationTypeId);

		UpdatePartyClassificationType command = new UpdatePartyClassificationType(partyClassificationTypeToBeUpdated);

		try {
			if(((PartyClassificationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyClassificationTypeId}")
	public ResponseEntity<PartyClassificationType> findById(@PathVariable String partyClassificationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyClassificationTypeId", partyClassificationTypeId);
		try {

			List<PartyClassificationType> foundPartyClassificationType = findPartyClassificationTypesBy(requestParams).getBody();
			if(foundPartyClassificationType.size()==1){				return successful(foundPartyClassificationType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyClassificationTypeId}")
	public ResponseEntity<String> deletePartyClassificationTypeByIdUpdated(@PathVariable String partyClassificationTypeId) throws Exception {
		DeletePartyClassificationType command = new DeletePartyClassificationType(partyClassificationTypeId);

		try {
			if (((PartyClassificationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
