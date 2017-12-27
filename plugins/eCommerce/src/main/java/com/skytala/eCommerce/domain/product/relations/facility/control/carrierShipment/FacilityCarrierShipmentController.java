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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityCarrierShipments")
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
	@GetMapping("/find")
	public ResponseEntity<List<FacilityCarrierShipment>> findFacilityCarrierShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityCarrierShipmentsBy query = new FindFacilityCarrierShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityCarrierShipment> facilityCarrierShipments =((FacilityCarrierShipmentFound) Scheduler.execute(query).data()).getFacilityCarrierShipments();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<FacilityCarrierShipment> createFacilityCarrierShipment(HttpServletRequest request) throws Exception {

		FacilityCarrierShipment facilityCarrierShipmentToBeAdded = new FacilityCarrierShipment();
		try {
			facilityCarrierShipmentToBeAdded = FacilityCarrierShipmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<FacilityCarrierShipment> createFacilityCarrierShipment(@RequestBody FacilityCarrierShipment facilityCarrierShipmentToBeAdded) throws Exception {

		AddFacilityCarrierShipment command = new AddFacilityCarrierShipment(facilityCarrierShipmentToBeAdded);
		FacilityCarrierShipment facilityCarrierShipment = ((FacilityCarrierShipmentAdded) Scheduler.execute(command).data()).getAddedFacilityCarrierShipment();
		
		if (facilityCarrierShipment != null) 
			return successful(facilityCarrierShipment);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFacilityCarrierShipment(@RequestBody FacilityCarrierShipment facilityCarrierShipmentToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		facilityCarrierShipmentToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateFacilityCarrierShipment command = new UpdateFacilityCarrierShipment(facilityCarrierShipmentToBeUpdated);

		try {
			if(((FacilityCarrierShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityCarrierShipmentId}")
	public ResponseEntity<FacilityCarrierShipment> findById(@PathVariable String facilityCarrierShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityCarrierShipmentId", facilityCarrierShipmentId);
		try {

			List<FacilityCarrierShipment> foundFacilityCarrierShipment = findFacilityCarrierShipmentsBy(requestParams).getBody();
			if(foundFacilityCarrierShipment.size()==1){				return successful(foundFacilityCarrierShipment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityCarrierShipmentId}")
	public ResponseEntity<String> deleteFacilityCarrierShipmentByIdUpdated(@PathVariable String facilityCarrierShipmentId) throws Exception {
		DeleteFacilityCarrierShipment command = new DeleteFacilityCarrierShipment(facilityCarrierShipmentId);

		try {
			if (((FacilityCarrierShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
