package com.skytala.eCommerce.domain.party.relations.party.control.nameHistory;

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
import com.skytala.eCommerce.domain.party.relations.party.command.nameHistory.AddPartyNameHistory;
import com.skytala.eCommerce.domain.party.relations.party.command.nameHistory.DeletePartyNameHistory;
import com.skytala.eCommerce.domain.party.relations.party.command.nameHistory.UpdatePartyNameHistory;
import com.skytala.eCommerce.domain.party.relations.party.event.nameHistory.PartyNameHistoryAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.nameHistory.PartyNameHistoryDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.nameHistory.PartyNameHistoryFound;
import com.skytala.eCommerce.domain.party.relations.party.event.nameHistory.PartyNameHistoryUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.nameHistory.PartyNameHistoryMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;
import com.skytala.eCommerce.domain.party.relations.party.query.nameHistory.FindPartyNameHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyNameHistorys")
public class PartyNameHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyNameHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyNameHistory
	 * @return a List with the PartyNameHistorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyNameHistory>> findPartyNameHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyNameHistorysBy query = new FindPartyNameHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyNameHistory> partyNameHistorys =((PartyNameHistoryFound) Scheduler.execute(query).data()).getPartyNameHistorys();

		return ResponseEntity.ok().body(partyNameHistorys);

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
	public ResponseEntity<PartyNameHistory> createPartyNameHistory(HttpServletRequest request) throws Exception {

		PartyNameHistory partyNameHistoryToBeAdded = new PartyNameHistory();
		try {
			partyNameHistoryToBeAdded = PartyNameHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyNameHistory(partyNameHistoryToBeAdded);

	}

	/**
	 * creates a new PartyNameHistory entry in the ofbiz database
	 * 
	 * @param partyNameHistoryToBeAdded
	 *            the PartyNameHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyNameHistory> createPartyNameHistory(@RequestBody PartyNameHistory partyNameHistoryToBeAdded) throws Exception {

		AddPartyNameHistory command = new AddPartyNameHistory(partyNameHistoryToBeAdded);
		PartyNameHistory partyNameHistory = ((PartyNameHistoryAdded) Scheduler.execute(command).data()).getAddedPartyNameHistory();
		
		if (partyNameHistory != null) 
			return successful(partyNameHistory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyNameHistory with the specific Id
	 * 
	 * @param partyNameHistoryToBeUpdated
	 *            the PartyNameHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyNameHistory(@RequestBody PartyNameHistory partyNameHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyNameHistoryToBeUpdated.setnull(null);

		UpdatePartyNameHistory command = new UpdatePartyNameHistory(partyNameHistoryToBeUpdated);

		try {
			if(((PartyNameHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyNameHistoryId}")
	public ResponseEntity<PartyNameHistory> findById(@PathVariable String partyNameHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyNameHistoryId", partyNameHistoryId);
		try {

			List<PartyNameHistory> foundPartyNameHistory = findPartyNameHistorysBy(requestParams).getBody();
			if(foundPartyNameHistory.size()==1){				return successful(foundPartyNameHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyNameHistoryId}")
	public ResponseEntity<String> deletePartyNameHistoryByIdUpdated(@PathVariable String partyNameHistoryId) throws Exception {
		DeletePartyNameHistory command = new DeletePartyNameHistory(partyNameHistoryId);

		try {
			if (((PartyNameHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
