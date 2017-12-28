package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayDhl;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayDhl.AddShipmentGatewayDhl;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayDhl.DeleteShipmentGatewayDhl;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayDhl.UpdateShipmentGatewayDhl;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl.ShipmentGatewayDhlAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl.ShipmentGatewayDhlDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl.ShipmentGatewayDhlFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl.ShipmentGatewayDhlUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayDhl.ShipmentGatewayDhlMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayDhl.ShipmentGatewayDhl;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayDhl.FindShipmentGatewayDhlsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayDhls")
public class ShipmentGatewayDhlController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayDhlController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayDhl
	 * @return a List with the ShipmentGatewayDhls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentGatewayDhl>> findShipmentGatewayDhlsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayDhlsBy query = new FindShipmentGatewayDhlsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayDhl> shipmentGatewayDhls =((ShipmentGatewayDhlFound) Scheduler.execute(query).data()).getShipmentGatewayDhls();

		return ResponseEntity.ok().body(shipmentGatewayDhls);

	}

	/**
	 * creates a new ShipmentGatewayDhl entry in the ofbiz database
	 * 
	 * @param shipmentGatewayDhlToBeAdded
	 *            the ShipmentGatewayDhl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentGatewayDhl> createShipmentGatewayDhl(@RequestBody ShipmentGatewayDhl shipmentGatewayDhlToBeAdded) throws Exception {

		AddShipmentGatewayDhl command = new AddShipmentGatewayDhl(shipmentGatewayDhlToBeAdded);
		ShipmentGatewayDhl shipmentGatewayDhl = ((ShipmentGatewayDhlAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayDhl();
		
		if (shipmentGatewayDhl != null) 
			return successful(shipmentGatewayDhl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentGatewayDhl with the specific Id
	 * 
	 * @param shipmentGatewayDhlToBeUpdated
	 *            the ShipmentGatewayDhl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentGatewayDhl(@RequestBody ShipmentGatewayDhl shipmentGatewayDhlToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayDhlToBeUpdated.setnull(null);

		UpdateShipmentGatewayDhl command = new UpdateShipmentGatewayDhl(shipmentGatewayDhlToBeUpdated);

		try {
			if(((ShipmentGatewayDhlUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentGatewayDhlId}")
	public ResponseEntity<ShipmentGatewayDhl> findById(@PathVariable String shipmentGatewayDhlId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayDhlId", shipmentGatewayDhlId);
		try {

			List<ShipmentGatewayDhl> foundShipmentGatewayDhl = findShipmentGatewayDhlsBy(requestParams).getBody();
			if(foundShipmentGatewayDhl.size()==1){				return successful(foundShipmentGatewayDhl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentGatewayDhlId}")
	public ResponseEntity<String> deleteShipmentGatewayDhlByIdUpdated(@PathVariable String shipmentGatewayDhlId) throws Exception {
		DeleteShipmentGatewayDhl command = new DeleteShipmentGatewayDhl(shipmentGatewayDhlId);

		try {
			if (((ShipmentGatewayDhlDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
