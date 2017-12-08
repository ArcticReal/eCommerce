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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/communicationEvents")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventsBy query = new FindCommunicationEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEvent> communicationEvents =((CommunicationEventFound) Scheduler.execute(query).data()).getCommunicationEvents();

		if (communicationEvents.size() == 1) {
			return ResponseEntity.ok().body(communicationEvents.get(0));
		}

		return ResponseEntity.ok().body(communicationEvents);

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
	public ResponseEntity<Object> createCommunicationEvent(HttpServletRequest request) throws Exception {

		CommunicationEvent communicationEventToBeAdded = new CommunicationEvent();
		try {
			communicationEventToBeAdded = CommunicationEventMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEvent(communicationEventToBeAdded);

	}

	/**
	 * creates a new CommunicationEvent entry in the ofbiz database
	 * 
	 * @param communicationEventToBeAdded
	 *            the CommunicationEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEvent(@RequestBody CommunicationEvent communicationEventToBeAdded) throws Exception {

		AddCommunicationEvent command = new AddCommunicationEvent(communicationEventToBeAdded);
		CommunicationEvent communicationEvent = ((CommunicationEventAdded) Scheduler.execute(command).data()).getAddedCommunicationEvent();
		
		if (communicationEvent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEvent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEvent could not be created.");
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
	public boolean updateCommunicationEvent(HttpServletRequest request) throws Exception {

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

		CommunicationEvent communicationEventToBeUpdated = new CommunicationEvent();

		try {
			communicationEventToBeUpdated = CommunicationEventMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEvent(communicationEventToBeUpdated, communicationEventToBeUpdated.getCommunicationEventId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateCommunicationEvent(@RequestBody CommunicationEvent communicationEventToBeUpdated,
			@PathVariable String communicationEventId) throws Exception {

		communicationEventToBeUpdated.setCommunicationEventId(communicationEventId);

		UpdateCommunicationEvent command = new UpdateCommunicationEvent(communicationEventToBeUpdated);

		try {
			if(((CommunicationEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventId", communicationEventId);
		try {

			Object foundCommunicationEvent = findCommunicationEventsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEvent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventId}")
	public ResponseEntity<Object> deleteCommunicationEventByIdUpdated(@PathVariable String communicationEventId) throws Exception {
		DeleteCommunicationEvent command = new DeleteCommunicationEvent(communicationEventId);

		try {
			if (((CommunicationEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEvent could not be deleted");

	}

}
