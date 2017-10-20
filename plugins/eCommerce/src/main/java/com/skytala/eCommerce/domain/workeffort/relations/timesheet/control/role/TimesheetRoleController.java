package com.skytala.eCommerce.domain.workeffort.relations.timesheet.control.role;

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
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.command.role.AddTimesheetRole;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.command.role.DeleteTimesheetRole;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.command.role.UpdateTimesheetRole;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role.TimesheetRoleAdded;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role.TimesheetRoleDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role.TimesheetRoleFound;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role.TimesheetRoleUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.mapper.role.TimesheetRoleMapper;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.role.TimesheetRole;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.query.role.FindTimesheetRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/timesheetRoles")
public class TimesheetRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TimesheetRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TimesheetRole
	 * @return a List with the TimesheetRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTimesheetRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTimesheetRolesBy query = new FindTimesheetRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TimesheetRole> timesheetRoles =((TimesheetRoleFound) Scheduler.execute(query).data()).getTimesheetRoles();

		if (timesheetRoles.size() == 1) {
			return ResponseEntity.ok().body(timesheetRoles.get(0));
		}

		return ResponseEntity.ok().body(timesheetRoles);

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
	public ResponseEntity<Object> createTimesheetRole(HttpServletRequest request) throws Exception {

		TimesheetRole timesheetRoleToBeAdded = new TimesheetRole();
		try {
			timesheetRoleToBeAdded = TimesheetRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTimesheetRole(timesheetRoleToBeAdded);

	}

	/**
	 * creates a new TimesheetRole entry in the ofbiz database
	 * 
	 * @param timesheetRoleToBeAdded
	 *            the TimesheetRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTimesheetRole(@RequestBody TimesheetRole timesheetRoleToBeAdded) throws Exception {

		AddTimesheetRole command = new AddTimesheetRole(timesheetRoleToBeAdded);
		TimesheetRole timesheetRole = ((TimesheetRoleAdded) Scheduler.execute(command).data()).getAddedTimesheetRole();
		
		if (timesheetRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(timesheetRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TimesheetRole could not be created.");
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
	public boolean updateTimesheetRole(HttpServletRequest request) throws Exception {

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

		TimesheetRole timesheetRoleToBeUpdated = new TimesheetRole();

		try {
			timesheetRoleToBeUpdated = TimesheetRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTimesheetRole(timesheetRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TimesheetRole with the specific Id
	 * 
	 * @param timesheetRoleToBeUpdated
	 *            the TimesheetRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTimesheetRole(@RequestBody TimesheetRole timesheetRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		timesheetRoleToBeUpdated.setnull(null);

		UpdateTimesheetRole command = new UpdateTimesheetRole(timesheetRoleToBeUpdated);

		try {
			if(((TimesheetRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{timesheetRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String timesheetRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("timesheetRoleId", timesheetRoleId);
		try {

			Object foundTimesheetRole = findTimesheetRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTimesheetRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{timesheetRoleId}")
	public ResponseEntity<Object> deleteTimesheetRoleByIdUpdated(@PathVariable String timesheetRoleId) throws Exception {
		DeleteTimesheetRole command = new DeleteTimesheetRole(timesheetRoleId);

		try {
			if (((TimesheetRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TimesheetRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/timesheetRole/\" plus one of the following: "
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
