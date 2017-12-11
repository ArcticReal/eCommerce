package com.skytala.eCommerce.domain.party.relations.party.control.profileDefault;

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
import com.skytala.eCommerce.domain.party.relations.party.command.profileDefault.AddPartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.command.profileDefault.DeletePartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.command.profileDefault.UpdatePartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultFound;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.profileDefault.PartyProfileDefaultMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;
import com.skytala.eCommerce.domain.party.relations.party.query.profileDefault.FindPartyProfileDefaultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyProfileDefaults")
public class PartyProfileDefaultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyProfileDefaultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyProfileDefault
	 * @return a List with the PartyProfileDefaults
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyProfileDefaultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyProfileDefaultsBy query = new FindPartyProfileDefaultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyProfileDefault> partyProfileDefaults =((PartyProfileDefaultFound) Scheduler.execute(query).data()).getPartyProfileDefaults();

		if (partyProfileDefaults.size() == 1) {
			return ResponseEntity.ok().body(partyProfileDefaults.get(0));
		}

		return ResponseEntity.ok().body(partyProfileDefaults);

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
	public ResponseEntity<Object> createPartyProfileDefault(HttpServletRequest request) throws Exception {

		PartyProfileDefault partyProfileDefaultToBeAdded = new PartyProfileDefault();
		try {
			partyProfileDefaultToBeAdded = PartyProfileDefaultMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyProfileDefault(partyProfileDefaultToBeAdded);

	}

	/**
	 * creates a new PartyProfileDefault entry in the ofbiz database
	 * 
	 * @param partyProfileDefaultToBeAdded
	 *            the PartyProfileDefault thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyProfileDefault(@RequestBody PartyProfileDefault partyProfileDefaultToBeAdded) throws Exception {

		AddPartyProfileDefault command = new AddPartyProfileDefault(partyProfileDefaultToBeAdded);
		PartyProfileDefault partyProfileDefault = ((PartyProfileDefaultAdded) Scheduler.execute(command).data()).getAddedPartyProfileDefault();
		
		if (partyProfileDefault != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyProfileDefault);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyProfileDefault could not be created.");
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
	public boolean updatePartyProfileDefault(HttpServletRequest request) throws Exception {

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

		PartyProfileDefault partyProfileDefaultToBeUpdated = new PartyProfileDefault();

		try {
			partyProfileDefaultToBeUpdated = PartyProfileDefaultMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyProfileDefault(partyProfileDefaultToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyProfileDefault with the specific Id
	 * 
	 * @param partyProfileDefaultToBeUpdated
	 *            the PartyProfileDefault thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyProfileDefault(@RequestBody PartyProfileDefault partyProfileDefaultToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyProfileDefaultToBeUpdated.setnull(null);

		UpdatePartyProfileDefault command = new UpdatePartyProfileDefault(partyProfileDefaultToBeUpdated);

		try {
			if(((PartyProfileDefaultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyProfileDefaultId}")
	public ResponseEntity<Object> findById(@PathVariable String partyProfileDefaultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyProfileDefaultId", partyProfileDefaultId);
		try {

			Object foundPartyProfileDefault = findPartyProfileDefaultsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyProfileDefault);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyProfileDefaultId}")
	public ResponseEntity<Object> deletePartyProfileDefaultByIdUpdated(@PathVariable String partyProfileDefaultId) throws Exception {
		DeletePartyProfileDefault command = new DeletePartyProfileDefault(partyProfileDefaultId);

		try {
			if (((PartyProfileDefaultDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyProfileDefault could not be deleted");

	}

}
