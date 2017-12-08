package com.skytala.eCommerce.domain.shipment.relations.shipment.control.type;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.type.AddShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.type.DeleteShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.type.UpdateShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.type.ShipmentTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.type.ShipmentType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.type.FindShipmentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentTypes")
public class ShipmentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentType
	 * @return a List with the ShipmentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentTypesBy query = new FindShipmentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentType> shipmentTypes =((ShipmentTypeFound) Scheduler.execute(query).data()).getShipmentTypes();

		if (shipmentTypes.size() == 1) {
			return ResponseEntity.ok().body(shipmentTypes.get(0));
		}

		return ResponseEntity.ok().body(shipmentTypes);

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
	public ResponseEntity<Object> createShipmentType(HttpServletRequest request) throws Exception {

		ShipmentType shipmentTypeToBeAdded = new ShipmentType();
		try {
			shipmentTypeToBeAdded = ShipmentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentType(shipmentTypeToBeAdded);

	}

	/**
	 * creates a new ShipmentType entry in the ofbiz database
	 * 
	 * @param shipmentTypeToBeAdded
	 *            the ShipmentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentType(@RequestBody ShipmentType shipmentTypeToBeAdded) throws Exception {

		AddShipmentType command = new AddShipmentType(shipmentTypeToBeAdded);
		ShipmentType shipmentType = ((ShipmentTypeAdded) Scheduler.execute(command).data()).getAddedShipmentType();
		
		if (shipmentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentType could not be created.");
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
	public boolean updateShipmentType(HttpServletRequest request) throws Exception {

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

		ShipmentType shipmentTypeToBeUpdated = new ShipmentType();

		try {
			shipmentTypeToBeUpdated = ShipmentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentType(shipmentTypeToBeUpdated, shipmentTypeToBeUpdated.getShipmentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentType with the specific Id
	 * 
	 * @param shipmentTypeToBeUpdated
	 *            the ShipmentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentType(@RequestBody ShipmentType shipmentTypeToBeUpdated,
			@PathVariable String shipmentTypeId) throws Exception {

		shipmentTypeToBeUpdated.setShipmentTypeId(shipmentTypeId);

		UpdateShipmentType command = new UpdateShipmentType(shipmentTypeToBeUpdated);

		try {
			if(((ShipmentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentTypeId", shipmentTypeId);
		try {

			Object foundShipmentType = findShipmentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentTypeId}")
	public ResponseEntity<Object> deleteShipmentTypeByIdUpdated(@PathVariable String shipmentTypeId) throws Exception {
		DeleteShipmentType command = new DeleteShipmentType(shipmentTypeId);

		try {
			if (((ShipmentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentType could not be deleted");

	}

}
