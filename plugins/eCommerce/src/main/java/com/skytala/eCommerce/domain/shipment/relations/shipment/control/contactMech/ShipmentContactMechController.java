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
	public ResponseEntity<Object> findShipmentContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentContactMechsBy query = new FindShipmentContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentContactMech> shipmentContactMechs =((ShipmentContactMechFound) Scheduler.execute(query).data()).getShipmentContactMechs();

		if (shipmentContactMechs.size() == 1) {
			return ResponseEntity.ok().body(shipmentContactMechs.get(0));
		}

		return ResponseEntity.ok().body(shipmentContactMechs);

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
	public ResponseEntity<Object> createShipmentContactMech(HttpServletRequest request) throws Exception {

		ShipmentContactMech shipmentContactMechToBeAdded = new ShipmentContactMech();
		try {
			shipmentContactMechToBeAdded = ShipmentContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentContactMech(shipmentContactMechToBeAdded);

	}

	/**
	 * creates a new ShipmentContactMech entry in the ofbiz database
	 * 
	 * @param shipmentContactMechToBeAdded
	 *            the ShipmentContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentContactMech(@RequestBody ShipmentContactMech shipmentContactMechToBeAdded) throws Exception {

		AddShipmentContactMech command = new AddShipmentContactMech(shipmentContactMechToBeAdded);
		ShipmentContactMech shipmentContactMech = ((ShipmentContactMechAdded) Scheduler.execute(command).data()).getAddedShipmentContactMech();
		
		if (shipmentContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentContactMech could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateShipmentContactMech(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ShipmentContactMech shipmentContactMechToBeUpdated = new ShipmentContactMech();

		try {
			shipmentContactMechToBeUpdated = ShipmentContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentContactMech(shipmentContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentContactMech(@RequestBody ShipmentContactMech shipmentContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentContactMechToBeUpdated.setnull(null);

		UpdateShipmentContactMech command = new UpdateShipmentContactMech(shipmentContactMechToBeUpdated);

		try {
			if(((ShipmentContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shipmentContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentContactMechId", shipmentContactMechId);
		try {

			Object foundShipmentContactMech = findShipmentContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shipmentContactMechId}")
	public ResponseEntity<Object> deleteShipmentContactMechByIdUpdated(@PathVariable String shipmentContactMechId) throws Exception {
		DeleteShipmentContactMech command = new DeleteShipmentContactMech(shipmentContactMechId);

		try {
			if (((ShipmentContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentContactMech could not be deleted");

	}

}
