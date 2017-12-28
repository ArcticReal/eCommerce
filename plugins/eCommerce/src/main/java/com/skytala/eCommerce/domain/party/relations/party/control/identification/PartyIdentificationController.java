package com.skytala.eCommerce.domain.party.relations.party.control.identification;

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
import com.skytala.eCommerce.domain.party.relations.party.command.identification.AddPartyIdentification;
import com.skytala.eCommerce.domain.party.relations.party.command.identification.DeletePartyIdentification;
import com.skytala.eCommerce.domain.party.relations.party.command.identification.UpdatePartyIdentification;
import com.skytala.eCommerce.domain.party.relations.party.event.identification.PartyIdentificationAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.identification.PartyIdentificationDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.identification.PartyIdentificationFound;
import com.skytala.eCommerce.domain.party.relations.party.event.identification.PartyIdentificationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.identification.PartyIdentificationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.identification.PartyIdentification;
import com.skytala.eCommerce.domain.party.relations.party.query.identification.FindPartyIdentificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyIdentifications")
public class PartyIdentificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyIdentificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyIdentification
	 * @return a List with the PartyIdentifications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyIdentification>> findPartyIdentificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyIdentificationsBy query = new FindPartyIdentificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyIdentification> partyIdentifications =((PartyIdentificationFound) Scheduler.execute(query).data()).getPartyIdentifications();

		return ResponseEntity.ok().body(partyIdentifications);

	}

	/**
	 * creates a new PartyIdentification entry in the ofbiz database
	 * 
	 * @param partyIdentificationToBeAdded
	 *            the PartyIdentification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyIdentification> createPartyIdentification(@RequestBody PartyIdentification partyIdentificationToBeAdded) throws Exception {

		AddPartyIdentification command = new AddPartyIdentification(partyIdentificationToBeAdded);
		PartyIdentification partyIdentification = ((PartyIdentificationAdded) Scheduler.execute(command).data()).getAddedPartyIdentification();
		
		if (partyIdentification != null) 
			return successful(partyIdentification);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyIdentification with the specific Id
	 * 
	 * @param partyIdentificationToBeUpdated
	 *            the PartyIdentification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyIdentification(@RequestBody PartyIdentification partyIdentificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyIdentificationToBeUpdated.setnull(null);

		UpdatePartyIdentification command = new UpdatePartyIdentification(partyIdentificationToBeUpdated);

		try {
			if(((PartyIdentificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyIdentificationId}")
	public ResponseEntity<PartyIdentification> findById(@PathVariable String partyIdentificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyIdentificationId", partyIdentificationId);
		try {

			List<PartyIdentification> foundPartyIdentification = findPartyIdentificationsBy(requestParams).getBody();
			if(foundPartyIdentification.size()==1){				return successful(foundPartyIdentification.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyIdentificationId}")
	public ResponseEntity<String> deletePartyIdentificationByIdUpdated(@PathVariable String partyIdentificationId) throws Exception {
		DeletePartyIdentification command = new DeletePartyIdentification(partyIdentificationId);

		try {
			if (((PartyIdentificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
