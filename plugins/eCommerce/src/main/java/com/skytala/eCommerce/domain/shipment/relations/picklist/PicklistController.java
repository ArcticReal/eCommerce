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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findPicklistsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistsBy query = new FindPicklistsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Picklist> picklists =((PicklistFound) Scheduler.execute(query).data()).getPicklists();

		if (picklists.size() == 1) {
			return ResponseEntity.ok().body(picklists.get(0));
		}

		return ResponseEntity.ok().body(picklists);

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
	public ResponseEntity<Object> createPicklist(HttpServletRequest request) throws Exception {

		Picklist picklistToBeAdded = new Picklist();
		try {
			picklistToBeAdded = PicklistMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPicklist(picklistToBeAdded);

	}

	/**
	 * creates a new Picklist entry in the ofbiz database
	 * 
	 * @param picklistToBeAdded
	 *            the Picklist thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPicklist(@RequestBody Picklist picklistToBeAdded) throws Exception {

		AddPicklist command = new AddPicklist(picklistToBeAdded);
		Picklist picklist = ((PicklistAdded) Scheduler.execute(command).data()).getAddedPicklist();
		
		if (picklist != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(picklist);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Picklist could not be created.");
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
	public boolean updatePicklist(HttpServletRequest request) throws Exception {

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

		Picklist picklistToBeUpdated = new Picklist();

		try {
			picklistToBeUpdated = PicklistMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePicklist(picklistToBeUpdated, picklistToBeUpdated.getPicklistId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePicklist(@RequestBody Picklist picklistToBeUpdated,
			@PathVariable String picklistId) throws Exception {

		picklistToBeUpdated.setPicklistId(picklistId);

		UpdatePicklist command = new UpdatePicklist(picklistToBeUpdated);

		try {
			if(((PicklistUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{picklistId}")
	public ResponseEntity<Object> findById(@PathVariable String picklistId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistId", picklistId);
		try {

			Object foundPicklist = findPicklistsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPicklist);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{picklistId}")
	public ResponseEntity<Object> deletePicklistByIdUpdated(@PathVariable String picklistId) throws Exception {
		DeletePicklist command = new DeletePicklist(picklistId);

		try {
			if (((PicklistDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Picklist could not be deleted");

	}

}
