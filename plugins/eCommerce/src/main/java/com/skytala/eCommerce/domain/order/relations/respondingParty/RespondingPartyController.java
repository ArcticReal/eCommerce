package com.skytala.eCommerce.domain.order.relations.respondingParty;

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
import com.skytala.eCommerce.domain.order.relations.respondingParty.command.AddRespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.command.DeleteRespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.command.UpdateRespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyAdded;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyDeleted;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyFound;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyUpdated;
import com.skytala.eCommerce.domain.order.relations.respondingParty.mapper.RespondingPartyMapper;
import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
import com.skytala.eCommerce.domain.order.relations.respondingParty.query.FindRespondingPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/respondingPartys")
public class RespondingPartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RespondingPartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RespondingParty
	 * @return a List with the RespondingPartys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRespondingPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRespondingPartysBy query = new FindRespondingPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RespondingParty> respondingPartys =((RespondingPartyFound) Scheduler.execute(query).data()).getRespondingPartys();

		if (respondingPartys.size() == 1) {
			return ResponseEntity.ok().body(respondingPartys.get(0));
		}

		return ResponseEntity.ok().body(respondingPartys);

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
	public ResponseEntity<Object> createRespondingParty(HttpServletRequest request) throws Exception {

		RespondingParty respondingPartyToBeAdded = new RespondingParty();
		try {
			respondingPartyToBeAdded = RespondingPartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRespondingParty(respondingPartyToBeAdded);

	}

	/**
	 * creates a new RespondingParty entry in the ofbiz database
	 * 
	 * @param respondingPartyToBeAdded
	 *            the RespondingParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRespondingParty(@RequestBody RespondingParty respondingPartyToBeAdded) throws Exception {

		AddRespondingParty command = new AddRespondingParty(respondingPartyToBeAdded);
		RespondingParty respondingParty = ((RespondingPartyAdded) Scheduler.execute(command).data()).getAddedRespondingParty();
		
		if (respondingParty != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(respondingParty);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RespondingParty could not be created.");
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
	public boolean updateRespondingParty(HttpServletRequest request) throws Exception {

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

		RespondingParty respondingPartyToBeUpdated = new RespondingParty();

		try {
			respondingPartyToBeUpdated = RespondingPartyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRespondingParty(respondingPartyToBeUpdated, respondingPartyToBeUpdated.getRespondingPartySeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RespondingParty with the specific Id
	 * 
	 * @param respondingPartyToBeUpdated
	 *            the RespondingParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{respondingPartySeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRespondingParty(@RequestBody RespondingParty respondingPartyToBeUpdated,
			@PathVariable String respondingPartySeqId) throws Exception {

		respondingPartyToBeUpdated.setRespondingPartySeqId(respondingPartySeqId);

		UpdateRespondingParty command = new UpdateRespondingParty(respondingPartyToBeUpdated);

		try {
			if(((RespondingPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{respondingPartyId}")
	public ResponseEntity<Object> findById(@PathVariable String respondingPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("respondingPartyId", respondingPartyId);
		try {

			Object foundRespondingParty = findRespondingPartysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRespondingParty);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{respondingPartyId}")
	public ResponseEntity<Object> deleteRespondingPartyByIdUpdated(@PathVariable String respondingPartyId) throws Exception {
		DeleteRespondingParty command = new DeleteRespondingParty(respondingPartyId);

		try {
			if (((RespondingPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RespondingParty could not be deleted");

	}

}
