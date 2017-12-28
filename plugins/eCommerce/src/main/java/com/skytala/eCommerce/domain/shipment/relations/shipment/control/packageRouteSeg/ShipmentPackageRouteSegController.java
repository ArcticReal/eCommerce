package com.skytala.eCommerce.domain.shipment.relations.shipment.control.packageRouteSeg;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageRouteSeg.AddShipmentPackageRouteSeg;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageRouteSeg.DeleteShipmentPackageRouteSeg;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageRouteSeg.UpdateShipmentPackageRouteSeg;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.packageRouteSeg.ShipmentPackageRouteSegMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.packageRouteSeg.FindShipmentPackageRouteSegsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentPackageRouteSegs")
public class ShipmentPackageRouteSegController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentPackageRouteSegController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentPackageRouteSeg
	 * @return a List with the ShipmentPackageRouteSegs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentPackageRouteSeg>> findShipmentPackageRouteSegsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentPackageRouteSegsBy query = new FindShipmentPackageRouteSegsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentPackageRouteSeg> shipmentPackageRouteSegs =((ShipmentPackageRouteSegFound) Scheduler.execute(query).data()).getShipmentPackageRouteSegs();

		return ResponseEntity.ok().body(shipmentPackageRouteSegs);

	}

	/**
	 * creates a new ShipmentPackageRouteSeg entry in the ofbiz database
	 * 
	 * @param shipmentPackageRouteSegToBeAdded
	 *            the ShipmentPackageRouteSeg thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentPackageRouteSeg> createShipmentPackageRouteSeg(@RequestBody ShipmentPackageRouteSeg shipmentPackageRouteSegToBeAdded) throws Exception {

		AddShipmentPackageRouteSeg command = new AddShipmentPackageRouteSeg(shipmentPackageRouteSegToBeAdded);
		ShipmentPackageRouteSeg shipmentPackageRouteSeg = ((ShipmentPackageRouteSegAdded) Scheduler.execute(command).data()).getAddedShipmentPackageRouteSeg();
		
		if (shipmentPackageRouteSeg != null) 
			return successful(shipmentPackageRouteSeg);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentPackageRouteSeg with the specific Id
	 * 
	 * @param shipmentPackageRouteSegToBeUpdated
	 *            the ShipmentPackageRouteSeg thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentPackageRouteSeg(@RequestBody ShipmentPackageRouteSeg shipmentPackageRouteSegToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentPackageRouteSegToBeUpdated.setnull(null);

		UpdateShipmentPackageRouteSeg command = new UpdateShipmentPackageRouteSeg(shipmentPackageRouteSegToBeUpdated);

		try {
			if(((ShipmentPackageRouteSegUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentPackageRouteSegId}")
	public ResponseEntity<ShipmentPackageRouteSeg> findById(@PathVariable String shipmentPackageRouteSegId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentPackageRouteSegId", shipmentPackageRouteSegId);
		try {

			List<ShipmentPackageRouteSeg> foundShipmentPackageRouteSeg = findShipmentPackageRouteSegsBy(requestParams).getBody();
			if(foundShipmentPackageRouteSeg.size()==1){				return successful(foundShipmentPackageRouteSeg.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentPackageRouteSegId}")
	public ResponseEntity<String> deleteShipmentPackageRouteSegByIdUpdated(@PathVariable String shipmentPackageRouteSegId) throws Exception {
		DeleteShipmentPackageRouteSeg command = new DeleteShipmentPackageRouteSeg(shipmentPackageRouteSegId);

		try {
			if (((ShipmentPackageRouteSegDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
