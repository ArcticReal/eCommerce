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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/performanceNotes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPerformanceNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerformanceNotesBy query = new FindPerformanceNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerformanceNote> performanceNotes =((PerformanceNoteFound) Scheduler.execute(query).data()).getPerformanceNotes();

		if (performanceNotes.size() == 1) {
			return ResponseEntity.ok().body(performanceNotes.get(0));
		}

		return ResponseEntity.ok().body(performanceNotes);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPerformanceNote(HttpServletRequest request) throws Exception {

		PerformanceNote performanceNoteToBeAdded = new PerformanceNote();
		try {
			performanceNoteToBeAdded = PerformanceNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPerformanceNote(performanceNoteToBeAdded);

	}

	/**
	 * creates a new PerformanceNote entry in the ofbiz database
	 * 
	 * @param performanceNoteToBeAdded
	 *            the PerformanceNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPerformanceNote(@RequestBody PerformanceNote performanceNoteToBeAdded) throws Exception {

		AddPerformanceNote command = new AddPerformanceNote(performanceNoteToBeAdded);
		PerformanceNote performanceNote = ((PerformanceNoteAdded) Scheduler.execute(command).data()).getAddedPerformanceNote();
		
		if (performanceNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(performanceNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PerformanceNote could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePerformanceNote(HttpServletRequest request) throws Exception {

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

		PerformanceNote performanceNoteToBeUpdated = new PerformanceNote();

		try {
			performanceNoteToBeUpdated = PerformanceNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePerformanceNote(performanceNoteToBeUpdated, performanceNoteToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePerformanceNote(@RequestBody PerformanceNote performanceNoteToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		performanceNoteToBeUpdated.setRoleTypeId(roleTypeId);

		UpdatePerformanceNote command = new UpdatePerformanceNote(performanceNoteToBeUpdated);

		try {
			if(((PerformanceNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{performanceNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String performanceNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("performanceNoteId", performanceNoteId);
		try {

			Object foundPerformanceNote = findPerformanceNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPerformanceNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{performanceNoteId}")
	public ResponseEntity<Object> deletePerformanceNoteByIdUpdated(@PathVariable String performanceNoteId) throws Exception {
		DeletePerformanceNote command = new DeletePerformanceNote(performanceNoteId);

		try {
			if (((PerformanceNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PerformanceNote could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/performanceNote/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
