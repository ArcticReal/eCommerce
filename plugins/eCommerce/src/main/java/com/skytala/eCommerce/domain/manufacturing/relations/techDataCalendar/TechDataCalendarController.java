package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar;

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
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.AddTechDataCalendar;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.DeleteTechDataCalendar;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.UpdateTechDataCalendar;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.TechDataCalendarUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.TechDataCalendarMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query.FindTechDataCalendarsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/manufacturing/techDataCalendars")
public class TechDataCalendarController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TechDataCalendarController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TechDataCalendar
	 * @return a List with the TechDataCalendars
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findTechDataCalendarsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarsBy query = new FindTechDataCalendarsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendar> techDataCalendars =((TechDataCalendarFound) Scheduler.execute(query).data()).getTechDataCalendars();

		if (techDataCalendars.size() == 1) {
			return ResponseEntity.ok().body(techDataCalendars.get(0));
		}

		return ResponseEntity.ok().body(techDataCalendars);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTechDataCalendar(HttpServletRequest request) throws Exception {

		TechDataCalendar techDataCalendarToBeAdded = new TechDataCalendar();
		try {
			techDataCalendarToBeAdded = TechDataCalendarMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTechDataCalendar(techDataCalendarToBeAdded);

	}

	/**
	 * creates a new TechDataCalendar entry in the ofbiz database
	 * 
	 * @param techDataCalendarToBeAdded
	 *            the TechDataCalendar thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTechDataCalendar(@RequestBody TechDataCalendar techDataCalendarToBeAdded) throws Exception {

		AddTechDataCalendar command = new AddTechDataCalendar(techDataCalendarToBeAdded);
		TechDataCalendar techDataCalendar = ((TechDataCalendarAdded) Scheduler.execute(command).data()).getAddedTechDataCalendar();
		
		if (techDataCalendar != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(techDataCalendar);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TechDataCalendar could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateTechDataCalendar(HttpServletRequest request) throws Exception {

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

		TechDataCalendar techDataCalendarToBeUpdated = new TechDataCalendar();

		try {
			techDataCalendarToBeUpdated = TechDataCalendarMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTechDataCalendar(techDataCalendarToBeUpdated, techDataCalendarToBeUpdated.getCalendarId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TechDataCalendar with the specific Id
	 * 
	 * @param techDataCalendarToBeUpdated
	 *            the TechDataCalendar thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{calendarId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTechDataCalendar(@RequestBody TechDataCalendar techDataCalendarToBeUpdated,
			@PathVariable String calendarId) throws Exception {

		techDataCalendarToBeUpdated.setCalendarId(calendarId);

		UpdateTechDataCalendar command = new UpdateTechDataCalendar(techDataCalendarToBeUpdated);

		try {
			if(((TechDataCalendarUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{techDataCalendarId}")
	public ResponseEntity<Object> findById(@PathVariable String techDataCalendarId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarId", techDataCalendarId);
		try {

			Object foundTechDataCalendar = findTechDataCalendarsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTechDataCalendar);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{techDataCalendarId}")
	public ResponseEntity<Object> deleteTechDataCalendarByIdUpdated(@PathVariable String techDataCalendarId) throws Exception {
		DeleteTechDataCalendar command = new DeleteTechDataCalendar(techDataCalendarId);

		try {
			if (((TechDataCalendarDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TechDataCalendar could not be deleted");

	}

}
