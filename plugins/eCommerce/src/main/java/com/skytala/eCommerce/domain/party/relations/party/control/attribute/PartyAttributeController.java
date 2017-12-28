package com.skytala.eCommerce.domain.party.relations.party.control.attribute;

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
import com.skytala.eCommerce.domain.party.relations.party.command.attribute.AddPartyAttribute;
import com.skytala.eCommerce.domain.party.relations.party.command.attribute.DeletePartyAttribute;
import com.skytala.eCommerce.domain.party.relations.party.command.attribute.UpdatePartyAttribute;
import com.skytala.eCommerce.domain.party.relations.party.event.attribute.PartyAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.attribute.PartyAttributeDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.attribute.PartyAttributeFound;
import com.skytala.eCommerce.domain.party.relations.party.event.attribute.PartyAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.attribute.PartyAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.attribute.PartyAttribute;
import com.skytala.eCommerce.domain.party.relations.party.query.attribute.FindPartyAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/partyAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<PartyAttribute>> findPartyAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyAttributesBy query = new FindPartyAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyAttribute> partyAttributes =((PartyAttributeFound) Scheduler.execute(query).data()).getPartyAttributes();

		return ResponseEntity.ok().body(partyAttributes);

	}

	/**
	 * creates a new PartyAttribute entry in the ofbiz database
	 * 
	 * @param partyAttributeToBeAdded
	 *            the PartyAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyAttribute> createPartyAttribute(@RequestBody PartyAttribute partyAttributeToBeAdded) throws Exception {

		AddPartyAttribute command = new AddPartyAttribute(partyAttributeToBeAdded);
		PartyAttribute partyAttribute = ((PartyAttributeAdded) Scheduler.execute(command).data()).getAddedPartyAttribute();
		
		if (partyAttribute != null) 
			return successful(partyAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyAttribute with the specific Id
	 * 
	 * @param partyAttributeToBeUpdated
	 *            the PartyAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyAttribute(@RequestBody PartyAttribute partyAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		partyAttributeToBeUpdated.setAttrName(attrName);

		UpdatePartyAttribute command = new UpdatePartyAttribute(partyAttributeToBeUpdated);

		try {
			if(((PartyAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyAttributeId}")
	public ResponseEntity<PartyAttribute> findById(@PathVariable String partyAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyAttributeId", partyAttributeId);
		try {

			List<PartyAttribute> foundPartyAttribute = findPartyAttributesBy(requestParams).getBody();
			if(foundPartyAttribute.size()==1){				return successful(foundPartyAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyAttributeId}")
	public ResponseEntity<String> deletePartyAttributeByIdUpdated(@PathVariable String partyAttributeId) throws Exception {
		DeletePartyAttribute command = new DeletePartyAttribute(partyAttributeId);

		try {
			if (((PartyAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
