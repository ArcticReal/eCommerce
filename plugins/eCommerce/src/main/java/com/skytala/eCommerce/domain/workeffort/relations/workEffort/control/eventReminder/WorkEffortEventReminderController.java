package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.eventReminder;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.eventReminder.AddWorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.eventReminder.DeleteWorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.eventReminder.UpdateWorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder.WorkEffortEventReminderAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder.WorkEffortEventReminderDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder.WorkEffortEventReminderFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.eventReminder.WorkEffortEventReminderUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.eventReminder.WorkEffortEventReminderMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.eventReminder.WorkEffortEventReminder;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.eventReminder.FindWorkEffortEventRemindersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortEventReminders")
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
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortEventReminder>> findWorkEffortEventRemindersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortEventRemindersBy query = new FindWorkEffortEventRemindersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortEventReminder> workEffortEventReminders =((WorkEffortEventReminderFound) Scheduler.execute(query).data()).getWorkEffortEventReminders();

		return ResponseEntity.ok().body(workEffortEventReminders);

	}

	/**
	 * creates a new WorkEffortEventReminder entry in the ofbiz database
	 * 
	 * @param workEffortEventReminderToBeAdded
	 *            the WorkEffortEventReminder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortEventReminder> createWorkEffortEventReminder(@RequestBody WorkEffortEventReminder workEffortEventReminderToBeAdded) throws Exception {

		AddWorkEffortEventReminder command = new AddWorkEffortEventReminder(workEffortEventReminderToBeAdded);
		WorkEffortEventReminder workEffortEventReminder = ((WorkEffortEventReminderAdded) Scheduler.execute(command).data()).getAddedWorkEffortEventReminder();
		
		if (workEffortEventReminder != null) 
			return successful(workEffortEventReminder);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWorkEffortEventReminder(@RequestBody WorkEffortEventReminder workEffortEventReminderToBeUpdated,
			@PathVariable String sequenceId) throws Exception {

		workEffortEventReminderToBeUpdated.setSequenceId(sequenceId);

		UpdateWorkEffortEventReminder command = new UpdateWorkEffortEventReminder(workEffortEventReminderToBeUpdated);

		try {
			if(((WorkEffortEventReminderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortEventReminderId}")
	public ResponseEntity<WorkEffortEventReminder> findById(@PathVariable String workEffortEventReminderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortEventReminderId", workEffortEventReminderId);
		try {

			List<WorkEffortEventReminder> foundWorkEffortEventReminder = findWorkEffortEventRemindersBy(requestParams).getBody();
			if(foundWorkEffortEventReminder.size()==1){				return successful(foundWorkEffortEventReminder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortEventReminderId}")
	public ResponseEntity<String> deleteWorkEffortEventReminderByIdUpdated(@PathVariable String workEffortEventReminderId) throws Exception {
		DeleteWorkEffortEventReminder command = new DeleteWorkEffortEventReminder(workEffortEventReminderId);

		try {
			if (((WorkEffortEventReminderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
