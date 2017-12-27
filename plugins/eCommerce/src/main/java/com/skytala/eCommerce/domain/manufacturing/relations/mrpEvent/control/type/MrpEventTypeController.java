package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.control.type;

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
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.type.AddMrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.type.DeleteMrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command.type.UpdateMrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type.MrpEventTypeAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type.MrpEventTypeDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type.MrpEventTypeFound;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type.MrpEventTypeUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.type.MrpEventTypeMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type.MrpEventType;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.query.type.FindMrpEventTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/manufacturing/mrpEvent/mrpEventTypes")
public class MrpEventTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MrpEventTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MrpEventType
	 * @return a List with the MrpEventTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<MrpEventType>> findMrpEventTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMrpEventTypesBy query = new FindMrpEventTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MrpEventType> mrpEventTypes =((MrpEventTypeFound) Scheduler.execute(query).data()).getMrpEventTypes();

		return ResponseEntity.ok().body(mrpEventTypes);

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
	public ResponseEntity<MrpEventType> createMrpEventType(HttpServletRequest request) throws Exception {

		MrpEventType mrpEventTypeToBeAdded = new MrpEventType();
		try {
			mrpEventTypeToBeAdded = MrpEventTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createMrpEventType(mrpEventTypeToBeAdded);

	}

	/**
	 * creates a new MrpEventType entry in the ofbiz database
	 * 
	 * @param mrpEventTypeToBeAdded
	 *            the MrpEventType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MrpEventType> createMrpEventType(@RequestBody MrpEventType mrpEventTypeToBeAdded) throws Exception {

		AddMrpEventType command = new AddMrpEventType(mrpEventTypeToBeAdded);
		MrpEventType mrpEventType = ((MrpEventTypeAdded) Scheduler.execute(command).data()).getAddedMrpEventType();
		
		if (mrpEventType != null) 
			return successful(mrpEventType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MrpEventType with the specific Id
	 * 
	 * @param mrpEventTypeToBeUpdated
	 *            the MrpEventType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{mrpEventTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMrpEventType(@RequestBody MrpEventType mrpEventTypeToBeUpdated,
			@PathVariable String mrpEventTypeId) throws Exception {

		mrpEventTypeToBeUpdated.setMrpEventTypeId(mrpEventTypeId);

		UpdateMrpEventType command = new UpdateMrpEventType(mrpEventTypeToBeUpdated);

		try {
			if(((MrpEventTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{mrpEventTypeId}")
	public ResponseEntity<MrpEventType> findById(@PathVariable String mrpEventTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("mrpEventTypeId", mrpEventTypeId);
		try {

			List<MrpEventType> foundMrpEventType = findMrpEventTypesBy(requestParams).getBody();
			if(foundMrpEventType.size()==1){				return successful(foundMrpEventType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{mrpEventTypeId}")
	public ResponseEntity<String> deleteMrpEventTypeByIdUpdated(@PathVariable String mrpEventTypeId) throws Exception {
		DeleteMrpEventType command = new DeleteMrpEventType(mrpEventTypeId);

		try {
			if (((MrpEventTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
