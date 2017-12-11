package com.skytala.eCommerce.domain.party.relations.party.control.content;

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
import com.skytala.eCommerce.domain.party.relations.party.command.content.AddPartyContent;
import com.skytala.eCommerce.domain.party.relations.party.command.content.DeletePartyContent;
import com.skytala.eCommerce.domain.party.relations.party.command.content.UpdatePartyContent;
import com.skytala.eCommerce.domain.party.relations.party.event.content.PartyContentAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.content.PartyContentDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.content.PartyContentFound;
import com.skytala.eCommerce.domain.party.relations.party.event.content.PartyContentUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.content.PartyContentMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;
import com.skytala.eCommerce.domain.party.relations.party.query.content.FindPartyContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/party/partyContents")
public class PartyContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyContent
	 * @return a List with the PartyContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPartyContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContentsBy query = new FindPartyContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContent> partyContents =((PartyContentFound) Scheduler.execute(query).data()).getPartyContents();

		if (partyContents.size() == 1) {
			return ResponseEntity.ok().body(partyContents.get(0));
		}

		return ResponseEntity.ok().body(partyContents);

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
	public ResponseEntity<Object> createPartyContent(HttpServletRequest request) throws Exception {

		PartyContent partyContentToBeAdded = new PartyContent();
		try {
			partyContentToBeAdded = PartyContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPartyContent(partyContentToBeAdded);

	}

	/**
	 * creates a new PartyContent entry in the ofbiz database
	 * 
	 * @param partyContentToBeAdded
	 *            the PartyContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPartyContent(@RequestBody PartyContent partyContentToBeAdded) throws Exception {

		AddPartyContent command = new AddPartyContent(partyContentToBeAdded);
		PartyContent partyContent = ((PartyContentAdded) Scheduler.execute(command).data()).getAddedPartyContent();
		
		if (partyContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(partyContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PartyContent could not be created.");
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
	public boolean updatePartyContent(HttpServletRequest request) throws Exception {

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

		PartyContent partyContentToBeUpdated = new PartyContent();

		try {
			partyContentToBeUpdated = PartyContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePartyContent(partyContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PartyContent with the specific Id
	 * 
	 * @param partyContentToBeUpdated
	 *            the PartyContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePartyContent(@RequestBody PartyContent partyContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyContentToBeUpdated.setnull(null);

		UpdatePartyContent command = new UpdatePartyContent(partyContentToBeUpdated);

		try {
			if(((PartyContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{partyContentId}")
	public ResponseEntity<Object> findById(@PathVariable String partyContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContentId", partyContentId);
		try {

			Object foundPartyContent = findPartyContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPartyContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{partyContentId}")
	public ResponseEntity<Object> deletePartyContentByIdUpdated(@PathVariable String partyContentId) throws Exception {
		DeletePartyContent command = new DeletePartyContent(partyContentId);

		try {
			if (((PartyContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PartyContent could not be deleted");

	}

}
