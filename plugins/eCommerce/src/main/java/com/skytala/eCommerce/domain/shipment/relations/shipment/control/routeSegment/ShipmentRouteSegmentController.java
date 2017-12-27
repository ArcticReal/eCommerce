package com.skytala.eCommerce.domain.shipment.relations.shipment.control.routeSegment;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.routeSegment.AddShipmentRouteSegment;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.routeSegment.DeleteShipmentRouteSegment;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.routeSegment.UpdateShipmentRouteSegment;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment.ShipmentRouteSegmentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment.ShipmentRouteSegmentDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment.ShipmentRouteSegmentFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment.ShipmentRouteSegmentUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.routeSegment.ShipmentRouteSegmentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.routeSegment.FindShipmentRouteSegmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentRouteSegments")
public class ShipmentRouteSegmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentRouteSegmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentRouteSegment
	 * @return a List with the ShipmentRouteSegments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentRouteSegment>> findShipmentRouteSegmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentRouteSegmentsBy query = new FindShipmentRouteSegmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentRouteSegment> shipmentRouteSegments =((ShipmentRouteSegmentFound) Scheduler.execute(query).data()).getShipmentRouteSegments();

		return ResponseEntity.ok().body(shipmentRouteSegments);

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
	public ResponseEntity<ShipmentRouteSegment> createShipmentRouteSegment(HttpServletRequest request) throws Exception {

		ShipmentRouteSegment shipmentRouteSegmentToBeAdded = new ShipmentRouteSegment();
		try {
			shipmentRouteSegmentToBeAdded = ShipmentRouteSegmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShipmentRouteSegment(shipmentRouteSegmentToBeAdded);

	}

	/**
	 * creates a new ShipmentRouteSegment entry in the ofbiz database
	 * 
	 * @param shipmentRouteSegmentToBeAdded
	 *            the ShipmentRouteSegment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentRouteSegment> createShipmentRouteSegment(@RequestBody ShipmentRouteSegment shipmentRouteSegmentToBeAdded) throws Exception {

		AddShipmentRouteSegment command = new AddShipmentRouteSegment(shipmentRouteSegmentToBeAdded);
		ShipmentRouteSegment shipmentRouteSegment = ((ShipmentRouteSegmentAdded) Scheduler.execute(command).data()).getAddedShipmentRouteSegment();
		
		if (shipmentRouteSegment != null) 
			return successful(shipmentRouteSegment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentRouteSegment with the specific Id
	 * 
	 * @param shipmentRouteSegmentToBeUpdated
	 *            the ShipmentRouteSegment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentRouteSegmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentRouteSegment(@RequestBody ShipmentRouteSegment shipmentRouteSegmentToBeUpdated,
			@PathVariable String shipmentRouteSegmentId) throws Exception {

		shipmentRouteSegmentToBeUpdated.setShipmentRouteSegmentId(shipmentRouteSegmentId);

		UpdateShipmentRouteSegment command = new UpdateShipmentRouteSegment(shipmentRouteSegmentToBeUpdated);

		try {
			if(((ShipmentRouteSegmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentRouteSegmentId}")
	public ResponseEntity<ShipmentRouteSegment> findById(@PathVariable String shipmentRouteSegmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentRouteSegmentId", shipmentRouteSegmentId);
		try {

			List<ShipmentRouteSegment> foundShipmentRouteSegment = findShipmentRouteSegmentsBy(requestParams).getBody();
			if(foundShipmentRouteSegment.size()==1){				return successful(foundShipmentRouteSegment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentRouteSegmentId}")
	public ResponseEntity<String> deleteShipmentRouteSegmentByIdUpdated(@PathVariable String shipmentRouteSegmentId) throws Exception {
		DeleteShipmentRouteSegment command = new DeleteShipmentRouteSegment(shipmentRouteSegmentId);

		try {
			if (((ShipmentRouteSegmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
