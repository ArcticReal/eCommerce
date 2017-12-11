package com.skytala.eCommerce.domain.shipment.relations.shipment.control.costEstimate;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.costEstimate.AddShipmentCostEstimate;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.costEstimate.DeleteShipmentCostEstimate;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.costEstimate.UpdateShipmentCostEstimate;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.costEstimate.ShipmentCostEstimateAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.costEstimate.ShipmentCostEstimateDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.costEstimate.ShipmentCostEstimateFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.costEstimate.ShipmentCostEstimateUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.costEstimate.ShipmentCostEstimateMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.costEstimate.ShipmentCostEstimate;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.costEstimate.FindShipmentCostEstimatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/shipment/shipment/shipmentCostEstimates")
public class ShipmentCostEstimateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentCostEstimateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentCostEstimate
	 * @return a List with the ShipmentCostEstimates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findShipmentCostEstimatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentCostEstimatesBy query = new FindShipmentCostEstimatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentCostEstimate> shipmentCostEstimates =((ShipmentCostEstimateFound) Scheduler.execute(query).data()).getShipmentCostEstimates();

		if (shipmentCostEstimates.size() == 1) {
			return ResponseEntity.ok().body(shipmentCostEstimates.get(0));
		}

		return ResponseEntity.ok().body(shipmentCostEstimates);

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
	public ResponseEntity<Object> createShipmentCostEstimate(HttpServletRequest request) throws Exception {

		ShipmentCostEstimate shipmentCostEstimateToBeAdded = new ShipmentCostEstimate();
		try {
			shipmentCostEstimateToBeAdded = ShipmentCostEstimateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentCostEstimate(shipmentCostEstimateToBeAdded);

	}

	/**
	 * creates a new ShipmentCostEstimate entry in the ofbiz database
	 * 
	 * @param shipmentCostEstimateToBeAdded
	 *            the ShipmentCostEstimate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentCostEstimate(@RequestBody ShipmentCostEstimate shipmentCostEstimateToBeAdded) throws Exception {

		AddShipmentCostEstimate command = new AddShipmentCostEstimate(shipmentCostEstimateToBeAdded);
		ShipmentCostEstimate shipmentCostEstimate = ((ShipmentCostEstimateAdded) Scheduler.execute(command).data()).getAddedShipmentCostEstimate();
		
		if (shipmentCostEstimate != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentCostEstimate);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentCostEstimate could not be created.");
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
	public boolean updateShipmentCostEstimate(HttpServletRequest request) throws Exception {

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

		ShipmentCostEstimate shipmentCostEstimateToBeUpdated = new ShipmentCostEstimate();

		try {
			shipmentCostEstimateToBeUpdated = ShipmentCostEstimateMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentCostEstimate(shipmentCostEstimateToBeUpdated, shipmentCostEstimateToBeUpdated.getShipmentCostEstimateId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ShipmentCostEstimate with the specific Id
	 * 
	 * @param shipmentCostEstimateToBeUpdated
	 *            the ShipmentCostEstimate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentCostEstimateId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipmentCostEstimate(@RequestBody ShipmentCostEstimate shipmentCostEstimateToBeUpdated,
			@PathVariable String shipmentCostEstimateId) throws Exception {

		shipmentCostEstimateToBeUpdated.setShipmentCostEstimateId(shipmentCostEstimateId);

		UpdateShipmentCostEstimate command = new UpdateShipmentCostEstimate(shipmentCostEstimateToBeUpdated);

		try {
			if(((ShipmentCostEstimateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{shipmentCostEstimateId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentCostEstimateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentCostEstimateId", shipmentCostEstimateId);
		try {

			Object foundShipmentCostEstimate = findShipmentCostEstimatesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentCostEstimate);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{shipmentCostEstimateId}")
	public ResponseEntity<Object> deleteShipmentCostEstimateByIdUpdated(@PathVariable String shipmentCostEstimateId) throws Exception {
		DeleteShipmentCostEstimate command = new DeleteShipmentCostEstimate(shipmentCostEstimateId);

		try {
			if (((ShipmentCostEstimateDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentCostEstimate could not be deleted");

	}

}
