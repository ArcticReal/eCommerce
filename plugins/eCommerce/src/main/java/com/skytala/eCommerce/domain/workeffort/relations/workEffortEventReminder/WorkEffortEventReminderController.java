package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.command.AddWorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.command.DeleteWorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.command.UpdateWorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event.WorkEffortEventReminderAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event.WorkEffortEventReminderDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event.WorkEffortEventReminderFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event.WorkEffortEventReminderUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.mapper.WorkEffortEventReminderMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.query.FindWorkEffortEventRemindersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortEventReminders")
public class WorkEffortEventReminderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortEventReminderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortEventReminder
	 * @return a List with the WorkEffortEventReminders
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortEventRemindersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortEventRemindersBy query = new FindWorkEffortEventRemindersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortEventReminder> workEffortEventReminders =((WorkEffortEventReminderFound) Scheduler.execute(query).data()).getWorkEffortEventReminders();

		if (workEffortEventReminders.size() == 1) {
			return ResponseEntity.ok().body(workEffortEventReminders.get(0));
		}

		return ResponseEntity.ok().body(workEffortEventReminders);

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
	public ResponseEntity<Object> createWorkEffortEventReminder(HttpServletRequest request) throws Exception {

		WorkEffortEventReminder workEffortEventReminderToBeAdded = new WorkEffortEventReminder();
		try {
			workEffortEventReminderToBeAdded = WorkEffortEventReminderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortEventReminder(workEffortEventReminderToBeAdded);

	}

	/**
	 * creates a new WorkEffortEventReminder entry in the ofbiz database
	 * 
	 * @param workEffortEventReminderToBeAdded
	 *            the WorkEffortEventReminder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortEventReminder(@RequestBody WorkEffortEventReminder workEffortEventReminderToBeAdded) throws Exception {

		AddWorkEffortEventReminder command = new AddWorkEffortEventReminder(workEffortEventReminderToBeAdded);
		WorkEffortEventReminder workEffortEventReminder = ((WorkEffortEventReminderAdded) Scheduler.execute(command).data()).getAddedWorkEffortEventReminder();
		
		if (workEffortEventReminder != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortEventReminder);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortEventReminder could not be created.");
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
	public boolean updateWorkEffortEventReminder(HttpServletRequest request) throws Exception {

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

		WorkEffortEventReminder workEffortEventReminderToBeUpdated = new WorkEffortEventReminder();

		try {
			workEffortEventReminderToBeUpdated = WorkEffortEventReminderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortEventReminder(workEffortEventReminderToBeUpdated, workEffortEventReminderToBeUpdated.getSequenceId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WorkEffortEventReminder with the specific Id
	 * 
	 * @param workEffortEventReminderToBeUpdated
	 *            the WorkEffortEventReminder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{sequenceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWorkEffortEventReminder(@RequestBody WorkEffortEventReminder workEffortEventReminderToBeUpdated,
			@PathVariable String sequenceId) throws Exception {

		workEffortEventReminderToBeUpdated.setSequenceId(sequenceId);

		UpdateWorkEffortEventReminder command = new UpdateWorkEffortEventReminder(workEffortEventReminderToBeUpdated);

		try {
			if(((WorkEffortEventReminderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortEventReminderId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortEventReminderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortEventReminderId", workEffortEventReminderId);
		try {

			Object foundWorkEffortEventReminder = findWorkEffortEventRemindersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortEventReminder);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortEventReminderId}")
	public ResponseEntity<Object> deleteWorkEffortEventReminderByIdUpdated(@PathVariable String workEffortEventReminderId) throws Exception {
		DeleteWorkEffortEventReminder command = new DeleteWorkEffortEventReminder(workEffortEventReminderId);

		try {
			if (((WorkEffortEventReminderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortEventReminder could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortEventReminder/\" plus one of the following: "
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
