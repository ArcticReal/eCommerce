package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.control.week;

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
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.week.AddTechDataCalendarWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.week.DeleteTechDataCalendarWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.week.UpdateTechDataCalendarWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.week.TechDataCalendarWeekUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.week.TechDataCalendarWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query.week.FindTechDataCalendarWeeksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/manufacturing/techDataCalendar/techDataCalendarWeeks")
public class TechDataCalendarWeekController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TechDataCalendarWeekController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TechDataCalendarWeek
	 * @return a List with the TechDataCalendarWeeks
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TechDataCalendarWeek>> findTechDataCalendarWeeksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarWeeksBy query = new FindTechDataCalendarWeeksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendarWeek> techDataCalendarWeeks =((TechDataCalendarWeekFound) Scheduler.execute(query).data()).getTechDataCalendarWeeks();

		return ResponseEntity.ok().body(techDataCalendarWeeks);

	}

	/**
	 * creates a new TechDataCalendarWeek entry in the ofbiz database
	 * 
	 * @param techDataCalendarWeekToBeAdded
	 *            the TechDataCalendarWeek thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TechDataCalendarWeek> createTechDataCalendarWeek(@RequestBody TechDataCalendarWeek techDataCalendarWeekToBeAdded) throws Exception {

		AddTechDataCalendarWeek command = new AddTechDataCalendarWeek(techDataCalendarWeekToBeAdded);
		TechDataCalendarWeek techDataCalendarWeek = ((TechDataCalendarWeekAdded) Scheduler.execute(command).data()).getAddedTechDataCalendarWeek();
		
		if (techDataCalendarWeek != null) 
			return successful(techDataCalendarWeek);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TechDataCalendarWeek with the specific Id
	 * 
	 * @param techDataCalendarWeekToBeUpdated
	 *            the TechDataCalendarWeek thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{calendarWeekId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTechDataCalendarWeek(@RequestBody TechDataCalendarWeek techDataCalendarWeekToBeUpdated,
			@PathVariable String calendarWeekId) throws Exception {

		techDataCalendarWeekToBeUpdated.setCalendarWeekId(calendarWeekId);

		UpdateTechDataCalendarWeek command = new UpdateTechDataCalendarWeek(techDataCalendarWeekToBeUpdated);

		try {
			if(((TechDataCalendarWeekUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{techDataCalendarWeekId}")
	public ResponseEntity<TechDataCalendarWeek> findById(@PathVariable String techDataCalendarWeekId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarWeekId", techDataCalendarWeekId);
		try {

			List<TechDataCalendarWeek> foundTechDataCalendarWeek = findTechDataCalendarWeeksBy(requestParams).getBody();
			if(foundTechDataCalendarWeek.size()==1){				return successful(foundTechDataCalendarWeek.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{techDataCalendarWeekId}")
	public ResponseEntity<String> deleteTechDataCalendarWeekByIdUpdated(@PathVariable String techDataCalendarWeekId) throws Exception {
		DeleteTechDataCalendarWeek command = new DeleteTechDataCalendarWeek(techDataCalendarWeekId);

		try {
			if (((TechDataCalendarWeekDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
