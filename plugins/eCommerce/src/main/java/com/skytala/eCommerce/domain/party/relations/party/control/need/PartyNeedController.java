package com.skytala.eCommerce.domain.party.relations.party.control.need;

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
import com.skytala.eCommerce.domain.party.relations.party.command.need.AddPartyNeed;
import com.skytala.eCommerce.domain.party.relations.party.command.need.DeletePartyNeed;
import com.skytala.eCommerce.domain.party.relations.party.command.need.UpdatePartyNeed;
import com.skytala.eCommerce.domain.party.relations.party.event.need.PartyNeedAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.need.PartyNeedDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.need.PartyNeedFound;
import com.skytala.eCommerce.domain.party.relations.party.event.need.PartyNeedUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.need.PartyNeedMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.need.PartyNeed;
import com.skytala.eCommerce.domain.party.relations.party.query.need.FindPartyNeedsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyNeeds")
public class PartyNeedController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyNeedController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyNeed
	 * @return a List with the PartyNeeds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyNeed>> findPartyNeedsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyNeedsBy query = new FindPartyNeedsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyNeed> partyNeeds =((PartyNeedFound) Scheduler.execute(query).data()).getPartyNeeds();

		return ResponseEntity.ok().body(partyNeeds);

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
	public ResponseEntity<PartyNeed> createPartyNeed(HttpServletRequest request) throws Exception {

		PartyNeed partyNeedToBeAdded = new PartyNeed();
		try {
			partyNeedToBeAdded = PartyNeedMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyNeed(partyNeedToBeAdded);

	}

	/**
	 * creates a new PartyNeed entry in the ofbiz database
	 * 
	 * @param partyNeedToBeAdded
	 *            the PartyNeed thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyNeed> createPartyNeed(@RequestBody PartyNeed partyNeedToBeAdded) throws Exception {

		AddPartyNeed command = new AddPartyNeed(partyNeedToBeAdded);
		PartyNeed partyNeed = ((PartyNeedAdded) Scheduler.execute(command).data()).getAddedPartyNeed();
		
		if (partyNeed != null) 
			return successful(partyNeed);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyNeed with the specific Id
	 * 
	 * @param partyNeedToBeUpdated
	 *            the PartyNeed thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyNeedId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyNeed(@RequestBody PartyNeed partyNeedToBeUpdated,
			@PathVariable String partyNeedId) throws Exception {

		partyNeedToBeUpdated.setPartyNeedId(partyNeedId);

		UpdatePartyNeed command = new UpdatePartyNeed(partyNeedToBeUpdated);

		try {
			if(((PartyNeedUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyNeedId}")
	public ResponseEntity<PartyNeed> findById(@PathVariable String partyNeedId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyNeedId", partyNeedId);
		try {

			List<PartyNeed> foundPartyNeed = findPartyNeedsBy(requestParams).getBody();
			if(foundPartyNeed.size()==1){				return successful(foundPartyNeed.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyNeedId}")
	public ResponseEntity<String> deletePartyNeedByIdUpdated(@PathVariable String partyNeedId) throws Exception {
		DeletePartyNeed command = new DeletePartyNeed(partyNeedId);

		try {
			if (((PartyNeedDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
