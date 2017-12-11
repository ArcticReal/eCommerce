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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findShipmentGatewayUspssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayUspssBy query = new FindShipmentGatewayUspssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayUsps> shipmentGatewayUspss =((ShipmentGatewayUspsFound) Scheduler.execute(query).data()).getShipmentGatewayUspss();

		if (shipmentGatewayUspss.size() == 1) {
			return ResponseEntity.ok().body(shipmentGatewayUspss.get(0));
		}

		return ResponseEntity.ok().body(shipmentGatewayUspss);

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
	public ResponseEntity<Object> createShipmentGatewayUsps(HttpServletRequest request) throws Exception {

		ShipmentGatewayUsps shipmentGatewayUspsToBeAdded = new ShipmentGatewayUsps();
		try {
			shipmentGatewayUspsToBeAdded = ShipmentGatewayUspsMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentGatewayUsps(shipmentGatewayUspsToBeAdded);

	}

	/**
	 * creates a new ShipmentGatewayUsps entry in the ofbiz database
	 * 
	 * @param shipmentGatewayUspsToBeAdded
	 *            the ShipmentGatewayUsps thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentGatewayUsps(@RequestBody ShipmentGatewayUsps shipmentGatewayUspsToBeAdded) throws Exception {

		AddShipmentGatewayUsps command = new AddShipmentGatewayUsps(shipmentGatewayUspsToBeAdded);
		ShipmentGatewayUsps shipmentGatewayUsps = ((ShipmentGatewayUspsAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayUsps();
		
		if (shipmentGatewayUsps != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentGatewayUsps);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentGatewayUsps could not be created.");
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
	public boolean updateShipmentGatewayUsps(HttpServletRequest request) throws Exception {

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

		ShipmentGatewayUsps shipmentGatewayUspsToBeUpdated = new ShipmentGatewayUsps();

		try {
			shipmentGatewayUspsToBeUpdated = ShipmentGatewayUspsMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentGatewayUsps(shipmentGatewayUspsToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentGatewayUsps(@RequestBody ShipmentGatewayUsps shipmentGatewayUspsToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayUspsToBeUpdated.setnull(null);

		UpdateShipmentGatewayUsps command = new UpdateShipmentGatewayUsps(shipmentGatewayUspsToBeUpdated);

		try {
			if(((ShipmentGatewayUspsUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shipmentGatewayUspsId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentGatewayUspsId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayUspsId", shipmentGatewayUspsId);
		try {

			Object foundShipmentGatewayUsps = findShipmentGatewayUspssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentGatewayUsps);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shipmentGatewayUspsId}")
	public ResponseEntity<Object> deleteShipmentGatewayUspsByIdUpdated(@PathVariable String shipmentGatewayUspsId) throws Exception {
		DeleteShipmentGatewayUsps command = new DeleteShipmentGatewayUsps(shipmentGatewayUspsId);

		try {
			if (((ShipmentGatewayUspsDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentGatewayUsps could not be deleted");

	}

}
