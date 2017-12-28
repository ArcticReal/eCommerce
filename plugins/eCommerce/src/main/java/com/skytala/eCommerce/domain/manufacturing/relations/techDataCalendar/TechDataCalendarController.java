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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<TechDataCalendar>> findTechDataCalendarsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarsBy query = new FindTechDataCalendarsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendar> techDataCalendars =((TechDataCalendarFound) Scheduler.execute(query).data()).getTechDataCalendars();

		return ResponseEntity.ok().body(techDataCalendars);

	}

	/**
	 * creates a new TechDataCalendar entry in the ofbiz database
	 * 
	 * @param techDataCalendarToBeAdded
	 *            the TechDataCalendar thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TechDataCalendar> createTechDataCalendar(@RequestBody TechDataCalendar techDataCalendarToBeAdded) throws Exception {

		AddTechDataCalendar command = new AddTechDataCalendar(techDataCalendarToBeAdded);
		TechDataCalendar techDataCalendar = ((TechDataCalendarAdded) Scheduler.execute(command).data()).getAddedTechDataCalendar();
		
		if (techDataCalendar != null) 
			return successful(techDataCalendar);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTechDataCalendar(@RequestBody TechDataCalendar techDataCalendarToBeUpdated,
			@PathVariable String calendarId) throws Exception {

		techDataCalendarToBeUpdated.setCalendarId(calendarId);

		UpdateTechDataCalendar command = new UpdateTechDataCalendar(techDataCalendarToBeUpdated);

		try {
			if(((TechDataCalendarUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{techDataCalendarId}")
	public ResponseEntity<TechDataCalendar> findById(@PathVariable String techDataCalendarId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarId", techDataCalendarId);
		try {

			List<TechDataCalendar> foundTechDataCalendar = findTechDataCalendarsBy(requestParams).getBody();
			if(foundTechDataCalendar.size()==1){				return successful(foundTechDataCalendar.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{techDataCalendarId}")
	public ResponseEntity<String> deleteTechDataCalendarByIdUpdated(@PathVariable String techDataCalendarId) throws Exception {
		DeleteTechDataCalendar command = new DeleteTechDataCalendar(techDataCalendarId);

		try {
			if (((TechDataCalendarDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
