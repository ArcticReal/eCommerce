package com.skytala.eCommerce.domain.shipment.relations.shipment.control.attribute;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.attribute.AddShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.attribute.DeleteShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.attribute.UpdateShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute.ShipmentAttributeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute.ShipmentAttributeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute.ShipmentAttributeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute.ShipmentAttributeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.attribute.ShipmentAttributeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.attribute.ShipmentAttribute;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.attribute.FindShipmentAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentAttribute>> findShipmentAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentAttributesBy query = new FindShipmentAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentAttribute> shipmentAttributes =((ShipmentAttributeFound) Scheduler.execute(query).data()).getShipmentAttributes();

		return ResponseEntity.ok().body(shipmentAttributes);

	}

	/**
	 * creates a new ShipmentAttribute entry in the ofbiz database
	 * 
	 * @param shipmentAttributeToBeAdded
	 *            the ShipmentAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentAttribute> createShipmentAttribute(@RequestBody ShipmentAttribute shipmentAttributeToBeAdded) throws Exception {

		AddShipmentAttribute command = new AddShipmentAttribute(shipmentAttributeToBeAdded);
		ShipmentAttribute shipmentAttribute = ((ShipmentAttributeAdded) Scheduler.execute(command).data()).getAddedShipmentAttribute();
		
		if (shipmentAttribute != null) 
			return successful(shipmentAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentAttribute with the specific Id
	 * 
	 * @param shipmentAttributeToBeUpdated
	 *            the ShipmentAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentAttribute(@RequestBody ShipmentAttribute shipmentAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		shipmentAttributeToBeUpdated.setAttrName(attrName);

		UpdateShipmentAttribute command = new UpdateShipmentAttribute(shipmentAttributeToBeUpdated);

		try {
			if(((ShipmentAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentAttributeId}")
	public ResponseEntity<ShipmentAttribute> findById(@PathVariable String shipmentAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentAttributeId", shipmentAttributeId);
		try {

			List<ShipmentAttribute> foundShipmentAttribute = findShipmentAttributesBy(requestParams).getBody();
			if(foundShipmentAttribute.size()==1){				return successful(foundShipmentAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentAttributeId}")
	public ResponseEntity<String> deleteShipmentAttributeByIdUpdated(@PathVariable String shipmentAttributeId) throws Exception {
		DeleteShipmentAttribute command = new DeleteShipmentAttribute(shipmentAttributeId);

		try {
			if (((ShipmentAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
