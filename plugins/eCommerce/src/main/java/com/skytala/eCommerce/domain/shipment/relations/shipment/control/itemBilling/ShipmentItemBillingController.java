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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentItemBillings")
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
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentItemBilling>> findShipmentItemBillingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentItemBillingsBy query = new FindShipmentItemBillingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentItemBilling> shipmentItemBillings =((ShipmentItemBillingFound) Scheduler.execute(query).data()).getShipmentItemBillings();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ShipmentItemBilling> createShipmentItemBilling(HttpServletRequest request) throws Exception {

		ShipmentItemBilling shipmentItemBillingToBeAdded = new ShipmentItemBilling();
		try {
			shipmentItemBillingToBeAdded = ShipmentItemBillingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ShipmentItemBilling> createShipmentItemBilling(@RequestBody ShipmentItemBilling shipmentItemBillingToBeAdded) throws Exception {

		AddShipmentItemBilling command = new AddShipmentItemBilling(shipmentItemBillingToBeAdded);
		ShipmentItemBilling shipmentItemBilling = ((ShipmentItemBillingAdded) Scheduler.execute(command).data()).getAddedShipmentItemBilling();
		
		if (shipmentItemBilling != null) 
			return successful(shipmentItemBilling);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateShipmentItemBilling(@RequestBody ShipmentItemBilling shipmentItemBillingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentItemBillingToBeUpdated.setnull(null);

		UpdateShipmentItemBilling command = new UpdateShipmentItemBilling(shipmentItemBillingToBeUpdated);

		try {
			if(((ShipmentItemBillingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentItemBillingId}")
	public ResponseEntity<ShipmentItemBilling> findById(@PathVariable String shipmentItemBillingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentItemBillingId", shipmentItemBillingId);
		try {

			List<ShipmentItemBilling> foundShipmentItemBilling = findShipmentItemBillingsBy(requestParams).getBody();
			if(foundShipmentItemBilling.size()==1){				return successful(foundShipmentItemBilling.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentItemBillingId}")
	public ResponseEntity<String> deleteShipmentItemBillingByIdUpdated(@PathVariable String shipmentItemBillingId) throws Exception {
		DeleteShipmentItemBilling command = new DeleteShipmentItemBilling(shipmentItemBillingId);

		try {
			if (((ShipmentItemBillingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
