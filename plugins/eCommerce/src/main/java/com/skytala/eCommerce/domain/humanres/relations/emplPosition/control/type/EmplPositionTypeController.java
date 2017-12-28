package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.type;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.type.AddEmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.type.DeleteEmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.type.UpdateEmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type.EmplPositionTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type.EmplPositionTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type.EmplPositionTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type.EmplPositionTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.type.EmplPositionTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.type.FindEmplPositionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionTypes")
public class EmplPositionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionType
	 * @return a List with the EmplPositionTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionType>> findEmplPositionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionTypesBy query = new FindEmplPositionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionType> emplPositionTypes =((EmplPositionTypeFound) Scheduler.execute(query).data()).getEmplPositionTypes();

		return ResponseEntity.ok().body(emplPositionTypes);

	}

	/**
	 * creates a new EmplPositionType entry in the ofbiz database
	 * 
	 * @param emplPositionTypeToBeAdded
	 *            the EmplPositionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionType> createEmplPositionType(@RequestBody EmplPositionType emplPositionTypeToBeAdded) throws Exception {

		AddEmplPositionType command = new AddEmplPositionType(emplPositionTypeToBeAdded);
		EmplPositionType emplPositionType = ((EmplPositionTypeAdded) Scheduler.execute(command).data()).getAddedEmplPositionType();
		
		if (emplPositionType != null) 
			return successful(emplPositionType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionType with the specific Id
	 * 
	 * @param emplPositionTypeToBeUpdated
	 *            the EmplPositionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emplPositionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionType(@RequestBody EmplPositionType emplPositionTypeToBeUpdated,
			@PathVariable String emplPositionTypeId) throws Exception {

		emplPositionTypeToBeUpdated.setEmplPositionTypeId(emplPositionTypeId);

		UpdateEmplPositionType command = new UpdateEmplPositionType(emplPositionTypeToBeUpdated);

		try {
			if(((EmplPositionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionTypeId}")
	public ResponseEntity<EmplPositionType> findById(@PathVariable String emplPositionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionTypeId", emplPositionTypeId);
		try {

			List<EmplPositionType> foundEmplPositionType = findEmplPositionTypesBy(requestParams).getBody();
			if(foundEmplPositionType.size()==1){				return successful(foundEmplPositionType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionTypeId}")
	public ResponseEntity<String> deleteEmplPositionTypeByIdUpdated(@PathVariable String emplPositionTypeId) throws Exception {
		DeleteEmplPositionType command = new DeleteEmplPositionType(emplPositionTypeId);

		try {
			if (((EmplPositionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
