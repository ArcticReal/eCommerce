package com.skytala.eCommerce.domain.shipment.relations.shipment.control.contactMechType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMechType.AddShipmentContactMechType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMechType.DeleteShipmentContactMechType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMechType.UpdateShipmentContactMechType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType.ShipmentContactMechTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType.ShipmentContactMechTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType.ShipmentContactMechTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType.ShipmentContactMechTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.contactMechType.ShipmentContactMechTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMechType.ShipmentContactMechType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.contactMechType.FindShipmentContactMechTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentContactMechTypes")
public class ShipmentContactMechTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentContactMechTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentContactMechType
	 * @return a List with the ShipmentContactMechTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentContactMechType>> findShipmentContactMechTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentContactMechTypesBy query = new FindShipmentContactMechTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentContactMechType> shipmentContactMechTypes =((ShipmentContactMechTypeFound) Scheduler.execute(query).data()).getShipmentContactMechTypes();

		return ResponseEntity.ok().body(shipmentContactMechTypes);

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
	public ResponseEntity<ShipmentContactMechType> createShipmentContactMechType(HttpServletRequest request) throws Exception {

		ShipmentContactMechType shipmentContactMechTypeToBeAdded = new ShipmentContactMechType();
		try {
			shipmentContactMechTypeToBeAdded = ShipmentContactMechTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShipmentContactMechType(shipmentContactMechTypeToBeAdded);

	}

	/**
	 * creates a new ShipmentContactMechType entry in the ofbiz database
	 * 
	 * @param shipmentContactMechTypeToBeAdded
	 *            the ShipmentContactMechType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentContactMechType> createShipmentContactMechType(@RequestBody ShipmentContactMechType shipmentContactMechTypeToBeAdded) throws Exception {

		AddShipmentContactMechType command = new AddShipmentContactMechType(shipmentContactMechTypeToBeAdded);
		ShipmentContactMechType shipmentContactMechType = ((ShipmentContactMechTypeAdded) Scheduler.execute(command).data()).getAddedShipmentContactMechType();
		
		if (shipmentContactMechType != null) 
			return successful(shipmentContactMechType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentContactMechType with the specific Id
	 * 
	 * @param shipmentContactMechTypeToBeUpdated
	 *            the ShipmentContactMechType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentContactMechTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentContactMechType(@RequestBody ShipmentContactMechType shipmentContactMechTypeToBeUpdated,
			@PathVariable String shipmentContactMechTypeId) throws Exception {

		shipmentContactMechTypeToBeUpdated.setShipmentContactMechTypeId(shipmentContactMechTypeId);

		UpdateShipmentContactMechType command = new UpdateShipmentContactMechType(shipmentContactMechTypeToBeUpdated);

		try {
			if(((ShipmentContactMechTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentContactMechTypeId}")
	public ResponseEntity<ShipmentContactMechType> findById(@PathVariable String shipmentContactMechTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentContactMechTypeId", shipmentContactMechTypeId);
		try {

			List<ShipmentContactMechType> foundShipmentContactMechType = findShipmentContactMechTypesBy(requestParams).getBody();
			if(foundShipmentContactMechType.size()==1){				return successful(foundShipmentContactMechType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentContactMechTypeId}")
	public ResponseEntity<String> deleteShipmentContactMechTypeByIdUpdated(@PathVariable String shipmentContactMechTypeId) throws Exception {
		DeleteShipmentContactMechType command = new DeleteShipmentContactMechType(shipmentContactMechTypeId);

		try {
			if (((ShipmentContactMechTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
