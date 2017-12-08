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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/timeEntrys")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTimeEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTimeEntrysBy query = new FindTimeEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TimeEntry> timeEntrys =((TimeEntryFound) Scheduler.execute(query).data()).getTimeEntrys();

		if (timeEntrys.size() == 1) {
			return ResponseEntity.ok().body(timeEntrys.get(0));
		}

		return ResponseEntity.ok().body(timeEntrys);

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
	public ResponseEntity<Object> createTimeEntry(HttpServletRequest request) throws Exception {

		TimeEntry timeEntryToBeAdded = new TimeEntry();
		try {
			timeEntryToBeAdded = TimeEntryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTimeEntry(timeEntryToBeAdded);

	}

	/**
	 * creates a new TimeEntry entry in the ofbiz database
	 * 
	 * @param timeEntryToBeAdded
	 *            the TimeEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTimeEntry(@RequestBody TimeEntry timeEntryToBeAdded) throws Exception {

		AddTimeEntry command = new AddTimeEntry(timeEntryToBeAdded);
		TimeEntry timeEntry = ((TimeEntryAdded) Scheduler.execute(command).data()).getAddedTimeEntry();
		
		if (timeEntry != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(timeEntry);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TimeEntry could not be created.");
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
	public boolean updateTimeEntry(HttpServletRequest request) throws Exception {

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

		TimeEntry timeEntryToBeUpdated = new TimeEntry();

		try {
			timeEntryToBeUpdated = TimeEntryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTimeEntry(timeEntryToBeUpdated, timeEntryToBeUpdated.getTimeEntryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTimeEntry(@RequestBody TimeEntry timeEntryToBeUpdated,
			@PathVariable String timeEntryId) throws Exception {

		timeEntryToBeUpdated.setTimeEntryId(timeEntryId);

		UpdateTimeEntry command = new UpdateTimeEntry(timeEntryToBeUpdated);

		try {
			if(((TimeEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{timeEntryId}")
	public ResponseEntity<Object> findById(@PathVariable String timeEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("timeEntryId", timeEntryId);
		try {

			Object foundTimeEntry = findTimeEntrysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTimeEntry);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{timeEntryId}")
	public ResponseEntity<Object> deleteTimeEntryByIdUpdated(@PathVariable String timeEntryId) throws Exception {
		DeleteTimeEntry command = new DeleteTimeEntry(timeEntryId);

		try {
			if (((TimeEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TimeEntry could not be deleted");

	}

}
