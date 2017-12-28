package com.skytala.eCommerce.domain.party.relations.party.control.note;

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
import com.skytala.eCommerce.domain.party.relations.party.command.note.AddPartyNote;
import com.skytala.eCommerce.domain.party.relations.party.command.note.DeletePartyNote;
import com.skytala.eCommerce.domain.party.relations.party.command.note.UpdatePartyNote;
import com.skytala.eCommerce.domain.party.relations.party.event.note.PartyNoteAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.note.PartyNoteDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.note.PartyNoteFound;
import com.skytala.eCommerce.domain.party.relations.party.event.note.PartyNoteUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.note.PartyNoteMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.note.PartyNote;
import com.skytala.eCommerce.domain.party.relations.party.query.note.FindPartyNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyNotes")
public class PartyNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyNote
	 * @return a List with the PartyNotes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyNote>> findPartyNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyNotesBy query = new FindPartyNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyNote> partyNotes =((PartyNoteFound) Scheduler.execute(query).data()).getPartyNotes();

		return ResponseEntity.ok().body(partyNotes);

	}

	/**
	 * creates a new PartyNote entry in the ofbiz database
	 * 
	 * @param partyNoteToBeAdded
	 *            the PartyNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyNote> createPartyNote(@RequestBody PartyNote partyNoteToBeAdded) throws Exception {

		AddPartyNote command = new AddPartyNote(partyNoteToBeAdded);
		PartyNote partyNote = ((PartyNoteAdded) Scheduler.execute(command).data()).getAddedPartyNote();
		
		if (partyNote != null) 
			return successful(partyNote);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyNote with the specific Id
	 * 
	 * @param partyNoteToBeUpdated
	 *            the PartyNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyNote(@RequestBody PartyNote partyNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyNoteToBeUpdated.setnull(null);

		UpdatePartyNote command = new UpdatePartyNote(partyNoteToBeUpdated);

		try {
			if(((PartyNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyNoteId}")
	public ResponseEntity<PartyNote> findById(@PathVariable String partyNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyNoteId", partyNoteId);
		try {

			List<PartyNote> foundPartyNote = findPartyNotesBy(requestParams).getBody();
			if(foundPartyNote.size()==1){				return successful(foundPartyNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyNoteId}")
	public ResponseEntity<String> deletePartyNoteByIdUpdated(@PathVariable String partyNoteId) throws Exception {
		DeletePartyNote command = new DeletePartyNote(partyNoteId);

		try {
			if (((PartyNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
