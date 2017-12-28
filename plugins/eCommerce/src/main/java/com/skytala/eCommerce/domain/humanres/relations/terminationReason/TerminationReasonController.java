package com.skytala.eCommerce.domain.humanres.relations.terminationReason;

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
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.command.AddTerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.command.DeleteTerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.command.UpdateTerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonAdded;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonDeleted;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonFound;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonUpdated;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.mapper.TerminationReasonMapper;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.query.FindTerminationReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/terminationReasons")
public class TerminationReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TerminationReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TerminationReason
	 * @return a List with the TerminationReasons
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TerminationReason>> findTerminationReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTerminationReasonsBy query = new FindTerminationReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TerminationReason> terminationReasons =((TerminationReasonFound) Scheduler.execute(query).data()).getTerminationReasons();

		return ResponseEntity.ok().body(terminationReasons);

	}

	/**
	 * creates a new TerminationReason entry in the ofbiz database
	 * 
	 * @param terminationReasonToBeAdded
	 *            the TerminationReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TerminationReason> createTerminationReason(@RequestBody TerminationReason terminationReasonToBeAdded) throws Exception {

		AddTerminationReason command = new AddTerminationReason(terminationReasonToBeAdded);
		TerminationReason terminationReason = ((TerminationReasonAdded) Scheduler.execute(command).data()).getAddedTerminationReason();
		
		if (terminationReason != null) 
			return successful(terminationReason);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TerminationReason with the specific Id
	 * 
	 * @param terminationReasonToBeUpdated
	 *            the TerminationReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{terminationReasonId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTerminationReason(@RequestBody TerminationReason terminationReasonToBeUpdated,
			@PathVariable String terminationReasonId) throws Exception {

		terminationReasonToBeUpdated.setTerminationReasonId(terminationReasonId);

		UpdateTerminationReason command = new UpdateTerminationReason(terminationReasonToBeUpdated);

		try {
			if(((TerminationReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{terminationReasonId}")
	public ResponseEntity<TerminationReason> findById(@PathVariable String terminationReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("terminationReasonId", terminationReasonId);
		try {

			List<TerminationReason> foundTerminationReason = findTerminationReasonsBy(requestParams).getBody();
			if(foundTerminationReason.size()==1){				return successful(foundTerminationReason.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{terminationReasonId}")
	public ResponseEntity<String> deleteTerminationReasonByIdUpdated(@PathVariable String terminationReasonId) throws Exception {
		DeleteTerminationReason command = new DeleteTerminationReason(terminationReasonId);

		try {
			if (((TerminationReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
