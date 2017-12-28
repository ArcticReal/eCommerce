package com.skytala.eCommerce.domain.accounting.relations.partyRate;

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
import com.skytala.eCommerce.domain.accounting.relations.partyRate.command.AddPartyRate;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.command.DeletePartyRate;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.command.UpdatePartyRate;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateDeleted;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateFound;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.mapper.PartyRateMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.query.FindPartyRatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/partyRates")
public class PartyRateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyRateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyRate
	 * @return a List with the PartyRates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyRate>> findPartyRatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRatesBy query = new FindPartyRatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRate> partyRates =((PartyRateFound) Scheduler.execute(query).data()).getPartyRates();

		return ResponseEntity.ok().body(partyRates);

	}

	/**
	 * creates a new PartyRate entry in the ofbiz database
	 * 
	 * @param partyRateToBeAdded
	 *            the PartyRate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyRate> createPartyRate(@RequestBody PartyRate partyRateToBeAdded) throws Exception {

		AddPartyRate command = new AddPartyRate(partyRateToBeAdded);
		PartyRate partyRate = ((PartyRateAdded) Scheduler.execute(command).data()).getAddedPartyRate();
		
		if (partyRate != null) 
			return successful(partyRate);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyRate with the specific Id
	 * 
	 * @param partyRateToBeUpdated
	 *            the PartyRate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyRate(@RequestBody PartyRate partyRateToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyRateToBeUpdated.setnull(null);

		UpdatePartyRate command = new UpdatePartyRate(partyRateToBeUpdated);

		try {
			if(((PartyRateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyRateId}")
	public ResponseEntity<PartyRate> findById(@PathVariable String partyRateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRateId", partyRateId);
		try {

			List<PartyRate> foundPartyRate = findPartyRatesBy(requestParams).getBody();
			if(foundPartyRate.size()==1){				return successful(foundPartyRate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyRateId}")
	public ResponseEntity<String> deletePartyRateByIdUpdated(@PathVariable String partyRateId) throws Exception {
		DeletePartyRate command = new DeletePartyRate(partyRateId);

		try {
			if (((PartyRateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
