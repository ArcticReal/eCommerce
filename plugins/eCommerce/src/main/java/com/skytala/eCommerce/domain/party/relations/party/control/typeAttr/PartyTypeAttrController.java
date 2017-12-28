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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PartyTypeAttr>> findPartyTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyTypeAttrsBy query = new FindPartyTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyTypeAttr> partyTypeAttrs =((PartyTypeAttrFound) Scheduler.execute(query).data()).getPartyTypeAttrs();

		return ResponseEntity.ok().body(partyTypeAttrs);

	}

	/**
	 * creates a new PartyTypeAttr entry in the ofbiz database
	 * 
	 * @param partyTypeAttrToBeAdded
	 *            the PartyTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyTypeAttr> createPartyTypeAttr(@RequestBody PartyTypeAttr partyTypeAttrToBeAdded) throws Exception {

		AddPartyTypeAttr command = new AddPartyTypeAttr(partyTypeAttrToBeAdded);
		PartyTypeAttr partyTypeAttr = ((PartyTypeAttrAdded) Scheduler.execute(command).data()).getAddedPartyTypeAttr();
		
		if (partyTypeAttr != null) 
			return successful(partyTypeAttr);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePartyTypeAttr(@RequestBody PartyTypeAttr partyTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		partyTypeAttrToBeUpdated.setAttrName(attrName);

		UpdatePartyTypeAttr command = new UpdatePartyTypeAttr(partyTypeAttrToBeUpdated);

		try {
			if(((PartyTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyTypeAttrId}")
	public ResponseEntity<PartyTypeAttr> findById(@PathVariable String partyTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyTypeAttrId", partyTypeAttrId);
		try {

			List<PartyTypeAttr> foundPartyTypeAttr = findPartyTypeAttrsBy(requestParams).getBody();
			if(foundPartyTypeAttr.size()==1){				return successful(foundPartyTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyTypeAttrId}")
	public ResponseEntity<String> deletePartyTypeAttrByIdUpdated(@PathVariable String partyTypeAttrId) throws Exception {
		DeletePartyTypeAttr command = new DeletePartyTypeAttr(partyTypeAttrId);

		try {
			if (((PartyTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
