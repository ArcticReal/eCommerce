package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex;

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
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.command.AddShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.command.DeleteShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.command.UpdateShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.mapper.ShipmentGatewayFedexMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.query.FindShipmentGatewayFedexsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentGatewayFedexs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentGatewayFedexId}")
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentGatewayFedexId}")
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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentGatewayFedex/\" plus one of the following: "
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