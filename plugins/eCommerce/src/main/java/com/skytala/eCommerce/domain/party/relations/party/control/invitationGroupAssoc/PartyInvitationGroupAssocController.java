package com.skytala.eCommerce.domain.party.relations.party.control.invitationGroupAssoc;

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
import com.skytala.eCommerce.domain.party.relations.party.command.invitationGroupAssoc.AddPartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.party.command.invitationGroupAssoc.DeletePartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.party.command.invitationGroupAssoc.UpdatePartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc.PartyInvitationGroupAssocAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc.PartyInvitationGroupAssocDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc.PartyInvitationGroupAssocFound;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc.PartyInvitationGroupAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationGroupAssoc.PartyInvitationGroupAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;
import com.skytala.eCommerce.domain.party.relations.party.query.invitationGroupAssoc.FindPartyInvitationGroupAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyInvitationGroupAssocs")
public class PartyInvitationGroupAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyInvitationGroupAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyInvitationGroupAssoc
	 * @return a List with the PartyInvitationGroupAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyInvitationGroupAssoc>> findPartyInvitationGroupAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyInvitationGroupAssocsBy query = new FindPartyInvitationGroupAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyInvitationGroupAssoc> partyInvitationGroupAssocs =((PartyInvitationGroupAssocFound) Scheduler.execute(query).data()).getPartyInvitationGroupAssocs();

		return ResponseEntity.ok().body(partyInvitationGroupAssocs);

	}

	/**
	 * creates a new PartyInvitationGroupAssoc entry in the ofbiz database
	 * 
	 * @param partyInvitationGroupAssocToBeAdded
	 *            the PartyInvitationGroupAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyInvitationGroupAssoc> createPartyInvitationGroupAssoc(@RequestBody PartyInvitationGroupAssoc partyInvitationGroupAssocToBeAdded) throws Exception {

		AddPartyInvitationGroupAssoc command = new AddPartyInvitationGroupAssoc(partyInvitationGroupAssocToBeAdded);
		PartyInvitationGroupAssoc partyInvitationGroupAssoc = ((PartyInvitationGroupAssocAdded) Scheduler.execute(command).data()).getAddedPartyInvitationGroupAssoc();
		
		if (partyInvitationGroupAssoc != null) 
			return successful(partyInvitationGroupAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyInvitationGroupAssoc with the specific Id
	 * 
	 * @param partyInvitationGroupAssocToBeUpdated
	 *            the PartyInvitationGroupAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyInvitationGroupAssoc(@RequestBody PartyInvitationGroupAssoc partyInvitationGroupAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyInvitationGroupAssocToBeUpdated.setnull(null);

		UpdatePartyInvitationGroupAssoc command = new UpdatePartyInvitationGroupAssoc(partyInvitationGroupAssocToBeUpdated);

		try {
			if(((PartyInvitationGroupAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyInvitationGroupAssocId}")
	public ResponseEntity<PartyInvitationGroupAssoc> findById(@PathVariable String partyInvitationGroupAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyInvitationGroupAssocId", partyInvitationGroupAssocId);
		try {

			List<PartyInvitationGroupAssoc> foundPartyInvitationGroupAssoc = findPartyInvitationGroupAssocsBy(requestParams).getBody();
			if(foundPartyInvitationGroupAssoc.size()==1){				return successful(foundPartyInvitationGroupAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyInvitationGroupAssocId}")
	public ResponseEntity<String> deletePartyInvitationGroupAssocByIdUpdated(@PathVariable String partyInvitationGroupAssocId) throws Exception {
		DeletePartyInvitationGroupAssoc command = new DeletePartyInvitationGroupAssoc(partyInvitationGroupAssocId);

		try {
			if (((PartyInvitationGroupAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
