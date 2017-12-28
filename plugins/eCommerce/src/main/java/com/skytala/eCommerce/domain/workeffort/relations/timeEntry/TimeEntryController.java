package com.skytala.eCommerce.domain.workeffort.relations.timeEntry;

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
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.command.AddTimeEntry;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.command.DeleteTimeEntry;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.command.UpdateTimeEntry;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event.TimeEntryAdded;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event.TimeEntryDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event.TimeEntryFound;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.event.TimeEntryUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.mapper.TimeEntryMapper;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.model.TimeEntry;
import com.skytala.eCommerce.domain.workeffort.relations.timeEntry.query.FindTimeEntrysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/timeEntrys")
public class TimeEntryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TimeEntryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TimeEntry
	 * @return a List with the TimeEntrys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TimeEntry>> findTimeEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTimeEntrysBy query = new FindTimeEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TimeEntry> timeEntrys =((TimeEntryFound) Scheduler.execute(query).data()).getTimeEntrys();

		return ResponseEntity.ok().body(timeEntrys);

	}

	/**
	 * creates a new TimeEntry entry in the ofbiz database
	 * 
	 * @param timeEntryToBeAdded
	 *            the TimeEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TimeEntry> createTimeEntry(@RequestBody TimeEntry timeEntryToBeAdded) throws Exception {

		AddTimeEntry command = new AddTimeEntry(timeEntryToBeAdded);
		TimeEntry timeEntry = ((TimeEntryAdded) Scheduler.execute(command).data()).getAddedTimeEntry();
		
		if (timeEntry != null) 
			return successful(timeEntry);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TimeEntry with the specific Id
	 * 
	 * @param timeEntryToBeUpdated
	 *            the TimeEntry thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{timeEntryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTimeEntry(@RequestBody TimeEntry timeEntryToBeUpdated,
			@PathVariable String timeEntryId) throws Exception {

		timeEntryToBeUpdated.setTimeEntryId(timeEntryId);

		UpdateTimeEntry command = new UpdateTimeEntry(timeEntryToBeUpdated);

		try {
			if(((TimeEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{timeEntryId}")
	public ResponseEntity<TimeEntry> findById(@PathVariable String timeEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("timeEntryId", timeEntryId);
		try {

			List<TimeEntry> foundTimeEntry = findTimeEntrysBy(requestParams).getBody();
			if(foundTimeEntry.size()==1){				return successful(foundTimeEntry.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{timeEntryId}")
	public ResponseEntity<String> deleteTimeEntryByIdUpdated(@PathVariable String timeEntryId) throws Exception {
		DeleteTimeEntry command = new DeleteTimeEntry(timeEntryId);

		try {
			if (((TimeEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
