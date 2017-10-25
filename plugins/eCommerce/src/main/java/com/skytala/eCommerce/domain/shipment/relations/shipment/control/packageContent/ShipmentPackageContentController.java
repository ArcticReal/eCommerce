package com.skytala.eCommerce.domain.shipment.relations.shipment.control.packageContent;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageContent.AddShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageContent.DeleteShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageContent.UpdateShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.packageContent.ShipmentPackageContentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageContent.ShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.packageContent.FindShipmentPackageContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipmentPackageContents")
public class ShipmentPackageContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentPackageContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentPackageContent
	 * @return a List with the ShipmentPackageContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentPackageContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentPackageContentsBy query = new FindShipmentPackageContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentPackageContent> shipmentPackageContents =((ShipmentPackageContentFound) Scheduler.execute(query).data()).getShipmentPackageContents();

		if (shipmentPackageContents.size() == 1) {
			return ResponseEntity.ok().body(shipmentPackageContents.get(0));
		}

		return ResponseEntity.ok().body(shipmentPackageContents);

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
	public ResponseEntity<Object> createShipmentPackageContent(HttpServletRequest request) throws Exception {

		ShipmentPackageContent shipmentPackageContentToBeAdded = new ShipmentPackageContent();
		try {
			shipmentPackageContentToBeAdded = ShipmentPackageContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentPackageContent(shipmentPackageContentToBeAdded);

	}

	/**
	 * creates a new ShipmentPackageContent entry in the ofbiz database
	 * 
	 * @param shipmentPackageContentToBeAdded
	 *            the ShipmentPackageContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentPackageContent(@RequestBody ShipmentPackageContent shipmentPackageContentToBeAdded) throws Exception {

		AddShipmentPackageContent command = new AddShipmentPackageContent(shipmentPackageContentToBeAdded);
		ShipmentPackageContent shipmentPackageContent = ((ShipmentPackageContentAdded) Scheduler.execute(command).data()).getAddedShipmentPackageContent();
		
		if (shipmentPackageContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentPackageContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentPackageContent could not be created.");
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
	public boolean updateShipmentPackageContent(HttpServletRequest request) throws Exception {

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

		ShipmentPackageContent shipmentPackageContentToBeUpdated = new ShipmentPackageContent();

		try {
			shipmentPackageContentToBeUpdated = ShipmentPackageContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentPackageContent(shipmentPackageContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentPackageContent with the specific Id
	 * 
	 * @param shipmentPackageContentToBeUpdated
	 *            the ShipmentPackageContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentPackageContent(@RequestBody ShipmentPackageContent shipmentPackageContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentPackageContentToBeUpdated.setnull(null);

		UpdateShipmentPackageContent command = new UpdateShipmentPackageContent(shipmentPackageContentToBeUpdated);

		try {
			if(((ShipmentPackageContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentPackageContentId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentPackageContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentPackageContentId", shipmentPackageContentId);
		try {

			Object foundShipmentPackageContent = findShipmentPackageContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentPackageContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentPackageContentId}")
	public ResponseEntity<Object> deleteShipmentPackageContentByIdUpdated(@PathVariable String shipmentPackageContentId) throws Exception {
		DeleteShipmentPackageContent command = new DeleteShipmentPackageContent(shipmentPackageContentId);

		try {
			if (((ShipmentPackageContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentPackageContent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/shipmentPackageContent/\" plus one of the following: "
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
