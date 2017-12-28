package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent;

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
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.AddMrpEvent;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.DeleteMrpEvent;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.UpdateMrpEvent;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventFound;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.MrpEventMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.query.FindMrpEventsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/manufacturing/mrpEvents")
public class MrpEventController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MrpEventController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MrpEvent
	 * @return a List with the MrpEvents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<MrpEvent>> findMrpEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMrpEventsBy query = new FindMrpEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MrpEvent> mrpEvents =((MrpEventFound) Scheduler.execute(query).data()).getMrpEvents();

		return ResponseEntity.ok().body(mrpEvents);

	}

	/**
	 * creates a new MrpEvent entry in the ofbiz database
	 * 
	 * @param mrpEventToBeAdded
	 *            the MrpEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MrpEvent> createMrpEvent(@RequestBody MrpEvent mrpEventToBeAdded) throws Exception {

		AddMrpEvent command = new AddMrpEvent(mrpEventToBeAdded);
		MrpEvent mrpEvent = ((MrpEventAdded) Scheduler.execute(command).data()).getAddedMrpEvent();
		
		if (mrpEvent != null) 
			return successful(mrpEvent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MrpEvent with the specific Id
	 * 
	 * @param mrpEventToBeUpdated
	 *            the MrpEvent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{mrpId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMrpEvent(@RequestBody MrpEvent mrpEventToBeUpdated,
			@PathVariable String mrpId) throws Exception {

		mrpEventToBeUpdated.setMrpId(mrpId);

		UpdateMrpEvent command = new UpdateMrpEvent(mrpEventToBeUpdated);

		try {
			if(((MrpEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{mrpEventId}")
	public ResponseEntity<MrpEvent> findById(@PathVariable String mrpEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mrpEventId", mrpEventId);
		try {

			List<MrpEvent> foundMrpEvent = findMrpEventsBy(requestParams).getBody();
			if(foundMrpEvent.size()==1){				return successful(foundMrpEvent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{mrpEventId}")
	public ResponseEntity<String> deleteMrpEventByIdUpdated(@PathVariable String mrpEventId) throws Exception {
		DeleteMrpEvent command = new DeleteMrpEvent(mrpEventId);

		try {
			if (((MrpEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
