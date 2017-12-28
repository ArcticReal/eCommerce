package com.skytala.eCommerce.domain.party.relations.party.control.type;

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
import com.skytala.eCommerce.domain.party.relations.party.command.type.AddPartyType;
import com.skytala.eCommerce.domain.party.relations.party.command.type.DeletePartyType;
import com.skytala.eCommerce.domain.party.relations.party.command.type.UpdatePartyType;
import com.skytala.eCommerce.domain.party.relations.party.event.type.PartyTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.type.PartyTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.type.PartyTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.type.PartyTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.type.PartyTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.type.PartyType;
import com.skytala.eCommerce.domain.party.relations.party.query.type.FindPartyTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyTypes")
public class PartyTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyType
	 * @return a List with the PartyTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyType>> findPartyTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyTypesBy query = new FindPartyTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyType> partyTypes =((PartyTypeFound) Scheduler.execute(query).data()).getPartyTypes();

		return ResponseEntity.ok().body(partyTypes);

	}

	/**
	 * creates a new PartyType entry in the ofbiz database
	 * 
	 * @param partyTypeToBeAdded
	 *            the PartyType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyType> createPartyType(@RequestBody PartyType partyTypeToBeAdded) throws Exception {

		AddPartyType command = new AddPartyType(partyTypeToBeAdded);
		PartyType partyType = ((PartyTypeAdded) Scheduler.execute(command).data()).getAddedPartyType();
		
		if (partyType != null) 
			return successful(partyType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyType with the specific Id
	 * 
	 * @param partyTypeToBeUpdated
	 *            the PartyType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyType(@RequestBody PartyType partyTypeToBeUpdated,
			@PathVariable String partyTypeId) throws Exception {

		partyTypeToBeUpdated.setPartyTypeId(partyTypeId);

		UpdatePartyType command = new UpdatePartyType(partyTypeToBeUpdated);

		try {
			if(((PartyTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyTypeId}")
	public ResponseEntity<PartyType> findById(@PathVariable String partyTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyTypeId", partyTypeId);
		try {

			List<PartyType> foundPartyType = findPartyTypesBy(requestParams).getBody();
			if(foundPartyType.size()==1){				return successful(foundPartyType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyTypeId}")
	public ResponseEntity<String> deletePartyTypeByIdUpdated(@PathVariable String partyTypeId) throws Exception {
		DeletePartyType command = new DeletePartyType(partyTypeId);

		try {
			if (((PartyTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
