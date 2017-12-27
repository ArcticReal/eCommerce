package com.skytala.eCommerce.domain.shipment.relations.shipment.control.receiptRole;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.receiptRole.AddShipmentReceiptRole;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.receiptRole.DeleteShipmentReceiptRole;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.receiptRole.UpdateShipmentReceiptRole;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole.ShipmentReceiptRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole.ShipmentReceiptRoleDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole.ShipmentReceiptRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole.ShipmentReceiptRoleUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receiptRole.ShipmentReceiptRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole.ShipmentReceiptRole;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.receiptRole.FindShipmentReceiptRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentReceiptRoles")
public class ShipmentReceiptRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentReceiptRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentReceiptRole
	 * @return a List with the ShipmentReceiptRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentReceiptRole>> findShipmentReceiptRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentReceiptRolesBy query = new FindShipmentReceiptRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentReceiptRole> shipmentReceiptRoles =((ShipmentReceiptRoleFound) Scheduler.execute(query).data()).getShipmentReceiptRoles();

		return ResponseEntity.ok().body(shipmentReceiptRoles);

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
	public ResponseEntity<ShipmentReceiptRole> createShipmentReceiptRole(HttpServletRequest request) throws Exception {

		ShipmentReceiptRole shipmentReceiptRoleToBeAdded = new ShipmentReceiptRole();
		try {
			shipmentReceiptRoleToBeAdded = ShipmentReceiptRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShipmentReceiptRole(shipmentReceiptRoleToBeAdded);

	}

	/**
	 * creates a new ShipmentReceiptRole entry in the ofbiz database
	 * 
	 * @param shipmentReceiptRoleToBeAdded
	 *            the ShipmentReceiptRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentReceiptRole> createShipmentReceiptRole(@RequestBody ShipmentReceiptRole shipmentReceiptRoleToBeAdded) throws Exception {

		AddShipmentReceiptRole command = new AddShipmentReceiptRole(shipmentReceiptRoleToBeAdded);
		ShipmentReceiptRole shipmentReceiptRole = ((ShipmentReceiptRoleAdded) Scheduler.execute(command).data()).getAddedShipmentReceiptRole();
		
		if (shipmentReceiptRole != null) 
			return successful(shipmentReceiptRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentReceiptRole with the specific Id
	 * 
	 * @param shipmentReceiptRoleToBeUpdated
	 *            the ShipmentReceiptRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentReceiptRole(@RequestBody ShipmentReceiptRole shipmentReceiptRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		shipmentReceiptRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateShipmentReceiptRole command = new UpdateShipmentReceiptRole(shipmentReceiptRoleToBeUpdated);

		try {
			if(((ShipmentReceiptRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentReceiptRoleId}")
	public ResponseEntity<ShipmentReceiptRole> findById(@PathVariable String shipmentReceiptRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentReceiptRoleId", shipmentReceiptRoleId);
		try {

			List<ShipmentReceiptRole> foundShipmentReceiptRole = findShipmentReceiptRolesBy(requestParams).getBody();
			if(foundShipmentReceiptRole.size()==1){				return successful(foundShipmentReceiptRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentReceiptRoleId}")
	public ResponseEntity<String> deleteShipmentReceiptRoleByIdUpdated(@PathVariable String shipmentReceiptRoleId) throws Exception {
		DeleteShipmentReceiptRole command = new DeleteShipmentReceiptRole(shipmentReceiptRoleId);

		try {
			if (((ShipmentReceiptRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
