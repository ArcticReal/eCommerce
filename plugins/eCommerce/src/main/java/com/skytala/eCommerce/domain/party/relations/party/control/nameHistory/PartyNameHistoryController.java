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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/partyNameHistorys")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyNameHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyNameHistorysBy query = new FindPartyNameHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyNameHistory> partyNameHistorys =((PartyNameHistoryFound) Scheduler.execute(query).data()).getPartyNameHistorys();

		if (partyNameHistorys.size() == 1) {
			return ResponseEntity.ok().body(partyNameHistorys.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPartyNameHistory(HttpServletRequest request) throws Exception {

		PartyNameHistory partyNameHistoryToBeAdded = new PartyNameHistory();
		try {
			partyNameHistoryToBeAdded = PartyNameHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPartyNameHistory(@RequestBody PartyNameHistory partyNameHistoryToBeAdded) throws Exception {

		AddPartyNameHistory command = new AddPartyNameHistory(partyNameHistoryToBeAdded);
		PartyNameHistory partyNameHistory = ((PartyNameHistoryAdded) Scheduler.execute(command).data()).getAddedPartyNameHistory();
		
		if (partyNameHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyNameHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyNameHistory could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePartyNameHistory(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		PartyNameHistory partyNameHistoryToBeUpdated = new PartyNameHistory();

		try {
			partyNameHistoryToBeUpdated = PartyNameHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyNameHistory(partyNameHistoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyNameHistory(@RequestBody PartyNameHistory partyNameHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyNameHistoryToBeUpdated.setnull(null);

		UpdatePartyNameHistory command = new UpdatePartyNameHistory(partyNameHistoryToBeUpdated);

		try {
			if(((PartyNameHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyNameHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String partyNameHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyNameHistoryId", partyNameHistoryId);
		try {

			Object foundPartyNameHistory = findPartyNameHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyNameHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyNameHistoryId}")
	public ResponseEntity<Object> deletePartyNameHistoryByIdUpdated(@PathVariable String partyNameHistoryId) throws Exception {
		DeletePartyNameHistory command = new DeletePartyNameHistory(partyNameHistoryId);

		try {
			if (((PartyNameHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyNameHistory could not be deleted");

	}

}
