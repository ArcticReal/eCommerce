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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<EmplLeaveType>> findEmplLeaveTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplLeaveTypesBy query = new FindEmplLeaveTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplLeaveType> emplLeaveTypes =((EmplLeaveTypeFound) Scheduler.execute(query).data()).getEmplLeaveTypes();

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
	public ResponseEntity<EmplLeaveType> createEmplLeaveType(HttpServletRequest request) throws Exception {

		EmplLeaveType emplLeaveTypeToBeAdded = new EmplLeaveType();
		try {
			emplLeaveTypeToBeAdded = EmplLeaveTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<EmplLeaveType> createEmplLeaveType(@RequestBody EmplLeaveType emplLeaveTypeToBeAdded) throws Exception {

		AddEmplLeaveType command = new AddEmplLeaveType(emplLeaveTypeToBeAdded);
		EmplLeaveType emplLeaveType = ((EmplLeaveTypeAdded) Scheduler.execute(command).data()).getAddedEmplLeaveType();
		
		if (emplLeaveType != null) 
			return successful(emplLeaveType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateEmplLeaveType(@RequestBody EmplLeaveType emplLeaveTypeToBeUpdated,
			@PathVariable String leaveTypeId) throws Exception {

		emplLeaveTypeToBeUpdated.setLeaveTypeId(leaveTypeId);

		UpdateEmplLeaveType command = new UpdateEmplLeaveType(emplLeaveTypeToBeUpdated);

		try {
			if(((EmplLeaveTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplLeaveTypeId}")
	public ResponseEntity<EmplLeaveType> findById(@PathVariable String emplLeaveTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplLeaveTypeId", emplLeaveTypeId);
		try {

			List<EmplLeaveType> foundEmplLeaveType = findEmplLeaveTypesBy(requestParams).getBody();
			if(foundEmplLeaveType.size()==1){				return successful(foundEmplLeaveType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplLeaveTypeId}")
	public ResponseEntity<String> deleteEmplLeaveTypeByIdUpdated(@PathVariable String emplLeaveTypeId) throws Exception {
		DeleteEmplLeaveType command = new DeleteEmplLeaveType(emplLeaveTypeId);

		try {
			if (((EmplLeaveTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
