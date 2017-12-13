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
	public ResponseEntity<Object> findShipmentGatewayFedexsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayFedexsBy query = new FindShipmentGatewayFedexsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayFedex> shipmentGatewayFedexs =((ShipmentGatewayFedexFound) Scheduler.execute(query).data()).getShipmentGatewayFedexs();

		if (shipmentGatewayFedexs.size() == 1) {
			return ResponseEntity.ok().body(shipmentGatewayFedexs.get(0));
		}

		return ResponseEntity.ok().body(shipmentGatewayFedexs);

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
	public ResponseEntity<Object> createShipmentGatewayFedex(HttpServletRequest request) throws Exception {

		ShipmentGatewayFedex shipmentGatewayFedexToBeAdded = new ShipmentGatewayFedex();
		try {
			shipmentGatewayFedexToBeAdded = ShipmentGatewayFedexMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentGatewayFedex(shipmentGatewayFedexToBeAdded);

	}

	/**
	 * creates a new ShipmentGatewayFedex entry in the ofbiz database
	 * 
	 * @param shipmentGatewayFedexToBeAdded
	 *            the ShipmentGatewayFedex thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentGatewayFedex(@RequestBody ShipmentGatewayFedex shipmentGatewayFedexToBeAdded) throws Exception {

		AddShipmentGatewayFedex command = new AddShipmentGatewayFedex(shipmentGatewayFedexToBeAdded);
		ShipmentGatewayFedex shipmentGatewayFedex = ((ShipmentGatewayFedexAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayFedex();
		
		if (shipmentGatewayFedex != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentGatewayFedex);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentGatewayFedex could not be created.");
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
	public boolean updateShipmentGatewayFedex(HttpServletRequest request) throws Exception {

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

		ShipmentGatewayFedex shipmentGatewayFedexToBeUpdated = new ShipmentGatewayFedex();

		try {
			shipmentGatewayFedexToBeUpdated = ShipmentGatewayFedexMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentGatewayFedex(shipmentGatewayFedexToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentGatewayFedex(@RequestBody ShipmentGatewayFedex shipmentGatewayFedexToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayFedexToBeUpdated.setnull(null);

		UpdateShipmentGatewayFedex command = new UpdateShipmentGatewayFedex(shipmentGatewayFedexToBeUpdated);

		try {
			if(((ShipmentGatewayFedexUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shipmentGatewayFedexId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentGatewayFedexId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayFedexId", shipmentGatewayFedexId);
		try {

			Object foundShipmentGatewayFedex = findShipmentGatewayFedexsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentGatewayFedex);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shipmentGatewayFedexId}")
	public ResponseEntity<Object> deleteShipmentGatewayFedexByIdUpdated(@PathVariable String shipmentGatewayFedexId) throws Exception {
		DeleteShipmentGatewayFedex command = new DeleteShipmentGatewayFedex(shipmentGatewayFedexId);

		try {
			if (((ShipmentGatewayFedexDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentGatewayFedex could not be deleted");

	}

}
