package com.skytala.eCommerce.domain.party.relations.party.control.classification;

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
import com.skytala.eCommerce.domain.party.relations.party.command.classification.AddPartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.command.classification.DeletePartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.command.classification.UpdatePartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationFound;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classification.PartyClassificationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;
import com.skytala.eCommerce.domain.party.relations.party.query.classification.FindPartyClassificationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyClassifications")
public class PartyClassificationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyClassificationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyClassification
	 * @return a List with the PartyClassifications
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyClassificationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyClassificationsBy query = new FindPartyClassificationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyClassification> partyClassifications =((PartyClassificationFound) Scheduler.execute(query).data()).getPartyClassifications();

		if (partyClassifications.size() == 1) {
			return ResponseEntity.ok().body(partyClassifications.get(0));
		}

		return ResponseEntity.ok().body(partyClassifications);

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
	public ResponseEntity<Object> createPartyClassification(HttpServletRequest request) throws Exception {

		PartyClassification partyClassificationToBeAdded = new PartyClassification();
		try {
			partyClassificationToBeAdded = PartyClassificationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyClassification(partyClassificationToBeAdded);

	}

	/**
	 * creates a new PartyClassification entry in the ofbiz database
	 * 
	 * @param partyClassificationToBeAdded
	 *            the PartyClassification thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyClassification(@RequestBody PartyClassification partyClassificationToBeAdded) throws Exception {

		AddPartyClassification command = new AddPartyClassification(partyClassificationToBeAdded);
		PartyClassification partyClassification = ((PartyClassificationAdded) Scheduler.execute(command).data()).getAddedPartyClassification();
		
		if (partyClassification != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyClassification);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyClassification could not be created.");
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
	public boolean updatePartyClassification(HttpServletRequest request) throws Exception {

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

		PartyClassification partyClassificationToBeUpdated = new PartyClassification();

		try {
			partyClassificationToBeUpdated = PartyClassificationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyClassification(partyClassificationToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyClassification with the specific Id
	 * 
	 * @param partyClassificationToBeUpdated
	 *            the PartyClassification thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyClassification(@RequestBody PartyClassification partyClassificationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyClassificationToBeUpdated.setnull(null);

		UpdatePartyClassification command = new UpdatePartyClassification(partyClassificationToBeUpdated);

		try {
			if(((PartyClassificationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyClassificationId}")
	public ResponseEntity<Object> findById(@PathVariable String partyClassificationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyClassificationId", partyClassificationId);
		try {

			Object foundPartyClassification = findPartyClassificationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyClassification);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyClassificationId}")
	public ResponseEntity<Object> deletePartyClassificationByIdUpdated(@PathVariable String partyClassificationId) throws Exception {
		DeletePartyClassification command = new DeletePartyClassification(partyClassificationId);

		try {
			if (((PartyClassificationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyClassification could not be deleted");

	}

}
