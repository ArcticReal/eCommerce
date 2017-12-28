package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayFedex;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayFedex.AddShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayFedex.DeleteShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayFedex.UpdateShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex.ShipmentGatewayFedexAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex.ShipmentGatewayFedexDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex.ShipmentGatewayFedexFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex.ShipmentGatewayFedexUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayFedex.ShipmentGatewayFedexMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex.ShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayFedex.FindShipmentGatewayFedexsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayFedexs")
public class ShipmentGatewayFedexController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayFedexController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayFedex
	 * @return a List with the ShipmentGatewayFedexs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentGatewayFedex>> findShipmentGatewayFedexsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayFedexsBy query = new FindShipmentGatewayFedexsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayFedex> shipmentGatewayFedexs =((ShipmentGatewayFedexFound) Scheduler.execute(query).data()).getShipmentGatewayFedexs();

		return ResponseEntity.ok().body(shipmentGatewayFedexs);

	}

	/**
	 * creates a new ShipmentGatewayFedex entry in the ofbiz database
	 * 
	 * @param shipmentGatewayFedexToBeAdded
	 *            the ShipmentGatewayFedex thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentGatewayFedex> createShipmentGatewayFedex(@RequestBody ShipmentGatewayFedex shipmentGatewayFedexToBeAdded) throws Exception {

		AddShipmentGatewayFedex command = new AddShipmentGatewayFedex(shipmentGatewayFedexToBeAdded);
		ShipmentGatewayFedex shipmentGatewayFedex = ((ShipmentGatewayFedexAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayFedex();
		
		if (shipmentGatewayFedex != null) 
			return successful(shipmentGatewayFedex);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentGatewayFedex with the specific Id
	 * 
	 * @param shipmentGatewayFedexToBeUpdated
	 *            the ShipmentGatewayFedex thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentGatewayFedex(@RequestBody ShipmentGatewayFedex shipmentGatewayFedexToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayFedexToBeUpdated.setnull(null);

		UpdateShipmentGatewayFedex command = new UpdateShipmentGatewayFedex(shipmentGatewayFedexToBeUpdated);

		try {
			if(((ShipmentGatewayFedexUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentGatewayFedexId}")
	public ResponseEntity<ShipmentGatewayFedex> findById(@PathVariable String shipmentGatewayFedexId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayFedexId", shipmentGatewayFedexId);
		try {

			List<ShipmentGatewayFedex> foundShipmentGatewayFedex = findShipmentGatewayFedexsBy(requestParams).getBody();
			if(foundShipmentGatewayFedex.size()==1){				return successful(foundShipmentGatewayFedex.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentGatewayFedexId}")
	public ResponseEntity<String> deleteShipmentGatewayFedexByIdUpdated(@PathVariable String shipmentGatewayFedexId) throws Exception {
		DeleteShipmentGatewayFedex command = new DeleteShipmentGatewayFedex(shipmentGatewayFedexId);

		try {
			if (((ShipmentGatewayFedexDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
