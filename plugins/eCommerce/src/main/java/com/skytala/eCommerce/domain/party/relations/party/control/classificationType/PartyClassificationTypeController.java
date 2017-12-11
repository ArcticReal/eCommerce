package com.skytala.eCommerce.domain.party.relations.party.control.classificationType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.classificationType.AddPartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.classificationType.DeletePartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.command.classificationType.UpdatePartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.classificationType.PartyClassificationTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classificationType.PartyClassificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classificationType.PartyClassificationType;
import com.skytala.eCommerce.domain.party.relations.party.query.classificationType.FindPartyClassificationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyClassificationTypes")
public class PartyClassificationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyClassificationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyClassificationType
	 * @return a List with the PartyClassificationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyClassificationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyClassificationTypesBy query = new FindPartyClassificationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyClassificationType> partyClassificationTypes =((PartyClassificationTypeFound) Scheduler.execute(query).data()).getPartyClassificationTypes();

		if (partyClassificationTypes.size() == 1) {
			return ResponseEntity.ok().body(partyClassificationTypes.get(0));
		}

		return ResponseEntity.ok().body(partyClassificationTypes);

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
	public ResponseEntity<Object> createPartyClassificationType(HttpServletRequest request) throws Exception {

		PartyClassificationType partyClassificationTypeToBeAdded = new PartyClassificationType();
		try {
			partyClassificationTypeToBeAdded = PartyClassificationTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyClassificationType(partyClassificationTypeToBeAdded);

	}

	/**
	 * creates a new PartyClassificationType entry in the ofbiz database
	 * 
	 * @param partyClassificationTypeToBeAdded
	 *            the PartyClassificationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyClassificationType(@RequestBody PartyClassificationType partyClassificationTypeToBeAdded) throws Exception {

		AddPartyClassificationType command = new AddPartyClassificationType(partyClassificationTypeToBeAdded);
		PartyClassificationType partyClassificationType = ((PartyClassificationTypeAdded) Scheduler.execute(command).data()).getAddedPartyClassificationType();
		
		if (partyClassificationType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyClassificationType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyClassificationType could not be created.");
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
	public boolean updatePartyClassificationType(HttpServletRequest request) throws Exception {

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

		PartyClassificationType partyClassificationTypeToBeUpdated = new PartyClassificationType();

		try {
			partyClassificationTypeToBeUpdated = PartyClassificationTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyClassificationType(partyClassificationTypeToBeUpdated, partyClassificationTypeToBeUpdated.getPartyClassificationTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyClassificationType with the specific Id
	 * 
	 * @param partyClassificationTypeToBeUpdated
	 *            the PartyClassificationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyClassificationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyClassificationType(@RequestBody PartyClassificationType partyClassificationTypeToBeUpdated,
			@PathVariable String partyClassificationTypeId) throws Exception {

		partyClassificationTypeToBeUpdated.setPartyClassificationTypeId(partyClassificationTypeId);

		UpdatePartyClassificationType command = new UpdatePartyClassificationType(partyClassificationTypeToBeUpdated);

		try {
			if(((PartyClassificationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyClassificationTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyClassificationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyClassificationTypeId", partyClassificationTypeId);
		try {

			Object foundPartyClassificationType = findPartyClassificationTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyClassificationType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyClassificationTypeId}")
	public ResponseEntity<Object> deletePartyClassificationTypeByIdUpdated(@PathVariable String partyClassificationTypeId) throws Exception {
		DeletePartyClassificationType command = new DeletePartyClassificationType(partyClassificationTypeId);

		try {
			if (((PartyClassificationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyClassificationType could not be deleted");

	}

}
