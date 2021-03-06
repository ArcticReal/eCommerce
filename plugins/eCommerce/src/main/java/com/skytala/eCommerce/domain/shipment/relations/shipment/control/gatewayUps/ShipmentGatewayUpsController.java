package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayUps;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUps.AddShipmentGatewayUps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUps.DeleteShipmentGatewayUps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUps.UpdateShipmentGatewayUps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps.ShipmentGatewayUpsAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps.ShipmentGatewayUpsDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps.ShipmentGatewayUpsFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps.ShipmentGatewayUpsUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUps.ShipmentGatewayUpsMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps.ShipmentGatewayUps;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayUps.FindShipmentGatewayUpssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayUpss")
public class ShipmentGatewayUpsController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayUpsController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayUps
	 * @return a List with the ShipmentGatewayUpss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentGatewayUps>> findShipmentGatewayUpssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayUpssBy query = new FindShipmentGatewayUpssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayUps> shipmentGatewayUpss =((ShipmentGatewayUpsFound) Scheduler.execute(query).data()).getShipmentGatewayUpss();

		return ResponseEntity.ok().body(shipmentGatewayUpss);

	}

	/**
	 * creates a new ShipmentGatewayUps entry in the ofbiz database
	 * 
	 * @param shipmentGatewayUpsToBeAdded
	 *            the ShipmentGatewayUps thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentGatewayUps> createShipmentGatewayUps(@RequestBody ShipmentGatewayUps shipmentGatewayUpsToBeAdded) throws Exception {

		AddShipmentGatewayUps command = new AddShipmentGatewayUps(shipmentGatewayUpsToBeAdded);
		ShipmentGatewayUps shipmentGatewayUps = ((ShipmentGatewayUpsAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayUps();
		
		if (shipmentGatewayUps != null) 
			return successful(shipmentGatewayUps);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentGatewayUps with the specific Id
	 * 
	 * @param shipmentGatewayUpsToBeUpdated
	 *            the ShipmentGatewayUps thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentGatewayUps(@RequestBody ShipmentGatewayUps shipmentGatewayUpsToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayUpsToBeUpdated.setnull(null);

		UpdateShipmentGatewayUps command = new UpdateShipmentGatewayUps(shipmentGatewayUpsToBeUpdated);

		try {
			if(((ShipmentGatewayUpsUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentGatewayUpsId}")
	public ResponseEntity<ShipmentGatewayUps> findById(@PathVariable String shipmentGatewayUpsId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayUpsId", shipmentGatewayUpsId);
		try {

			List<ShipmentGatewayUps> foundShipmentGatewayUps = findShipmentGatewayUpssBy(requestParams).getBody();
			if(foundShipmentGatewayUps.size()==1){				return successful(foundShipmentGatewayUps.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentGatewayUpsId}")
	public ResponseEntity<String> deleteShipmentGatewayUpsByIdUpdated(@PathVariable String shipmentGatewayUpsId) throws Exception {
		DeleteShipmentGatewayUps command = new DeleteShipmentGatewayUps(shipmentGatewayUpsId);

		try {
			if (((ShipmentGatewayUpsDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
