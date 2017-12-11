package com.skytala.eCommerce.domain.party.relations.party.control.relationshipType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.relationshipType.AddPartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.command.relationshipType.DeletePartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.command.relationshipType.UpdatePartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.relationshipType.PartyRelationshipTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.relationshipType.PartyRelationshipTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.relationshipType.PartyRelationshipType;
import com.skytala.eCommerce.domain.party.relations.party.query.relationshipType.FindPartyRelationshipTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyRelationshipTypes")
public class PartyRelationshipTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyRelationshipTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyRelationshipType
	 * @return a List with the PartyRelationshipTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyRelationshipTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyRelationshipTypesBy query = new FindPartyRelationshipTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyRelationshipType> partyRelationshipTypes =((PartyRelationshipTypeFound) Scheduler.execute(query).data()).getPartyRelationshipTypes();

		if (partyRelationshipTypes.size() == 1) {
			return ResponseEntity.ok().body(partyRelationshipTypes.get(0));
		}

		return ResponseEntity.ok().body(partyRelationshipTypes);

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
	public ResponseEntity<Object> createPartyRelationshipType(HttpServletRequest request) throws Exception {

		PartyRelationshipType partyRelationshipTypeToBeAdded = new PartyRelationshipType();
		try {
			partyRelationshipTypeToBeAdded = PartyRelationshipTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyRelationshipType(partyRelationshipTypeToBeAdded);

	}

	/**
	 * creates a new PartyRelationshipType entry in the ofbiz database
	 * 
	 * @param partyRelationshipTypeToBeAdded
	 *            the PartyRelationshipType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyRelationshipType(@RequestBody PartyRelationshipType partyRelationshipTypeToBeAdded) throws Exception {

		AddPartyRelationshipType command = new AddPartyRelationshipType(partyRelationshipTypeToBeAdded);
		PartyRelationshipType partyRelationshipType = ((PartyRelationshipTypeAdded) Scheduler.execute(command).data()).getAddedPartyRelationshipType();
		
		if (partyRelationshipType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyRelationshipType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyRelationshipType could not be created.");
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
	public boolean updatePartyRelationshipType(HttpServletRequest request) throws Exception {

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

		PartyRelationshipType partyRelationshipTypeToBeUpdated = new PartyRelationshipType();

		try {
			partyRelationshipTypeToBeUpdated = PartyRelationshipTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyRelationshipType(partyRelationshipTypeToBeUpdated, partyRelationshipTypeToBeUpdated.getPartyRelationshipTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyRelationshipType with the specific Id
	 * 
	 * @param partyRelationshipTypeToBeUpdated
	 *            the PartyRelationshipType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyRelationshipTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyRelationshipType(@RequestBody PartyRelationshipType partyRelationshipTypeToBeUpdated,
			@PathVariable String partyRelationshipTypeId) throws Exception {

		partyRelationshipTypeToBeUpdated.setPartyRelationshipTypeId(partyRelationshipTypeId);

		UpdatePartyRelationshipType command = new UpdatePartyRelationshipType(partyRelationshipTypeToBeUpdated);

		try {
			if(((PartyRelationshipTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyRelationshipTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyRelationshipTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyRelationshipTypeId", partyRelationshipTypeId);
		try {

			Object foundPartyRelationshipType = findPartyRelationshipTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyRelationshipType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyRelationshipTypeId}")
	public ResponseEntity<Object> deletePartyRelationshipTypeByIdUpdated(@PathVariable String partyRelationshipTypeId) throws Exception {
		DeletePartyRelationshipType command = new DeletePartyRelationshipType(partyRelationshipTypeId);

		try {
			if (((PartyRelationshipTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyRelationshipType could not be deleted");

	}

}
