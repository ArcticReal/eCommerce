package com.skytala.eCommerce.domain.party.relations.communicationEvent.control.prpTyp;

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
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.prpTyp.AddCommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.prpTyp.DeleteCommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.command.prpTyp.UpdateCommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp.CommunicationEventPrpTypAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp.CommunicationEventPrpTypDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp.CommunicationEventPrpTypFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp.CommunicationEventPrpTypUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.prpTyp.CommunicationEventPrpTypMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.query.prpTyp.FindCommunicationEventPrpTypsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/communicationEvent/communicationEventPrpTyps")
public class CommunicationEventPrpTypController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventPrpTypController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventPrpTyp
	 * @return a List with the CommunicationEventPrpTyps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CommunicationEventPrpTyp>> findCommunicationEventPrpTypsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventPrpTypsBy query = new FindCommunicationEventPrpTypsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventPrpTyp> communicationEventPrpTyps =((CommunicationEventPrpTypFound) Scheduler.execute(query).data()).getCommunicationEventPrpTyps();

		return ResponseEntity.ok().body(communicationEventPrpTyps);

	}

	/**
	 * creates a new CommunicationEventPrpTyp entry in the ofbiz database
	 * 
	 * @param communicationEventPrpTypToBeAdded
	 *            the CommunicationEventPrpTyp thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CommunicationEventPrpTyp> createCommunicationEventPrpTyp(@RequestBody CommunicationEventPrpTyp communicationEventPrpTypToBeAdded) throws Exception {

		AddCommunicationEventPrpTyp command = new AddCommunicationEventPrpTyp(communicationEventPrpTypToBeAdded);
		CommunicationEventPrpTyp communicationEventPrpTyp = ((CommunicationEventPrpTypAdded) Scheduler.execute(command).data()).getAddedCommunicationEventPrpTyp();
		
		if (communicationEventPrpTyp != null) 
			return successful(communicationEventPrpTyp);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CommunicationEventPrpTyp with the specific Id
	 * 
	 * @param communicationEventPrpTypToBeUpdated
	 *            the CommunicationEventPrpTyp thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{communicationEventPrpTypId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCommunicationEventPrpTyp(@RequestBody CommunicationEventPrpTyp communicationEventPrpTypToBeUpdated,
			@PathVariable String communicationEventPrpTypId) throws Exception {

		communicationEventPrpTypToBeUpdated.setCommunicationEventPrpTypId(communicationEventPrpTypId);

		UpdateCommunicationEventPrpTyp command = new UpdateCommunicationEventPrpTyp(communicationEventPrpTypToBeUpdated);

		try {
			if(((CommunicationEventPrpTypUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{communicationEventPrpTypId}")
	public ResponseEntity<CommunicationEventPrpTyp> findById(@PathVariable String communicationEventPrpTypId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventPrpTypId", communicationEventPrpTypId);
		try {

			List<CommunicationEventPrpTyp> foundCommunicationEventPrpTyp = findCommunicationEventPrpTypsBy(requestParams).getBody();
			if(foundCommunicationEventPrpTyp.size()==1){				return successful(foundCommunicationEventPrpTyp.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{communicationEventPrpTypId}")
	public ResponseEntity<String> deleteCommunicationEventPrpTypByIdUpdated(@PathVariable String communicationEventPrpTypId) throws Exception {
		DeleteCommunicationEventPrpTyp command = new DeleteCommunicationEventPrpTyp(communicationEventPrpTypId);

		try {
			if (((CommunicationEventPrpTypDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
