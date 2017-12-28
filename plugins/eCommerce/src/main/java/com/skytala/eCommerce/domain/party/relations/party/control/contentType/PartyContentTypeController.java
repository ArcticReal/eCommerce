package com.skytala.eCommerce.domain.party.relations.party.control.contentType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.contentType.AddPartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.command.contentType.DeletePartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.command.contentType.UpdatePartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.contentType.PartyContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.query.contentType.FindPartyContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyContentTypes")
public class PartyContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContentType
	 * @return a List with the PartyContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyContentType>> findPartyContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContentTypesBy query = new FindPartyContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContentType> partyContentTypes =((PartyContentTypeFound) Scheduler.execute(query).data()).getPartyContentTypes();

		return ResponseEntity.ok().body(partyContentTypes);

	}

	/**
	 * creates a new PartyContentType entry in the ofbiz database
	 * 
	 * @param partyContentTypeToBeAdded
	 *            the PartyContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyContentType> createPartyContentType(@RequestBody PartyContentType partyContentTypeToBeAdded) throws Exception {

		AddPartyContentType command = new AddPartyContentType(partyContentTypeToBeAdded);
		PartyContentType partyContentType = ((PartyContentTypeAdded) Scheduler.execute(command).data()).getAddedPartyContentType();
		
		if (partyContentType != null) 
			return successful(partyContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyContentType with the specific Id
	 * 
	 * @param partyContentTypeToBeUpdated
	 *            the PartyContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyContentType(@RequestBody PartyContentType partyContentTypeToBeUpdated,
			@PathVariable String partyContentTypeId) throws Exception {

		partyContentTypeToBeUpdated.setPartyContentTypeId(partyContentTypeId);

		UpdatePartyContentType command = new UpdatePartyContentType(partyContentTypeToBeUpdated);

		try {
			if(((PartyContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyContentTypeId}")
	public ResponseEntity<PartyContentType> findById(@PathVariable String partyContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContentTypeId", partyContentTypeId);
		try {

			List<PartyContentType> foundPartyContentType = findPartyContentTypesBy(requestParams).getBody();
			if(foundPartyContentType.size()==1){				return successful(foundPartyContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyContentTypeId}")
	public ResponseEntity<String> deletePartyContentTypeByIdUpdated(@PathVariable String partyContentTypeId) throws Exception {
		DeletePartyContentType command = new DeletePartyContentType(partyContentTypeId);

		try {
			if (((PartyContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
