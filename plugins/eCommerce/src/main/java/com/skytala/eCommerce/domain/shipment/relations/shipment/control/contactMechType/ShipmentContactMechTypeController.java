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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/shipmentContactMechTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentContactMechTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentContactMechTypesBy query = new FindShipmentContactMechTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentContactMechType> shipmentContactMechTypes =((ShipmentContactMechTypeFound) Scheduler.execute(query).data()).getShipmentContactMechTypes();

		if (shipmentContactMechTypes.size() == 1) {
			return ResponseEntity.ok().body(shipmentContactMechTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShipmentContactMechType(HttpServletRequest request) throws Exception {

		ShipmentContactMechType shipmentContactMechTypeToBeAdded = new ShipmentContactMechType();
		try {
			shipmentContactMechTypeToBeAdded = ShipmentContactMechTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createShipmentContactMechType(@RequestBody ShipmentContactMechType shipmentContactMechTypeToBeAdded) throws Exception {

		AddShipmentContactMechType command = new AddShipmentContactMechType(shipmentContactMechTypeToBeAdded);
		ShipmentContactMechType shipmentContactMechType = ((ShipmentContactMechTypeAdded) Scheduler.execute(command).data()).getAddedShipmentContactMechType();
		
		if (shipmentContactMechType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentContactMechType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentContactMechType could not be created.");
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
	public boolean updateShipmentContactMechType(HttpServletRequest request) throws Exception {

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

		ShipmentContactMechType shipmentContactMechTypeToBeUpdated = new ShipmentContactMechType();

		try {
			shipmentContactMechTypeToBeUpdated = ShipmentContactMechTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentContactMechType(shipmentContactMechTypeToBeUpdated, shipmentContactMechTypeToBeUpdated.getShipmentContactMechTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentContactMechType(@RequestBody ShipmentContactMechType shipmentContactMechTypeToBeUpdated,
			@PathVariable String shipmentContactMechTypeId) throws Exception {

		shipmentContactMechTypeToBeUpdated.setShipmentContactMechTypeId(shipmentContactMechTypeId);

		UpdateShipmentContactMechType command = new UpdateShipmentContactMechType(shipmentContactMechTypeToBeUpdated);

		try {
			if(((ShipmentContactMechTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentContactMechTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentContactMechTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentContactMechTypeId", shipmentContactMechTypeId);
		try {

			Object foundShipmentContactMechType = findShipmentContactMechTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentContactMechType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentContactMechTypeId}")
	public ResponseEntity<Object> deleteShipmentContactMechTypeByIdUpdated(@PathVariable String shipmentContactMechTypeId) throws Exception {
		DeleteShipmentContactMechType command = new DeleteShipmentContactMechType(shipmentContactMechTypeId);

		try {
			if (((ShipmentContactMechTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentContactMechType could not be deleted");

	}

}
