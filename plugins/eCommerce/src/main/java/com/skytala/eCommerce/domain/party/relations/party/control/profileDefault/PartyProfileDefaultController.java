package com.skytala.eCommerce.domain.party.relations.party.control.profileDefault;

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
import com.skytala.eCommerce.domain.party.relations.party.command.profileDefault.AddPartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.command.profileDefault.DeletePartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.command.profileDefault.UpdatePartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultFound;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.profileDefault.PartyProfileDefaultMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.query.profileDefault.FindPartyProfileDefaultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyProfileDefaults")
public class PartyProfileDefaultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyProfileDefaultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyProfileDefault
	 * @return a List with the PartyProfileDefaults
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyProfileDefault>> findPartyProfileDefaultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyProfileDefaultsBy query = new FindPartyProfileDefaultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyProfileDefault> partyProfileDefaults =((PartyProfileDefaultFound) Scheduler.execute(query).data()).getPartyProfileDefaults();

		return ResponseEntity.ok().body(partyProfileDefaults);

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
	public ResponseEntity<PartyProfileDefault> createPartyProfileDefault(HttpServletRequest request) throws Exception {

		PartyProfileDefault partyProfileDefaultToBeAdded = new PartyProfileDefault();
		try {
			partyProfileDefaultToBeAdded = PartyProfileDefaultMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyProfileDefault(partyProfileDefaultToBeAdded);

	}

	/**
	 * creates a new PartyProfileDefault entry in the ofbiz database
	 * 
	 * @param partyProfileDefaultToBeAdded
	 *            the PartyProfileDefault thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyProfileDefault> createPartyProfileDefault(@RequestBody PartyProfileDefault partyProfileDefaultToBeAdded) throws Exception {

		AddPartyProfileDefault command = new AddPartyProfileDefault(partyProfileDefaultToBeAdded);
		PartyProfileDefault partyProfileDefault = ((PartyProfileDefaultAdded) Scheduler.execute(command).data()).getAddedPartyProfileDefault();
		
		if (partyProfileDefault != null) 
			return successful(partyProfileDefault);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyProfileDefault with the specific Id
	 * 
	 * @param partyProfileDefaultToBeUpdated
	 *            the PartyProfileDefault thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyProfileDefault(@RequestBody PartyProfileDefault partyProfileDefaultToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyProfileDefaultToBeUpdated.setnull(null);

		UpdatePartyProfileDefault command = new UpdatePartyProfileDefault(partyProfileDefaultToBeUpdated);

		try {
			if(((PartyProfileDefaultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyProfileDefaultId}")
	public ResponseEntity<PartyProfileDefault> findById(@PathVariable String partyProfileDefaultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyProfileDefaultId", partyProfileDefaultId);
		try {

			List<PartyProfileDefault> foundPartyProfileDefault = findPartyProfileDefaultsBy(requestParams).getBody();
			if(foundPartyProfileDefault.size()==1){				return successful(foundPartyProfileDefault.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyProfileDefaultId}")
	public ResponseEntity<String> deletePartyProfileDefaultByIdUpdated(@PathVariable String partyProfileDefaultId) throws Exception {
		DeletePartyProfileDefault command = new DeletePartyProfileDefault(partyProfileDefaultId);

		try {
			if (((PartyProfileDefaultDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
