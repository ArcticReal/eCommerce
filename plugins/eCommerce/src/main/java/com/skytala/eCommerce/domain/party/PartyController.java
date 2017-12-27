package com.skytala.eCommerce.domain.party;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.command.AddParty;
import com.skytala.eCommerce.domain.party.command.DeleteParty;
import com.skytala.eCommerce.domain.party.command.UpdateParty;
import com.skytala.eCommerce.domain.party.event.PartyAdded;
import com.skytala.eCommerce.domain.party.event.PartyDeleted;
import com.skytala.eCommerce.domain.party.event.PartyFound;
import com.skytala.eCommerce.domain.party.event.PartyUpdated;
import com.skytala.eCommerce.domain.party.mapper.PartyMapper;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.domain.party.query.FindPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partys")
public class PartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Party
	 * @return a List with the Partys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<Party>> findPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartysBy query = new FindPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Party> partys =((PartyFound) Scheduler.execute(query).data()).getPartys();



		return ResponseEntity.ok().body(partys);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Party> createParty(HttpServletRequest request) throws Exception {

		Party partyToBeAdded = new Party();
		try {
			partyToBeAdded = PartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createParty(partyToBeAdded);

	}

	/**
	 * creates a new Party entry in the ofbiz database
	 * 
	 * @param partyToBeAdded
	 *            the Party thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Party> createParty(@RequestBody Party partyToBeAdded) throws Exception {

		AddParty command = new AddParty(partyToBeAdded);
		Party party = ((PartyAdded) Scheduler.execute(command).data()).getAddedParty();
		
		if (party != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(party);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
	}

	/**
	 * Updates the Party with the specific Id
	 * 
	 * @param partyToBeUpdated
	 *            the Party thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateParty(@RequestBody Party partyToBeUpdated,
			@PathVariable String partyId) throws Exception {

		partyToBeUpdated.setPartyId(partyId);

		UpdateParty command = new UpdateParty(partyToBeUpdated);

		try {
			if(((PartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyId}")
	public ResponseEntity<Party> findById(@PathVariable String partyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyId", partyId);
		try {

			List<Party> foundParty = findPartysBy(requestParams).getBody();
			if(foundParty.size()==1)
				return ResponseEntity.status(HttpStatus.OK).body(foundParty.get(0));

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyId}")
	public ResponseEntity<Object> deletePartyByIdUpdated(@PathVariable String partyId) throws Exception {
		DeleteParty command = new DeleteParty(partyId);

		try {
			if (((PartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Party could not be deleted");

	}

}
