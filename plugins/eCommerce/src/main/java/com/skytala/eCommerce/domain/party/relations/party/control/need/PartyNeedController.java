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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/partyNeeds")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyNeedsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyNeedsBy query = new FindPartyNeedsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyNeed> partyNeeds =((PartyNeedFound) Scheduler.execute(query).data()).getPartyNeeds();

		if (partyNeeds.size() == 1) {
			return ResponseEntity.ok().body(partyNeeds.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createPartyNeed(HttpServletRequest request) throws Exception {

		PartyNeed partyNeedToBeAdded = new PartyNeed();
		try {
			partyNeedToBeAdded = PartyNeedMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createPartyNeed(@RequestBody PartyNeed partyNeedToBeAdded) throws Exception {

		AddPartyNeed command = new AddPartyNeed(partyNeedToBeAdded);
		PartyNeed partyNeed = ((PartyNeedAdded) Scheduler.execute(command).data()).getAddedPartyNeed();
		
		if (partyNeed != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyNeed);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyNeed could not be created.");
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
	public boolean updatePartyNeed(HttpServletRequest request) throws Exception {

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

		PartyNeed partyNeedToBeUpdated = new PartyNeed();

		try {
			partyNeedToBeUpdated = PartyNeedMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyNeed(partyNeedToBeUpdated, partyNeedToBeUpdated.getPartyNeedId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyNeed(@RequestBody PartyNeed partyNeedToBeUpdated,
			@PathVariable String partyNeedId) throws Exception {

		partyNeedToBeUpdated.setPartyNeedId(partyNeedId);

		UpdatePartyNeed command = new UpdatePartyNeed(partyNeedToBeUpdated);

		try {
			if(((PartyNeedUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyNeedId}")
	public ResponseEntity<Object> findById(@PathVariable String partyNeedId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyNeedId", partyNeedId);
		try {

			Object foundPartyNeed = findPartyNeedsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyNeed);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyNeedId}")
	public ResponseEntity<Object> deletePartyNeedByIdUpdated(@PathVariable String partyNeedId) throws Exception {
		DeletePartyNeed command = new DeletePartyNeed(partyNeedId);

		try {
			if (((PartyNeedDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyNeed could not be deleted");

	}

}
