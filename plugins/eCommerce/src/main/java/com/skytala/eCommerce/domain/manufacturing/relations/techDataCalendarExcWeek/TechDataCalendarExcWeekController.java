package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.command.AddTechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.command.DeleteTechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.command.UpdateTechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event.TechDataCalendarExcWeekAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event.TechDataCalendarExcWeekDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event.TechDataCalendarExcWeekFound;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.event.TechDataCalendarExcWeekUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.mapper.TechDataCalendarExcWeekMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.model.TechDataCalendarExcWeek;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendarExcWeek.query.FindTechDataCalendarExcWeeksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/techDataCalendarExcWeeks")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTechDataCalendarExcWeeksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTechDataCalendarExcWeeksBy query = new FindTechDataCalendarExcWeeksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TechDataCalendarExcWeek> techDataCalendarExcWeeks =((TechDataCalendarExcWeekFound) Scheduler.execute(query).data()).getTechDataCalendarExcWeeks();

		if (techDataCalendarExcWeeks.size() == 1) {
			return ResponseEntity.ok().body(techDataCalendarExcWeeks.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTechDataCalendarExcWeek(HttpServletRequest request) throws Exception {

		TechDataCalendarExcWeek techDataCalendarExcWeekToBeAdded = new TechDataCalendarExcWeek();
		try {
			techDataCalendarExcWeekToBeAdded = TechDataCalendarExcWeekMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createTechDataCalendarExcWeek(@RequestBody TechDataCalendarExcWeek techDataCalendarExcWeekToBeAdded) throws Exception {

		AddTechDataCalendarExcWeek command = new AddTechDataCalendarExcWeek(techDataCalendarExcWeekToBeAdded);
		TechDataCalendarExcWeek techDataCalendarExcWeek = ((TechDataCalendarExcWeekAdded) Scheduler.execute(command).data()).getAddedTechDataCalendarExcWeek();
		
		if (techDataCalendarExcWeek != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(techDataCalendarExcWeek);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TechDataCalendarExcWeek could not be created.");
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
	public boolean updateTechDataCalendarExcWeek(HttpServletRequest request) throws Exception {

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

		TechDataCalendarExcWeek techDataCalendarExcWeekToBeUpdated = new TechDataCalendarExcWeek();

		try {
			techDataCalendarExcWeekToBeUpdated = TechDataCalendarExcWeekMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTechDataCalendarExcWeek(techDataCalendarExcWeekToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTechDataCalendarExcWeek(@RequestBody TechDataCalendarExcWeek techDataCalendarExcWeekToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		techDataCalendarExcWeekToBeUpdated.setnull(null);

		UpdateTechDataCalendarExcWeek command = new UpdateTechDataCalendarExcWeek(techDataCalendarExcWeekToBeUpdated);

		try {
			if(((TechDataCalendarExcWeekUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{techDataCalendarExcWeekId}")
	public ResponseEntity<Object> findById(@PathVariable String techDataCalendarExcWeekId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("techDataCalendarExcWeekId", techDataCalendarExcWeekId);
		try {

			Object foundTechDataCalendarExcWeek = findTechDataCalendarExcWeeksBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTechDataCalendarExcWeek);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{techDataCalendarExcWeekId}")
	public ResponseEntity<Object> deleteTechDataCalendarExcWeekByIdUpdated(@PathVariable String techDataCalendarExcWeekId) throws Exception {
		DeleteTechDataCalendarExcWeek command = new DeleteTechDataCalendarExcWeek(techDataCalendarExcWeekId);

		try {
			if (((TechDataCalendarExcWeekDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TechDataCalendarExcWeek could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/techDataCalendarExcWeek/\" plus one of the following: "
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
