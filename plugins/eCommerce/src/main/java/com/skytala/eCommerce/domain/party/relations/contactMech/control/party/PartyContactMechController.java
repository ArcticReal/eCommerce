package com.skytala.eCommerce.domain.party.relations.contactMech.control.party;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.skytala.eCommerce.framework.util.TimestampUtil;
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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.party.AddPartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.party.DeletePartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.party.UpdatePartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.party.PartyContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.party.FindPartyContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/contactMech/partyContactMechs")
public class PartyContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContactMech
	 * @return a List with the PartyContactMechs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<PartyContactMech>> findPartyContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContactMechsBy query = new FindPartyContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContactMech> partyContactMechs =((PartyContactMechFound) Scheduler.execute(query).data()).getPartyContactMechs();



		return ResponseEntity.ok().body(partyContactMechs);

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
	public ResponseEntity<Object> createPartyContactMech(HttpServletRequest request) throws Exception {

		PartyContactMech partyContactMechToBeAdded = new PartyContactMech();
		try {
			partyContactMechToBeAdded = PartyContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyContactMech(partyContactMechToBeAdded);

	}

	/**
	 * creates a new PartyContactMech entry in the ofbiz database
	 * 
	 * @param partyContactMechToBeAdded
	 *            the PartyContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyContactMech(@RequestBody PartyContactMech partyContactMechToBeAdded) throws Exception {

		AddPartyContactMech command = new AddPartyContactMech(partyContactMechToBeAdded);
		PartyContactMech partyContactMech = ((PartyContactMechAdded) Scheduler.execute(command).data()).getAddedPartyContactMech();
		
		if (partyContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyContactMech could not be created.");
	}

	/**
	 * Updates the PartyContactMech with the specific Id
	 * 
	 * @param partyContactMechToBeUpdated
	 *            the PartyContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyContactMech(@RequestBody PartyContactMech partyContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyContactMechToBeUpdated.setnull(null);

		UpdatePartyContactMech command = new UpdatePartyContactMech(partyContactMechToBeUpdated);

		try {
			if(((PartyContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyId}")
	public ResponseEntity<List<PartyContactMech>> findByPartyId(@PathVariable String partyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyId", partyId);

		try {

			List<PartyContactMech> foundPartyContactMechs = findPartyContactMechsBy(requestParams).getBody();

			foundPartyContactMechs = foundPartyContactMechs
                    .stream()
                    .filter(partyContactMech -> {
                        if(partyContactMech.getFromDate().before(TimestampUtil.currentTime())&&
                                (partyContactMech.getThruDate()==null
                                ||partyContactMech.getThruDate().after(TimestampUtil.currentTime()))
							&&partyContactMech.getPartyId().equals(partyId))
                            return true;
                        else
                            return false;
                    })
                    .collect(Collectors.toList());



			return ResponseEntity.status(HttpStatus.OK).body(foundPartyContactMechs);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyContactMechId}")
	public ResponseEntity<Object> deletePartyContactMechByIdUpdated(@PathVariable String partyContactMechId) throws Exception {
		DeletePartyContactMech command = new DeletePartyContactMech(partyContactMechId);

		try {
			if (((PartyContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyContactMech could not be deleted");

	}

}
