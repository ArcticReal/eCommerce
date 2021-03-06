package com.skytala.eCommerce.domain.shipment.relations.shipment.control.boxType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.boxType.AddShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.boxType.DeleteShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.boxType.UpdateShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.boxType.ShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.boxType.ShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.boxType.FindShipmentBoxTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentBoxTypes")
public class ShipmentBoxTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentBoxTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentBoxType
	 * @return a List with the ShipmentBoxTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentBoxType>> findShipmentBoxTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentBoxTypesBy query = new FindShipmentBoxTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentBoxType> shipmentBoxTypes =((ShipmentBoxTypeFound) Scheduler.execute(query).data()).getShipmentBoxTypes();

		return ResponseEntity.ok().body(shipmentBoxTypes);

	}

	/**
	 * creates a new ShipmentBoxType entry in the ofbiz database
	 * 
	 * @param shipmentBoxTypeToBeAdded
	 *            the ShipmentBoxType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentBoxType> createShipmentBoxType(@RequestBody ShipmentBoxType shipmentBoxTypeToBeAdded) throws Exception {

		AddShipmentBoxType command = new AddShipmentBoxType(shipmentBoxTypeToBeAdded);
		ShipmentBoxType shipmentBoxType = ((ShipmentBoxTypeAdded) Scheduler.execute(command).data()).getAddedShipmentBoxType();
		
		if (shipmentBoxType != null) 
			return successful(shipmentBoxType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentBoxType with the specific Id
	 * 
	 * @param shipmentBoxTypeToBeUpdated
	 *            the ShipmentBoxType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentBoxTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentBoxType(@RequestBody ShipmentBoxType shipmentBoxTypeToBeUpdated,
			@PathVariable String shipmentBoxTypeId) throws Exception {

		shipmentBoxTypeToBeUpdated.setShipmentBoxTypeId(shipmentBoxTypeId);

		UpdateShipmentBoxType command = new UpdateShipmentBoxType(shipmentBoxTypeToBeUpdated);

		try {
			if(((ShipmentBoxTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentBoxTypeId}")
	public ResponseEntity<ShipmentBoxType> findById(@PathVariable String shipmentBoxTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentBoxTypeId", shipmentBoxTypeId);
		try {

			List<ShipmentBoxType> foundShipmentBoxType = findShipmentBoxTypesBy(requestParams).getBody();
			if(foundShipmentBoxType.size()==1){				return successful(foundShipmentBoxType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentBoxTypeId}")
	public ResponseEntity<String> deleteShipmentBoxTypeByIdUpdated(@PathVariable String shipmentBoxTypeId) throws Exception {
		DeleteShipmentBoxType command = new DeleteShipmentBoxType(shipmentBoxTypeId);

		try {
			if (((ShipmentBoxTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
