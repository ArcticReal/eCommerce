package com.skytala.eCommerce.domain.product.relations.facility;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.AddFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.DeleteFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.UpdateFacility;
import com.skytala.eCommerce.domain.product.relations.facility.event.FacilityAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.FacilityDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.FacilityFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.FacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.FacilityMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.Facility;
import com.skytala.eCommerce.domain.product.relations.facility.query.FindFacilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facilitys")
public class FacilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Facility
	 * @return a List with the Facilitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Facility>> findFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilitysBy query = new FindFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Facility> facilitys =((FacilityFound) Scheduler.execute(query).data()).getFacilitys();

		return ResponseEntity.ok().body(facilitys);

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
	public ResponseEntity<Facility> createFacility(HttpServletRequest request) throws Exception {

		Facility facilityToBeAdded = new Facility();
		try {
			facilityToBeAdded = FacilityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFacility(facilityToBeAdded);

	}

	/**
	 * creates a new Facility entry in the ofbiz database
	 * 
	 * @param facilityToBeAdded
	 *            the Facility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Facility> createFacility(@RequestBody Facility facilityToBeAdded) throws Exception {

		AddFacility command = new AddFacility(facilityToBeAdded);
		Facility facility = ((FacilityAdded) Scheduler.execute(command).data()).getAddedFacility();
		
		if (facility != null) 
			return successful(facility);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Facility with the specific Id
	 * 
	 * @param facilityToBeUpdated
	 *            the Facility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{facilityId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacility(@RequestBody Facility facilityToBeUpdated,
			@PathVariable String facilityId) throws Exception {

		facilityToBeUpdated.setFacilityId(facilityId);

		UpdateFacility command = new UpdateFacility(facilityToBeUpdated);

		try {
			if(((FacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityId}")
	public ResponseEntity<Facility> findById(@PathVariable String facilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityId", facilityId);
		try {

			List<Facility> foundFacility = findFacilitysBy(requestParams).getBody();
			if(foundFacility.size()==1){				return successful(foundFacility.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityId}")
	public ResponseEntity<String> deleteFacilityByIdUpdated(@PathVariable String facilityId) throws Exception {
		DeleteFacility command = new DeleteFacility(facilityId);

		try {
			if (((FacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
