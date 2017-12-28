package com.skytala.eCommerce.domain.party.relations.addendum;

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
import com.skytala.eCommerce.domain.party.relations.addendum.command.AddAddendum;
import com.skytala.eCommerce.domain.party.relations.addendum.command.DeleteAddendum;
import com.skytala.eCommerce.domain.party.relations.addendum.command.UpdateAddendum;
import com.skytala.eCommerce.domain.party.relations.addendum.event.AddendumAdded;
import com.skytala.eCommerce.domain.party.relations.addendum.event.AddendumDeleted;
import com.skytala.eCommerce.domain.party.relations.addendum.event.AddendumFound;
import com.skytala.eCommerce.domain.party.relations.addendum.event.AddendumUpdated;
import com.skytala.eCommerce.domain.party.relations.addendum.mapper.AddendumMapper;
import com.skytala.eCommerce.domain.party.relations.addendum.model.Addendum;
import com.skytala.eCommerce.domain.party.relations.addendum.query.FindAddendumsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/addendums")
public class AddendumController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AddendumController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Addendum
	 * @return a List with the Addendums
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Addendum>> findAddendumsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAddendumsBy query = new FindAddendumsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Addendum> addendums =((AddendumFound) Scheduler.execute(query).data()).getAddendums();

		return ResponseEntity.ok().body(addendums);

	}

	/**
	 * creates a new Addendum entry in the ofbiz database
	 * 
	 * @param addendumToBeAdded
	 *            the Addendum thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Addendum> createAddendum(@RequestBody Addendum addendumToBeAdded) throws Exception {

		AddAddendum command = new AddAddendum(addendumToBeAdded);
		Addendum addendum = ((AddendumAdded) Scheduler.execute(command).data()).getAddedAddendum();
		
		if (addendum != null) 
			return successful(addendum);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Addendum with the specific Id
	 * 
	 * @param addendumToBeUpdated
	 *            the Addendum thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{addendumId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAddendum(@RequestBody Addendum addendumToBeUpdated,
			@PathVariable String addendumId) throws Exception {

		addendumToBeUpdated.setAddendumId(addendumId);

		UpdateAddendum command = new UpdateAddendum(addendumToBeUpdated);

		try {
			if(((AddendumUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{addendumId}")
	public ResponseEntity<Addendum> findById(@PathVariable String addendumId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("addendumId", addendumId);
		try {

			List<Addendum> foundAddendum = findAddendumsBy(requestParams).getBody();
			if(foundAddendum.size()==1){				return successful(foundAddendum.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{addendumId}")
	public ResponseEntity<String> deleteAddendumByIdUpdated(@PathVariable String addendumId) throws Exception {
		DeleteAddendum command = new DeleteAddendum(addendumId);

		try {
			if (((AddendumDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
