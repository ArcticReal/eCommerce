package com.skytala.eCommerce.domain.shipment.relations.shipment.control.type;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.type.AddShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.type.DeleteShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.type.UpdateShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.type.ShipmentTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.type.ShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.type.FindShipmentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentTypes")
public class ShipmentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentType
	 * @return a List with the ShipmentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentType>> findShipmentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentTypesBy query = new FindShipmentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentType> shipmentTypes =((ShipmentTypeFound) Scheduler.execute(query).data()).getShipmentTypes();

		return ResponseEntity.ok().body(shipmentTypes);

	}

	/**
	 * creates a new ShipmentType entry in the ofbiz database
	 * 
	 * @param shipmentTypeToBeAdded
	 *            the ShipmentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentType> createShipmentType(@RequestBody ShipmentType shipmentTypeToBeAdded) throws Exception {

		AddShipmentType command = new AddShipmentType(shipmentTypeToBeAdded);
		ShipmentType shipmentType = ((ShipmentTypeAdded) Scheduler.execute(command).data()).getAddedShipmentType();
		
		if (shipmentType != null) 
			return successful(shipmentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentType with the specific Id
	 * 
	 * @param shipmentTypeToBeUpdated
	 *            the ShipmentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentType(@RequestBody ShipmentType shipmentTypeToBeUpdated,
			@PathVariable String shipmentTypeId) throws Exception {

		shipmentTypeToBeUpdated.setShipmentTypeId(shipmentTypeId);

		UpdateShipmentType command = new UpdateShipmentType(shipmentTypeToBeUpdated);

		try {
			if(((ShipmentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentTypeId}")
	public ResponseEntity<ShipmentType> findById(@PathVariable String shipmentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentTypeId", shipmentTypeId);
		try {

			List<ShipmentType> foundShipmentType = findShipmentTypesBy(requestParams).getBody();
			if(foundShipmentType.size()==1){				return successful(foundShipmentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentTypeId}")
	public ResponseEntity<String> deleteShipmentTypeByIdUpdated(@PathVariable String shipmentTypeId) throws Exception {
		DeleteShipmentType command = new DeleteShipmentType(shipmentTypeId);

		try {
			if (((ShipmentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
