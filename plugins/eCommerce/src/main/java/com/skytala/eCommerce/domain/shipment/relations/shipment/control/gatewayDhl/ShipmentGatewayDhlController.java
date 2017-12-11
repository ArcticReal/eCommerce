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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentGatewayDhlsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayDhlsBy query = new FindShipmentGatewayDhlsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayDhl> shipmentGatewayDhls =((ShipmentGatewayDhlFound) Scheduler.execute(query).data()).getShipmentGatewayDhls();

		if (shipmentGatewayDhls.size() == 1) {
			return ResponseEntity.ok().body(shipmentGatewayDhls.get(0));
		}

		return ResponseEntity.ok().body(shipmentGatewayDhls);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShipmentGatewayDhl(HttpServletRequest request) throws Exception {

		ShipmentGatewayDhl shipmentGatewayDhlToBeAdded = new ShipmentGatewayDhl();
		try {
			shipmentGatewayDhlToBeAdded = ShipmentGatewayDhlMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentGatewayDhl(shipmentGatewayDhlToBeAdded);

	}

	/**
	 * creates a new ShipmentGatewayDhl entry in the ofbiz database
	 * 
	 * @param shipmentGatewayDhlToBeAdded
	 *            the ShipmentGatewayDhl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentGatewayDhl(@RequestBody ShipmentGatewayDhl shipmentGatewayDhlToBeAdded) throws Exception {

		AddShipmentGatewayDhl command = new AddShipmentGatewayDhl(shipmentGatewayDhlToBeAdded);
		ShipmentGatewayDhl shipmentGatewayDhl = ((ShipmentGatewayDhlAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayDhl();
		
		if (shipmentGatewayDhl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentGatewayDhl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentGatewayDhl could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateShipmentGatewayDhl(HttpServletRequest request) throws Exception {

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

		ShipmentGatewayDhl shipmentGatewayDhlToBeUpdated = new ShipmentGatewayDhl();

		try {
			shipmentGatewayDhlToBeUpdated = ShipmentGatewayDhlMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentGatewayDhl(shipmentGatewayDhlToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentGatewayDhl(@RequestBody ShipmentGatewayDhl shipmentGatewayDhlToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentGatewayDhlToBeUpdated.setnull(null);

		UpdateShipmentGatewayDhl command = new UpdateShipmentGatewayDhl(shipmentGatewayDhlToBeUpdated);

		try {
			if(((ShipmentGatewayDhlUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentGatewayDhlId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentGatewayDhlId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayDhlId", shipmentGatewayDhlId);
		try {

			Object foundShipmentGatewayDhl = findShipmentGatewayDhlsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentGatewayDhl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentGatewayDhlId}")
	public ResponseEntity<Object> deleteShipmentGatewayDhlByIdUpdated(@PathVariable String shipmentGatewayDhlId) throws Exception {
		DeleteShipmentGatewayDhl command = new DeleteShipmentGatewayDhl(shipmentGatewayDhlId);

		try {
			if (((ShipmentGatewayDhlDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentGatewayDhl could not be deleted");

	}

}
