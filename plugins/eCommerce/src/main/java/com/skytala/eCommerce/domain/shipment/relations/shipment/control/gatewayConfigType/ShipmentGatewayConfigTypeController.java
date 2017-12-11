package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayConfigType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType.AddShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType.DeleteShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType.UpdateShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfigType.ShipmentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType.ShipmentGatewayConfigType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayConfigType.FindShipmentGatewayConfigTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayConfigTypes")
public class ShipmentGatewayConfigTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayConfigTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayConfigType
	 * @return a List with the ShipmentGatewayConfigTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentGatewayConfigTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayConfigTypesBy query = new FindShipmentGatewayConfigTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayConfigType> shipmentGatewayConfigTypes =((ShipmentGatewayConfigTypeFound) Scheduler.execute(query).data()).getShipmentGatewayConfigTypes();

		if (shipmentGatewayConfigTypes.size() == 1) {
			return ResponseEntity.ok().body(shipmentGatewayConfigTypes.get(0));
		}

		return ResponseEntity.ok().body(shipmentGatewayConfigTypes);

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
	public ResponseEntity<Object> createShipmentGatewayConfigType(HttpServletRequest request) throws Exception {

		ShipmentGatewayConfigType shipmentGatewayConfigTypeToBeAdded = new ShipmentGatewayConfigType();
		try {
			shipmentGatewayConfigTypeToBeAdded = ShipmentGatewayConfigTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentGatewayConfigType(shipmentGatewayConfigTypeToBeAdded);

	}

	/**
	 * creates a new ShipmentGatewayConfigType entry in the ofbiz database
	 * 
	 * @param shipmentGatewayConfigTypeToBeAdded
	 *            the ShipmentGatewayConfigType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentGatewayConfigType(@RequestBody ShipmentGatewayConfigType shipmentGatewayConfigTypeToBeAdded) throws Exception {

		AddShipmentGatewayConfigType command = new AddShipmentGatewayConfigType(shipmentGatewayConfigTypeToBeAdded);
		ShipmentGatewayConfigType shipmentGatewayConfigType = ((ShipmentGatewayConfigTypeAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayConfigType();
		
		if (shipmentGatewayConfigType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentGatewayConfigType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentGatewayConfigType could not be created.");
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
	public boolean updateShipmentGatewayConfigType(HttpServletRequest request) throws Exception {

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

		ShipmentGatewayConfigType shipmentGatewayConfigTypeToBeUpdated = new ShipmentGatewayConfigType();

		try {
			shipmentGatewayConfigTypeToBeUpdated = ShipmentGatewayConfigTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentGatewayConfigType(shipmentGatewayConfigTypeToBeUpdated, shipmentGatewayConfigTypeToBeUpdated.getShipmentGatewayConfTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentGatewayConfigType with the specific Id
	 * 
	 * @param shipmentGatewayConfigTypeToBeUpdated
	 *            the ShipmentGatewayConfigType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentGatewayConfTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentGatewayConfigType(@RequestBody ShipmentGatewayConfigType shipmentGatewayConfigTypeToBeUpdated,
			@PathVariable String shipmentGatewayConfTypeId) throws Exception {

		shipmentGatewayConfigTypeToBeUpdated.setShipmentGatewayConfTypeId(shipmentGatewayConfTypeId);

		UpdateShipmentGatewayConfigType command = new UpdateShipmentGatewayConfigType(shipmentGatewayConfigTypeToBeUpdated);

		try {
			if(((ShipmentGatewayConfigTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentGatewayConfigTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentGatewayConfigTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayConfigTypeId", shipmentGatewayConfigTypeId);
		try {

			Object foundShipmentGatewayConfigType = findShipmentGatewayConfigTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentGatewayConfigType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentGatewayConfigTypeId}")
	public ResponseEntity<Object> deleteShipmentGatewayConfigTypeByIdUpdated(@PathVariable String shipmentGatewayConfigTypeId) throws Exception {
		DeleteShipmentGatewayConfigType command = new DeleteShipmentGatewayConfigType(shipmentGatewayConfigTypeId);

		try {
			if (((ShipmentGatewayConfigTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentGatewayConfigType could not be deleted");

	}

}
