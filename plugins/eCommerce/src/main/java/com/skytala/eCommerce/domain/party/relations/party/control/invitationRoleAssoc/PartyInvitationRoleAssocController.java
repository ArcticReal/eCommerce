package com.skytala.eCommerce.domain.party.relations.party.control.invitationRoleAssoc;

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
import com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc.AddPartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc.DeletePartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.command.invitationRoleAssoc.UpdatePartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocFound;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationRoleAssoc.PartyInvitationRoleAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;
import com.skytala.eCommerce.domain.party.relations.party.query.invitationRoleAssoc.FindPartyInvitationRoleAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyInvitationRoleAssocs")
public class PartyInvitationRoleAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyInvitationRoleAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyInvitationRoleAssoc
	 * @return a List with the PartyInvitationRoleAssocs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyInvitationRoleAssoc>> findPartyInvitationRoleAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyInvitationRoleAssocsBy query = new FindPartyInvitationRoleAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyInvitationRoleAssoc> partyInvitationRoleAssocs =((PartyInvitationRoleAssocFound) Scheduler.execute(query).data()).getPartyInvitationRoleAssocs();

		return ResponseEntity.ok().body(partyInvitationRoleAssocs);

	}

	/**
	 * creates a new PartyInvitationRoleAssoc entry in the ofbiz database
	 * 
	 * @param partyInvitationRoleAssocToBeAdded
	 *            the PartyInvitationRoleAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyInvitationRoleAssoc> createPartyInvitationRoleAssoc(@RequestBody PartyInvitationRoleAssoc partyInvitationRoleAssocToBeAdded) throws Exception {

		AddPartyInvitationRoleAssoc command = new AddPartyInvitationRoleAssoc(partyInvitationRoleAssocToBeAdded);
		PartyInvitationRoleAssoc partyInvitationRoleAssoc = ((PartyInvitationRoleAssocAdded) Scheduler.execute(command).data()).getAddedPartyInvitationRoleAssoc();
		
		if (partyInvitationRoleAssoc != null) 
			return successful(partyInvitationRoleAssoc);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyInvitationRoleAssoc with the specific Id
	 * 
	 * @param partyInvitationRoleAssocToBeUpdated
	 *            the PartyInvitationRoleAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyInvitationRoleAssoc(@RequestBody PartyInvitationRoleAssoc partyInvitationRoleAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyInvitationRoleAssocToBeUpdated.setnull(null);

		UpdatePartyInvitationRoleAssoc command = new UpdatePartyInvitationRoleAssoc(partyInvitationRoleAssocToBeUpdated);

		try {
			if(((PartyInvitationRoleAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyInvitationRoleAssocId}")
	public ResponseEntity<PartyInvitationRoleAssoc> findById(@PathVariable String partyInvitationRoleAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyInvitationRoleAssocId", partyInvitationRoleAssocId);
		try {

			List<PartyInvitationRoleAssoc> foundPartyInvitationRoleAssoc = findPartyInvitationRoleAssocsBy(requestParams).getBody();
			if(foundPartyInvitationRoleAssoc.size()==1){				return successful(foundPartyInvitationRoleAssoc.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyInvitationRoleAssocId}")
	public ResponseEntity<String> deletePartyInvitationRoleAssocByIdUpdated(@PathVariable String partyInvitationRoleAssocId) throws Exception {
		DeletePartyInvitationRoleAssoc command = new DeletePartyInvitationRoleAssoc(partyInvitationRoleAssocId);

		try {
			if (((PartyInvitationRoleAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
