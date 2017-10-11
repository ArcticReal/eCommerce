package com.skytala.eCommerce.domain.party.relations.partyAttribute;

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
import com.skytala.eCommerce.domain.party.relations.partyAttribute.command.AddPartyAttribute;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.command.DeletePartyAttribute;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.command.UpdatePartyAttribute;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.event.PartyAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.event.PartyAttributeDeleted;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.event.PartyAttributeFound;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.event.PartyAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.mapper.PartyAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.model.PartyAttribute;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.query.FindPartyAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/partyAttributes")
public class PartyAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyAttribute
	 * @return a List with the PartyAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPartyAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyAttributesBy query = new FindPartyAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyAttribute> partyAttributes =((PartyAttributeFound) Scheduler.execute(query).data()).getPartyAttributes();

		if (partyAttributes.size() == 1) {
			return ResponseEntity.ok().body(partyAttributes.get(0));
		}

		return ResponseEntity.ok().body(partyAttributes);

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
	public ResponseEntity<Object> createPartyAttribute(HttpServletRequest request) throws Exception {

		PartyAttribute partyAttributeToBeAdded = new PartyAttribute();
		try {
			partyAttributeToBeAdded = PartyAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyAttribute(partyAttributeToBeAdded);

	}

	/**
	 * creates a new PartyAttribute entry in the ofbiz database
	 * 
	 * @param partyAttributeToBeAdded
	 *            the PartyAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyAttribute(@RequestBody PartyAttribute partyAttributeToBeAdded) throws Exception {

		AddPartyAttribute command = new AddPartyAttribute(partyAttributeToBeAdded);
		PartyAttribute partyAttribute = ((PartyAttributeAdded) Scheduler.execute(command).data()).getAddedPartyAttribute();
		
		if (partyAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyAttribute could not be created.");
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
	public boolean updatePartyAttribute(HttpServletRequest request) throws Exception {

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

		PartyAttribute partyAttributeToBeUpdated = new PartyAttribute();

		try {
			partyAttributeToBeUpdated = PartyAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyAttribute(partyAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyAttribute with the specific Id
	 * 
	 * @param partyAttributeToBeUpdated
	 *            the PartyAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyAttribute(@RequestBody PartyAttribute partyAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyAttributeToBeUpdated.setnull(null);

		UpdatePartyAttribute command = new UpdatePartyAttribute(partyAttributeToBeUpdated);

		try {
			if(((PartyAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{partyAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String partyAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyAttributeId", partyAttributeId);
		try {

			Object foundPartyAttribute = findPartyAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{partyAttributeId}")
	public ResponseEntity<Object> deletePartyAttributeByIdUpdated(@PathVariable String partyAttributeId) throws Exception {
		DeletePartyAttribute command = new DeletePartyAttribute(partyAttributeId);

		try {
			if (((PartyAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/partyAttribute/\" plus one of the following: "
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