package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose;

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
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.command.AddCommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.command.DeleteCommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.command.UpdateCommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeFound;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.mapper.CommunicationEventPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.query.FindCommunicationEventPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/communicationEventPurposes")
public class CommunicationEventPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventPurpose
	 * @return a List with the CommunicationEventPurposes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventPurposesBy query = new FindCommunicationEventPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventPurpose> communicationEventPurposes =((CommunicationEventPurposeFound) Scheduler.execute(query).data()).getCommunicationEventPurposes();

		if (communicationEventPurposes.size() == 1) {
			return ResponseEntity.ok().body(communicationEventPurposes.get(0));
		}

		return ResponseEntity.ok().body(communicationEventPurposes);

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
	public ResponseEntity<Object> createCommunicationEventPurpose(HttpServletRequest request) throws Exception {

		CommunicationEventPurpose communicationEventPurposeToBeAdded = new CommunicationEventPurpose();
		try {
			communicationEventPurposeToBeAdded = CommunicationEventPurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventPurpose(communicationEventPurposeToBeAdded);

	}

	/**
	 * creates a new CommunicationEventPurpose entry in the ofbiz database
	 * 
	 * @param communicationEventPurposeToBeAdded
	 *            the CommunicationEventPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventPurpose(@RequestBody CommunicationEventPurpose communicationEventPurposeToBeAdded) throws Exception {

		AddCommunicationEventPurpose command = new AddCommunicationEventPurpose(communicationEventPurposeToBeAdded);
		CommunicationEventPurpose communicationEventPurpose = ((CommunicationEventPurposeAdded) Scheduler.execute(command).data()).getAddedCommunicationEventPurpose();
		
		if (communicationEventPurpose != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventPurpose);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventPurpose could not be created.");
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
	public boolean updateCommunicationEventPurpose(HttpServletRequest request) throws Exception {

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

		CommunicationEventPurpose communicationEventPurposeToBeUpdated = new CommunicationEventPurpose();

		try {
			communicationEventPurposeToBeUpdated = CommunicationEventPurposeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventPurpose(communicationEventPurposeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommunicationEventPurpose with the specific Id
	 * 
	 * @param communicationEventPurposeToBeUpdated
	 *            the CommunicationEventPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommunicationEventPurpose(@RequestBody CommunicationEventPurpose communicationEventPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		communicationEventPurposeToBeUpdated.setnull(null);

		UpdateCommunicationEventPurpose command = new UpdateCommunicationEventPurpose(communicationEventPurposeToBeUpdated);

		try {
			if(((CommunicationEventPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventPurposeId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventPurposeId", communicationEventPurposeId);
		try {

			Object foundCommunicationEventPurpose = findCommunicationEventPurposesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventPurpose);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventPurposeId}")
	public ResponseEntity<Object> deleteCommunicationEventPurposeByIdUpdated(@PathVariable String communicationEventPurposeId) throws Exception {
		DeleteCommunicationEventPurpose command = new DeleteCommunicationEventPurpose(communicationEventPurposeId);

		try {
			if (((CommunicationEventPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventPurpose could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/communicationEventPurpose/\" plus one of the following: "
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
