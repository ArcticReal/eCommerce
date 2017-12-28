package com.skytala.eCommerce.domain.shipment.relations.shipment.control.item;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.item.AddShipmentItem;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.item.DeleteShipmentItem;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.item.UpdateShipmentItem;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.item.ShipmentItemAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.item.ShipmentItemDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.item.ShipmentItemFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.item.ShipmentItemUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.item.ShipmentItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.item.ShipmentItem;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.item.FindShipmentItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentItems")
public class ShipmentItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentItem
	 * @return a List with the ShipmentItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentItem>> findShipmentItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentItemsBy query = new FindShipmentItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentItem> shipmentItems =((ShipmentItemFound) Scheduler.execute(query).data()).getShipmentItems();

		return ResponseEntity.ok().body(shipmentItems);

	}

	/**
	 * creates a new ShipmentItem entry in the ofbiz database
	 * 
	 * @param shipmentItemToBeAdded
	 *            the ShipmentItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentItem> createShipmentItem(@RequestBody ShipmentItem shipmentItemToBeAdded) throws Exception {

		AddShipmentItem command = new AddShipmentItem(shipmentItemToBeAdded);
		ShipmentItem shipmentItem = ((ShipmentItemAdded) Scheduler.execute(command).data()).getAddedShipmentItem();
		
		if (shipmentItem != null) 
			return successful(shipmentItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentItem with the specific Id
	 * 
	 * @param shipmentItemToBeUpdated
	 *            the ShipmentItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentItem(@RequestBody ShipmentItem shipmentItemToBeUpdated,
			@PathVariable String shipmentItemSeqId) throws Exception {

		shipmentItemToBeUpdated.setShipmentItemSeqId(shipmentItemSeqId);

		UpdateShipmentItem command = new UpdateShipmentItem(shipmentItemToBeUpdated);

		try {
			if(((ShipmentItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentItemId}")
	public ResponseEntity<ShipmentItem> findById(@PathVariable String shipmentItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentItemId", shipmentItemId);
		try {

			List<ShipmentItem> foundShipmentItem = findShipmentItemsBy(requestParams).getBody();
			if(foundShipmentItem.size()==1){				return successful(foundShipmentItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentItemId}")
	public ResponseEntity<String> deleteShipmentItemByIdUpdated(@PathVariable String shipmentItemId) throws Exception {
		DeleteShipmentItem command = new DeleteShipmentItem(shipmentItemId);

		try {
			if (((ShipmentItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
