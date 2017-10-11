package com.skytala.eCommerce.domain.party.relations.partyGeoPoint;

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
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.command.AddPartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.command.DeletePartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.command.UpdatePartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event.PartyGeoPointAdded;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event.PartyGeoPointDeleted;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event.PartyGeoPointFound;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event.PartyGeoPointUpdated;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.mapper.PartyGeoPointMapper;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.model.PartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.query.FindPartyGeoPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyGeoPoints")
public class PartyGeoPointController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyGeoPointController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyGeoPoint
	 * @return a List with the PartyGeoPoints
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyGeoPointsBy query = new FindPartyGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyGeoPoint> partyGeoPoints =((PartyGeoPointFound) Scheduler.execute(query).data()).getPartyGeoPoints();

		if (partyGeoPoints.size() == 1) {
			return ResponseEntity.ok().body(partyGeoPoints.get(0));
		}

		return ResponseEntity.ok().body(partyGeoPoints);

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
	public ResponseEntity<Object> createPartyGeoPoint(HttpServletRequest request) throws Exception {

		PartyGeoPoint partyGeoPointToBeAdded = new PartyGeoPoint();
		try {
			partyGeoPointToBeAdded = PartyGeoPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyGeoPoint(partyGeoPointToBeAdded);

	}

	/**
	 * creates a new PartyGeoPoint entry in the ofbiz database
	 * 
	 * @param partyGeoPointToBeAdded
	 *            the PartyGeoPoint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyGeoPoint(@RequestBody PartyGeoPoint partyGeoPointToBeAdded) throws Exception {

		AddPartyGeoPoint command = new AddPartyGeoPoint(partyGeoPointToBeAdded);
		PartyGeoPoint partyGeoPoint = ((PartyGeoPointAdded) Scheduler.execute(command).data()).getAddedPartyGeoPoint();
		
		if (partyGeoPoint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyGeoPoint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyGeoPoint could not be created.");
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
	public boolean updatePartyGeoPoint(HttpServletRequest request) throws Exception {

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

		PartyGeoPoint partyGeoPointToBeUpdated = new PartyGeoPoint();

		try {
			partyGeoPointToBeUpdated = PartyGeoPointMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyGeoPoint(partyGeoPointToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyGeoPoint with the specific Id
	 * 
	 * @param partyGeoPointToBeUpdated
	 *            the PartyGeoPoint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyGeoPoint(@RequestBody PartyGeoPoint partyGeoPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyGeoPointToBeUpdated.setnull(null);

		UpdatePartyGeoPoint command = new UpdatePartyGeoPoint(partyGeoPointToBeUpdated);

		try {
			if(((PartyGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyGeoPointId}")
	public ResponseEntity<Object> findById(@PathVariable String partyGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyGeoPointId", partyGeoPointId);
		try {

			Object foundPartyGeoPoint = findPartyGeoPointsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyGeoPoint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyGeoPointId}")
	public ResponseEntity<Object> deletePartyGeoPointByIdUpdated(@PathVariable String partyGeoPointId) throws Exception {
		DeletePartyGeoPoint command = new DeletePartyGeoPoint(partyGeoPointId);

		try {
			if (((PartyGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyGeoPoint could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyGeoPoint/\" plus one of the following: "
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