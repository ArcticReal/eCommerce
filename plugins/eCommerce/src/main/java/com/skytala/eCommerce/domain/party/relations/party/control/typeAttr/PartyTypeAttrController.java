package com.skytala.eCommerce.domain.party.relations.party.control.typeAttr;

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
import com.skytala.eCommerce.domain.party.relations.party.command.typeAttr.AddPartyTypeAttr;
import com.skytala.eCommerce.domain.party.relations.party.command.typeAttr.DeletePartyTypeAttr;
import com.skytala.eCommerce.domain.party.relations.party.command.typeAttr.UpdatePartyTypeAttr;
import com.skytala.eCommerce.domain.party.relations.party.event.typeAttr.PartyTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.typeAttr.PartyTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.typeAttr.PartyTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.party.event.typeAttr.PartyTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.typeAttr.PartyTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.typeAttr.PartyTypeAttr;
import com.skytala.eCommerce.domain.party.relations.party.query.typeAttr.FindPartyTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyTypeAttrs")
public class PartyTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyTypeAttr
	 * @return a List with the PartyTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyTypeAttrsBy query = new FindPartyTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyTypeAttr> partyTypeAttrs =((PartyTypeAttrFound) Scheduler.execute(query).data()).getPartyTypeAttrs();

		if (partyTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(partyTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(partyTypeAttrs);

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
	public ResponseEntity<Object> createPartyTypeAttr(HttpServletRequest request) throws Exception {

		PartyTypeAttr partyTypeAttrToBeAdded = new PartyTypeAttr();
		try {
			partyTypeAttrToBeAdded = PartyTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyTypeAttr(partyTypeAttrToBeAdded);

	}

	/**
	 * creates a new PartyTypeAttr entry in the ofbiz database
	 * 
	 * @param partyTypeAttrToBeAdded
	 *            the PartyTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyTypeAttr(@RequestBody PartyTypeAttr partyTypeAttrToBeAdded) throws Exception {

		AddPartyTypeAttr command = new AddPartyTypeAttr(partyTypeAttrToBeAdded);
		PartyTypeAttr partyTypeAttr = ((PartyTypeAttrAdded) Scheduler.execute(command).data()).getAddedPartyTypeAttr();
		
		if (partyTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyTypeAttr could not be created.");
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
	public boolean updatePartyTypeAttr(HttpServletRequest request) throws Exception {

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

		PartyTypeAttr partyTypeAttrToBeUpdated = new PartyTypeAttr();

		try {
			partyTypeAttrToBeUpdated = PartyTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyTypeAttr(partyTypeAttrToBeUpdated, partyTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyTypeAttr with the specific Id
	 * 
	 * @param partyTypeAttrToBeUpdated
	 *            the PartyTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyTypeAttr(@RequestBody PartyTypeAttr partyTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		partyTypeAttrToBeUpdated.setAttrName(attrName);

		UpdatePartyTypeAttr command = new UpdatePartyTypeAttr(partyTypeAttrToBeUpdated);

		try {
			if(((PartyTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String partyTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyTypeAttrId", partyTypeAttrId);
		try {

			Object foundPartyTypeAttr = findPartyTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyTypeAttrId}")
	public ResponseEntity<Object> deletePartyTypeAttrByIdUpdated(@PathVariable String partyTypeAttrId) throws Exception {
		DeletePartyTypeAttr command = new DeletePartyTypeAttr(partyTypeAttrId);

		try {
			if (((PartyTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyTypeAttr could not be deleted");

	}

}
