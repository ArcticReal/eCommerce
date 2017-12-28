package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.typeClass;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeClass.AddEmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeClass.DeleteEmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeClass.UpdateEmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeClass.EmplPositionTypeClassMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.typeClass.FindEmplPositionTypeClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionTypeClasss")
public class EmplPositionTypeClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionTypeClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionTypeClass
	 * @return a List with the EmplPositionTypeClasss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionTypeClass>> findEmplPositionTypeClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionTypeClasssBy query = new FindEmplPositionTypeClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionTypeClass> emplPositionTypeClasss =((EmplPositionTypeClassFound) Scheduler.execute(query).data()).getEmplPositionTypeClasss();

		return ResponseEntity.ok().body(emplPositionTypeClasss);

	}

	/**
	 * creates a new EmplPositionTypeClass entry in the ofbiz database
	 * 
	 * @param emplPositionTypeClassToBeAdded
	 *            the EmplPositionTypeClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionTypeClass> createEmplPositionTypeClass(@RequestBody EmplPositionTypeClass emplPositionTypeClassToBeAdded) throws Exception {

		AddEmplPositionTypeClass command = new AddEmplPositionTypeClass(emplPositionTypeClassToBeAdded);
		EmplPositionTypeClass emplPositionTypeClass = ((EmplPositionTypeClassAdded) Scheduler.execute(command).data()).getAddedEmplPositionTypeClass();
		
		if (emplPositionTypeClass != null) 
			return successful(emplPositionTypeClass);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionTypeClass with the specific Id
	 * 
	 * @param emplPositionTypeClassToBeUpdated
	 *            the EmplPositionTypeClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionTypeClass(@RequestBody EmplPositionTypeClass emplPositionTypeClassToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionTypeClassToBeUpdated.setnull(null);

		UpdateEmplPositionTypeClass command = new UpdateEmplPositionTypeClass(emplPositionTypeClassToBeUpdated);

		try {
			if(((EmplPositionTypeClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionTypeClassId}")
	public ResponseEntity<EmplPositionTypeClass> findById(@PathVariable String emplPositionTypeClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionTypeClassId", emplPositionTypeClassId);
		try {

			List<EmplPositionTypeClass> foundEmplPositionTypeClass = findEmplPositionTypeClasssBy(requestParams).getBody();
			if(foundEmplPositionTypeClass.size()==1){				return successful(foundEmplPositionTypeClass.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionTypeClassId}")
	public ResponseEntity<String> deleteEmplPositionTypeClassByIdUpdated(@PathVariable String emplPositionTypeClassId) throws Exception {
		DeleteEmplPositionTypeClass command = new DeleteEmplPositionTypeClass(emplPositionTypeClassId);

		try {
			if (((EmplPositionTypeClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
