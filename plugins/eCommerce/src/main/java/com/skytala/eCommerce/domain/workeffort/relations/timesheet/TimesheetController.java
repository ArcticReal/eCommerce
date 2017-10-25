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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/timesheets")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTimesheetsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTimesheetsBy query = new FindTimesheetsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Timesheet> timesheets =((TimesheetFound) Scheduler.execute(query).data()).getTimesheets();

		if (timesheets.size() == 1) {
			return ResponseEntity.ok().body(timesheets.get(0));
		}

		return ResponseEntity.ok().body(timesheets);

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
	public ResponseEntity<Object> createTimesheet(HttpServletRequest request) throws Exception {

		Timesheet timesheetToBeAdded = new Timesheet();
		try {
			timesheetToBeAdded = TimesheetMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTimesheet(timesheetToBeAdded);

	}

	/**
	 * creates a new Timesheet entry in the ofbiz database
	 * 
	 * @param timesheetToBeAdded
	 *            the Timesheet thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTimesheet(@RequestBody Timesheet timesheetToBeAdded) throws Exception {

		AddTimesheet command = new AddTimesheet(timesheetToBeAdded);
		Timesheet timesheet = ((TimesheetAdded) Scheduler.execute(command).data()).getAddedTimesheet();
		
		if (timesheet != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(timesheet);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Timesheet could not be created.");
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
	public boolean updateTimesheet(HttpServletRequest request) throws Exception {

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

		Timesheet timesheetToBeUpdated = new Timesheet();

		try {
			timesheetToBeUpdated = TimesheetMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTimesheet(timesheetToBeUpdated, timesheetToBeUpdated.getTimesheetId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTimesheet(@RequestBody Timesheet timesheetToBeUpdated,
			@PathVariable String timesheetId) throws Exception {

		timesheetToBeUpdated.setTimesheetId(timesheetId);

		UpdateTimesheet command = new UpdateTimesheet(timesheetToBeUpdated);

		try {
			if(((TimesheetUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{timesheetId}")
	public ResponseEntity<Object> findById(@PathVariable String timesheetId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("timesheetId", timesheetId);
		try {

			Object foundTimesheet = findTimesheetsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTimesheet);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{timesheetId}")
	public ResponseEntity<Object> deleteTimesheetByIdUpdated(@PathVariable String timesheetId) throws Exception {
		DeleteTimesheet command = new DeleteTimesheet(timesheetId);

		try {
			if (((TimesheetDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Timesheet could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/timesheet/\" plus one of the following: "
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
