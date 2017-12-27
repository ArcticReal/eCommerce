package com.skytala.eCommerce.domain.party.relations.party.control.relationshipType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.relationshipType.AddPartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.command.relationshipType.DeletePartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.command.relationshipType.UpdatePartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.relationshipType.PartyRelationshipTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.relationshipType.PartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.query.relationshipType.FindPartyRelationshipTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyRelationshipTypes")
public class PartyRelationshipTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyRelationshipTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyRelationshipType
	 * @return a List with the PartyRelationshipTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyRelationshipType>> findPartyRelationshipTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRelationshipTypesBy query = new FindPartyRelationshipTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRelationshipType> partyRelationshipTypes =((PartyRelationshipTypeFound) Scheduler.execute(query).data()).getPartyRelationshipTypes();

		return ResponseEntity.ok().body(partyRelationshipTypes);

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
	public ResponseEntity<PartyRelationshipType> createPartyRelationshipType(HttpServletRequest request) throws Exception {

		PartyRelationshipType partyRelationshipTypeToBeAdded = new PartyRelationshipType();
		try {
			partyRelationshipTypeToBeAdded = PartyRelationshipTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyRelationshipType(partyRelationshipTypeToBeAdded);

	}

	/**
	 * creates a new PartyRelationshipType entry in the ofbiz database
	 * 
	 * @param partyRelationshipTypeToBeAdded
	 *            the PartyRelationshipType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyRelationshipType> createPartyRelationshipType(@RequestBody PartyRelationshipType partyRelationshipTypeToBeAdded) throws Exception {

		AddPartyRelationshipType command = new AddPartyRelationshipType(partyRelationshipTypeToBeAdded);
		PartyRelationshipType partyRelationshipType = ((PartyRelationshipTypeAdded) Scheduler.execute(command).data()).getAddedPartyRelationshipType();
		
		if (partyRelationshipType != null) 
			return successful(partyRelationshipType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyRelationshipType with the specific Id
	 * 
	 * @param partyRelationshipTypeToBeUpdated
	 *            the PartyRelationshipType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyRelationshipTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyRelationshipType(@RequestBody PartyRelationshipType partyRelationshipTypeToBeUpdated,
			@PathVariable String partyRelationshipTypeId) throws Exception {

		partyRelationshipTypeToBeUpdated.setPartyRelationshipTypeId(partyRelationshipTypeId);

		UpdatePartyRelationshipType command = new UpdatePartyRelationshipType(partyRelationshipTypeToBeUpdated);

		try {
			if(((PartyRelationshipTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyRelationshipTypeId}")
	public ResponseEntity<PartyRelationshipType> findById(@PathVariable String partyRelationshipTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRelationshipTypeId", partyRelationshipTypeId);
		try {

			List<PartyRelationshipType> foundPartyRelationshipType = findPartyRelationshipTypesBy(requestParams).getBody();
			if(foundPartyRelationshipType.size()==1){				return successful(foundPartyRelationshipType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyRelationshipTypeId}")
	public ResponseEntity<String> deletePartyRelationshipTypeByIdUpdated(@PathVariable String partyRelationshipTypeId) throws Exception {
		DeletePartyRelationshipType command = new DeletePartyRelationshipType(partyRelationshipTypeId);

		try {
			if (((PartyRelationshipTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
