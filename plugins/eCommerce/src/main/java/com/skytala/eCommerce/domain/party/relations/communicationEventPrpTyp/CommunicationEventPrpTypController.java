package com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp;

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
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.command.AddCommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.command.DeleteCommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.command.UpdateCommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.event.CommunicationEventPrpTypAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.event.CommunicationEventPrpTypDeleted;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.event.CommunicationEventPrpTypFound;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.event.CommunicationEventPrpTypUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.mapper.CommunicationEventPrpTypMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.model.CommunicationEventPrpTyp;
import com.skytala.eCommerce.domain.party.relations.communicationEventPrpTyp.query.FindCommunicationEventPrpTypsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/communicationEventPrpTyps")
public class CommunicationEventPrpTypController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CommunicationEventPrpTypController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CommunicationEventPrpTyp
	 * @return a List with the CommunicationEventPrpTyps
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCommunicationEventPrpTypsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCommunicationEventPrpTypsBy query = new FindCommunicationEventPrpTypsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CommunicationEventPrpTyp> communicationEventPrpTyps =((CommunicationEventPrpTypFound) Scheduler.execute(query).data()).getCommunicationEventPrpTyps();

		if (communicationEventPrpTyps.size() == 1) {
			return ResponseEntity.ok().body(communicationEventPrpTyps.get(0));
		}

		return ResponseEntity.ok().body(communicationEventPrpTyps);

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
	public ResponseEntity<Object> createCommunicationEventPrpTyp(HttpServletRequest request) throws Exception {

		CommunicationEventPrpTyp communicationEventPrpTypToBeAdded = new CommunicationEventPrpTyp();
		try {
			communicationEventPrpTypToBeAdded = CommunicationEventPrpTypMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCommunicationEventPrpTyp(communicationEventPrpTypToBeAdded);

	}

	/**
	 * creates a new CommunicationEventPrpTyp entry in the ofbiz database
	 * 
	 * @param communicationEventPrpTypToBeAdded
	 *            the CommunicationEventPrpTyp thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCommunicationEventPrpTyp(@RequestBody CommunicationEventPrpTyp communicationEventPrpTypToBeAdded) throws Exception {

		AddCommunicationEventPrpTyp command = new AddCommunicationEventPrpTyp(communicationEventPrpTypToBeAdded);
		CommunicationEventPrpTyp communicationEventPrpTyp = ((CommunicationEventPrpTypAdded) Scheduler.execute(command).data()).getAddedCommunicationEventPrpTyp();
		
		if (communicationEventPrpTyp != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(communicationEventPrpTyp);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CommunicationEventPrpTyp could not be created.");
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
	public boolean updateCommunicationEventPrpTyp(HttpServletRequest request) throws Exception {

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

		CommunicationEventPrpTyp communicationEventPrpTypToBeUpdated = new CommunicationEventPrpTyp();

		try {
			communicationEventPrpTypToBeUpdated = CommunicationEventPrpTypMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCommunicationEventPrpTyp(communicationEventPrpTypToBeUpdated, communicationEventPrpTypToBeUpdated.getCommunicationEventPrpTypId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CommunicationEventPrpTyp with the specific Id
	 * 
	 * @param communicationEventPrpTypToBeUpdated
	 *            the CommunicationEventPrpTyp thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{communicationEventPrpTypId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCommunicationEventPrpTyp(@RequestBody CommunicationEventPrpTyp communicationEventPrpTypToBeUpdated,
			@PathVariable String communicationEventPrpTypId) throws Exception {

		communicationEventPrpTypToBeUpdated.setCommunicationEventPrpTypId(communicationEventPrpTypId);

		UpdateCommunicationEventPrpTyp command = new UpdateCommunicationEventPrpTyp(communicationEventPrpTypToBeUpdated);

		try {
			if(((CommunicationEventPrpTypUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{communicationEventPrpTypId}")
	public ResponseEntity<Object> findById(@PathVariable String communicationEventPrpTypId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("communicationEventPrpTypId", communicationEventPrpTypId);
		try {

			Object foundCommunicationEventPrpTyp = findCommunicationEventPrpTypsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCommunicationEventPrpTyp);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{communicationEventPrpTypId}")
	public ResponseEntity<Object> deleteCommunicationEventPrpTypByIdUpdated(@PathVariable String communicationEventPrpTypId) throws Exception {
		DeleteCommunicationEventPrpTyp command = new DeleteCommunicationEventPrpTyp(communicationEventPrpTypId);

		try {
			if (((CommunicationEventPrpTypDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CommunicationEventPrpTyp could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/communicationEventPrpTyp/\" plus one of the following: "
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
