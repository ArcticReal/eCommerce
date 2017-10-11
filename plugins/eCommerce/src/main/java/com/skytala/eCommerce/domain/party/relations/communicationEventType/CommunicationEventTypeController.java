package com.skytala.eCommerce.domain.party.relations.communicationEventType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.command.AddCommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.command.DeleteCommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.command.UpdateCommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.event.CommunicationEventTypeAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.event.CommunicationEventTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.event.CommunicationEventTypeFound;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.event.CommunicationEventTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.mapper.CommunicationEventTypeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.model.CommunicationEventType;
import com.skytala.eCommerce.domain.party.relations.communicationEventType.query.FindCommunicationEventTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/communicationEventTypes")
public class CommunicationEventTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventType
	 * @return a List with the CommunicationEventTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventTypesBy query = new FindCommunicationEventTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventType> communicationEventTypes =((CommunicationEventTypeFound) Scheduler.execute(query).data()).getCommunicationEventTypes();

		if (communicationEventTypes.size() == 1) {
			return ResponseEntity.ok().body(communicationEventTypes.get(0));
		}

		return ResponseEntity.ok().body(communicationEventTypes);

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
	public ResponseEntity<Object> createCommunicationEventType(HttpServletRequest request) throws Exception {

		CommunicationEventType communicationEventTypeToBeAdded = new CommunicationEventType();
		try {
			communicationEventTypeToBeAdded = CommunicationEventTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventType(communicationEventTypeToBeAdded);

	}

	/**
	 * creates a new CommunicationEventType entry in the ofbiz database
	 * 
	 * @param communicationEventTypeToBeAdded
	 *            the CommunicationEventType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventType(@RequestBody CommunicationEventType communicationEventTypeToBeAdded) throws Exception {

		AddCommunicationEventType command = new AddCommunicationEventType(communicationEventTypeToBeAdded);
		CommunicationEventType communicationEventType = ((CommunicationEventTypeAdded) Scheduler.execute(command).data()).getAddedCommunicationEventType();
		
		if (communicationEventType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventType could not be created.");
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
	public boolean updateCommunicationEventType(HttpServletRequest request) throws Exception {

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

		CommunicationEventType communicationEventTypeToBeUpdated = new CommunicationEventType();

		try {
			communicationEventTypeToBeUpdated = CommunicationEventTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventType(communicationEventTypeToBeUpdated, communicationEventTypeToBeUpdated.getCommunicationEventTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommunicationEventType with the specific Id
	 * 
	 * @param communicationEventTypeToBeUpdated
	 *            the CommunicationEventType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{communicationEventTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommunicationEventType(@RequestBody CommunicationEventType communicationEventTypeToBeUpdated,
			@PathVariable String communicationEventTypeId) throws Exception {

		communicationEventTypeToBeUpdated.setCommunicationEventTypeId(communicationEventTypeId);

		UpdateCommunicationEventType command = new UpdateCommunicationEventType(communicationEventTypeToBeUpdated);

		try {
			if(((CommunicationEventTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventTypeId", communicationEventTypeId);
		try {

			Object foundCommunicationEventType = findCommunicationEventTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventTypeId}")
	public ResponseEntity<Object> deleteCommunicationEventTypeByIdUpdated(@PathVariable String communicationEventTypeId) throws Exception {
		DeleteCommunicationEventType command = new DeleteCommunicationEventType(communicationEventTypeId);

		try {
			if (((CommunicationEventTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/communicationEventType/\" plus one of the following: "
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
