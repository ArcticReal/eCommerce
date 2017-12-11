package com.skytala.eCommerce.domain.party.relations.party.control.identificationType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.identificationType.AddPartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.identificationType.DeletePartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.identificationType.UpdatePartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.identificationType.PartyIdentificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.identificationType.PartyIdentificationType;
import com.skytala.eCommerce.domain.party.relations.party.query.identificationType.FindPartyIdentificationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyIdentificationTypes")
public class PartyIdentificationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyIdentificationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyIdentificationType
	 * @return a List with the PartyIdentificationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyIdentificationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyIdentificationTypesBy query = new FindPartyIdentificationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyIdentificationType> partyIdentificationTypes =((PartyIdentificationTypeFound) Scheduler.execute(query).data()).getPartyIdentificationTypes();

		if (partyIdentificationTypes.size() == 1) {
			return ResponseEntity.ok().body(partyIdentificationTypes.get(0));
		}

		return ResponseEntity.ok().body(partyIdentificationTypes);

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
	public ResponseEntity<Object> createPartyIdentificationType(HttpServletRequest request) throws Exception {

		PartyIdentificationType partyIdentificationTypeToBeAdded = new PartyIdentificationType();
		try {
			partyIdentificationTypeToBeAdded = PartyIdentificationTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyIdentificationType(partyIdentificationTypeToBeAdded);

	}

	/**
	 * creates a new PartyIdentificationType entry in the ofbiz database
	 * 
	 * @param partyIdentificationTypeToBeAdded
	 *            the PartyIdentificationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyIdentificationType(@RequestBody PartyIdentificationType partyIdentificationTypeToBeAdded) throws Exception {

		AddPartyIdentificationType command = new AddPartyIdentificationType(partyIdentificationTypeToBeAdded);
		PartyIdentificationType partyIdentificationType = ((PartyIdentificationTypeAdded) Scheduler.execute(command).data()).getAddedPartyIdentificationType();
		
		if (partyIdentificationType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyIdentificationType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyIdentificationType could not be created.");
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
	public boolean updatePartyIdentificationType(HttpServletRequest request) throws Exception {

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

		PartyIdentificationType partyIdentificationTypeToBeUpdated = new PartyIdentificationType();

		try {
			partyIdentificationTypeToBeUpdated = PartyIdentificationTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyIdentificationType(partyIdentificationTypeToBeUpdated, partyIdentificationTypeToBeUpdated.getPartyIdentificationTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyIdentificationType with the specific Id
	 * 
	 * @param partyIdentificationTypeToBeUpdated
	 *            the PartyIdentificationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyIdentificationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyIdentificationType(@RequestBody PartyIdentificationType partyIdentificationTypeToBeUpdated,
			@PathVariable String partyIdentificationTypeId) throws Exception {

		partyIdentificationTypeToBeUpdated.setPartyIdentificationTypeId(partyIdentificationTypeId);

		UpdatePartyIdentificationType command = new UpdatePartyIdentificationType(partyIdentificationTypeToBeUpdated);

		try {
			if(((PartyIdentificationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyIdentificationTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyIdentificationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyIdentificationTypeId", partyIdentificationTypeId);
		try {

			Object foundPartyIdentificationType = findPartyIdentificationTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyIdentificationType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyIdentificationTypeId}")
	public ResponseEntity<Object> deletePartyIdentificationTypeByIdUpdated(@PathVariable String partyIdentificationTypeId) throws Exception {
		DeletePartyIdentificationType command = new DeletePartyIdentificationType(partyIdentificationTypeId);

		try {
			if (((PartyIdentificationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyIdentificationType could not be deleted");

	}

}
