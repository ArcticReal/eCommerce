package com.skytala.eCommerce.domain.shipment.relations.picklist;

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
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.AddPicklist;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.DeletePicklist;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.UpdatePicklist;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.PicklistAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.PicklistDeleted;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.PicklistFound;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.PicklistUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.PicklistMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.Picklist;
import com.skytala.eCommerce.domain.shipment.relations.picklist.query.FindPicklistsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/picklists")
public class PicklistController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PicklistController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Picklist
	 * @return a List with the Picklists
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Picklist>> findPicklistsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistsBy query = new FindPicklistsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Picklist> picklists =((PicklistFound) Scheduler.execute(query).data()).getPicklists();

		return ResponseEntity.ok().body(picklists);

	}

	/**
	 * creates a new Picklist entry in the ofbiz database
	 * 
	 * @param picklistToBeAdded
	 *            the Picklist thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Picklist> createPicklist(@RequestBody Picklist picklistToBeAdded) throws Exception {

		AddPicklist command = new AddPicklist(picklistToBeAdded);
		Picklist picklist = ((PicklistAdded) Scheduler.execute(command).data()).getAddedPicklist();
		
		if (picklist != null) 
			return successful(picklist);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Picklist with the specific Id
	 * 
	 * @param picklistToBeUpdated
	 *            the Picklist thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{picklistId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePicklist(@RequestBody Picklist picklistToBeUpdated,
			@PathVariable String picklistId) throws Exception {

		picklistToBeUpdated.setPicklistId(picklistId);

		UpdatePicklist command = new UpdatePicklist(picklistToBeUpdated);

		try {
			if(((PicklistUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{picklistId}")
	public ResponseEntity<Picklist> findById(@PathVariable String picklistId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistId", picklistId);
		try {

			List<Picklist> foundPicklist = findPicklistsBy(requestParams).getBody();
			if(foundPicklist.size()==1){				return successful(foundPicklist.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{picklistId}")
	public ResponseEntity<String> deletePicklistByIdUpdated(@PathVariable String picklistId) throws Exception {
		DeletePicklist command = new DeletePicklist(picklistId);

		try {
			if (((PicklistDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
