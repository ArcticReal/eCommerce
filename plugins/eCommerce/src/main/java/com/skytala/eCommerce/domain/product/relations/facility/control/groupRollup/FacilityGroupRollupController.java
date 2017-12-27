package com.skytala.eCommerce.domain.product.relations.facility.control.groupRollup;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRollup.AddFacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRollup.DeleteFacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupRollup.UpdateFacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupRollup.FacilityGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupRollup.FacilityGroupRollup;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupRollup.FindFacilityGroupRollupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityGroupRollups")
public class FacilityGroupRollupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupRollupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupRollup
	 * @return a List with the FacilityGroupRollups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityGroupRollup>> findFacilityGroupRollupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupRollupsBy query = new FindFacilityGroupRollupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupRollup> facilityGroupRollups =((FacilityGroupRollupFound) Scheduler.execute(query).data()).getFacilityGroupRollups();

		return ResponseEntity.ok().body(facilityGroupRollups);

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
	public ResponseEntity<FacilityGroupRollup> createFacilityGroupRollup(HttpServletRequest request) throws Exception {

		FacilityGroupRollup facilityGroupRollupToBeAdded = new FacilityGroupRollup();
		try {
			facilityGroupRollupToBeAdded = FacilityGroupRollupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFacilityGroupRollup(facilityGroupRollupToBeAdded);

	}

	/**
	 * creates a new FacilityGroupRollup entry in the ofbiz database
	 * 
	 * @param facilityGroupRollupToBeAdded
	 *            the FacilityGroupRollup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityGroupRollup> createFacilityGroupRollup(@RequestBody FacilityGroupRollup facilityGroupRollupToBeAdded) throws Exception {

		AddFacilityGroupRollup command = new AddFacilityGroupRollup(facilityGroupRollupToBeAdded);
		FacilityGroupRollup facilityGroupRollup = ((FacilityGroupRollupAdded) Scheduler.execute(command).data()).getAddedFacilityGroupRollup();
		
		if (facilityGroupRollup != null) 
			return successful(facilityGroupRollup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityGroupRollup with the specific Id
	 * 
	 * @param facilityGroupRollupToBeUpdated
	 *            the FacilityGroupRollup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityGroupRollup(@RequestBody FacilityGroupRollup facilityGroupRollupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityGroupRollupToBeUpdated.setnull(null);

		UpdateFacilityGroupRollup command = new UpdateFacilityGroupRollup(facilityGroupRollupToBeUpdated);

		try {
			if(((FacilityGroupRollupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityGroupRollupId}")
	public ResponseEntity<FacilityGroupRollup> findById(@PathVariable String facilityGroupRollupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupRollupId", facilityGroupRollupId);
		try {

			List<FacilityGroupRollup> foundFacilityGroupRollup = findFacilityGroupRollupsBy(requestParams).getBody();
			if(foundFacilityGroupRollup.size()==1){				return successful(foundFacilityGroupRollup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityGroupRollupId}")
	public ResponseEntity<String> deleteFacilityGroupRollupByIdUpdated(@PathVariable String facilityGroupRollupId) throws Exception {
		DeleteFacilityGroupRollup command = new DeleteFacilityGroupRollup(facilityGroupRollupId);

		try {
			if (((FacilityGroupRollupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
