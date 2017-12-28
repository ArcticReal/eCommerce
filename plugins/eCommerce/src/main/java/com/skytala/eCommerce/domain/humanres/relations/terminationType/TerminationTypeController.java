package com.skytala.eCommerce.domain.humanres.relations.terminationType;

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
import com.skytala.eCommerce.domain.humanres.relations.terminationType.command.AddTerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.command.DeleteTerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.command.UpdateTerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.mapper.TerminationTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.model.TerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.query.FindTerminationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/terminationTypes")
public class TerminationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TerminationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TerminationType
	 * @return a List with the TerminationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TerminationType>> findTerminationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTerminationTypesBy query = new FindTerminationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TerminationType> terminationTypes =((TerminationTypeFound) Scheduler.execute(query).data()).getTerminationTypes();

		return ResponseEntity.ok().body(terminationTypes);

	}

	/**
	 * creates a new TerminationType entry in the ofbiz database
	 * 
	 * @param terminationTypeToBeAdded
	 *            the TerminationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TerminationType> createTerminationType(@RequestBody TerminationType terminationTypeToBeAdded) throws Exception {

		AddTerminationType command = new AddTerminationType(terminationTypeToBeAdded);
		TerminationType terminationType = ((TerminationTypeAdded) Scheduler.execute(command).data()).getAddedTerminationType();
		
		if (terminationType != null) 
			return successful(terminationType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TerminationType with the specific Id
	 * 
	 * @param terminationTypeToBeUpdated
	 *            the TerminationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{terminationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTerminationType(@RequestBody TerminationType terminationTypeToBeUpdated,
			@PathVariable String terminationTypeId) throws Exception {

		terminationTypeToBeUpdated.setTerminationTypeId(terminationTypeId);

		UpdateTerminationType command = new UpdateTerminationType(terminationTypeToBeUpdated);

		try {
			if(((TerminationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{terminationTypeId}")
	public ResponseEntity<TerminationType> findById(@PathVariable String terminationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("terminationTypeId", terminationTypeId);
		try {

			List<TerminationType> foundTerminationType = findTerminationTypesBy(requestParams).getBody();
			if(foundTerminationType.size()==1){				return successful(foundTerminationType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{terminationTypeId}")
	public ResponseEntity<String> deleteTerminationTypeByIdUpdated(@PathVariable String terminationTypeId) throws Exception {
		DeleteTerminationType command = new DeleteTerminationType(terminationTypeId);

		try {
			if (((TerminationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
