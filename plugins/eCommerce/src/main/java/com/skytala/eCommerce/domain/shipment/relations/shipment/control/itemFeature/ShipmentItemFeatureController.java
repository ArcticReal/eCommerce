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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentItemFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentItemFeaturesBy query = new FindShipmentItemFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentItemFeature> shipmentItemFeatures =((ShipmentItemFeatureFound) Scheduler.execute(query).data()).getShipmentItemFeatures();

		if (shipmentItemFeatures.size() == 1) {
			return ResponseEntity.ok().body(shipmentItemFeatures.get(0));
		}

		return ResponseEntity.ok().body(shipmentItemFeatures);

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
	public ResponseEntity<Object> createShipmentItemFeature(HttpServletRequest request) throws Exception {

		ShipmentItemFeature shipmentItemFeatureToBeAdded = new ShipmentItemFeature();
		try {
			shipmentItemFeatureToBeAdded = ShipmentItemFeatureMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentItemFeature(shipmentItemFeatureToBeAdded);

	}

	/**
	 * creates a new ShipmentItemFeature entry in the ofbiz database
	 * 
	 * @param shipmentItemFeatureToBeAdded
	 *            the ShipmentItemFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentItemFeature(@RequestBody ShipmentItemFeature shipmentItemFeatureToBeAdded) throws Exception {

		AddShipmentItemFeature command = new AddShipmentItemFeature(shipmentItemFeatureToBeAdded);
		ShipmentItemFeature shipmentItemFeature = ((ShipmentItemFeatureAdded) Scheduler.execute(command).data()).getAddedShipmentItemFeature();
		
		if (shipmentItemFeature != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentItemFeature);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentItemFeature could not be created.");
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
	public boolean updateShipmentItemFeature(HttpServletRequest request) throws Exception {

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

		ShipmentItemFeature shipmentItemFeatureToBeUpdated = new ShipmentItemFeature();

		try {
			shipmentItemFeatureToBeUpdated = ShipmentItemFeatureMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentItemFeature(shipmentItemFeatureToBeUpdated, shipmentItemFeatureToBeUpdated.getShipmentItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentItemFeature(@RequestBody ShipmentItemFeature shipmentItemFeatureToBeUpdated,
			@PathVariable String shipmentItemSeqId) throws Exception {

		shipmentItemFeatureToBeUpdated.setShipmentItemSeqId(shipmentItemSeqId);

		UpdateShipmentItemFeature command = new UpdateShipmentItemFeature(shipmentItemFeatureToBeUpdated);

		try {
			if(((ShipmentItemFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentItemFeatureId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentItemFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentItemFeatureId", shipmentItemFeatureId);
		try {

			Object foundShipmentItemFeature = findShipmentItemFeaturesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentItemFeature);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentItemFeatureId}")
	public ResponseEntity<Object> deleteShipmentItemFeatureByIdUpdated(@PathVariable String shipmentItemFeatureId) throws Exception {
		DeleteShipmentItemFeature command = new DeleteShipmentItemFeature(shipmentItemFeatureId);

		try {
			if (((ShipmentItemFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentItemFeature could not be deleted");

	}

}
