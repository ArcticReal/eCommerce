package com.skytala.eCommerce.domain.humanres.relations.emplLeave;

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
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.AddEmplLeave;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.DeleteEmplLeave;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.UpdateEmplLeave;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveFound;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.EmplLeaveMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.query.FindEmplLeavesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/emplLeaves")
public class EmplLeaveController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplLeaveController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplLeave
	 * @return a List with the EmplLeaves
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findEmplLeavesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplLeavesBy query = new FindEmplLeavesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplLeave> emplLeaves =((EmplLeaveFound) Scheduler.execute(query).data()).getEmplLeaves();

		if (emplLeaves.size() == 1) {
			return ResponseEntity.ok().body(emplLeaves.get(0));
		}

		return ResponseEntity.ok().body(emplLeaves);

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
	public ResponseEntity<Object> createEmplLeave(HttpServletRequest request) throws Exception {

		EmplLeave emplLeaveToBeAdded = new EmplLeave();
		try {
			emplLeaveToBeAdded = EmplLeaveMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplLeave(emplLeaveToBeAdded);

	}

	/**
	 * creates a new EmplLeave entry in the ofbiz database
	 * 
	 * @param emplLeaveToBeAdded
	 *            the EmplLeave thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplLeave(@RequestBody EmplLeave emplLeaveToBeAdded) throws Exception {

		AddEmplLeave command = new AddEmplLeave(emplLeaveToBeAdded);
		EmplLeave emplLeave = ((EmplLeaveAdded) Scheduler.execute(command).data()).getAddedEmplLeave();
		
		if (emplLeave != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplLeave);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplLeave could not be created.");
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
	public boolean updateEmplLeave(HttpServletRequest request) throws Exception {

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

		EmplLeave emplLeaveToBeUpdated = new EmplLeave();

		try {
			emplLeaveToBeUpdated = EmplLeaveMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplLeave(emplLeaveToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmplLeave with the specific Id
	 * 
	 * @param emplLeaveToBeUpdated
	 *            the EmplLeave thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmplLeave(@RequestBody EmplLeave emplLeaveToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplLeaveToBeUpdated.setnull(null);

		UpdateEmplLeave command = new UpdateEmplLeave(emplLeaveToBeUpdated);

		try {
			if(((EmplLeaveUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{emplLeaveId}")
	public ResponseEntity<Object> findById(@PathVariable String emplLeaveId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplLeaveId", emplLeaveId);
		try {

			Object foundEmplLeave = findEmplLeavesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplLeave);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{emplLeaveId}")
	public ResponseEntity<Object> deleteEmplLeaveByIdUpdated(@PathVariable String emplLeaveId) throws Exception {
		DeleteEmplLeave command = new DeleteEmplLeave(emplLeaveId);

		try {
			if (((EmplLeaveDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplLeave could not be deleted");

	}

}
