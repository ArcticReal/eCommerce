package com.skytala.eCommerce.domain.shipment.relations.shipment.control.gatewayConfig;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfig.AddShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfig.DeleteShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfig.UpdateShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig.ShipmentGatewayConfigAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig.ShipmentGatewayConfigDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig.ShipmentGatewayConfigFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig.ShipmentGatewayConfigUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfig.ShipmentGatewayConfigMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig.ShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayConfig.FindShipmentGatewayConfigsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentGatewayConfigs")
public class ShipmentGatewayConfigController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentGatewayConfigController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentGatewayConfig
	 * @return a List with the ShipmentGatewayConfigs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentGatewayConfig>> findShipmentGatewayConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayConfigsBy query = new FindShipmentGatewayConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayConfig> shipmentGatewayConfigs =((ShipmentGatewayConfigFound) Scheduler.execute(query).data()).getShipmentGatewayConfigs();

		return ResponseEntity.ok().body(shipmentGatewayConfigs);

	}

	/**
	 * creates a new ShipmentGatewayConfig entry in the ofbiz database
	 * 
	 * @param shipmentGatewayConfigToBeAdded
	 *            the ShipmentGatewayConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentGatewayConfig> createShipmentGatewayConfig(@RequestBody ShipmentGatewayConfig shipmentGatewayConfigToBeAdded) throws Exception {

		AddShipmentGatewayConfig command = new AddShipmentGatewayConfig(shipmentGatewayConfigToBeAdded);
		ShipmentGatewayConfig shipmentGatewayConfig = ((ShipmentGatewayConfigAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayConfig();
		
		if (shipmentGatewayConfig != null) 
			return successful(shipmentGatewayConfig);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentGatewayConfig with the specific Id
	 * 
	 * @param shipmentGatewayConfigToBeUpdated
	 *            the ShipmentGatewayConfig thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentGatewayConfigId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentGatewayConfig(@RequestBody ShipmentGatewayConfig shipmentGatewayConfigToBeUpdated,
			@PathVariable String shipmentGatewayConfigId) throws Exception {

		shipmentGatewayConfigToBeUpdated.setShipmentGatewayConfigId(shipmentGatewayConfigId);

		UpdateShipmentGatewayConfig command = new UpdateShipmentGatewayConfig(shipmentGatewayConfigToBeUpdated);

		try {
			if(((ShipmentGatewayConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentGatewayConfigId}")
	public ResponseEntity<ShipmentGatewayConfig> findById(@PathVariable String shipmentGatewayConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayConfigId", shipmentGatewayConfigId);
		try {

			List<ShipmentGatewayConfig> foundShipmentGatewayConfig = findShipmentGatewayConfigsBy(requestParams).getBody();
			if(foundShipmentGatewayConfig.size()==1){				return successful(foundShipmentGatewayConfig.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentGatewayConfigId}")
	public ResponseEntity<String> deleteShipmentGatewayConfigByIdUpdated(@PathVariable String shipmentGatewayConfigId) throws Exception {
		DeleteShipmentGatewayConfig command = new DeleteShipmentGatewayConfig(shipmentGatewayConfigId);

		try {
			if (((ShipmentGatewayConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
