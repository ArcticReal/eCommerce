package com.skytala.eCommerce.domain.product.relations.facility.control.carrierShipment;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.carrierShipment.AddFacilityCarrierShipment;
import com.skytala.eCommerce.domain.product.relations.facility.command.carrierShipment.DeleteFacilityCarrierShipment;
import com.skytala.eCommerce.domain.product.relations.facility.command.carrierShipment.UpdateFacilityCarrierShipment;
import com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment.FacilityCarrierShipmentAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment.FacilityCarrierShipmentDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment.FacilityCarrierShipmentFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment.FacilityCarrierShipmentUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.carrierShipment.FacilityCarrierShipmentMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;
import com.skytala.eCommerce.domain.product.relations.facility.query.carrierShipment.FindFacilityCarrierShipmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityCarrierShipments")
public class FacilityCarrierShipmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityCarrierShipmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityCarrierShipment
	 * @return a List with the FacilityCarrierShipments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityCarrierShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityCarrierShipmentsBy query = new FindFacilityCarrierShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityCarrierShipment> facilityCarrierShipments =((FacilityCarrierShipmentFound) Scheduler.execute(query).data()).getFacilityCarrierShipments();

		if (facilityCarrierShipments.size() == 1) {
			return ResponseEntity.ok().body(facilityCarrierShipments.get(0));
		}

		return ResponseEntity.ok().body(facilityCarrierShipments);

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
	public ResponseEntity<Object> createFacilityCarrierShipment(HttpServletRequest request) throws Exception {

		FacilityCarrierShipment facilityCarrierShipmentToBeAdded = new FacilityCarrierShipment();
		try {
			facilityCarrierShipmentToBeAdded = FacilityCarrierShipmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityCarrierShipment(facilityCarrierShipmentToBeAdded);

	}

	/**
	 * creates a new FacilityCarrierShipment entry in the ofbiz database
	 * 
	 * @param facilityCarrierShipmentToBeAdded
	 *            the FacilityCarrierShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityCarrierShipment(@RequestBody FacilityCarrierShipment facilityCarrierShipmentToBeAdded) throws Exception {

		AddFacilityCarrierShipment command = new AddFacilityCarrierShipment(facilityCarrierShipmentToBeAdded);
		FacilityCarrierShipment facilityCarrierShipment = ((FacilityCarrierShipmentAdded) Scheduler.execute(command).data()).getAddedFacilityCarrierShipment();
		
		if (facilityCarrierShipment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityCarrierShipment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityCarrierShipment could not be created.");
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
	public boolean updateFacilityCarrierShipment(HttpServletRequest request) throws Exception {

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

		FacilityCarrierShipment facilityCarrierShipmentToBeUpdated = new FacilityCarrierShipment();

		try {
			facilityCarrierShipmentToBeUpdated = FacilityCarrierShipmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityCarrierShipment(facilityCarrierShipmentToBeUpdated, facilityCarrierShipmentToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityCarrierShipment with the specific Id
	 * 
	 * @param facilityCarrierShipmentToBeUpdated
	 *            the FacilityCarrierShipment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityCarrierShipment(@RequestBody FacilityCarrierShipment facilityCarrierShipmentToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		facilityCarrierShipmentToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateFacilityCarrierShipment command = new UpdateFacilityCarrierShipment(facilityCarrierShipmentToBeUpdated);

		try {
			if(((FacilityCarrierShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityCarrierShipmentId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityCarrierShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityCarrierShipmentId", facilityCarrierShipmentId);
		try {

			Object foundFacilityCarrierShipment = findFacilityCarrierShipmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityCarrierShipment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityCarrierShipmentId}")
	public ResponseEntity<Object> deleteFacilityCarrierShipmentByIdUpdated(@PathVariable String facilityCarrierShipmentId) throws Exception {
		DeleteFacilityCarrierShipment command = new DeleteFacilityCarrierShipment(facilityCarrierShipmentId);

		try {
			if (((FacilityCarrierShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityCarrierShipment could not be deleted");

	}

}
