package com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute;

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
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.command.AddShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.command.DeleteShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.command.UpdateShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.event.ShipmentAttributeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.event.ShipmentAttributeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.event.ShipmentAttributeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.event.ShipmentAttributeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.mapper.ShipmentAttributeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.model.ShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.query.FindShipmentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentAttributes")
public class ShipmentAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentAttribute
	 * @return a List with the ShipmentAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentAttributesBy query = new FindShipmentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentAttribute> shipmentAttributes =((ShipmentAttributeFound) Scheduler.execute(query).data()).getShipmentAttributes();

		if (shipmentAttributes.size() == 1) {
			return ResponseEntity.ok().body(shipmentAttributes.get(0));
		}

		return ResponseEntity.ok().body(shipmentAttributes);

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
	public ResponseEntity<Object> createShipmentAttribute(HttpServletRequest request) throws Exception {

		ShipmentAttribute shipmentAttributeToBeAdded = new ShipmentAttribute();
		try {
			shipmentAttributeToBeAdded = ShipmentAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentAttribute(shipmentAttributeToBeAdded);

	}

	/**
	 * creates a new ShipmentAttribute entry in the ofbiz database
	 * 
	 * @param shipmentAttributeToBeAdded
	 *            the ShipmentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentAttribute(@RequestBody ShipmentAttribute shipmentAttributeToBeAdded) throws Exception {

		AddShipmentAttribute command = new AddShipmentAttribute(shipmentAttributeToBeAdded);
		ShipmentAttribute shipmentAttribute = ((ShipmentAttributeAdded) Scheduler.execute(command).data()).getAddedShipmentAttribute();
		
		if (shipmentAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentAttribute could not be created.");
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
	public boolean updateShipmentAttribute(HttpServletRequest request) throws Exception {

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

		ShipmentAttribute shipmentAttributeToBeUpdated = new ShipmentAttribute();

		try {
			shipmentAttributeToBeUpdated = ShipmentAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentAttribute(shipmentAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentAttribute with the specific Id
	 * 
	 * @param shipmentAttributeToBeUpdated
	 *            the ShipmentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentAttribute(@RequestBody ShipmentAttribute shipmentAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentAttributeToBeUpdated.setnull(null);

		UpdateShipmentAttribute command = new UpdateShipmentAttribute(shipmentAttributeToBeUpdated);

		try {
			if(((ShipmentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentAttributeId", shipmentAttributeId);
		try {

			Object foundShipmentAttribute = findShipmentAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentAttributeId}")
	public ResponseEntity<Object> deleteShipmentAttributeByIdUpdated(@PathVariable String shipmentAttributeId) throws Exception {
		DeleteShipmentAttribute command = new DeleteShipmentAttribute(shipmentAttributeId);

		try {
			if (((ShipmentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentAttribute/\" plus one of the following: "
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
