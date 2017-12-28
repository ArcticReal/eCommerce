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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<PicklistItem>> findPicklistItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistItemsBy query = new FindPicklistItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistItem> picklistItems =((PicklistItemFound) Scheduler.execute(query).data()).getPicklistItems();

		return ResponseEntity.ok().body(picklistItems);

	}

	/**
	 * creates a new PicklistItem entry in the ofbiz database
	 * 
	 * @param picklistItemToBeAdded
	 *            the PicklistItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PicklistItem> createPicklistItem(@RequestBody PicklistItem picklistItemToBeAdded) throws Exception {

		AddPicklistItem command = new AddPicklistItem(picklistItemToBeAdded);
		PicklistItem picklistItem = ((PicklistItemAdded) Scheduler.execute(command).data()).getAddedPicklistItem();
		
		if (picklistItem != null) 
			return successful(picklistItem);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updatePicklistItem(@RequestBody PicklistItem picklistItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		picklistItemToBeUpdated.setnull(null);

		UpdatePicklistItem command = new UpdatePicklistItem(picklistItemToBeUpdated);

		try {
			if(((PicklistItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{picklistItemId}")
	public ResponseEntity<PicklistItem> findById(@PathVariable String picklistItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistItemId", picklistItemId);
		try {

			List<PicklistItem> foundPicklistItem = findPicklistItemsBy(requestParams).getBody();
			if(foundPicklistItem.size()==1){				return successful(foundPicklistItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{picklistItemId}")
	public ResponseEntity<String> deletePicklistItemByIdUpdated(@PathVariable String picklistItemId) throws Exception {
		DeletePicklistItem command = new DeletePicklistItem(picklistItemId);

		try {
			if (((PicklistItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
