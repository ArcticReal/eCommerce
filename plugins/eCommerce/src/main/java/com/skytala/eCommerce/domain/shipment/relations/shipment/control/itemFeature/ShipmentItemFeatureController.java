package com.skytala.eCommerce.domain.shipment.relations.shipment.control.itemFeature;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.itemFeature.AddShipmentItemFeature;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.itemFeature.DeleteShipmentItemFeature;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.itemFeature.UpdateShipmentItemFeature;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemFeature.ShipmentItemFeatureAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemFeature.ShipmentItemFeatureDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemFeature.ShipmentItemFeatureFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.itemFeature.ShipmentItemFeatureUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.itemFeature.ShipmentItemFeatureMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemFeature.ShipmentItemFeature;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.itemFeature.FindShipmentItemFeaturesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentItemFeatures")
public class ShipmentItemFeatureController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentItemFeatureController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentItemFeature
	 * @return a List with the ShipmentItemFeatures
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentItemFeature>> findShipmentItemFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentItemFeaturesBy query = new FindShipmentItemFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentItemFeature> shipmentItemFeatures =((ShipmentItemFeatureFound) Scheduler.execute(query).data()).getShipmentItemFeatures();

		return ResponseEntity.ok().body(shipmentItemFeatures);

	}

	/**
	 * creates a new ShipmentItemFeature entry in the ofbiz database
	 * 
	 * @param shipmentItemFeatureToBeAdded
	 *            the ShipmentItemFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentItemFeature> createShipmentItemFeature(@RequestBody ShipmentItemFeature shipmentItemFeatureToBeAdded) throws Exception {

		AddShipmentItemFeature command = new AddShipmentItemFeature(shipmentItemFeatureToBeAdded);
		ShipmentItemFeature shipmentItemFeature = ((ShipmentItemFeatureAdded) Scheduler.execute(command).data()).getAddedShipmentItemFeature();
		
		if (shipmentItemFeature != null) 
			return successful(shipmentItemFeature);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentItemFeature with the specific Id
	 * 
	 * @param shipmentItemFeatureToBeUpdated
	 *            the ShipmentItemFeature thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentItemFeature(@RequestBody ShipmentItemFeature shipmentItemFeatureToBeUpdated,
			@PathVariable String shipmentItemSeqId) throws Exception {

		shipmentItemFeatureToBeUpdated.setShipmentItemSeqId(shipmentItemSeqId);

		UpdateShipmentItemFeature command = new UpdateShipmentItemFeature(shipmentItemFeatureToBeUpdated);

		try {
			if(((ShipmentItemFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentItemFeatureId}")
	public ResponseEntity<ShipmentItemFeature> findById(@PathVariable String shipmentItemFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentItemFeatureId", shipmentItemFeatureId);
		try {

			List<ShipmentItemFeature> foundShipmentItemFeature = findShipmentItemFeaturesBy(requestParams).getBody();
			if(foundShipmentItemFeature.size()==1){				return successful(foundShipmentItemFeature.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentItemFeatureId}")
	public ResponseEntity<String> deleteShipmentItemFeatureByIdUpdated(@PathVariable String shipmentItemFeatureId) throws Exception {
		DeleteShipmentItemFeature command = new DeleteShipmentItemFeature(shipmentItemFeatureId);

		try {
			if (((ShipmentItemFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
