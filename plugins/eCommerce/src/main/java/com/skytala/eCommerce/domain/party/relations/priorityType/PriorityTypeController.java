package com.skytala.eCommerce.domain.party.relations.priorityType;

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
import com.skytala.eCommerce.domain.party.relations.priorityType.command.AddPriorityType;
import com.skytala.eCommerce.domain.party.relations.priorityType.command.DeletePriorityType;
import com.skytala.eCommerce.domain.party.relations.priorityType.command.UpdatePriorityType;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeAdded;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeFound;
import com.skytala.eCommerce.domain.party.relations.priorityType.event.PriorityTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.priorityType.mapper.PriorityTypeMapper;
import com.skytala.eCommerce.domain.party.relations.priorityType.model.PriorityType;
import com.skytala.eCommerce.domain.party.relations.priorityType.query.FindPriorityTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/priorityTypes")
public class PriorityTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PriorityTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PriorityType
	 * @return a List with the PriorityTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PriorityType>> findPriorityTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPriorityTypesBy query = new FindPriorityTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PriorityType> priorityTypes =((PriorityTypeFound) Scheduler.execute(query).data()).getPriorityTypes();

		return ResponseEntity.ok().body(priorityTypes);

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
	public ResponseEntity<PriorityType> createPriorityType(HttpServletRequest request) throws Exception {

		PriorityType priorityTypeToBeAdded = new PriorityType();
		try {
			priorityTypeToBeAdded = PriorityTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPriorityType(priorityTypeToBeAdded);

	}

	/**
	 * creates a new PriorityType entry in the ofbiz database
	 * 
	 * @param priorityTypeToBeAdded
	 *            the PriorityType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PriorityType> createPriorityType(@RequestBody PriorityType priorityTypeToBeAdded) throws Exception {

		AddPriorityType command = new AddPriorityType(priorityTypeToBeAdded);
		PriorityType priorityType = ((PriorityTypeAdded) Scheduler.execute(command).data()).getAddedPriorityType();
		
		if (priorityType != null) 
			return successful(priorityType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PriorityType with the specific Id
	 * 
	 * @param priorityTypeToBeUpdated
	 *            the PriorityType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{priorityTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePriorityType(@RequestBody PriorityType priorityTypeToBeUpdated,
			@PathVariable String priorityTypeId) throws Exception {

		priorityTypeToBeUpdated.setPriorityTypeId(priorityTypeId);

		UpdatePriorityType command = new UpdatePriorityType(priorityTypeToBeUpdated);

		try {
			if(((PriorityTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{priorityTypeId}")
	public ResponseEntity<PriorityType> findById(@PathVariable String priorityTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("priorityTypeId", priorityTypeId);
		try {

			List<PriorityType> foundPriorityType = findPriorityTypesBy(requestParams).getBody();
			if(foundPriorityType.size()==1){				return successful(foundPriorityType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{priorityTypeId}")
	public ResponseEntity<String> deletePriorityTypeByIdUpdated(@PathVariable String priorityTypeId) throws Exception {
		DeletePriorityType command = new DeletePriorityType(priorityTypeId);

		try {
			if (((PriorityTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
