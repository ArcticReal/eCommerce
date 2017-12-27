package com.skytala.eCommerce.domain.product.relations.facility.control.contactMech;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.contactMech.AddFacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facility.command.contactMech.DeleteFacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facility.command.contactMech.UpdateFacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMech.FacilityContactMechAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMech.FacilityContactMechDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMech.FacilityContactMechFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.contactMech.FacilityContactMechUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.contactMech.FacilityContactMechMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.contactMech.FacilityContactMech;
import com.skytala.eCommerce.domain.product.relations.facility.query.contactMech.FindFacilityContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityContactMechs")
public class FacilityContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityContactMech
	 * @return a List with the FacilityContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityContactMech>> findFacilityContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityContactMechsBy query = new FindFacilityContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityContactMech> facilityContactMechs =((FacilityContactMechFound) Scheduler.execute(query).data()).getFacilityContactMechs();

		return ResponseEntity.ok().body(facilityContactMechs);

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
	public ResponseEntity<FacilityContactMech> createFacilityContactMech(HttpServletRequest request) throws Exception {

		FacilityContactMech facilityContactMechToBeAdded = new FacilityContactMech();
		try {
			facilityContactMechToBeAdded = FacilityContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFacilityContactMech(facilityContactMechToBeAdded);

	}

	/**
	 * creates a new FacilityContactMech entry in the ofbiz database
	 * 
	 * @param facilityContactMechToBeAdded
	 *            the FacilityContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityContactMech> createFacilityContactMech(@RequestBody FacilityContactMech facilityContactMechToBeAdded) throws Exception {

		AddFacilityContactMech command = new AddFacilityContactMech(facilityContactMechToBeAdded);
		FacilityContactMech facilityContactMech = ((FacilityContactMechAdded) Scheduler.execute(command).data()).getAddedFacilityContactMech();
		
		if (facilityContactMech != null) 
			return successful(facilityContactMech);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityContactMech with the specific Id
	 * 
	 * @param facilityContactMechToBeUpdated
	 *            the FacilityContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityContactMech(@RequestBody FacilityContactMech facilityContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityContactMechToBeUpdated.setnull(null);

		UpdateFacilityContactMech command = new UpdateFacilityContactMech(facilityContactMechToBeUpdated);

		try {
			if(((FacilityContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityContactMechId}")
	public ResponseEntity<FacilityContactMech> findById(@PathVariable String facilityContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityContactMechId", facilityContactMechId);
		try {

			List<FacilityContactMech> foundFacilityContactMech = findFacilityContactMechsBy(requestParams).getBody();
			if(foundFacilityContactMech.size()==1){				return successful(foundFacilityContactMech.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityContactMechId}")
	public ResponseEntity<String> deleteFacilityContactMechByIdUpdated(@PathVariable String facilityContactMechId) throws Exception {
		DeleteFacilityContactMech command = new DeleteFacilityContactMech(facilityContactMechId);

		try {
			if (((FacilityContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
