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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/shipmentStatuss")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShipmentStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentStatussBy query = new FindShipmentStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentStatus> shipmentStatuss =((ShipmentStatusFound) Scheduler.execute(query).data()).getShipmentStatuss();

		if (shipmentStatuss.size() == 1) {
			return ResponseEntity.ok().body(shipmentStatuss.get(0));
		}

		return ResponseEntity.ok().body(shipmentStatuss);

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
	public ResponseEntity<Object> createShipmentStatus(HttpServletRequest request) throws Exception {

		ShipmentStatus shipmentStatusToBeAdded = new ShipmentStatus();
		try {
			shipmentStatusToBeAdded = ShipmentStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createShipmentStatus(shipmentStatusToBeAdded);

	}

	/**
	 * creates a new ShipmentStatus entry in the ofbiz database
	 * 
	 * @param shipmentStatusToBeAdded
	 *            the ShipmentStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createShipmentStatus(@RequestBody ShipmentStatus shipmentStatusToBeAdded) throws Exception {

		AddShipmentStatus command = new AddShipmentStatus(shipmentStatusToBeAdded);
		ShipmentStatus shipmentStatus = ((ShipmentStatusAdded) Scheduler.execute(command).data()).getAddedShipmentStatus();
		
		if (shipmentStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shipmentStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShipmentStatus could not be created.");
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
	public boolean updateShipmentStatus(HttpServletRequest request) throws Exception {

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

		ShipmentStatus shipmentStatusToBeUpdated = new ShipmentStatus();

		try {
			shipmentStatusToBeUpdated = ShipmentStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShipmentStatus(shipmentStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShipmentStatus(@RequestBody ShipmentStatus shipmentStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentStatusToBeUpdated.setnull(null);

		UpdateShipmentStatus command = new UpdateShipmentStatus(shipmentStatusToBeUpdated);

		try {
			if(((ShipmentStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shipmentStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String shipmentStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentStatusId", shipmentStatusId);
		try {

			Object foundShipmentStatus = findShipmentStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShipmentStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shipmentStatusId}")
	public ResponseEntity<Object> deleteShipmentStatusByIdUpdated(@PathVariable String shipmentStatusId) throws Exception {
		DeleteShipmentStatus command = new DeleteShipmentStatus(shipmentStatusId);

		try {
			if (((ShipmentStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShipmentStatus could not be deleted");

	}

}
