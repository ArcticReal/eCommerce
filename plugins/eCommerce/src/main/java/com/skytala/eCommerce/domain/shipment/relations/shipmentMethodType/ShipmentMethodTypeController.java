package com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.command.AddShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.command.DeleteShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.command.UpdateShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.event.ShipmentMethodTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.event.ShipmentMethodTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.event.ShipmentMethodTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.event.ShipmentMethodTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.mapper.ShipmentMethodTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.model.ShipmentMethodType;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.query.FindShipmentMethodTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentMethodTypes")
public class ShipmentMethodTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentMethodTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentMethodType
	 * @return a List with the ShipmentMethodTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentMethodTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentMethodTypesBy query = new FindShipmentMethodTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentMethodType> shipmentMethodTypes =((ShipmentMethodTypeFound) Scheduler.execute(query).data()).getShipmentMethodTypes();

		if (shipmentMethodTypes.size() == 1) {
			return ResponseEntity.ok().body(shipmentMethodTypes.get(0));
		}

		return ResponseEntity.ok().body(shipmentMethodTypes);

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
	public ResponseEntity<Object> createShipmentMethodType(HttpServletRequest request) throws Exception {

		ShipmentMethodType shipmentMethodTypeToBeAdded = new ShipmentMethodType();
		try {
			shipmentMethodTypeToBeAdded = ShipmentMethodTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentMethodType(shipmentMethodTypeToBeAdded);

	}

	/**
	 * creates a new ShipmentMethodType entry in the ofbiz database
	 * 
	 * @param shipmentMethodTypeToBeAdded
	 *            the ShipmentMethodType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentMethodType(@RequestBody ShipmentMethodType shipmentMethodTypeToBeAdded) throws Exception {

		AddShipmentMethodType command = new AddShipmentMethodType(shipmentMethodTypeToBeAdded);
		ShipmentMethodType shipmentMethodType = ((ShipmentMethodTypeAdded) Scheduler.execute(command).data()).getAddedShipmentMethodType();
		
		if (shipmentMethodType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentMethodType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentMethodType could not be created.");
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
	public boolean updateShipmentMethodType(HttpServletRequest request) throws Exception {

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

		ShipmentMethodType shipmentMethodTypeToBeUpdated = new ShipmentMethodType();

		try {
			shipmentMethodTypeToBeUpdated = ShipmentMethodTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentMethodType(shipmentMethodTypeToBeUpdated, shipmentMethodTypeToBeUpdated.getShipmentMethodTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentMethodType with the specific Id
	 * 
	 * @param shipmentMethodTypeToBeUpdated
	 *            the ShipmentMethodType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentMethodTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentMethodType(@RequestBody ShipmentMethodType shipmentMethodTypeToBeUpdated,
			@PathVariable String shipmentMethodTypeId) throws Exception {

		shipmentMethodTypeToBeUpdated.setShipmentMethodTypeId(shipmentMethodTypeId);

		UpdateShipmentMethodType command = new UpdateShipmentMethodType(shipmentMethodTypeToBeUpdated);

		try {
			if(((ShipmentMethodTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentMethodTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentMethodTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentMethodTypeId", shipmentMethodTypeId);
		try {

			Object foundShipmentMethodType = findShipmentMethodTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentMethodType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentMethodTypeId}")
	public ResponseEntity<Object> deleteShipmentMethodTypeByIdUpdated(@PathVariable String shipmentMethodTypeId) throws Exception {
		DeleteShipmentMethodType command = new DeleteShipmentMethodType(shipmentMethodTypeId);

		try {
			if (((ShipmentMethodTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentMethodType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentMethodType/\" plus one of the following: "
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