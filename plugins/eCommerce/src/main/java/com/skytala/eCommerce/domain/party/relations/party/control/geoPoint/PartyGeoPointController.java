package com.skytala.eCommerce.domain.party.relations.party.control.geoPoint;

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
import com.skytala.eCommerce.domain.party.relations.party.command.geoPoint.AddPartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.party.command.geoPoint.DeletePartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.party.command.geoPoint.UpdatePartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.party.event.geoPoint.PartyGeoPointAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.geoPoint.PartyGeoPointDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.geoPoint.PartyGeoPointFound;
import com.skytala.eCommerce.domain.party.relations.party.event.geoPoint.PartyGeoPointUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.geoPoint.PartyGeoPointMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.geoPoint.PartyGeoPoint;
import com.skytala.eCommerce.domain.party.relations.party.query.geoPoint.FindPartyGeoPointsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyGeoPoints")
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
	@GetMapping("/find")
	public ResponseEntity<List<PartyGeoPoint>> findPartyGeoPointsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyGeoPointsBy query = new FindPartyGeoPointsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyGeoPoint> partyGeoPoints =((PartyGeoPointFound) Scheduler.execute(query).data()).getPartyGeoPoints();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<PartyGeoPoint> createPartyGeoPoint(HttpServletRequest request) throws Exception {

		PartyGeoPoint partyGeoPointToBeAdded = new PartyGeoPoint();
		try {
			partyGeoPointToBeAdded = PartyGeoPointMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PartyGeoPoint> createPartyGeoPoint(@RequestBody PartyGeoPoint partyGeoPointToBeAdded) throws Exception {

		AddPartyGeoPoint command = new AddPartyGeoPoint(partyGeoPointToBeAdded);
		PartyGeoPoint partyGeoPoint = ((PartyGeoPointAdded) Scheduler.execute(command).data()).getAddedPartyGeoPoint();
		
		if (partyGeoPoint != null) 
			return successful(partyGeoPoint);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyGeoPoint(@RequestBody PartyGeoPoint partyGeoPointToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyGeoPointToBeUpdated.setnull(null);

		UpdatePartyGeoPoint command = new UpdatePartyGeoPoint(partyGeoPointToBeUpdated);

		try {
			if(((PartyGeoPointUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyGeoPointId}")
	public ResponseEntity<PartyGeoPoint> findById(@PathVariable String partyGeoPointId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyGeoPointId", partyGeoPointId);
		try {

			List<PartyGeoPoint> foundPartyGeoPoint = findPartyGeoPointsBy(requestParams).getBody();
			if(foundPartyGeoPoint.size()==1){				return successful(foundPartyGeoPoint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyGeoPointId}")
	public ResponseEntity<String> deletePartyGeoPointByIdUpdated(@PathVariable String partyGeoPointId) throws Exception {
		DeletePartyGeoPoint command = new DeletePartyGeoPoint(partyGeoPointId);

		try {
			if (((PartyGeoPointDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
