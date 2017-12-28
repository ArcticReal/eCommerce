package com.skytala.eCommerce.domain.workeffort.relations.timesheet;

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
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.command.AddTimesheet;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.command.DeleteTimesheet;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.command.UpdateTimesheet;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.TimesheetAdded;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.TimesheetDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.TimesheetFound;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.TimesheetUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.mapper.TimesheetMapper;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.Timesheet;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.query.FindTimesheetsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/timesheets")
public class TimesheetController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TimesheetController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Timesheet
	 * @return a List with the Timesheets
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Timesheet>> findTimesheetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTimesheetsBy query = new FindTimesheetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Timesheet> timesheets =((TimesheetFound) Scheduler.execute(query).data()).getTimesheets();

		return ResponseEntity.ok().body(timesheets);

	}

	/**
	 * creates a new Timesheet entry in the ofbiz database
	 * 
	 * @param timesheetToBeAdded
	 *            the Timesheet thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Timesheet> createTimesheet(@RequestBody Timesheet timesheetToBeAdded) throws Exception {

		AddTimesheet command = new AddTimesheet(timesheetToBeAdded);
		Timesheet timesheet = ((TimesheetAdded) Scheduler.execute(command).data()).getAddedTimesheet();
		
		if (timesheet != null) 
			return successful(timesheet);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Timesheet with the specific Id
	 * 
	 * @param timesheetToBeUpdated
	 *            the Timesheet thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{timesheetId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTimesheet(@RequestBody Timesheet timesheetToBeUpdated,
			@PathVariable String timesheetId) throws Exception {

		timesheetToBeUpdated.setTimesheetId(timesheetId);

		UpdateTimesheet command = new UpdateTimesheet(timesheetToBeUpdated);

		try {
			if(((TimesheetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{timesheetId}")
	public ResponseEntity<Timesheet> findById(@PathVariable String timesheetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("timesheetId", timesheetId);
		try {

			List<Timesheet> foundTimesheet = findTimesheetsBy(requestParams).getBody();
			if(foundTimesheet.size()==1){				return successful(foundTimesheet.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{timesheetId}")
	public ResponseEntity<String> deleteTimesheetByIdUpdated(@PathVariable String timesheetId) throws Exception {
		DeleteTimesheet command = new DeleteTimesheet(timesheetId);

		try {
			if (((TimesheetDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
