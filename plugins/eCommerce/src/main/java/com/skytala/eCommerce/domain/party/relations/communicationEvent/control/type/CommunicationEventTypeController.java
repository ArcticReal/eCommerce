package com.skytala.eCommerce.domain.party.relations.communicationEvent.control.type;

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
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.type.AddCommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.type.DeleteCommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.type.UpdateCommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type.CommunicationEventTypeAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type.CommunicationEventTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type.CommunicationEventTypeFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type.CommunicationEventTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.type.CommunicationEventTypeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.type.CommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.query.type.FindCommunicationEventTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/communicationEvent/communicationEventTypes")
public class CommunicationEventTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventType
	 * @return a List with the CommunicationEventTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventType>> findCommunicationEventTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventTypesBy query = new FindCommunicationEventTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventType> communicationEventTypes =((CommunicationEventTypeFound) Scheduler.execute(query).data()).getCommunicationEventTypes();

		return ResponseEntity.ok().body(communicationEventTypes);

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
	public ResponseEntity<CommunicationEventType> createCommunicationEventType(HttpServletRequest request) throws Exception {

		CommunicationEventType communicationEventTypeToBeAdded = new CommunicationEventType();
		try {
			communicationEventTypeToBeAdded = CommunicationEventTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCommunicationEventType(communicationEventTypeToBeAdded);

	}

	/**
	 * creates a new CommunicationEventType entry in the ofbiz database
	 * 
	 * @param communicationEventTypeToBeAdded
	 *            the CommunicationEventType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommunicationEventType> createCommunicationEventType(@RequestBody CommunicationEventType communicationEventTypeToBeAdded) throws Exception {

		AddCommunicationEventType command = new AddCommunicationEventType(communicationEventTypeToBeAdded);
		CommunicationEventType communicationEventType = ((CommunicationEventTypeAdded) Scheduler.execute(command).data()).getAddedCommunicationEventType();
		
		if (communicationEventType != null) 
			return successful(communicationEventType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommunicationEventType with the specific Id
	 * 
	 * @param communicationEventTypeToBeUpdated
	 *            the CommunicationEventType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{communicationEventTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommunicationEventType(@RequestBody CommunicationEventType communicationEventTypeToBeUpdated,
			@PathVariable String communicationEventTypeId) throws Exception {

		communicationEventTypeToBeUpdated.setCommunicationEventTypeId(communicationEventTypeId);

		UpdateCommunicationEventType command = new UpdateCommunicationEventType(communicationEventTypeToBeUpdated);

		try {
			if(((CommunicationEventTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventTypeId}")
	public ResponseEntity<CommunicationEventType> findById(@PathVariable String communicationEventTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventTypeId", communicationEventTypeId);
		try {

			List<CommunicationEventType> foundCommunicationEventType = findCommunicationEventTypesBy(requestParams).getBody();
			if(foundCommunicationEventType.size()==1){				return successful(foundCommunicationEventType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventTypeId}")
	public ResponseEntity<String> deleteCommunicationEventTypeByIdUpdated(@PathVariable String communicationEventTypeId) throws Exception {
		DeleteCommunicationEventType command = new DeleteCommunicationEventType(communicationEventTypeId);

		try {
			if (((CommunicationEventTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
