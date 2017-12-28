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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ShipmentCostEstimate>> findShipmentCostEstimatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentCostEstimatesBy query = new FindShipmentCostEstimatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentCostEstimate> shipmentCostEstimates =((ShipmentCostEstimateFound) Scheduler.execute(query).data()).getShipmentCostEstimates();

		return ResponseEntity.ok().body(shipmentCostEstimates);

	}

	/**
	 * creates a new ShipmentCostEstimate entry in the ofbiz database
	 * 
	 * @param shipmentCostEstimateToBeAdded
	 *            the ShipmentCostEstimate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentCostEstimate> createShipmentCostEstimate(@RequestBody ShipmentCostEstimate shipmentCostEstimateToBeAdded) throws Exception {

		AddShipmentCostEstimate command = new AddShipmentCostEstimate(shipmentCostEstimateToBeAdded);
		ShipmentCostEstimate shipmentCostEstimate = ((ShipmentCostEstimateAdded) Scheduler.execute(command).data()).getAddedShipmentCostEstimate();
		
		if (shipmentCostEstimate != null) 
			return successful(shipmentCostEstimate);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateShipmentCostEstimate(@RequestBody ShipmentCostEstimate shipmentCostEstimateToBeUpdated,
			@PathVariable String shipmentCostEstimateId) throws Exception {

		shipmentCostEstimateToBeUpdated.setShipmentCostEstimateId(shipmentCostEstimateId);

		UpdateShipmentCostEstimate command = new UpdateShipmentCostEstimate(shipmentCostEstimateToBeUpdated);

		try {
			if(((ShipmentCostEstimateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentCostEstimateId}")
	public ResponseEntity<ShipmentCostEstimate> findById(@PathVariable String shipmentCostEstimateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentCostEstimateId", shipmentCostEstimateId);
		try {

			List<ShipmentCostEstimate> foundShipmentCostEstimate = findShipmentCostEstimatesBy(requestParams).getBody();
			if(foundShipmentCostEstimate.size()==1){				return successful(foundShipmentCostEstimate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentCostEstimateId}")
	public ResponseEntity<String> deleteShipmentCostEstimateByIdUpdated(@PathVariable String shipmentCostEstimateId) throws Exception {
		DeleteShipmentCostEstimate command = new DeleteShipmentCostEstimate(shipmentCostEstimateId);

		try {
			if (((ShipmentCostEstimateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
