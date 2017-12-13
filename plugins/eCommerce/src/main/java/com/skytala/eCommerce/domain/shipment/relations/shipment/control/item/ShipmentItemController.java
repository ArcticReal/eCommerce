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
	public ResponseEntity<Object> findShipmentItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentItemsBy query = new FindShipmentItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentItem> shipmentItems =((ShipmentItemFound) Scheduler.execute(query).data()).getShipmentItems();

		if (shipmentItems.size() == 1) {
			return ResponseEntity.ok().body(shipmentItems.get(0));
		}

		return ResponseEntity.ok().body(shipmentItems);

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
	public ResponseEntity<Object> createShipmentItem(HttpServletRequest request) throws Exception {

		ShipmentItem shipmentItemToBeAdded = new ShipmentItem();
		try {
			shipmentItemToBeAdded = ShipmentItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentItem(shipmentItemToBeAdded);

	}

	/**
	 * creates a new ShipmentItem entry in the ofbiz database
	 * 
	 * @param shipmentItemToBeAdded
	 *            the ShipmentItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentItem(@RequestBody ShipmentItem shipmentItemToBeAdded) throws Exception {

		AddShipmentItem command = new AddShipmentItem(shipmentItemToBeAdded);
		ShipmentItem shipmentItem = ((ShipmentItemAdded) Scheduler.execute(command).data()).getAddedShipmentItem();
		
		if (shipmentItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentItem could not be created.");
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
	public boolean updateShipmentItem(HttpServletRequest request) throws Exception {

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

		ShipmentItem shipmentItemToBeUpdated = new ShipmentItem();

		try {
			shipmentItemToBeUpdated = ShipmentItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentItem(shipmentItemToBeUpdated, shipmentItemToBeUpdated.getShipmentItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentItem(@RequestBody ShipmentItem shipmentItemToBeUpdated,
			@PathVariable String shipmentItemSeqId) throws Exception {

		shipmentItemToBeUpdated.setShipmentItemSeqId(shipmentItemSeqId);

		UpdateShipmentItem command = new UpdateShipmentItem(shipmentItemToBeUpdated);

		try {
			if(((ShipmentItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shipmentItemId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentItemId", shipmentItemId);
		try {

			Object foundShipmentItem = findShipmentItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shipmentItemId}")
	public ResponseEntity<Object> deleteShipmentItemByIdUpdated(@PathVariable String shipmentItemId) throws Exception {
		DeleteShipmentItem command = new DeleteShipmentItem(shipmentItemId);

		try {
			if (((ShipmentItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentItem could not be deleted");

	}

}
