package com.skytala.eCommerce.domain.shipment.relations.shipment.control.boxType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.boxType.AddShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.boxType.DeleteShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.boxType.UpdateShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.boxType.ShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.boxType.ShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.boxType.FindShipmentBoxTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipment/shipment/shipmentBoxTypes")
public class ShipmentBoxTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentBoxTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentBoxType
	 * @return a List with the ShipmentBoxTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentBoxTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentBoxTypesBy query = new FindShipmentBoxTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentBoxType> shipmentBoxTypes =((ShipmentBoxTypeFound) Scheduler.execute(query).data()).getShipmentBoxTypes();

		if (shipmentBoxTypes.size() == 1) {
			return ResponseEntity.ok().body(shipmentBoxTypes.get(0));
		}

		return ResponseEntity.ok().body(shipmentBoxTypes);

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
	public ResponseEntity<Object> createShipmentBoxType(HttpServletRequest request) throws Exception {

		ShipmentBoxType shipmentBoxTypeToBeAdded = new ShipmentBoxType();
		try {
			shipmentBoxTypeToBeAdded = ShipmentBoxTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentBoxType(shipmentBoxTypeToBeAdded);

	}

	/**
	 * creates a new ShipmentBoxType entry in the ofbiz database
	 * 
	 * @param shipmentBoxTypeToBeAdded
	 *            the ShipmentBoxType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentBoxType(@RequestBody ShipmentBoxType shipmentBoxTypeToBeAdded) throws Exception {

		AddShipmentBoxType command = new AddShipmentBoxType(shipmentBoxTypeToBeAdded);
		ShipmentBoxType shipmentBoxType = ((ShipmentBoxTypeAdded) Scheduler.execute(command).data()).getAddedShipmentBoxType();
		
		if (shipmentBoxType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentBoxType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentBoxType could not be created.");
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
	public boolean updateShipmentBoxType(HttpServletRequest request) throws Exception {

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

		ShipmentBoxType shipmentBoxTypeToBeUpdated = new ShipmentBoxType();

		try {
			shipmentBoxTypeToBeUpdated = ShipmentBoxTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentBoxType(shipmentBoxTypeToBeUpdated, shipmentBoxTypeToBeUpdated.getShipmentBoxTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentBoxType with the specific Id
	 * 
	 * @param shipmentBoxTypeToBeUpdated
	 *            the ShipmentBoxType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentBoxTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentBoxType(@RequestBody ShipmentBoxType shipmentBoxTypeToBeUpdated,
			@PathVariable String shipmentBoxTypeId) throws Exception {

		shipmentBoxTypeToBeUpdated.setShipmentBoxTypeId(shipmentBoxTypeId);

		UpdateShipmentBoxType command = new UpdateShipmentBoxType(shipmentBoxTypeToBeUpdated);

		try {
			if(((ShipmentBoxTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentBoxTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentBoxTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentBoxTypeId", shipmentBoxTypeId);
		try {

			Object foundShipmentBoxType = findShipmentBoxTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentBoxType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentBoxTypeId}")
	public ResponseEntity<Object> deleteShipmentBoxTypeByIdUpdated(@PathVariable String shipmentBoxTypeId) throws Exception {
		DeleteShipmentBoxType command = new DeleteShipmentBoxType(shipmentBoxTypeId);

		try {
			if (((ShipmentBoxTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentBoxType could not be deleted");

	}

}
