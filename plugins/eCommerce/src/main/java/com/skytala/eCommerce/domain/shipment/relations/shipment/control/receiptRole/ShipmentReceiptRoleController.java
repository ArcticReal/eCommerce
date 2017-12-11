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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentReceiptRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentReceiptRolesBy query = new FindShipmentReceiptRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentReceiptRole> shipmentReceiptRoles =((ShipmentReceiptRoleFound) Scheduler.execute(query).data()).getShipmentReceiptRoles();

		if (shipmentReceiptRoles.size() == 1) {
			return ResponseEntity.ok().body(shipmentReceiptRoles.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShipmentReceiptRole(HttpServletRequest request) throws Exception {

		ShipmentReceiptRole shipmentReceiptRoleToBeAdded = new ShipmentReceiptRole();
		try {
			shipmentReceiptRoleToBeAdded = ShipmentReceiptRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createShipmentReceiptRole(@RequestBody ShipmentReceiptRole shipmentReceiptRoleToBeAdded) throws Exception {

		AddShipmentReceiptRole command = new AddShipmentReceiptRole(shipmentReceiptRoleToBeAdded);
		ShipmentReceiptRole shipmentReceiptRole = ((ShipmentReceiptRoleAdded) Scheduler.execute(command).data()).getAddedShipmentReceiptRole();
		
		if (shipmentReceiptRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentReceiptRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentReceiptRole could not be created.");
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
	public boolean updateShipmentReceiptRole(HttpServletRequest request) throws Exception {

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

		ShipmentReceiptRole shipmentReceiptRoleToBeUpdated = new ShipmentReceiptRole();

		try {
			shipmentReceiptRoleToBeUpdated = ShipmentReceiptRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentReceiptRole(shipmentReceiptRoleToBeUpdated, shipmentReceiptRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentReceiptRole(@RequestBody ShipmentReceiptRole shipmentReceiptRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		shipmentReceiptRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateShipmentReceiptRole command = new UpdateShipmentReceiptRole(shipmentReceiptRoleToBeUpdated);

		try {
			if(((ShipmentReceiptRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentReceiptRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentReceiptRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentReceiptRoleId", shipmentReceiptRoleId);
		try {

			Object foundShipmentReceiptRole = findShipmentReceiptRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentReceiptRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentReceiptRoleId}")
	public ResponseEntity<Object> deleteShipmentReceiptRoleByIdUpdated(@PathVariable String shipmentReceiptRoleId) throws Exception {
		DeleteShipmentReceiptRole command = new DeleteShipmentReceiptRole(shipmentReceiptRoleId);

		try {
			if (((ShipmentReceiptRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentReceiptRole could not be deleted");

	}

}
