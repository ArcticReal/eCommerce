package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayConfigType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType.AddShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType.DeleteShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType.UpdateShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfigType.ShipmentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType.ShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayConfigType.FindShipmentGatewayConfigTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayConfigTypes")
public class ShipmentGatewayConfigTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayConfigTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayConfigType
	 * @return a List with the ShipmentGatewayConfigTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentGatewayConfigType>> findShipmentGatewayConfigTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayConfigTypesBy query = new FindShipmentGatewayConfigTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayConfigType> shipmentGatewayConfigTypes =((ShipmentGatewayConfigTypeFound) Scheduler.execute(query).data()).getShipmentGatewayConfigTypes();

		return ResponseEntity.ok().body(shipmentGatewayConfigTypes);

	}

	/**
	 * creates a new ShipmentGatewayConfigType entry in the ofbiz database
	 * 
	 * @param shipmentGatewayConfigTypeToBeAdded
	 *            the ShipmentGatewayConfigType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentGatewayConfigType> createShipmentGatewayConfigType(@RequestBody ShipmentGatewayConfigType shipmentGatewayConfigTypeToBeAdded) throws Exception {

		AddShipmentGatewayConfigType command = new AddShipmentGatewayConfigType(shipmentGatewayConfigTypeToBeAdded);
		ShipmentGatewayConfigType shipmentGatewayConfigType = ((ShipmentGatewayConfigTypeAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayConfigType();
		
		if (shipmentGatewayConfigType != null) 
			return successful(shipmentGatewayConfigType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentGatewayConfigType with the specific Id
	 * 
	 * @param shipmentGatewayConfigTypeToBeUpdated
	 *            the ShipmentGatewayConfigType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentGatewayConfTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentGatewayConfigType(@RequestBody ShipmentGatewayConfigType shipmentGatewayConfigTypeToBeUpdated,
			@PathVariable String shipmentGatewayConfTypeId) throws Exception {

		shipmentGatewayConfigTypeToBeUpdated.setShipmentGatewayConfTypeId(shipmentGatewayConfTypeId);

		UpdateShipmentGatewayConfigType command = new UpdateShipmentGatewayConfigType(shipmentGatewayConfigTypeToBeUpdated);

		try {
			if(((ShipmentGatewayConfigTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentGatewayConfigTypeId}")
	public ResponseEntity<ShipmentGatewayConfigType> findById(@PathVariable String shipmentGatewayConfigTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayConfigTypeId", shipmentGatewayConfigTypeId);
		try {

			List<ShipmentGatewayConfigType> foundShipmentGatewayConfigType = findShipmentGatewayConfigTypesBy(requestParams).getBody();
			if(foundShipmentGatewayConfigType.size()==1){				return successful(foundShipmentGatewayConfigType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentGatewayConfigTypeId}")
	public ResponseEntity<String> deleteShipmentGatewayConfigTypeByIdUpdated(@PathVariable String shipmentGatewayConfigTypeId) throws Exception {
		DeleteShipmentGatewayConfigType command = new DeleteShipmentGatewayConfigType(shipmentGatewayConfigTypeId);

		try {
			if (((ShipmentGatewayConfigTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
