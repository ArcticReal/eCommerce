package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayUsps;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUsps.AddShipmentGatewayUsps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUsps.DeleteShipmentGatewayUsps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUsps.UpdateShipmentGatewayUsps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps.ShipmentGatewayUspsAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps.ShipmentGatewayUspsDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps.ShipmentGatewayUspsFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps.ShipmentGatewayUspsUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUsps.ShipmentGatewayUspsMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUsps.ShipmentGatewayUsps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayUsps.FindShipmentGatewayUspssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayUspss")
public class ShipmentGatewayUspsController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayUspsController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayUsps
	 * @return a List with the ShipmentGatewayUspss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentGatewayUsps>> findShipmentGatewayUspssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayUspssBy query = new FindShipmentGatewayUspssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayUsps> shipmentGatewayUspss =((ShipmentGatewayUspsFound) Scheduler.execute(query).data()).getShipmentGatewayUspss();

		return ResponseEntity.ok().body(shipmentGatewayUspss);

	}

	/**
	 * creates a new ShipmentGatewayUsps entry in the ofbiz database
	 * 
	 * @param shipmentGatewayUspsToBeAdded
	 *            the ShipmentGatewayUsps thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentGatewayUsps> createShipmentGatewayUsps(@RequestBody ShipmentGatewayUsps shipmentGatewayUspsToBeAdded) throws Exception {

		AddShipmentGatewayUsps command = new AddShipmentGatewayUsps(shipmentGatewayUspsToBeAdded);
		ShipmentGatewayUsps shipmentGatewayUsps = ((ShipmentGatewayUspsAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayUsps();
		
		if (shipmentGatewayUsps != null) 
			return successful(shipmentGatewayUsps);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentGatewayUsps with the specific Id
	 * 
	 * @param shipmentGatewayUspsToBeUpdated
	 *            the ShipmentGatewayUsps thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentGatewayUsps(@RequestBody ShipmentGatewayUsps shipmentGatewayUspsToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayUspsToBeUpdated.setnull(null);

		UpdateShipmentGatewayUsps command = new UpdateShipmentGatewayUsps(shipmentGatewayUspsToBeUpdated);

		try {
			if(((ShipmentGatewayUspsUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentGatewayUspsId}")
	public ResponseEntity<ShipmentGatewayUsps> findById(@PathVariable String shipmentGatewayUspsId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayUspsId", shipmentGatewayUspsId);
		try {

			List<ShipmentGatewayUsps> foundShipmentGatewayUsps = findShipmentGatewayUspssBy(requestParams).getBody();
			if(foundShipmentGatewayUsps.size()==1){				return successful(foundShipmentGatewayUsps.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentGatewayUspsId}")
	public ResponseEntity<String> deleteShipmentGatewayUspsByIdUpdated(@PathVariable String shipmentGatewayUspsId) throws Exception {
		DeleteShipmentGatewayUsps command = new DeleteShipmentGatewayUsps(shipmentGatewayUspsId);

		try {
			if (((ShipmentGatewayUspsDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
