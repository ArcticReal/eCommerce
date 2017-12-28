package com.skytala.eCommerce.domain.shipment.relations.shipment.control.status;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.status.AddShipmentStatus;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.status.DeleteShipmentStatus;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.status.UpdateShipmentStatus;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.status.ShipmentStatusAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.status.ShipmentStatusDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.status.ShipmentStatusFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.status.ShipmentStatusUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.status.ShipmentStatusMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.status.ShipmentStatus;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.status.FindShipmentStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentStatuss")
public class ShipmentStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentStatus
	 * @return a List with the ShipmentStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentStatus>> findShipmentStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentStatussBy query = new FindShipmentStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentStatus> shipmentStatuss =((ShipmentStatusFound) Scheduler.execute(query).data()).getShipmentStatuss();

		return ResponseEntity.ok().body(shipmentStatuss);

	}

	/**
	 * creates a new ShipmentStatus entry in the ofbiz database
	 * 
	 * @param shipmentStatusToBeAdded
	 *            the ShipmentStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentStatus> createShipmentStatus(@RequestBody ShipmentStatus shipmentStatusToBeAdded) throws Exception {

		AddShipmentStatus command = new AddShipmentStatus(shipmentStatusToBeAdded);
		ShipmentStatus shipmentStatus = ((ShipmentStatusAdded) Scheduler.execute(command).data()).getAddedShipmentStatus();
		
		if (shipmentStatus != null) 
			return successful(shipmentStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentStatus with the specific Id
	 * 
	 * @param shipmentStatusToBeUpdated
	 *            the ShipmentStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentStatus(@RequestBody ShipmentStatus shipmentStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentStatusToBeUpdated.setnull(null);

		UpdateShipmentStatus command = new UpdateShipmentStatus(shipmentStatusToBeUpdated);

		try {
			if(((ShipmentStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentStatusId}")
	public ResponseEntity<ShipmentStatus> findById(@PathVariable String shipmentStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentStatusId", shipmentStatusId);
		try {

			List<ShipmentStatus> foundShipmentStatus = findShipmentStatussBy(requestParams).getBody();
			if(foundShipmentStatus.size()==1){				return successful(foundShipmentStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentStatusId}")
	public ResponseEntity<String> deleteShipmentStatusByIdUpdated(@PathVariable String shipmentStatusId) throws Exception {
		DeleteShipmentStatus command = new DeleteShipmentStatus(shipmentStatusId);

		try {
			if (((ShipmentStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
