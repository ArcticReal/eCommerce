package com.skytala.eCommerce.domain.party.relations.party.control.classification;

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
import com.skytala.eCommerce.domain.party.relations.party.command.classification.AddPartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.command.classification.DeletePartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.command.classification.UpdatePartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationFound;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classification.PartyClassificationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.query.classification.FindPartyClassificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyClassifications")
public class PartyClassificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyClassificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyClassification
	 * @return a List with the PartyClassifications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyClassification>> findPartyClassificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyClassificationsBy query = new FindPartyClassificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyClassification> partyClassifications =((PartyClassificationFound) Scheduler.execute(query).data()).getPartyClassifications();

		return ResponseEntity.ok().body(partyClassifications);

	}

	/**
	 * creates a new PartyClassification entry in the ofbiz database
	 * 
	 * @param partyClassificationToBeAdded
	 *            the PartyClassification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyClassification> createPartyClassification(@RequestBody PartyClassification partyClassificationToBeAdded) throws Exception {

		AddPartyClassification command = new AddPartyClassification(partyClassificationToBeAdded);
		PartyClassification partyClassification = ((PartyClassificationAdded) Scheduler.execute(command).data()).getAddedPartyClassification();
		
		if (partyClassification != null) 
			return successful(partyClassification);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyClassification with the specific Id
	 * 
	 * @param partyClassificationToBeUpdated
	 *            the PartyClassification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyClassification(@RequestBody PartyClassification partyClassificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyClassificationToBeUpdated.setnull(null);

		UpdatePartyClassification command = new UpdatePartyClassification(partyClassificationToBeUpdated);

		try {
			if(((PartyClassificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyClassificationId}")
	public ResponseEntity<PartyClassification> findById(@PathVariable String partyClassificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyClassificationId", partyClassificationId);
		try {

			List<PartyClassification> foundPartyClassification = findPartyClassificationsBy(requestParams).getBody();
			if(foundPartyClassification.size()==1){				return successful(foundPartyClassification.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyClassificationId}")
	public ResponseEntity<String> deletePartyClassificationByIdUpdated(@PathVariable String partyClassificationId) throws Exception {
		DeletePartyClassification command = new DeletePartyClassification(partyClassificationId);

		try {
			if (((PartyClassificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
