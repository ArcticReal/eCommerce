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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PartyContent>> findPartyContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyContentsBy query = new FindPartyContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyContent> partyContents =((PartyContentFound) Scheduler.execute(query).data()).getPartyContents();

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
	public ResponseEntity<PartyContent> createPartyContent(HttpServletRequest request) throws Exception {

		PartyContent partyContentToBeAdded = new PartyContent();
		try {
			partyContentToBeAdded = PartyContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<PartyContent> createPartyContent(@RequestBody PartyContent partyContentToBeAdded) throws Exception {

		AddPartyContent command = new AddPartyContent(partyContentToBeAdded);
		PartyContent partyContent = ((PartyContentAdded) Scheduler.execute(command).data()).getAddedPartyContent();
		
		if (partyContent != null) 
			return successful(partyContent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyContent(@RequestBody PartyContent partyContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyContentToBeUpdated.setnull(null);

		UpdatePartyContent command = new UpdatePartyContent(partyContentToBeUpdated);

		try {
			if(((PartyContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyContentId}")
	public ResponseEntity<PartyContent> findById(@PathVariable String partyContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyContentId", partyContentId);
		try {

			List<PartyContent> foundPartyContent = findPartyContentsBy(requestParams).getBody();
			if(foundPartyContent.size()==1){				return successful(foundPartyContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyContentId}")
	public ResponseEntity<String> deletePartyContentByIdUpdated(@PathVariable String partyContentId) throws Exception {
		DeletePartyContent command = new DeletePartyContent(partyContentId);

		try {
			if (((PartyContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
