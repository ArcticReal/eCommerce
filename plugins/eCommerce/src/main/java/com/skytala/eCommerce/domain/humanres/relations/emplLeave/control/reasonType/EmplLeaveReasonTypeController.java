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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findEmplLeaveReasonTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplLeaveReasonTypesBy query = new FindEmplLeaveReasonTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplLeaveReasonType> emplLeaveReasonTypes =((EmplLeaveReasonTypeFound) Scheduler.execute(query).data()).getEmplLeaveReasonTypes();

		if (emplLeaveReasonTypes.size() == 1) {
			return ResponseEntity.ok().body(emplLeaveReasonTypes.get(0));
		}

		return ResponseEntity.ok().body(emplLeaveReasonTypes);

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
	public ResponseEntity<Object> createEmplLeaveReasonType(HttpServletRequest request) throws Exception {

		EmplLeaveReasonType emplLeaveReasonTypeToBeAdded = new EmplLeaveReasonType();
		try {
			emplLeaveReasonTypeToBeAdded = EmplLeaveReasonTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplLeaveReasonType(emplLeaveReasonTypeToBeAdded);

	}

	/**
	 * creates a new EmplLeaveReasonType entry in the ofbiz database
	 * 
	 * @param emplLeaveReasonTypeToBeAdded
	 *            the EmplLeaveReasonType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplLeaveReasonType(@RequestBody EmplLeaveReasonType emplLeaveReasonTypeToBeAdded) throws Exception {

		AddEmplLeaveReasonType command = new AddEmplLeaveReasonType(emplLeaveReasonTypeToBeAdded);
		EmplLeaveReasonType emplLeaveReasonType = ((EmplLeaveReasonTypeAdded) Scheduler.execute(command).data()).getAddedEmplLeaveReasonType();
		
		if (emplLeaveReasonType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplLeaveReasonType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplLeaveReasonType could not be created.");
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
	public boolean updateEmplLeaveReasonType(HttpServletRequest request) throws Exception {

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

		EmplLeaveReasonType emplLeaveReasonTypeToBeUpdated = new EmplLeaveReasonType();

		try {
			emplLeaveReasonTypeToBeUpdated = EmplLeaveReasonTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplLeaveReasonType(emplLeaveReasonTypeToBeUpdated, emplLeaveReasonTypeToBeUpdated.getEmplLeaveReasonTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateEmplLeaveReasonType(@RequestBody EmplLeaveReasonType emplLeaveReasonTypeToBeUpdated,
			@PathVariable String emplLeaveReasonTypeId) throws Exception {

		emplLeaveReasonTypeToBeUpdated.setEmplLeaveReasonTypeId(emplLeaveReasonTypeId);

		UpdateEmplLeaveReasonType command = new UpdateEmplLeaveReasonType(emplLeaveReasonTypeToBeUpdated);

		try {
			if(((EmplLeaveReasonTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{emplLeaveReasonTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String emplLeaveReasonTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplLeaveReasonTypeId", emplLeaveReasonTypeId);
		try {

			Object foundEmplLeaveReasonType = findEmplLeaveReasonTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplLeaveReasonType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{emplLeaveReasonTypeId}")
	public ResponseEntity<Object> deleteEmplLeaveReasonTypeByIdUpdated(@PathVariable String emplLeaveReasonTypeId) throws Exception {
		DeleteEmplLeaveReasonType command = new DeleteEmplLeaveReasonType(emplLeaveReasonTypeId);

		try {
			if (((EmplLeaveReasonTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplLeaveReasonType could not be deleted");

	}

}
