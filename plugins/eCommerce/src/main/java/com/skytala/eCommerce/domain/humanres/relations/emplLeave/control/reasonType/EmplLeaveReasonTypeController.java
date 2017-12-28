package com.skytala.eCommerce.domain.humanres.relations.emplLeave.control.reasonType;

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
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.reasonType.AddEmplLeaveReasonType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.reasonType.DeleteEmplLeaveReasonType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.command.reasonType.UpdateEmplLeaveReasonType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType.EmplLeaveReasonTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType.EmplLeaveReasonTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType.EmplLeaveReasonTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType.EmplLeaveReasonTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.reasonType.EmplLeaveReasonTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType.EmplLeaveReasonType;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.query.reasonType.FindEmplLeaveReasonTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplLeave/emplLeaveReasonTypes")
public class EmplLeaveReasonTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplLeaveReasonTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplLeaveReasonType
	 * @return a List with the EmplLeaveReasonTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplLeaveReasonType>> findEmplLeaveReasonTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplLeaveReasonTypesBy query = new FindEmplLeaveReasonTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplLeaveReasonType> emplLeaveReasonTypes =((EmplLeaveReasonTypeFound) Scheduler.execute(query).data()).getEmplLeaveReasonTypes();

		return ResponseEntity.ok().body(emplLeaveReasonTypes);

	}

	/**
	 * creates a new EmplLeaveReasonType entry in the ofbiz database
	 * 
	 * @param emplLeaveReasonTypeToBeAdded
	 *            the EmplLeaveReasonType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplLeaveReasonType> createEmplLeaveReasonType(@RequestBody EmplLeaveReasonType emplLeaveReasonTypeToBeAdded) throws Exception {

		AddEmplLeaveReasonType command = new AddEmplLeaveReasonType(emplLeaveReasonTypeToBeAdded);
		EmplLeaveReasonType emplLeaveReasonType = ((EmplLeaveReasonTypeAdded) Scheduler.execute(command).data()).getAddedEmplLeaveReasonType();
		
		if (emplLeaveReasonType != null) 
			return successful(emplLeaveReasonType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplLeaveReasonType with the specific Id
	 * 
	 * @param emplLeaveReasonTypeToBeUpdated
	 *            the EmplLeaveReasonType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emplLeaveReasonTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplLeaveReasonType(@RequestBody EmplLeaveReasonType emplLeaveReasonTypeToBeUpdated,
			@PathVariable String emplLeaveReasonTypeId) throws Exception {

		emplLeaveReasonTypeToBeUpdated.setEmplLeaveReasonTypeId(emplLeaveReasonTypeId);

		UpdateEmplLeaveReasonType command = new UpdateEmplLeaveReasonType(emplLeaveReasonTypeToBeUpdated);

		try {
			if(((EmplLeaveReasonTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplLeaveReasonTypeId}")
	public ResponseEntity<EmplLeaveReasonType> findById(@PathVariable String emplLeaveReasonTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplLeaveReasonTypeId", emplLeaveReasonTypeId);
		try {

			List<EmplLeaveReasonType> foundEmplLeaveReasonType = findEmplLeaveReasonTypesBy(requestParams).getBody();
			if(foundEmplLeaveReasonType.size()==1){				return successful(foundEmplLeaveReasonType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplLeaveReasonTypeId}")
	public ResponseEntity<String> deleteEmplLeaveReasonTypeByIdUpdated(@PathVariable String emplLeaveReasonTypeId) throws Exception {
		DeleteEmplLeaveReasonType command = new DeleteEmplLeaveReasonType(emplLeaveReasonTypeId);

		try {
			if (((EmplLeaveReasonTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
