package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.command.AddShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.command.DeleteShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.command.UpdateShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event.ShipmentGatewayConfigAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event.ShipmentGatewayConfigDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event.ShipmentGatewayConfigFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event.ShipmentGatewayConfigUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.mapper.ShipmentGatewayConfigMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.model.ShipmentGatewayConfig;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.query.FindShipmentGatewayConfigsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentGatewayConfigs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentGatewayConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentGatewayConfigsBy query = new FindShipmentGatewayConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentGatewayConfig> shipmentGatewayConfigs =((ShipmentGatewayConfigFound) Scheduler.execute(query).data()).getShipmentGatewayConfigs();

		if (shipmentGatewayConfigs.size() == 1) {
			return ResponseEntity.ok().body(shipmentGatewayConfigs.get(0));
		}

		return ResponseEntity.ok().body(shipmentGatewayConfigs);

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
	public ResponseEntity<Object> createShipmentGatewayConfig(HttpServletRequest request) throws Exception {

		ShipmentGatewayConfig shipmentGatewayConfigToBeAdded = new ShipmentGatewayConfig();
		try {
			shipmentGatewayConfigToBeAdded = ShipmentGatewayConfigMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentGatewayConfig(shipmentGatewayConfigToBeAdded);

	}

	/**
	 * creates a new ShipmentGatewayConfig entry in the ofbiz database
	 * 
	 * @param shipmentGatewayConfigToBeAdded
	 *            the ShipmentGatewayConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentGatewayConfig(@RequestBody ShipmentGatewayConfig shipmentGatewayConfigToBeAdded) throws Exception {

		AddShipmentGatewayConfig command = new AddShipmentGatewayConfig(shipmentGatewayConfigToBeAdded);
		ShipmentGatewayConfig shipmentGatewayConfig = ((ShipmentGatewayConfigAdded) Scheduler.execute(command).data()).getAddedShipmentGatewayConfig();
		
		if (shipmentGatewayConfig != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentGatewayConfig);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentGatewayConfig could not be created.");
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
	public boolean updateShipmentGatewayConfig(HttpServletRequest request) throws Exception {

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

		ShipmentGatewayConfig shipmentGatewayConfigToBeUpdated = new ShipmentGatewayConfig();

		try {
			shipmentGatewayConfigToBeUpdated = ShipmentGatewayConfigMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentGatewayConfig(shipmentGatewayConfigToBeUpdated, shipmentGatewayConfigToBeUpdated.getShipmentGatewayConfigId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentGatewayConfig(@RequestBody ShipmentGatewayConfig shipmentGatewayConfigToBeUpdated,
			@PathVariable String shipmentGatewayConfigId) throws Exception {

		shipmentGatewayConfigToBeUpdated.setShipmentGatewayConfigId(shipmentGatewayConfigId);

		UpdateShipmentGatewayConfig command = new UpdateShipmentGatewayConfig(shipmentGatewayConfigToBeUpdated);

		try {
			if(((ShipmentGatewayConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentGatewayConfigId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentGatewayConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentGatewayConfigId", shipmentGatewayConfigId);
		try {

			Object foundShipmentGatewayConfig = findShipmentGatewayConfigsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentGatewayConfig);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentGatewayConfigId}")
	public ResponseEntity<Object> deleteShipmentGatewayConfigByIdUpdated(@PathVariable String shipmentGatewayConfigId) throws Exception {
		DeleteShipmentGatewayConfig command = new DeleteShipmentGatewayConfig(shipmentGatewayConfigId);

		try {
			if (((ShipmentGatewayConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentGatewayConfig could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentGatewayConfig/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
