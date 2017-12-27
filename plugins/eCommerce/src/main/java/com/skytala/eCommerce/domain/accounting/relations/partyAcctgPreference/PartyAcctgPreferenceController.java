package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference;

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
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.command.AddPartyAcctgPreference;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.command.DeletePartyAcctgPreference;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.command.UpdatePartyAcctgPreference;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceDeleted;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceFound;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.mapper.PartyAcctgPreferenceMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.query.FindPartyAcctgPreferencesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/partyAcctgPreferences")
public class PartyAcctgPreferenceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyAcctgPreferenceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyAcctgPreference
	 * @return a List with the PartyAcctgPreferences
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyAcctgPreference>> findPartyAcctgPreferencesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyAcctgPreferencesBy query = new FindPartyAcctgPreferencesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyAcctgPreference> partyAcctgPreferences =((PartyAcctgPreferenceFound) Scheduler.execute(query).data()).getPartyAcctgPreferences();

		return ResponseEntity.ok().body(partyAcctgPreferences);

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
	public ResponseEntity<PartyAcctgPreference> createPartyAcctgPreference(HttpServletRequest request) throws Exception {

		PartyAcctgPreference partyAcctgPreferenceToBeAdded = new PartyAcctgPreference();
		try {
			partyAcctgPreferenceToBeAdded = PartyAcctgPreferenceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyAcctgPreference(partyAcctgPreferenceToBeAdded);

	}

	/**
	 * creates a new PartyAcctgPreference entry in the ofbiz database
	 * 
	 * @param partyAcctgPreferenceToBeAdded
	 *            the PartyAcctgPreference thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyAcctgPreference> createPartyAcctgPreference(@RequestBody PartyAcctgPreference partyAcctgPreferenceToBeAdded) throws Exception {

		AddPartyAcctgPreference command = new AddPartyAcctgPreference(partyAcctgPreferenceToBeAdded);
		PartyAcctgPreference partyAcctgPreference = ((PartyAcctgPreferenceAdded) Scheduler.execute(command).data()).getAddedPartyAcctgPreference();
		
		if (partyAcctgPreference != null) 
			return successful(partyAcctgPreference);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyAcctgPreference with the specific Id
	 * 
	 * @param partyAcctgPreferenceToBeUpdated
	 *            the PartyAcctgPreference thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyAcctgPreference(@RequestBody PartyAcctgPreference partyAcctgPreferenceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyAcctgPreferenceToBeUpdated.setnull(null);

		UpdatePartyAcctgPreference command = new UpdatePartyAcctgPreference(partyAcctgPreferenceToBeUpdated);

		try {
			if(((PartyAcctgPreferenceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyAcctgPreferenceId}")
	public ResponseEntity<PartyAcctgPreference> findById(@PathVariable String partyAcctgPreferenceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyAcctgPreferenceId", partyAcctgPreferenceId);
		try {

			List<PartyAcctgPreference> foundPartyAcctgPreference = findPartyAcctgPreferencesBy(requestParams).getBody();
			if(foundPartyAcctgPreference.size()==1){				return successful(foundPartyAcctgPreference.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyAcctgPreferenceId}")
	public ResponseEntity<String> deletePartyAcctgPreferenceByIdUpdated(@PathVariable String partyAcctgPreferenceId) throws Exception {
		DeletePartyAcctgPreference command = new DeletePartyAcctgPreference(partyAcctgPreferenceId);

		try {
			if (((PartyAcctgPreferenceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
