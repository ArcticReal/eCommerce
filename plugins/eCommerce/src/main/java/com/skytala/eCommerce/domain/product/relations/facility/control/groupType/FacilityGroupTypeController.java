package com.skytala.eCommerce.domain.product.relations.facility.control.groupType;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.groupType.AddFacilityGroupType;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupType.DeleteFacilityGroupType;
import com.skytala.eCommerce.domain.product.relations.facility.command.groupType.UpdateFacilityGroupType;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupType.FacilityGroupTypeAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupType.FacilityGroupTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupType.FacilityGroupTypeFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupType.FacilityGroupTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupType.FacilityGroupTypeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupType.FacilityGroupType;
import com.skytala.eCommerce.domain.product.relations.facility.query.groupType.FindFacilityGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityGroupTypes")
public class FacilityGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroupType
	 * @return a List with the FacilityGroupTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FacilityGroupType>> findFacilityGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupTypesBy query = new FindFacilityGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroupType> facilityGroupTypes =((FacilityGroupTypeFound) Scheduler.execute(query).data()).getFacilityGroupTypes();

		return ResponseEntity.ok().body(facilityGroupTypes);

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
	public ResponseEntity<FacilityGroupType> createFacilityGroupType(HttpServletRequest request) throws Exception {

		FacilityGroupType facilityGroupTypeToBeAdded = new FacilityGroupType();
		try {
			facilityGroupTypeToBeAdded = FacilityGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFacilityGroupType(facilityGroupTypeToBeAdded);

	}

	/**
	 * creates a new FacilityGroupType entry in the ofbiz database
	 * 
	 * @param facilityGroupTypeToBeAdded
	 *            the FacilityGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityGroupType> createFacilityGroupType(@RequestBody FacilityGroupType facilityGroupTypeToBeAdded) throws Exception {

		AddFacilityGroupType command = new AddFacilityGroupType(facilityGroupTypeToBeAdded);
		FacilityGroupType facilityGroupType = ((FacilityGroupTypeAdded) Scheduler.execute(command).data()).getAddedFacilityGroupType();
		
		if (facilityGroupType != null) 
			return successful(facilityGroupType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityGroupType with the specific Id
	 * 
	 * @param facilityGroupTypeToBeUpdated
	 *            the FacilityGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{facilityGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityGroupType(@RequestBody FacilityGroupType facilityGroupTypeToBeUpdated,
			@PathVariable String facilityGroupTypeId) throws Exception {

		facilityGroupTypeToBeUpdated.setFacilityGroupTypeId(facilityGroupTypeId);

		UpdateFacilityGroupType command = new UpdateFacilityGroupType(facilityGroupTypeToBeUpdated);

		try {
			if(((FacilityGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityGroupTypeId}")
	public ResponseEntity<FacilityGroupType> findById(@PathVariable String facilityGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupTypeId", facilityGroupTypeId);
		try {

			List<FacilityGroupType> foundFacilityGroupType = findFacilityGroupTypesBy(requestParams).getBody();
			if(foundFacilityGroupType.size()==1){				return successful(foundFacilityGroupType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityGroupTypeId}")
	public ResponseEntity<String> deleteFacilityGroupTypeByIdUpdated(@PathVariable String facilityGroupTypeId) throws Exception {
		DeleteFacilityGroupType command = new DeleteFacilityGroupType(facilityGroupTypeId);

		try {
			if (((FacilityGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
