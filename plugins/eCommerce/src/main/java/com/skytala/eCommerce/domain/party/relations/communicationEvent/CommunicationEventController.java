package com.skytala.eCommerce.domain.party.relations.communicationEvent;

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
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.AddCommunicationEvent;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.DeleteCommunicationEvent;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.UpdateCommunicationEvent;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.CommunicationEventAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.CommunicationEventDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.CommunicationEventFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.CommunicationEventUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.CommunicationEventMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.CommunicationEvent;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.query.FindCommunicationEventsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/communicationEvents")
public class CommunicationEventController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEvent
	 * @return a List with the CommunicationEvents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEvent>> findCommunicationEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventsBy query = new FindCommunicationEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEvent> communicationEvents =((CommunicationEventFound) Scheduler.execute(query).data()).getCommunicationEvents();

		return ResponseEntity.ok().body(communicationEvents);

	}

	/**
	 * creates a new CommunicationEvent entry in the ofbiz database
	 * 
	 * @param communicationEventToBeAdded
	 *            the CommunicationEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommunicationEvent> createCommunicationEvent(@RequestBody CommunicationEvent communicationEventToBeAdded) throws Exception {

		AddCommunicationEvent command = new AddCommunicationEvent(communicationEventToBeAdded);
		CommunicationEvent communicationEvent = ((CommunicationEventAdded) Scheduler.execute(command).data()).getAddedCommunicationEvent();
		
		if (communicationEvent != null) 
			return successful(communicationEvent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommunicationEvent with the specific Id
	 * 
	 * @param communicationEventToBeUpdated
	 *            the CommunicationEvent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{communicationEventId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommunicationEvent(@RequestBody CommunicationEvent communicationEventToBeUpdated,
			@PathVariable String communicationEventId) throws Exception {

		communicationEventToBeUpdated.setCommunicationEventId(communicationEventId);

		UpdateCommunicationEvent command = new UpdateCommunicationEvent(communicationEventToBeUpdated);

		try {
			if(((CommunicationEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventId}")
	public ResponseEntity<CommunicationEvent> findById(@PathVariable String communicationEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventId", communicationEventId);
		try {

			List<CommunicationEvent> foundCommunicationEvent = findCommunicationEventsBy(requestParams).getBody();
			if(foundCommunicationEvent.size()==1){				return successful(foundCommunicationEvent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventId}")
	public ResponseEntity<String> deleteCommunicationEventByIdUpdated(@PathVariable String communicationEventId) throws Exception {
		DeleteCommunicationEvent command = new DeleteCommunicationEvent(communicationEventId);

		try {
			if (((CommunicationEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
