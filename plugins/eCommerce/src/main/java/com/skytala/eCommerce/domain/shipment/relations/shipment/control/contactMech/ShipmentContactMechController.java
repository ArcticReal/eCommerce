package com.skytala.eCommerce.domain.shipment.relations.shipment.control.contactMech;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMech.AddShipmentContactMech;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMech.DeleteShipmentContactMech;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMech.UpdateShipmentContactMech;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.contactMech.ShipmentContactMechMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.contactMech.FindShipmentContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentContactMechs")
public class ShipmentContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentContactMech
	 * @return a List with the ShipmentContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentContactMech>> findShipmentContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentContactMechsBy query = new FindShipmentContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentContactMech> shipmentContactMechs =((ShipmentContactMechFound) Scheduler.execute(query).data()).getShipmentContactMechs();

		return ResponseEntity.ok().body(shipmentContactMechs);

	}

	/**
	 * creates a new ShipmentContactMech entry in the ofbiz database
	 * 
	 * @param shipmentContactMechToBeAdded
	 *            the ShipmentContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentContactMech> createShipmentContactMech(@RequestBody ShipmentContactMech shipmentContactMechToBeAdded) throws Exception {

		AddShipmentContactMech command = new AddShipmentContactMech(shipmentContactMechToBeAdded);
		ShipmentContactMech shipmentContactMech = ((ShipmentContactMechAdded) Scheduler.execute(command).data()).getAddedShipmentContactMech();
		
		if (shipmentContactMech != null) 
			return successful(shipmentContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentContactMech with the specific Id
	 * 
	 * @param shipmentContactMechToBeUpdated
	 *            the ShipmentContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentContactMech(@RequestBody ShipmentContactMech shipmentContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentContactMechToBeUpdated.setnull(null);

		UpdateShipmentContactMech command = new UpdateShipmentContactMech(shipmentContactMechToBeUpdated);

		try {
			if(((ShipmentContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentContactMechId}")
	public ResponseEntity<ShipmentContactMech> findById(@PathVariable String shipmentContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentContactMechId", shipmentContactMechId);
		try {

			List<ShipmentContactMech> foundShipmentContactMech = findShipmentContactMechsBy(requestParams).getBody();
			if(foundShipmentContactMech.size()==1){				return successful(foundShipmentContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentContactMechId}")
	public ResponseEntity<String> deleteShipmentContactMechByIdUpdated(@PathVariable String shipmentContactMechId) throws Exception {
		DeleteShipmentContactMech command = new DeleteShipmentContactMech(shipmentContactMechId);

		try {
			if (((ShipmentContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
