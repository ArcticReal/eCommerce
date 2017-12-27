package com.skytala.eCommerce.domain.shipment;

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
import com.skytala.eCommerce.domain.shipment.command.AddShipment;
import com.skytala.eCommerce.domain.shipment.command.DeleteShipment;
import com.skytala.eCommerce.domain.shipment.command.UpdateShipment;
import com.skytala.eCommerce.domain.shipment.event.ShipmentAdded;
import com.skytala.eCommerce.domain.shipment.event.ShipmentDeleted;
import com.skytala.eCommerce.domain.shipment.event.ShipmentFound;
import com.skytala.eCommerce.domain.shipment.event.ShipmentUpdated;
import com.skytala.eCommerce.domain.shipment.mapper.ShipmentMapper;
import com.skytala.eCommerce.domain.shipment.model.Shipment;
import com.skytala.eCommerce.domain.shipment.query.FindShipmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.notFound;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Shipment
	 * @return a List with the Shipments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<Shipment>> findShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentsBy query = new FindShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Shipment> shipments =((ShipmentFound) Scheduler.execute(query).data()).getShipments();



		return ResponseEntity.ok().body(shipments);

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
	public ResponseEntity<Object> createShipment(HttpServletRequest request) throws Exception {

		Shipment shipmentToBeAdded = new Shipment();
		try {
			shipmentToBeAdded = ShipmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipment(shipmentToBeAdded);

	}

	/**
	 * creates a new Shipment entry in the ofbiz database
	 * 
	 * @param shipmentToBeAdded
	 *            the Shipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipment(@RequestBody Shipment shipmentToBeAdded) throws Exception {

		AddShipment command = new AddShipment(shipmentToBeAdded);
		Shipment shipment = ((ShipmentAdded) Scheduler.execute(command).data()).getAddedShipment();
		
		if (shipment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Shipment could not be created.");
	}

	/**
	 * Updates the Shipment with the specific Id
	 * 
	 * @param shipmentToBeUpdated
	 *            the Shipment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateShipment(@RequestBody Shipment shipmentToBeUpdated,
			@PathVariable String shipmentId) throws Exception {

		shipmentToBeUpdated.setShipmentId(shipmentId);

		UpdateShipment command = new UpdateShipment(shipmentToBeUpdated);

		try {
			if(((ShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentId}")
	public ResponseEntity<Shipment> findById(@PathVariable String shipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentId", shipmentId);
		try {

			List<Shipment> foundShipment = findShipmentsBy(requestParams).getBody();

			if(foundShipment.size()==1)
				return ResponseEntity.status(HttpStatus.OK).body(foundShipment.get(0));
			else
				return notFound();
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentId}")
	public ResponseEntity<Object> deleteShipmentByIdUpdated(@PathVariable String shipmentId) throws Exception {
		DeleteShipment command = new DeleteShipment(shipmentId);

		try {
			if (((ShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Shipment could not be deleted");

	}

}
