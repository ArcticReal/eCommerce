package com.skytala.eCommerce.domain.shipment.relations.shipment.control.methodType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.methodType.AddShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.methodType.DeleteShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.methodType.UpdateShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType.ShipmentMethodTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType.ShipmentMethodTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType.ShipmentMethodTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType.ShipmentMethodTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.methodType.ShipmentMethodTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.methodType.ShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.methodType.FindShipmentMethodTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentMethodTypes")
public class ShipmentMethodTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentMethodTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentMethodType
	 * @return a List with the ShipmentMethodTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentMethodType>> findShipmentMethodTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentMethodTypesBy query = new FindShipmentMethodTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentMethodType> shipmentMethodTypes =((ShipmentMethodTypeFound) Scheduler.execute(query).data()).getShipmentMethodTypes();

		return ResponseEntity.ok().body(shipmentMethodTypes);

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
	public ResponseEntity<ShipmentMethodType> createShipmentMethodType(HttpServletRequest request) throws Exception {

		ShipmentMethodType shipmentMethodTypeToBeAdded = new ShipmentMethodType();
		try {
			shipmentMethodTypeToBeAdded = ShipmentMethodTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShipmentMethodType(shipmentMethodTypeToBeAdded);

	}

	/**
	 * creates a new ShipmentMethodType entry in the ofbiz database
	 * 
	 * @param shipmentMethodTypeToBeAdded
	 *            the ShipmentMethodType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentMethodType> createShipmentMethodType(@RequestBody ShipmentMethodType shipmentMethodTypeToBeAdded) throws Exception {

		AddShipmentMethodType command = new AddShipmentMethodType(shipmentMethodTypeToBeAdded);
		ShipmentMethodType shipmentMethodType = ((ShipmentMethodTypeAdded) Scheduler.execute(command).data()).getAddedShipmentMethodType();
		
		if (shipmentMethodType != null) 
			return successful(shipmentMethodType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentMethodType with the specific Id
	 * 
	 * @param shipmentMethodTypeToBeUpdated
	 *            the ShipmentMethodType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentMethodTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentMethodType(@RequestBody ShipmentMethodType shipmentMethodTypeToBeUpdated,
			@PathVariable String shipmentMethodTypeId) throws Exception {

		shipmentMethodTypeToBeUpdated.setShipmentMethodTypeId(shipmentMethodTypeId);

		UpdateShipmentMethodType command = new UpdateShipmentMethodType(shipmentMethodTypeToBeUpdated);

		try {
			if(((ShipmentMethodTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentMethodTypeId}")
	public ResponseEntity<ShipmentMethodType> findById(@PathVariable String shipmentMethodTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentMethodTypeId", shipmentMethodTypeId);
		try {

			List<ShipmentMethodType> foundShipmentMethodType = findShipmentMethodTypesBy(requestParams).getBody();
			if(foundShipmentMethodType.size()==1){				return successful(foundShipmentMethodType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentMethodTypeId}")
	public ResponseEntity<String> deleteShipmentMethodTypeByIdUpdated(@PathVariable String shipmentMethodTypeId) throws Exception {
		DeleteShipmentMethodType command = new DeleteShipmentMethodType(shipmentMethodTypeId);

		try {
			if (((ShipmentMethodTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
