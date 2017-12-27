package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.control.excWeek;

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
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excWeek.AddTechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excWeek.DeleteTechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.command.excWeek.UpdateTechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek.TechDataCalendarExcWeekAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek.TechDataCalendarExcWeekDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek.TechDataCalendarExcWeekFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.event.excWeek.TechDataCalendarExcWeekUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.excWeek.TechDataCalendarExcWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.query.excWeek.FindTechDataCalendarExcWeeksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/manufacturing/techDataCalendar/techDataCalendarExcWeeks")
public class TechDataCalendarExcWeekController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TechDataCalendarExcWeekController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TechDataCalendarExcWeek
	 * @return a List with the TechDataCalendarExcWeeks
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TechDataCalendarExcWeek>> findTechDataCalendarExcWeeksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarExcWeeksBy query = new FindTechDataCalendarExcWeeksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendarExcWeek> techDataCalendarExcWeeks =((TechDataCalendarExcWeekFound) Scheduler.execute(query).data()).getTechDataCalendarExcWeeks();

		return ResponseEntity.ok().body(techDataCalendarExcWeeks);

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
	public ResponseEntity<TechDataCalendarExcWeek> createTechDataCalendarExcWeek(HttpServletRequest request) throws Exception {

		TechDataCalendarExcWeek techDataCalendarExcWeekToBeAdded = new TechDataCalendarExcWeek();
		try {
			techDataCalendarExcWeekToBeAdded = TechDataCalendarExcWeekMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createTechDataCalendarExcWeek(techDataCalendarExcWeekToBeAdded);

	}

	/**
	 * creates a new TechDataCalendarExcWeek entry in the ofbiz database
	 * 
	 * @param techDataCalendarExcWeekToBeAdded
	 *            the TechDataCalendarExcWeek thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TechDataCalendarExcWeek> createTechDataCalendarExcWeek(@RequestBody TechDataCalendarExcWeek techDataCalendarExcWeekToBeAdded) throws Exception {

		AddTechDataCalendarExcWeek command = new AddTechDataCalendarExcWeek(techDataCalendarExcWeekToBeAdded);
		TechDataCalendarExcWeek techDataCalendarExcWeek = ((TechDataCalendarExcWeekAdded) Scheduler.execute(command).data()).getAddedTechDataCalendarExcWeek();
		
		if (techDataCalendarExcWeek != null) 
			return successful(techDataCalendarExcWeek);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TechDataCalendarExcWeek with the specific Id
	 * 
	 * @param techDataCalendarExcWeekToBeUpdated
	 *            the TechDataCalendarExcWeek thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTechDataCalendarExcWeek(@RequestBody TechDataCalendarExcWeek techDataCalendarExcWeekToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		techDataCalendarExcWeekToBeUpdated.setnull(null);

		UpdateTechDataCalendarExcWeek command = new UpdateTechDataCalendarExcWeek(techDataCalendarExcWeekToBeUpdated);

		try {
			if(((TechDataCalendarExcWeekUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{techDataCalendarExcWeekId}")
	public ResponseEntity<TechDataCalendarExcWeek> findById(@PathVariable String techDataCalendarExcWeekId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarExcWeekId", techDataCalendarExcWeekId);
		try {

			List<TechDataCalendarExcWeek> foundTechDataCalendarExcWeek = findTechDataCalendarExcWeeksBy(requestParams).getBody();
			if(foundTechDataCalendarExcWeek.size()==1){				return successful(foundTechDataCalendarExcWeek.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{techDataCalendarExcWeekId}")
	public ResponseEntity<String> deleteTechDataCalendarExcWeekByIdUpdated(@PathVariable String techDataCalendarExcWeekId) throws Exception {
		DeleteTechDataCalendarExcWeek command = new DeleteTechDataCalendarExcWeek(techDataCalendarExcWeekId);

		try {
			if (((TechDataCalendarExcWeekDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
