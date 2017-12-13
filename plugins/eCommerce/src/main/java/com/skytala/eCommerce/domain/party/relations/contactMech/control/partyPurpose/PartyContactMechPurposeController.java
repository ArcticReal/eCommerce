package com.skytala.eCommerce.domain.party.relations.contactMech.control.partyPurpose;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.partyPurpose.AddPartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.partyPurpose.DeletePartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.partyPurpose.UpdatePartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.partyPurpose.PartyContactMechPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.partyPurpose.PartyContactMechPurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.partyPurpose.FindPartyContactMechPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/contactMech/partyContactMechPurposes")
public class PartyContactMechPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContactMechPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContactMechPurpose
	 * @return a List with the PartyContactMechPurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyContactMechPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContactMechPurposesBy query = new FindPartyContactMechPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContactMechPurpose> partyContactMechPurposes =((PartyContactMechPurposeFound) Scheduler.execute(query).data()).getPartyContactMechPurposes();

		if (partyContactMechPurposes.size() == 1) {
			return ResponseEntity.ok().body(partyContactMechPurposes.get(0));
		}

		return ResponseEntity.ok().body(partyContactMechPurposes);

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
	public ResponseEntity<Object> createPartyContactMechPurpose(HttpServletRequest request) throws Exception {

		PartyContactMechPurpose partyContactMechPurposeToBeAdded = new PartyContactMechPurpose();
		try {
			partyContactMechPurposeToBeAdded = PartyContactMechPurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyContactMechPurpose(partyContactMechPurposeToBeAdded);

	}

	/**
	 * creates a new PartyContactMechPurpose entry in the ofbiz database
	 * 
	 * @param partyContactMechPurposeToBeAdded
	 *            the PartyContactMechPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyContactMechPurpose(@RequestBody PartyContactMechPurpose partyContactMechPurposeToBeAdded) throws Exception {

		AddPartyContactMechPurpose command = new AddPartyContactMechPurpose(partyContactMechPurposeToBeAdded);
		PartyContactMechPurpose partyContactMechPurpose = ((PartyContactMechPurposeAdded) Scheduler.execute(command).data()).getAddedPartyContactMechPurpose();
		
		if (partyContactMechPurpose != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyContactMechPurpose);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyContactMechPurpose could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePartyContactMechPurpose(HttpServletRequest request) throws Exception {

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

		PartyContactMechPurpose partyContactMechPurposeToBeUpdated = new PartyContactMechPurpose();

		try {
			partyContactMechPurposeToBeUpdated = PartyContactMechPurposeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyContactMechPurpose(partyContactMechPurposeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyContactMechPurpose with the specific Id
	 * 
	 * @param partyContactMechPurposeToBeUpdated
	 *            the PartyContactMechPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyContactMechPurpose(@RequestBody PartyContactMechPurpose partyContactMechPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyContactMechPurposeToBeUpdated.setnull(null);

		UpdatePartyContactMechPurpose command = new UpdatePartyContactMechPurpose(partyContactMechPurposeToBeUpdated);

		try {
			if(((PartyContactMechPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyContactMechPurposeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyContactMechPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContactMechPurposeId", partyContactMechPurposeId);
		try {

			Object foundPartyContactMechPurpose = findPartyContactMechPurposesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyContactMechPurpose);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyContactMechPurposeId}")
	public ResponseEntity<Object> deletePartyContactMechPurposeByIdUpdated(@PathVariable String partyContactMechPurposeId) throws Exception {
		DeletePartyContactMechPurpose command = new DeletePartyContactMechPurpose(partyContactMechPurposeId);

		try {
			if (((PartyContactMechPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyContactMechPurpose could not be deleted");

	}

}
