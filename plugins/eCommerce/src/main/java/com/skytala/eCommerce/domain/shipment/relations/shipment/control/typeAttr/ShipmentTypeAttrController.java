package com.skytala.eCommerce.domain.shipment.relations.shipment.control.typeAttr;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.typeAttr.AddShipmentTypeAttr;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.typeAttr.DeleteShipmentTypeAttr;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.typeAttr.UpdateShipmentTypeAttr;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr.ShipmentTypeAttrAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr.ShipmentTypeAttrDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr.ShipmentTypeAttrFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.typeAttr.ShipmentTypeAttrUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.typeAttr.ShipmentTypeAttrMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.typeAttr.ShipmentTypeAttr;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.typeAttr.FindShipmentTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipment/shipment/shipmentTypeAttrs")
public class ShipmentTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentTypeAttr
	 * @return a List with the ShipmentTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findShipmentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentTypeAttrsBy query = new FindShipmentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentTypeAttr> shipmentTypeAttrs =((ShipmentTypeAttrFound) Scheduler.execute(query).data()).getShipmentTypeAttrs();

		if (shipmentTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(shipmentTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(shipmentTypeAttrs);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShipmentTypeAttr(HttpServletRequest request) throws Exception {

		ShipmentTypeAttr shipmentTypeAttrToBeAdded = new ShipmentTypeAttr();
		try {
			shipmentTypeAttrToBeAdded = ShipmentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentTypeAttr(shipmentTypeAttrToBeAdded);

	}

	/**
	 * creates a new ShipmentTypeAttr entry in the ofbiz database
	 * 
	 * @param shipmentTypeAttrToBeAdded
	 *            the ShipmentTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentTypeAttr(@RequestBody ShipmentTypeAttr shipmentTypeAttrToBeAdded) throws Exception {

		AddShipmentTypeAttr command = new AddShipmentTypeAttr(shipmentTypeAttrToBeAdded);
		ShipmentTypeAttr shipmentTypeAttr = ((ShipmentTypeAttrAdded) Scheduler.execute(command).data()).getAddedShipmentTypeAttr();
		
		if (shipmentTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentTypeAttr could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateShipmentTypeAttr(HttpServletRequest request) throws Exception {

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

		ShipmentTypeAttr shipmentTypeAttrToBeUpdated = new ShipmentTypeAttr();

		try {
			shipmentTypeAttrToBeUpdated = ShipmentTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentTypeAttr(shipmentTypeAttrToBeUpdated, shipmentTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentTypeAttr with the specific Id
	 * 
	 * @param shipmentTypeAttrToBeUpdated
	 *            the ShipmentTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentTypeAttr(@RequestBody ShipmentTypeAttr shipmentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		shipmentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateShipmentTypeAttr command = new UpdateShipmentTypeAttr(shipmentTypeAttrToBeUpdated);

		try {
			if(((ShipmentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shipmentTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentTypeAttrId", shipmentTypeAttrId);
		try {

			Object foundShipmentTypeAttr = findShipmentTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shipmentTypeAttrId}")
	public ResponseEntity<Object> deleteShipmentTypeAttrByIdUpdated(@PathVariable String shipmentTypeAttrId) throws Exception {
		DeleteShipmentTypeAttr command = new DeleteShipmentTypeAttr(shipmentTypeAttrId);

		try {
			if (((ShipmentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentTypeAttr could not be deleted");

	}

}
