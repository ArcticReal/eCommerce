package com.skytala.eCommerce.domain.shipment.relations.picklist.control.item;

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
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.item.AddPicklistItem;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.item.DeletePicklistItem;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.item.UpdatePicklistItem;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.item.PicklistItemAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.item.PicklistItemDeleted;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.item.PicklistItemFound;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.item.PicklistItemUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.item.PicklistItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.item.PicklistItem;
import com.skytala.eCommerce.domain.shipment.relations.picklist.query.item.FindPicklistItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipment/picklist/picklistItems")
public class PicklistItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PicklistItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PicklistItem
	 * @return a List with the PicklistItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findPicklistItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistItemsBy query = new FindPicklistItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistItem> picklistItems =((PicklistItemFound) Scheduler.execute(query).data()).getPicklistItems();

		if (picklistItems.size() == 1) {
			return ResponseEntity.ok().body(picklistItems.get(0));
		}

		return ResponseEntity.ok().body(picklistItems);

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
	public ResponseEntity<Object> createPicklistItem(HttpServletRequest request) throws Exception {

		PicklistItem picklistItemToBeAdded = new PicklistItem();
		try {
			picklistItemToBeAdded = PicklistItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPicklistItem(picklistItemToBeAdded);

	}

	/**
	 * creates a new PicklistItem entry in the ofbiz database
	 * 
	 * @param picklistItemToBeAdded
	 *            the PicklistItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPicklistItem(@RequestBody PicklistItem picklistItemToBeAdded) throws Exception {

		AddPicklistItem command = new AddPicklistItem(picklistItemToBeAdded);
		PicklistItem picklistItem = ((PicklistItemAdded) Scheduler.execute(command).data()).getAddedPicklistItem();
		
		if (picklistItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(picklistItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PicklistItem could not be created.");
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
	public boolean updatePicklistItem(HttpServletRequest request) throws Exception {

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

		PicklistItem picklistItemToBeUpdated = new PicklistItem();

		try {
			picklistItemToBeUpdated = PicklistItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePicklistItem(picklistItemToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the PicklistItem with the specific Id
	 * 
	 * @param picklistItemToBeUpdated
	 *            the PicklistItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updatePicklistItem(@RequestBody PicklistItem picklistItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		picklistItemToBeUpdated.setnull(null);

		UpdatePicklistItem command = new UpdatePicklistItem(picklistItemToBeUpdated);

		try {
			if(((PicklistItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{picklistItemId}")
	public ResponseEntity<Object> findById(@PathVariable String picklistItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistItemId", picklistItemId);
		try {

			Object foundPicklistItem = findPicklistItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPicklistItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{picklistItemId}")
	public ResponseEntity<Object> deletePicklistItemByIdUpdated(@PathVariable String picklistItemId) throws Exception {
		DeletePicklistItem command = new DeletePicklistItem(picklistItemId);

		try {
			if (((PicklistItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PicklistItem could not be deleted");

	}

}
