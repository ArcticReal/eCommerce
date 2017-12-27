package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.classType;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType.AddEmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType.DeleteEmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType.UpdateEmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.classType.EmplPositionClassTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.classType.FindEmplPositionClassTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionClassTypes")
public class EmplPositionClassTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionClassTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionClassType
	 * @return a List with the EmplPositionClassTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionClassType>> findEmplPositionClassTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionClassTypesBy query = new FindEmplPositionClassTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionClassType> emplPositionClassTypes =((EmplPositionClassTypeFound) Scheduler.execute(query).data()).getEmplPositionClassTypes();

		return ResponseEntity.ok().body(emplPositionClassTypes);

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
	public ResponseEntity<EmplPositionClassType> createEmplPositionClassType(HttpServletRequest request) throws Exception {

		EmplPositionClassType emplPositionClassTypeToBeAdded = new EmplPositionClassType();
		try {
			emplPositionClassTypeToBeAdded = EmplPositionClassTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createEmplPositionClassType(emplPositionClassTypeToBeAdded);

	}

	/**
	 * creates a new EmplPositionClassType entry in the ofbiz database
	 * 
	 * @param emplPositionClassTypeToBeAdded
	 *            the EmplPositionClassType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionClassType> createEmplPositionClassType(@RequestBody EmplPositionClassType emplPositionClassTypeToBeAdded) throws Exception {

		AddEmplPositionClassType command = new AddEmplPositionClassType(emplPositionClassTypeToBeAdded);
		EmplPositionClassType emplPositionClassType = ((EmplPositionClassTypeAdded) Scheduler.execute(command).data()).getAddedEmplPositionClassType();
		
		if (emplPositionClassType != null) 
			return successful(emplPositionClassType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionClassType with the specific Id
	 * 
	 * @param emplPositionClassTypeToBeUpdated
	 *            the EmplPositionClassType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emplPositionClassTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionClassType(@RequestBody EmplPositionClassType emplPositionClassTypeToBeUpdated,
			@PathVariable String emplPositionClassTypeId) throws Exception {

		emplPositionClassTypeToBeUpdated.setEmplPositionClassTypeId(emplPositionClassTypeId);

		UpdateEmplPositionClassType command = new UpdateEmplPositionClassType(emplPositionClassTypeToBeUpdated);

		try {
			if(((EmplPositionClassTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionClassTypeId}")
	public ResponseEntity<EmplPositionClassType> findById(@PathVariable String emplPositionClassTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionClassTypeId", emplPositionClassTypeId);
		try {

			List<EmplPositionClassType> foundEmplPositionClassType = findEmplPositionClassTypesBy(requestParams).getBody();
			if(foundEmplPositionClassType.size()==1){				return successful(foundEmplPositionClassType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionClassTypeId}")
	public ResponseEntity<String> deleteEmplPositionClassTypeByIdUpdated(@PathVariable String emplPositionClassTypeId) throws Exception {
		DeleteEmplPositionClassType command = new DeleteEmplPositionClassType(emplPositionClassTypeId);

		try {
			if (((EmplPositionClassTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
