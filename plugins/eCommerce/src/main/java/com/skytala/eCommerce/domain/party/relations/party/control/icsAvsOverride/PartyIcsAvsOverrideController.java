package com.skytala.eCommerce.domain.party.relations.party.control.icsAvsOverride;

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
import com.skytala.eCommerce.domain.party.relations.party.command.icsAvsOverride.AddPartyIcsAvsOverride;
import com.skytala.eCommerce.domain.party.relations.party.command.icsAvsOverride.DeletePartyIcsAvsOverride;
import com.skytala.eCommerce.domain.party.relations.party.command.icsAvsOverride.UpdatePartyIcsAvsOverride;
import com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride.PartyIcsAvsOverrideAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride.PartyIcsAvsOverrideDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride.PartyIcsAvsOverrideFound;
import com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride.PartyIcsAvsOverrideUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.icsAvsOverride.PartyIcsAvsOverrideMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.icsAvsOverride.PartyIcsAvsOverride;
import com.skytala.eCommerce.domain.party.relations.party.query.icsAvsOverride.FindPartyIcsAvsOverridesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyIcsAvsOverrides")
public class PartyIcsAvsOverrideController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyIcsAvsOverrideController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyIcsAvsOverride
	 * @return a List with the PartyIcsAvsOverrides
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyIcsAvsOverride>> findPartyIcsAvsOverridesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyIcsAvsOverridesBy query = new FindPartyIcsAvsOverridesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyIcsAvsOverride> partyIcsAvsOverrides =((PartyIcsAvsOverrideFound) Scheduler.execute(query).data()).getPartyIcsAvsOverrides();

		return ResponseEntity.ok().body(partyIcsAvsOverrides);

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
	public ResponseEntity<PartyIcsAvsOverride> createPartyIcsAvsOverride(HttpServletRequest request) throws Exception {

		PartyIcsAvsOverride partyIcsAvsOverrideToBeAdded = new PartyIcsAvsOverride();
		try {
			partyIcsAvsOverrideToBeAdded = PartyIcsAvsOverrideMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyIcsAvsOverride(partyIcsAvsOverrideToBeAdded);

	}

	/**
	 * creates a new PartyIcsAvsOverride entry in the ofbiz database
	 * 
	 * @param partyIcsAvsOverrideToBeAdded
	 *            the PartyIcsAvsOverride thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyIcsAvsOverride> createPartyIcsAvsOverride(@RequestBody PartyIcsAvsOverride partyIcsAvsOverrideToBeAdded) throws Exception {

		AddPartyIcsAvsOverride command = new AddPartyIcsAvsOverride(partyIcsAvsOverrideToBeAdded);
		PartyIcsAvsOverride partyIcsAvsOverride = ((PartyIcsAvsOverrideAdded) Scheduler.execute(command).data()).getAddedPartyIcsAvsOverride();
		
		if (partyIcsAvsOverride != null) 
			return successful(partyIcsAvsOverride);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyIcsAvsOverride with the specific Id
	 * 
	 * @param partyIcsAvsOverrideToBeUpdated
	 *            the PartyIcsAvsOverride thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyIcsAvsOverride(@RequestBody PartyIcsAvsOverride partyIcsAvsOverrideToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyIcsAvsOverrideToBeUpdated.setnull(null);

		UpdatePartyIcsAvsOverride command = new UpdatePartyIcsAvsOverride(partyIcsAvsOverrideToBeUpdated);

		try {
			if(((PartyIcsAvsOverrideUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyIcsAvsOverrideId}")
	public ResponseEntity<PartyIcsAvsOverride> findById(@PathVariable String partyIcsAvsOverrideId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyIcsAvsOverrideId", partyIcsAvsOverrideId);
		try {

			List<PartyIcsAvsOverride> foundPartyIcsAvsOverride = findPartyIcsAvsOverridesBy(requestParams).getBody();
			if(foundPartyIcsAvsOverride.size()==1){				return successful(foundPartyIcsAvsOverride.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyIcsAvsOverrideId}")
	public ResponseEntity<String> deletePartyIcsAvsOverrideByIdUpdated(@PathVariable String partyIcsAvsOverrideId) throws Exception {
		DeletePartyIcsAvsOverride command = new DeletePartyIcsAvsOverride(partyIcsAvsOverrideId);

		try {
			if (((PartyIcsAvsOverrideDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
