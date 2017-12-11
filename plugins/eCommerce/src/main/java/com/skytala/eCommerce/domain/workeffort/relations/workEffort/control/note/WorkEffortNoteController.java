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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findWorkEffortNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortNotesBy query = new FindWorkEffortNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortNote> workEffortNotes =((WorkEffortNoteFound) Scheduler.execute(query).data()).getWorkEffortNotes();

		if (workEffortNotes.size() == 1) {
			return ResponseEntity.ok().body(workEffortNotes.get(0));
		}

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
	public ResponseEntity<Object> createWorkEffortNote(HttpServletRequest request) throws Exception {

		WorkEffortNote workEffortNoteToBeAdded = new WorkEffortNote();
		try {
			workEffortNoteToBeAdded = WorkEffortNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createWorkEffortNote(@RequestBody WorkEffortNote workEffortNoteToBeAdded) throws Exception {

		AddWorkEffortNote command = new AddWorkEffortNote(workEffortNoteToBeAdded);
		WorkEffortNote workEffortNote = ((WorkEffortNoteAdded) Scheduler.execute(command).data()).getAddedWorkEffortNote();
		
		if (workEffortNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortNote could not be created.");
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
	public boolean updateWorkEffortNote(HttpServletRequest request) throws Exception {

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

		WorkEffortNote workEffortNoteToBeUpdated = new WorkEffortNote();

		try {
			workEffortNoteToBeUpdated = WorkEffortNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortNote(workEffortNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkEffortNote(@RequestBody WorkEffortNote workEffortNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortNoteToBeUpdated.setnull(null);

		UpdateWorkEffortNote command = new UpdateWorkEffortNote(workEffortNoteToBeUpdated);

		try {
			if(((WorkEffortNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{workEffortNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortNoteId", workEffortNoteId);
		try {

			Object foundWorkEffortNote = findWorkEffortNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{workEffortNoteId}")
	public ResponseEntity<Object> deleteWorkEffortNoteByIdUpdated(@PathVariable String workEffortNoteId) throws Exception {
		DeleteWorkEffortNote command = new DeleteWorkEffortNote(workEffortNoteId);

		try {
			if (((WorkEffortNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortNote could not be deleted");

	}

}
