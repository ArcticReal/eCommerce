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
	public ResponseEntity<Object> findPartyNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyNotesBy query = new FindPartyNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyNote> partyNotes =((PartyNoteFound) Scheduler.execute(query).data()).getPartyNotes();

		if (partyNotes.size() == 1) {
			return ResponseEntity.ok().body(partyNotes.get(0));
		}

		return ResponseEntity.ok().body(partyNotes);

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
	public ResponseEntity<Object> createPartyNote(HttpServletRequest request) throws Exception {

		PartyNote partyNoteToBeAdded = new PartyNote();
		try {
			partyNoteToBeAdded = PartyNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyNote(partyNoteToBeAdded);

	}

	/**
	 * creates a new PartyNote entry in the ofbiz database
	 * 
	 * @param partyNoteToBeAdded
	 *            the PartyNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyNote(@RequestBody PartyNote partyNoteToBeAdded) throws Exception {

		AddPartyNote command = new AddPartyNote(partyNoteToBeAdded);
		PartyNote partyNote = ((PartyNoteAdded) Scheduler.execute(command).data()).getAddedPartyNote();
		
		if (partyNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyNote could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePartyNote(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		PartyNote partyNoteToBeUpdated = new PartyNote();

		try {
			partyNoteToBeUpdated = PartyNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyNote(partyNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyNote(@RequestBody PartyNote partyNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyNoteToBeUpdated.setnull(null);

		UpdatePartyNote command = new UpdatePartyNote(partyNoteToBeUpdated);

		try {
			if(((PartyNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String partyNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyNoteId", partyNoteId);
		try {

			Object foundPartyNote = findPartyNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyNoteId}")
	public ResponseEntity<Object> deletePartyNoteByIdUpdated(@PathVariable String partyNoteId) throws Exception {
		DeletePartyNote command = new DeletePartyNote(partyNoteId);

		try {
			if (((PartyNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyNote could not be deleted");

	}

}
