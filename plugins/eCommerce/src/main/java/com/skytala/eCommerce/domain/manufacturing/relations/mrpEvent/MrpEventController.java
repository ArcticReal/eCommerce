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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/mrpEvents")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMrpEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMrpEventsBy query = new FindMrpEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MrpEvent> mrpEvents =((MrpEventFound) Scheduler.execute(query).data()).getMrpEvents();

		if (mrpEvents.size() == 1) {
			return ResponseEntity.ok().body(mrpEvents.get(0));
		}

		return ResponseEntity.ok().body(mrpEvents);

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
	public ResponseEntity<Object> createMrpEvent(HttpServletRequest request) throws Exception {

		MrpEvent mrpEventToBeAdded = new MrpEvent();
		try {
			mrpEventToBeAdded = MrpEventMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMrpEvent(mrpEventToBeAdded);

	}

	/**
	 * creates a new MrpEvent entry in the ofbiz database
	 * 
	 * @param mrpEventToBeAdded
	 *            the MrpEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMrpEvent(@RequestBody MrpEvent mrpEventToBeAdded) throws Exception {

		AddMrpEvent command = new AddMrpEvent(mrpEventToBeAdded);
		MrpEvent mrpEvent = ((MrpEventAdded) Scheduler.execute(command).data()).getAddedMrpEvent();
		
		if (mrpEvent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(mrpEvent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MrpEvent could not be created.");
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
	public boolean updateMrpEvent(HttpServletRequest request) throws Exception {

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

		MrpEvent mrpEventToBeUpdated = new MrpEvent();

		try {
			mrpEventToBeUpdated = MrpEventMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMrpEvent(mrpEventToBeUpdated, mrpEventToBeUpdated.getMrpId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateMrpEvent(@RequestBody MrpEvent mrpEventToBeUpdated,
			@PathVariable String mrpId) throws Exception {

		mrpEventToBeUpdated.setMrpId(mrpId);

		UpdateMrpEvent command = new UpdateMrpEvent(mrpEventToBeUpdated);

		try {
			if(((MrpEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{mrpEventId}")
	public ResponseEntity<Object> findById(@PathVariable String mrpEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mrpEventId", mrpEventId);
		try {

			Object foundMrpEvent = findMrpEventsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMrpEvent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{mrpEventId}")
	public ResponseEntity<Object> deleteMrpEventByIdUpdated(@PathVariable String mrpEventId) throws Exception {
		DeleteMrpEvent command = new DeleteMrpEvent(mrpEventId);

		try {
			if (((MrpEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MrpEvent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/mrpEvent/\" plus one of the following: "
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
