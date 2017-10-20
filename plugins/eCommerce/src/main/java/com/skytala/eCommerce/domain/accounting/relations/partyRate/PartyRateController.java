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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/partyRates")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyRatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRatesBy query = new FindPartyRatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRate> partyRates =((PartyRateFound) Scheduler.execute(query).data()).getPartyRates();

		if (partyRates.size() == 1) {
			return ResponseEntity.ok().body(partyRates.get(0));
		}

		return ResponseEntity.ok().body(partyRates);

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
	public ResponseEntity<Object> createPartyRate(HttpServletRequest request) throws Exception {

		PartyRate partyRateToBeAdded = new PartyRate();
		try {
			partyRateToBeAdded = PartyRateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyRate(partyRateToBeAdded);

	}

	/**
	 * creates a new PartyRate entry in the ofbiz database
	 * 
	 * @param partyRateToBeAdded
	 *            the PartyRate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyRate(@RequestBody PartyRate partyRateToBeAdded) throws Exception {

		AddPartyRate command = new AddPartyRate(partyRateToBeAdded);
		PartyRate partyRate = ((PartyRateAdded) Scheduler.execute(command).data()).getAddedPartyRate();
		
		if (partyRate != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyRate);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyRate could not be created.");
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
	public boolean updatePartyRate(HttpServletRequest request) throws Exception {

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

		PartyRate partyRateToBeUpdated = new PartyRate();

		try {
			partyRateToBeUpdated = PartyRateMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyRate(partyRateToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePartyRate(@RequestBody PartyRate partyRateToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyRateToBeUpdated.setnull(null);

		UpdatePartyRate command = new UpdatePartyRate(partyRateToBeUpdated);

		try {
			if(((PartyRateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyRateId}")
	public ResponseEntity<Object> findById(@PathVariable String partyRateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRateId", partyRateId);
		try {

			Object foundPartyRate = findPartyRatesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyRate);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyRateId}")
	public ResponseEntity<Object> deletePartyRateByIdUpdated(@PathVariable String partyRateId) throws Exception {
		DeletePartyRate command = new DeletePartyRate(partyRateId);

		try {
			if (((PartyRateDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyRate could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyRate/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
