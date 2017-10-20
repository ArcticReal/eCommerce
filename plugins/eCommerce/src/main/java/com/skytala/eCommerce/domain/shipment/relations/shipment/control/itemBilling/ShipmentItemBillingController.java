package com.skytala.eCommerce.domain.shipment.relations.shipment.control.itemBilling;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.itemBilling.AddShipmentItemBilling;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.itemBilling.DeleteShipmentItemBilling;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.itemBilling.UpdateShipmentItemBilling;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling.ShipmentItemBillingAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling.ShipmentItemBillingDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling.ShipmentItemBillingFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemBilling.ShipmentItemBillingUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.itemBilling.ShipmentItemBillingMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemBilling.ShipmentItemBilling;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.itemBilling.FindShipmentItemBillingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/shipmentItemBillings")
public class ShipmentItemBillingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentItemBillingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentItemBilling
	 * @return a List with the ShipmentItemBillings
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentItemBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentItemBillingsBy query = new FindShipmentItemBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentItemBilling> shipmentItemBillings =((ShipmentItemBillingFound) Scheduler.execute(query).data()).getShipmentItemBillings();

		if (shipmentItemBillings.size() == 1) {
			return ResponseEntity.ok().body(shipmentItemBillings.get(0));
		}

		return ResponseEntity.ok().body(shipmentItemBillings);

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
	public ResponseEntity<Object> createShipmentItemBilling(HttpServletRequest request) throws Exception {

		ShipmentItemBilling shipmentItemBillingToBeAdded = new ShipmentItemBilling();
		try {
			shipmentItemBillingToBeAdded = ShipmentItemBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentItemBilling(shipmentItemBillingToBeAdded);

	}

	/**
	 * creates a new ShipmentItemBilling entry in the ofbiz database
	 * 
	 * @param shipmentItemBillingToBeAdded
	 *            the ShipmentItemBilling thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentItemBilling(@RequestBody ShipmentItemBilling shipmentItemBillingToBeAdded) throws Exception {

		AddShipmentItemBilling command = new AddShipmentItemBilling(shipmentItemBillingToBeAdded);
		ShipmentItemBilling shipmentItemBilling = ((ShipmentItemBillingAdded) Scheduler.execute(command).data()).getAddedShipmentItemBilling();
		
		if (shipmentItemBilling != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentItemBilling);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentItemBilling could not be created.");
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
	public boolean updateShipmentItemBilling(HttpServletRequest request) throws Exception {

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

		ShipmentItemBilling shipmentItemBillingToBeUpdated = new ShipmentItemBilling();

		try {
			shipmentItemBillingToBeUpdated = ShipmentItemBillingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentItemBilling(shipmentItemBillingToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentItemBilling with the specific Id
	 * 
	 * @param shipmentItemBillingToBeUpdated
	 *            the ShipmentItemBilling thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentItemBilling(@RequestBody ShipmentItemBilling shipmentItemBillingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentItemBillingToBeUpdated.setnull(null);

		UpdateShipmentItemBilling command = new UpdateShipmentItemBilling(shipmentItemBillingToBeUpdated);

		try {
			if(((ShipmentItemBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentItemBillingId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentItemBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentItemBillingId", shipmentItemBillingId);
		try {

			Object foundShipmentItemBilling = findShipmentItemBillingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentItemBilling);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentItemBillingId}")
	public ResponseEntity<Object> deleteShipmentItemBillingByIdUpdated(@PathVariable String shipmentItemBillingId) throws Exception {
		DeleteShipmentItemBilling command = new DeleteShipmentItemBilling(shipmentItemBillingId);

		try {
			if (((ShipmentItemBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentItemBilling could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentItemBilling/\" plus one of the following: "
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
