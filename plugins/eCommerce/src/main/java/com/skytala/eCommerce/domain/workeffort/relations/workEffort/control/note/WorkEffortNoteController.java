package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.note;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.note.AddWorkEffortNote;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.note.DeleteWorkEffortNote;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.note.UpdateWorkEffortNote;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note.WorkEffortNoteAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note.WorkEffortNoteDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note.WorkEffortNoteFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note.WorkEffortNoteUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.note.WorkEffortNoteMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.note.WorkEffortNote;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.note.FindWorkEffortNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortNotes")
public class WorkEffortNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortNote
	 * @return a List with the WorkEffortNotes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortNote>> findWorkEffortNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortNotesBy query = new FindWorkEffortNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortNote> workEffortNotes =((WorkEffortNoteFound) Scheduler.execute(query).data()).getWorkEffortNotes();

		return ResponseEntity.ok().body(workEffortNotes);

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
	public ResponseEntity<WorkEffortNote> createWorkEffortNote(HttpServletRequest request) throws Exception {

		WorkEffortNote workEffortNoteToBeAdded = new WorkEffortNote();
		try {
			workEffortNoteToBeAdded = WorkEffortNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createWorkEffortNote(workEffortNoteToBeAdded);

	}

	/**
	 * creates a new WorkEffortNote entry in the ofbiz database
	 * 
	 * @param workEffortNoteToBeAdded
	 *            the WorkEffortNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortNote> createWorkEffortNote(@RequestBody WorkEffortNote workEffortNoteToBeAdded) throws Exception {

		AddWorkEffortNote command = new AddWorkEffortNote(workEffortNoteToBeAdded);
		WorkEffortNote workEffortNote = ((WorkEffortNoteAdded) Scheduler.execute(command).data()).getAddedWorkEffortNote();
		
		if (workEffortNote != null) 
			return successful(workEffortNote);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortNote with the specific Id
	 * 
	 * @param workEffortNoteToBeUpdated
	 *            the WorkEffortNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortNote(@RequestBody WorkEffortNote workEffortNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortNoteToBeUpdated.setnull(null);

		UpdateWorkEffortNote command = new UpdateWorkEffortNote(workEffortNoteToBeUpdated);

		try {
			if(((WorkEffortNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortNoteId}")
	public ResponseEntity<WorkEffortNote> findById(@PathVariable String workEffortNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortNoteId", workEffortNoteId);
		try {

			List<WorkEffortNote> foundWorkEffortNote = findWorkEffortNotesBy(requestParams).getBody();
			if(foundWorkEffortNote.size()==1){				return successful(foundWorkEffortNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortNoteId}")
	public ResponseEntity<String> deleteWorkEffortNoteByIdUpdated(@PathVariable String workEffortNoteId) throws Exception {
		DeleteWorkEffortNote command = new DeleteWorkEffortNote(workEffortNoteId);

		try {
			if (((WorkEffortNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
