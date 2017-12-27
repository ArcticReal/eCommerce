package com.skytala.eCommerce.domain.shipment.relations.shipment.control.paccage;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.paccage.AddShipmentPackage;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.paccage.DeleteShipmentPackage;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.paccage.UpdateShipmentPackage;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage.ShipmentPackageAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage.ShipmentPackageDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage.ShipmentPackageFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage.ShipmentPackageUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.paccage.ShipmentPackageMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.paccage.ShipmentPackage;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.paccage.FindShipmentPackagesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentPackages")
public class ShipmentPackageController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentPackageController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentPackage
	 * @return a List with the ShipmentPackages
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentPackage>> findShipmentPackagesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentPackagesBy query = new FindShipmentPackagesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentPackage> shipmentPackages =((ShipmentPackageFound) Scheduler.execute(query).data()).getShipmentPackages();

		return ResponseEntity.ok().body(shipmentPackages);

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
	public ResponseEntity<ShipmentPackage> createShipmentPackage(HttpServletRequest request) throws Exception {

		ShipmentPackage shipmentPackageToBeAdded = new ShipmentPackage();
		try {
			shipmentPackageToBeAdded = ShipmentPackageMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShipmentPackage(shipmentPackageToBeAdded);

	}

	/**
	 * creates a new ShipmentPackage entry in the ofbiz database
	 * 
	 * @param shipmentPackageToBeAdded
	 *            the ShipmentPackage thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentPackage> createShipmentPackage(@RequestBody ShipmentPackage shipmentPackageToBeAdded) throws Exception {

		AddShipmentPackage command = new AddShipmentPackage(shipmentPackageToBeAdded);
		ShipmentPackage shipmentPackage = ((ShipmentPackageAdded) Scheduler.execute(command).data()).getAddedShipmentPackage();
		
		if (shipmentPackage != null) 
			return successful(shipmentPackage);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentPackage with the specific Id
	 * 
	 * @param shipmentPackageToBeUpdated
	 *            the ShipmentPackage thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{shipmentPackageSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentPackage(@RequestBody ShipmentPackage shipmentPackageToBeUpdated,
			@PathVariable String shipmentPackageSeqId) throws Exception {

		shipmentPackageToBeUpdated.setShipmentPackageSeqId(shipmentPackageSeqId);

		UpdateShipmentPackage command = new UpdateShipmentPackage(shipmentPackageToBeUpdated);

		try {
			if(((ShipmentPackageUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentPackageId}")
	public ResponseEntity<ShipmentPackage> findById(@PathVariable String shipmentPackageId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentPackageId", shipmentPackageId);
		try {

			List<ShipmentPackage> foundShipmentPackage = findShipmentPackagesBy(requestParams).getBody();
			if(foundShipmentPackage.size()==1){				return successful(foundShipmentPackage.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentPackageId}")
	public ResponseEntity<String> deleteShipmentPackageByIdUpdated(@PathVariable String shipmentPackageId) throws Exception {
		DeleteShipmentPackage command = new DeleteShipmentPackage(shipmentPackageId);

		try {
			if (((ShipmentPackageDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
