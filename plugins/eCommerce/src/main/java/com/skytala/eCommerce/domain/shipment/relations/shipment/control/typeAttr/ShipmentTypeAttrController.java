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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ShipmentTypeAttr>> findShipmentTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentTypeAttrsBy query = new FindShipmentTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentTypeAttr> shipmentTypeAttrs =((ShipmentTypeAttrFound) Scheduler.execute(query).data()).getShipmentTypeAttrs();

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
	public ResponseEntity<ShipmentTypeAttr> createShipmentTypeAttr(HttpServletRequest request) throws Exception {

		ShipmentTypeAttr shipmentTypeAttrToBeAdded = new ShipmentTypeAttr();
		try {
			shipmentTypeAttrToBeAdded = ShipmentTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ShipmentTypeAttr> createShipmentTypeAttr(@RequestBody ShipmentTypeAttr shipmentTypeAttrToBeAdded) throws Exception {

		AddShipmentTypeAttr command = new AddShipmentTypeAttr(shipmentTypeAttrToBeAdded);
		ShipmentTypeAttr shipmentTypeAttr = ((ShipmentTypeAttrAdded) Scheduler.execute(command).data()).getAddedShipmentTypeAttr();
		
		if (shipmentTypeAttr != null) 
			return successful(shipmentTypeAttr);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateShipmentTypeAttr(@RequestBody ShipmentTypeAttr shipmentTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		shipmentTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateShipmentTypeAttr command = new UpdateShipmentTypeAttr(shipmentTypeAttrToBeUpdated);

		try {
			if(((ShipmentTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentTypeAttrId}")
	public ResponseEntity<ShipmentTypeAttr> findById(@PathVariable String shipmentTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentTypeAttrId", shipmentTypeAttrId);
		try {

			List<ShipmentTypeAttr> foundShipmentTypeAttr = findShipmentTypeAttrsBy(requestParams).getBody();
			if(foundShipmentTypeAttr.size()==1){				return successful(foundShipmentTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentTypeAttrId}")
	public ResponseEntity<String> deleteShipmentTypeAttrByIdUpdated(@PathVariable String shipmentTypeAttrId) throws Exception {
		DeleteShipmentTypeAttr command = new DeleteShipmentTypeAttr(shipmentTypeAttrId);

		try {
			if (((ShipmentTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
