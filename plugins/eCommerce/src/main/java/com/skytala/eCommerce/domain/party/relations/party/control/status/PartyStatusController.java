package com.skytala.eCommerce.domain.party.relations.party.control.status;

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
import com.skytala.eCommerce.domain.party.relations.party.command.status.AddPartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.command.status.DeletePartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.command.status.UpdatePartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusFound;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.status.PartyStatusMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.status.PartyStatus;
import com.skytala.eCommerce.domain.party.relations.party.query.status.FindPartyStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/partyStatuss")
public class PartyStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyStatus
	 * @return a List with the PartyStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyStatussBy query = new FindPartyStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyStatus> partyStatuss =((PartyStatusFound) Scheduler.execute(query).data()).getPartyStatuss();

		if (partyStatuss.size() == 1) {
			return ResponseEntity.ok().body(partyStatuss.get(0));
		}

		return ResponseEntity.ok().body(partyStatuss);

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
	public ResponseEntity<Object> createPartyStatus(HttpServletRequest request) throws Exception {

		PartyStatus partyStatusToBeAdded = new PartyStatus();
		try {
			partyStatusToBeAdded = PartyStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyStatus(partyStatusToBeAdded);

	}

	/**
	 * creates a new PartyStatus entry in the ofbiz database
	 * 
	 * @param partyStatusToBeAdded
	 *            the PartyStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyStatus(@RequestBody PartyStatus partyStatusToBeAdded) throws Exception {

		AddPartyStatus command = new AddPartyStatus(partyStatusToBeAdded);
		PartyStatus partyStatus = ((PartyStatusAdded) Scheduler.execute(command).data()).getAddedPartyStatus();
		
		if (partyStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyStatus could not be created.");
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
	public boolean updatePartyStatus(HttpServletRequest request) throws Exception {

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

		PartyStatus partyStatusToBeUpdated = new PartyStatus();

		try {
			partyStatusToBeUpdated = PartyStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyStatus(partyStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyStatus with the specific Id
	 * 
	 * @param partyStatusToBeUpdated
	 *            the PartyStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyStatus(@RequestBody PartyStatus partyStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyStatusToBeUpdated.setnull(null);

		UpdatePartyStatus command = new UpdatePartyStatus(partyStatusToBeUpdated);

		try {
			if(((PartyStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String partyStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyStatusId", partyStatusId);
		try {

			Object foundPartyStatus = findPartyStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyStatusId}")
	public ResponseEntity<Object> deletePartyStatusByIdUpdated(@PathVariable String partyStatusId) throws Exception {
		DeletePartyStatus command = new DeletePartyStatus(partyStatusId);

		try {
			if (((PartyStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyStatus/\" plus one of the following: "
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
