package com.skytala.eCommerce.domain.party.relations.termType;

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
import com.skytala.eCommerce.domain.party.relations.termType.command.AddTermType;
import com.skytala.eCommerce.domain.party.relations.termType.command.DeleteTermType;
import com.skytala.eCommerce.domain.party.relations.termType.command.UpdateTermType;
import com.skytala.eCommerce.domain.party.relations.termType.event.TermTypeAdded;
import com.skytala.eCommerce.domain.party.relations.termType.event.TermTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.termType.event.TermTypeFound;
import com.skytala.eCommerce.domain.party.relations.termType.event.TermTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.termType.mapper.TermTypeMapper;
import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;
import com.skytala.eCommerce.domain.party.relations.termType.query.FindTermTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/termTypes")
public class TermTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TermTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TermType
	 * @return a List with the TermTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TermType>> findTermTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTermTypesBy query = new FindTermTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TermType> termTypes =((TermTypeFound) Scheduler.execute(query).data()).getTermTypes();

		return ResponseEntity.ok().body(termTypes);

	}

	/**
	 * creates a new TermType entry in the ofbiz database
	 * 
	 * @param termTypeToBeAdded
	 *            the TermType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TermType> createTermType(@RequestBody TermType termTypeToBeAdded) throws Exception {

		AddTermType command = new AddTermType(termTypeToBeAdded);
		TermType termType = ((TermTypeAdded) Scheduler.execute(command).data()).getAddedTermType();
		
		if (termType != null) 
			return successful(termType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TermType with the specific Id
	 * 
	 * @param termTypeToBeUpdated
	 *            the TermType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{termTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTermType(@RequestBody TermType termTypeToBeUpdated,
			@PathVariable String termTypeId) throws Exception {

		termTypeToBeUpdated.setTermTypeId(termTypeId);

		UpdateTermType command = new UpdateTermType(termTypeToBeUpdated);

		try {
			if(((TermTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{termTypeId}")
	public ResponseEntity<TermType> findById(@PathVariable String termTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("termTypeId", termTypeId);
		try {

			List<TermType> foundTermType = findTermTypesBy(requestParams).getBody();
			if(foundTermType.size()==1){				return successful(foundTermType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{termTypeId}")
	public ResponseEntity<String> deleteTermTypeByIdUpdated(@PathVariable String termTypeId) throws Exception {
		DeleteTermType command = new DeleteTermType(termTypeId);

		try {
			if (((TermTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
