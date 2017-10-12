package com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt;

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
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.command.AddShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.command.DeleteShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.command.UpdateShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event.ShipmentReceiptAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event.ShipmentReceiptDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event.ShipmentReceiptFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event.ShipmentReceiptUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.mapper.ShipmentReceiptMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.model.ShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.query.FindShipmentReceiptsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentReceipts")
public class ShipmentReceiptController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentReceiptController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentReceipt
	 * @return a List with the ShipmentReceipts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentReceiptsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentReceiptsBy query = new FindShipmentReceiptsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentReceipt> shipmentReceipts =((ShipmentReceiptFound) Scheduler.execute(query).data()).getShipmentReceipts();

		if (shipmentReceipts.size() == 1) {
			return ResponseEntity.ok().body(shipmentReceipts.get(0));
		}

		return ResponseEntity.ok().body(shipmentReceipts);

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
	public ResponseEntity<Object> createShipmentReceipt(HttpServletRequest request) throws Exception {

		ShipmentReceipt shipmentReceiptToBeAdded = new ShipmentReceipt();
		try {
			shipmentReceiptToBeAdded = ShipmentReceiptMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentReceipt(shipmentReceiptToBeAdded);

	}

	/**
	 * creates a new ShipmentReceipt entry in the ofbiz database
	 * 
	 * @param shipmentReceiptToBeAdded
	 *            the ShipmentReceipt thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentReceipt(@RequestBody ShipmentReceipt shipmentReceiptToBeAdded) throws Exception {

		AddShipmentReceipt command = new AddShipmentReceipt(shipmentReceiptToBeAdded);
		ShipmentReceipt shipmentReceipt = ((ShipmentReceiptAdded) Scheduler.execute(command).data()).getAddedShipmentReceipt();
		
		if (shipmentReceipt != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentReceipt);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentReceipt could not be created.");
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
	public boolean updateShipmentReceipt(HttpServletRequest request) throws Exception {

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

		ShipmentReceipt shipmentReceiptToBeUpdated = new ShipmentReceipt();

		try {
			shipmentReceiptToBeUpdated = ShipmentReceiptMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentReceipt(shipmentReceiptToBeUpdated, shipmentReceiptToBeUpdated.getReceiptId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentReceipt with the specific Id
	 * 
	 * @param shipmentReceiptToBeUpdated
	 *            the ShipmentReceipt thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{receiptId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentReceipt(@RequestBody ShipmentReceipt shipmentReceiptToBeUpdated,
			@PathVariable String receiptId) throws Exception {

		shipmentReceiptToBeUpdated.setReceiptId(receiptId);

		UpdateShipmentReceipt command = new UpdateShipmentReceipt(shipmentReceiptToBeUpdated);

		try {
			if(((ShipmentReceiptUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentReceiptId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentReceiptId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentReceiptId", shipmentReceiptId);
		try {

			Object foundShipmentReceipt = findShipmentReceiptsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentReceipt);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentReceiptId}")
	public ResponseEntity<Object> deleteShipmentReceiptByIdUpdated(@PathVariable String shipmentReceiptId) throws Exception {
		DeleteShipmentReceipt command = new DeleteShipmentReceipt(shipmentReceiptId);

		try {
			if (((ShipmentReceiptDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentReceipt could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentReceipt/\" plus one of the following: "
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
