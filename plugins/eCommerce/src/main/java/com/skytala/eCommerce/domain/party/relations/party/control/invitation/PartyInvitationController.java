package com.skytala.eCommerce.domain.party.relations.party.control.invitation;

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
import com.skytala.eCommerce.domain.party.relations.party.command.invitation.AddPartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.command.invitation.DeletePartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.command.invitation.UpdatePartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationFound;
import com.skytala.eCommerce.domain.party.relations.party.event.invitation.PartyInvitationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitation.PartyInvitationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitation.PartyInvitation;
import com.skytala.eCommerce.domain.party.relations.party.query.invitation.FindPartyInvitationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyInvitations")
public class PartyInvitationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyInvitationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyInvitation
	 * @return a List with the PartyInvitations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyInvitation>> findPartyInvitationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyInvitationsBy query = new FindPartyInvitationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyInvitation> partyInvitations =((PartyInvitationFound) Scheduler.execute(query).data()).getPartyInvitations();

		return ResponseEntity.ok().body(partyInvitations);

	}

	/**
	 * creates a new PartyInvitation entry in the ofbiz database
	 * 
	 * @param partyInvitationToBeAdded
	 *            the PartyInvitation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyInvitation> createPartyInvitation(@RequestBody PartyInvitation partyInvitationToBeAdded) throws Exception {

		AddPartyInvitation command = new AddPartyInvitation(partyInvitationToBeAdded);
		PartyInvitation partyInvitation = ((PartyInvitationAdded) Scheduler.execute(command).data()).getAddedPartyInvitation();
		
		if (partyInvitation != null) 
			return successful(partyInvitation);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyInvitation with the specific Id
	 * 
	 * @param partyInvitationToBeUpdated
	 *            the PartyInvitation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyInvitationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyInvitation(@RequestBody PartyInvitation partyInvitationToBeUpdated,
			@PathVariable String partyInvitationId) throws Exception {

		partyInvitationToBeUpdated.setPartyInvitationId(partyInvitationId);

		UpdatePartyInvitation command = new UpdatePartyInvitation(partyInvitationToBeUpdated);

		try {
			if(((PartyInvitationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyInvitationId}")
	public ResponseEntity<PartyInvitation> findById(@PathVariable String partyInvitationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyInvitationId", partyInvitationId);
		try {

			List<PartyInvitation> foundPartyInvitation = findPartyInvitationsBy(requestParams).getBody();
			if(foundPartyInvitation.size()==1){				return successful(foundPartyInvitation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyInvitationId}")
	public ResponseEntity<String> deletePartyInvitationByIdUpdated(@PathVariable String partyInvitationId) throws Exception {
		DeletePartyInvitation command = new DeletePartyInvitation(partyInvitationId);

		try {
			if (((PartyInvitationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
