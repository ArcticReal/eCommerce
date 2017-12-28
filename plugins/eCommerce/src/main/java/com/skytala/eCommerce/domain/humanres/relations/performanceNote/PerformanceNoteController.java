package com.skytala.eCommerce.domain.humanres.relations.performanceNote;

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
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.command.AddPerformanceNote;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.command.DeletePerformanceNote;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.command.UpdatePerformanceNote;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteAdded;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteDeleted;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteFound;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteUpdated;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.mapper.PerformanceNoteMapper;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.query.FindPerformanceNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/performanceNotes")
public class PerformanceNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PerformanceNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PerformanceNote
	 * @return a List with the PerformanceNotes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PerformanceNote>> findPerformanceNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerformanceNotesBy query = new FindPerformanceNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerformanceNote> performanceNotes =((PerformanceNoteFound) Scheduler.execute(query).data()).getPerformanceNotes();

		return ResponseEntity.ok().body(performanceNotes);

	}

	/**
	 * creates a new PerformanceNote entry in the ofbiz database
	 * 
	 * @param performanceNoteToBeAdded
	 *            the PerformanceNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PerformanceNote> createPerformanceNote(@RequestBody PerformanceNote performanceNoteToBeAdded) throws Exception {

		AddPerformanceNote command = new AddPerformanceNote(performanceNoteToBeAdded);
		PerformanceNote performanceNote = ((PerformanceNoteAdded) Scheduler.execute(command).data()).getAddedPerformanceNote();
		
		if (performanceNote != null) 
			return successful(performanceNote);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PerformanceNote with the specific Id
	 * 
	 * @param performanceNoteToBeUpdated
	 *            the PerformanceNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePerformanceNote(@RequestBody PerformanceNote performanceNoteToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		performanceNoteToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePerformanceNote command = new UpdatePerformanceNote(performanceNoteToBeUpdated);

		try {
			if(((PerformanceNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{performanceNoteId}")
	public ResponseEntity<PerformanceNote> findById(@PathVariable String performanceNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("performanceNoteId", performanceNoteId);
		try {

			List<PerformanceNote> foundPerformanceNote = findPerformanceNotesBy(requestParams).getBody();
			if(foundPerformanceNote.size()==1){				return successful(foundPerformanceNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{performanceNoteId}")
	public ResponseEntity<String> deletePerformanceNoteByIdUpdated(@PathVariable String performanceNoteId) throws Exception {
		DeletePerformanceNote command = new DeletePerformanceNote(performanceNoteId);

		try {
			if (((PerformanceNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
