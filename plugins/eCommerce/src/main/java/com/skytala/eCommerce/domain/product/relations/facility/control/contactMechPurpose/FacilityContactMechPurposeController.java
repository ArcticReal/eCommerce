package com.skytala.eCommerce.domain.product.relations.facility.control.contactMechPurpose;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.contactMechPurpose.AddFacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facility.command.contactMechPurpose.DeleteFacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facility.command.contactMechPurpose.UpdateFacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose.FacilityContactMechPurposeAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose.FacilityContactMechPurposeDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose.FacilityContactMechPurposeFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose.FacilityContactMechPurposeUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.contactMechPurpose.FacilityContactMechPurposeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.contactMechPurpose.FacilityContactMechPurpose;
import com.skytala.eCommerce.domain.product.relations.facility.query.contactMechPurpose.FindFacilityContactMechPurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityContactMechPurposes")
public class FacilityContactMechPurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityContactMechPurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityContactMechPurpose
	 * @return a List with the FacilityContactMechPurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityContactMechPurpose>> findFacilityContactMechPurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityContactMechPurposesBy query = new FindFacilityContactMechPurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityContactMechPurpose> facilityContactMechPurposes =((FacilityContactMechPurposeFound) Scheduler.execute(query).data()).getFacilityContactMechPurposes();

		return ResponseEntity.ok().body(facilityContactMechPurposes);

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
	public ResponseEntity<FacilityContactMechPurpose> createFacilityContactMechPurpose(HttpServletRequest request) throws Exception {

		FacilityContactMechPurpose facilityContactMechPurposeToBeAdded = new FacilityContactMechPurpose();
		try {
			facilityContactMechPurposeToBeAdded = FacilityContactMechPurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFacilityContactMechPurpose(facilityContactMechPurposeToBeAdded);

	}

	/**
	 * creates a new FacilityContactMechPurpose entry in the ofbiz database
	 * 
	 * @param facilityContactMechPurposeToBeAdded
	 *            the FacilityContactMechPurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityContactMechPurpose> createFacilityContactMechPurpose(@RequestBody FacilityContactMechPurpose facilityContactMechPurposeToBeAdded) throws Exception {

		AddFacilityContactMechPurpose command = new AddFacilityContactMechPurpose(facilityContactMechPurposeToBeAdded);
		FacilityContactMechPurpose facilityContactMechPurpose = ((FacilityContactMechPurposeAdded) Scheduler.execute(command).data()).getAddedFacilityContactMechPurpose();
		
		if (facilityContactMechPurpose != null) 
			return successful(facilityContactMechPurpose);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityContactMechPurpose with the specific Id
	 * 
	 * @param facilityContactMechPurposeToBeUpdated
	 *            the FacilityContactMechPurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityContactMechPurpose(@RequestBody FacilityContactMechPurpose facilityContactMechPurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityContactMechPurposeToBeUpdated.setnull(null);

		UpdateFacilityContactMechPurpose command = new UpdateFacilityContactMechPurpose(facilityContactMechPurposeToBeUpdated);

		try {
			if(((FacilityContactMechPurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityContactMechPurposeId}")
	public ResponseEntity<FacilityContactMechPurpose> findById(@PathVariable String facilityContactMechPurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityContactMechPurposeId", facilityContactMechPurposeId);
		try {

			List<FacilityContactMechPurpose> foundFacilityContactMechPurpose = findFacilityContactMechPurposesBy(requestParams).getBody();
			if(foundFacilityContactMechPurpose.size()==1){				return successful(foundFacilityContactMechPurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityContactMechPurposeId}")
	public ResponseEntity<String> deleteFacilityContactMechPurposeByIdUpdated(@PathVariable String facilityContactMechPurposeId) throws Exception {
		DeleteFacilityContactMechPurpose command = new DeleteFacilityContactMechPurpose(facilityContactMechPurposeId);

		try {
			if (((FacilityContactMechPurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
