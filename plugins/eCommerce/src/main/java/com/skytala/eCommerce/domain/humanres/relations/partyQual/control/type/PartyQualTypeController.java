package com.skytala.eCommerce.domain.humanres.relations.partyQual.control.type;

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
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type.AddPartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type.DeletePartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type.UpdatePartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.type.PartyQualTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.query.type.FindPartyQualTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/partyQual/partyQualTypes")
public class PartyQualTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyQualTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyQualType
	 * @return a List with the PartyQualTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyQualType>> findPartyQualTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyQualTypesBy query = new FindPartyQualTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyQualType> partyQualTypes =((PartyQualTypeFound) Scheduler.execute(query).data()).getPartyQualTypes();

		return ResponseEntity.ok().body(partyQualTypes);

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
	public ResponseEntity<PartyQualType> createPartyQualType(HttpServletRequest request) throws Exception {

		PartyQualType partyQualTypeToBeAdded = new PartyQualType();
		try {
			partyQualTypeToBeAdded = PartyQualTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPartyQualType(partyQualTypeToBeAdded);

	}

	/**
	 * creates a new PartyQualType entry in the ofbiz database
	 * 
	 * @param partyQualTypeToBeAdded
	 *            the PartyQualType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyQualType> createPartyQualType(@RequestBody PartyQualType partyQualTypeToBeAdded) throws Exception {

		AddPartyQualType command = new AddPartyQualType(partyQualTypeToBeAdded);
		PartyQualType partyQualType = ((PartyQualTypeAdded) Scheduler.execute(command).data()).getAddedPartyQualType();
		
		if (partyQualType != null) 
			return successful(partyQualType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyQualType with the specific Id
	 * 
	 * @param partyQualTypeToBeUpdated
	 *            the PartyQualType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyQualTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyQualType(@RequestBody PartyQualType partyQualTypeToBeUpdated,
			@PathVariable String partyQualTypeId) throws Exception {

		partyQualTypeToBeUpdated.setPartyQualTypeId(partyQualTypeId);

		UpdatePartyQualType command = new UpdatePartyQualType(partyQualTypeToBeUpdated);

		try {
			if(((PartyQualTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyQualTypeId}")
	public ResponseEntity<PartyQualType> findById(@PathVariable String partyQualTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyQualTypeId", partyQualTypeId);
		try {

			List<PartyQualType> foundPartyQualType = findPartyQualTypesBy(requestParams).getBody();
			if(foundPartyQualType.size()==1){				return successful(foundPartyQualType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyQualTypeId}")
	public ResponseEntity<String> deletePartyQualTypeByIdUpdated(@PathVariable String partyQualTypeId) throws Exception {
		DeletePartyQualType command = new DeletePartyQualType(partyQualTypeId);

		try {
			if (((PartyQualTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
