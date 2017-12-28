package com.skytala.eCommerce.domain.order.relations.respondingParty;

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
import com.skytala.eCommerce.domain.order.relations.respondingParty.command.AddRespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.command.DeleteRespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.command.UpdateRespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyAdded;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyDeleted;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyFound;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyUpdated;
import com.skytala.eCommerce.domain.order.relations.respondingParty.mapper.RespondingPartyMapper;
import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.query.FindRespondingPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/respondingPartys")
public class RespondingPartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RespondingPartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RespondingParty
	 * @return a List with the RespondingPartys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RespondingParty>> findRespondingPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRespondingPartysBy query = new FindRespondingPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RespondingParty> respondingPartys =((RespondingPartyFound) Scheduler.execute(query).data()).getRespondingPartys();

		return ResponseEntity.ok().body(respondingPartys);

	}

	/**
	 * creates a new RespondingParty entry in the ofbiz database
	 * 
	 * @param respondingPartyToBeAdded
	 *            the RespondingParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RespondingParty> createRespondingParty(@RequestBody RespondingParty respondingPartyToBeAdded) throws Exception {

		AddRespondingParty command = new AddRespondingParty(respondingPartyToBeAdded);
		RespondingParty respondingParty = ((RespondingPartyAdded) Scheduler.execute(command).data()).getAddedRespondingParty();
		
		if (respondingParty != null) 
			return successful(respondingParty);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RespondingParty with the specific Id
	 * 
	 * @param respondingPartyToBeUpdated
	 *            the RespondingParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{respondingPartySeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRespondingParty(@RequestBody RespondingParty respondingPartyToBeUpdated,
			@PathVariable String respondingPartySeqId) throws Exception {

		respondingPartyToBeUpdated.setRespondingPartySeqId(respondingPartySeqId);

		UpdateRespondingParty command = new UpdateRespondingParty(respondingPartyToBeUpdated);

		try {
			if(((RespondingPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{respondingPartyId}")
	public ResponseEntity<RespondingParty> findById(@PathVariable String respondingPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("respondingPartyId", respondingPartyId);
		try {

			List<RespondingParty> foundRespondingParty = findRespondingPartysBy(requestParams).getBody();
			if(foundRespondingParty.size()==1){				return successful(foundRespondingParty.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{respondingPartyId}")
	public ResponseEntity<String> deleteRespondingPartyByIdUpdated(@PathVariable String respondingPartyId) throws Exception {
		DeleteRespondingParty command = new DeleteRespondingParty(respondingPartyId);

		try {
			if (((RespondingPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
