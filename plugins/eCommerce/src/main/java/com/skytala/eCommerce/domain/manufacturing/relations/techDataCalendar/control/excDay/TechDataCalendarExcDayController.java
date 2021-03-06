package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.control.excDay;

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
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excDay.AddTechDataCalendarExcDay;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excDay.DeleteTechDataCalendarExcDay;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excDay.UpdateTechDataCalendarExcDay;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay.TechDataCalendarExcDayAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay.TechDataCalendarExcDayDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay.TechDataCalendarExcDayFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excDay.TechDataCalendarExcDayUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.excDay.TechDataCalendarExcDayMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excDay.TechDataCalendarExcDay;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query.excDay.FindTechDataCalendarExcDaysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/manufacturing/techDataCalendar/techDataCalendarExcDays")
public class TechDataCalendarExcDayController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TechDataCalendarExcDayController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TechDataCalendarExcDay
	 * @return a List with the TechDataCalendarExcDays
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TechDataCalendarExcDay>> findTechDataCalendarExcDaysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarExcDaysBy query = new FindTechDataCalendarExcDaysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendarExcDay> techDataCalendarExcDays =((TechDataCalendarExcDayFound) Scheduler.execute(query).data()).getTechDataCalendarExcDays();

		return ResponseEntity.ok().body(techDataCalendarExcDays);

	}

	/**
	 * creates a new TechDataCalendarExcDay entry in the ofbiz database
	 * 
	 * @param techDataCalendarExcDayToBeAdded
	 *            the TechDataCalendarExcDay thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TechDataCalendarExcDay> createTechDataCalendarExcDay(@RequestBody TechDataCalendarExcDay techDataCalendarExcDayToBeAdded) throws Exception {

		AddTechDataCalendarExcDay command = new AddTechDataCalendarExcDay(techDataCalendarExcDayToBeAdded);
		TechDataCalendarExcDay techDataCalendarExcDay = ((TechDataCalendarExcDayAdded) Scheduler.execute(command).data()).getAddedTechDataCalendarExcDay();
		
		if (techDataCalendarExcDay != null) 
			return successful(techDataCalendarExcDay);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TechDataCalendarExcDay with the specific Id
	 * 
	 * @param techDataCalendarExcDayToBeUpdated
	 *            the TechDataCalendarExcDay thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTechDataCalendarExcDay(@RequestBody TechDataCalendarExcDay techDataCalendarExcDayToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		techDataCalendarExcDayToBeUpdated.setnull(null);

		UpdateTechDataCalendarExcDay command = new UpdateTechDataCalendarExcDay(techDataCalendarExcDayToBeUpdated);

		try {
			if(((TechDataCalendarExcDayUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{techDataCalendarExcDayId}")
	public ResponseEntity<TechDataCalendarExcDay> findById(@PathVariable String techDataCalendarExcDayId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarExcDayId", techDataCalendarExcDayId);
		try {

			List<TechDataCalendarExcDay> foundTechDataCalendarExcDay = findTechDataCalendarExcDaysBy(requestParams).getBody();
			if(foundTechDataCalendarExcDay.size()==1){				return successful(foundTechDataCalendarExcDay.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{techDataCalendarExcDayId}")
	public ResponseEntity<String> deleteTechDataCalendarExcDayByIdUpdated(@PathVariable String techDataCalendarExcDayId) throws Exception {
		DeleteTechDataCalendarExcDay command = new DeleteTechDataCalendarExcDay(techDataCalendarExcDayId);

		try {
			if (((TechDataCalendarExcDayDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
