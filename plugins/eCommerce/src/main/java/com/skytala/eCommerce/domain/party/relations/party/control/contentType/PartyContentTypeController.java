package com.skytala.eCommerce.domain.party.relations.party.control.contentType;

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
import com.skytala.eCommerce.domain.party.relations.party.command.contentType.AddPartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.command.contentType.DeletePartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.command.contentType.UpdatePartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.contentType.PartyContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;
import com.skytala.eCommerce.domain.party.relations.party.query.contentType.FindPartyContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyContentTypes")
public class PartyContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContentType
	 * @return a List with the PartyContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContentTypesBy query = new FindPartyContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContentType> partyContentTypes =((PartyContentTypeFound) Scheduler.execute(query).data()).getPartyContentTypes();

		if (partyContentTypes.size() == 1) {
			return ResponseEntity.ok().body(partyContentTypes.get(0));
		}

		return ResponseEntity.ok().body(partyContentTypes);

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
	public ResponseEntity<Object> createPartyContentType(HttpServletRequest request) throws Exception {

		PartyContentType partyContentTypeToBeAdded = new PartyContentType();
		try {
			partyContentTypeToBeAdded = PartyContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyContentType(partyContentTypeToBeAdded);

	}

	/**
	 * creates a new PartyContentType entry in the ofbiz database
	 * 
	 * @param partyContentTypeToBeAdded
	 *            the PartyContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyContentType(@RequestBody PartyContentType partyContentTypeToBeAdded) throws Exception {

		AddPartyContentType command = new AddPartyContentType(partyContentTypeToBeAdded);
		PartyContentType partyContentType = ((PartyContentTypeAdded) Scheduler.execute(command).data()).getAddedPartyContentType();
		
		if (partyContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyContentType could not be created.");
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
	public boolean updatePartyContentType(HttpServletRequest request) throws Exception {

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

		PartyContentType partyContentTypeToBeUpdated = new PartyContentType();

		try {
			partyContentTypeToBeUpdated = PartyContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyContentType(partyContentTypeToBeUpdated, partyContentTypeToBeUpdated.getPartyContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyContentType with the specific Id
	 * 
	 * @param partyContentTypeToBeUpdated
	 *            the PartyContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyContentType(@RequestBody PartyContentType partyContentTypeToBeUpdated,
			@PathVariable String partyContentTypeId) throws Exception {

		partyContentTypeToBeUpdated.setPartyContentTypeId(partyContentTypeId);

		UpdatePartyContentType command = new UpdatePartyContentType(partyContentTypeToBeUpdated);

		try {
			if(((PartyContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContentTypeId", partyContentTypeId);
		try {

			Object foundPartyContentType = findPartyContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyContentTypeId}")
	public ResponseEntity<Object> deletePartyContentTypeByIdUpdated(@PathVariable String partyContentTypeId) throws Exception {
		DeletePartyContentType command = new DeletePartyContentType(partyContentTypeId);

		try {
			if (((PartyContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyContentType could not be deleted");

	}

}
