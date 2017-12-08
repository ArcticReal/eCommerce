package com.skytala.eCommerce.domain.humanres.relations.partyQual.control.type;

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
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type.AddPartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type.DeletePartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type.UpdatePartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.type.PartyQualTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.query.type.FindPartyQualTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyQualTypes")
public class PartyQualTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyQualTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyQualType
	 * @return a List with the PartyQualTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyQualTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyQualTypesBy query = new FindPartyQualTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyQualType> partyQualTypes =((PartyQualTypeFound) Scheduler.execute(query).data()).getPartyQualTypes();

		if (partyQualTypes.size() == 1) {
			return ResponseEntity.ok().body(partyQualTypes.get(0));
		}

		return ResponseEntity.ok().body(partyQualTypes);

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
	public ResponseEntity<Object> createPartyQualType(HttpServletRequest request) throws Exception {

		PartyQualType partyQualTypeToBeAdded = new PartyQualType();
		try {
			partyQualTypeToBeAdded = PartyQualTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyQualType(partyQualTypeToBeAdded);

	}

	/**
	 * creates a new PartyQualType entry in the ofbiz database
	 * 
	 * @param partyQualTypeToBeAdded
	 *            the PartyQualType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyQualType(@RequestBody PartyQualType partyQualTypeToBeAdded) throws Exception {

		AddPartyQualType command = new AddPartyQualType(partyQualTypeToBeAdded);
		PartyQualType partyQualType = ((PartyQualTypeAdded) Scheduler.execute(command).data()).getAddedPartyQualType();
		
		if (partyQualType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyQualType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyQualType could not be created.");
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
	public boolean updatePartyQualType(HttpServletRequest request) throws Exception {

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

		PartyQualType partyQualTypeToBeUpdated = new PartyQualType();

		try {
			partyQualTypeToBeUpdated = PartyQualTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyQualType(partyQualTypeToBeUpdated, partyQualTypeToBeUpdated.getPartyQualTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyQualType with the specific Id
	 * 
	 * @param partyQualTypeToBeUpdated
	 *            the PartyQualType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyQualTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyQualType(@RequestBody PartyQualType partyQualTypeToBeUpdated,
			@PathVariable String partyQualTypeId) throws Exception {

		partyQualTypeToBeUpdated.setPartyQualTypeId(partyQualTypeId);

		UpdatePartyQualType command = new UpdatePartyQualType(partyQualTypeToBeUpdated);

		try {
			if(((PartyQualTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyQualTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyQualTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyQualTypeId", partyQualTypeId);
		try {

			Object foundPartyQualType = findPartyQualTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyQualType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyQualTypeId}")
	public ResponseEntity<Object> deletePartyQualTypeByIdUpdated(@PathVariable String partyQualTypeId) throws Exception {
		DeletePartyQualType command = new DeletePartyQualType(partyQualTypeId);

		try {
			if (((PartyQualTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyQualType could not be deleted");

	}

}
