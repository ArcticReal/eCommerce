package com.skytala.eCommerce.domain.humanres.relations.partyQual;

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
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.AddPartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.DeletePartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.UpdatePartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualFound;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.PartyQualMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.query.FindPartyQualsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/partyQuals")
public class PartyQualController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyQualController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyQual
	 * @return a List with the PartyQuals
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyQual>> findPartyQualsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyQualsBy query = new FindPartyQualsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyQual> partyQuals =((PartyQualFound) Scheduler.execute(query).data()).getPartyQuals();

		return ResponseEntity.ok().body(partyQuals);

	}

	/**
	 * creates a new PartyQual entry in the ofbiz database
	 * 
	 * @param partyQualToBeAdded
	 *            the PartyQual thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyQual> createPartyQual(@RequestBody PartyQual partyQualToBeAdded) throws Exception {

		AddPartyQual command = new AddPartyQual(partyQualToBeAdded);
		PartyQual partyQual = ((PartyQualAdded) Scheduler.execute(command).data()).getAddedPartyQual();
		
		if (partyQual != null) 
			return successful(partyQual);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyQual with the specific Id
	 * 
	 * @param partyQualToBeUpdated
	 *            the PartyQual thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyQual(@RequestBody PartyQual partyQualToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		partyQualToBeUpdated.setnull(null);

		UpdatePartyQual command = new UpdatePartyQual(partyQualToBeUpdated);

		try {
			if(((PartyQualUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyQualId}")
	public ResponseEntity<PartyQual> findById(@PathVariable String partyQualId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyQualId", partyQualId);
		try {

			List<PartyQual> foundPartyQual = findPartyQualsBy(requestParams).getBody();
			if(foundPartyQual.size()==1){				return successful(foundPartyQual.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyQualId}")
	public ResponseEntity<String> deletePartyQualByIdUpdated(@PathVariable String partyQualId) throws Exception {
		DeletePartyQual command = new DeletePartyQual(partyQualId);

		try {
			if (((PartyQualDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
