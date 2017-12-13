package com.skytala.eCommerce.domain.humanres.relations.emplLeave.control.type;

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
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.type.AddEmplLeaveType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.type.DeleteEmplLeaveType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.type.UpdateEmplLeaveType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type.EmplLeaveTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type.EmplLeaveTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type.EmplLeaveTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.type.EmplLeaveTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.type.EmplLeaveTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.type.EmplLeaveType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.query.type.FindEmplLeaveTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/emplLeave/emplLeaveTypes")
public class EmplLeaveTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplLeaveTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplLeaveType
	 * @return a List with the EmplLeaveTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findEmplLeaveTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplLeaveTypesBy query = new FindEmplLeaveTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplLeaveType> emplLeaveTypes =((EmplLeaveTypeFound) Scheduler.execute(query).data()).getEmplLeaveTypes();

		if (emplLeaveTypes.size() == 1) {
			return ResponseEntity.ok().body(emplLeaveTypes.get(0));
		}

		return ResponseEntity.ok().body(emplLeaveTypes);

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
	public ResponseEntity<Object> createEmplLeaveType(HttpServletRequest request) throws Exception {

		EmplLeaveType emplLeaveTypeToBeAdded = new EmplLeaveType();
		try {
			emplLeaveTypeToBeAdded = EmplLeaveTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplLeaveType(emplLeaveTypeToBeAdded);

	}

	/**
	 * creates a new EmplLeaveType entry in the ofbiz database
	 * 
	 * @param emplLeaveTypeToBeAdded
	 *            the EmplLeaveType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplLeaveType(@RequestBody EmplLeaveType emplLeaveTypeToBeAdded) throws Exception {

		AddEmplLeaveType command = new AddEmplLeaveType(emplLeaveTypeToBeAdded);
		EmplLeaveType emplLeaveType = ((EmplLeaveTypeAdded) Scheduler.execute(command).data()).getAddedEmplLeaveType();
		
		if (emplLeaveType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplLeaveType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplLeaveType could not be created.");
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
	public boolean updateEmplLeaveType(HttpServletRequest request) throws Exception {

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

		EmplLeaveType emplLeaveTypeToBeUpdated = new EmplLeaveType();

		try {
			emplLeaveTypeToBeUpdated = EmplLeaveTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplLeaveType(emplLeaveTypeToBeUpdated, emplLeaveTypeToBeUpdated.getLeaveTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmplLeaveType with the specific Id
	 * 
	 * @param emplLeaveTypeToBeUpdated
	 *            the EmplLeaveType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{leaveTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmplLeaveType(@RequestBody EmplLeaveType emplLeaveTypeToBeUpdated,
			@PathVariable String leaveTypeId) throws Exception {

		emplLeaveTypeToBeUpdated.setLeaveTypeId(leaveTypeId);

		UpdateEmplLeaveType command = new UpdateEmplLeaveType(emplLeaveTypeToBeUpdated);

		try {
			if(((EmplLeaveTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{emplLeaveTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String emplLeaveTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplLeaveTypeId", emplLeaveTypeId);
		try {

			Object foundEmplLeaveType = findEmplLeaveTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplLeaveType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{emplLeaveTypeId}")
	public ResponseEntity<Object> deleteEmplLeaveTypeByIdUpdated(@PathVariable String emplLeaveTypeId) throws Exception {
		DeleteEmplLeaveType command = new DeleteEmplLeaveType(emplLeaveTypeId);

		try {
			if (((EmplLeaveTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplLeaveType could not be deleted");

	}

}
