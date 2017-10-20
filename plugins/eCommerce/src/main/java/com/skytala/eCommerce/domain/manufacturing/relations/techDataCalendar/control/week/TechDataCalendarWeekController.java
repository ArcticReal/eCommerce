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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/techDataCalendarWeeks")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTechDataCalendarWeeksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarWeeksBy query = new FindTechDataCalendarWeeksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendarWeek> techDataCalendarWeeks =((TechDataCalendarWeekFound) Scheduler.execute(query).data()).getTechDataCalendarWeeks();

		if (techDataCalendarWeeks.size() == 1) {
			return ResponseEntity.ok().body(techDataCalendarWeeks.get(0));
		}

		return ResponseEntity.ok().body(techDataCalendarWeeks);

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
	public ResponseEntity<Object> createTechDataCalendarWeek(HttpServletRequest request) throws Exception {

		TechDataCalendarWeek techDataCalendarWeekToBeAdded = new TechDataCalendarWeek();
		try {
			techDataCalendarWeekToBeAdded = TechDataCalendarWeekMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTechDataCalendarWeek(techDataCalendarWeekToBeAdded);

	}

	/**
	 * creates a new TechDataCalendarWeek entry in the ofbiz database
	 * 
	 * @param techDataCalendarWeekToBeAdded
	 *            the TechDataCalendarWeek thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTechDataCalendarWeek(@RequestBody TechDataCalendarWeek techDataCalendarWeekToBeAdded) throws Exception {

		AddTechDataCalendarWeek command = new AddTechDataCalendarWeek(techDataCalendarWeekToBeAdded);
		TechDataCalendarWeek techDataCalendarWeek = ((TechDataCalendarWeekAdded) Scheduler.execute(command).data()).getAddedTechDataCalendarWeek();
		
		if (techDataCalendarWeek != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(techDataCalendarWeek);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TechDataCalendarWeek could not be created.");
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
	public boolean updateTechDataCalendarWeek(HttpServletRequest request) throws Exception {

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

		TechDataCalendarWeek techDataCalendarWeekToBeUpdated = new TechDataCalendarWeek();

		try {
			techDataCalendarWeekToBeUpdated = TechDataCalendarWeekMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTechDataCalendarWeek(techDataCalendarWeekToBeUpdated, techDataCalendarWeekToBeUpdated.getCalendarWeekId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTechDataCalendarWeek(@RequestBody TechDataCalendarWeek techDataCalendarWeekToBeUpdated,
			@PathVariable String calendarWeekId) throws Exception {

		techDataCalendarWeekToBeUpdated.setCalendarWeekId(calendarWeekId);

		UpdateTechDataCalendarWeek command = new UpdateTechDataCalendarWeek(techDataCalendarWeekToBeUpdated);

		try {
			if(((TechDataCalendarWeekUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{techDataCalendarWeekId}")
	public ResponseEntity<Object> findById(@PathVariable String techDataCalendarWeekId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarWeekId", techDataCalendarWeekId);
		try {

			Object foundTechDataCalendarWeek = findTechDataCalendarWeeksBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTechDataCalendarWeek);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{techDataCalendarWeekId}")
	public ResponseEntity<Object> deleteTechDataCalendarWeekByIdUpdated(@PathVariable String techDataCalendarWeekId) throws Exception {
		DeleteTechDataCalendarWeek command = new DeleteTechDataCalendarWeek(techDataCalendarWeekId);

		try {
			if (((TechDataCalendarWeekDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TechDataCalendarWeek could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/techDataCalendarWeek/\" plus one of the following: "
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
